package com.itemstore.engine;

import com.itemstore.engine.model.tag3.ItemTagTree;
import com.itemstore.engine.model.tag3.TagTreeFilter;

import java.util.List;

public interface ItemGroupFilter {

    List<Integer> getExcludeIds();

    List<Integer> getItemIds();

    int getMaxResult();

    TagTreeFilter getExcludeTag();

    TagTreeFilter getFavoriteTag();

    TagTreeFilter getIncludeOnlyTag();

}
