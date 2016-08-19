package com.itemstore.engine.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Set;

class SimilarTextContextImpl implements Text {
    private final String originalText;
    private final String text;
    private final Set<String> synonyms;
    private final Set<String> contextWords;

    public SimilarTextContextImpl(String text, Set<String> synonyms, Set<String> contextWords) {
        this.text = StringUtils.trimToEmpty(text).toLowerCase();
        this.originalText = text;
        this.synonyms = synonyms;
        this.contextWords = contextWords;
    }

    @Override
    public boolean isSimilarInContext(String text) {

        System.out.println("My text=" + this.text + " inpu text=" + text);

        double jaroWinklerDistance = StringUtils.getJaroWinklerDistance(this.text, text);
        return jaroWinklerDistance>0.8;
    }
}
