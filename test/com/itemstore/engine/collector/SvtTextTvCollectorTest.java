package com.itemstore.engine.collector;

import com.itemstore.collector.SvtTextTvCollector;
import com.itemstore.engine.model.Item;
import jdk.nashorn.internal.ir.annotations.Ignore;

import java.util.List;

public class SvtTextTvCollectorTest {

   // @Test
    @Ignore //FIXME network
    public void testGetItems() {

        SvtTextTvCollector collector = new SvtTextTvCollector();

        List<Item> items = collector.collect();

    }
}