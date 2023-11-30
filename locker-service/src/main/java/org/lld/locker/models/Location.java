package org.lld.locker.models;

import org.lld.locker.generator.IdGenerator;

import java.util.UUID;

public class Location {
    private final String latitude;
    private final String longitude;
    private final String id;
    public Location( String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.id = UUID.randomUUID().toString();;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getId() {
        return id;
    }
}
