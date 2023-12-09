package org.lld.logger.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lld.logger.appenders.LogAppender;

public class DefaultLogObservable implements LogObservable {
    private Map<Level, List<LogAppender>> appender;

    public DefaultLogObservable() {
        this.appender = new HashMap<>();
    }

    @Override
    public void addObserver(Level pLevel, LogAppender logAppender) {
        List<LogAppender> levelAppender = appender.getOrDefault(pLevel, new ArrayList<>());
        levelAppender.add(logAppender);
        appender.put(pLevel, levelAppender);
    }

    @Override
    public void notifyObserver(Level pLevel, String message) {
        List<LogAppender> levelAppender = appender.getOrDefault(pLevel, new ArrayList<>());
        levelAppender.stream().forEach(l -> l.log(message));
    }

}
