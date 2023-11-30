package org.lld.conrefenceroom.models;

public class ConferenceRoom {
    private String roomID;
    private int capacity;

    public ConferenceRoom(String roomID, int capacity) {
        this.roomID = roomID;
        this.capacity = capacity;
    }

    public String getRoomID() {
        return roomID;
    }

    public int getCapacity() {
        return capacity;
    }
}
