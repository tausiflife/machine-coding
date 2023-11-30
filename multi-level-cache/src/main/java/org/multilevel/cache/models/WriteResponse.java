package org.multilevel.cache.models;

public class WriteResponse {
    private double totalTimeTaken;

    public WriteResponse(double totalTimeTaken) {
        this.totalTimeTaken = totalTimeTaken;
    }

    public double getTotalTimeTaken() {
        return totalTimeTaken;
    }
}
