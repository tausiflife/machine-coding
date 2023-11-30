package org.lld.cache.policy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LRUEvictionPolicy<KEY, VALUE> implements EvictionPolicy<KEY, VALUE> {
    private final Map<KEY, LinkedNode<KEY, VALUE>> data;

    public LRUEvictionPolicy() {
        this.data = new ConcurrentHashMap<>();
    }

    @Override
    public void evictKey(KEY key) {
        // return null;
    }

    @Override
    public void keyAccessed(KEY key) {
        // return null;
    }
}

class LinkedNode<KEY, VALUE> {
    private final KEY key;
    private final VALUE value;

    public LinkedNode(KEY key, VALUE value) {
        this.key = key;
        this.value = value;
    }

    LinkedNode next;
    LinkedNode prev;
}
