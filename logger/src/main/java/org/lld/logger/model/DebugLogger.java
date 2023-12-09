package org.lld.logger.model;

public class DebugLogger extends AbstractLogger {
    public DebugLogger(Level pLevel) {
        this.level = pLevel;
    }

    protected void display(String message, LogObservable pObservable) {
        pObservable.notifyObserver(this.level, message);
    }
}
