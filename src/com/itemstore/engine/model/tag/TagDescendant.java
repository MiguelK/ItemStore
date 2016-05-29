package com.itemstore.engine.model.tag;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagDescendant {

    private final Map<String, TagNode> tagNodes = new HashMap<String, TagNode>();

    public TagDescendant(String tags) {

        String[] split = tags.split("_");

        for (String s : split) {
            String trim = s.trim();

            TagNode tagNode = tagNodes.get(trim);
            if(tagNode==null){
              tagNode = new TagNode(trim);
            }
            tagNode.nodeOccurrence++;//FIXME

            tagNodes.put(trim,tagNode);
        }
    }

    public TagNode getTagNodeByName(String name) {
        return null;
    }

    public List<TagNode> getTagNodes() {
        return new ArrayList<TagNode>(tagNodes.values());
    }

    public static TagDescendant create(String tags) {
        String s = StringUtils.trimToNull(tags);
        if (s == null) {
            throw new TagException();
        }
        return new TagDescendant(s);
    }

    public static class TagNode {
        private  int nodeOccurrence;
        private String tag;

        private TagNode(String tag) {
            this.tag = tag;
        }

        public TagNode(int nodeOccurrence) {
            this.nodeOccurrence = nodeOccurrence;
        }

        public int getNodeOccurrence() {
            return nodeOccurrence;
        }
    }
}
