package com.flow.admin.impl;

import com.flow.admin.Action;
import com.flow.admin.dto.EventDTO;
import com.flow.engine.event.Event;
import com.flow.engine.event.EventTracker;
import com.flow.engine.event.Events;

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
