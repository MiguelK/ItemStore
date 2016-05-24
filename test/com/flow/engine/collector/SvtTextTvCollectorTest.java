package com.flow.engine.collector;

import com.flow.engine.model.Item;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class SvtTextTvCollectorTest {

    @Test
    public void testGetItems() {


        SvtTextTvCollector collector = new SvtTextTvCollector();

        List<Item> items = collector.getItems();

    }
}