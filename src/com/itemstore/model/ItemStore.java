package com.itemstore.model;


import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Subscribes to Emgine, in-memory store of all Item's
 */
public class ItemStore {

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    //REad only
    List<Item> items;

    List<ItemGroup> itemGroups;

}
