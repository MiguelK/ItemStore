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

    private final ItemTagTree itemTagTree; //Transient

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
                 ItemTagTree itemTagTree, String itemGroupId) {
        this.id = title.hashCode() + targetURL.hashCode(); //FIXME UUID.randomUUID().toString();
        this.itemTagTree = itemTagTree;
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
        private ItemTagTree itemTagTree; //Default empty itemTagTree
        private String itemGroupId; //Same id will be part of same composite object

        public Builder sourceURL(String sourceURL) {
            this.sourceURL = StringUtils.trimToNull(sourceURL);
            return this;
        }

        public Builder imageURL1(String imageURL1) {
            this.imageURL1 = StringUtils.trimToNull(imageURL1);
            return this;
        }

        public Builder imageURL2(String imageURL2) { //FIXME use
            this.imageURL2 = StringUtils.trimToNull(imageURL2);
            return this;
        }

        public Builder itemTagTree(ItemTagTree itemTagTree) {
            this.itemTagTree = itemTagTree;
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

            if (itemTagTree == null) {
                throw new IllegalArgumentException("itemTagTree is missing");
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
                    imageURL2, youTubeVideoID, targetURL, sourceURL, itemTagTree, itemGroupId);
        }
    }

    public int getId() {
        return id;
    }

    public ItemTagTree getItemTagTree() { //getTagNames //FIXME
        return itemTagTree;
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
                ", itemTagTree=" + itemTagTree +
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
