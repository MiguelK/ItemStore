package com.itemstore.engine.model.tag3;

//Root swe_sport (Mandatory for Items)

public enum TagRoot {
    ENG_NEWS("eng_news"),
    ENG_SPORT("eng_sport"),

    //Swedish tag roots
    SWE_NYHETER_EXTRA("swe_nyheter_extra"),
    SWE_DATA("swe_data"),
    SWE_NYHETER("swe_nyheter"),
    SWE_SPORT("swe_sport");

    private String tags;

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
