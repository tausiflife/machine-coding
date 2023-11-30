package org.lld.eventbus.utils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class KeyedExecutor {
    private final Executor[] executors;

    public KeyedExecutor(final int threads) {
        this.executors = new Executor[threads];
        for (int i = 0; i < threads; i++) {
            this.executors[i] = Executors.newSingleThreadExecutor();
        }
    }

    public CompletionStage<Void> submit(final String id, final Runnable task) {
        return CompletableFuture.runAsync(task, this.executors[id.hashCode() % executors.length]);
    }

    public <T> CompletableFuture<T> submit(final String id, final Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(supplier, this.executors[id.hashCode() % executors.length]);
    }
}
