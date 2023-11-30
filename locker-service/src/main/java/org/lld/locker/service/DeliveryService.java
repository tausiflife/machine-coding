package org.lld.locker.service;

import org.lld.locker.models.*;
import org.lld.locker.models.Package;
import org.lld.locker.util.CodeGenerator;
import org.lld.locker.util.LockerSizeUtil;

import java.time.LocalDateTime;

public class DeliveryService {
    private final OrderService orderService;
    private final LockerService lockerService;
    private final NotificationService notificationService;

    public DeliveryService(OrderService orderService, LockerService lockerService, NotificationService notificationService) {
        this.orderService = orderService;
        this.lockerService = lockerService;
        this.notificationService = notificationService;
    }

    public void deliver(String orderId) {
        Order order = orderService.findOrder(orderId);
        Package aPackage = new Package(orderId, order.getItems());
        LockerSize lockerSize = LockerSizeUtil.getLockerSizeForPack(aPackage.getPackageSize());
        Locker locker = this.lockerService.getLocker(lockerSize, order.getGeoLocation());
        LockerPackage lockerPackage = new LockerPackage(locker.getId(), orderId,
                CodeGenerator.generateCode(6), LocalDateTime.now());
        lockerService.addLockerPackageToLocker(lockerPackage);
        locker.setLockerStatus(LockerStatus.CLOSED);
        notificationService.sendNotification(lockerPackage);
    }
}
