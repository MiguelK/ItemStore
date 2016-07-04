package com.itemstore.api.request;

import com.itemstore.engine.SearchItemGroupQuery;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;

public class SearchItemGroupQueryTest {

    @Test(expectedExceptions = SearchItemGroupQuery.InvalidRequestException.class)
    public void create_with_null_excludeItemGroupIds() throws SearchItemGroupQuery.InvalidRequestException {
        SearchItemGroupQuery.create(null, null, null, null, null);
    }

    @Test(expectedExceptions = SearchItemGroupQuery.InvalidRequestException.class)
    public void create_with_null_itemGroupIds() throws SearchItemGroupQuery.InvalidRequestException {
        SearchItemGroupQuery.create(null, null, null, null, null);
    }

    @Test(expectedExceptions = SearchItemGroupQuery.InvalidRequestException.class)
    public void create_invalid_exclude_filter() throws SearchItemGroupQuery.InvalidRequestException {
        SearchItemGroupQuery.create("invalid exclude filter", null, null, null, null);
    }

    @Test(expectedExceptions = SearchItemGroupQuery.InvalidRequestException.class)
    public void create_invalid_filters() throws SearchItemGroupQuery.InvalidRequestException {
        SearchItemGroupQuery.create("invalid exclude filter", "invalid'", null, null, null);
    }

    @Test(expectedExceptions = SearchItemGroupQuery.InvalidRequestException.class)
    public void create_invalid_favoriteTagFilter_filters() throws SearchItemGroupQuery.InvalidRequestException {
        SearchItemGroupQuery.create(null, "invalid'", null, null, null);
    }

    @Test
    public void create_default_empty() throws SearchItemGroupQuery.InvalidRequestException {
        SearchItemGroupQuery request = SearchItemGroupQuery.create(" ", " ",
                Collections.emptyList(), Collections.emptyList(), null);
        Assert.assertTrue(request.getExcludeIds().isEmpty());
        Assert.assertTrue(request.getItemIds().isEmpty());
        Assert.assertNull(request.getExcludeTag());
        Assert.assertNull(request.getFavoriteTag(),"" + request.getFavoriteTag());
        Assert.assertTrue(request.getMaxResult() > 0);
    }

    @Test
    public void create_default_null() throws SearchItemGroupQuery.InvalidRequestException {
        SearchItemGroupQuery request = SearchItemGroupQuery.create(null, null,
                Collections.emptyList(), Collections.emptyList(), null);
        Assert.assertTrue(request.getExcludeIds().isEmpty());
        Assert.assertTrue(request.getItemIds().isEmpty());
        Assert.assertNull(request.getExcludeTag());
        Assert.assertNull(request.getFavoriteTag());
        Assert.assertTrue(request.getMaxResult() > 0);
    }

    @Test
    public void valid_excludeTagFilter() throws SearchItemGroupQuery.InvalidRequestException {
        SearchItemGroupQuery request = SearchItemGroupQuery.create("swe_sport,swe_news", null,
                Collections.emptyList(), Collections.emptyList(), null);
        Assert.assertNotNull(request.getExcludeTag());
    }

    @Test
    public void valid_tagFilters() throws SearchItemGroupQuery.InvalidRequestException {
        SearchItemGroupQuery request = SearchItemGroupQuery.create("swe_sport,swe_news", "swe_sport_*",
                Collections.emptyList(), Collections.emptyList(), null);
        Assert.assertNotNull(request.getExcludeTag());
    }

    @Test
    public void testToString() throws SearchItemGroupQuery.InvalidRequestException {
        SearchItemGroupQuery request = SearchItemGroupQuery.create("swe_sport,swe_news", "swe_sport_*",
                Collections.emptyList(), Collections.emptyList(), null);

        Assert.assertTrue(request.toString().contains("swe_sport"), request.toString());
    }

}