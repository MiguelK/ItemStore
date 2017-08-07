package com.itemstore.collector.rss;

import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlElement;
import java.net.MalformedURLException;
import java.net.URL;

public class Channel {

    @XmlElement(name = "RefreshPeriodInSeconds")
    private int refreshPeriodInSeconds;

    @XmlElement(name = "Url")
    private String url;

    @XmlElement(name = "Tags")
    private String tags;  // , (comma) separated words, space in word separtaed by '_' (underscore)
                        //e.g aik,aik_forboll,allsvenskan

    private String tagRoot;

    protected Channel() {
    }

    Channel(int refreshPeriodInSeconds, String url, String tags) {
        this.refreshPeriodInSeconds = refreshPeriodInSeconds;
        this.url = url;
        this.tags = tags;
    }

    void setRefreshPeriodInSeconds(int refreshPeriodInSeconds) {
        this.refreshPeriodInSeconds = refreshPeriodInSeconds;
    }

    /**
     *
     * @param parentRootTagFromGroup parentRootTags e.g swe_sport or swe_kultur
     */
    void setTagFromChannelGroup(String parentRootTagFromGroup) {
        this.tagRoot = StringUtils.trimToNull(parentRootTagFromGroup);
    }

    public String getTags() {

        String extraTags = StringUtils.trimToNull(tags);

        String result =  tagRoot;

        if(extraTags != null) {
            result = result + "," + extraTags;
        }

        return result.toLowerCase().trim();
    }

    public int getRefreshPeriodInSeconds() {
        return refreshPeriodInSeconds;
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
                "refreshPeriodInSeconds=" + refreshPeriodInSeconds +
                ", url='" + url + '\'' +
                ", tags='" + tags + '\'' +
                '}';
    }
}
