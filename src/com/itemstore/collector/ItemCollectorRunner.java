package com.itemstore.collector;

import com.itemstore.commons.AsyncService;
import com.itemstore.engine.model.Item;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemCollectorRunner {

    static final int EXECUTE_ONLY_ONCE = -1;

    private static final Logger LOG = Logger.getLogger(ItemCollectorRunner.class.getName());
    private static final int INITIAL_DELAY_SECONDS = 1;

    private ScheduledExecutorService scheduledExecutorService; //FIXME use syncService
    private final List<ItemCollector> collectors = Collections.synchronizedList(new ArrayList<ItemCollector>());
    private final Set<Item> handledItems = Collections.synchronizedSet(new CopyOnWriteArraySet<Item>());

    private ItemCollectorListener itemCollectorListener;

    public ItemCollectorRunner(ItemCollectorListener itemCollectorListener) {
        this.itemCollectorListener = itemCollectorListener;
    }

    public void addCollectors(List<ItemCollector> collectors) {
        this.collectors.addAll(collectors);
    }

    public void addCollector(ItemCollector collector) {
        this.collectors.add(collector);
    }

    public void start() {
        if(collectors.isEmpty()){
            throw new IllegalStateException("No collectors are registered");
        }

        scheduledExecutorService = Executors.newScheduledThreadPool(35); //EngineConf.getInstance().getThreadPoolSize());

        LOG.info("Starting " + getClass().getSimpleName() + " with " + collectors.size() + " collectors");

        for (ItemCollector itemCollector : collectors) {
            int pollFrequencyInSeconds = itemCollector.getPollFrequencyInSeconds();
            ItemCollectorTask collectorTask = new ItemCollectorTask(itemCollector, itemCollectorListener, handledItems);

            if (pollFrequencyInSeconds == EXECUTE_ONLY_ONCE) {
                AsyncService.instance().submit(collectorTask);
            } else {
                scheduledExecutorService.scheduleAtFixedRate(collectorTask,
                        INITIAL_DELAY_SECONDS, pollFrequencyInSeconds, TimeUnit.SECONDS);
            }
        }
    }

    //FIXME
    public void reloadChannels() {
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
        }

        start();
    }

    private static final class ItemCollectorTask implements Runnable {
        private final ItemCollector itemCollector;
        private final ItemCollectorListener itemCollectorListener;
        private final Set<Item> handledItems;

        ItemCollectorTask(ItemCollector itemCollector, ItemCollectorListener itemCollectorListener, Set<Item> handledItems) {
            this.itemCollector = itemCollector;
            this.itemCollectorListener = itemCollectorListener;
            this.handledItems = handledItems;
        }

        @Override
        public void run() {
            try {
                List<Item> items = itemCollector.collect();

                if (items.isEmpty()) {
                    return;
                }

                List<Item> notHandledItems = (List<Item>) CollectionUtils.subtract(items, handledItems);

                if (notHandledItems.isEmpty()) {
                    return;
                }

                LOG.info("Items to be handled " + notHandledItems.size());

                synchronized (itemCollectorListener) {
                    itemCollectorListener.handleNewItems(notHandledItems); //FIXME call in seperate Thread
                }

                handledItems.addAll(notHandledItems);

            } catch (Exception e) {
                LOG.log(Level.SEVERE, "Unable to parse itemCollector " + itemCollector, e);
            }
        }
    }
}
