package com.itemstore.engine.model.tag;

import com.itemstore.engine.model.tag.detect.TagDetector;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class APITest {

    @Test
    public void createItemWithTags() {
        //Used in ChannelParser
      TagDetector tagDetector =  TagDetector.create("swe_sport", Arrays.asList("fotboll","zlatan"));
      //tagDetector.loadSynonyms(....

        //Om multipla ord skapa child
      List<TagDescendant> paths=  tagDetector.detects("en lång zlatan [fotboll] Lagerbäck Stockholm...");

        TagTree tagTree = new TagTree.Builder().add(paths).build();

        //item.setTagTree(tagTree)
    }


    @Test
    public void compareTagForest() {
        TagDetector tagDetector = TagDetector.create("swe_teknik", Arrays.asList("Java","c++"));
        //tagDetector.addSynonims(List<List<String> zlatan=Ibra=
        List<TagDescendant> paths=  tagDetector.detects("en lång text..zlatan sommar Zlatan fotboll.");
        TagTree tagTree = new TagTree.Builder().add(paths).build();


        TagDetector tagDetector2 = TagDetector.create("swe_teknik", Arrays.asList("Java","c++"));
        List<TagDescendant> paths2=  tagDetector.detects("en lång text..zlatan sommar Zlatan fotboll.");
        TagTree tagTree2 = new TagTree.Builder().add(paths).build();

        double match = tagTree.match(tagTree2); //1.0


    }
}
