package com.itemstore.engine.model.tag;

public class TagForest implements TagMatcher {
    @Override
    public double match(TagMatcher tagMatcher) {
        return 0;
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
