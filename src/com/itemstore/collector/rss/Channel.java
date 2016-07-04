package com.itemstore.collector.rss;

import com.itemstore.engine.model.tag3.ItemTagTree;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlElement;
import java.net.MalformedURLException;
import java.net.URL;

public class Channel {

    @XmlElement(name = "RefreshPeridInSeconds")
    private int refreshPeridInSeconds;

    @XmlElement(name = "Url")
    private String url;

    @XmlElement(name = "Tag")
    private String tag;

    private ItemTagTree itemTagTree;

    protected Channel() {
    }

    Channel(int refreshPeridInSeconds, String url, String tag) {
        this.refreshPeridInSeconds = refreshPeridInSeconds;
        this.url = url;
        this.tag = tag;
    }

    void setRefreshPeridInSeconds(int refreshPeridInSeconds) {
        this.refreshPeridInSeconds = refreshPeridInSeconds;
    }

    /**
     *
     * @param parentRootTagFromGroup parentRootTags e.g swe_sport or swe_kultur
     */
    void setTagFromChannelGroup(String parentRootTagFromGroup) {

        String parentTag = StringUtils.trimToNull(parentRootTagFromGroup);

        if(parentTag==null){
            return;
        }

        if (this.tag != null && this.tag.contains(parentTag)) { //FIXME
            return; //Only add the same tag once
        }

        if(itemTagTree ==null){
            //parentRootTagFromGroup swe_sport  tag=aik_fotboll,aik_2016
            itemTagTree = new ItemTagTree.Builder(parentTag).addTagsToSingleTree(tag).build();
        }

        this.tag = itemTagTree.toString();

        //use both common tag and channel tag
       // this.tag = this.tag != null ? this.tag + ItemTagTree.TAG_DESCENDANT_SEPARATOR + parentRootTagFromGroup : parentRootTagFromGroup;
    }

    public ItemTagTree getTag() {

        if(itemTagTree ==null){
            //parentRootTagFromGroup swe_sport  tag=aik_fotboll,aik_2016
            itemTagTree = new ItemTagTree.Builder(tag).build();
        }

        return itemTagTree;
    }

    public int getRefreshPeridInSeconds() {
        return refreshPeridInSeconds;
    }

    public URL getUrl() {
        try {
            return  new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    void validate() {
        //FIXME validate url+filter
    }

    @Override
    public String toString() {
        return "{" +
                "refreshPeriodInSeconds=" + refreshPeridInSeconds +
                ", url='" + url + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
