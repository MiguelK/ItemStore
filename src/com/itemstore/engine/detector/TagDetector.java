package com.itemstore.engine.detector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class TagDetector {

    private final List<String> detectableTags;
    private final Map<String, List<String>> detectableSynonymsTags;

    private TagDetector() {
        detectableTags = new ArrayList<String>();
        detectableSynonymsTags = new HashMap<String, List<String>>();
    }

    public static TagDetector newDetector() {
        return new TagDetector();
    }


    public List<String> detectTags(String text) {
        Scanner scanner = new Scanner(text);
        //FIXME perf
        Set<String> detectedTags = new HashSet<String>();

        while (scanner.hasNext()) {
            String tagToTest = scanner.next().trim();

            boolean handled = false;
            Set<String> syno = getSyno(tagToTest);
            Set<String> tags = getTags(tagToTest);

            detectedTags.addAll(syno);
            detectedTags.addAll(tags);
        }
        return new ArrayList<String>(detectedTags);
    }

    private Set<String> getTags(String tagToTest) {
        Set<String> detectedTags = new HashSet<String>();
        for (String tag : detectableTags) {
            if (tag.trim().equalsIgnoreCase(tagToTest)) {
                detectedTags.add(tag.trim());
            }
        }
        return detectedTags;
    }

    private Set<String> getSyno(String tagToTest) {
        Set<String> detectedTags = new HashSet<String>();
        for (Map.Entry<String, List<String>> entry : detectableSynonymsTags.entrySet()) {
            for (String tagSyn : entry.getValue()) {
                if (tagToTest.equalsIgnoreCase(tagSyn)) {
                    detectedTags.add(entry.getKey().trim());
//                    handled = true;
                }
            }
        }
        return detectedTags;
    }


    public void addDetectableTags(List<String> tags) {

        for (String tag : tags) {
            String[] split = tag.split(",");
            if (split.length > 1) {
                String masterTag = split[0];
                detectableTags.add(masterTag);

                for (String s : split) {
                    System.out.println(s);
                    List<String> strings = detectableSynonymsTags.get(masterTag);
                    if (strings == null) {
                        strings = new ArrayList<String>();
                        detectableSynonymsTags.put(masterTag, strings);
                    }

                    strings.add(s);
                }


            } else {
                detectableTags.add(tag);
            }
        }
    }
}
