package com.itemstore.api.request;


import java.util.List;

public class SearchItemGroupRequest {

    public static class InvalidRequestException extends Exception{
        public InvalidRequestException(String message) {
            super(message);
        }
    }

    private String excludeTagFilter; //swe_sport,eng_*,swe_kultur_*

    private String favoriteTagFilter; //swe_sport,eng_*,swe_kultur_*

    private List<String> excludeItemGroupIds;

    private List<Integer> itemGroupIds;

    private Integer maxResultSize;

    private SearchItemGroupRequest(String excludeTagFilter, String favoriteTagFilter, List<String> excludeItemGroupIds, List<Integer> itemGroupIds, Integer maxResultSize) {

        this.excludeTagFilter = excludeTagFilter;
        this.favoriteTagFilter = favoriteTagFilter;
        this.excludeItemGroupIds = excludeItemGroupIds;
        this.itemGroupIds = itemGroupIds;
        this.maxResultSize = maxResultSize;
    }

    public static SearchItemGroupRequest create(String excludeTagFilter, String favoriteTagFilter, List<String> excludeItemGroupIds, List<Integer> itemGroupIds, Integer maxResultSize) {
        return new SearchItemGroupRequest(excludeTagFilter, favoriteTagFilter, excludeItemGroupIds, itemGroupIds, maxResultSize);
    }

    public String getExcludeTagFilter() {
        return excludeTagFilter;
    }

    public String getFavoriteTagFilter() {
        return favoriteTagFilter;
    }

    public List<String> getExcludeItemGroupIds() {
        return excludeItemGroupIds;
    }

    public List<Integer> getItemGroupIds() {
        return itemGroupIds;
    }

    public Integer getMaxResultSize() {
        return maxResultSize;
    }

    public void validate() throws InvalidRequestException{

    }
}
