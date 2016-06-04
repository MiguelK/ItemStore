package com.itemstore.engine;

import com.itemstore.engine.model.Item;
import com.itemstore.engine.model.ItemGroup;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.logging.Logger;

class BasicIndexBuilder {

    private static final Logger LOG = Logger.getLogger(BasicIndexBuilder.class.getName());
    private static final int MAX_ITEM_IN_GROUP = 5;

    private final List<Item> items;

    public BasicIndexBuilder(Set<Item> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("No item(s) to index");
        }

        this.items = new ArrayList<Item>(items);
    }

    // 1. Iterate all items, create ItemGroups based on similra item(s) (max x in each)
    // 2. Sort on date
    // 3  Done!
    // 4 (ItemGroup API search will sort on tags, priority etc...)
    public Result buildIndex() {
        Set<String> itemTags = new HashSet<String>();

        Collections.sort(items, Item.PUBLISHED_DATE_SORTER);

        List<ItemGroup> itemGroups = new ArrayList<ItemGroup>();
        List<Item> handledItems = new ArrayList<Item>();
        for (Item item : items) {
            if (handledItems.contains(item)) {
                //User client has already this items in is local cache
                continue;
            }
            //FIXME needed???    itemTags.addAll(item.getTags());
            List<Item> itemsNotHandled = ListUtils.subtract(items, handledItems);

            ItemGroup itemGroup = new ItemGroup();
            itemGroup.addItem(item);

            for (Item itemNotHandled : itemsNotHandled) {
                if (itemGroup.getItems().size() == MAX_ITEM_IN_GROUP) {
                    break;
                }

                if (StringUtils.trimToEmpty(item.getItemGroupId()).equals(itemNotHandled.getItemGroupId())
                        || item.isSimilarTo(itemNotHandled)) {
                    itemGroup.addItem(itemNotHandled);
                }   //FIXME //contains same tags + similar description
            }


            itemGroup.sortItemsByPublishedDate();

            handledItems.addAll(itemGroup.getItems());
            itemGroups.add(itemGroup);
        }

        Collections.sort(itemGroups, ItemGroup.PUBLISHED_DATE_SORTER);

        return new Result(itemGroups, new ArrayList<>(itemTags));
    }

    public static final class Result {
        private final List<ItemGroup> itemGroups;
        private final List<String> itemTags;

        public Result(List<ItemGroup> itemGroups, List<String> itemTags) {
            this.itemGroups = ListUtils.unmodifiableList(itemGroups);
            this.itemTags = ListUtils.unmodifiableList(itemTags);
        }

        public List<ItemGroup> getItemGroups() {
            return itemGroups;
        }

        public List<String> getItemTags() {
            return itemTags;
        }
    }


}
