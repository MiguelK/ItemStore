package com.itemstore.engine.model.tag3;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemTagTree {

    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final String TAG_DESCENDANT_SEPARATOR = ",";
    private final List<TagDescendant> tagDescendants;

    private ItemTagTree(List<TagDescendant> tagDescendants) {
        this.tagDescendants = tagDescendants;
    }

    public List<TagDescendant> getTagDescendants() {
        return tagDescendants;
    }

    //Should this be included depending on input tree
    public double match(ItemTagTree.Filter searchFilterTagTree) { //passTargetTagTree

        for (TagDescendant searchTagDescendant : searchFilterTagTree.getTagDescendants()) {

            if (searchTagDescendant.isWildCard()) {
                for (TagDescendant tagDescendant : tagDescendants) {
                    String tags = tagDescendant.getTags();
                    if (tags.startsWith(searchTagDescendant.getWithotWildcard())) {
                        return 1.0;//FIXME
                    }
                }
            } else {
                String withotWildcard = searchTagDescendant.getWithotWildcard();

                for (TagDescendant tagDescendant : tagDescendants) {
                    if (tagDescendant.getTags().contains(withotWildcard)) {
                        return 1.0; //FIXME use TagNodes
                    }
                }
            }
        }
        //FIXME
        return 0;
    }

    public static class Builder {
        private List<TagDescendant> tagDescendants = new ArrayList<>();

        private TagDescendant rootTag;

        public Builder(RootTag rootTags) {
            this(rootTags.getTags());
        }

        public Builder(String rootTags) {
            String s = StringUtils.trimToNull(rootTags);
            if (s == null) {
                throw new TagTreeException("Invalid itemTagTree " + rootTags);
            }

             RootTag.validate(s);

            rootTag = TagDescendant.parse(s);
            tagDescendants.add(rootTag);
        }

        public ItemTagTree build() {
            for (TagDescendant tagDescendant : tagDescendants) {
                if (tagDescendant.isWildCard()) {
                    throw new TagTreeException("Wildcard not allowed");
                }
            }


            return new ItemTagTree(tagDescendants);
        }

        //aik,fotbol_aik,zlatan
        public Builder addTagsToSingleTree(String tagNodes) {

            String tagNodesTrimmed = StringUtils.trimToNull(tagNodes);

            if (tagNodesTrimmed == null) {
                return this;
            }

            List<TagDescendant> list = parseTagDescendants(tagNodes);
            for (TagDescendant tagDescendant : list) {
                TagDescendant t = TagDescendant.parse(rootTag.getTags() + "_" + tagDescendant.getTags());//FIXME

                tagDescendants.add(t);
            }

            return this;
        }
    }

    public static class Filter {
        private List<TagDescendant> tagDescendants;


        public Filter(List<TagDescendant> tagDescendants) {
            this.tagDescendants = tagDescendants;
        }

        public List<TagDescendant> getTagDescendants() {
            return tagDescendants;
        }

        public static Filter parse(String t) {
            List<TagDescendant> tagDescendants = parseTagDescendants(t);

            return new Filter(tagDescendants);
        }

        @Override
        public String toString() {
            return "Filter{" +
                    "tagDescendants=" + tagDescendants +
                    '}';
        }
    }

    private static List<TagDescendant> parseTagDescendants(String t) {
        String s = StringUtils.trimToNull(t);
        if (s == null) {
            throw new TagTreeException("Invalid itemTagTree " + t);
        }

        List<String> tags = Arrays.asList(s.split(TAG_DESCENDANT_SEPARATOR));

        List<TagDescendant> tagDescendants = new ArrayList<>();
        if (tags.isEmpty()) {
            TagDescendant parse = TagDescendant.parse(s);
            tagDescendants.add(parse);
        } else {
            List<TagDescendant> parse = TagDescendant.parse(tags);
            tagDescendants.addAll(parse);
        }
        return tagDescendants;
    }

    @Override
    public String toString() {

        String toString = "ItemTagTree {" + NEW_LINE;
        for (TagDescendant tagDescendant : tagDescendants) {
            toString += tagDescendant + NEW_LINE;
        }
        toString = toString + "}";


        return toString;
    }
}
