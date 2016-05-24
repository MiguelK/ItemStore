package com.flow.admin.impl;

import com.flow.engine.FlowEngine;
import com.flow.admin.Action;
import com.flow.admin.dto.StatisticsDTO;
import com.flow.engine.model.Item;

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
