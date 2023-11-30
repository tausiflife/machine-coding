package org.multilevel.cache.storage;

import org.multilevel.cache.exception.StorageFullException;

import java.util.HashMap;
import java.util.Map;

public class InMemoryStorage<Key,Value> implements Storage<Key, Value> {
    private final int capacity;
    private final Map<Key, Value> map;

    public InMemoryStorage(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
    }

    @Override
    public Value get(Key key) {
        return map.get(key);
    }

    @Override
    public void put(Key key, Value value) {
        if(isStorageFull())
            throw new StorageFullException();
        map.put(key, value);
    }

    @Override
    public void remove(Key key) {
        map.remove(key);
    }

    @Override
    public double currentUsage() {
        return 0;
    }

    private boolean isStorageFull() {
        return map.size() == capacity;
    }
}
