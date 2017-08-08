package com.itemstore.collector;

import com.itemstore.engine.model.Item;

import java.util.List;

public interface ItemCollector {

    List<Item> collect();

    int getPollFrequencyInSeconds();
}
