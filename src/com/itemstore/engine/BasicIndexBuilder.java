package com.itemstore.engine;

import com.itemstore.engine.model.Item;
import com.itemstore.engine.model.ItemGroup;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.logging.Logger;

class BasicIndexBuilder {

    private static final Logger LOG = Logger.getLogger(BasicIndexBuilder.class.getName());
    private static final int MAX_NUMBER_OF_TOP_ITEMS_TO_SHOW = 25;
    private static final int MAX_ITEM_IN_GROUP = 5;

    private final List<Item> items;

    public  BasicIndexBuilder(List<Item> items) {
        if(items==null || items.isEmpty()){
            throw  new IllegalArgumentException("No item to inex");
        }

        Set<Item> clean = new HashSet<Item>(items);

        this.items = new ArrayList<Item>(clean); //FIXME
    }


    // 1. Iterate all items, create ItemGroups (max x in each)
    // 2. Sort... done!
    public Result buildIndexForUsers() {
        Set<String> itemTags = new HashSet<String>();

        Collections.sort(items, Item.PUBLISHED_DATE_SORTER);

        List<ItemGroup> itemGroups = new ArrayList<ItemGroup>();
        List<Item> handledItems = new ArrayList<Item>();
        for (Item item : items) {
            if (handledItems.contains(item)) {
                //User client has already this items in is local cache
                continue;
            }

            itemTags.addAll(item.getTags());

            List<Item> itemsNotHandled = ListUtils.subtract(items, handledItems);

            ItemGroup itemGroup = new ItemGroup("Some header FIXME"); //FIXME
            itemGroup.addItem(item);

            boolean testVideo = false;
            for (Item itemNotHandled : itemsNotHandled) {
                if (itemGroup.getItems().size() == MAX_ITEM_IN_GROUP) {
                    break;
                }

                /*if (!testVideo) {
                    testVideo = true;
                    String videoId = "HNaF4-oK_Oo"; //FIXME test only
                    Item build = new Item.Builder().title("Some bananas").youTubeVideoID(videoId).build();
                    itemGroup.addItem(build);

                }*/

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

        return new Result(itemGroups, new ArrayList<String>(itemTags));
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
