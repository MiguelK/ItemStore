package com.itemstore.engine.model.tag;

public class TagForest implements TagFilter {
    @Override
    public double match(TagFilter tagFilter) {
        return 0;
    }

    public static TagForest create(String tagForest) {
        return null;
    }


    public static class Builder {
        public Builder addTree(TagTree tagTree) {
            return this;
        }

        public TagForest build() {
            return null;
        }
    }


/*
    TagForest tf = TagForest.decode(int[] decodedTree);
    tf.match(itemGroup.getTagMatcher()

            if(serverTree.match(excludeA) > 0){
        skip this item not interseted*/
}
