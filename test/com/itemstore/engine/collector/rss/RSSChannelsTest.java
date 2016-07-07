package com.itemstore.engine.collector.rss;

import com.itemstore.TestUtil;
import com.itemstore.collector.rss.Channel;
import com.itemstore.collector.rss.RSSChannels;
import com.itemstore.engine.model.tag3.TagTreePath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

public class RSSChannelsTest {


    @Test
    public void treePaths() {

        RSSChannels rss = RSSChannels.loadFromFile(TestUtil.readFile("channels-muktiple-tags-ok.xml"));

        List<TagTreePath> tagTreePaths = rss.getChannels().get(0).getTag().getTagTreePaths();
        Assert.assertEquals(tagTreePaths.size(), 3);
        Assert.assertEquals(tagTreePaths.get(0).getTagTreePath(), "swe_sport");
        Assert.assertEquals(tagTreePaths.get(1).getTagTreePath(), "swe_sport_Nyheter");
        Assert.assertEquals(tagTreePaths.get(2).getTagTreePath(), "swe_sport_Stockholm");

        List<TagTreePath> treePaths = rss.getChannels().get(1).getTag().getTagTreePaths();
        Assert.assertEquals(treePaths.get(0).getTagTreePath(), "swe_sport");
        Assert.assertEquals(treePaths.get(1).getTagTreePath(), "swe_sport_fotboll_aik_2016");


    }

    @Test
    public void extra_tag() {
        RSSChannels rss = RSSChannels.loadFromFile(TestUtil.readFile("channels-group-extra-tags.xml"));

        Assert.assertEquals(rss.getChannels().size(), 1);
        /*String tag = rss.getChannels().get(0).getTag();
        List<TagTreePath> tagDescendants = new ItemTagTree.Builder(tag).build().getTagTreePaths();

        Assert.assertEquals(tagDescendants.size(), 2); */
    }

    @Test
    public void channels_with_1_group() {
        RSSChannels rss = RSSChannels.loadFromFile(TestUtil.readFile("channels-group-1.xml"));

        Assert.assertEquals(rss.getChannels().size(), 2);
        for (Channel channel : rss.getChannels()) {
            Assert.assertEquals(channel.getRefreshPeridInSeconds(), 30);
            Assert.assertNotNull(channel.getUrl());
            Assert.assertEquals(channel.getTag().getTagTreePaths().get(0).getTagTreePath(), "eng_news");
        }
    }

    @Test
    public void so_not_add_tags_twice() {
        RSSChannels rss = RSSChannels.loadFromFile(TestUtil.readFile("channels-group-1.xml"));

        Assert.assertEquals(rss.getChannels().size(), 2);
        for (Channel channel : rss.getChannels()) {
            Assert.assertEquals(channel.getTag().getTagTreePaths().get(0).getTagTreePath(), "eng_news");
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
            Assert.assertNotNull(channel.getTag(), "itemTagTree missing " + channel.getUrl());
        }
    }
}
