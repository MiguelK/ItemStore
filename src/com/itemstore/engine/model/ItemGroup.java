package com.itemstore.engine.model;

import com.itemstore.engine.model.tag3.TagTree;

import java.util.*;

public class ItemGroup {

    public static final Comparator<ItemGroup> PUBLISHED_DATE_SORTER = new LATEST_PUBLISHED_DATE_SORTER();

    private List<Item> items = new ArrayList<Item>();

    private Date getLatestItemCreatedDate() {
        return items.get(0).getPublishedDate(); //0 is always latest withing group
    }

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

    /*public List<String> getItemIds() {
        List<String> ids = new ArrayList<String>();

        for (Item item : items) {
            ids.add(item.getId());
        }

        return ids;
    }*/

    public void sortItemsByPublishedDate() {
        Collections.sort(items, Item.PUBLISHED_DATE_SORTER);
    }

    private static final class LATEST_PUBLISHED_DATE_SORTER implements Comparator<ItemGroup> {
        @Override
        public int compare(ItemGroup o1, ItemGroup o2) {
            return o1.getLatestItemCreatedDate().compareTo(o2.getLatestItemCreatedDate());
        }
    }
}
