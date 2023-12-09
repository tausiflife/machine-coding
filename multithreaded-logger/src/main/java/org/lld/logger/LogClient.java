package org.lld.logger;

public interface LogClient {

    void start(String processId, long timestamp);
    void end(String processId);
    String poll();
}
