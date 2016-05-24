package com.itemstore.admin.impl;

import com.itemstore.engine.FlowEngine;
import com.itemstore.admin.Action;
import com.itemstore.admin.dto.StatisticsDTO;
import com.itemstore.engine.model.Item;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

public class ItemListAllAction extends Action {

    public ItemListAllAction() {
    }

    @Override
    protected Object execute(HttpServletRequest request) {
        Collection<Item> allItems = FlowEngine.getInstance().searchItems(new org.apache.commons.collections4.Predicate<Item>() {
            @Override
            public boolean evaluate(Item item) {
                return true;
            }
        });

        StatisticsDTO s = new StatisticsDTO();
        s.itemsCount = allItems.size();

        return s;
    }
}
