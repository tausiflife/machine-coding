package org.lld.cache;


import org.lld.cache.models.AccessDetails;
import org.lld.cache.models.EvictionAlgorithm;
import org.lld.cache.models.Record;
import org.lld.cache.models.WriteAlgorithm;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Cache<KEY,VALUE> {
    private final Storage<KEY, VALUE> storage;
    private final Map<KEY, Record<KEY,VALUE>> map;
    private final WriteAlgorithm writeAlgorithm;
    private final EvictionAlgorithm evictionAlgorithm;
    private final static int THRESHOLD_SIZE = 500;
    private final Integer expiryTimeInMillis;
    private final ConcurrentSkipListMap<Long, List<Record<KEY,VALUE>>> expiryQueue;
    private final ConcurrentSkipListMap<AccessDetails, List<Record<KEY, VALUE>>> priorityQueue;
    public Cache(Storage<KEY, VALUE> storage, WriteAlgorithm writeAlgorithm, EvictionAlgorithm evictionAlgorithm, Integer expiryTimeInMillis) {
        this.storage = storage;
        this.writeAlgorithm = writeAlgorithm;
        this.evictionAlgorithm = evictionAlgorithm;
        this.expiryTimeInMillis = expiryTimeInMillis;
        this.map = new ConcurrentHashMap<>();
        expiryQueue = new ConcurrentSkipListMap<>();
        priorityQueue = new ConcurrentSkipListMap<>((first, second)-> {
            final var older = (int) (first.getAccessTimeStamp() - first.getAccessTimeStamp());
             if (evictionAlgorithm.equals(EvictionAlgorithm.LRU)) {
                 return older;
             } else {
                 return (int) (first.getAccessCount() == second.getAccessCount() ? older :
                                          first.getAccessCount() - second.getAccessCount());
             }
        });
    }

    public VALUE get(KEY key) {
        VALUE value;
        final Record<KEY, VALUE> record = map.get(key);
        final AccessDetails accessDetails = record.getAccessDetails();
        if (map.containsKey(key))
            value = record.getValue();
            //if key is expired we remove it from the cache and load from storage
            if(hasExpired(record)) {
                expiryQueue.get(accessDetails.getAccessTimeStamp()).remove(record);
                priorityQueue.get(accessDetails).remove(record);
                value = storage.load(key);
            }
        else {
            value = storage.load(key);
            set(key ,value);
        }
        accessDetails.update(System.currentTimeMillis());
        expiryQueue.putIfAbsent(accessDetails.getAccessTimeStamp(), new CopyOnWriteArrayList<>());
        expiryQueue.get(accessDetails.getAccessTimeStamp()).add(record);
        priorityQueue.putIfAbsent(accessDetails, new CopyOnWriteArrayList<>());
        expiryQueue.get(accessDetails).add(record);
        return value;
    }

    private boolean hasExpired(Record<KEY, VALUE> record) {
        return record.getInsertionTime() <= System.currentTimeMillis() - expiryTimeInMillis;
    }

    public void set(KEY key, VALUE value) {
        Record<KEY,VALUE> record = new Record<>(key,value, insertionTime);
        if(!map.containsKey(key) && map.size() >= THRESHOLD_SIZE) {
            //Depending on the EvictionPolicy do the eviction LRU AND LFU
        }
        if(writeAlgorithm.equals(WriteAlgorithm.WRITE_THROUGH)) {
            storage.persist(key, value);
            map.put(key, record);
        } else {
            //write back, we need to persist data regularly
            map.put(key, record);
            //when do we update record, either its TTL has expired
            //or the data is updated, hence we should update the database
            storage.persist(key, value);
        }
    }
}
