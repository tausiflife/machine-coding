package org.lld.conrefenceroom;

import org.lld.conrefenceroom.models.ConferenceRoom;
import org.lld.conrefenceroom.models.User;
import org.lld.conrefenceroom.models.Booking;
import org.lld.conrefenceroom.service.ConferenceRoomAllocationService;


public class Main {
    public static void main(String[] args) {
        // Example usage of the conference room allocation system
        ConferenceRoomAllocationService allocationSystem = new ConferenceRoomAllocationService();

        // Adding users
        User user1 = new User("U1", "John Doe");
        allocationSystem.addUser(user1);

        // Adding conference rooms
        ConferenceRoom room1 = new ConferenceRoom("R1", 10);
        allocationSystem.addConferenceRoom(room1);

        // Allocating a room
        long startTime = System.currentTimeMillis();
        long endTime = startTime + 3600000; // 1 hour later
        int participants = 5;
        Booking booking = allocationSystem.allocateRoom(user1.getUserID(), startTime, endTime, participants);

        // Displaying booking details
        if (booking != null) {
            System.out.println("Booking ID: " + booking.getBookingID());
            System.out.println("User: " + booking.getUserId());
            System.out.println("Room: " + booking.getConferenceRoomId());
            System.out.println("Start Time: " + booking.getStartTime());
            System.out.println("End Time: " + booking.getEndTime());
        }
    }
}
