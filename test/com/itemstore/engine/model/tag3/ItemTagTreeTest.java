package com.itemstore.engine.model.tag3;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ItemTagTreeTest {

    @Test
    public void parseMultiTag() {
        ItemTagTree itemTagTreeA = new ItemTagTree.Builder(TagRoot.SWE_NYHETER).addTagsToSingleTree("Zlatan").build();
        Assert.assertEquals(itemTagTreeA.getTagTreePaths().size(), 2);
    }

    @Test
    public void parseTagTrim() {
        ItemTagTree itemTagTreeA = new ItemTagTree.Builder(" eng_news ").addTagsToSingleTree(" usa_newyork,usa").build();
        Assert.assertEquals(itemTagTreeA.getTagTreePaths().size(), 3);
    }

    @Test
    public void tag_tree_path_wth_added_tags()  {

        ItemTagTree itemTagTreeB = new ItemTagTree.Builder("swe_sport")
                .addTagsToSingleTree("fotboll_zlatan, fotboll_OS-2016, fotboll_fotboll_sverige_landslaget").build();

        Assert.assertEquals(itemTagTreeB.getTagTreePaths().size(), 4);

        Assert.assertEquals(itemTagTreeB.getTagTreePaths().get(0).getTagTreePath(),"swe_sport");
        Assert.assertEquals(itemTagTreeB.getTagTreePaths().get(1).getTagTreePath(),"swe_sport_fotboll_zlatan");
        Assert.assertEquals(itemTagTreeB.getTagTreePaths().get(2).getTagTreePath(),"swe_sport_fotboll_OS-2016");
        Assert.assertEquals(itemTagTreeB.getTagTreePaths().get(3).getTagTreePath(),"swe_sport_fotboll_fotboll_sverige_landslaget");
    }

    @Test
    public void toStringSingleTree() {
        ItemTagTree itemTagTreeB = new ItemTagTree.Builder("swe_sport")
                .addTagsToSingleTree("fotboll,fotboll_zlatan,fotbollOS-2016").build();

        Assert.assertTrue(itemTagTreeB.toString().contains("swe_sport_fotboll"), itemTagTreeB.toString());
        Assert.assertTrue(itemTagTreeB.toString().contains("swe_sport_fotboll_zlatan"), itemTagTreeB.toString());
        Assert.assertTrue(itemTagTreeB.toString().contains("swe_sport_fotbollOS-2016"), itemTagTreeB.toString());
    }

    @Test
    public void getTagTreePathsAsString() {
        ItemTagTree itemTagTreeB = new ItemTagTree.Builder(TagRoot.SWE_SPORT)
                .addTagsToSingleTree("aik,fotboll_aik,Karlberg_fotboll_AIK").build();


        //swe_sport_fotboll_aik,swe_sport_Karlberg_fotboll_AIK
        System.out.println(itemTagTreeB.toString());
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
        ItemTagTree itemTagTreeA = new ItemTagTree.Builder(TagRoot.SWE_SPORT).
                addTagsToSingleTree("os2016,os2016_a,os2016_b,os2016_c")
                .build();

        //swe_sport
        //swe_sport_os2016
        //swe_sport_os2016_a
        //swe_sport_os2016_b
        //swe_sport_os2016_c
        Assert.assertEquals(itemTagTreeA.getTagTreePaths().size(), 5);
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
}
