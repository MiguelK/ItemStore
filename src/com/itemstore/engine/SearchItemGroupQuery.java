package com.itemstore.engine;


import com.itemstore.engine.model.tag3.ItemTagTree;
import com.itemstore.engine.model.tag3.TagTreeFilter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SearchItemGroupQuery implements ItemGroupFilter {

    public static class InvalidRequestException extends Exception {
        public InvalidRequestException(String message) {
            super(message);
        }
    }

    private final TagTreeFilter excludeTagTagTreeFilter; //FIXME SearchFilterTagTree

    private final TagTreeFilter favoriteTagTagTreeFilter; //FIXME SearchFilterTagTree

    private final TagTreeFilter includeOnlyTagTagTreeFilter; //If not null only items matching will be returned

    private final List<Integer> excludeItemGroupIds;

    private final List<Integer> itemGroupIds;

    private final Integer maxResultSize;

    private SearchItemGroupQuery(String includeOnlyTagFilter,String excludeTagFilter, String favoriteTagFilter, List<Integer> excludeItemGroupIds,
                                 List<Integer> itemGroupIds, Integer maxResultSize) throws InvalidRequestException {

        if (itemGroupIds == null) {
            throw new InvalidRequestException("itemGroupIds is null");
        }

        if (excludeItemGroupIds == null) {
            throw new InvalidRequestException("excludeItemGroupIds is null");
        }


        this.excludeTagTagTreeFilter = StringUtils.trimToNull(excludeTagFilter) == null ?
                null :  TagTreeFilter.parse(excludeTagFilter);
        this.favoriteTagTagTreeFilter = StringUtils.trimToNull(favoriteTagFilter) == null ?
                null :  TagTreeFilter.parse(favoriteTagFilter);
        this.includeOnlyTagTagTreeFilter = StringUtils.trimToNull(includeOnlyTagFilter) == null ?
                null :  TagTreeFilter.parse(includeOnlyTagFilter);

        this.excludeItemGroupIds = excludeItemGroupIds;
        this.itemGroupIds = itemGroupIds;
        this.maxResultSize = maxResultSize;
    }

    public static SearchItemGroupQuery create(String includeOnlyTagFilter,String excludeTagFilter, String favoriteTagFilter,
                                              List<Integer> excludeItemGroupIds, List<Integer> itemGroupIds,
                                              Integer maxResultSize) throws InvalidRequestException {
        return new SearchItemGroupQuery(includeOnlyTagFilter,excludeTagFilter,
                favoriteTagFilter, excludeItemGroupIds, itemGroupIds, maxResultSize);
    }

    public static SearchItemGroupQuery create(String excludeTagFilter, String favoriteTagFilter,
                                              List<Integer> excludeItemGroupIds, List<Integer> itemGroupIds,
                                              Integer maxResultSize) throws InvalidRequestException {
        return new SearchItemGroupQuery(null,excludeTagFilter,
                favoriteTagFilter, excludeItemGroupIds, itemGroupIds, maxResultSize);
    }

    @Override
    public List<Integer> getExcludeIds() {
        return excludeItemGroupIds;
    }

    @Override
    public List<Integer> getItemIds() {
        return itemGroupIds;
    }

    @Override
    public int getMaxResult() {
        return maxResultSize == null ? 100 : maxResultSize; //FIXME
    }

    @Override
    public TagTreeFilter getExcludeTag() {
        return excludeTagTagTreeFilter;
    }

    @Override
    public TagTreeFilter getFavoriteTag() {
        return favoriteTagTagTreeFilter;
    }

    @Override
    public TagTreeFilter getIncludeOnlyTag() {
        return includeOnlyTagTagTreeFilter;
    }

    @Override
    public String toString() {
        return "SearchItemGroupQuery{" +
                "excludeTagTagTreeFilter=" + excludeTagTagTreeFilter +
                ", favoriteTagTagTreeFilter=" + favoriteTagTagTreeFilter +
                ", excludeItemGroupIds=" + excludeItemGroupIds +
                ", itemGroupIds=" + itemGroupIds +
                ", maxResultSize=" + maxResultSize +
                '}';
    }
}
