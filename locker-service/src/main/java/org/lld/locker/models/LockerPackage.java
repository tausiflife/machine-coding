package org.lld.locker.models;

import org.lld.locker.exception.PickUpCodeExpiredException;

import java.time.LocalDateTime;

public class LockerPackage {
    private final int codeExpiryDays = 3;
    private String lockerId;
    private String orderId;
    private String code;
    private LocalDateTime packageDeliveryTime;

    public LockerPackage(String lockerId, String orderId, String code, LocalDateTime packageDeliveryTime) {
        this.lockerId = lockerId;
        this.orderId = orderId;
        this.code = code;
        this.packageDeliveryTime = packageDeliveryTime;
    }

    public String getLockerId() {
        return lockerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCode() {
        return code;
    }

    public boolean verifyCode(String pCode) {
        return this.code.equals(pCode);
    }

    public boolean isValidCode(LocalDateTime pickUpTime) {
        if(pickUpTime.compareTo(packageDeliveryTime) > codeExpiryDays)
            throw new PickUpCodeExpiredException("Pick up code expired.");
        return true;
    }
}
