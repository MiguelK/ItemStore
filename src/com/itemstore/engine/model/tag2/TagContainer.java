package com.itemstore.engine.model.tag2;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.*;

public class TagContainer implements Serializable {

    private static final String LANGUAGE_ANY = "Any-language";//Needed?? FIXME
    public static final String LANGUAGE_SWE = "Swe";
    public static final String LANGUAGE_ENG = "Eng";

    public static final String TOP_NEWS_SWE = "TopNews_sweden";
    public static final String TV_USER_OLD_STOCKHOLM = "TV_USER_OLD_STOCKHOLM";

    public static final String EXCLUDE_ALL = "EXCLUDE_ALL";


    private static final List<String> LANGUAGE_TAGS = ListUtils.unmodifiableList(Arrays.asList(LANGUAGE_SWE, LANGUAGE_ENG));

    private static final Map<String, List<String>> FIRST_LEVEL_CATEGORY_TAG_BY_LANGUAGE;

    //Swe, "Teknik" [Apple, Nokia]
    private static final Map<String, Map<String, List<String>>> LANGUAGE_CATEGORY;

    //Swedish hardcoded tags
    private static final String SWE_CATEGORY1_NYHETER = "Nyheter";
    private static final String SWE_CATEGORY1_SPORT = "Sport";
    private static final String SWE_CATEGORY1_TEKNIK = "Teknik";
    private static final String SWE_CATEGORY1_NOJE = "Nöje";
    private static final String SWE_CATEGORY1_MUSIK = "Musik";
    private static final String SWE_CATEGORY1_FOTO = "Foto";
    private static final String SWE_CATEGORY1_VETENSKAP = "Vetenskap";
    private static final String SWE_CATEGORY1_MODE = "Mode";
    private static final String SWE_CATEGORY1_HUMOR = "Humor";
    private static final String SWE_CATEGORY1_FILM = "Film";
    private static final String SWE_CATEGORY1_BLOGGAR = "Bloggar";

    //Category2
    private static final String SWE_CATEGORY2_SAMHALLE = "Samhälle";
    private static final String SWE_CATEGORY2_EKONOMI = "Ekonomi";


    public static final String ENG_CATEGORY1_NEWS = "News";
    public static final String ENG_CATEGORY1_SPORT = "Sport";
    public static final String ENG_CATEGORY1_TECHNOLOGY = "Tech";

    //Category2 tags
    private static final String CATEGORY2_APPLE = "Apple";
    private static final String CATEGORY2_MICROSOFT = "Microsoft";

    static {
        TagNode swe = new TagNode(LANGUAGE_SWE);
        TagNode sweNyheter = TagNode.create(SWE_CATEGORY1_NYHETER);
        sweNyheter.add(TagLeaf.create(SWE_CATEGORY2_SAMHALLE));
        sweNyheter.add(TagLeaf.create(SWE_CATEGORY2_EKONOMI));

        swe.add(sweNyheter);
        swe.add(TagLeaf.create(SWE_CATEGORY1_SPORT));
        swe.add(TagLeaf.create(SWE_CATEGORY1_NOJE));
        swe.add(TagLeaf.create(SWE_CATEGORY1_MUSIK));
        swe.add(TagLeaf.create(SWE_CATEGORY1_FOTO));
        swe.add(TagLeaf.create(SWE_CATEGORY1_VETENSKAP));
        swe.add(TagLeaf.create(SWE_CATEGORY1_MODE));
        swe.add(TagLeaf.create(SWE_CATEGORY1_HUMOR));
        swe.add(TagLeaf.create(SWE_CATEGORY1_FILM));
        swe.add(TagLeaf.create(SWE_CATEGORY1_BLOGGAR));
        swe.add(TagNode.create(SWE_CATEGORY1_TEKNIK).add(TagLeaf.create(CATEGORY2_APPLE)).add(TagLeaf.create(CATEGORY2_MICROSOFT)));

        List<String> firstLevel = swe.getFirstLevelTags();

        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        map.put(LANGUAGE_SWE, firstLevel);

        FIRST_LEVEL_CATEGORY_TAG_BY_LANGUAGE = Collections.unmodifiableMap(map);

        HashMap<String, Map<String, List<String>>> hashMap = new HashMap<String, Map<String, List<String>>>();
        hashMap.put(swe.getTag(), swe.getTagsByCategory());

        LANGUAGE_CATEGORY = Collections.unmodifiableMap(hashMap);
    }

    public static List<String> getLanguageTags() {
        return LANGUAGE_TAGS;
    }

    /**
     * USed by client to get values for current user setting language.
     *
     * @return
     */
    public static Map<String, List<String>> getFirstLevelTagsByLanguageKey() {
        return FIRST_LEVEL_CATEGORY_TAG_BY_LANGUAGE;
    }

    /**
     * //"Swe","Sport" ["Sport","News"]
     * //"Eng","News" ["USA","Worldwide"]
     *
     * @return
     */
    public static Map<String, Map<String, List<String>>> getSecondLevelTagsForLanguageCategory() {
        return LANGUAGE_CATEGORY;
    }

//    public static final Tag TV_SODER = Tag.createTag("TV_SODER");
//    public static final Tag TV_SODER_HEADER = Tag.createTag("TV_SODER_HEADER#Part2");

    private static final String NAME_VALUE_DELIMITTER = "#";
    private static final String TAG_DELIMITTER = ";;";

    private List<Tag> tags = new ArrayList<Tag>();

    private final List<String> rawTags;

    private TagContainer(List<String> tags) {

        this.rawTags = tags;

        List<Tag> parsedTags = new ArrayList<Tag>();
        for (String tag : tags) {
            String[] split = tag.split(TAG_DELIMITTER);
            for (String s : split) {

                String[] nameValue = s.split(NAME_VALUE_DELIMITTER);
                if (nameValue.length == 2) {
                    parsedTags.add(new NameValueTag(nameValue[0], nameValue[1]));
                } else {
                    parsedTags.add(new NameTag(nameValue[0]));
                }
            }
        }

        this.tags = parsedTags;
    }

    public static TagContainer create(String... tags) {
        return new TagContainer(Arrays.asList(tags));
    }

    public static TagContainer create(List<String> tags) {
        return new TagContainer(tags);
    }

    public static TagContainer create(String tags) {
        return new TagContainer(Collections.singletonList(StringUtils.trim(tags)));
    }

    public List<String> getRawTags() {
        return rawTags;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public NameValueTag getNameValueTagByName(String tagName) {

        final String trimmedTagName = StringUtils.trimToEmpty(tagName);

        Collection<Tag> result = CollectionUtils.select(tags, new Predicate<Tag>() {
            @Override
            public boolean evaluate(Tag tag) {

                return tag.isNameValueTag() && tag.getName().equals(trimmedTagName);


            }
        });
        if (result.size() != 1) {
            return null; //throw new IllegalArgumentException("More then 1 tag2 with name " + tagName);
        }

        return (NameValueTag) result.iterator().next();
    }

    public List<Tag> getTagByNames(String... tagNames) {
        final List<String> tagNamesToSearch = new ArrayList<String>();
        for (String tagName : tagNames) {
            tagNamesToSearch.add(tagName.trim());
        }

        Collection<Tag> result = CollectionUtils.select(tags, new Predicate<Tag>() {
            @Override
            public boolean evaluate(Tag object) {
                return tagNamesToSearch.contains(object.getName());
            }
        });

        return new ArrayList<Tag>(result);
    }

    public String getTagValueByName(String tagName) {
        return getNameValueTagByName(tagName).getValue();
    }

    public boolean containsTagWithName(String tagName) {

        String trimmedName = StringUtils.trimToEmpty(tagName);
        for (Tag tag : tags) {
            if (tag.getName().equals(trimmedName)) {
                return true;
            }
        }

        return false;
    }

    public boolean containsTagWithKeyValue(String key, String value) {
        String trimmedKey = StringUtils.trimToEmpty(key);
        String trimmedValue = StringUtils.trimToEmpty(value);

        for (Tag tag : tags) {
            if (tag.isNameValueTag() && tag.getName().equals(trimmedKey)
                    && tag.getValue().equals(trimmedValue)) {
                return true;
            }
        }

        return false;
    }

    private interface Node {
        String getTag();

        List<String> getTags();
    }

    private static class TagLeaf implements Node {

        private final String tag;

        @Override
        public List<String> getTags() {
            return Collections.singletonList(tag);
        }

        private TagLeaf(String tag) {
            this.tag = tag;
        }

        public static TagLeaf create(String tag) {
            return new TagLeaf(tag);
        }

        @Override
        public String getTag() {
            return tag;
        }
    }

    private static class TagNode implements Node {

        private final List<Node> children = new ArrayList<Node>();

        private final String tag;

        public TagNode(String tag) {
            this.tag = tag;
        }

        private static TagNode create(String tag) {
            return new TagNode(tag);
        }

        public TagNode add(Node node) {
            children.add(node);
            return this;
        }

        @Override
        public List<String> getTags() {
            List<String> tags = new ArrayList<String>();
            for (Node child : children) {
                tags.addAll(child.getTags());

            }

            return tags;
        }

        @Override
        public String getTag() {
            return tag;
        }

        public List<String> getFirstLevelTags() {

            List<String> tags = new ArrayList<String>();

            for (Node child : children) {
                tags.add(child.getTag());
            }


            return tags;
        }

        public Map<String, List<String>> getTagsByCategory() {
            Map<String, List<String>> values = new HashMap<String, List<String>>();

            for (Node child : children) {
                values.put(child.getTag(), child.getTags());
            }

            return values;
        }
    }
}
