package com.itemstore.engine;


import com.itemstore.engine.model.tag3.TagTree;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SearchItemGroupQuery implements ItemGroupFilter {

    public static class InvalidRequestException extends Exception {
        public InvalidRequestException(String message) {
            super(message);
        }
    }

    private final TagTree excludeTagFilter;

    private final TagTree favoriteTagFilter;

    private final List<Integer> excludeItemGroupIds;

    private final List<Integer> itemGroupIds;

    private final Integer maxResultSize;

    private SearchItemGroupQuery(String excludeTagFilter, String favoriteTagFilter, List<Integer> excludeItemGroupIds,
                                 List<Integer> itemGroupIds, Integer maxResultSize) throws InvalidRequestException {

        if (itemGroupIds == null) {
            throw new InvalidRequestException("itemGroupIds is null");
        }

        if (excludeItemGroupIds == null) {
            throw new InvalidRequestException("excludeItemGroupIds is null");
        }


        this.excludeTagFilter = StringUtils.trimToNull(excludeTagFilter) == null ?
                null : new TagTree.Builder(excludeTagFilter).build();
        this.favoriteTagFilter = StringUtils.trimToNull(favoriteTagFilter) == null ?
                null : new TagTree.Builder(favoriteTagFilter).build();

        this.excludeItemGroupIds = excludeItemGroupIds;
        this.itemGroupIds = itemGroupIds;
        this.maxResultSize = maxResultSize;
    }

    public static SearchItemGroupQuery create(String excludeTagFilter, String favoriteTagFilter,
                                              List<Integer> excludeItemGroupIds, List<Integer> itemGroupIds,
                                              Integer maxResultSize) throws InvalidRequestException {
        return new SearchItemGroupQuery(excludeTagFilter, favoriteTagFilter, excludeItemGroupIds, itemGroupIds, maxResultSize);
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
    public TagTree getExcludeTag() {
        return excludeTagFilter;
    }

    @Override
    public TagTree getFavoriteTag() {
        return favoriteTagFilter;
    }

    @Override
    public String toString() {
        return "SearchItemGroupQuery{" +
                "excludeTagFilter=" + excludeTagFilter +
                ", favoriteTagFilter=" + favoriteTagFilter +
                ", excludeItemGroupIds=" + excludeItemGroupIds +
                ", itemGroupIds=" + itemGroupIds +
                ", maxResultSize=" + maxResultSize +
                '}';
    }
}
