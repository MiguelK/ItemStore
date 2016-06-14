package com.itemstore.engine;

import com.itemstore.engine.model.tag3.TagTree;

import java.util.List;

public interface ItemGroupFilter {

    List<Integer> getExcludeIds();

    List<Integer> getItemIds();

    int getMaxResult();

    TagTree getExcludeTag();

    TagTree getFavoriteTag();

    TagTree getIncludeOnlyTag();

}
