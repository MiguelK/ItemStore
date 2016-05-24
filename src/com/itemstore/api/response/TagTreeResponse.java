package com.itemstore.api.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagTreeResponse implements Serializable {

    Map<String, List<String>> firstLevelCategoryByLangage = new HashMap<String, List<String>>();

    //Swe, "Teknik" [Apple, Nokia]
    Map<String, Map<String, List<String>>> categoriesByLangauge = new HashMap<String, Map<String, List<String>>>();

    List<String> languageTags = new ArrayList<String>();

    public static TagTreeResponse create(List<String> languageTags,
                                         Map<String, Map<String, List<String>>> secondLevelTagsForLanguageCategory,
                                         Map<String, List<String>> firstLevelTagsByLanguageKey) {

        TagTreeResponse tagTreeResponse = new TagTreeResponse();
        tagTreeResponse.languageTags = languageTags;
        tagTreeResponse.categoriesByLangauge = secondLevelTagsForLanguageCategory;
        tagTreeResponse.firstLevelCategoryByLangage = firstLevelTagsByLanguageKey;

        return tagTreeResponse;

    }

    public Map<String, List<String>> getFirstLevelCategoryByLangage() {
        return firstLevelCategoryByLangage;
    }

    public Map<String, Map<String, List<String>>> getCategoriesByLangauge() {
        return categoriesByLangauge;
    }

    public List<String> getLanguageTags() {
        return languageTags;
    }
}
