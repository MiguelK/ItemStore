package com.itemstore.engine.model.tagtree;

import com.itemstore.engine.model.tag3.TagTreeException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class TagTreeAbstract implements TagTree {


    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final String TAG_DESCENDANT_SEPARATOR = ",";
    private List<TagPath> tagPaths = new ArrayList<>();

    TagTreeAbstract(List<TagPath> tagPaths) {
        this.tagPaths = tagPaths;
    }

    TagTreeAbstract(String unparsedTagPaths) {

        String s = StringUtils.trimToNull(unparsedTagPaths);
        if (s == null) {
            throw new TagTreeException("Invalid tags " + unparsedTagPaths);
        }

        List<String> tags = Arrays.asList(s.split(TAG_DESCENDANT_SEPARATOR));

        if (tags.isEmpty()) {
            TagPath parse = TagPath.parse(s);
            tagPaths.add(parse);
        } else {
            List<TagPath> parse = TagPath.parse(tags);
            tagPaths.addAll(parse);
        }
    }

    public List<TagPath> getTagPaths() {
        return tagPaths;
    }



   /* public static class Builder {
        List<TagPath> tagPaths = new ArrayList<>();

        List<String> tagsToAddToSingleTree = new ArrayList<>();

        public Builder(String rootTags) {

            String s = StringUtils.trimToNull(rootTags);
            if (s == null) {
                throw new TagTreeException("Invalid tags " + rootTags);
            }

            List<String> tags = Arrays.asList(s.split(TAG_DESCENDANT_SEPARATOR));

            if (tags.isEmpty()) {
                TagPath parse = TagPath.parse(s);
                tagPaths.add(parse);
            } else {
                List<TagPath> parse = TagPath.parse(tags);
                tagPaths.addAll(parse);
            }
        }

        public Builder(List<String> rootTags) {
            List<TagPath> parse = TagPath.parse(rootTags);
            tagPaths.addAll(parse);

        }

        //public  TagTree build() ;
        public TagTree build() {
            if (tagsToAddToSingleTree.size() > 0 && tagPaths.size() != 1) {
                throw new TagTreeException("Only ok to add tags to single tree");
            }

            TagPath firstTagDescendant = tagPaths.get(0);
            for (String tag : tagsToAddToSingleTree) {
                TagPath tagDescendant = TagPath.parse(firstTagDescendant.getTags() + "_" + tag);//FIXME
                tagPaths.add(tagDescendant);
            }

            return new TagTree(tagPaths);
        }*/

       /* protected TagTree createTagTree(List<TagPath> tagPaths) {
            return null;
        }*/

        /*TagTree.Builder addTagsToSingleTree(List<String> tags) {
            tagsToAddToSingleTree.addAll(tags);
            return this;
        }*/

        /*public TagTree.Builder addTagsToSingleTree(String tag) {

            String tagTrimmed = StringUtils.trimToNull(tag);

            if (tagTrimmed == null) {
                return this;
            }

            List<String> tags = Arrays.asList(tagTrimmed.split(TAG_DESCENDANT_SEPARATOR));
            tagsToAddToSingleTree.addAll(tags);

            return this;
        }*/
    //}


}
