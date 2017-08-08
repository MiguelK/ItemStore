package com.itemstore.admin.impl;

import com.itemstore.engine.ItemEngine;
import com.itemstore.admin.Action;
import com.itemstore.admin.dto.StatisticsDTO;
import com.itemstore.engine.model.Item;
import com.itemstore.engine.model.ItemGroup;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

public class ItemListAllAction extends Action {

    public ItemListAllAction() {
    }

    @Override
    protected Object execute(HttpServletRequest request) {
        List<ItemGroup> itemGroups = ItemEngine.getInstance().getAllItemGroupsSortedByDate(null);

        StatisticsDTO s = new StatisticsDTO();
        s.itemsCount = itemGroups.size();

        return s;
    }
}
