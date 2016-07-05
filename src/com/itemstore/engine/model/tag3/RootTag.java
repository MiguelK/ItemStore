package com.itemstore.engine.model.tag3;

//Root swe_sport (Mandatory for Items)

public enum RootTag {
    ENG_NEWS("eng_news"),
    ENG_SPORT("eng_sport"),

    SWE_NEWS("swe_news"),
    SWE_NYHETER("swe_nyheter"),
    SWE_SPORT("swe_sport");

    private String tags;

    RootTag(String tags) {
        this.tags = tags;
    }

    static void validate(String tag) {

        boolean notFound = true;
        for (RootTag mandatoryTag : values()) {
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
