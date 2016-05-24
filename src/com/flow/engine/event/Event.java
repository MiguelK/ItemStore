package com.flow.engine.event;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public final class Event {

    private static final Map<TheKey, Event> events = new HashMap<TheKey, Event>();
    private final AtomicInteger counter = new AtomicInteger(0);

    private String message;
    private Exception exception;
    private EventType eventType;
    private final TheKey thekey;


    private Event(TheKey thekey, String message, Exception exception, EventType eventType) {
        this.message = message;
        this.exception = exception;
        this.eventType = eventType;
        this.thekey = thekey;
    }

    public static Event create(String message, Exception exception, EventType eventType) {
        TheKey thekey = TheKey.create(eventType, message);
        Event event = events.get(thekey);
        if (event != null) {
            return event;
        }

        Event event1 = new Event(thekey, message, exception, eventType);
        events.put(thekey, event1);
        return event1;
    }

    public EventType getEventType() {
        return eventType;
    }

    @Override
    public String toString() {
        return "Event{" +
                "message='" + message + '\'' +
                ", eventType=" + eventType +
                '}' + hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (!thekey.equals(event.thekey)) return false;

        return true;
    }

    /*@Override
    public int hashCode() {
        return thekey.hashCode();
    }*/

    public int getCounte() {
        return counter.get();
    }

    public String getMessage() {
        return message;
    }

    public Exception getException() {
        return exception;
    }

    public void increaseCounter() {
        counter.incrementAndGet();
    }

    private static class TheKey {
        private EventType eventType;
        private String message;

        private TheKey(EventType eventType, String message) {
            this.eventType = eventType;
            this.message = message;
        }

        public static TheKey create(EventType eventType, String message) {
            return new TheKey(eventType, message);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TheKey thekey = (TheKey) o;

            if (eventType != thekey.eventType) return false;
            if (message != null ? !message.equals(thekey.message) : thekey.message != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = eventType.hashCode();
            result = 31 * result + (message != null ? message.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Thekey{" +
                    "eventType=" + eventType +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

}
