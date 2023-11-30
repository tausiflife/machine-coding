package org.lld.logger;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.util.concurrent.CompletableFuture.allOf;
import static java.util.concurrent.CompletableFuture.runAsync;

public class LoggerTest {

    @Test
    void defaultLogging() throws InterruptedException, ExecutionException {
        final LogClient logClient = new LogClientImpl(10);
        List<CompletableFuture<Void>> tasks = new ArrayList<>();
        logClient.start("1", 1);
        logClient.start("2", 2);
        logClient.start("3", 3);
        logClient.end("3");
        logClient.end("2");
        tasks.add(runAsync(logClient::poll));
        tasks.add(runAsync(logClient::poll));
        logClient.end("1");
        tasks.add(runAsync(logClient::poll));
        allOf(tasks.toArray(CompletableFuture[]::new)).get();
    }
}
