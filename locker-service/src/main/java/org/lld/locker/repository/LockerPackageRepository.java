package org.lld.locker.repository;

import org.lld.locker.models.LockerPackage;

import java.util.HashMap;
import java.util.Map;

public class LockerPackageRepository {
    private final Map<String, LockerPackage> lockerPackages;

    public LockerPackageRepository() {
        this.lockerPackages = new HashMap<>();
    }

    public void addPackage(LockerPackage aPackage) {
        this.lockerPackages.put(aPackage.getLockerId(), aPackage);
    }
    public LockerPackage findPackage(String lockerId) {
        return this.lockerPackages.get(lockerId);
    }
}
