package org.multilevel.cache.policy;

public class LRUEvictionPolicy<Key> implements EvictionPolicy<Key>{
    @Override
    public void keyAccessed(Key key) {
        System.out.println("Key accessed "+ key);
    }

    @Override
    public void evict(Key key) {
        System.out.println("Key evicting "+ key);
    }
}
