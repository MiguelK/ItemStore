package com.itemstore.api.request;


import com.itemstore.engine.ItemGroupFilter;
import com.itemstore.engine.model.tag3.TagTree;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SearchItemGroupRequest implements ItemGroupFilter {

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

    private SearchItemGroupRequest(String excludeTagFilter, String favoriteTagFilter, List<Integer> excludeItemGroupIds,
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

    public static SearchItemGroupRequest create(String excludeTagFilter, String favoriteTagFilter,
                                                List<Integer> excludeItemGroupIds, List<Integer> itemGroupIds,
                                                Integer maxResultSize) throws InvalidRequestException {
        return new SearchItemGroupRequest(excludeTagFilter, favoriteTagFilter, excludeItemGroupIds, itemGroupIds, maxResultSize);
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
        return "SearchItemGroupRequest{" +
                "excludeTagFilter=" + excludeTagFilter +
                ", favoriteTagFilter=" + favoriteTagFilter +
                ", excludeItemGroupIds=" + excludeItemGroupIds +
                ", itemGroupIds=" + itemGroupIds +
                ", maxResultSize=" + maxResultSize +
                '}';
    }
}
