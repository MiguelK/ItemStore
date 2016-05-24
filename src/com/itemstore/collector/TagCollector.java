package com.itemstore.collector;


import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;

public class TagCollector {

    private final Map<String, String> tagsToDetect = new HashMap<String, String>();
    private String url;
    private final Set<String> locatedTags = new HashSet<String>();

    private final Set<String> tags = new HashSet<String>();
    private final Set<String> tagsLoweCase = new HashSet<String>();

    public List<String> getTags() {
        return new ArrayList<String>(tags);
    }

    private TagCollector(String url) {
        this.url = url;
    }

    private TagCollector() {
    }

    public static TagCollector create(String url) {
        return new TagCollector(url);
    }

    public void addTagsToDetect(String... tags) {
        for (String tag : tags) {
            tagsToDetect.put(tag.toLowerCase(), tag);
        }
    }

    public void add(String tag) {
        String trimmedTag = StringUtils.trimToEmpty(tag);
        if (StringUtils.isEmpty(trimmedTag)) {
            return;
        }
        String lowerCase = trimmedTag.toLowerCase();

        boolean didNotExists = tagsLoweCase.add(lowerCase);
        if (didNotExists) {
            tags.add(trimmedTag);
        }
    }

    public void add(List<String> tags) {
        for (String tag : tags) {
            add(tag);
        }
    }

    public List<String> findTags(String text) {

        String trimmed = StringUtils.trimToNull(text);
        if (trimmed == null) {
            return Collections.emptyList();
        }
        String[] strings = StringUtils.splitPreserveAllTokens(text);
        final Set<String> allInText = new HashSet<String>(new ArrayList<String>(Arrays.asList(strings)));//FIXME

        //FIXME
        List<String> result = new ArrayList<String>();
        for (String s : allInText) {
            String x = StringUtils.trimToEmpty(s).toLowerCase();
            if (tagsLoweCase.contains(x)) {
                result.add(s);
            }
        }

        return result;
    }

    public void collect() {

        try {
            Document doc = Jsoup.connect(url).get();
            String text = doc.text().toLowerCase();

            for (Map.Entry<String, String> entry : tagsToDetect.entrySet()) {
                if (text.contains(entry.getKey())) {
                    locatedTags.add(entry.getValue());
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<String> getLocatedTags() {
        return new ArrayList<String>(locatedTags);
    }

    public List<String> getTagCandidates() {
        return null;
    }

    public static TagCollector create() {
        return new TagCollector();
    }
}
