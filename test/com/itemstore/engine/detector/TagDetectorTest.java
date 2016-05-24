package com.itemstore.engine.detector;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TagDetectorTest {

    private TagDetector tagDetector;

    @BeforeMethod
    public void setUp() {
        tagDetector = TagDetector.newDetector();
    }

    @Test
    public void usage()  {
        TagDetector tagDetector = TagDetector.newDetector();
        tagDetector.addDetectableTags(Arrays.asList("Piteå","Kriminalitet,Våld","Våld"));
        List<String> tags = tagDetector.detectTags("Some text about våld and some about");
        Assert.assertEquals(tags.size(),2);
        Assert.assertTrue(tags.contains("Våld"));
        Assert.assertTrue(tags.contains("Kriminalitet"));

    }

    @Test(dataProvider = "tagDetectorResult")
    public void empTest(TagDetectorResult tagDetectorResult) {
        tagDetector.addDetectableTags(tagDetectorResult.detectableTags);
        List<String> detectTags = tagDetector.detectTags(tagDetectorResult.text);
        Assert.assertEquals(detectTags.size(),tagDetectorResult.expected.size(),"Size= " + detectTags.size() + " expected=" +tagDetectorResult.expected);

        for (String tag : tagDetectorResult.expected) {
            Assert.assertTrue(detectTags.contains(tag), detectTags + " Does not contain " + tag);
        }
    }

    @DataProvider(name = "tagDetectorResult")
    public Object[][] getTagDetectorResult() {
        return new Object[][]{{new TagDetectorResult("Some apa sasa", Collections.singletonList("Apa"), Arrays.asList("Joe", "Apa"))},
                {new TagDetectorResult("Some stockholm text STHML ", Collections.singletonList("Stockholm"), Arrays.asList("Joe", " Stockholm "))},
                {new TagDetectorResult("Some stockholm text STHML ", Collections.singletonList("Stockholm"), Arrays.asList("Joe", " Stockholm,STHML,Eken "))}};
    }

    private static class TagDetectorResult {
        List<String> expected;
        String text;
        List<String> detectableTags;

        public TagDetectorResult(String text, List<String> expected, List<String> detectableTags) {
            this.expected = expected;
            this.detectableTags = detectableTags;
            this.text = text;
        }
    }

}