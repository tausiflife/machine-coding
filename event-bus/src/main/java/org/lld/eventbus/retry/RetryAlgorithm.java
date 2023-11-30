package org.lld.eventbus.retry;

import java.util.function.Function;

import org.lld.eventbus.exceptions.RetryLimitExceededException;
import org.lld.eventbus.utils.EventTimer;

public class RetryAlgorithm<PARAMETER, RESULT> {
    private final int maxAttempts;
    private final Function<Long, Long> timeIncreaseFunction;
    private final EventTimer timer;

    public RetryAlgorithm(int maxAttempts, Function<Long, Long> timeIncreaseFunction, EventTimer timer) {
        this.maxAttempts = maxAttempts;
        this.timeIncreaseFunction = timeIncreaseFunction;
        this.timer = timer;
    }

    public RESULT attempt(final Function<PARAMETER, RESULT> task, final PARAMETER parameter) throws RetryLimitExceededException {
        int attempt = 1;
        while (attempt <= maxAttempts) {
            try {
                return task.apply(parameter);
            } catch (Exception e) {
                if (attempt == maxAttempts)
                    throw new RetryLimitExceededException();
            }
            long currentTime = timer.getTime();
            long timeToSleep = timeIncreaseFunction.apply(currentTime);
            try {
                Thread.sleep(timeToSleep);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            attempt++;
        }
        throw new RuntimeException();
    }
}
