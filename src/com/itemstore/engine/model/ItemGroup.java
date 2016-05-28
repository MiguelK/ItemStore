package com.itemstore.engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itemstore.engine.model.tag2.TagContainer;

import java.util.*;

public class ItemGroup {

    public static final Comparator<ItemGroup> PUBLISHED_DATE_SORTER = new LATEST_PUBLISHED_DATE_SORTER();

    private String header;

    private List<Item> items = new ArrayList<Item>();

    public ItemGroup(String header) {
        this.header = header;
    }

    private Date getLatestItemCreatedDate() {
        return items.get(0).getPublishedDate(); //0 is always latest withing group
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<String> getTags() {
        return items.get(0).getTags(); //FIXME
    }

    @JsonIgnore
    public TagContainer getTagContainer() {
        return items.get(0).getTagContainer(); //FIXME
    }

    public List<Item> getItems() {
        return items;
    }

    public List<String> getItemIds() {
        List<String> ids = new ArrayList<String>();

        for (Item item : items) {
            ids.add(item.getId());
        }

        return ids;
    }

    public String getHeader() {
        return header;
    }

    @JsonIgnore
    public Item getSingeItem() {
        if (items.size() != 1) {
            throw new IllegalStateException("ItemGroup not=1" + getTags());
        }

        return items.get(0);
    }

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
