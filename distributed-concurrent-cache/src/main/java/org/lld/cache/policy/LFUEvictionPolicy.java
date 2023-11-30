package org.lld.cache.policy;

public class LFUEvictionPolicy<KEY,VALUE> implements EvictionPolicy<KEY,VALUE>{

    @Override
    public void evictKey(KEY key) {
        //return null;
    }

    @Override
    public void keyAccessed(KEY key) {
        //return null;
    }
}
