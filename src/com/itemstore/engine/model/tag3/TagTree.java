package com.itemstore.engine.model.tag3;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.xml.internal.ws.encoding.ContentTypeImpl;

import java.util.ArrayList;
import java.util.List;

public class TagTree {
    private static final String NEW_LINE = System.getProperty("line.separator");
    private final List<TagDescendant> tagDescendants;

    public TagTree(List<TagDescendant> tagDescendants) {
        this.tagDescendants = tagDescendants;
    }

    public List<TagDescendant> getTagDescendants() {
        return tagDescendants;
    }

    public double match(TagTree tagTree) { //passTargetTagTree

        for (TagDescendant tagDescendant : tagTree.getTagDescendants()) {

            String tags = tagDescendant.getTags(); //swe_sport_fotboll_xyz
            for (TagDescendant tagDescendant1 : tagDescendants) {
                String tags1 = tagDescendant1.getTags(); //swe_sport_fotboll_xyz

                if (tagDescendant1.isWildCard()) {
                    String withotWildcard = tagDescendant1.getWithotWildcard();

                    if (tags1.startsWith(withotWildcard)) {
                        return 1.0;//FIXME
                    }
                }

                if (tags1.contains(tags)) {
                    return 1.0; //FIXME use TagNodes
                }
            }
        }
        //FIXME
        return 0;
    }

    public static class Builder {
        private List<TagDescendant> tagDescendants = new ArrayList<>();

        private List<String> tagsToAddToSingleTree = new ArrayList<>();

        public Builder(String rootTags) {
            TagDescendant parse = TagDescendant.parse(rootTags);
            tagDescendants.add(parse);
        }

        public Builder(List<String> rootTags) {
            List<TagDescendant> parse = TagDescendant.parse(rootTags);
            tagDescendants.addAll(parse);

        }

        public TagTree build() {
            if (tagsToAddToSingleTree.size() > 0 && tagDescendants.size() != 1) {
                throw new TagTreeException("Only ok to add tags to single tree");
            }

            TagDescendant firstTagDescendant = tagDescendants.get(0);
            for (String tag : tagsToAddToSingleTree) {
                TagDescendant tagDescendant = TagDescendant.parse(firstTagDescendant.getTags() + "_" + tag);//FIXME
                tagDescendants.add(tagDescendant);
            }

            return new TagTree(tagDescendants);
        }

        public Builder addTagsToSingleTree(List<String> tags) {

            tagsToAddToSingleTree.addAll(tags);
            return this;
        }
    }

    @Override
    public String toString() {

        String toString = "TagTree {" + NEW_LINE;
        for (TagDescendant tagDescendant : tagDescendants) {
            toString += tagDescendant + NEW_LINE;
        }
        toString = toString + "}";


        return toString;
    }
}