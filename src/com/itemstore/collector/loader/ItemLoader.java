package com.itemstore.collector.loader;

import com.itemstore.engine.model.Item;
import com.itemstore.engine.model.tag2.TagContainer;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class ItemLoader extends Loader {
    private static final Logger logger = Logger.getLogger(ItemLoader.class.getName());

    private Set<Item> items = new HashSet<Item>();

    public void registerItem(Item item) {
        items.add(item);
    }

    public void removeItemsByTags(TagContainer tagContainer) {
        Set<Item> filteredItems = new HashSet<Item>();
        for (Item item : items) {
            if (CollectionUtils.containsAny(item.getTags(), tagContainer.getRawTags())) {
                continue;
            }
            filteredItems.add(item);
        }

        this.items = filteredItems;
    }

    public List<Item> getItems() {
        return new ArrayList<Item>();
    }


    protected void handledLoadedDate(Object object) {
        items = (Set<Item>) object; //FIXME

        logger.info("Loaded " + items.size() + " items from disk");
    }

    protected Object getData() {
        return items;
    }

    public void registerItems(List<Item> items) {
        this.items.addAll(items);
    }
}
