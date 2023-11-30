package org.lld.locker.service;

import org.lld.locker.exception.InvalidCodeException;
import org.lld.locker.exception.NoLockerFoundException;
import org.lld.locker.exception.StoreClosedException;
import org.lld.locker.models.*;
import org.lld.locker.repository.LockerPackageRepository;
import org.lld.locker.repository.LockerRepository;
import org.lld.locker.repository.LockerStoreRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public class LockerService {
    private final LockerRepository lockerRepository;
    private final LockerStoreRepository lockerStoreRepository;
    private final LockerPackageRepository lockerPackageRepository;
    public LockerService(LockerRepository lockerRepository, LockerStoreRepository lockerStoreRepository, LockerPackageRepository lockerPackageRepository) {
        this.lockerRepository = lockerRepository;
        this.lockerStoreRepository = lockerStoreRepository;
        this.lockerPackageRepository = lockerPackageRepository;
    }

    public void addLockerStore(LockerStore lockerStore) {
        this.lockerStoreRepository.addLockerStore(lockerStore);
    }

    public void addLockerToLockerStore(String lockerStoreId, Locker locker) {
        LockerStore store = this.lockerStoreRepository.getLockerStore(lockerStoreId);
        store.addLocker(locker);
        this.lockerRepository.addLocker(locker);

    }

    public void addLockerPackageToLocker(LockerPackage lockerPackage) {
        this.lockerPackageRepository.addPackage(lockerPackage);
    }

    public LockerPackage getLockerPackage(String lockerId) {
        return this.lockerPackageRepository.findPackage(lockerId);
    }
    public Locker getLocker(LockerSize lockerSize, Location location) {
        LockerStore lockerStore = lockerStoreRepository.findLockerStores(location);
        Optional<Locker> locker =
                lockerStore.getLockers().stream().filter(l -> checkLockerSize(l, lockerSize)
                && l.getLockerStatus() == LockerStatus.AVAILABLE).findFirst();
        if(locker.isPresent())
            return locker.get();
        throw new NoLockerFoundException("No locker of size is available at that location");
    }

    private boolean checkLockerSize(Locker l, LockerSize lockerSize) {
        return l.getLockerSize().equals(lockerSize) || l.getLockerSize().ordinal() > lockerSize.ordinal();
    }

    public void pickUpFromLocker(String lockerId, String code, LocalDateTime pickUpTime) {
        Locker locker = lockerRepository.getLocker(lockerId);
        LockerStore store = lockerStoreRepository.getLockerStore(locker.getStoreId());
        boolean storeOpen = store.isStoreOpen(pickUpTime);
        if(!storeOpen)
            throw new StoreClosedException("Store is closed at this time.");
        LockerPackage lockerPackage = this.lockerPackageRepository.findPackage(lockerId);
        lockerPackage.isValidCode(pickUpTime);
        boolean codeVerificationPassed = lockerPackage.verifyCode(code);
        if(!codeVerificationPassed)
            throw new InvalidCodeException("Invalid code!");
        locker.setLockerStatus(LockerStatus.AVAILABLE);
        System.out.println("Package picked up");
    }
}
