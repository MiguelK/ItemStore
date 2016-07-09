package com.itemstore.engine.model.tag3;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

//-> TagTreePath swe_sport_fotboll
public class TagTreePath {

    private static final String TAG_TREE_PATH_SEPARATOR = ",";
    private static final String TAG_SPACE_SEPARATOR = "+"; //FIXME
    private static final String TAG_SEPARATOR = "_";
    private static final char WILDCARD_CHARACTER = '*';
    private static final int MAX_WILD_CARDS_ALLOWED = 2;

    private final String tagTreePath;
    private boolean suffixWildCard;
    private boolean prefixWildCard;

    private TagTreePath(String tagTreePath) {
        this.tagTreePath = StringUtils.trimToEmpty(tagTreePath);

        if (this.tagTreePath.isEmpty()) {
            throw new TagTreeException("Invalid tagTreePath");
        }

        if (this.tagTreePath.chars().filter(c -> c == WILDCARD_CHARACTER).count() > MAX_WILD_CARDS_ALLOWED) {
            throw new TagTreeException("To many wildcards(*) in tagPath " + tagTreePath);
        }

        prefixWildCard = this.tagTreePath.startsWith("*");
        suffixWildCard = this.tagTreePath.endsWith("*");

        List<String> tags = Arrays.asList(this.tagTreePath.split(TAG_SEPARATOR));
        //FIXME stream,

        for (int i = 0; i < tags.size(); i++) {
            if (i != 0 && i != tags.size() - 1) {
                if (tags.get(i).contains("*")) {
                    throw new TagTreeException("WildCard is only allowed at begining or end " + tagTreePath);
                }
            }
        }
    }

    String getWithotWildcard() {
        return tagTreePath.replace("_*", "").replace("*","");//FIXME
    }

    boolean isSuffixWildCard() {
        return suffixWildCard;
    }

    boolean isPrefixWildCard() {
        return prefixWildCard;
    }

    boolean isMultiWildcard(){
        return suffixWildCard && prefixWildCard;
    }

    public String getTagTreePath() {
        return tagTreePath;
    }


    static List<TagTreePath> parseTagTreePath(List<String> tagPaths) {
        Map<String, TagTreePath> descendantsUnique = new HashMap<>();
        List<TagTreePath> tagTreePaths = new ArrayList<>();

        for (String tagPath : tagPaths) {
            TagTreePath tagTreePath = descendantsUnique.get(tagPath);
            if (tagTreePath == null) {
                tagTreePath = new TagTreePath(tagPath);
                descendantsUnique.put(tagPath, tagTreePath);
                tagTreePaths.add(tagTreePath);
            }
        }
        return tagTreePaths;
    }

    static TagTreePath parseTagTreePath(String tags) {
        return new TagTreePath(tags);
    }

    static List<TagTreePath> parseTagTreePaths(String tagPaths) {
        String s = StringUtils.trimToNull(tagPaths);
        if (s == null) {
            throw new TagTreeException("Invalid tagPaths " + tagPaths);
        }

        List<String> tags = Arrays.asList(s.split(TAG_TREE_PATH_SEPARATOR));

        List<TagTreePath> tagTreePaths = new ArrayList<>();
        if (tags.isEmpty()) {
            TagTreePath parse = TagTreePath.parseTagTreePath(s);
            tagTreePaths.add(parse);
        } else {
            List<TagTreePath> parse = TagTreePath.parseTagTreePath(tags);
            tagTreePaths.addAll(parse);
        }
        return tagTreePaths;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagTreePath that = (TagTreePath) o;

        return tagTreePath.equals(that.tagTreePath);

    }

    @Override
    public int hashCode() {
        return tagTreePath.hashCode();
    }

    @Override
    public String toString() {
        return "TagTreePath{" +
                "itemTagTree='" + tagTreePath + '\'' +
                ", suffixWildCard=" + suffixWildCard +
                '}';
    }
}
