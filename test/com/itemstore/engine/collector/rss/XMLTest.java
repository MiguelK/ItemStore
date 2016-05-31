package com.itemstore.engine.collector.rss;

import com.itemstore.collector.rss.Channel;
import com.itemstore.collector.rss.RSSChannels;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

public class XMLTest {


    @Test
    public void validateChannelsFile() {

        File f = new File("web","channels.xml");
        RSSChannels rss = RSSChannels.loadFromFile(f);

        List<Channel> channels = rss.getChannels();

        for (Channel channel : channels) {
            System.out.println(channel.getRefreshPeridInSeconds());
            System.out.println(channel.getUrl());
            System.out.println(channel.getTag());
            Assert.assertNotNull(channel.getRefreshPeridInSeconds());
            Assert.assertNotNull(channel.getUrl());
            Assert.assertNotNull(channel.getTag());
        }
    }
}
