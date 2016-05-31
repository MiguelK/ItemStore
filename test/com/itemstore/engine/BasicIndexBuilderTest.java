package com.itemstore.engine;

import com.itemstore.engine.model.Item;
import com.itemstore.engine.model.tag3.TagTree;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class BasicIndexBuilderTest {

    @Test
    public void buildIndex_2_item_groups() {
        List<Item> items = new ArrayList<Item>();

        items.add(new Item.Builder().title("Test aaa").targetURL("www.dn.se")
                .tags(new TagTree.Builder("swe_sport").build()).build());
        items.add(new Item.Builder().title("Test DUPLICATE").targetURL("www.aik.se")
                .tags(new TagTree.Builder("swe_sport").build()).build());

        BasicIndexBuilder basicIndexBuilder = new BasicIndexBuilder(new HashSet<>(items));
        BasicIndexBuilder.Result result = basicIndexBuilder.buildIndex();

        Assert.assertEquals(result.getItemGroups().size(), 2);
    }


   /* @Test
    public void buildIndex_3_composite_exclude_eng() {
        List<Item> items = new ArrayList<Item>();

        items.add(new Item.Builder().title("Test aaa").tags(TagContainer.create(Arrays.asList("Swe", "Sport"))).build());
        items.add(new Item.Builder().title("Test DUPLICATE").tags(TagContainer.create(Arrays.asList("Swe", "Sport"))).build());
        items.add(new Item.Builder().title("Test xbbb").tags(TagContainer.create(Arrays.asList("Swe", "Sport"))).build());
        items.add(new Item.Builder().title("Test bbb").tags(TagContainer.create(Arrays.asList("Eng", "Sport"))).build());

        User user1 = new User();
        user1.addExcludeTag("Eng");

        BasicIndexBuilder indexBuilder = new BasicIndexBuilder(items);

        Map<String, BasicIndexBuilder.UserResult> userItems = indexBuilder.buildIndex().getUserItems();
        Assert.assertEquals(userItems.get(user1.getId()).getItemGroups().size(), 3);
    }

    @Test
    public void buildIndex_2_composite() {
        List<Item> items = new ArrayList<Item>();
        items.add(new Item.Builder().title("Test aaa").tags(TagContainer.create(Arrays.asList("Swe", "Sport"))).description("some text").build());
        items.add(new Item.Builder().title("Test bbb").tags(TagContainer.create(Arrays.asList("Swe", "Sport"))).description("some stuff...").build());
        items.add(new Item.Builder().title("Test bbb").tags(TagContainer.create(Arrays.asList("Swe", "Sport", "Mode"))).description("bara bra").build());
        items.add(new Item.Builder().title("Test bbb").tags(TagContainer.create(Arrays.asList("Swe", "Sport", "Mode"))).description("bara bra").build());

        User user1 = new User();
        user1.addExcludeTag("Eng");

        BasicIndexBuilder indexBuilder = new BasicIndexBuilder(items);

        Map<String, BasicIndexBuilder.UserResult> userItems = indexBuilder.buildIndex().getUserItems();

        Assert.assertEquals(userItems.get(user1.getId()).getItemGroups().size(), 2);
    }

    @Test
    public void addReceivedItems_1() {
        Item item = new Item.Builder().title("Test aaa").tags(TagContainer.create(Arrays.asList("Swe", "Sport"))).description("some text").build();
        List<Item> items = Collections.singletonList(item);

        User user = new User();

        Map<String, BasicIndexBuilder.UserResult> userItems = new BasicIndexBuilder(items)
                .buildIndex().getUserItems();
        Assert.assertEquals(userItems.get(user.getId()).getItemGroups().size(), 1);

        user.addReceivedItems(Collections.singletonList(item.getId()));

        userItems = new BasicIndexBuilder(items)
                .buildIndex().getUserItems();
        Assert.assertNull(userItems.get(user.getId()));
    }

    @Test
    public void topNews_should_be_sorted_by_createdDate() {
        List<Item> items = new ArrayList<Item>();
        items.add(new Item.Builder().title("Test aaa").tags(TagContainer.create(Arrays.asList("Swe", "Sport"))).build());
        items.add(new Item.Builder().title("Test ssss").tags(TagContainer.create(Arrays.asList("Swe", "News"))).build());
        items.add(new Item.Builder().title("Test bbb").tags(TagContainer.create(Arrays.asList("Swe", "Sport", "Mode"))).build());


        BasicIndexBuilder indexBuilder = new BasicIndexBuilder(items);

        Map<String, BasicIndexBuilder.UserResult> userItems = indexBuilder.buildIndex().getUserItems();

        List<ItemGroup> itemGroups = userItems.get("id_1").getItemGroups();

        Assert.assertEquals(itemGroups.size(), 3);
    }*/
}