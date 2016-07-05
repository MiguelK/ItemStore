package com.itemstore.engine.model.tag3;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class TagTreePathTest {
    //@Test
    public void decode_space() {
        TagTreePath parse = TagTreePath.parseTagTreePath(" Lars+Larsson ");
        Assert.assertEquals(parse.getTagTreePath(), "Lars Larsson");
    }

    //@Test
    public void encode_space() {
        TagTreePath parse = TagTreePath.parseTagTreePath(" Lars Larsson ");
        Assert.assertEquals(parse.getTagTreePath(), "Lars+Larsson");
    }

    @Test(expectedExceptions = TagTreeException.class)
    public void invalid_path_empty() {
        TagTreePath.parseTagTreePath("");
    }

    @Test(expectedExceptions = TagTreeException.class)
    public void to_many_wildcards() {
        TagTreePath.parseTagTreePath("*swe*_sport*");
    }

    //    @Test(expectedExceptions = TagTreeException.class)
    public void invalid_char_path_separator() {
        TagTreePath.parseTagTreePath(",");
    }

    @Test
    public void parse_trim_3() {
        Assert.assertEquals(TagTreePath.parseTagTreePath(" swe_kultur_os2020 ").getTagTreePath(), "swe_kultur_os2020");
    }

    @Test
    public void prefix_suffix_wildcard() {
        Assert.assertEquals(TagTreePath.parseTagTreePath(" *swe_sport* ").getWithotWildcard(), "swe_sport");
        Assert.assertTrue(TagTreePath.parseTagTreePath(" *swe_sport* ").isSuffixWildCard());
        Assert.assertTrue(TagTreePath.parseTagTreePath(" *swe_sport* ").isPrefixWildCard());
    }

    @Test
    public void getWithoutWildcard()  {
        Assert.assertEquals(TagTreePath.parseTagTreePath(" *swe_sport* ").getWithotWildcard(), "swe_sport");
        Assert.assertEquals(TagTreePath.parseTagTreePath(" swe_sport* ").getWithotWildcard(), "swe_sport");
        Assert.assertEquals(TagTreePath.parseTagTreePath(" swe_sport_zlatan* ").getWithotWildcard(), "swe_sport_zlatan");
    }

    @Test
    public void do_no_convert_lower_case() {
        Assert.assertEquals(TagTreePath.parseTagTreePath(" SWE_kultur_os2020 ").getTagTreePath(), "SWE_kultur_os2020");
    }

    @Test(expectedExceptions = TagTreeException.class)
    public void invalid_path_null() throws Exception {
        TagTreePath.parseTagTreePath((String) null);
    }

    @Test
    public void testEqual() {
        TagTreePath a = TagTreePath.parseTagTreePath("swe_apa");
        TagTreePath b = TagTreePath.parseTagTreePath("swe_apa");

        Assert.assertTrue(a.equals(b));
    }

    @Test
    public void parse_ignore_duplicates_2() {
        List<TagTreePath> tagTreePaths = TagTreePath.parseTagTreePath(Arrays.asList("swe_news", "swe_news"));
        Assert.assertEquals(tagTreePaths.size(), 1);
    }

    @Test
    public void parse_3() {
        List<TagTreePath> tagTreePaths = TagTreePath.parseTagTreePath(Arrays.asList("swe_news", "swe_news_zlatan"));
        Assert.assertEquals(tagTreePaths.size(), 2);
    }

    @Test
    public void parse_wildcard() {
        TagTreePath tagTreePath = TagTreePath.parseTagTreePath("swe_*");
        Assert.assertTrue(tagTreePath.isSuffixWildCard());
    }

    @Test(expectedExceptions = TagTreeException.class)
    public void invalid_tags_to_many_wildcards() {
        TagTreePath.parseTagTreePath("swe_*_sport_*_*");
    }

    @Test(expectedExceptions = TagTreeException.class)
    public void invalid_tags_wildcards_in_wrong_place() {
        TagTreePath.parseTagTreePath("swe_*_sport_*");
    }


}