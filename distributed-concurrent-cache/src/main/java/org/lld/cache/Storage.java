package org.lld.cache;

import java.util.concurrent.CompletableFuture;

public class Storage<KEY, VALUE> {
    public VALUE load(KEY key) {
        return null;
    }

    public void persist(KEY key, VALUE value) {
        //return null;
    }
}
