package org.lld.logger;

public class Process {
    private final String id;
    private final long startTime;
    private long endTime;

    public Process(String id, long startTime) {
        this.id = id;
        this.startTime = startTime;
        endTime = -1;
    }

    public String getId() {
        return id;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
