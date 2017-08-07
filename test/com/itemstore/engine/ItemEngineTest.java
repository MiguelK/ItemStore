package com.itemstore.engine;

import com.itemstore.engine.model.Item;
import com.itemstore.engine.model.ItemGroup;
import com.itemstore.engine.model.tag3.TagRoot;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ItemEngineTest {

    @BeforeMethod
    public void setup() {
        ItemEngine.getInstance().clear();
    }

    @Test
    public void items_3()  {

        Item item1 = new Item.Builder().title("Test A").targetURL("dn.se")
                .publishedDate(LocalDateTime.now()).
                        tags(TagRoot.SWE_SPORT.getTags()).build();
        Item item2 = new Item.Builder().title("Test B").targetURL("aik.se").
                tags(TagRoot.SWE_SPORT.getTags()).build();
        Item item3 = new Item.Builder().title("Test C").targetURL("aik.se").
                tags(TagRoot.SWE_SPORT.getTags()).build();
        ItemEngine.getInstance().handleNewItems(Arrays.asList(item1, item2, item3));
        ItemEngine.getInstance().rebuildIndex();

        List<ItemGroup> itemGroups = ItemEngine.getInstance().getAllItemGroupsSortedByDate(null);
        Assert.assertEquals(itemGroups.size(),3);
    }


    @DataProvider(name = "SearchData")
    public Object[][] search() {
        return new Object[][]{
                {new SearchData("swe_sport", null, Collections.emptyList(), 1)},
                {new SearchData("swe_kultur", null, Collections.emptyList(), 0)},
                {new SearchData("swe*", "*aik*", Collections.emptyList(), 1)},
                {new SearchData(TagRoot.ENG_SPORT.getTags(), null, Collections.emptyList(), 2)},
                {new SearchData("swe_sport", "aik", Collections.emptyList(), 1)
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