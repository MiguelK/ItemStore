package com.itemstore.engine;

import com.itemstore.engine.model.tag3.TagRoot;

import java.util.*;
import java.util.stream.Collectors;

public class ItemGroupSorter<T extends ItemGroupSortable> {

    private List<T> itemGroupsToSort;

    public ItemGroupSorter(List<T> itemGroupsToSort) {
        this.itemGroupsToSort = itemGroupsToSort;
    }

    public List<? extends ItemGroupSortable> sort() {

        List<ItemGroupSortable> sorted = new ArrayList<>();

        if (itemGroupsToSort.size() <= 5) {
            sorted.addAll(itemGroupsToSort);
            return sorted;
        }

        TagRoot.values();

        Comparator<ItemGroupSortable> tagRootOrder = (e1, e2) -> {
            TagRoot tagRootA = e1.getItemTagTree().getTagRoot();
            TagRoot tagRootB = e2.getItemTagTree().getTagRoot();


            //Must contain all
            List<TagRoot> order = Arrays.asList(TagRoot.SWE_NYHETER_EXTRA, TagRoot.SWE_NYHETER, TagRoot.SWE_SPORT,
                    TagRoot.SWE_DATA,TagRoot.ENG_NEWS,TagRoot.ENG_SPORT);

            Integer i1 = order.indexOf(tagRootA);
            Integer i2 = order.indexOf(tagRootB);

            //if(tagRootA==TagRoot.SWE_NYHETER_EXTRA)
            return i1.compareTo(i2);
        };


        //Integer.compare(e1.getEmployeeNumber(), e2.getEmployeeNumber() );

        itemGroupsToSort = itemGroupsToSort.stream().sorted(tagRootOrder).collect(Collectors.toList());

        List<? extends ItemGroupSortable> prefix = itemGroupsToSort.subList(0, 5);
        sorted.addAll(prefix);


        List<ItemGroupSortable> rest = (List<ItemGroupSortable>) itemGroupsToSort.subList(5, itemGroupsToSort.size());

        //Bucket TagRoot, List<Item>
        //data = 3, nyheter =5 sport =1


        //loop
        Collections.shuffle(rest);
        sorted.addAll(rest);

        //itemGroupsToSort.stream()


        return sorted;
    }


}
