package com.itemstore.engine.loader.rss;

import com.itemstore.collector.loader.Loader;
import com.itemstore.collector.loader.rss.Channel;
import com.itemstore.collector.loader.rss.ChannelLoader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ChannelLoaderTest {

    private File channelSourceFile;
    private static final File CHANNEL_FILE = new File("/Users/miguelkrantz/Documents/Flow/FlowServer/web/channels.txt");

    @BeforeMethod
    public void setUp() throws Exception {
        channelSourceFile = new File("/Users/miguelkrantz/Documents/temp/test.txt");
        save();
    }

    @Test
    public void save() throws Exception {
        ChannelLoader c = ChannelLoader.create();
        c.setLoadSource(channelSourceFile, Loader.StorageType.TextRows);
        c.addChannel("http://www.aftonbladet.se/sportbladet/rss.xml", Arrays.asList("Swe", "Sport", "Toplist"), 10);
        c.save();

        List<Channel> x = c.getChannels();

        Assert.assertEquals(x.size(), 1);
    }

    /*@Test
    public void load() throws Exception {
        ChannelLoader c = ChannelLoader.create();
        c.setLoadSource(channelSourceFile, Loader.StorageType.TextRows);

        c.load();
        List<Channel> x = c.getChannels();

        Assert.assertEquals(x.size(), 1);
    }*/

    @Test
    public void preventDuplicates() throws Exception {
        ChannelLoader c = ChannelLoader.create();
        c.setLoadSource(channelSourceFile, Loader.StorageType.TextRows);

        c.addChannel("http://www.aftonbladet.se/sportbladet/rss.xml", Arrays.asList("Swe", "Sport", "Toplist"), 10);
        c.addChannel("http://www.aftonbladet.se/sportbladet/rss.xml", Arrays.asList("Swe", "Sport", "Toplist"), 10);

        List<Channel> x = c.getChannels();

        Assert.assertEquals(x.size(), 1);
    }


  /*  @Test
    public void getChannels() {
        ChannelLoader channelLoader = ChannelLoader.create();
        channelLoader.setLoadSource(CHANNEL_FILE, Loader.StorageType.TextRows);

        channelLoader.load();
        for (Channel channel : channelLoader.getChannels()) {
//Validate url
        }


        //      Assert.assertEquals(channelLoader.getCollectors().size(), 39);
    } */


   /* @Test
    public void validateChannel_0()  {
        ChannelLoader channelLoader = ChannelLoader.create(CHANNEL_FILE);
        channelLoader.load();
        Channel channel = channelLoader.getCollectors().get(0);

        List<Item> parse = channel.parse();
    } */
}