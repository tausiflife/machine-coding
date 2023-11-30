package org.lld.notifyme.models;

public interface InventoryObservable {
    void add(InventoryObserver observer);
    void remove(InventoryObserver observer);
    void notifyObservers();
    void setCount(int count);
    int getCount();

}
