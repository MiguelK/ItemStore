package com.itemstore.engine.model.tagtree;


import com.itemstore.engine.model.tag3.TagTreeException;

import java.util.Arrays;
import java.util.List;

class ItemTagTree extends TagTreeAbstract {


    public static final String SWE_SPORT = "swe_sport";
    public static final String SWE_NYHETER = "swe_nyheter";

    public static final String ENG_NEWS = "eng_news";

    private static final List<String> ALL_TAG_PREFIX = Arrays.asList(SWE_NYHETER, SWE_SPORT,ENG_NEWS);

    //filterMatch
    double match(SearchFilterTagTree searchFilterTagTree) {

        for (TagPath searchTagPath : searchFilterTagTree.getTagPaths()) {

            //searchFilterTagTree-> swe_sport_*
            for (TagPath itemTagPaths : getTagPaths()) {
                String itemTagPathsTags = itemTagPaths.getTags(); //swe_sport_fotboll_xyz

                if (searchTagPath.isWildCard()) {
                    String withotWildcard = searchTagPath.getWithotWildcard();
                    System.out.println(withotWildcard);
                    if (itemTagPathsTags.contains(withotWildcard)) {
                        return 1.0; //FIXME use TagNodes
                    }
                } else {
                    if (itemTagPathsTags.contains(searchTagPath.getTags())) {
                        return 1.0; //FIXME use TagNodes
                    }
                }

            }
        }
        //FIXME
        return 0;

    }
//Minst 2 levels swe_sport
    //Inga wil cards
    //trim to lowercase
    //  Builder addTagsToSingleTree(List<String> tags) {
    // private static final String NEW_LINE = System.getProperty("line.separator");
    // public static final String TAG_DESCENDANT_SEPARATOR = ",";
    // private final List<TagPath> tagDescendants;

    public ItemTagTree(List<TagPath> tagDescendants) {
        super(tagDescendants);
    }

    public ItemTagTree(String tags) {
        super(tags);
    }

    public static ItemTagTree create(String tagPaths) {
        ItemTagTree itemTagTree = new ItemTagTree(tagPaths);

        itemTagTree.validate();
        return itemTagTree;
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

            String tags = tagPath.getTags();

            boolean validTagPrefix = false;
            for (String tagPrefix : ALL_TAG_PREFIX) {
                if(tags.startsWith(tagPrefix)){
                    validTagPrefix = true;
                    break;
                }
            }

            if(!validTagPrefix){
                throw  new TagTreeException("Invalid root and/or first level tag " + tags);
            }


        }

    }

}
