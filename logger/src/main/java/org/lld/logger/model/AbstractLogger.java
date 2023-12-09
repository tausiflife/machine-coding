package org.lld.logger.model;

public abstract class AbstractLogger {
    protected Level level;
    protected AbstractLogger nextLogger;

    protected void setNextLogger(AbstractLogger logger) {
        nextLogger = logger;
    }

    public void logMessage(Level pLevel, String message, LogObservable pObservable) {
        if (this.level == pLevel)
            display(message, pObservable);
        if (nextLogger != null)
            nextLogger.logMessage(pLevel, message, pObservable);
    }

    protected abstract void display(String message, LogObservable pObservable);
}
