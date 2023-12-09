package org.lld.logger.appenders;

public class FileAppender implements LogAppender{

    @Override
    public void log(String message) {
        System.out.println("File appender : "+message);
    }
}
