package com.itemstore.api.request;


import com.itemstore.engine.ItemGroupFilter;
import com.itemstore.engine.model.tag3.TagTree;

import java.util.List;

public class SearchItemGroupRequest implements ItemGroupFilter {

    public static class InvalidRequestException extends Exception {
        public InvalidRequestException(String message) {
            super(message);
        }
    }

    private String excludeTagFilter; //swe_sport,eng_*,swe_kultur_*

    private String favoriteTagFilter; //swe_sport,eng_*,swe_kultur_*

    private List<Integer> excludeItemGroupIds;

    private List<Integer> itemGroupIds;

    private Integer maxResultSize;

    private SearchItemGroupRequest(String excludeTagFilter, String favoriteTagFilter, List<Integer> excludeItemGroupIds,
                                   List<Integer> itemGroupIds, Integer maxResultSize) {

        this.excludeTagFilter = excludeTagFilter;
        this.favoriteTagFilter = favoriteTagFilter;
        this.excludeItemGroupIds = excludeItemGroupIds;
        this.itemGroupIds = itemGroupIds;
        this.maxResultSize = maxResultSize;
    }

    public static SearchItemGroupRequest create(String excludeTagFilter, String favoriteTagFilter, List<Integer> excludeItemGroupIds, List<Integer> itemGroupIds, Integer maxResultSize) {
        return new SearchItemGroupRequest(excludeTagFilter, favoriteTagFilter, excludeItemGroupIds, itemGroupIds, maxResultSize);
    }

    public void validate() throws InvalidRequestException {
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
        return excludeTagFilter == null ? null : new TagTree.Builder(excludeTagFilter).build();
    }

    @Override
    public TagTree getFavoriteTag() {
        return favoriteTagFilter == null ? null : new TagTree.Builder(favoriteTagFilter).build();
    }
}
