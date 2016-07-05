package com.itemstore.engine.model.tag3;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TagTreeFilterTest {
    @Test
    public void prefix_sufix_wildcard()  {
        ItemTagTree itemTagTreeA = new ItemTagTree.Builder("swe_sport")
                .addTagsToSingleTree("allsvenskan_zlatan_os2020, fotboll_zlatan").build();

        Assert.assertEquals(itemTagTreeA.match(TagTreeFilter.parse("*zlatan*")), 1.0);
        Assert.assertEquals(itemTagTreeA.match(TagTreeFilter.parse(" *zlatan* ")), 1.0);
     //   Assert.assertEquals(itemTagTreeA.match(TagTreeFilter.parse(" *zlatan * ")), 1.0);
    }

    @Test
    public void prefix_sufix_wildcard_no_hit()  {
        ItemTagTree itemTagTreeA = new ItemTagTree.Builder("swe_sport")
                .addTagsToSingleTree("allsvenskan_zlatan_os2020, fotboll_zlatan").build();
        TagTreeFilter searchTagTreeFilter = TagTreeFilter.parse("swe_nyheter_zlatan*");

        double result = itemTagTreeA.match(searchTagTreeFilter);

        Assert.assertEquals(result, 0.0);

    }
    @Test
    public void equal_full_match() {
        ItemTagTree itemTagTreeA = new ItemTagTree.Builder("swe_sport").build();
        TagTreeFilter searchTagTreeFilter = TagTreeFilter.parse("swe_sport_*");

        double result = itemTagTreeA.match(searchTagTreeFilter);

        Assert.assertEquals(result, 1.0);
    }

    @Test
    public void match_no_wildcard() {
        ItemTagTree itemTagTreeA = new ItemTagTree.Builder("swe_sport").addTagsToSingleTree("fotboll_aik").build();
        TagTreeFilter searchTagTreeFilter = TagTreeFilter.parse("swe_sport");

        double result = itemTagTreeA.match(searchTagTreeFilter);

        Assert.assertEquals(result, 1.0);
    }

    @Test
    public void no_match() {
        ItemTagTree itemTagTreeB = new ItemTagTree.Builder("swe_sport").addTagsToSingleTree("Zlatan").build();
        TagTreeFilter searchTagTreeFilter = TagTreeFilter.parse("swe_kultur_*");

        double result = itemTagTreeB.match(searchTagTreeFilter);

        Assert.assertEquals(result, 0.0);
    }

    @Test
    public void no_match_2() {
        ItemTagTree itemTagTreeB = new ItemTagTree.Builder(RootTag.ENG_SPORT).build();
        TagTreeFilter searchTagTreeFilter = TagTreeFilter.parse("swe_kultur");

        double result = itemTagTreeB.match(searchTagTreeFilter);

        Assert.assertEquals(result, 0.0);
    }

}