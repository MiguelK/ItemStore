package com.pusher;


import java.io.File;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Logger;

public class ChannelSubscriptionService {

    private static final Logger LOG = Logger.getLogger(ChannelSubscriptionService.class.getName());

    private static final ChannelSubscriptionService INSTANCE = new ChannelSubscriptionService();
    private static final String SUBSCRIBERS_DAT_FILE_NAME = "Subscribers.dat";

    private Subscribers subscribers;

    private final Lock readLock;
    private final Lock writeLock;
    private File subscriptionData;

    public static ChannelSubscriptionService getInstance() {
        return INSTANCE;
    }


    public void loadSubscribers() {
        LOG.info("Start loading subscribers...");

        subscriptionData = null; //FIXME //new File(Config.CONTENT_ROOT_DIR, SUBSCRIBERS_DAT_FILE_NAME);

        if (!subscriptionData.exists()) {
            LOG.info("Creating new Subscription dat file..." + subscriptionData.getAbsolutePath());
            this.subscribers = Subscribers.createNew(subscriptionData);
        } else {
            this.subscribers = Subscribers.load(subscriptionData);
        }

        int subscribersSize = subscribers.getAllSubscribers().size();
       // Statistics.getInstance().increaseAsync(Statistics.TOTAL_SUBSCRIBERS_COUNTER, subscribersSize);

        LOG.info("Done loading " + subscribersSize + " Subscriber(s)");
    }

    private ChannelSubscriptionService() {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readLock = readWriteLock.readLock();
        writeLock = readWriteLock.writeLock();
    }

    public void notifySubscribers(String tag) { //FIXME add notfy message "3 new images"
       // StationChannelKey updatedStationChannelKey = StationChannelKey.parse(updatedChannel.getStationId(), updatedChannel.getChannelId());

        readLock.lock();

        try {
            List<String> deviceTokens = subscribers.getSubscribers(tag); //FIXME ok with read? lock
            LOG.fine("notifySubscribers deviceTokens=" + deviceTokens);

            LOG.fine("All subscribers=" + subscribers);

            if (deviceTokens != null) {
             //   LOG.fine("Start notify " + subscriberStations.size() + " subscribers updatedChannel " + updatedChannel.getChannelId() + " changes");
                for (String deviceToken : deviceTokens) {
                    PushNotificationService.getInstance().sendPushNotificationAsync(deviceToken, "Push meddelandet...123");
                }
            }
        } finally {
            readLock.unlock();
        }
    }

    public void addSubscriber(String deviceToken, String tag) {
        writeLock.lock();
        try {
          //  Statistics.getInstance().increaseAsync(Statistics.TOTAL_SUBSCRIBERS_COUNTER);

            subscribers.registerSubscriber(deviceToken, tag);
            subscribers.save(subscriptionData);
        } finally {
            writeLock.unlock();
        }
    }

    //FIXME Not used???? by client
    public void removeSubscriber(String deviceToken, String tag) {
        writeLock.lock();
        try {
            subscribers.removeSubscriber(deviceToken, tag);
            subscribers.save(subscriptionData);
        } finally {
            writeLock.unlock();
        }
    }

    public List<String> getSubscribers() {
        readLock.lock();
        try {
            return subscribers.getAllSubscribers();
        } finally {
            readLock.unlock();
        }
    }
}
