package com.itemstore.engine.event;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class EventsTest {
    private int totalExpectedChannelCoEvents = 3;

    @BeforeClass
    public void initialize() {
        Events.reset();
    }

   /* @Test
    public void fireEvent_1() throws InterruptedException {
        final AtomicInteger counter = new AtomicInteger();
        Events.registerListener(new EventListener() {
            @Override
            public void handleEvent(Event event) {
                counter.incrementAndGet();
            }
        });

        Events.fireEvent(EventType.ChannelCo);
        Thread.sleep(1000);

        Assert.assertEquals(counter.get(), 1);
    }*/

    @Test
    public void increase_2() throws InterruptedException {
        Events.fireEvent(EventType.ImageServletRequestCount);
        Events.fireEvent(EventType.ImageServletRequestCount);

        Thread.sleep(100);
        Assert.assertEquals(Events.getEventTracker().getCounter(EventType.ImageServletRequestCount), 2);
    }

    @Test
    public void sameEvent() throws InterruptedException {
        final AtomicInteger counter = new AtomicInteger(0);
        Events.registerListener(new EventListener() {
            @Override
            public void handleEvent(Event event) {
                counter.incrementAndGet();
            }
        });

        Events.fireEvent(EventType.PostCardChanged);
        Events.fireEvent(EventType.PostCardChanged);
        Thread.sleep(100);

        Assert.assertEquals(counter.get(), 2);
    }

    /*  @Test(invocationCount = 100, threadPoolSize = 20)
    public void fireEvent_Concurrent_200() throws InterruptedException {

        Events.registerListener(new EventListener() {
            @Override
            public void handleEvent(Event event) {
                countDownLatch1.countDown();
            }
        });

        for (int i = 0; i < COUNT_200; i++) {
            Events.fireEvent(PostCardChangeEvent.create());
            Events.fireEvent(PostCardChangeEvent.create());
        }

        countDownLatch1.await(2, TimeUnit.SECONDS);
        Assert.assertEquals(countDownLatch1.getCount(), 0);
    }

    @Test
    public void fireEvent_3() throws InterruptedException {
        int expectedEvents = 3;
        final CountDownLatch countDownLatch = new CountDownLatch(expectedEvents);

        Events.registerListener(new EventListener() {
            @Override
            public void handleEvent(Event eventType) {
                countDownLatch.countDown();
            }
        });

        Events.fireEvent(PostCardChangeEvent.create());
        Events.fireEvent(PostCardChangeEvent.create());
        Events.fireEvent(PostCardChangeEvent.create());

        countDownLatch.await(1, TimeUnit.SECONDS);

        Assert.assertEquals(countDownLatch.getCount(), 0);
    }



    @Test
    public void fireFailureEvent_2() throws InterruptedException {
        String value = "Some Value1";
        Exception e = new IllegalArgumentException("Oops");
        Events.fireEvent(FailureEvent.create("Something went wrong", e));    //componi same type or same message
        Events.fireEvent(FailureEvent.create("Something went wrong", e));    //componi same type or same message

        Thread.sleep(2000);

        EventTracker eventTracker = Events.getEventTracker();
        List<FailureEvent> events = eventTracker.getFailureEvents();


        Assert.assertEquals(events.size(), 1);
        Assert.assertEquals(events.get(0).getCounter(), 2);
        Assert.assertEquals(events.get(0).getMessage(), "Something went wrong");
        Assert.assertEquals(events.get(0).getException(), e);
    }*/
}