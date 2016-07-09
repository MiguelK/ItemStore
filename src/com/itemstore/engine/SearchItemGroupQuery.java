package com.itemstore.engine;


import com.itemstore.engine.model.tag3.TagTreeFilter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SearchItemGroupQuery {

    public static class InvalidRequestException extends Exception {
        InvalidRequestException(String message) {
            super(message);
        }
    }

    private final TagTreeFilter excludeTagTagTreeFilter; //FIXME SearchFilterTagTree

    private final TagTreeFilter includeTagTreeFilter; //If not null only items matching will be returned

    private final List<Integer> excludeItemGroupIds;

    private SearchItemGroupQuery(String includeTagFilter, String excludeTagFilter, List<Integer> excludeItemGroupIds) throws InvalidRequestException {

        if (excludeItemGroupIds == null) {
            throw new InvalidRequestException("excludeItemGroupIds is null");
        }

        this.includeTagTreeFilter = StringUtils.trimToNull(includeTagFilter) == null ?
                null : TagTreeFilter.parse(includeTagFilter);

        this.excludeTagTagTreeFilter = StringUtils.trimToNull(excludeTagFilter) == null ?
                null : TagTreeFilter.parse(excludeTagFilter);

        this.excludeItemGroupIds = excludeItemGroupIds;
    }

    public static SearchItemGroupQuery create(String includeTagFilter, String excludeTagFilter,
                                              List<Integer> excludeItemGroupIds) throws InvalidRequestException {
        return new SearchItemGroupQuery(includeTagFilter, excludeTagFilter,
                excludeItemGroupIds);
    }

    public List<Integer> getExcludeIds() {
        return excludeItemGroupIds;
    }

    public TagTreeFilter getExcludeTagTreeFilter() {
        return excludeTagTagTreeFilter;
    }

    public TagTreeFilter getIncludeTagTreeFilter() {
        return includeTagTreeFilter;
    }

    @Override
    public String toString() {
        return "SearchItemGroupQuery{" +
                "excludeTagTagTreeFilter=" + excludeTagTagTreeFilter +
                ", includeTagTreeFilter=" + includeTagTreeFilter +
                ", excludeItemGroupIds=" + excludeItemGroupIds +
                '}';
    }
}
