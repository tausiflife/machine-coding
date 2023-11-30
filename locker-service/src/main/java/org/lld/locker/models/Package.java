package org.lld.locker.models;

import org.lld.locker.generator.IdGenerator;

import java.net.IDN;
import java.util.List;
import java.util.UUID;

public class Package {
    private String id;
    private String orderId;
    private List<Item> items;
    private double packageSize;

    public Package(String orderId, List<Item> items) {
        this.id = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.items = items;
        updatePackageSize();
    }

    private void updatePackageSize() {
        this.packageSize = Math.random() * 10;
    }

    public double getPackageSize() {
        return packageSize;
    }
}
