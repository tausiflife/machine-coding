package org.lld.eventbus;

import java.util.*;
import java.util.concurrent.*;

import org.lld.eventbus.exceptions.RetryLimitExceededException;
import org.lld.eventbus.models.*;
import org.lld.eventbus.retry.RetryAlgorithm;
import org.lld.eventbus.utils.EventTimer;
import org.lld.eventbus.utils.KeyedExecutor;

public class EventBus {
    private final Map<Topic, List<Event>> topics;
    private final Map<Topic, Set<Subscription>> subscribers;
    private final KeyedExecutor readerExecutors;
    private final KeyedExecutor writerExecutors;
    private final Map<Topic, Map<String, Long>> subscriberIndexes;
    private final Map<Topic, ConcurrentSkipListMap<Long, Long>> eventTimeStamps;
    private final RetryAlgorithm<Event, Void> retryAlgorithm;
    private final int threadsCount;
    private EventBus deadLetterQueue;
    private final EventTimer timer;
    public EventBus(final int threadsCount, final RetryAlgorithm<Event, Void> retryAlgorithm, EventTimer timer) {
        this.retryAlgorithm = retryAlgorithm;
        this.timer = timer;
        this.threadsCount = threadsCount;
        this.topics = new ConcurrentHashMap<>();
        this.readerExecutors = new KeyedExecutor(threadsCount );
        this.writerExecutors = new KeyedExecutor(threadsCount);
        subscriberIndexes = new ConcurrentHashMap<>();
        subscribers = new ConcurrentHashMap<>();
        eventTimeStamps = new ConcurrentHashMap<>();
    }

    public void publish(final Topic topic, final Event event) {
        writerExecutors.submit(topic.getName(), () -> {
            addEventToBus(topic, event);
        });
    }

    private void addEventToBus(final Topic topic, final Event event) {
        this.topics.putIfAbsent(topic, new CopyOnWriteArrayList<>());
        this.topics.get(topic).add(event);
        this.eventTimeStamps.putIfAbsent(topic, new ConcurrentSkipListMap<>());
        this.eventTimeStamps.get(topic).put(event.getTimeStamp(), event.getId());
        this.subscribers
                .getOrDefault(topic, Collections.newSetFromMap(new ConcurrentHashMap<>()))
                .stream()
                .filter(s -> s.getType().equals(SubscriptionType.PUSH))
                .forEach(subscriber -> push(topic, subscriber, event));
    }

    public CompletionStage<Event> poll(final Topic topic, final String subscriberId) {
        return readerExecutors.submit(topic.getName() + subscriberId, () -> {
            final var index = this.subscriberIndexes.get(topic).getOrDefault(subscriberId, 0l);
            var event = getEvent(topic, subscriberId, index );
            setIndexFromEvent(topic, subscriberId, index + 1);
            return event;
        });
    }

    public void push(final Topic topic, final Subscription subscriber, final Event event) {
        try {
            retryAlgorithm.attempt(subscriber.handler(), event);
        } catch (RetryLimitExceededException e) {
            e.printStackTrace();
            if(deadLetterQueue == null)
                deadLetterQueue = new EventBus(threadsCount, retryAlgorithm, timer);
            deadLetterQueue.publish(new Topic(topic+"_dlq"), new FailureEvent(event, timer.getTime(), e));
        }
    }

    public void subscribe(final Topic topic, final Subscription subscription) {
        this.subscribers.putIfAbsent(topic, Collections.newSetFromMap(new ConcurrentHashMap<>()));
        this.subscribers.get(topic).add(subscription);
        this.subscriberIndexes.putIfAbsent(topic, new ConcurrentHashMap<>());
        this.subscriberIndexes.get(topic).putIfAbsent(subscription.getSubscriberId(), 0l);
    }

    private void setIndexFromEvent(final Topic topic, final String subscriberId, final long eventId) {
        this.subscriberIndexes.putIfAbsent(topic, new ConcurrentHashMap<>());
        this.subscriberIndexes.get(topic).put(subscriberId, eventId);
    }

    private void setIndexFromTimeStamp(final Topic topic, final String subscriberId, final long timestamp) {
        this.eventTimeStamps.putIfAbsent(topic, new ConcurrentSkipListMap<>());
        var eventId = this.eventTimeStamps.get(topic).higherEntry(timestamp).getValue();
        setIndexFromEvent(topic, subscriberId, eventId);
    }

    public Event getEventAfterTimeStamp(final Topic topic, final String subscriberId, final long timestamp) {
        var eventId = this.eventTimeStamps.get(topic).higherEntry(timestamp).getValue();
        var event = getEvent(topic, subscriberId, eventId);
        setIndexFromEvent(topic, subscriberId, eventId);
        return event;
    }

    public Event getEvent(final Topic topic, final String subscriberId, final long eventId) {
        if(this.topics.get(topic).size() <= eventId)
            return null;
        return this.topics.get(topic).get((int) eventId);
    }

}
