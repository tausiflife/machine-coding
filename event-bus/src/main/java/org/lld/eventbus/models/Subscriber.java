package org.lld.eventbus.models;

import java.util.function.Function;

public class Subscriber {
    private final String id;
    private final String name;
    private final String ipAddress;
    private final Function<Event, Void> handler;
    public Subscriber(String id, String name, String ipAddress, Function<Event, Void> handler) {
        this.id = id;
        this.name = name;
        this.ipAddress = ipAddress;
        this.handler = handler;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Function<Event, Void> handler() {
        return handler;
    }
}
