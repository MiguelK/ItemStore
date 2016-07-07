package com.itemstore.engine.model.tag3;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TagTreeFilterTest {

    @Test(dataProvider = "tagDataSucess")
    public void search_success(TagData tagData) {
        ItemTagTree itemTagTreeA = new ItemTagTree.Builder(tagData.getItemRootTag())
                .addTagsToSingleTree(tagData.getItemTags()).build();
        Assert.assertEquals(itemTagTreeA.match(TagTreeFilter.parse(tagData.getFilterTags())), tagData.getResult(), tagData.toString());
    }

    @DataProvider(name = "tagDataSucess")
    public Object[][] tagDataSucess() {
        return new Object[][]{
                {new TagData("swe_sport", "", "eng_nes", 0.0)},
                {new TagData(TagRoot.SWE_DATA.getTags(), "ios,andraoid,apple_macOS", "swe_sport", 0.0)},
                {new TagData("swe_sport", "", "eng_nes", 0.0)},
                {new TagData("swe_sport", "", "eng_nes", 0.0)},
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
            return "SearchData{" +
                    "itemRootTag='" + itemRootTag + '\'' +
                    ", itemTags='" + itemTags + '\'' +
                    ", filterTags='" + filterTags + '\'' +
                    ", result=" + result +
                    '}';
        }
    }

}