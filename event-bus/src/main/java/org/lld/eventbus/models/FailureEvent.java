package org.lld.eventbus.models;

public class FailureEvent extends Event{
    private final Event event;
    private final long timeStamp;
    private final Throwable exception;

    public FailureEvent(Event event, long timeStamp, Throwable exception) {
        super(event.getId(), event.getType(), event.getFields(), event.getTimeStamp());
        this.event = event;
        this.timeStamp = timeStamp;
        this.exception = exception;
    }

}
