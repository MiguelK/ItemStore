package com.pusher;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Subscribers implements Serializable {
    private static final Logger LOG = Logger.getLogger(Subscribers.class.getName());

    /**
     * A list of subscribers(deviceToken) for a given tag
     */
    private final Map<String, List<String>> subscribers = new HashMap<>();


    static Subscribers createNew(File subscriptionData) {
        try {
            subscriptionData.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Unable to create Subscription file " + subscriptionData.getAbsolutePath() ,e);
        }

        Subscribers subscribers = new Subscribers();

        write(subscriptionData, subscribers);

        return subscribers;
    }

    static Subscribers load(File dataFile) {
        if (!dataFile.exists()) {
            throw new IllegalArgumentException("File doe not exists " + dataFile.getAbsolutePath());
        }
        if (!dataFile.canRead()) {
            throw new IllegalArgumentException("can not read file" + dataFile.getAbsolutePath());
        }
        if (!dataFile.canWrite()) {
            throw new IllegalArgumentException("can not write file " + dataFile.getAbsolutePath());
        }
        return read(dataFile);
    }

    void save(File dataFile) {
        write(dataFile, this);
    }

    List<String> getSubscribers(String tag) {
        return subscribers.get(tag);
    }

    void registerSubscriber(String deviceToken, String tag) {
        List<String> registeredSubscribers = subscribers.get(tag);
        if (registeredSubscribers == null) {
            registeredSubscribers = new ArrayList<>();
            subscribers.put(deviceToken, registeredSubscribers);
        }

        registeredSubscribers.add(deviceToken);
    }

    /**
     *
     * @param stationId the stationId of that should be removed
     * @param channelGUID The deviceToken that stationId subscribes to
     */
    public void removeSubscriber(String deviceToken, String tag) {
        List<String> registeredSubscribers = subscribers.get(tag);

        if (registeredSubscribers != null && registeredSubscribers.contains(deviceToken)) {
            registeredSubscribers.remove(deviceToken);
            LOG.info(deviceToken + " unsubscribed from " + tag);
        }
    }

    public List<String> getAllSubscribers() {
        List<String> result = new ArrayList<>();

        for (Map.Entry<String, List<String>> stationChannelKeyListEntry : subscribers.entrySet()) {
            result.addAll(stationChannelKeyListEntry.getValue());
        }
        return result;
    }


    private static Subscribers read(File subscriptions) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(subscriptions);
            ObjectInput objectInput = new ObjectInputStream(fileInputStream);

            return (Subscribers) objectInput.readObject();
        } catch (Exception e) {
           throw new RuntimeException("Corrupt file? Unable to load Subscription file " + subscriptions.getAbsolutePath());
        } finally {
            IOUtils.closeQuietly(fileInputStream);
        }
    }

    private static void write(File configDirectory, Object object) {

        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(configDirectory);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Error saving StationConf file=" + configDirectory.getAbsolutePath(), e);
        } finally {
            IOUtils.closeQuietly(fileOutputStream);
            IOUtils.closeQuietly(objectOutputStream);
        }
    }

}
