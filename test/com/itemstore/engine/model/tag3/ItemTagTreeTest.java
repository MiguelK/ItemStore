package com.itemstore.engine.model.tag3;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class ItemTagTreeTest {

    @Test
    public void parseMultiTag() {
        ItemTagTree itemTagTreeA = new ItemTagTree.Builder(RootTag.SWE_NYHETER).addTagsToSingleTree("Zlatan").build();
        Assert.assertEquals(itemTagTreeA.getTagDescendants().size(), 2);
    }

    @Test
    public void parseTagTrim() {
        ItemTagTree itemTagTreeA = new ItemTagTree.Builder(" eng_news ").addTagsToSingleTree(" usa_newyork,usa").build();
        Assert.assertEquals(itemTagTreeA.getTagDescendants().size(), 3);
    }

    @Test
    public void tagDescendants_wth_added_tags()  {

        /*ItemTagTree itemTagTreeB = new ItemTagTree.Builder("swe_sport_fotboll")
                .addTagsToSingleTree(Arrays.asList("zlatan", "OS-2016","fotboll_sverige_landslaget")).build();

        Assert.assertEquals(itemTagTreeB.getTagDescendants().size(), 4);

        Assert.assertEquals(itemTagTreeB.getTagDescendants().get(0).getItemTagTree(),"swe_sport_fotboll");
        Assert.assertEquals(itemTagTreeB.getTagDescendants().get(1).getItemTagTree(),"swe_sport_fotboll_zlatan");
        Assert.assertEquals(itemTagTreeB.getTagDescendants().get(2).getItemTagTree(),"swe_sport_fotboll_OS-2016");
        Assert.assertEquals(itemTagTreeB.getTagDescendants().get(3).getItemTagTree(),"swe_sport_fotboll_fotboll_sverige_landslaget");*/
    }

    @Test
    public void toStringSingleTree() {
       /* ItemTagTree itemTagTreeB = new ItemTagTree.Builder("swe_sport_fotboll")
                .addTagsToSingleTree(Arrays.asList("zlatan", "OS-2016")).build();

        Assert.assertTrue(itemTagTreeB.toString().contains("swe_sport_fotboll"), itemTagTreeB.toString());
        Assert.assertTrue(itemTagTreeB.toString().contains("swe_sport_fotboll_zlatan"), itemTagTreeB.toString());
        Assert.assertTrue(itemTagTreeB.toString().contains("swe_sport_fotboll_OS-2016"), itemTagTreeB.toString()); */
    }


    @Test
    public void equal_full_match() {
        ItemTagTree itemTagTreeA = new ItemTagTree.Builder("swe_sport").build();
        ItemTagTree.Filter searchFilter = ItemTagTree.Filter.parse("swe_sport_*");

        double result = itemTagTreeA.match(searchFilter);

        Assert.assertEquals(result, 1.0);
    }

    @Test
    public void match_no_wildcard() {
        ItemTagTree itemTagTreeA = new ItemTagTree.Builder("swe_sport").addTagsToSingleTree("fotboll_aik").build();
        ItemTagTree.Filter searchFilter = ItemTagTree.Filter.parse("swe_sport");

        double result = itemTagTreeA.match(searchFilter);

        Assert.assertEquals(result, 1.0);
    }


    @Test
    public void no_match() {
        ItemTagTree itemTagTreeB = new ItemTagTree.Builder("swe_sport").addTagsToSingleTree("Zlatan").build();
        ItemTagTree.Filter searchFilter = ItemTagTree.Filter.parse("swe_kultur_*");

        double result = itemTagTreeB.match(searchFilter);

        Assert.assertEquals(result, 0.0);
    }

    @Test
    public void no_match_2() {
        ItemTagTree itemTagTreeB = new ItemTagTree.Builder("swe_sport").build();
        ItemTagTree.Filter searchFilter = ItemTagTree.Filter.parse("swe_kultur");

        double result = itemTagTreeB.match(searchFilter);

        Assert.assertEquals(result, 0.0);
    }
    /*
    @Test
    public void no_match_many_nodes() {
        ItemTagTree itemTagTreeB = new ItemTagTree.Builder(Arrays.asList("swe_sport_zlatan", "swe_nyheter", "eng_news", "swe_kultur")).build();
        ItemTagTree itemTagTreeA = new ItemTagTree.Builder("swe_sport_os2016").build();

        double result = itemTagTreeB.match(itemTagTreeA);

        Assert.assertEquals(result, 0.0);
    }*/

    @Test
    public void addTagsToSingleTree() {
        //"swe_sport_os2016"
        ItemTagTree itemTagTreeA = new ItemTagTree.Builder(RootTag.SWE_SPORT).
                addTagsToSingleTree("os2016,os2016_a,os2016_b,os2016_c")
                .build();

        //swe_sport
        //swe_sport_os2016
        //swe_sport_os2016_a
        //swe_sport_os2016_b
        //swe_sport_os2016_c
        Assert.assertEquals(itemTagTreeA.getTagDescendants().size(), 5);
    }

    @Test(expectedExceptions = TagTreeException.class)
    public void invalidTag_1() {
        ItemTagTree itemTagTreeA = new ItemTagTree.Builder("swe_*").build();
        Assert.fail("Invalid tag " + itemTagTreeA);
    }

    @Test(expectedExceptions = TagTreeException.class)
    public void invalidTag_blank() {
        ItemTagTree itemTagTreeA = new ItemTagTree.Builder(" ").build();
        Assert.fail("Invalid tag " + itemTagTreeA);
    }

    @Test(expectedExceptions = TagTreeException.class)
    public void invalidTag_null() {
        ItemTagTree itemTagTreeA = new ItemTagTree.Builder((String)null).build();
        Assert.fail("Invalid tag " + itemTagTreeA);
    }

    //@Test(expectedExceptions = TagTreeException.class)
    public void addTagsToSingleTree_not_singletree() {
       /* ItemTagTree itemTagTreeA = new ItemTagTree.Builder(Arrays.asList("swe_sport_os2016", "swe_kultur_z"))
                .addTagsToSingleTree(Arrays.asList("a", "b", "c")).build();
        Assert.fail("itemTagTreeA is not a single tree" + itemTagTreeA); */
    }
}
