package com.itemstore.collector;


import com.itemstore.engine.model.Item;

import java.util.List;

public interface ItemCollectorListener {
    void handleNewItems(List<Item> items);
}
