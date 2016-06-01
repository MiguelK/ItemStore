package com.itemstore.engine.event;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class EventDispatcher {

    private static final int THREADS = 2;
    private final EventTracker eventTracker;
    private final List<EventListener> itemCollectorEventListeners = new CopyOnWriteArrayList<EventListener>();
    private final ExecutorService threadPool;

    EventDispatcher() {
        threadPool = Executors.newFixedThreadPool(THREADS);
        eventTracker = new EventTracker();
    }

    void registerListenerImpl(EventListener listener) {
        itemCollectorEventListeners.add(listener);
    }

    public void dispach(Event event) {
        threadPool.submit(new EventDispatcherTask(event));
    }

    public EventTracker getEventTracker() {
        return eventTracker;
    }

    public void reset() {
        eventTracker.reset();//FIXME
    }

    private class EventDispatcherTask implements Runnable {
        private final Event event;

        private EventDispatcherTask(Event event) {
            this.event = event;
        }

        @Override
        public void run() {
            event.increaseCounter();

            for (EventListener listener : itemCollectorEventListeners) {
                listener.handleEvent(event);//FIXME
            }

            eventTracker.register(event);
        }
    }

}
