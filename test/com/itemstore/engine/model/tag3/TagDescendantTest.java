package com.itemstore.engine.model.tag3;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class TagDescendantTest {

    @Test
    public void parse_tag_nodes_trim_2() {
        TagDescendant tagDescendant = TagDescendant.parse(" swe_news ");
        Assert.assertEquals(tagDescendant.getTagNodes().size(),2);
        Assert.assertEquals(tagDescendant.getTagNodes().get(0).getTag(),"swe");
        Assert.assertEquals(tagDescendant.getTagNodes().get(1).getTag(),"news");
    }

    @Test
    public void parse_tagnodes_2() {
        TagDescendant tagDescendant = TagDescendant.parse("swe_news");
        Assert.assertEquals(tagDescendant.getTagNodes().size(),2);
    }

    @Test
    public void testEqual() {
        TagDescendant a = TagDescendant.parse("swe_apa");
        TagDescendant b = TagDescendant.parse("swe_apa");

        Assert.assertTrue(a.equals(b));
    }

    @Test
    public void parse_ignore_duplicates_2() {
        List<TagDescendant> tagDescendants = TagDescendant.parse(Arrays.asList("swe_news", "swe_news"));
        Assert.assertEquals(tagDescendants.size(),1);
    }

    @Test
    public void parse_3() {
        List<TagDescendant> tagDescendants = TagDescendant.parse(Arrays.asList("swe_news", "swe_news_zlatan"));
        Assert.assertEquals(tagDescendants.size(),2);
    }

    @Test
    public void parse_wildcard_1() {
        TagDescendant tagDescendant = TagDescendant.parse("swe_*");
        Assert.assertTrue(tagDescendant.getTagNodes().get(1).isWildcard());
    }

    @Test(expectedExceptions = TagTreeException.class)
    public void invalid_tags_to_many_wildcards() {
       TagDescendant.parse("swe_*_sport_*");
    }

    @Test(expectedExceptions = TagTreeException.class)
    public void addTags_to_wildcard_node_not_allowed() {
        TagDescendant.parse("swe_*").addTags(Arrays.asList("a","b"));
    }


}