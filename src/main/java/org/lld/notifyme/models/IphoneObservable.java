package org.lld.notifyme.models;

import java.util.ArrayList;
import java.util.List;

public class IphoneObservable implements InventoryObservable {
    private List<InventoryObserver> observers;
    private int count;
    public IphoneObservable() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void add(InventoryObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void remove(InventoryObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.stream().forEach(InventoryObserver::update);
    }

    @Override
    public void setCount(int pCount) {
        //Meaning previously the count was and the
        //newly added count increases the count.
        if(this.count == 0 && pCount != 0)
            notifyObservers();
        this.count += pCount;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
