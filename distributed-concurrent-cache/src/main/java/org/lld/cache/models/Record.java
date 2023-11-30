package org.lld.cache.models;

public class Record<KEY,VALUE> {
    private final KEY key;
    private final VALUE value;
    private final long insertionTime;
    private final AccessDetails accessDetails;
    public Record(KEY key, VALUE value, long insertionTime) {
        this.key = key;
        this.value = value;
        this.insertionTime = insertionTime;
        accessDetails = new AccessDetails(insertionTime);
    }

    public VALUE getValue() {
        return value;
    }

    public long getInsertionTime() {
        return insertionTime;
    }

    public AccessDetails getAccessDetails() {
        return accessDetails;
    }
}
