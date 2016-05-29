package com.itemstore.engine.model.tag;

import com.itemstore.engine.model.tag.detect.TagDetector;
import org.testng.annotations.Test;
import sun.java2d.pipe.AATileGenerator;

import java.util.Arrays;
import java.util.List;

public class TagFilterTest {

    @Test
    public void same_root_and_second() {
        //FIXME kanske ska detecktera flera taggar, typ alla men bara markera Konst,Picasso?
        List<TagDescendant> descendants = TagDetector.create("swe_kultur", Arrays.asList("Konst", "Picasso", "Teater"))
                .detects("Picasso konst i sommar teater och Picasso Picasso ");
        //swe_kultur_konst
        //swe_kultur_picasso (3)
        //swe_kultur_teater
        TagTree tagTreeA = new TagTree.Builder().add(descendants).build();

        TagTree tagTreeB = new TagTree.Builder().addTagDescendants(TagDescendant.create("swe_kultur_picasso")).build();

        double match = tagTreeA.match(tagTreeB);

        System.out.println("Match=" + match); //FIXME % av antal node träffar? om picasso>1 öka värde?


    }
}
