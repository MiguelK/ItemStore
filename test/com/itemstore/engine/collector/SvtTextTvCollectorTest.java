package com.itemstore.engine.collector;

import com.itemstore.model.Item;
import org.testng.annotations.Test;

import java.util.List;

public class SvtTextTvCollectorTest {

    @Test
    public void testGetItems() {


        SvtTextTvCollector collector = new SvtTextTvCollector();

        List<Item> items = collector.getItems();

    }
}