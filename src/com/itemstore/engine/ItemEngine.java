package com.itemstore.engine;


import com.itemstore.collector.ItemCollector;
import com.itemstore.collector.ItemCollectorListener;
import com.itemstore.collector.ItemCollectorRunner;
import com.itemstore.commons.AsyncService;
import com.itemstore.engine.event.EventType;
import com.itemstore.engine.event.Events;
import com.itemstore.engine.model.Item;
import com.itemstore.engine.model.ItemGroup;
import com.itemstore.engine.model.tag3.ItemTagTree;
import com.itemstore.engine.model.tag3.TagRoot;
import com.itemstore.engine.model.tag3.TagTreeFilter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.map.HashedMap;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Logger;
import java.util.stream.Collectors;

//FIXME rename ItemStore, ItemEngine
public final class ItemEngine implements ItemCollectorListener {
    private static final Logger LOG = Logger.getLogger(ItemEngine.class.getName());

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    private Set<Item> allItems = new HashSet<Item>();
    private List<String> allItemTags;
    private List<ItemGroup> allItemGroupsSortedByDate = new ArrayList<ItemGroup>();

    private final ItemCollectorRunner itemCollectorRunner;

    private static final ItemEngine INSTANCE = new ItemEngine();

    public static ItemEngine getInstance() {
        return INSTANCE;
    }

    private ItemEngine() {
        itemCollectorRunner = new ItemCollectorRunner(this); //FIXME
    }

    @Override
    public void handleNewItems(List<Item> items) {
        LOG.fine("handleNewItems recived from collector" + items);

        writeLock.lock();
        try {
            this.allItems.addAll(items);

        } finally {
            rebuildIndex(); //FIXME TEST
            writeLock.unlock();
        }
        //FIXME
    }

    //USED by test FIXME
    public void clear() {
        writeLock.lock();
        try {
            this.allItems = new HashSet<>();
        } finally {

            writeLock.unlock();
        }
    }

    public void registerItem(Item item) {
        writeLock.lock();
        try {
            allItems.add(item);
            rebuildIndex(); //FIXME TEST
        } finally {
            writeLock.unlock();
        }
    }

    public void registerItems(List<Item> items) {
        writeLock.lock();
        try {
            allItems.addAll(items);
            rebuildIndex(); //FIXME TEST
        } finally {
            writeLock.unlock();
        }
    }

    public void rebuildIndex() {

        if (allItems.isEmpty()) {
            return; //FIXME
        }

        LOG.fine("Start building full index...");

        //FIXME asynch can take long time...
        BasicIndexBuilder.Result result;
        readLock.lock();
        try {
            BasicIndexBuilder indexBuilder = new BasicIndexBuilder(this.allItems);
            result = indexBuilder.buildIndex();
        } finally {
            readLock.unlock();
        }

        //FIXME
        writeLock.lock();
        try {
            this.allItemGroupsSortedByDate = result.getItemGroups();
            this.allItemTags = result.getItemTags();
        } finally {
            writeLock.unlock();
        }

        Events.fireEvent(EventType.EngineRebuildIndex);
    }

    public void start() {
        writeLock.lock();
        try {
            AsyncService.instance().init();

            itemCollectorRunner.start();

            rebuildIndex();
        } finally {
            writeLock.unlock();
        }
    }

    public List<ItemGroup> search(SearchItemGroupQuery filter) {
        readLock.lock();
        try {

            TagTreeFilter includeTagTreeFilter = filter.getIncludeTagTreeFilter();
            List<Integer> excludeItemGroupIds = filter.getExcludeIds();
            TagTreeFilter excludeTagTreeFilter = filter.getExcludeTagTreeFilter();
            int maxResult = 50; //filter.getMaxResult();

            //Copy??? FIXME
            Map<TagRoot, Integer> maxTagrootTracker = new HashedMap<>();
            List<ItemGroup> itemGroups = allItemGroupsSortedByDate.stream()
                    .filter(itemGroup -> {

                        //1# If included in excludeItemGroupId do not return it
                        if (excludeItemGroupIds.contains(itemGroup.getId())) {
                            return false; //Do not include
                        }

                        ItemTagTree itemTagTree = itemGroup.getItemTagTree();

                        if (maxTagrootTracker.get(itemTagTree.getTagRoot()) != null
                                && maxTagrootTracker.get(itemTagTree.getTagRoot()) > 5) {
                            return false; //Max 5 items per tagRoot (Category)
                        }

                        if (excludeTagTreeFilter != null &&
                                itemTagTree.match(excludeTagTreeFilter) > 0) {
                            return false;
                        }

                        if (includeTagTreeFilter != null && itemTagTree.match(includeTagTreeFilter) >= 1.0) {
                            TagRoot tagRoot = itemTagTree.getTagRoot();
                            Integer integer = maxTagrootTracker.get(tagRoot);
                            if (integer == null) {
                                maxTagrootTracker.put(tagRoot, 0);
                            } else {
                                integer = integer + 1;
                                maxTagrootTracker.put(tagRoot, integer);
                            }
                            return true;
                        }


                        return false;
                    }).collect(Collectors.toList());

            if (itemGroups.size() > maxResult) {
                itemGroups = itemGroups.subList(0, maxResult);
            }

            //FIXME add sorting? date tagMatch??? filter
            //includeTagTreeFilter

            //FIXME conf?
            ItemGroupSorter itemGroupSorter = new ItemGroupSorter<ItemGroup>(itemGroups);
            itemGroups = (List<ItemGroup>) itemGroupSorter.sort();


            return itemGroups;
        } finally {
            readLock.unlock();
        }
    }

    public Collection<Item> searchItems(Predicate<Item> predicate) {
        readLock.lock();
        try {
            return CollectionUtils.select(allItems, predicate);
        } finally {
            readLock.unlock();
        }
    }

    //FIXME Uset ItemTagTree filter
    /*public List<ItemGroup> searchItemsByTags(final List<String> itemTagTree) {
        readLock.lock();
        try {

            Predicate<ItemGroup> predicate = new Predicate<ItemGroup>() {
                @Override
                public boolean evaluate(ItemGroup item) {

                    boolean tagsMatch = true;
                    if (!itemTagTree.isEmpty()) {
                        tagsMatch = CollectionUtils.containsAny(item.getItemTagTree(), itemTagTree);
                    }


                    return tagsMatch;
                }
            };

            return new ArrayList<ItemGroup>(CollectionUtils.select(allItemGroupsSortedByDate, predicate));

        } finally {
            readLock.unlock();
        }
    }*/


    public List<String> getAllItemTags() {
        readLock.lock();
        try {
            return allItemTags;
        } finally {
            readLock.unlock();
        }
    }

    public List<ItemGroup> getAllItemGroupsSortedByDate() {
        readLock.lock();
        try {
            return allItemGroupsSortedByDate;
        } finally {
            readLock.unlock();
        }
    }

    public void addCollectors(List<ItemCollector> itemCollectors) {
        writeLock.lock();
        try {
            this.itemCollectorRunner.addCollectors(itemCollectors);
        } finally {
            writeLock.unlock();
        }
    }

    public void addCollector(ItemCollector collector) {
        writeLock.lock();
        try {
            this.itemCollectorRunner.addCollector(collector);
        } finally {
            writeLock.unlock();
        }
    }

}
