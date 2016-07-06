package com.itemstore.engine.model.tag3;

import com.itemstore.engine.detector.TagDetectorTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.testng.Assert.*;

public class TagTreeFilterTest {
    @Test
    public void prefix_sufix_wildcard() {
        ItemTagTree itemTagTreeA = new ItemTagTree.Builder("swe_sport")
                .addTagsToSingleTree("allsvenskan_zlatan_os2020, fotboll_zlatan, aik").build();

        Assert.assertEquals(itemTagTreeA.match(TagTreeFilter.parse("*zlatan*")), 1.0);
        Assert.assertEquals(itemTagTreeA.match(TagTreeFilter.parse(" *zlatan* ")), 1.0);
        Assert.assertEquals(itemTagTreeA.match(TagTreeFilter.parse(" *Zlatan* ")), 1.0);
        Assert.assertEquals(itemTagTreeA.match(TagTreeFilter.parse(" *aik* ")), 1.0);
    }

    @Test
    public void prefix_sufix_wildcard_no_hit() {
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

    @Test(dataProvider = "tagDataSucess")
    public void search_success(TagData tagData) {
        ItemTagTree itemTagTreeA = new ItemTagTree.Builder(tagData.getItemRootTag())
                .addTagsToSingleTree(tagData.getItemTags()).build();
        Assert.assertEquals(itemTagTreeA.match(TagTreeFilter.parse(tagData.getFilterTags())), tagData.getResult(), tagData.toString());
    }

    @DataProvider(name = "tagDataSucess")
    public Object[][] tagDataSucess() {
        return new Object[][]{
                {new TagData("swe_sport", "", "swe_sport", 1.0)},
                {new TagData(" swe_sport ", "", "swe_sport", 1.0)},
                {new TagData(" swe_sport ", "", " swe_sport", 1.0)},
                {new TagData(" swe_sport ", "", " swe_sport* ", 1.0)},
                {new TagData("swe_sport", "zlatan", "swe_sport_zlatan", 1.0)},
                {new TagData("swe_sport", "zlatan", "swe_sport_zlatan*", 1.0)},
                {new TagData("swe_sport", "zlatan", "*swe_sport_zlatan*", 1.0)},
                {new TagData("swe_nyheter", "sthockholm_os2020,Sofo,aik,riksdagen_lars", "*swe*", 1.0)},
                {new TagData("swe_nyheter", "sthockholm_os2020,Sofo,aik,riksdagen_lars", "*Sofo*", 1.0)},
                {new TagData("swe_nyheter", "sthockholm_os2020,Sofo,aik,riksdagen_lars", "*AIK*", 1.0)},
                {new TagData("swe_sport", "zlatan", "*Zlatan*", 1.0)}};
    }

    private static class TagData {
        String itemRootTag;
        String itemTags;
        String filterTags;
        double result;

        public TagData(String itemRootTag, String itemTags, String filterTags, double result) {
            this.itemRootTag = itemRootTag;
            this.itemTags = itemTags;
            this.filterTags = filterTags;
            this.result = result;
        }

        public String getItemRootTag() {
            return itemRootTag;
        }

        public String getItemTags() {
            return itemTags;
        }

        public String getFilterTags() {
            return filterTags;
        }

        public double getResult() {
            return result;
        }

        @Override
        public String toString() {
            return "TagData{" +
                    "itemRootTag='" + itemRootTag + '\'' +
                    ", itemTags='" + itemTags + '\'' +
                    ", filterTags='" + filterTags + '\'' +
                    ", result=" + result +
                    '}';
        }
    }

}