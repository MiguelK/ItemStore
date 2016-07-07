package com.itemstore.engine;

import com.itemstore.engine.model.tag3.ItemTagTree;
import com.itemstore.engine.model.tag3.TagTreeFilter;

import java.util.List;

public interface ItemGroupFilter {

    List<Integer> getExcludeIds();

    int getMaxResult();

    TagTreeFilter getExcludeTagTreeFilter();

    TagTreeFilter getIncludeTagTreeFilter();

}
