package org.lld.notifyme.service;

import org.lld.notifyme.models.*;

public class StoreService {
    public static void main(String[] args) {
        InventoryObservable observable = new IphoneObservable();
        User user = new User("user1@gmail.com", "8991221123");
        InventoryObserver observer1 = new EmailAlertObserver(observable, user);
        InventoryObserver observer2 = new SmsAlertObserver(observable, user);
        User user2 = new User("user2@gmail.com", "8991221124");
        InventoryObserver observer3 = new EmailAlertObserver(observable, user2);
        InventoryObserver observer4 = new SmsAlertObserver(observable, user2);
        observable.add(observer1);
        observable.add(observer2);
        observable.add(observer3);
        observable.add(observer4);
        observable.setCount(10);
    }
}
