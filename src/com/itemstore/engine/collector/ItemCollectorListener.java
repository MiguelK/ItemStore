package com.itemstore.engine.collector;


import com.itemstore.model.Item;

import java.util.List;

public interface ItemCollectorListener {
    void handleNewItems(List<Item> items);
}
