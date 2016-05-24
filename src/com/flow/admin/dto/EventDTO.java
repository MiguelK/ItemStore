package com.flow.admin.dto;

import com.flow.engine.event.Event;

import java.util.ArrayList;
import java.util.List;

public class EventDTO {

    public String name;
    public int count;

    public static List<EventDTO> map(List<Event> events) {

        List<EventDTO> eventDTOs = new ArrayList<EventDTO>();
        for (Event event : events) {
            EventDTO eventDTO = new EventDTO();
            eventDTO.name = event.getEventType().name();
            eventDTO.count = event.getCounte();
            eventDTOs.add(eventDTO);
        }

        return eventDTOs;
    }
}
