package org.lld.locker.repository;

import org.lld.locker.models.Notification;

import java.util.HashMap;
import java.util.Map;

public class NotificationRepository {
    private final Map<String, Notification> notifications;

    public NotificationRepository() {
        this.notifications = new HashMap<>();
    }

    public void addNotification(Notification notification) {
        this.notifications.put(notification.getId(), notification);
    }

    public Notification findNotification(String notificationId) {
        return this.notifications.get(notificationId);
    }
}
