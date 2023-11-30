package org.lld.locker.models;

import org.lld.locker.generator.IdGenerator;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LockerStore {
    private final String id;
    private final Location locationId;
    private final LocalTime openingTime;
    private final LocalTime closingTime;
    private List<Locker> lockers;
    public LockerStore(Location location, LocalTime openingTime, LocalTime closingTime) {
        this.id = UUID.randomUUID().toString();
        this.locationId = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.lockers = new ArrayList<>();
    }

    public List<Locker> getLockers() {
        return lockers;
    }

    public void addLocker(Locker locker) {
        this.lockers.add(locker);
    }

    public String getId() {
        return id;
    }

    public Location getLocation() {
        return locationId;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public boolean isStoreOpen(LocalDateTime pickUpTime) {
        return pickUpTime.toLocalTime().isAfter(openingTime) && pickUpTime.toLocalTime().isBefore(closingTime);
    }
}
