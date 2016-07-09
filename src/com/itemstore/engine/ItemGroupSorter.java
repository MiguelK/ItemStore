package com.itemstore.engine;

import com.itemstore.engine.model.tag3.TagRoot;

import java.util.*;
import java.util.stream.Collectors;

class ItemGroupSorter<T extends ItemGroupSortable> {

    private static final int TOP_ITEMS_SORTEDD_BY_DATA = 5;
    private List<T> itemGroupsToSort;

    ItemGroupSorter(List<T> itemGroupsToSort) {
        this.itemGroupsToSort = itemGroupsToSort;
    }

    public List<? extends ItemGroupSortable> sort() {

        List<ItemGroupSortable> sorted = new ArrayList<>();

        if (itemGroupsToSort.size() <= TOP_ITEMS_SORTEDD_BY_DATA) {
            sorted.addAll(itemGroupsToSort);
            return sorted;
        }

        Comparator<ItemGroupSortable> tagRootOrder = (e1, e2) -> {
            TagRoot tagRootA = e1.getItemTagTree().getTagRoot();
            TagRoot tagRootB = e2.getItemTagTree().getTagRoot();

            Integer i1 = TagRoot.SORT_ORDER.indexOf(tagRootA);
            Integer i2 = TagRoot.SORT_ORDER.indexOf(tagRootB);

            return i1.compareTo(i2);
        };

        itemGroupsToSort = itemGroupsToSort.stream().sorted(tagRootOrder).collect(Collectors.toList());

        List<? extends ItemGroupSortable> prefix = itemGroupsToSort.subList(0, 5);
        sorted.addAll(prefix);


        List<ItemGroupSortable> rest = (List<ItemGroupSortable>) itemGroupsToSort.subList(5, itemGroupsToSort.size());

        //Bucket TagRoot, List<Item>
        //data = 3, nyheter =5 sport =1


        //loop
        Collections.shuffle(rest);
        sorted.addAll(rest);

        return sorted;
    }
}
