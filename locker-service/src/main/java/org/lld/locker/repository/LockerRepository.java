package org.lld.locker.repository;

import org.lld.locker.models.Locker;
import org.lld.locker.models.LockerStore;

import java.util.HashMap;
import java.util.Map;

public class LockerRepository {
    private final Map<String, Locker> lockers;

    public LockerRepository() {
        this.lockers = new HashMap<>();
    }

    public void addLocker(Locker locker) {
        this.lockers.put(locker.getId(), locker);
    }

    public Locker getLocker(String lockerId) {
        return this.lockers.get(lockerId);
    }
}
