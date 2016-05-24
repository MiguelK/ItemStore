package com.flow.engine.collector;

import com.flow.engine.event.EventType;
import com.flow.engine.model.Item;

import java.util.List;

public interface ItemCollector { //Collector

    List<Item> getItems(); //collect()

    int getPollFrequencyInSeconds();

    List<EventType> getPostProcessEvents();


}
