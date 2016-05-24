package com.flow.engine.event;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

public class EventTracker {

    private final Set<Event> allEvents = new CopyOnWriteArraySet<Event>();
    private final Map<EventType, Event> eventTypeEventMap = Collections.synchronizedMap(new HashMap<EventType, Event>());

    public void reset() {
        allEvents.clear();
    }

    public int getCounter(EventType eventType) {

        return eventTypeEventMap.get(eventType).getCounte();
    }

    public void register(Event event) {
        allEvents.add(event);
        eventTypeEventMap.put(event.getEventType(), event);
    }

    public List<Event> getAllEvents() {
        return new ArrayList<Event>(allEvents);
    }
}
