package org.lld.logger.appenders;

public class ConsoleAppender implements LogAppender {
    @Override
    public void log(String message) {
        System.out.println("Console appender : "+message);
    }
}
