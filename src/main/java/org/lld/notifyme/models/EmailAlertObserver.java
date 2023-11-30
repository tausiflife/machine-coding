package org.lld.notifyme.models;

public class EmailAlertObserver implements InventoryObserver{
    private InventoryObservable observable;
    private User user;

    public EmailAlertObserver(InventoryObservable observable, User user) {
        this.observable = observable;
        this.user = user;
    }

    @Override
    public void update() {
        System.out.println("Send Email to "+user.getEmailId()+" alert the user about this item.");
    }
}
