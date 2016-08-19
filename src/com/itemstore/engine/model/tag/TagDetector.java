package com.itemstore.engine.model.tag;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagDetector {

    private final TagStore tagStore;

    public TagDetector(TagStore tagStore) {
        this.tagStore = tagStore;
    }

    List<Tag> detect(String text){


        String toLowerCase = text.toLowerCase();

        List<Tag> collect = Arrays.stream(toLowerCase.split(" "))
                .filter(s -> tagStore.getTag(s).isPresent()).map(m -> tagStore.getTag(m).get()).collect(Collectors.toList());

        return collect;
    }
}
