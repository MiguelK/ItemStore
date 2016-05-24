package com.itemstore.engine;


import com.itemstore.commons.AsyncService;
import com.itemstore.collector.ItemCollector;
import com.itemstore.collector.ItemCollectorListener;
import com.itemstore.collector.ItemCollectorRunner;
import com.itemstore.engine.event.EventType;
import com.itemstore.engine.event.Events;
import com.itemstore.collector.loader.rss.Channel;
import com.itemstore.collector.loader.rss.ChannelLoader;
import com.itemstore.model.Item;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Logger;


//FIXME rename ItemStore, ItemEngine
public final class ItemEngine implements ItemCollectorListener {
    private static final Logger LOG = Logger.getLogger(ItemEngine.class.getName());

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    private ItemCollectorRunner itemCollectorRunner;

    private static final ItemEngine INSTANCE = new ItemEngine();

    public static ItemEngine getInstance() {
        return INSTANCE;
    }

    private ItemEngine() {
        itemCollectorRunner = new ItemCollectorRunner(this); //FIXME
    }

    @Override
    public void handleNewItems(List<Item> items) {
        LOG.fine("handleNewItems " + items);

        writeLock.lock();
        /*try {
            itemLoader.registerItems(items);
        } finally {
            itemLoader.save();
            rebuildIndex(); //FIXME TEST
            writeLock.unlock();
        } */
        //FIXME
    }



    public void registerItem(Item item) {
        writeLock.lock();
        try {
   //         itemLoader.registerItem(item);
            //         itemLoader.save();
            rebuildIndex(); //FIXME TEST

        } finally {
            writeLock.unlock();
        }
    }


    public void registerItems(List<Item> items) {
        writeLock.lock();
        try {
            //    itemLoader.registerItems(items);
            // itemLoader.save();
            rebuildIndex(); //FIXME TEST

        } finally {
            writeLock.unlock();
        }
    }

    public void rebuildIndex() {
        LOG.fine("Start building full index...");


        //FIXME asynch can take long time...
       /* BasicIndexBuilder.Result newResult;
        readLock.lock();
        try {
            BasicIndexBuilder indexBuilder = new BasicIndexBuilder(null);//FIXME//itemLoader.getItems()); //FIXME
            newResult = indexBuilder.buildIndexForUsers();
        } finally {
            readLock.unlock();
        }

        //FIXME
        writeLock.lock();
        try {
            this.userItems = newResult.getUserItems();
            this.itemTags = newResult.getItemTags();
        } finally {
            writeLock.unlock();
        }*/

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


    public Collection<Item> searchItems(Predicate<Item> predicate) {
        readLock.lock();
        try {
            List<Item> items = null;//FIXEM //itemLoader.getItems();
            return CollectionUtils.select(items, predicate);
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
