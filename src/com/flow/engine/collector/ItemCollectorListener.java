package com.flow.engine.collector;


import com.flow.engine.model.Item;

import java.util.List;

public interface ItemCollectorListener {
    void handleNewItems(List<Item> items);
}
