package com.itemstore.api.request;

import com.itemstore.engine.ItemEngine;
import com.itemstore.engine.ItemGroupFilter;
import com.itemstore.engine.model.Item;
import com.itemstore.engine.model.ItemGroup;
import com.itemstore.engine.model.tag3.TagTree;
import junit.framework.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.*;

public class SearchItemGroupRequestTest {

    @Test(expectedExceptions = SearchItemGroupRequest.InvalidRequestException.class)
    public void create_with_null_excludeItemGroupIds() throws SearchItemGroupRequest.InvalidRequestException {
        SearchItemGroupRequest.create(null, null, null, null, null);
    }

    @Test(expectedExceptions = SearchItemGroupRequest.InvalidRequestException.class)
    public void create_with_null_itemGroupIds() throws SearchItemGroupRequest.InvalidRequestException {
        SearchItemGroupRequest.create(null, null, null, null, null);
    }

    @Test(expectedExceptions = SearchItemGroupRequest.InvalidRequestException.class)
    public void create_invalid_exclude_filter() throws SearchItemGroupRequest.InvalidRequestException {
        SearchItemGroupRequest.create("invalid exclude filter", null, null, null, null);
    }

    @Test(expectedExceptions = SearchItemGroupRequest.InvalidRequestException.class)
    public void create_invalid_filters() throws SearchItemGroupRequest.InvalidRequestException {
        SearchItemGroupRequest.create("invalid exclude filter", "invalid'", null, null, null);
    }

    @Test(expectedExceptions = SearchItemGroupRequest.InvalidRequestException.class)
    public void create_invalid_favoriteTagFilter_filters() throws SearchItemGroupRequest.InvalidRequestException {
        SearchItemGroupRequest.create(null, "invalid'", null, null, null);
    }

    @Test
    public void create_default_empty() throws SearchItemGroupRequest.InvalidRequestException {
        SearchItemGroupRequest request = SearchItemGroupRequest.create(" ", " ",
                Collections.emptyList(), Collections.emptyList(), null);
        Assert.assertTrue(request.getExcludeIds().isEmpty());
        Assert.assertTrue(request.getItemIds().isEmpty());
        Assert.assertNull(request.getExcludeTag());
        Assert.assertNull("" +request.getFavoriteTag(),request.getFavoriteTag());
        Assert.assertTrue(request.getMaxResult() > 0);
    }

    @Test
    public void create_default_null() throws SearchItemGroupRequest.InvalidRequestException {
        SearchItemGroupRequest request = SearchItemGroupRequest.create(null, null,
                Collections.emptyList(), Collections.emptyList(), null);
        Assert.assertTrue(request.getExcludeIds().isEmpty());
        Assert.assertTrue(request.getItemIds().isEmpty());
        Assert.assertNull(request.getExcludeTag());
        Assert.assertNull(request.getFavoriteTag());
        Assert.assertTrue(request.getMaxResult() > 0);
    }

    @Test
    public void valid_excludeTagFilter() throws SearchItemGroupRequest.InvalidRequestException {
        SearchItemGroupRequest request = SearchItemGroupRequest.create("swe_sport,swe_news", null,
                Collections.emptyList(),Collections.emptyList(), null);
        Assert.assertNotNull(request.getExcludeTag());
    }

    @Test
    public void valid_tagFilters() throws SearchItemGroupRequest.InvalidRequestException {
        SearchItemGroupRequest request = SearchItemGroupRequest.create("swe_sport,swe_news", "swe_sport_*",
                Collections.emptyList(), Collections.emptyList(), null);
        Assert.assertNotNull(request.getExcludeTag());
    }

    @Test
    public void testToString() throws SearchItemGroupRequest.InvalidRequestException {
        SearchItemGroupRequest request = SearchItemGroupRequest.create("swe_sport,swe_news", "swe_sport_*",
                Collections.emptyList(), Collections.emptyList(), null);

        Assert.assertTrue(request.toString().contains("swe_sport"));
    }

    @Test
    public void search_by_exclude_tag() throws SearchItemGroupRequest.InvalidRequestException {

        Item item1 = new Item.Builder().title("Test A").targetURL("dn.se").
                tags(new TagTree.Builder("swe_sport").build()).build();

        Item item2 = new Item.Builder().title("Test B").targetURL("aik.se").
                tags(new TagTree.Builder("eng_sport").build()).build();

        ItemGroup itemGroup1 = new ItemGroup();
        itemGroup1.addItem(item1);

        ItemGroup itemGroup2 = new ItemGroup();
        itemGroup2.addItem(item2);

        ItemEngine.getInstance().handleNewItems(Arrays.asList(item1, item2));
        ItemEngine.getInstance().rebuildIndex();

        List<ItemGroup> allItemGroupsSortedByDate = ItemEngine.getInstance().getAllItemGroupsSortedByDate();

        org.testng.Assert.assertEquals(allItemGroupsSortedByDate.size(), 2);

        ItemGroupFilter filter =  SearchItemGroupRequest.create("eng_sport",null,Collections.emptyList(),
                Collections.emptyList(),10);

        List<ItemGroup> itemGroups = ItemEngine.getInstance().search(filter);

        org.testng.Assert.assertTrue(itemGroups.size() == 1);
        org.testng.Assert.assertEquals(itemGroups.get(0).getItems().get(0), item1);
    }

    @Test
    public void search_by_id() throws SearchItemGroupRequest.InvalidRequestException {

        Item item1 = new Item.Builder().title("Test A").targetURL("dn.se").
                tags(new TagTree.Builder("swe_sport").build()).build();

        Item item2 = new Item.Builder().title("Test B").targetURL("aik.se").
                tags(new TagTree.Builder("swe_sport").build()).build();

        Item item3 = new Item.Builder().title("Test C").targetURL("sune.se").
                tags(new TagTree.Builder("swe_sport").build()).build();


        ItemGroup itemGroup1 = new ItemGroup();
        itemGroup1.addItem(item1);

        ItemGroup itemGroup2 = new ItemGroup();
        itemGroup2.addItem(item2);

        ItemGroup itemGroup3 = new ItemGroup();
        itemGroup3.addItem(item3);

        ItemEngine.getInstance().handleNewItems(Arrays.asList(item1, item2, item3));
        ItemEngine.getInstance().rebuildIndex();

        List<ItemGroup> allItemGroupsSortedByDate = ItemEngine.getInstance().getAllItemGroupsSortedByDate();

        org.testng.Assert.assertEquals(allItemGroupsSortedByDate.size(), 3);

        ItemGroupFilter filter = SearchItemGroupRequest.create(null, null,
                 Collections.emptyList(),Collections.singletonList(itemGroup2.getId()),10);

        List<ItemGroup> itemGroups = ItemEngine.getInstance().search(filter);

        org.testng.Assert.assertTrue(itemGroups.size() == 1, "Size=" + itemGroups.size());
        org.testng.Assert.assertEquals(itemGroups.get(0).getItems().get(0), item2);
    }
}