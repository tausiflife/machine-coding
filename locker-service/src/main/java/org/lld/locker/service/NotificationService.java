package org.lld.locker.service;

import org.lld.locker.models.LockerPackage;
import org.lld.locker.models.Notification;
import org.lld.locker.models.Order;
import org.lld.locker.repository.NotificationRepository;

public class NotificationService {
    private final OrderService orderService;
    private final NotificationRepository notificationRepository;
    public NotificationService(OrderService orderService, NotificationRepository notificationRepository) {
        this.orderService = orderService;
        this.notificationRepository = notificationRepository;
    }

    public void sendNotification(LockerPackage lockerPackage) {
        Order order = orderService.findOrder(lockerPackage.getOrderId());
        Notification notification = new Notification(order.getCustomerId(),
                lockerPackage.getLockerId(), lockerPackage.getCode(), lockerPackage.getOrderId());
        this.notificationRepository.addNotification(notification);
    }
}
