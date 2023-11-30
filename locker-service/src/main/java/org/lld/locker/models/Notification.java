package org.lld.locker.models;

import java.util.UUID;

public class Notification {
    private final String customerId;
    private final String lockerId;
    private final String code;
    private final String orderId;
    private final String id;
    public Notification(String customerId, String lockerId, String code, String orderId) {
        this.customerId = customerId;
        this.lockerId = lockerId;
        this.code = code;
        this.orderId = orderId;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getLockerId() {
        return lockerId;
    }

    public String getCode() {
        return code;
    }

    public String getOrderId() {
        return orderId;
    }
}
