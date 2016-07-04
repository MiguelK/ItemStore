package com.itemstore.engine.model.tag3;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

public class TagTreeTest {

    @Test
    public void parseMultiTag() {
        TagTree tagTreeA = new TagTree.Builder(RootTag.SWE_NYHETER.getTags()+"_os2016,swe_sport_zlatan").build();
        Assert.assertEquals(tagTreeA.getTagDescendants().size(), 2);
    }

    @Test
    public void parseMultiTagTrim() {
        TagTree tagTreeA = new TagTree.Builder(" eng_*,swe_sport_os2016 ,swe_sport_zlatan ").build();
        Assert.assertEquals(tagTreeA.getTagDescendants().size(), 3);
    }

    @Test
    public void tagDescendants_wth_added_tags()  {

        TagTree tagTreeB = new TagTree.Builder("swe_sport_fotboll")
                .addTagsToSingleTree(Arrays.asList("zlatan", "OS-2016","fotboll_sverige_landslaget")).build();

        Assert.assertEquals(tagTreeB.getTagDescendants().size(), 4);

        Assert.assertEquals(tagTreeB.getTagDescendants().get(0).getTags(),"swe_sport_fotboll");
        Assert.assertEquals(tagTreeB.getTagDescendants().get(1).getTags(),"swe_sport_fotboll_zlatan");
        Assert.assertEquals(tagTreeB.getTagDescendants().get(2).getTags(),"swe_sport_fotboll_OS-2016");
        Assert.assertEquals(tagTreeB.getTagDescendants().get(3).getTags(),"swe_sport_fotboll_fotboll_sverige_landslaget");
    }

    @Test
    public void toStringSingleTree() {
        TagTree tagTreeB = new TagTree.Builder("swe_sport_fotboll")
                .addTagsToSingleTree(Arrays.asList("zlatan", "OS-2016")).build();

        Assert.assertTrue(tagTreeB.toString().contains("swe_sport_fotboll"), tagTreeB.toString());
        Assert.assertTrue(tagTreeB.toString().contains("swe_sport_fotboll_zlatan"), tagTreeB.toString());
        Assert.assertTrue(tagTreeB.toString().contains("swe_sport_fotboll_OS-2016"), tagTreeB.toString());
    }

    @Test
    public void equal_full_match() {
        TagTree tagTreeA = new TagTree.Builder("swe_sport_*").build();
        TagTree tagTreeB = new TagTree.Builder("swe_sport_*").build();

        double result = tagTreeA.match(tagTreeB);

        Assert.assertEquals(result, 1.0);
    }

    @Test
    public void star_full_match() {
        TagTree tagTreeA = new TagTree.Builder("swe_*").build();
        TagTree tagTreeB = new TagTree.Builder(Arrays.asList("swe_sport_zlatan", "swe_nyheter")).build();

        double result = tagTreeA.match(tagTreeB);

        Assert.assertEquals(result, 1.0);
    }

    @Test
    public void no_match() {
        TagTree tagTreeB = new TagTree.Builder(Arrays.asList("swe_sport_zlatan", "swe_nyheter")).build();
        TagTree tagTreeA = new TagTree.Builder("swe_*").build();

        double result = tagTreeB.match(tagTreeA);

        Assert.assertEquals(result, 0.0);
    }

    @Test
    public void no_match_many_nodes() {
        TagTree tagTreeB = new TagTree.Builder(Arrays.asList("swe_sport_zlatan", "swe_nyheter", "eng_news", "swe_kultur")).build();
        TagTree tagTreeA = new TagTree.Builder("swe_sport_os2016").build();

        double result = tagTreeB.match(tagTreeA);

        Assert.assertEquals(result, 0.0);
    }

    @Test
    public void addTagsToSingleTree() {
        //"swe_sport_os2016"
        TagTree tagTreeA = new TagTree.Builder(RootTag.SWE_SPORT).addTagsToSingleTree("os2016")
                .addTagsToSingleTree(Arrays.asList("a", "b", "c")).build();

        //swe_sport_os2016
        //swe_sport_os2016_a
        //swe_sport_os2016_b
        //swe_sport_os2016_c
        Assert.assertEquals(tagTreeA.getTagDescendants().size(), 4);
    }

    //FIXME @Test(expectedExceptions = TagTreeException.class)
    public void invalidTag_1() {
        TagTree tagTreeA = new TagTree.Builder(",*").build();
        Assert.fail("Invalid tag " + tagTreeA);
    }

    @Test(expectedExceptions = TagTreeException.class)
    public void invalidTag_blank() {
        TagTree tagTreeA = new TagTree.Builder(" ").build();
        Assert.fail("Invalid tag " + tagTreeA);
    }

    @Test(expectedExceptions = TagTreeException.class)
    public void invalidTag_null() {
        TagTree tagTreeA = new TagTree.Builder((String)null).build();
        Assert.fail("Invalid tag " + tagTreeA);
    }

    @Test(expectedExceptions = TagTreeException.class)
    public void addTagsToSingleTree_not_singletree() {
        TagTree tagTreeA = new TagTree.Builder(Arrays.asList("swe_sport_os2016", "swe_kultur_z"))
                .addTagsToSingleTree(Arrays.asList("a", "b", "c")).build();
        Assert.fail("tagTreeA is not a single tree" + tagTreeA);
    }
}
