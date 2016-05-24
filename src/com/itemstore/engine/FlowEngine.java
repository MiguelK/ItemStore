package com.itemstore.engine;


import com.itemstore.commons.AsyncService;
import com.itemstore.engine.collector.ItemCollector;
import com.itemstore.engine.collector.ItemCollectorListener;
import com.itemstore.engine.collector.ItemCollectorRunner;
import com.itemstore.engine.event.EventType;
import com.itemstore.engine.event.Events;
import com.itemstore.engine.loader.ItemLoader;
import com.itemstore.engine.loader.UserLoader;
import com.itemstore.engine.loader.rss.Channel;
import com.itemstore.engine.loader.rss.ChannelLoader;
import com.itemstore.engine.model.Item;
import com.itemstore.engine.model.ItemGroup;
import com.itemstore.engine.model.tag.TagContainer;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Logger;

/**
 * (Synchronized in-memory wrapper for accessing userLoader,itemLoader and  channelManager
 * access read/update the core model User,Item rename o ItemStore
 */
public final class FlowEngine implements ItemCollectorListener {
    private static final Logger logger = Logger.getLogger(FlowEngine.class.getName());

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private ItemLoader itemLoader;
    private ChannelLoader channelLoader;
    private ItemCollectorRunner itemCollectorRunner;

    private List<String> itemTags = new ArrayList<String>();

    private Map<String, BasicIndexBuilder.UserResult> userItems;

    private static final FlowEngine INSTANCE = new FlowEngine();

    public static FlowEngine getInstance() {
        return INSTANCE;
    }

    private FlowEngine() {
    }

    @Override
    public void handleNewItems(List<Item> items) {
        logger.fine("handleNewItems " + items);

        writeLock.lock();
        try {
            itemLoader.registerItems(items);
        } finally {
            itemLoader.save();
            rebuildIndex(); //FIXME TEST
            writeLock.unlock();
        }
    }


    public void removeItemsByTags(TagContainer tagContainer) {
        writeLock.lock();
        try {
            itemLoader.removeItemsByTags(tagContainer);
            itemLoader.save();
        } finally {
            writeLock.unlock();
        }
    }

    public void registerItem(Item item) {
        writeLock.lock();
        try {
            itemLoader.registerItem(item);
            itemLoader.save();
            rebuildIndex(); //FIXME TEST

        } finally {
            writeLock.unlock();
        }
    }


    public void registerItems(List<Item> items) {
        writeLock.lock();
        try {
            itemLoader.registerItems(items);
            itemLoader.save();
            rebuildIndex(); //FIXME TEST

        } finally {
            writeLock.unlock();
        }
    }

    public void rebuildIndex() {
        logger.fine("Start building full index...");


        //FIXME asynch can take long time...
        BasicIndexBuilder.Result newResult;
        readLock.lock();
        try {
            BasicIndexBuilder indexBuilder = new BasicIndexBuilder(itemLoader.getItems());
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
        }

        Events.fireEvent(EventType.EngineRebuildIndex);
    }


    public void start() {
        writeLock.lock();
        try {


            if (itemLoader == null) {
                throw new IllegalStateException("itemLoader is null, call init() method");
            }

            if (itemCollectorRunner == null) {
                throw new IllegalStateException("itemCollectorRunner is null, call init() method");
            }


            if (channelLoader == null) {
                throw new IllegalStateException("channelLoader is null, call init() method");
            }

            AsyncService.instance().init();


            //FIXME call load on registers async
            logger.info("Start loading tag/user and item Register...");

            itemLoader.load();
            logger.info("Start channelManager...");

            itemCollectorRunner.start();

            rebuildIndex();
        } finally {
            writeLock.unlock();
        }
    }


    public List<ItemGroup> getItemGroupsForUser(String userId) {
        readLock.lock();
        try {
            List<ItemGroup> itemGroups = userItems.get(userId).getItemGroups();
            if (itemGroups == null) {
                return Collections.emptyList();
            }

            return itemGroups;
        } finally {
            readLock.unlock();
        }
    }



    public void init(ChannelLoader channelLoader, UserLoader userLoader, ItemLoader itemLoader) {
        writeLock.lock();
        try {
            this.itemLoader = itemLoader;
            this.channelLoader = channelLoader;
            userItems = new HashMap<String, BasicIndexBuilder.UserResult>();
            itemCollectorRunner = new ItemCollectorRunner(this); //FIXME
        } finally {
            writeLock.unlock();
        }
    }


    public List<Item> getTopNewsForUser(String userid) {
        readLock.lock();
        try {
            return userItems.get(userid).getTopNews();
        } finally {
            readLock.unlock();
        }
    }

    public Collection<Item> searchItems(Predicate<Item> predicate) {
        readLock.lock();
        try {
            List<Item> items = itemLoader.getItems();
            return CollectionUtils.select(items, predicate);
        } finally {
            readLock.unlock();
        }
    }

    public List<Channel> getChannels() {
        readLock.lock();
        try {

            return channelLoader.getChannels();
        } finally {
            readLock.unlock();
        }
    }

    public void reloadChannels() {
        writeLock.lock();
        try {
            //FIXME remove channel collectors and readd
            throw new UnsupportedOperationException("TODO...");
            //  channelLoader.reloadChannels(); //FIXME
        } finally {
            writeLock.unlock();
        }
    }

    public List<String> getItemTags() {

        readLock.lock();
        try {
            return itemTags;
        } finally {
            readLock.unlock();
        }
    }



    public void addCollectors(List<ItemCollector> itemCollectors) {
        writeLock.lock();
        try {
            this.itemCollectorRunner.add(itemCollectors);
        } finally {
            writeLock.unlock();
        }
    }

    public void addCollector(ItemCollector collector) {
        writeLock.lock();
        try {
            this.itemCollectorRunner.add(collector);
        } finally {
            writeLock.unlock();
        }
    }

}
