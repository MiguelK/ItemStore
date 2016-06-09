package com.itemstore.collector.rss;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ChannelTest {


    @Test
    public void testSetRefreshPeridInSeconds() throws Exception {


    }

    @Test
    public void testSetTagFromChannelGroup() throws Exception {

    }

    @Test
    public void testGetTag() throws Exception {
        Channel channel = new Channel(12,"dn.se","swe_sport");
        channel.setTagFromChannelGroup("swe_nyheter");

        System.out.println(channel.getTag());

    }

    @Test
    public void testGetRefreshPeridInSeconds() throws Exception {

    }

    @Test
    public void testGetUrl() throws Exception {

    }

    @Test
    public void testValidate() throws Exception {

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