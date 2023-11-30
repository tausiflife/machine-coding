package org.lld.locker.models;

import java.util.UUID;

public class Locker {
    private final String id;
    private final String locationId;
    private LockerStatus lockerStatus;
    private final LockerSize lockerSize;
    private final String storeId;

    public Locker(String locationId, LockerSize lockerSize, String storeId) {
        this.storeId = storeId;
        this.id = UUID.randomUUID().toString();
        this.locationId = locationId;
        this.lockerStatus = LockerStatus.AVAILABLE;
        this.lockerSize = lockerSize;
    }

    public String getStoreId() {
        return storeId;
    }

    public String getId() {
        return id;
    }

    public String getLocationId() {
        return locationId;
    }

    public LockerStatus getLockerStatus() {
        return lockerStatus;
    }

    public LockerSize getLockerSize() {
        return lockerSize;
    }

    public void setLockerStatus(LockerStatus lockerStatus) {
        this.lockerStatus = lockerStatus;
    }
}
