package com.itemstore.engine.model.tag;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TagForestTest {

    @Test
    public void match_1_0_tagtree_and_tagforest() {
        TagTree tagTree = new TagTree.Builder().addTagDescendants(TagDescendant.create("swe_fiske")).build();
        TagForest tagForest = new TagForest.Builder().addTree(tagTree).build();

        Assert.assertTrue(tagForest.match(tagForest)==1.0);
    }

    @Test
    public void asd() {
        TagTree tagTreeA = new TagTree.Builder().addTagDescendants(TagDescendant.create("swe_fiske")).build();
        TagForest tagForest = new TagForest.Builder().addTree(tagTreeA).build();

        Assert.assertTrue(tagForest.match(tagForest)==1.0);
    }
}