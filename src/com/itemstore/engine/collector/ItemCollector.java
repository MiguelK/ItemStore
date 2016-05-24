package com.itemstore.engine.collector;

import com.itemstore.engine.event.EventType;
import com.itemstore.model.Item;

import java.util.List;

public interface ItemCollector { //Collector

    List<Item> getItems(); //collect()

    int getPollFrequencyInSeconds();

    List<EventType> getPostProcessEvents();


}
