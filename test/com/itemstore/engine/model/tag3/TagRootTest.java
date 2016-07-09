package com.itemstore.engine.model.tag3;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TagRootTest {

    @Test
    public void sort_order_must_contan_all()  {
        Assert.assertTrue(TagRoot.SORT_ORDER.size()== TagRoot.values().length);
    }

}