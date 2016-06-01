package com.itemstore.engine.collector.rss;

import com.itemstore.TestUtil;
import com.itemstore.collector.rss.Channel;
import com.itemstore.collector.rss.RSSChannels;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

public class RSSChannelsTest {
    @Test
    public void channels_with_1_group() {
        RSSChannels rss = RSSChannels.loadFromFile(TestUtil.readFile("channels-group-1.xml"));

        Assert.assertEquals(rss.getChannels().size(), 2);
        for (Channel channel : rss.getChannels()) {
            Assert.assertEquals(channel.getRefreshPeridInSeconds(), 30);
            Assert.assertNotNull(channel.getUrl());
            Assert.assertEquals(channel.getTag(), "eng_news");
        }
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void duplicated_channels_should_throw_exception() {
        RSSChannels.loadFromFile(TestUtil.readFile("duplicated_channels.xml"));
    }

    @Test
    public void validateProductionChannelFile() {

        File file = new File("web", "channels.xml");
        RSSChannels rss = RSSChannels.loadFromFile(file);

        List<Channel> channels = rss.getChannels();

        Assert.assertFalse(channels.isEmpty());

        for (Channel channel : channels) {
            Assert.assertNotNull(channel.getRefreshPeridInSeconds());
            Assert.assertNotNull(channel.getUrl());
            Assert.assertNotNull(channel.getTag());
        }
    }
}
