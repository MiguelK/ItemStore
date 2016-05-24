package com.itemstore.admin.impl;

import com.itemstore.admin.Action;
import com.itemstore.admin.dto.EventDTO;
import com.itemstore.engine.event.Event;
import com.itemstore.engine.event.EventTracker;
import com.itemstore.engine.event.Events;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowEventsAction extends Action {

    @Override
    protected Object execute(HttpServletRequest request) {
        EventTracker eventTracker = Events.getEventTracker();

        List<Event> allEvents = eventTracker.getAllEvents();

        return EventDTO.map(allEvents);
    }
}
