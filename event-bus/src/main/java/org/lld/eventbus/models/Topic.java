package org.lld.eventbus.models;

public class Topic {
    private final String name;

    public Topic(String id) {
        this.name = id;
    }

    public String getName() {
        return name;
    }
}
