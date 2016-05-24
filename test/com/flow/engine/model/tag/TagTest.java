package com.flow.engine.model.tag;

import junit.framework.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class TagTest {

    @Test
    public void getRootLanguageTags() {
        List<String> languageTags = TagContainer.getLanguageTags(); //hardcode, swe, rus, any-lang

        Assert.assertTrue(languageTags.contains(TagContainer.LANGUAGE_SWE));
        Assert.assertTrue(languageTags.contains(TagContainer.LANGUAGE_ENG));
        Assert.assertEquals(2, languageTags.size());
    }

    @Test
    public void getFirstLevelTagsForLanguage_Swe() {
        List<String> tagsForLanguage = TagContainer.getFirstLevelTagsByLanguageKey().get("Swe");//hardcoded, Musik,Nyheter,Mode
        Assert.assertEquals("Nyheter", tagsForLanguage.get(0));
        Assert.assertEquals("Sport", tagsForLanguage.get(1));
    }

    @Test
    public void getSecondLevelTagsForLanguage_Swe() {
        Map<String, Map<String, List<String>>> languageCategory = TagContainer.getSecondLevelTagsForLanguageCategory();
        Map<String, List<String>> swe = languageCategory.get("Swe");
        Assert.assertTrue(swe.get("Teknik").contains("Apple"));
    }

    /*@Test
    public void validateTags_invalid() {
        Assert.assertFalse(Tag.isInvalidValidTags(Collections.<String>emptyList()));
        Assert.assertFalse(Tag.isInvalidValidTags(Arrays.asList("as", "")));
        Assert.assertFalse(Tag.isInvalidValidTags(Arrays.asList("", "")));
        Assert.assertFalse(Tag.isInvalidValidTags(Arrays.asList(null, "", "sdsd")));
        Assert.assertFalse(Tag.isInvalidValidTags(Arrays.asList(Tag.LANGUAGE_SWE, Tag.ENG_CATEGORY1_NEWS)));
        Assert.assertFalse(Tag.isInvalidValidTags(Arrays.asList(Tag.SWE_CATEGORY1_NYHETER)));
        Assert.assertFalse(Tag.isInvalidValidTags(Arrays.asList(Tag.SWE_CATEGORY1_NYHETER, Tag.ENG_CATEGORY1_TECHNOLOGY)));
        Assert.assertTrue(Tag.isInvalidValidTags(Arrays.asList(Tag.LANGUAGE_ANY, Tag.CATEGORY2_APPLE)));

    }

    @Test
    public void validateTags_valid() {
        Assert.assertTrue(Tag.isInvalidValidTags(Arrays.asList(Tag.LANGUAGE_SWE)));
        Assert.assertTrue(Tag.isInvalidValidTags(Arrays.asList(Tag.LANGUAGE_ENG, Tag.ENG_CATEGORY1_NEWS)));
        Assert.assertTrue(Tag.isInvalidValidTags(Arrays.asList(Tag.LANGUAGE_SWE, Tag.ENG_CATEGORY1_NEWS, Tag.SWE_CATEGORY1_SPORT)));
        Assert.assertTrue(Tag.isInvalidValidTags(Arrays.asList(Tag.LANGUAGE_ANY, Tag.CATEGORY2_APPLE, Tag.ENG_CATEGORY1_TECHNOLOGY)));
    }*/
}