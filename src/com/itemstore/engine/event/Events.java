package com.itemstore.engine.event;

public class Events {
    private static final EventDispatcher EVENT_DISPATCHER = new EventDispatcher();

    private Events() {
    }

    public static void registerListener(EventListener listener) {
        EVENT_DISPATCHER.registerListenerImpl(listener);
    }

    public static EventTracker getEventTracker() {
        return EVENT_DISPATCHER.getEventTracker();
    }

    public static void reset() {
        EVENT_DISPATCHER.reset();     //FIXME
    }

    public static void fireEvent(EventType eventType) {
        Event event = Event.create("", null, eventType);
        EVENT_DISPATCHER.dispach(event);
    }
}
