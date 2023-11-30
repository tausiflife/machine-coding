package org.multilevel.cache.models;


public class CacheCharacteristics {
    private final int readTime;
    private final int writeTime;

    public CacheCharacteristics(int readTime, int writeTime) {
        this.readTime = readTime;
        this.writeTime = writeTime;
    }

    public int getReadTime() {
        return readTime;
    }

    public int getWriteTime() {
        return writeTime;
    }
}
