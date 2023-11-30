package org.lld.locker.models;

import java.util.UUID;

public class Item {
    private final String id;
    private final String desc;
    private final LockerSize size;

    public Item(String desc, LockerSize size) {
        this.id = UUID.randomUUID().toString();;
        this.desc = desc;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public LockerSize getSize() {
        return size;
    }
}
