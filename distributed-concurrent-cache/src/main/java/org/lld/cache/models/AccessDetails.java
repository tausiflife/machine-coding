package org.lld.cache.models;

import java.util.concurrent.atomic.AtomicLong;

public class AccessDetails {
    private final long accessTimeStamp;
    private final AtomicLong accessCount;

    public AccessDetails(long accessTimeStamp) {
        this.accessTimeStamp = accessTimeStamp;
        accessCount = new AtomicLong();
    }

    public long getAccessTimeStamp() {
        return accessTimeStamp;
    }

    public long getAccessCount() {
        return accessCount.get();
    }
    public AccessDetails update(long lastAccessTime) {
        final AccessDetails accessDetails = new AccessDetails(lastAccessTime);
        accessDetails.accessCount.getAndIncrement();
        return accessDetails;
    }
}
