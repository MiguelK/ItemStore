package com.flow.engine.model.tag;

import com.flow.engine.collector.TagCollector;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TagCollectorTest {

    private TagCollector tagCollector;

    @BeforeMethod
    public void setUp() throws Exception {
        tagCollector = TagCollector.create();
    }


    @Test
    public void containsTagName_true() {
        TagContainer tagContainer = TagContainer.create("apa#value1", "apa2#value2");
        Assert.assertTrue(tagContainer.containsTagWithName("apa"));
    }

    @Test
    public void containsTagName_false() {
        TagContainer tagContainer = TagContainer.create("apa#value1", "apa2#value2");
        Assert.assertFalse(tagContainer.containsTagWithName("zune"));
    }

    @Test
    public void getNameValueTagByName() {
        TagContainer tagContainer = TagContainer.create("apa#value1", "apa2#value2");

        NameValueTag tag = tagContainer.getNameValueTagByName("apa");
        Assert.assertEquals(tag.getValue(), "value1");
    }

    @Test
    public void ignoreInvalidTags() {
        tagCollector.add(" ");
        Assert.assertTrue(tagCollector.getTags().isEmpty());
    }

    @Test
    public void addDupliactedTags() {
        tagCollector.add("Swe");
        tagCollector.add(" Swe ");
        tagCollector.add(" swe ");
        tagCollector.add(" apa ");
        tagCollector.add(" APA ");

        Assert.assertEquals(tagCollector.getTags().size(), 2);
    }


    @Test
    public void tagSize() {
        tagCollector.add("Swe");
        tagCollector.add(" Stoch ");
        tagCollector.add(" Zlatt ");
        tagCollector.add(" Jaga av vargar ");
        tagCollector.add(" Bara barn ");
        tagCollector.add(" bara barn ");

        Assert.assertEquals(tagCollector.getTags().size(), 5);
    }

    @Test
    public void doNotModifyTags() {
        tagCollector.add("Swe");
        tagCollector.add(" Stoch ");
        tagCollector.add(" Jaga av vargar ");

        Assert.assertTrue(tagCollector.getTags().contains("Swe"));
        Assert.assertTrue(tagCollector.getTags().contains("Stoch"));
        Assert.assertTrue(tagCollector.getTags().contains("Jaga av vargar"));
    }

    @Test
    public void test() {
        tagCollector.add(" java ");
        tagCollector.add(" progrrammerings ");
        List<String> tags = tagCollector.findTags("Testar Java som progrrammerings test");
        Assert.assertEquals(tags.size(), 2);
    }

    @Test
    public void testName() {

        TagCollector tagCollector = TagCollector.create("http://jsoup.org/");
        tagCollector.addTagsToDetect("Open source", "html5");
        tagCollector.collect();


        List<String> tags = tagCollector.getLocatedTags();   //2 "Open source","html5"
        List<String> t2 = tagCollector.getTagCandidates();    //??

        System.out.println("Located ... " + tags);


    }
}