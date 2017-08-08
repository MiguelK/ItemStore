package com.itemstore.collector;

public abstract class ItemCollectorBase implements ItemCollector {

    @Override
    public int getPollFrequencyInSeconds() {
        return ItemCollectorRunner.EXECUTE_ONLY_ONCE;
    }

}
