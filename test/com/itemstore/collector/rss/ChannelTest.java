package com.itemstore.collector.rss;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ChannelTest {


    @Test
    public void testGetTag() throws Exception {
        Channel channel = new Channel(12,"dn.se","extraTag");
        channel.setTagFromChannelGroup("swe_nyheter");

        Assert.assertEquals(channel.getTags(), "swe_nyheter,extratag");
    }

    @Test
    public void testToString()  {
        Channel channel = new Channel(12,"www.dn.se","swe_sport,swe_nyheter");

        System.out.printf("Channel= " + channel);

        Assert.assertTrue(channel.toString().contains("www.dn.se"));
        Assert.assertTrue(channel.toString().contains("swe_sport"));
        Assert.assertTrue(channel.toString().contains("swe_nyheter"));
        Assert.assertTrue(channel.toString().contains("12"));
    }
}