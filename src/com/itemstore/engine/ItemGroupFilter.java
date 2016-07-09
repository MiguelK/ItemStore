package com.itemstore.engine;

import com.itemstore.engine.model.tag3.TagTreeFilter;

import java.util.List;

public interface ItemGroupFilter {

    List<Integer> getExcludeIds();

    TagTreeFilter getExcludeTagTreeFilter();

    TagTreeFilter getIncludeTagTreeFilter();

    //List<? extends ItemGroupSortable> sort(List<? extends ItemGroupSortable> itemGroups);
   // ItemGroupSorter getItemGroupSorter();
}
