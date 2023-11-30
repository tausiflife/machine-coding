package org.multilevel.cache.policy;

public interface EvictionPolicy<Key> {

    void keyAccessed(Key key);
    void evict(Key key);
}
