package com.itemstore.engine.model.tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TagStoreHardcoded implements TagStore {

    private final Map<String,Tag> tags = new HashMap<>();

    TagStoreHardcoded(){

        Tag tag = Tag.create("Zlatan").synonymer("Ibra","Ibrahimovich");
        Tag categoryTag = Tag.create("Södermalm").addChild("Hornstull","Skanstull");
        //friends category

        List<Tag> all = new ArrayList<>();


        all.addAll(tag.getTags());
        all.addAll(categoryTag.getTags());


        for (Tag tag1 : all) {
            tags.put(tag1.getNameKey(), tag1);

        }

        System.out.println(tags);


    }

    @Override
    public Optional<Tag> getTag(String tagName) {

        String s = tagName.toLowerCase();

        Tag tag = tags.get(s);

        if(tag!=null && tag.isSynonymTag()){
            return Optional.ofNullable(tag.getParenTag());
        }

        return Optional.ofNullable(tag);
    }
}
