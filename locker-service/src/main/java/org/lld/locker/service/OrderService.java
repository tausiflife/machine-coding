package org.lld.locker.service;

import org.lld.locker.models.Item;
import org.lld.locker.models.Location;
import org.lld.locker.models.Order;
import org.lld.locker.repository.OrderRepository;

import java.util.List;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void createOrder(Location geoLocation, List<Item> items, String customerId) {
        Order order = new Order(items, geoLocation, customerId);
        this.orderRepository.addOrder(order);
    }

    public Order findOrder(String orderId) {
        return this.orderRepository.getOrder(orderId);
    }
}
