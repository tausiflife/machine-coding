package org.multilevel.cache.service;

import org.multilevel.cache.levels.MultiLevelCache;
import org.multilevel.cache.models.WriteResponse;

public class CacheService<Key,Value> {
    private final MultiLevelCache<Key, Value> multiLevelCache;
    private final int lastCount;

    public CacheService(MultiLevelCache<Key, Value> multiLevelCache, int lastCount) {
        this.multiLevelCache = multiLevelCache;
        this.lastCount = lastCount;
    }

    public WriteResponse set(Key key, Value value) {
        WriteResponse writeResponse = multiLevelCache.set(key,value);
        return writeResponse;
    }
}
