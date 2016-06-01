package com.itemstore.collector.rss;

import com.itemstore.engine.model.tag3.TagTree;

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

    void setRefreshPeridInSeconds(int refreshPeridInSeconds) {
        this.refreshPeridInSeconds = refreshPeridInSeconds;
    }

    void setTag(String newTag) {
        //use both common tag and channel tag
        this.tag = this.tag != null ? this.tag + TagTree.TAGDESCENDANT_SEPARATOR + newTag : newTag;
    }

    public String getTag() {
        return tag;
    }

    public int getRefreshPeridInSeconds() {
        return refreshPeridInSeconds;
    }

    public URL getUrl() {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    void validate() {
        //FIXME validate url+filter


    }
}
