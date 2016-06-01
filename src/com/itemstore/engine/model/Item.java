package com.itemstore.engine.model;

import com.itemstore.engine.model.tag3.TagTree;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

/**
 * Versions FIXME serialId
 */
public class Item implements Serializable {

    public static final Comparator<Item> PUBLISHED_DATE_SORTER = new PublishedDateSorter();
    private int id;

    private final TagTree tagTree;

    private final String title; //Required
    private final String description; //Required
    private final String imageURL1;
    private final String imageURL2;
    private final String youTubeVideoID;
    private final String targetURL; //Required targetURL
    private final String sourceURL;
    private final Date publishedDate;
    private String itemGroupId; //if not null use it= part of group, groupId? Required Same id will be part of same composite object

    private Item(Date publishedDate, String title, String description, String imageURL1, String imageURL2,
                 String youTubeVideoID, String targetURL, String sourceURL,
                 TagTree tagTree, String itemGroupId) {
        this.id = title.hashCode() + targetURL.hashCode(); //FIXME UUID.randomUUID().toString();
        this.tagTree = tagTree;
        this.title = title;
        this.description = description;
        this.imageURL1 = imageURL1;
        this.imageURL2 = imageURL2;
        this.youTubeVideoID = youTubeVideoID;
        this.targetURL = targetURL;
        this.sourceURL = sourceURL;
        this.publishedDate = publishedDate == null ? new Date() : publishedDate; //FIXME
        this.itemGroupId = itemGroupId;
    }

    public static class Builder {
        private Date publishedDate;
        private String title;
        private String description;
        private String imageURL1;
        private String imageURL2;
        private String youTubeVideoID;
        private String targetURL;
        private String sourceURL;
        private TagTree tagTree;
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

        public Builder tags(TagTree tagTree) {
            this.tagTree = tagTree;
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

            //FIXME ???
            /*
            if (Tag.isInvalidValidTags(tags)) {
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


            return new Item(publishedDate, title, description, imageURL1,
                    imageURL2, youTubeVideoID, targetURL, sourceURL, tagTree, itemGroupId);
        }
    }

    public int getId() {
        return id;
    }

    public TagTree getTags() { //getTagNames //FIXME
        return tagTree;
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
                ", tagTree=" + tagTree +
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
