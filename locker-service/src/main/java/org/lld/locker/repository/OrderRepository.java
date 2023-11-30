package org.lld.locker.repository;

import org.lld.locker.models.Locker;
import org.lld.locker.models.Order;

import java.util.HashMap;
import java.util.Map;

public class OrderRepository {
    private final Map<String, Order> orders;

    public OrderRepository() {
        this.orders = new HashMap<>();
    }

    public void addOrder(Order order) {
        this.orders.put(order.getId(), order);
    }

    public Order getOrder(String orderId) {
        return this.orders.get(orderId);
    }
}
