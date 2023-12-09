package org.lld.logger.model;

public class ErrorLogger extends AbstractLogger {
    public ErrorLogger(Level pLevel) {
        this.level = pLevel;
    }

    @Override
    protected void display(String message, LogObservable pObservable) {
        pObservable.notifyObserver(this.level, message);
    }
}
