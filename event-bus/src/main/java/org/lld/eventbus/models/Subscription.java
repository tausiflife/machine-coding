package org.lld.eventbus.models;

import java.util.function.Function;

public class Subscription {
    private final String id;
    private final String subscriberId;
    private final SubscriptionType type;
    private final String ipAddress;
    private final Function<Event, Void> handler;
    public Subscription(String id, String subscriberId, SubscriptionType type, String ipAddress, Function<Event, Void> handler) {
        this.id = id;
        this.subscriberId = subscriberId;
        this.type = type;
        this.ipAddress = ipAddress;
        this.handler = handler;
    }

    public String getId() {
        return id;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public SubscriptionType getType() {
        return type;
    }

    public Function<Event, Void> handler() {
        return handler;
    }
}
