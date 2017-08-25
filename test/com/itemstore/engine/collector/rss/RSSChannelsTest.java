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
    public void tags() {
        RSSChannels rss = RSSChannels.loadFromFile(TestUtil.readFile("channels-muktiple-tags-ok.xml"));
        String tags = rss.getChannels().get(0).getTags();

        Assert.assertEquals(tags, "swe_sport,nyheter,stockholm");

        String tags1 = rss.getChannels().get(1).getTags();
        Assert.assertEquals(tags1, "swe_sport,fotboll,aik,aik_tifo");
    }

    @Test
    public void extra_tag() {
        RSSChannels rss = RSSChannels.loadFromFile(TestUtil.readFile("channels-group-extra-tags.xml"));

        Assert.assertEquals(rss.getChannels().size(), 1);

        Assert.assertEquals(rss.getChannels().get(0).getTags(), "eng_news,cnn.com");
    }

    @Test
    public void channels_with_1_group() {
        RSSChannels rss = RSSChannels.loadFromFile(TestUtil.readFile("channels-group-1.xml"));

        Assert.assertEquals(rss.getChannels().size(), 2);
        for (Channel channel : rss.getChannels()) {
            Assert.assertEquals(channel.getRefreshPeriodInSeconds(), 30);
            Assert.assertNotNull(channel.getUrl());
            Assert.assertEquals(channel.getTags(), "eng_news");
        }
    }

    @Test
    public void so_not_add_tags_twice() {
        RSSChannels rss = RSSChannels.loadFromFile(TestUtil.readFile("channels-group-1.xml"));

        Assert.assertEquals(rss.getChannels().size(), 2);
        for (Channel channel : rss.getChannels()) {
            Assert.assertEquals(channel.getTags(), "eng_news");
        }
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void duplicated_channels_should_throw_exception() {
        RSSChannels.loadFromFile(TestUtil.readFile("duplicated_channels.xml"));
    }

    @Test
    public void validateProductionChannelFile() {

        File file = new File("web", "channels_swe.xml");
        RSSChannels rss = RSSChannels.loadFromFile(file);

        List<Channel> channels = rss.getChannels();

        Assert.assertFalse(channels.isEmpty());

        for (Channel channel : channels) {
            Assert.assertNotNull(channel.getRefreshPeriodInSeconds());
            Assert.assertNotNull(channel.getUrl());
            Assert.assertNotNull(channel.getTags(), "itemTagTree missing " + channel.getUrl());
        }
    }
}
