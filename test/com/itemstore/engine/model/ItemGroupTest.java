package com.itemstore.engine.model;

import com.itemstore.engine.model.tag3.TagRoot;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ItemGroupTest {

    @Test
    public void testToString() {
        ItemGroup itemGroup1 = new ItemGroup();
        itemGroup1.addItem(new Item.Builder().title("A").
                tags(TagRoot.SWE_NYHETER.getTags()).
                publishedDate(LocalDateTime.now().minusDays(2)).
                targetURL("dn.se").build());

        Assert.assertTrue(itemGroup1.toString().contains("latestItemPublishedDate"));

    }

    @Test
    public void sort_by_date_3_item_groups() {

        String tags = "swe_sport,zlatan";
        ItemGroup itemGroup1 = new ItemGroup();
        itemGroup1.addItem(new Item.Builder().title("A").tags(tags).publishedDate(LocalDateTime.now().minusDays(2)).targetURL("dn.se").build());


        ItemGroup itemGroup2 = new ItemGroup();
        itemGroup2.addItem(new Item.Builder().title("B").tags(tags).publishedDate(LocalDateTime.now().minusMinutes(1)).targetURL("dn.se").build());


        ItemGroup itemGroup3 = new ItemGroup();
        itemGroup3.addItem(new Item.Builder().title("C").tags(tags).publishedDate(LocalDateTime.now()).targetURL("dn.se").build());

        List<ItemGroup> itemGroups = new ArrayList<>();
        itemGroups.add(itemGroup1);
        itemGroups.add(itemGroup2);
        itemGroups.add(itemGroup3);


        Assert.assertEquals(itemGroups.get(0), itemGroup1);
        Assert.assertEquals(itemGroups.get(1), itemGroup2);
        Assert.assertEquals(itemGroups.get(2), itemGroup3);

        itemGroups.sort(ItemGroup.PUBLISHED_DATE_SORTER);

        Assert.assertEquals(itemGroups.get(0), itemGroup3);
        Assert.assertEquals(itemGroups.get(1),itemGroup2);
        Assert.assertEquals(itemGroups.get(2),itemGroup1);
    }
}