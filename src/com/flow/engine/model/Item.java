package com.flow.engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flow.engine.model.tag.TagContainer;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Versions FIXME serialId
 */
public class Item implements Serializable {

    public static final Comparator<Item> PUBLISHED_DATE_SORTER = new PublishedDateSorter();
    private String id;

    private final List<String> tags;

    private final String title; //Required
    private final String description; //Required
    private final String imageURL1;
    private final String imageURL2;
    private final String youTubeVideoID;
    private final String articleURL1;
    private final String videoURL1;
    private final String sourceURL;
    private final Date publishedDate;
    private String itemGroupId; //if not null use it= part of group, groupId? Required Same id will be part of same composite object

    private Item(Date publishedDate, String title, String description, String imageURL1, String imageURL2,
                 String youTubeVideoID, String articleURL1, String videoURL1, String sourceURL,
                 List<String> tags, String itemGroupId) {
        this.id = UUID.randomUUID().toString();
        this.tags = tags;
        this.title = title;
        this.description = description;
        this.imageURL1 = imageURL1;
        this.imageURL2 = imageURL2;
        this.youTubeVideoID = youTubeVideoID;
        this.articleURL1 = articleURL1;
        this.videoURL1 = videoURL1;
        this.sourceURL = sourceURL;
        this.publishedDate = publishedDate == null ? new Date() : publishedDate; //FIXME
        this.itemGroupId = itemGroupId;
    }

    public String getTagWithPrefix(String tagPrefix) {

        for (String tag : this.tags) {
            if (tag.startsWith(tagPrefix)) {
                return tag;//FIXME
            }
        }
        return null;
    }

    public String getTagValueByName(String tagName) {
        return getTagContainer().getTagValueByName(tagName);
    }

    public static class Builder {
        private Date publishedDate;
        private String title;
        private String description;
        private String imageURL1;
        private String imageURL2;
        private String youTubeVideoID;
        private String articleURL1;
        private String videoURL1;
        private String sourceURL;
        private TagContainer tagContainer = TagContainer.create("");
        private String itemGroupId; //Same id will be part of same composite object

        public Builder itemGroupId(String itemGroupId) {
            this.itemGroupId = StringUtils.trimToNull(itemGroupId);
            return this;
        }

        public Builder sourceURL(String sourceURL) {
            this.sourceURL = StringUtils.trimToNull(sourceURL);
            return this;
        }

        public Builder imageURL1(String imageURL1) {
            this.imageURL1 = StringUtils.trimToNull(imageURL1);
            return this;
        }

        public Builder imageURL2(String imageURL2) {
            this.imageURL2 = StringUtils.trimToNull(imageURL2);
            return this;
        }

        public Builder tags(TagContainer tagContainer) {
            this.tagContainer = tagContainer;
            return this;
        }

        public Builder title(String title) {
            this.title = StringUtils.trimToNull(title);
            return this;
        }
        public Builder publishedDate(Date publishedDate) {
            this.publishedDate = publishedDate;
            return this;
        }

        public Builder description(String description) {
            this.description = StringUtils.trimToNull(description);
            return this;
        }

        public Builder youTubeVideoID(String youTubeVideoID) {
            this.youTubeVideoID = StringUtils.trimToNull(youTubeVideoID);
            return this;
        }

        public Builder articleURL1(String articleURL1) {
            this.articleURL1 = StringUtils.trimToNull(articleURL1);
            return this;
        }

        public Builder videoURL1(String videoURL1) {
            this.videoURL1 = StringUtils.trimToNull(videoURL1);
            return this;
        }


        public Item build() {
            if (StringUtils.isEmpty(title)) {
                throw new IllegalArgumentException("Invalid title " + title);
            }

            //FIXME ???
           /* if (Tag.isInvalidValidTags(tags)) {
                throw new IllegalArgumentException("Invalid tags " + tags);
            }*/

            /*if(StringUtils.isEmpty(description)){
                throw new IllegalArgumentException("Invalid description " + description);
            }*/

            /*if (tags.isEmpty()) {
                throw new IllegalArgumentException("Invalid tags " + tags);
            }*/

            //Validate
            //TagCollector tagCollector = new TagCollector();


            return new Item(publishedDate,title, description, imageURL1,
                    imageURL2, youTubeVideoID, articleURL1, videoURL1, sourceURL, tagContainer.getRawTags(), itemGroupId);
        }
    }

    public String getId() {
        return id;
    }

    public List<String> getTags() { //getTagNames //FIXME
        return tags;
    }

    @JsonIgnore
    public TagContainer getTagContainer() {
        return TagContainer.create(tags);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL1() {
        return imageURL1;
    }

    public String getImageURL2() {
        return imageURL2;
    }

    public String getArticleURL1() {
        return articleURL1;
    }

    public String getVideoURL1() {
        return videoURL1;
    }

    public String getYouTubeVideoID() {
        return youTubeVideoID;
    }

    public String getSourceURL() {
        return sourceURL;
    }

    public String getItemGroupId() {
        return itemGroupId;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public boolean isSimilarTo(Item item) {
        return false; //FIXME tags + and textAnalys
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return title.equals(item.title);

    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", tags=" + tags +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageURL1='" + imageURL1 + '\'' +
                ", imageURL2='" + imageURL2 + '\'' +
                ", youTubeVideoID='" + youTubeVideoID + '\'' +
                ", articleURL1='" + articleURL1 + '\'' +
                ", videoURL1='" + videoURL1 + '\'' +
                ", sourceURL='" + sourceURL + '\'' +
                ", publishedDate=" + publishedDate +
                ", itemGroupId='" + itemGroupId + '\'' +
                '}';
    }

    private static final class PublishedDateSorter implements Comparator<Item> {
        @Override
        public int compare(Item o1, Item o2) {
            return o1.publishedDate.compareTo(o2.publishedDate);
        }
    }
}
