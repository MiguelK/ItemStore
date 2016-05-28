package com.itemstore.engine.model.tag;

import java.util.ArrayList;
import java.util.List;

public class TagTree implements TagMatcher {


    public List<TagDescendant> getTagDescendants() {
        return new ArrayList();
    }

    public static class Builder {
        public TagTree build() {
            return null;
        }

        public Builder rootTagSweTeknik() {
            return this;
        }

        public Builder add(List<TagDescendant> paths) {
            return null;
        }

        public Builder addTagDescendants(TagDescendant... tagDescendants) {
            return null;
        }
    }


    /**
     * (Descendant – A node reachable by repeated proceeding from parent to child.).
     * e.g "swe/sport/fotboll/landslaget/zlatan" = TagDescendant
     */
   /* private class TagDescendant {

    }*/

    @Override
    public double match(TagMatcher tagMatcher) {
        return 0;
    }
}
