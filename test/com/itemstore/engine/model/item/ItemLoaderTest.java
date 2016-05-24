package com.itemstore.engine.model.item;

import com.itemstore.model.Item;
import com.itemstore.engine.loader.ItemLoader;
import com.itemstore.engine.loader.Loader;

import com.itemstore.model.tag.TagContainer;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Arrays;
import java.util.UUID;

public class ItemLoaderTest {

    private ItemLoader itemLoader;

    @BeforeMethod
    public void setUp() throws Exception {
        itemLoader = new ItemLoader();
    }

    @Test
    public void newEmptyLoader() throws Exception {
        itemLoader.load();
        Assert.assertTrue(itemLoader.getItems().isEmpty());
    }

    @Test
    public void notLoaded() throws Exception {
        Assert.assertTrue(itemLoader.getItems().isEmpty());
    }

    @Test
    public void register_1_item() {
        Item item = new Item.Builder().title("Test ccc").tags(TagContainer.create(Arrays.asList("Swe", "Sport"))).description("some text").build();
        itemLoader.registerItem(item);
        Assert.assertEquals(itemLoader.getItems().size(), 1);
    }

    @Test
    public void fileDoesNotExist() {
        File sourceTempFile = new File("/Users/miguelkrantz/Documents/temp/fileDoesNotExist.dat"); //FIXME

        try {
            itemLoader.setLoadSource(sourceTempFile, Loader.StorageType.Serializable);
            itemLoader.load();
            Assert.assertTrue(itemLoader.getItems().isEmpty());
        } finally {
            FileUtils.deleteQuietly(sourceTempFile);
        }
    }

    @Test
    public void saveLoadItemLoader() throws Exception {
        File sourceTempFile = new File("/Users/miguelkrantz/Documents/temp/ItemLoaderTest.dat"); //FIXME

        UUID randomText = UUID.randomUUID();
        Item item = new Item.Builder().title("Abc" + randomText).tags(TagContainer.create(Arrays.asList("Swe", "Sport"))).description("some text").build();
        itemLoader.setLoadSource(sourceTempFile, Loader.StorageType.Serializable);
        itemLoader.load();

        int currentSizeOnDisk = itemLoader.getItems().size();
        itemLoader.registerItem(item);

        Assert.assertEquals(itemLoader.getItems().size(), currentSizeOnDisk + 1);
        itemLoader.save();
        System.out.println("itemLoader currentSizeOnDisk=" + currentSizeOnDisk);

        ItemLoader newLoader = new ItemLoader();
        newLoader.setLoadSource(sourceTempFile, Loader.StorageType.Serializable);
    }
}