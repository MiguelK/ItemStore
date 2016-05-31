package com.itemstore.engine.model.tag3;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagDescendant {

    private final String tags;
    private final Map<String, TagNode> tagNodes;
    private  boolean wildCard;

    private TagDescendant(String tags) {
        this.tags = StringUtils.trimToNull(tags);

        tagNodes = new HashMap<>();
        List<String> allTags = parsetags(tags);
        //FIXME stream etc, trim, lowercase

        for (String s : allTags) {
            TagNode tagNode = tagNodes.get(s);
            if(tagNode==null){
                tagNode = new TagNode(s);
                tagNodes.put(s,tagNode);
            }
            tagNode.increaseOccurenc();

            if(wildCard && tagNode.isWildcard()){
                throw new TagTreeException("Multiple wild cards (*) not allowed");
            }

            if(tagNode.isWildcard()){
                wildCard = true;
            }
        }
    }

    String getWithotWildcard(){
        return tags.replace("__*","");//FIXME
    }

    public boolean isWildCard() {
        return wildCard;
    }

    public String getTags() {
        return tags;
    }

    public List<TagNode> getTagNodes(){
        return new ArrayList<>(tagNodes.values());
    }

    private List<String> parsetags(String tags) {

            String[] split = StringUtils.trimToNull(tags).split("_"); //STart *? FIXME
            return Arrays.asList(split);
    }

     static List<TagDescendant> parse(List<String> tags) {
        Map<String,TagDescendant> descendants = new HashMap<>();
        for (String tag : tags) {
            TagDescendant tagDescendant = descendants.get(tag);
            if(tagDescendant==null){
                 tagDescendant = new TagDescendant(tag);
                descendants.put(tag,tagDescendant);
            }
        }
        return new ArrayList<>(descendants.values());
    }
     static TagDescendant parse(String tags) {
        return new TagDescendant(tags);
    }

    public void addTags(List<String> tags) {
        if(wildCard){
            throw  new TagTreeException("Not allowed to add tags to wildcard node");
        }

        String currentTags = getTags();

        for (String tag : tags) {//FIXME support multiple tags swe_news
            TagDescendant tagDescendant = new TagDescendant(currentTags + "_" + tag);
//            ta
        }

    }

    public static class TagNode {
        private  int nodeOccurrence;
        private boolean wildcard;
        private String tag;

        public TagNode(String tag) {
            this.tag = tag;
            wildcard = tag.equals("*");
        }

        private void increaseOccurenc(){
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

        TagDescendant that = (TagDescendant) o;

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
