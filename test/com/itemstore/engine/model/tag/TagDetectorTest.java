package com.itemstore.engine.model.tag;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class TagDetectorTest {

    @Test
    public void tagDetector() {

        String descr = "Landets största idrottsevenemang för kvinnor. 10 km på en vacker bana på Södra Djurgården med Zlatan";
        TagDetector tagDetector = new TagDetector(TagStore.getInstnce());

        List<Tag> detect = tagDetector.detect(descr);
        //add to TagTree
        Assert.assertTrue(detect.size()==1);
    }

    @Test
    public void synonym() {
        //
        String descr = "Landets största idrottsevenemang för kvinnor. 10 km på en vacker bana på Södra Djurgården med Ibrahimovich";
        TagDetector tagDetector = new TagDetector(TagStore.getInstnce());

        Tag tag = tagDetector.detect(descr).get(0);
        Tag zlatan = TagStore.getInstnce().getTag("Zlatan").get();
        //add to TagTree
        Assert.assertEquals(tag,zlatan);

    }
}