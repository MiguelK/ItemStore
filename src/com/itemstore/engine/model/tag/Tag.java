package com.itemstore.engine.model.tag;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Tag {

    private String name;
    private String nameKey; //lower case + trimmed
    private boolean synonymTag;
    private Tag parenTag;

    //CateGoryTag SynonymTag

    List<Tag> synonymer = new ArrayList<>(); //No tags
    List<Tag> children = new ArrayList<>();

    public Tag(String name) {
        this.name = name;
        this.nameKey = StringUtils.trim(name).toLowerCase();
    }

    public Tag(String name,boolean synonymTag,Tag parenTag) {
        this.name = name;
        this.parenTag= parenTag;
        this.synonymTag = synonymTag;
        this.nameKey = StringUtils.trim(name).toLowerCase();
    }

    public boolean isSynonymTag() {
        return synonymTag;
    }

    public String getName() {
        return name;
    }

    public String getNameKey() {
        return nameKey;
    }

    public static Tag create(String name) {
        return new Tag(name);
    }

    public Tag getParenTag() {
        return parenTag;
    }

    public Tag synonymer(String syn, String... syns) {

        synonymer.add(new Tag(syn,true,this));

        for (String s : syns) {
            synonymer.add(new Tag(s,true,this));
        }

        return this;
    }

    public Tag addChild(String child, String... chid) {
        children.add(Tag.create(child));

        for (String s : chid) {
            children.add(Tag.create(s));
        }
        return this;
    }

    List<Tag> getTags(){

        List<Tag> all = new ArrayList<>();
        all.add(this);

        for (Tag tag : synonymer) {
            all.addAll(tag.getTags());
        }
        for (Tag child : children) {
            all.addAll(child.getTags());
        }


        return all;
    }
}
