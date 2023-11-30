package org.lld.eventbus.models;

import java.util.Collections;
import java.util.Map;

public class Event {
    private final long id;
    private final String type;
    private final Map<String, Object> fields;
    private final long timeStamp;

    public Event(long id, String type, Map<String, Object> fields, long timeStamp) {
        this.id = id;
        this.type = type;
        this.fields = fields;
        this.timeStamp = timeStamp;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Map<String, Object> getFields() {
        return Collections.unmodifiableMap(fields);
    }

    public Long getTimeStamp() {
        return timeStamp;
    }
}
