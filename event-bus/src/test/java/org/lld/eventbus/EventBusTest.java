package org.lld.eventbus;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Test;
import org.lld.eventbus.models.Event;
import org.lld.eventbus.models.Subscription;
import org.lld.eventbus.models.SubscriptionType;
import org.lld.eventbus.models.Topic;
import org.lld.eventbus.retry.RetryAlgorithm;
import org.lld.eventbus.utils.EventTimer;

public class EventBusTest {

    @Test
    public void defaultTest() throws ExecutionException, InterruptedException {
        EventTimer timer = new EventTimer();
        EventBus eventBus = new EventBus(1, new RetryAlgorithm<>(5, ((currentTime) -> currentTime + 1000), timer),
                timer);
        var topic = new Topic("topic-1");
        Set<Event> eventsReceived = new HashSet<>();
        eventBus.publish(topic, new Event(1, "demo", new HashMap<>(), timer.getTime()));
        eventBus.subscribe(topic,
                new Subscription("subscription-1", "subscriber-1", SubscriptionType.PULL, "10.0.1.1", (event -> {
                    eventsReceived.add(event);
                    return null;
                })));
        var event = eventBus.poll(topic, "subscriber-1").toCompletableFuture().get();
        Assert.assertEquals(1, event.getId());
        Assert.assertEquals("demo", event.getType());
    }

    @Test
    public void defaultTestWithOneEventAndMultiplePoll() throws ExecutionException, InterruptedException {
        EventTimer timer = new EventTimer();
        EventBus eventBus = new EventBus(1, new RetryAlgorithm<>(5, ((currentTime) -> currentTime + 1000), timer),
                timer);
        var topic = new Topic("topic-1");
        Set<Event> eventsReceived = new HashSet<>();
        eventBus.publish(topic, new Event(1, "demo", new HashMap<>(), timer.getTime()));
        eventBus.subscribe(topic,
                new Subscription("subscription-1", "subscriber-1", SubscriptionType.PULL, "10.0.1.1", (event -> {
                    eventsReceived.add(event);
                    return null;
                })));
        var event = eventBus.poll(topic, "subscriber-1").toCompletableFuture().get();
        Assert.assertEquals(1, event.getId());
        Assert.assertEquals("demo", event.getType());
        event = eventBus.poll(topic, "subscriber-1").toCompletableFuture().get();
        Assert.assertNull(event);
    }

    @Test
    public void defaultTestPush() throws ExecutionException, InterruptedException {
        EventTimer timer = new EventTimer();
        EventBus eventBus = new EventBus(1, new RetryAlgorithm<>(5, ((currentTime) -> currentTime + 1000), timer),
                timer);
        var topic = new Topic("topic-1");
        eventBus.subscribe(topic,
                new Subscription("subscription-1", "subscriber-1", SubscriptionType.PUSH, "10.0.1.1", (event -> {
                    Assert.assertEquals(1, event.getId());
                    return null;
                })));
        eventBus.publish(topic, new Event(1, "demo", new HashMap<>(), timer.getTime()));
    }
}
