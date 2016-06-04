package com.itemstore.engine.model;

import com.itemstore.engine.model.tag3.TagTree;

import java.time.LocalDateTime;
import java.util.*;

public class ItemGroup {

    public static final Comparator<ItemGroup> PUBLISHED_DATE_SORTER = new LATEST_PUBLISHED_DATE_SORTER();

    private List<Item> items = new ArrayList<Item>();

    public void addItem(Item item) {
        items.add(item);
    }

    public TagTree getTags() {
        return items.get(0).getTags();
    }

    public int getId() {
        return items.get(0).getId();
    }

    public List<Item> getItems() {
        return items;
    }

    public void sortItemsByPublishedDate() {
        Collections.sort(items, Item.PUBLISHED_DATE_SORTER);
    }

    private static final class LATEST_PUBLISHED_DATE_SORTER implements Comparator<ItemGroup> {
        @Override
        public int compare(ItemGroup o1, ItemGroup o2) {
            return o2.getLatestItemPublishedDate().compareTo(o1.getLatestItemPublishedDate());

        }
    }
    private LocalDateTime getLatestItemPublishedDate() {
        return items.get(0).getPublishedDate(); //0 is always latest in group
    }


    @Override
    public String toString() {
        return "ItemGroup{" +
                "items=" + items +
                "latestItemPublishedDate=" + getLatestItemPublishedDate() + "}";
    }
}
