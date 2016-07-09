package com.itemstore.engine.model.tag3;

//Root swe_sport (Mandatory for Items)

import java.util.Arrays;
import java.util.List;

public enum TagRoot {
    ENG_NEWS("eng_news"),
    ENG_SPORT("eng_sport"),

    //Swedish tag roots
    SWE_NYHETER_EXTRA("swe_nyheter_extra"),
    SWE_DATA("swe_data"),
    SWE_NYHETER("swe_nyheter"),
    SWE_SPORT("swe_sport");

    public static final List<TagRoot> SORT_ORDER = Arrays.asList(TagRoot.SWE_NYHETER_EXTRA, TagRoot.SWE_NYHETER, TagRoot.SWE_SPORT,
            TagRoot.SWE_DATA,TagRoot.ENG_NEWS,TagRoot.ENG_SPORT);

    private final String tags;

    TagRoot(String tags) {
        this.tags = tags;
    }

    static void validate(String tag) {

        boolean notFound = true;
        for (TagRoot mandatoryTag : values()) {
            if (mandatoryTag.tags.equals(tag)) {
                notFound = false;
                break;
            }
        }

        if (notFound) {
            throw new TagTreeException("Tag " + tag + " not valid");
        }
    }

    public String getTags() {
        return tags;
    }
}
