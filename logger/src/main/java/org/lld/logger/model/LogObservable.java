package org.lld.logger.model;

import org.lld.logger.appenders.LogAppender;

public interface LogObservable {
    void addObserver(Level pLevel, LogAppender logAppender);
    void notifyObserver(Level pLevel, String message);
}
