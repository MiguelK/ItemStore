package com.itemstore.engine.model.tag;

import java.util.List;

public class TagDescendant {

    public TagNode getTagNodeByName(String name){
        return null;
    }

    public List<TagNode> getTagNodes(){
        return null;
    }

    public static TagDescendant create(String tags) {
        return null;
    }

    public static class TagNode {
        private final int nodeOccurrence;

        public TagNode(int nodeOccurrence) {
            this.nodeOccurrence = nodeOccurrence;
        }

        public int getNodeOccurrence() {
            return nodeOccurrence;
        }
    }
}
