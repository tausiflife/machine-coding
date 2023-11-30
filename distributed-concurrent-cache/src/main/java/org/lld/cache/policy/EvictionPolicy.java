package org.lld.cache.policy;

public interface EvictionPolicy<KEY, VALUE> {
    void evictKey(KEY key);
    void keyAccessed(KEY key);
}
