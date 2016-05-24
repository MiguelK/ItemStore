package com.itemstore.engine.model.tag;

import com.itemstore.model.tag.Tag;
import com.itemstore.model.tag.TagContainer;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class TagContainerTest {

    @Test
    public void nameTag() {
        Assert.assertEquals(TagContainer.create("TV_SODER#value").getNameValueTagByName("TV_SODER").getName(), "TV_SODER");
    }

    @Test
    public void nameValueTag() {
        Assert.assertEquals(TagContainer.create("TV1#Soder").getNameValueTagByName("TV1").getValue(), "Soder");
    }

    @Test
    public void containsTagWithName_true() {
        Assert.assertTrue(TagContainer.create(TagContainer.TOP_NEWS_SWE).containsTagWithName(TagContainer.TOP_NEWS_SWE));
        Assert.assertTrue(TagContainer.create("key# value1").containsTagWithName("key"));
        Assert.assertTrue(TagContainer.create("key1# ").containsTagWithName(" key1 "));
        Assert.assertTrue(TagContainer.create("key1# ", "a", "key1").containsTagWithName(" key1 "));
    }

    @Test
    public void getTagValueByName() {
        Assert.assertEquals(TagContainer.create("key#vx").getTagValueByName("key"),"vx");
        Assert.assertEquals(TagContainer.create("key#vx").getTagValueByName("key"), "vx");
        Assert.assertEquals(TagContainer.create("key#vx").getTagValueByName(" key "),"vx");
      //  Assert.assertNull(TagContainer.create("key#vx").getTagValueByName(" key2 "),null);
    }

    @Test
    public void containsTagWithValue_true() {
        Assert.assertTrue(TagContainer.create("key#value1").containsTagWithKeyValue("key","value1" ));
        Assert.assertTrue(TagContainer.create(" key#value1").containsTagWithKeyValue(" key","value1" ));
        Assert.assertTrue(TagContainer.create(" key#value1 ").containsTagWithKeyValue("key"," value1" ));
        Assert.assertTrue(TagContainer.create("key#value1;;key2;;key3#sune").containsTagWithKeyValue("key","value1" ));
    }

    @Test
    public void containsTagWithValue_false() {
        Assert.assertFalse(TagContainer.create("key#value1").containsTagWithKeyValue("","" ));
        Assert.assertFalse(TagContainer.create("key#value1").containsTagWithKeyValue(null,null ));
        Assert.assertFalse(TagContainer.create("key#value1").containsTagWithKeyValue("key","x" ));
        Assert.assertFalse(TagContainer.create("key#value1;;key2;;key3#sune").containsTagWithKeyValue("test","test" ));
    }

    @Test
    public void containsTagWithName_false() {
        Assert.assertFalse(TagContainer.create("key# value1").containsTagWithName("a"));
        Assert.assertFalse(TagContainer.create("key1# ").containsTagWithName(" key2 "));
        Assert.assertFalse(TagContainer.create("key1# ", "a", "key1").containsTagWithName("  "));
        Assert.assertFalse(TagContainer.create("key# value1").containsTagWithName(null));
    }

    @Test
    public void nameValueTags_2() {
        Assert.assertEquals(TagContainer.create("TV1#Soder;;A#B").getTagByNames(" TV1", "A ").size(), 2);
        Assert.assertEquals(TagContainer.create("TV1#Soder;;A#B").getTagByNames("TV1", "A").get(0).getName(), "TV1");
        Assert.assertEquals(TagContainer.create("TV1#Soder;;A#B").getTagByNames("TV1", "A").get(0).getValue(), "Soder");
        Assert.assertEquals(TagContainer.create("TV1#Soder;;A#B").getTagByNames(" TV1", "A ").get(1).getName(), "A");
        Assert.assertEquals(TagContainer.create("TV1#Soder;;A#B").getTagByNames("TV1", "A ").get(1).getValue(), "B");
    }

    @Test
    public void createTags_2() {
        Assert.assertEquals(TagContainer.create(Arrays.asList("plies", "materia�")).getTags().size(), 2);
        Assert.assertEquals(TagContainer.create("plies;;materia�").getTags().size(), 2);
    }

    @Test
    public void getNameValueTagByName_foundName() {
        Assert.assertEquals("plies", TagContainer.create(Arrays.asList("plies#a", "materia#ab"))
                .getNameValueTagByName("plies").getName());

        Assert.assertEquals("a", TagContainer.create(Arrays.asList("a#Value1", "materia#ab"))
                .getNameValueTagByName("a").getName());
    }

    @Test
    public void getNameValueTagByName_NotFound() {
        Assert.assertNull(TagContainer.create("a").getNameValueTagByName("a"));
        Assert.assertNull(TagContainer.create("a#value").getNameValueTagByName("b"));
        Assert.assertNull(TagContainer.create("a#value", "s").getNameValueTagByName("s"));
    }

    @Test
    public void searchTagsByName() {
        TagContainer tc = TagContainer.create(Arrays.asList("plies", "materia�", "apppa"));
        List<Tag> tags = tc.getTagByNames("plies", "apppa");
        Assert.assertEquals(tags.size(), 2);
    }

    @Test
    public void testName() {
        String s = "Vägen";

        String s1 = s.replaceAll("ä", "x");


        Assert.assertEquals(s1, "Vxgen");

    }
}