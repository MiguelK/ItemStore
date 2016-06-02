package com.itemstore.api.request;

import junit.framework.Assert;
import org.testng.annotations.Test;

import java.util.Collections;

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

        org.testng.Assert.assertTrue(request.toString().contains("swe_sport"));

    }
}