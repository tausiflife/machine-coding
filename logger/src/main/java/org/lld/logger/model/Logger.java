package org.lld.logger.model;

public class Logger {
    private volatile static Logger logger;
    private volatile static AbstractLogger chainOfLoggers;
    private volatile static LogObservable observable;
    private Logger() {
        if (this.logger != null) {
            throw new IllegalStateException("Object already initialized.");
        }
    }

    public static Logger getInstance() {
        if (logger == null) {
            synchronized (Logger.class) {
                if (logger == null) {
                    logger = new Logger();
                    chainOfLoggers = LogManager.chainOfLoggers();
                    observable = LogManager.addAppender();
                }
            }
        }
        return logger;
    }

    public void info(String message) {
        chainOfLoggers.logMessage(Level.INFO, message, observable);
    }

    public void debug(String message) {
        chainOfLoggers.logMessage(Level.DEBUG, message, observable);
    }

    public void error(String message) {
        chainOfLoggers.logMessage(Level.ERROR, message, observable);
    }
}
