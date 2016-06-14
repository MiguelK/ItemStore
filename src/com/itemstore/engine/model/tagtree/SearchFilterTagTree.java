package com.itemstore.engine.model.tagtree;


import com.itemstore.engine.model.tag3.TagTreeException;

import java.util.List;

//SearchFilterTagTree
public class SearchFilterTagTree extends TagTreeAbstract {


    //wildcard ok sww_*
    //trim to lowercase

    public SearchFilterTagTree(List<TagPath> tagPaths) {
        super(tagPaths);
    }

    public SearchFilterTagTree(String tags) {
        super(tags);
    }

    public static SearchFilterTagTree create(String tagPaths) {
        SearchFilterTagTree tagTree = new SearchFilterTagTree(tagPaths);

        //tagTree.validate();


        return tagTree;
    }

    private void validate() {
        //Valdate 2 tag levels
        for (TagPath tagPath : getTagPaths()) {
            if (tagPath.isWildCard()) {
                throw new TagTreeException("No wildcard tags allowed in ItemTagTree");
            }

            if (tagPath.getTagNodes().size() < 2) {
                throw new TagTreeException("At least one root and a second node is required " + tagPath);
            }

        }

    }
}
