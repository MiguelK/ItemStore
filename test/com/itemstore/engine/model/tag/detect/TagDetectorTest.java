package com.itemstore.engine.model.tag.detect;

import com.itemstore.engine.model.tag.TagDescendant;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TagDetectorTest {

    @Test
    public void detect_2_tags() {
        TagDetector tagDetector = TagDetector.create("swe_sport",Arrays.asList("Zlatan","Fotboll"));

        List<TagDescendant> detects = tagDetector.detects("Sommar i Fotboll Stockholm zlatan Zlatan");

        Assert.assertTrue(detects.size()==2);
    }

    @Test
    public void nodeOccurrence_2() {
        TagDetector tagDetector = TagDetector.create("swe_sport", Collections.singletonList("Zlatan"));

       List<TagDescendant> detects = tagDetector.detects("Sommar i Fotboll Stockholm zlatan Zlatan");

       Assert.assertEquals(detects.get(0).getTagNodeByName("zlatan").getNodeOccurrence(),2);
    }

}