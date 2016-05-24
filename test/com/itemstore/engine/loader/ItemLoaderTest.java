package com.itemstore.engine.loader;

import com.itemstore.model.Item;
import com.itemstore.model.tag.TagContainer;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ItemLoaderTest {

    private ItemLoader itemLoader;

    @BeforeMethod
    public void setUp() throws Exception {
        itemLoader = new ItemLoader();
    }

    @Test
    public void removeItemsByTag() {
        itemLoader.registerItem(new Item.Builder().tags(TagContainer.create("apa#test")).title("Test A").build());
        itemLoader.registerItem(new Item.Builder().title("Test B").build());

        Assert.assertEquals(itemLoader.getItems().size(), 2);

        itemLoader.removeItemsByTags(TagContainer.create("apa#test"));

        Assert.assertEquals(itemLoader.getItems().size(), 1);
    }
}