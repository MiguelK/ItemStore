package com.itemstore.engine.model.tag3;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemTagTree {

    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final String TAG_DESCENDANT_SEPARATOR = ",";
    private final List<TagTreePath> tagTreePaths;

    private ItemTagTree(List<TagTreePath> tagTreePaths) {
        this.tagTreePaths = tagTreePaths;
    }

    public List<TagTreePath> getTagTreePaths() {
        return tagTreePaths;
    }

    //Should this be included depending on input tree
    public double match(TagTreeFilter searchTagTreeFilterTagTree) { //passTargetTagTree

        for (TagTreePath searchTagTreePath : searchTagTreeFilterTagTree.getTagTreePaths()) {

            if (searchTagTreePath.isMultiWildcard()) {
                for (TagTreePath tagTreePath : tagTreePaths) {
                    String tags = tagTreePath.getTagTreePath();
                    if (tags.contains(searchTagTreePath.getWithotWildcard())) {
                        return 1.0;//FIXME
                    }
                }
            }else if (searchTagTreePath.isSuffixWildCard()) {
                for (TagTreePath tagTreePath : tagTreePaths) {
                    String tags = tagTreePath.getTagTreePath();
                    if (tags.startsWith(searchTagTreePath.getWithotWildcard())) {
                        return 1.0;//FIXME
                    }
                }
            }else  if (searchTagTreePath.isPrefixWildCard()) {
                for (TagTreePath tagTreePath : tagTreePaths) {
                    String tags = tagTreePath.getTagTreePath();
                    if (tags.endsWith(searchTagTreePath.getWithotWildcard())) {
                        return 1.0;//FIXME
                    }
                }
            } else {
                String withotWildcard = searchTagTreePath.getWithotWildcard();

                for (TagTreePath tagTreePath : tagTreePaths) {
                    if (tagTreePath.getTagTreePath().contains(withotWildcard)) {
                        return 1.0; //FIXME use TagNodes
                    }
                }
            }
        }
        //FIXME
        return 0;
    }

    public static class Builder {
        private List<TagTreePath> tagTreePaths = new ArrayList<>();

        private TagTreePath rootTag;

        public Builder(RootTag rootTags) {
            this(rootTags.getTags());
        }

        public Builder(String rootTags) {
            String s = StringUtils.trimToNull(rootTags);
            if (s == null) {
                throw new TagTreeException("Invalid itemTagTree " + rootTags);
            }

             RootTag.validate(s);

            rootTag = TagTreePath.parseTagTreePath(s);
            tagTreePaths.add(rootTag);
        }

        public ItemTagTree build() {
            for (TagTreePath tagTreePath : tagTreePaths) {
                if (tagTreePath.isSuffixWildCard()) {
                    throw new TagTreeException("Wildcard not allowed");
                }
            }


            return new ItemTagTree(tagTreePaths);
        }

        //aik,fotbol_aik,zlatan
        public Builder addTagsToSingleTree(String tagNodes) {

            String tagNodesTrimmed = StringUtils.trimToNull(tagNodes);

            if (tagNodesTrimmed == null) {
                return this;
            }

            List<TagTreePath> list = parseTagDescendants(tagNodes);
            for (TagTreePath tagTreePath : list) {
                TagTreePath t = TagTreePath.parseTagTreePath(rootTag.getTagTreePath() + "_" + tagTreePath.getTagTreePath());//FIXME

                tagTreePaths.add(t);
            }

            return this;
        }
    }

    private static List<TagTreePath> parseTagDescendants(String t) {
        String s = StringUtils.trimToNull(t);
        if (s == null) {
            throw new TagTreeException("Invalid itemTagTree " + t);
        }

        List<String> tags = Arrays.asList(s.split(TAG_DESCENDANT_SEPARATOR));

        List<TagTreePath> tagTreePaths = new ArrayList<>();
        if (tags.isEmpty()) {
            TagTreePath parse = TagTreePath.parseTagTreePath(s);
            tagTreePaths.add(parse);
        } else {
            List<TagTreePath> parse = TagTreePath.parseTagTreePath(tags);
            tagTreePaths.addAll(parse);
        }
        return tagTreePaths;
    }

    @Override
    public String toString() {

        String toString = "ItemTagTree {" + NEW_LINE;
        for (TagTreePath tagTreePath : tagTreePaths) {
            toString += tagTreePath + NEW_LINE;
        }
        toString = toString + "}";


        return toString;
    }
}