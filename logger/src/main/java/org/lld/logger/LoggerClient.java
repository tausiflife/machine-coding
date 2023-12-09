package org.lld.logger;

import org.lld.logger.model.Logger;

public class LoggerClient {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.info("info message");
        logger.debug("debug message");
        logger.error("error message");
    }
}
