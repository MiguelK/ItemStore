package com.itemstore.engine;

import com.itemstore.engine.model.Item;
import com.itemstore.engine.model.ItemGroup;
import com.itemstore.engine.model.tag3.TagTree;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class ItemGroupFilterTest {

    @Test
    public void search_by_exclude_tag() {

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

        Assert.assertEquals(allItemGroupsSortedByDate.size(), 2);

        ItemGroupFilter filter = new ItemGroupFilterImpl(new ArrayList<>(),Collections.emptyList(), 10,
                new TagTree.Builder("eng_sport").build(), null);

        List<ItemGroup> itemGroups = ItemEngine.getInstance().search(filter);

        Assert.assertTrue(itemGroups.size() == 1);
        Assert.assertEquals(itemGroups.get(0).getItems().get(0), item1);
    }

    @Test
    public void search_by_id() {

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

        Assert.assertEquals(allItemGroupsSortedByDate.size(), 3);

        ItemGroupFilter filter = new ItemGroupFilterImpl(null, Collections.singletonList(itemGroup2.getId()), 10, null, null);

        List<ItemGroup> itemGroups = ItemEngine.getInstance().search(filter);

        Assert.assertTrue(itemGroups.size() == 1,"Size=" + itemGroups.size());
        Assert.assertEquals(itemGroups.get(0).getItems().get(0), item2);
    }

    class ItemGroupFilterImpl implements com.itemstore.engine.ItemGroupFilter {
        //(exludeIds,tags(lang 0.1),favories, date?
        private List<Integer> excludeIds = new ArrayList<>();
        private List<Integer> itemIds = new ArrayList<>(); //Only this
        private int maxResult;
        private TagTree excludeTag; //0...1
        private TagTree favoriteTag; //0...1

        public ItemGroupFilterImpl(List<Integer> excludeIds, List<Integer> itemIds, int maxResult,
                               TagTree excludeTag, TagTree favoriteTag) {
            this.excludeIds = excludeIds;
            this.itemIds = itemIds;
            this.maxResult = maxResult;
            this.excludeTag = excludeTag;
            this.favoriteTag = favoriteTag;
        }

        public List<Integer> getExcludeIds() {
            return excludeIds;
        }

        public List<Integer> getItemIds() {
            return itemIds;
        }

        public int getMaxResult() {
            return maxResult;
        }

        public TagTree getExcludeTag() {
            return excludeTag;
        }

        public TagTree getFavoriteTag() {
            return favoriteTag;
        }
    }
}