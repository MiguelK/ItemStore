package com.itemstore.engine.model.tag;

import com.itemstore.engine.model.tag.detect.TagDetector;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class TagTreeTest {

    @Test(expectedExceptions = TagException.class)
    public void missing_root_tag()  {
        TagTree tagTree = new TagTree.Builder().build();
        Assert.fail("Should not be possible to create tagtree without root tag " + tagTree);
    }

    @Test(expectedExceptions = TagException.class)
    public void missing_child_tag() throws Exception {
        TagTree tagTree = new TagTree.Builder().addTagDescendants(TagDescendant.create("swe")).build();
        Assert.fail("Should not be possible to create tagtree without at least one child tag " + tagTree);
    }

    @Test(expectedExceptions = TagException.class)
    public void does_not_start_with_same_root_tag() throws Exception {
        TagDescendant tagDescendant1 = TagDescendant.create("swe_sport_zlatan");
        TagDescendant tagDescendant2 = TagDescendant.create("eng_sport_zlatan");

        TagTree tagTree = new TagTree.Builder().addTagDescendants(tagDescendant1,tagDescendant2).build();
        Assert.fail("tagTree=" + tagTree);
    }


    @Test(expectedExceptions = TagException.class)
    public void does_not_start_with_same_second_tag() throws Exception {
        TagDescendant tagDescendant1 = TagDescendant.create("swe_sport_zlatan");
        TagDescendant tagDescendant2 = TagDescendant.create("swe_nyheter_zlatan");

        TagTree tagTree = new TagTree.Builder().addTagDescendants(tagDescendant1,tagDescendant2).build();
        Assert.fail("tagTree=" + tagTree);
    }

    @Test
    public void create_with_2_tagDescendant() throws Exception {
        TagDescendant tagDescendant1 = TagDescendant.create("swe_sport_zlatan");
        TagDescendant tagDescendant2 = TagDescendant.create("swe_sport_fotboll");

        TagTree tagTree = new TagTree.Builder().addTagDescendants(tagDescendant1,tagDescendant2).build();
        Assert.assertEquals(tagTree.getTagDescendants().size(), 2);
    }

    @Test
    public void createBigTree() {
        List<TagDescendant> descendants = TagDetector.create("swe_kultur", Arrays.asList("Konst", "Picasso",
                "Teater", "Dramaten", "Oscars", "Vernisage"))
                .detects("Picasso konst i Picasso Picasso sommar teater Vernisage Oscars och Teater dramaten");
        //swe_kultur_konst
        //swe_kultur_picasso (3)
        //swe_kultur_teater
        TagTree tagTree = new TagTree.Builder().add(descendants).build();
        Assert.assertEquals(tagTree.getTagDescendants().size(),3);

//        tagTree.g //FIXME tagTree.getTagNodeByName("Picasso") assert?


    }

    @Test
    public void match_tagTree_equal_1_0() throws Exception {
        TagTree tagTreeA = new TagTree.Builder().addTagDescendants(TagDescendant.create("swe_sport_zlatan")).build();
        TagTree tagTreeB = new TagTree.Builder().addTagDescendants(TagDescendant.create("swe_sport_zlatan")).build();

        Assert.assertTrue(tagTreeA.match(tagTreeB)==1.0);
    }

    @Test
    public void match_tagTree_equal_trim_ignorecase_1_0() throws Exception {
        TagTree tagTreeA = new TagTree.Builder().addTagDescendants(TagDescendant.create("Swe_sport_AIK ")).build();
        TagTree tagTreeB = new TagTree.Builder().addTagDescendants(TagDescendant.create(" swe_SPORT_AIK")).build();

        Assert.assertTrue(tagTreeA.match(tagTreeB)==1.0);
    }
}
