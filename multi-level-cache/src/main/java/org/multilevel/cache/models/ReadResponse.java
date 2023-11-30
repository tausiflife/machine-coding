package org.multilevel.cache.models;

public class ReadResponse<Value> {
    private final Value val;
    private final double totalTime;

    public ReadResponse(Value val, double totalTime) {
        this.val = val;
        this.totalTime = totalTime;
    }

    public Value getVal() {
        return val;
    }

    public double getTotalTime() {
        return totalTime;
    }
}


