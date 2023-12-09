package org.lld.logger.model;

public class InfoLogger extends AbstractLogger {

    public InfoLogger(Level pLevel) {
        this.level = pLevel;
    }

    @Override
    protected void display(String message, LogObservable pObservable) {
        pObservable.notifyObserver(this.level, message);
    }
}
