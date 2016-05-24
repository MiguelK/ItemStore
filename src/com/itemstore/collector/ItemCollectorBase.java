package com.itemstore.collector;

import com.itemstore.engine.event.EventType;

import java.util.Collections;
import java.util.List;

public abstract class ItemCollectorBase implements ItemCollector {

    @Override
    public int getPollFrequencyInSeconds() {
        return ItemCollectorRunner.EXECUTE_ONLY_ONCE;
    }

    @Override
    public List<EventType> getPostProcessEvents() {
        return Collections.emptyList();
    }
}
