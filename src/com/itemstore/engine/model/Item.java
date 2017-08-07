package com.itemstore.engine.model;

import com.itemstore.engine.model.tag3.ItemTagTree;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * Versions FIXME serialId
 */
public class Item implements Serializable {

    public static final Comparator<Item> PUBLISHED_DATE_SORTER = new PublishedDateSorter();
    private final int id;

    private final String tags; //Transient

    private final String title; //Required
    private final String description; //Required
    private final String imageURL1;
    private final String imageURL2;
    private final String youTubeVideoID;
    private final String targetURL; //Required targetURL
    private final String sourceURL;
    private final LocalDateTime publishedDate;
    private final String itemGroupId; //if not null use it= part of group, groupId? Required Same id will be part of same composite object

    private Item(LocalDateTime publishedDate, String title, String description, String imageURL1, String imageURL2,
                 String youTubeVideoID, String targetURL, String sourceURL,
                 String tags, String itemGroupId) {
        this.id = title.hashCode() + targetURL.hashCode(); //FIXME UUID.randomUUID().toString();
        this.tags = tags;
        this.title = title;
        this.description = description;
        this.imageURL1 = imageURL1;
        this.imageURL2 = imageURL2;
        this.youTubeVideoID = youTubeVideoID;
        this.targetURL = targetURL;
        this.sourceURL = sourceURL;
        this.publishedDate = publishedDate == null ? LocalDateTime.now() : publishedDate; //FIXME
        this.itemGroupId = itemGroupId;
    }

    public static class Builder {
        private LocalDateTime publishedDate;
        private String title;
        private String description;
        private String imageURL1;
        private String imageURL2;
        private String youTubeVideoID;
        private String targetURL;
        private String sourceURL;
        private String tags; //Default empty itemTagTree
        private String itemGroupId; //Same id will be part of same composite object

        public Builder sourceURL(String sourceURL) {
            this.sourceURL = StringUtils.trimToNull(sourceURL);
            return this;
        }

        public Builder imageURL1(String imageURL1) {
            this.imageURL1 = StringUtils.trimToNull(imageURL1);
            return this;
        }

        public Builder tags(String tags) {
            this.tags = tags;
            return this;
        }

        public Builder title(String title) {
            this.title = StringUtils.trimToNull(title);
            return this;
        }

        public Builder publishedDate(LocalDateTime publishedDate) {
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

        public Builder targetURL(String targetURL) {
            this.targetURL = StringUtils.trimToNull(targetURL);
            return this;
        }


        public Item build() {
            if (StringUtils.isEmpty(title)) {
                throw new IllegalArgumentException("Invalid title " + title);
            }


            if (StringUtils.isEmpty(targetURL)) {
                throw new IllegalArgumentException("Invalid targetURL " + targetURL);
            }

            if (tags == null) {
                throw new IllegalArgumentException("tags is missing");
            }

            //FIXME ???
            /*
            if (Tag.isInvalidValidTags(itemTagTree)) {
                throw new IllegalArgumentException("Invalid itemTagTree " + itemTagTree);
            }*/

            /*if(StringUtils.isEmpty(description)){
                throw new IllegalArgumentException("Invalid description " + description);
            }*/

            /*if (itemTagTree.isEmpty()) {
                throw new IllegalArgumentException("Invalid itemTagTree " + itemTagTree);
            }*/

            //Validate
            //FIXME TextAnayzier textAnayzier = new TextAnayzier() {
            //TagCollector tagCollector = new TagCollector(description);


            return new Item(publishedDate, title, description, imageURL1,
                    imageURL2, youTubeVideoID, targetURL, sourceURL, tags, itemGroupId);
        }
    }

    public int getId() {
        return id;
    }

    public String getTags() { //getTagNames //FIXME
        return tags;
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

    public String getTargetURL() {
        return targetURL;
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

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public boolean isSimilarTo(Item item) {
        return false; //FIXME itemTagTree + and textAnalys
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
                ", targetURL='" + targetURL + '\'' +
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
