package com.itemstore.engine.model.tagtree;

import com.itemstore.engine.model.tag3.TagDescendant;
import com.itemstore.engine.model.tag3.TagTreeException;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class TagPath {

    private final String tags;
    private final Map<String, TagPath.TagNode> tagNodes;
    private boolean wildCard;

    private TagPath(String tags) {
        this.tags = StringUtils.trimToNull(tags);

        tagNodes = new HashMap<>();
        List<String> allTags = parseTags(tags);
        //FIXME stream etc, trim, lowercase

        for (String s : allTags) {
            TagPath.TagNode tagNode = tagNodes.get(s);
            if (tagNode == null) {
                tagNode = new TagPath.TagNode(s);
                tagNodes.put(s, tagNode);
            }
            tagNode.increaseOccurenc();

            if (wildCard && tagNode.isWildcard()) {
                throw new TagTreeException("Multiple wild cards (*) not allowed");
            }

            if (tagNode.isWildcard()) {
                wildCard = true;
            }
        }
    }

    String getWithotWildcard() {

        if(!wildCard){
            return tags;
        }

        int i = tags.lastIndexOf("*");


        String substring = tags.substring(0, i - 1);
        System.out.println(substring);
        return substring;//FIXME swe_sport_*
    }

    public boolean isWildCard() {
        return wildCard;
    }

    public String getTags() {
        return tags;
    }

    public List<TagPath.TagNode> getTagNodes() {
        return new ArrayList<>(tagNodes.values());
    }

    private List<String> parseTags(String tags) {

        String[] split = StringUtils.trimToNull(tags).split("_"); //STart *? FIXME
        return Arrays.asList(split);
    }

    static List<TagPath> parse(List<String> tags) {
        Map<String, TagPath> descendants = new HashMap<>();
        for (String tag : tags) {
            TagPath tagDescendant = descendants.get(tag);
            if (tagDescendant == null) {
                tagDescendant = new TagPath(tag);
                descendants.put(tag, tagDescendant);
            }
        }
        return new ArrayList<>(descendants.values());
    }

    static TagPath parse(String tags) {
        return new TagPath(tags);
    }

    public void addTags(List<String> tags) {
        if (wildCard) {
            throw new TagTreeException("Not allowed to add tags to wildcard node");
        }

        String currentTags = getTags();

        for (String tag : tags) {//FIXME support multiple tags swe_news
            TagPath tagDescendant = new TagPath(currentTags + "_" + tag);
//            ta
        }

    }

    public static class TagNode {
        private int nodeOccurrence;
        private boolean wildcard;
        private String tag;

        public TagNode(String tag) {
            this.tag = tag;
            wildcard = tag.equals("*");
        }

        private void increaseOccurenc() {
            nodeOccurrence = nodeOccurrence + 1;
        }

        public boolean isWildcard() {
            return wildcard;
        }

        public int getNodeOccurrence() {
            return nodeOccurrence;
        }

        public String getTag() {
            return tag;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagPath that = (TagPath) o;

        return tags.equals(that.tags);

    }

    @Override
    public int hashCode() {
        return tags.hashCode();
    }

    @Override
    public String toString() {
        return "TagDescendant{" +
                "tags='" + tags + '\'' +
                ", wildCard=" + wildCard +
                '}';
    }
}
