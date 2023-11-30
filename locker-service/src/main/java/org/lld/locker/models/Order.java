package org.lld.locker.models;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Order {
    private final String id;
    private final List<Item> items;
    private final Location geoLocation;
    private final String customerId;

    public Order(List<Item> items, Location geoLocation, String customerId) {
        this.items = items;
        this.geoLocation = geoLocation;
        this.customerId = customerId;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public Location getGeoLocation() {
        return geoLocation;
    }

    public String getCustomerId() {
        return customerId;
    }
}
