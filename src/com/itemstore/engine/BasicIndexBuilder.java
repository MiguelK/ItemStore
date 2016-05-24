package com.itemstore.engine;

import com.itemstore.engine.model.Item;
import com.itemstore.engine.model.ItemGroup;
import com.itemstore.engine.model.tag.TagContainer;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.logging.Logger;

class BasicIndexBuilder {

    private static final Logger logger = Logger.getLogger(BasicIndexBuilder.class.getName());
    private static final int MAX_NUMBER_OF_TOP_ITEMS_TO_SHOW = 25;
    private static final int MAX_ITEM_IN_GROUP = 5;

    private final List<Item> items;

    public BasicIndexBuilder(List<Item> items) {

        Set<Item> clean = new HashSet<Item>(items);

        this.items = new ArrayList<Item>(clean); //FIXME
    }


    // 1. Iterate all items, createItemgroups (max x in each)
    // 2. Iterate all users, collect all itemgroups for each users
    // 3. done!
    public Result buildIndexForUsers() {
        Set<String> itemTags = new HashSet<String>();

        Map<String, List<Item>> topNewsByLocation = new HashMap<String, List<Item>>();

        Collections.sort(items, Item.PUBLISHED_DATE_SORTER);

        List<ItemGroup> itemGroups = new ArrayList<ItemGroup>();
        List<Item> handledItems = new ArrayList<Item>();
        for (Item item : items) {
            if (handledItems.contains(item)) {
                //User client has already this items in is local cache
                continue;
            }

            List<Item> topItems = topNewsByLocation.get(TagContainer.TOP_NEWS_SWE); //tag = TopNews_sweden
            if (topItems == null) {
                topItems = new ArrayList<Item>();
                topNewsByLocation.put(TagContainer.TOP_NEWS_SWE, topItems); //Add to user
            }

            if (item.getTagContainer().containsTagWithName(TagContainer.TOP_NEWS_SWE) &&
                    topItems.size() < MAX_NUMBER_OF_TOP_ITEMS_TO_SHOW) { //FIXME
                System.out.println("Index: " + item.getTitle() + " " + item.getPublishedDate());
                topItems.add(item);
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

        Map<String, List<ItemGroup>> newUserItems = new HashMap<String, List<ItemGroup>>();
        Map<String, List<Item>> newUserTopNews = new HashMap<String, List<Item>>();

        /*for (User user : users) {
            List<String> favoriteTags = user.getFavoriteTags(); //FIXME sort on this and createdDate

            newUserTopNews.put(user.getId(), new ArrayList<Item>());
            for (Map.Entry<String, List<Item>> entry : topNewsByLocation.entrySet()) {
                if (favoriteTags.contains(entry.getKey())) { //A user can have a multiple topNews ??//FIXME
                    List<Item> topNews = newUserTopNews.get(user.getId());
                    topNews.addAll(entry.getValue());
                }
            }

            List<String> receivedItems = user.getReceivedItems(); //FIXME exclude already wieved items

            List<String> userExcludeTags = user.getExcludeTags();
            for (ItemGroup itemGroup : itemGroups) {

                if (user.getExcludeTags().contains(TagContainer.EXCLUDE_ALL)
                        && !CollectionUtils.containsAny(itemGroup.getTags(), user.getFavoriteTags())) {
                    continue; //If exclude all tag then only favorites are matched
                }

                if (CollectionUtils.containsAny(itemGroup.getItemIds(), receivedItems)) {
                    continue; //Filter out already delivered items
                }

                if (CollectionUtils.containsAny(userExcludeTags, itemGroup.getTags())) {
                    logger.fine("Exclude " + itemGroup.getTags());
                    continue; //Exclude filter isSimilarText
                }

                List<ItemGroup> itemComponents = newUserItems.get(user.getId());
                if (itemComponents == null) {
                    itemComponents = new ArrayList<ItemGroup>();
                    newUserItems.put(user.getId(), itemComponents);
                }
                itemComponents.add(itemGroup);

            }
        }*/

        Map<String, UserResult> userResults = new HashMap<String, UserResult>();

        for (Map.Entry<String, List<ItemGroup>> entry : newUserItems.entrySet()) {
            List<Item> topNews = newUserTopNews.get(entry.getKey());
            userResults.put(entry.getKey(), new UserResult(entry.getValue(), topNews));
        }

        return new Result(userResults, itemTags);
    }

    public static final class UserResult {
        private final List<ItemGroup> itemGroups;
        private final List<Item> topNews;

        public UserResult(List<ItemGroup> itemGroups, List<Item> topNews) {
            this.itemGroups = ListUtils.unmodifiableList(itemGroups);
            this.topNews = ListUtils.unmodifiableList(topNews);
        }

        public List<ItemGroup> getItemGroups() {
            return itemGroups;
        }

        public List<Item> getTopNews() {
            return topNews;
        }
    }

    public static class Result {
        private final Map<String, UserResult> userItems;
        private final List<String> itemTags;

        public Result(Map<String, UserResult> userItems, Set<String> itemTags) {
            this.userItems = Collections.unmodifiableMap(userItems);
            this.itemTags = new ArrayList<String>(itemTags);
        }

        public Map<String, UserResult> getUserItems() {
            return userItems;
        }

        public List<String> getItemTags() {
            return itemTags;
        }
    }
}
