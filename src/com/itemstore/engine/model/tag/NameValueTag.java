package com.itemstore.engine.model.tag;

public class NameValueTag implements Tag {

    private final String name;

    private final String value;

    public NameValueTag(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean isNameValueTag() {
        return true;
    }

    @Override
    public String toString() {
        return "NameValueTag{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
