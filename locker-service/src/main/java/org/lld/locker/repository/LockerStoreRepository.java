package org.lld.locker.repository;

import org.lld.locker.exception.NoLockerStoreFound;
import org.lld.locker.models.Location;
import org.lld.locker.models.LockerStore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LockerStoreRepository {
    private final Map<String, LockerStore> lockerStores;

    public LockerStoreRepository() {
        lockerStores = new HashMap<>();
    }

    public void addLockerStore(LockerStore lockerStore) {
        this.lockerStores.put(lockerStore.getId(), lockerStore);
    }

    public LockerStore getLockerStore(String lockerId) {
        return this.lockerStores.get(lockerId);
    }

    public LockerStore findLockerStores(Location location) {
        Optional<LockerStore> store =
                this.lockerStores.values().stream().filter(e -> e.getLocation().equals(location)).findFirst();
        if(store.isPresent())
            return store.get();
        throw new NoLockerStoreFound("Locker store not found at the location");
    }
}
