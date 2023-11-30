package org.multilevel.cache.providers;

import org.multilevel.cache.exception.StorageFullException;
import org.multilevel.cache.policy.EvictionPolicy;
import org.multilevel.cache.storage.Storage;

public class DefaultCacheProvider<Key, Value> implements CacheProvider<Key, Value>{
    private final EvictionPolicy evictionPolicy;
    private final Storage<Key, Value> storage;

    public DefaultCacheProvider(EvictionPolicy evictionPolicy, Storage<Key, Value> storage) {
        this.evictionPolicy = evictionPolicy;
        this.storage = storage;
    }

    @Override
    public void set(Key key, Value value) {
        try {
            this.storage.put(key, value);
            this.evictionPolicy.keyAccessed(key);
        } catch (StorageFullException ex) {
            this.evictionPolicy.evict(key);
            this.storage.remove(key);
            set(key, value);
        }
    }

    @Override
    public Value get(Key key) {
        Value val = this.storage.get(key);
        this.evictionPolicy.keyAccessed(key);
        return val;
    }

    @Override
    public double getUsage() {
        return this.storage.currentUsage();
    }
}
