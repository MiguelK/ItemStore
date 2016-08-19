package com.itemstore.engine.model.tag;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TagTest {

    @Test
    public void testIsSynonymTag() {

    }

    @Test
    public void testCreate() {
        Tag tag = Tag.create("Single tag name");
        Assert.assertTrue(tag.getTags().size()==1);
    }

    @Test
    public void createCategoryTag() {
        Tag tag = Tag.create("Stockhom").addChild("Södermalm","Vasastan");
        Assert.assertTrue(tag.getTags().size()==3);
    }
}