package com.flow.engine.text;

import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

public class TextMatcher {

    public enum Lang {SWE}

    private final String text;

    private final TextMatcherLang textMatcherLang;

    private final List<String> parts;

    private TextMatcher(String text, TextMatcherLang textMatcherLang) {
        this.text = text;
        this.textMatcherLang = textMatcherLang;
        this.parts = parseParts();
    }

    public static TextMatcher getMatcher(String text) {
        return new TextMatcher(text, new TextMatcherSwe());//FIXME
    }

    public boolean match(TextMatcher textMatcher) {

        Collection<String> intersection = CollectionUtils.intersection(textMatcher.getParts(), parts);
        System.out.println("intersection= " + intersection);

        return intersection.size()>0;
    }

    private List<String> getParts() {
        return parts;
    }

    private List<String> parseParts() {

        if(text==null || text.isEmpty()){
            return Collections.emptyList();
        }

        Scanner scanner = new Scanner(text);

        List<String> theParts = new ArrayList<String>();
        while (scanner.hasNext()) {
            String next = scanner.next();

            String s = textMatcherLang.getWordId(next);

            if (s != null) {
                theParts.add(s);
            }

//            System.out.println("Part= " + next);
        }

        return theParts;
    }

}
