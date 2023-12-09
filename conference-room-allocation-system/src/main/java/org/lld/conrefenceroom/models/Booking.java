package org.lld.conrefenceroom.models;

public class Booking {
    private final String bookingID;
    private final String userId;
    private final String conferenceRoomId;
    private final long startTime;
    private final long endTime;

    public Booking(String bookingID, String pUserId, String pConferenceRoomId, long startTime, long endTime) {
        this.bookingID = bookingID;
        this.startTime = startTime;
        this.endTime = endTime;
        userId = pUserId;
        conferenceRoomId = pConferenceRoomId;
    }

    public String getBookingID() {
        return bookingID;
    }

    public String getUserId() {
        return userId;
    }

    public String getConferenceRoomId() {
        return conferenceRoomId;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingID='" + bookingID + '\'' +
                ", userId='" + userId + '\'' +
                ", conferenceRoomId='" + conferenceRoomId + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
