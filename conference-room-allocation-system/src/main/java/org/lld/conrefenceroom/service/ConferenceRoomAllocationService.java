package org.lld.conrefenceroom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.lld.conrefenceroom.models.Booking;
import org.lld.conrefenceroom.models.ConferenceRoom;
import org.lld.conrefenceroom.models.User;

public class ConferenceRoomAllocationService {
    private final List<User> users;
    private final ConcurrentSkipListMap<Integer, List<ConferenceRoom>> conferenceRooms;
    private final List<Booking> bookings;
    private final ConcurrentMap<String, ConcurrentSkipListMap<Long, Long>> roomBookedIntervals;

    public ConferenceRoomAllocationService() {
        this.users = new ArrayList<>();
        this.conferenceRooms = new ConcurrentSkipListMap<>();
        this.bookings = new CopyOnWriteArrayList<>();
        roomBookedIntervals = new ConcurrentHashMap<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addConferenceRoom(ConferenceRoom room) {
        conferenceRooms.putIfAbsent(room.getCapacity(), new ArrayList<>());
        conferenceRooms.get(room.getCapacity()).add(room);
    }

    public Booking allocateRoom(String userId, long startTime, long endTime, int participants) {
        ConferenceRoom availableRoom = findAvailableRoom(startTime, endTime, participants);
        if (availableRoom != null) {
            Booking booking = new Booking(generateBookingID(), userId, availableRoom.getRoomID(), startTime, endTime);
            bookings.add(booking);
            roomBookedIntervals.putIfAbsent(availableRoom.getRoomID(), new ConcurrentSkipListMap<>());
            roomBookedIntervals.get(availableRoom.getRoomID()).put(startTime, endTime);
            return booking;
        } else {
            System.out.println("No available room matching the constraints.");
            return null;
        }
    }

    private ConferenceRoom findAvailableRoom(long startTime, long endTime, int participants) {
        List<ConferenceRoom> conferenceRoomsWithSize = conferenceRooms.ceilingEntry(participants).getValue();
        for (ConferenceRoom room : conferenceRoomsWithSize) {
            if (isRoomAvailable(room.getRoomID(), startTime, endTime)) {
                return room;
            }
        }
        return null;
    }

    private boolean isRoomAvailable(final String roomId, long startTime, long endTime) {
        ConcurrentSkipListMap<Long, Long> roomIntervals = roomBookedIntervals.getOrDefault(roomId, new ConcurrentSkipListMap<>());
        synchronized (roomIntervals) {
            Long floorKey = roomIntervals.floorKey(startTime);
            if (floorKey != null && roomIntervals.get(floorKey) > startTime) {
                return false;
            }

            Long ceilingKey = roomIntervals.ceilingKey(startTime);
            if (ceilingKey != null && ceilingKey < endTime) {
                return false;
            }
            return true;
        }
    }

    private String generateBookingID() {
        return "B" + System.currentTimeMillis();
    }
}
