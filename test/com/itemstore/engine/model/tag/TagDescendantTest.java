package com.itemstore.engine.model.tag;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TagDescendantTest {

    @Test(expectedExceptions = TagException.class)
    public void createWithNull() {
        TagDescendant.create(null);
    }

    @Test(expectedExceptions = TagException.class)
    public void createWithEmpty() {
        TagDescendant.create(" ");
    }

    @Test
    public void createOneNode_trim() {
      Assert.assertEquals(TagDescendant.create(" swe ").getTagNodes().get(0).getNodeOccurrence(), 1);
    }

    @Test
    public void createOneNode_depth_6() {
        Assert.assertEquals(TagDescendant.create("swe_nyheter_stockhom_kista_kistagalleria_rån").getTagNodes().size(), 6);
    }

    /*@Test
    public void createOneNode_6_tags() {
        Assert.assertEquals(TagDescendant.create("swe_nyheter_stockhom_kista_kistagalleria_rån").getTagNodes().get(0).getNodeOccurrence(), 1);
    }*/ //FIXME needed?

    @Test
    public void createOneNode_multipletOccurrence() {
        Assert.assertEquals(TagDescendant.create("swe_swe").getTagNodes().size(), 1);
        Assert.assertEquals(TagDescendant.create("swe_swe").getTagNodes().get(0).getNodeOccurrence(), 2);
    }
}