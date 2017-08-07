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

public final class ItemEngine implements ItemCollectorListener {
    private static final Logger LOG = Logger.getLogger(ItemEngine.class.getName());

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    private Set<Item> allItems = new HashSet<Item>();
    private List<String> allItemTags;
    private List<ItemGroup> allItemGroupsSortedByDate = new ArrayList<>();

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
        LOG.fine("handleNewItems recived from collector" + items.size());

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
    public List<String> getAllItemTags() {
        readLock.lock();
        try {
            return allItemTags;
        } finally {
            readLock.unlock();
        }
    }

    public List<ItemGroup> getAllItemGroupsSortedByDate(Date fromDate) {
        readLock.lock();
        try {
            //FIXME split fromDate
            return allItemGroupsSortedByDate;
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
