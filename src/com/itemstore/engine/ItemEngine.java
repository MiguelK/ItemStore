package com.itemstore.engine;


import com.itemstore.collector.ItemCollector;
import com.itemstore.collector.ItemCollectorListener;
import com.itemstore.collector.ItemCollectorRunner;
import com.itemstore.commons.AsyncService;
import com.itemstore.engine.model.Item;
import com.itemstore.engine.model.ItemGroup;
import com.itemstore.engine.model.SnapShotItemGroups;
import com.itemstore.service.datastore.ServiceDataStorage;

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
    private final Set<String> tags = new HashSet<>();
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
        LOG.fine("handleNewItems received from collector" + items.size());

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

            SnapShotItemGroups snap = new SnapShotItemGroups(result.getItemGroups());
            ServiceDataStorage.useDefault().save(snap);
        } finally {
            readLock.unlock();
        }

        //FIXME
        writeLock.lock();
        try {
            this.allItemGroupsSortedByDate = result.getItemGroups();
            this.tags.addAll(result.getItemTags());
        } finally {
            writeLock.unlock();
        }
    }

    public List<String> getTags() {
        readLock.lock();
        try {
            return tags.stream().collect(Collectors.toList());
        } finally {
            readLock.unlock();
        }
    }

    public void addChannelTags(String tags) {
        writeLock.lock();
        try {
            this.tags.add(tags);
        } finally {
            writeLock.unlock();
        }
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

    public List<ItemGroup> getAllItemGroupsSortedByDate(String fromDate) {
        readLock.lock();
        try {
            //FIXME split fromDate
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
