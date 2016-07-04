package com.itemstore.engine;

import com.itemstore.engine.model.tag3.ItemTagTree;

import java.util.List;

public interface ItemGroupFilter {

    List<Integer> getExcludeIds();

    List<Integer> getItemIds();

    int getMaxResult();

    ItemTagTree.Filter getExcludeTag();

    ItemTagTree.Filter getFavoriteTag();

    ItemTagTree.Filter getIncludeOnlyTag();

}
