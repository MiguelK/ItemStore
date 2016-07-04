package com.itemstore.engine.collector.rss;

import com.itemstore.TestUtil;
import com.itemstore.collector.rss.Channel;
import com.itemstore.collector.rss.RSSChannels;
import com.itemstore.engine.model.tag3.TagDescendant;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

public class RSSChannelsTest {


    @Test
    public void testName()  {

        RSSChannels rss = RSSChannels.loadFromFile(TestUtil.readFile("channels-muktiple-tags-ok.xml"));

        //swe_sport
        //swe_sport_nyheter
        //swe_sport_stockholm
        List<TagDescendant> tagDescendants = rss.getChannels().get(0).getTag().getTagDescendants();
        Assert.assertEquals(tagDescendants.size(), 3);
        Assert.assertEquals(tagDescendants.get(0).getTags(), "swe_sport");
        Assert.assertEquals(tagDescendants.get(1).getTags(), "swe_sport_Nyheter");
        Assert.assertEquals(tagDescendants.get(2).getTags(), "swe_sport_Stockholm");

    }

    @Test
    public void extra_tag() {
        RSSChannels rss = RSSChannels.loadFromFile(TestUtil.readFile("channels-group-extra-tags.xml"));

        Assert.assertEquals(rss.getChannels().size(), 1);
        /*String tag = rss.getChannels().get(0).getTag();
        List<TagDescendant> tagDescendants = new ItemTagTree.Builder(tag).build().getTagDescendants();

        Assert.assertEquals(tagDescendants.size(), 2); */
    }

    @Test
    public void channels_with_1_group() {
        RSSChannels rss = RSSChannels.loadFromFile(TestUtil.readFile("channels-group-1.xml"));

        Assert.assertEquals(rss.getChannels().size(), 2);
        for (Channel channel : rss.getChannels()) {
            Assert.assertEquals(channel.getRefreshPeridInSeconds(), 30);
            Assert.assertNotNull(channel.getUrl());
            Assert.assertEquals(channel.getTag().getTagDescendants().get(0).getTags(), "eng_news");
        }
    }

    @Test
    public void so_not_add_tags_twice() {
        RSSChannels rss = RSSChannels.loadFromFile(TestUtil.readFile("channels-group-1.xml"));

        Assert.assertEquals(rss.getChannels().size(), 2);
        for (Channel channel : rss.getChannels()) {
            Assert.assertEquals(channel.getTag().getTagDescendants().get(0).getTags(), "eng_news");
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
