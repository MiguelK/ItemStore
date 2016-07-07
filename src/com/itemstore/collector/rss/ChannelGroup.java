package com.itemstore.collector.rss;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

class ChannelGroup {

    @XmlElementWrapper(name = "Channels")
    @XmlElement(name="Channel")
    private List<Channel> channels = new ArrayList<>();

    @XmlElement(name = "RefreshPeridInSeconds")
    private int refreshPeridInSeconds;

    @XmlElement(name = "TagRoot")
    private String tagRoot;

    public List<Channel> getChannels() {

        for (Channel channel : channels) {
            channel.setRefreshPeridInSeconds(refreshPeridInSeconds);
            channel.setTagFromChannelGroup(tagRoot);
        }
        return channels;
    }
}
