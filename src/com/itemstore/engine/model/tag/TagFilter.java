package com.itemstore.engine.model.tag;

public interface TagFilter {

    //Tagable

    //Implemented by TagTree and TagForest set/get på ItemGroup

    //int match = itemGroup.getTagForest().match(requestTagForest); match=0..1.0 1=max 0 = no match
    double match(TagFilter tagFilter);
}
