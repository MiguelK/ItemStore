package com.itemstore.engine.model.tag3;

import java.util.List;

public class TagTreeFilter {

    private final List<TagTreePath> tagTreePaths;

    private TagTreeFilter(List<TagTreePath> tagTreePaths) {
        this.tagTreePaths = tagTreePaths;
    }

    public List<TagTreePath> getTagTreePaths() {
        return tagTreePaths;
    }

    public static TagTreeFilter parse(String t) {
        List<TagTreePath> tagTreePaths = TagTreePath.parseTagTreePaths(t);

        return new TagTreeFilter(tagTreePaths);
    }

    @Override
    public String toString() {
        return "TagTreeFilter{" +
                "tagTreePaths=" + tagTreePaths +
                '}';
    }
}
