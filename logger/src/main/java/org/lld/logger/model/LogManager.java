package org.lld.logger.model;

import org.lld.logger.appenders.ConsoleAppender;
import org.lld.logger.appenders.FileAppender;

public class LogManager {
    static AbstractLogger chainOfLoggers() {
        AbstractLogger info = new InfoLogger(Level.INFO);
        AbstractLogger error = new ErrorLogger(Level.ERROR);
        info.setNextLogger(error);
        AbstractLogger debug = new DebugLogger(Level.DEBUG);
        error.setNextLogger(debug);
        return info;
    }

    static LogObservable addAppender() {
        LogObservable defaultLogObservable = new DefaultLogObservable();
        defaultLogObservable.addObserver(Level.INFO, new ConsoleAppender());
        defaultLogObservable.addObserver(Level.INFO, new FileAppender());

        defaultLogObservable.addObserver(Level.ERROR, new FileAppender());

        defaultLogObservable.addObserver(Level.DEBUG, new ConsoleAppender());
        defaultLogObservable.addObserver(Level.DEBUG, new FileAppender());
        return defaultLogObservable;
    }
}
