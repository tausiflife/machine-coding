package org.lld.notifyme.models;

public class SmsAlertObserver implements InventoryObserver {
    private InventoryObservable observable;
    private User user;

    public SmsAlertObserver(InventoryObservable observable, User user) {
        this.observable = observable;
        this.user = user;
    }
    @Override
    public void update() {
        System.out.println("Send SMS to "+user.getPhoneNumber()+" alert the user about this item.");
    }
}
