package com.itemstore.engine.model.tag;

public class NameTag implements Tag {
    private final String name;

    public NameTag(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        throw new IllegalStateException();
    }

    @Override
    public boolean isNameValueTag() {
        return false;
    }
}
