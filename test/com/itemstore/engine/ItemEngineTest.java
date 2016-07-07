package com.itemstore.engine;

import com.itemstore.engine.model.Item;
import com.itemstore.engine.model.tag3.ItemTagTree;
import com.itemstore.engine.model.tag3.TagRoot;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ItemEngineTest {

    @BeforeMethod
    public void setup() {
        ItemEngine.getInstance().clear();
    }


  /*  @Test
    public void testName() throws SearchItemGroupQuery.InvalidRequestException {
        Item a = new Item.Builder().title("Test A").targetURL("dn.se").
                itemTagTree(new ItemTagTree.Builder(TagRoot.SWE_SPORT).build()).build();

        Item b = new Item.Builder().title("Test B").targetURL("aik.se").
                itemTagTree(new ItemTagTree.Builder(TagRoot.ENG_SPORT).addTagsToSingleTree("aik").build()).build();

        Item c = new Item.Builder().title("Test C").targetURL("aik.se").
                itemTagTree(new ItemTagTree.Builder(TagRoot.ENG_SPORT).addTagsToSingleTree("fotboll_aik").build()).build();

        ItemEngine.getInstance().handleNewItems(Arrays.asList(a, b, c));
        ItemEngine.getInstance().rebuildIndex();

        //includeTagFilter
        //excludeTagFilter
        //excludeItemGroupIds

        Assert.assertEquals(ItemEngine.getInstance().search(SearchItemGroupQuery.create("*aik*", null, Collections.emptyList())).size(), 2);
        Assert.assertEquals(ItemEngine.getInstance().search(SearchItemGroupQuery.create("*dif*", null, Collections.emptyList())).size(), 0);
        Assert.assertEquals(ItemEngine.getInstance().search(SearchItemGroupQuery.create("*dif*", "swe*", Collections.emptyList())).size(), 0);
    }

    @Test
    public void search_by_exclude_tag() throws SearchItemGroupQuery.InvalidRequestException {

        Item item1 = new Item.Builder().title("Test A").targetURL("dn.se").
                itemTagTree(new ItemTagTree.Builder(TagRoot.SWE_SPORT).build()).build();

        Item item2 = new Item.Builder().title("Test B").targetURL("aik.se").
                itemTagTree(new ItemTagTree.Builder(TagRoot.ENG_SPORT).build()).build();

        ItemEngine.getInstance().handleNewItems(Arrays.asList(item1, item2));
        ItemEngine.getInstance().rebuildIndex();

        List<ItemGroup> allItemGroupsSortedByDate = ItemEngine.getInstance().getAllItemGroupsSortedByDate();

        org.testng.Assert.assertEquals(allItemGroupsSortedByDate.size(), 2);

        ItemGroupFilter filter = SearchItemGroupQuery.create("eng_sport", null, Collections.emptyList());

        List<ItemGroup> itemGroups = ItemEngine.getInstance().search(filter);

        Assert.assertTrue(itemGroups.size() == 1);
        Assert.assertEquals(itemGroups.get(0).getItems().get(0), item1);
    }

    @Test
    public void search_by_id() throws SearchItemGroupQuery.InvalidRequestException {

        Item expectedItem = new Item.Builder().title("Test B").targetURL("aik.se").
                itemTagTree(new ItemTagTree.Builder("swe_sport").build()).build();

        ItemEngine.getInstance().handleNewItems(Arrays.asList(new Item.Builder().title("Test A").targetURL("dn.se").
                itemTagTree(new ItemTagTree.Builder("swe_sport").build()).build(), expectedItem, new Item.Builder().title("Test C").targetURL("sune.se").
                itemTagTree(new ItemTagTree.Builder("swe_sport").build()).build()));

        ItemEngine.getInstance().rebuildIndex();

        List<ItemGroup> allItemGroupsSortedByDate = ItemEngine.getInstance().getAllItemGroupsSortedByDate();

        Assert.assertEquals(allItemGroupsSortedByDate.size(), 3);

        ItemGroupFilter filter = SearchItemGroupQuery.create(null, null,
                Collections.emptyList());

        List<ItemGroup> itemGroups = ItemEngine.getInstance().search(filter);

        Assert.assertTrue(itemGroups.size() == 1, "Size=" + itemGroups.size());
        Assert.assertEquals(itemGroups.get(0).getItems().get(0), expectedItem);
    }

    @Test
    public void search_excludeItemGroupId() throws SearchItemGroupQuery.InvalidRequestException {

        Item item1 = new Item.Builder().title("Test A").targetURL("dn.se").
                itemTagTree(new ItemTagTree.Builder("swe_sport").build()).build();

        Item item2 = new Item.Builder().title("Test B").targetURL("aik.se").
                itemTagTree(new ItemTagTree.Builder("eng_sport").build()).build();

        ItemGroup itemGroup1 = new ItemGroup();
        itemGroup1.addItem(item1);

        ItemGroup itemGroup2 = new ItemGroup();
        itemGroup2.addItem(item2);

        ItemEngine.getInstance().handleNewItems(Arrays.asList(item1, item2));
        ItemEngine.getInstance().rebuildIndex();

        List<ItemGroup> allItemGroupsSortedByDate = ItemEngine.getInstance().getAllItemGroupsSortedByDate();

        Assert.assertEquals(allItemGroupsSortedByDate.size(), 2);

        ItemGroupFilter filter = SearchItemGroupQuery.create(null, null,
                Collections.singletonList(itemGroup2.getId()));

        List<ItemGroup> itemGroups = ItemEngine.getInstance().search(filter);

        Assert.assertTrue(itemGroups.size() == 1);
        Assert.assertEquals(itemGroups.get(0).getItems().get(0), item1);
    }

    @Test
    public void search_max_result_20() throws SearchItemGroupQuery.InvalidRequestException {

        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            items.add(new Item.Builder().title("Test B" + i).targetURL("aik.se").
                    itemTagTree(new ItemTagTree.Builder("eng_sport").build()).build());
        }

        ItemEngine.getInstance().handleNewItems(items);
        ItemEngine.getInstance().rebuildIndex();

        List<ItemGroup> allItemGroupsSortedByDate = ItemEngine.getInstance().getAllItemGroupsSortedByDate();

        Assert.assertEquals(allItemGroupsSortedByDate.size(), 100);

        ItemGroupFilter filter = SearchItemGroupQuery.create(null, null,
                Collections.emptyList());

        List<ItemGroup> itemGroups = ItemEngine.getInstance().search(filter);

        Assert.assertTrue(itemGroups.size() == 20, "Was=" + itemGroups.size());
    }

    @Test
    public void search_sortt_on_date() throws SearchItemGroupQuery.InvalidRequestException {

        Item expectedItem = new Item.Builder().publishedDate(LocalDateTime.now()).title("Test B").targetURL("aik.se").
                itemTagTree(new ItemTagTree.Builder("swe_sport").build()).build();

        ItemEngine.getInstance().handleNewItems(Arrays.asList(new Item.Builder().
                        publishedDate(LocalDateTime.now().minusHours(1)).title("Test A").targetURL("dn.se").
                        itemTagTree(new ItemTagTree.Builder("swe_sport").build()).build(), expectedItem,
                new Item.Builder().publishedDate(LocalDateTime.now().minusMinutes(12)).title("Test C").targetURL("sune.se").
                        itemTagTree(new ItemTagTree.Builder("swe_sport").build()).build()));

        ItemEngine.getInstance().rebuildIndex();

        List<ItemGroup> allItemGroupsSortedByDate = ItemEngine.getInstance().getAllItemGroupsSortedByDate();

        Assert.assertEquals(allItemGroupsSortedByDate.size(), 3);

        ItemGroupFilter filter = SearchItemGroupQuery.create(null, null,
                Collections.emptyList());

        List<ItemGroup> itemGroups = ItemEngine.getInstance().search(filter);

        Assert.assertTrue(itemGroups.size() == 3, "Size=" + itemGroups.size());
        Assert.assertEquals(itemGroups.get(0).getItems().get(0), expectedItem, "Item1 has time now, should be first");
    }*/


    @Test(dataProvider = "SearchData")
    public void search(SearchData searchData) throws SearchItemGroupQuery.InvalidRequestException {

        Item a = new Item.Builder().title("Test A").targetURL("dn.se").
                itemTagTree(new ItemTagTree.Builder(TagRoot.SWE_SPORT).build()).build();

        Item b = new Item.Builder().title("Test B").targetURL("aik.se").
                itemTagTree(new ItemTagTree.Builder(TagRoot.ENG_SPORT).addTagsToSingleTree("aik").build()).build();

        Item c = new Item.Builder().title("Test C").targetURL("aik.se").
                itemTagTree(new ItemTagTree.Builder(TagRoot.ENG_SPORT).addTagsToSingleTree("fotboll_aik").build()).build();

        ItemEngine.getInstance().handleNewItems(Arrays.asList(a, b, c));
        ItemEngine.getInstance().rebuildIndex();

        Assert.assertEquals(ItemEngine.getInstance().search(SearchItemGroupQuery.create(searchData.getIncludeTagFilter(),
                null, Collections.emptyList())).size(), searchData.getResult());
    }

    @DataProvider(name = "SearchData")
    public Object[][] search() {
        return new Object[][]{
                {new SearchData("swe_sport", null, Collections.emptyList(), 1)},
                {new SearchData("swe_kultur", null, Collections.emptyList(), 0)},
                {new SearchData("swe*", "*aik*", Collections.emptyList(), 1)},
                {new SearchData(TagRoot.ENG_SPORT.getTags(), null, Collections.emptyList(), 2)},
                {new SearchData("swe_sport", "aik", Collections.emptyList(),1)
                }};
    }

    private static class SearchData {
        private String includeTagFilter;
        private String excludeTagFilter;
        private List<Integer> excludeItemGroupIds;

        private int result;

        public SearchData(String includeTagFilter, String excludeTagFilter, List<Integer> excludeItemGroupIds, int result) {
            this.includeTagFilter = includeTagFilter;
            this.excludeTagFilter = excludeTagFilter;
            this.excludeItemGroupIds = excludeItemGroupIds;
            this.result = result;
        }

        public int getResult() {
            return result;
        }

        public String getIncludeTagFilter() {
            return includeTagFilter;
        }

        public String getExcludeTagFilter() {
            return excludeTagFilter;
        }

        public List<Integer> getExcludeItemGroupIds() {
            return excludeItemGroupIds;
        }
    }
}