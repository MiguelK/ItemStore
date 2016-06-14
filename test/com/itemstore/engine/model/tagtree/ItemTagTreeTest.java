package com.itemstore.engine.model.tagtree;

import com.itemstore.engine.model.Item;
import com.itemstore.engine.model.tag3.TagTreeException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

public class ItemTagTreeTest {

    @Test
    public void parse_multi_tag_2() {
        TagTree tagTree = ItemTagTree.create("swe_sport_os2016,swe_sport_zlatan");

        Assert.assertEquals(tagTree.getTagPaths().size(), 2);
    }

    @Test
    public void parse_multi_tag_trim() {
        TagTree tagTree = ItemTagTree.create(" eng_news,swe_sport_os2016 ,swe_sport_zlatan ");
        Assert.assertEquals(tagTree.getTagPaths().size(), 3);
    }

    @Test(expectedExceptions = TagTreeException.class)
    public void parse_multi_invalid_wildcard() {
        TagTree tagTree = ItemTagTree.create(" eng_*,swe_sport_os2016 ,swe_sport_zlatan ");
        Assert.assertEquals(tagTree.getTagPaths().size(), 3);
    }

    //TEST felaktig aik,aik_fotboll
   /* @Test
    public void tagDescendants_wth_added_tags()  {

        TagTree tagTreeB = ItemTagTree.create("swe_sport_fotboll")
                .addTagsToSingleTree(Arrays.asList("zlatan", "OS-2016","fotboll_sverige_landslaget")).build();

        Assert.assertEquals(tagTreeB.getTagPaths().size(), 4);

        Assert.assertEquals(tagTreeB.getTagPaths().get(0).getTags(),"swe_sport_fotboll");
        Assert.assertEquals(tagTreeB.getTagPaths().get(1).getTags(),"swe_sport_fotboll_zlatan");
        Assert.assertEquals(tagTreeB.getTagPaths().get(2).getTags(),"swe_sport_fotboll_OS-2016");
        Assert.assertEquals(tagTreeB.getTagPaths().get(3).getTags(),"swe_sport_fotboll_fotboll_sverige_landslaget");
    }*/

    @Test(expectedExceptions = TagTreeException.class)
    public void toStringSingleTree() {
        ItemTagTree.create("swe_sport_fotboll,zlatan,OS-2016");
    }

    @Test
    public void equal_full_match() {
        ItemTagTree itemTagTree = ItemTagTree.create("swe_sport");

        double result = itemTagTree.match(SearchFilterTagTree.create("swe_sport_*"));

        Assert.assertEquals(result, 1.0);
    }

    @Test
    public void star_full_match() {
        SearchFilterTagTree tagTreeA = SearchFilterTagTree.create("swe_*");
        ItemTagTree tagTreeB = ItemTagTree.create(("swe_sport_zlatan,swe_nyheter"));

        double result = tagTreeB.match(tagTreeA);

        Assert.assertEquals(result, 1.0);
    }

    @Test
    public void star_trim_full_match() {
        ItemTagTree tagTreeB = ItemTagTree.create(" swe_sport_zlatan,swe_nyheter ");
        SearchFilterTagTree tagTreeA = SearchFilterTagTree.create(" swe_* ");

        double result = tagTreeB.match(tagTreeA);

        Assert.assertEquals(result, 1.0);
    }

    @Test
    public void no_match_many_nodes() {
        ItemTagTree itemTagTree = ItemTagTree.create("swe_sport_zlatan,swe_nyheter,eng_news,swe_kultur");

        double result = itemTagTree.match(SearchFilterTagTree.create("swe_sport_os2016"));

        Assert.assertEquals(result, 0.0);
    }


    @Test(expectedExceptions = TagTreeException.class)
    public void invalidTag_1() {
        TagTree itemTagTree = ItemTagTree.create(",*");
        Assert.fail("Invalid tag " + itemTagTree);
    }

    @Test(expectedExceptions = TagTreeException.class)
    public void invalid_tag_missing_second_level() {
        TagTree tagTreeA = ItemTagTree.create("swe_");
        Assert.fail("Invalid tag " + tagTreeA);
    }

      /*

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
    }*/

}