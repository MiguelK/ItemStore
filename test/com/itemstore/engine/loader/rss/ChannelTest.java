package com.itemstore.engine.loader.rss;

import it.sauronsoftware.feed4j.FeedParser;
import it.sauronsoftware.feed4j.bean.Feed;
import it.sauronsoftware.feed4j.bean.FeedHeader;
import it.sauronsoftware.feed4j.bean.FeedItem;
import junit.framework.Assert;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.Arrays;

public class ChannelTest {

    @Test
    public void testFeedParser() throws Exception {
        URL url = new URL("http://www.aftonbladet.se/sportbladet/rss.xml");
        Feed feed = FeedParser.parse(url);

        System.out.println("** HEADER **");
        FeedHeader header = feed.getHeader();
        System.out.println("Title: " + header.getTitle());
        System.out.println("Link: " + header.getLink());
        System.out.println("Description: " + header.getDescription());
        System.out.println("Language: " + header.getLanguage());
        System.out.println("PubDate: " + header.getPubDate());

        System.out.println("** ITEMS **");
        int items = feed.getItemCount();
        for (int i = 0; i < items; i++) {
            FeedItem item = feed.getItem(i);


            //Document doc = Jsoup.parse(item.getDescriptionAsHTML());
            //Elements pngs = doc.select("img[src$=.png]");

            //Elements jpg = doc.select("img[src]");


            //System.out.println(item.getDescriptionAsHTML() + " jpg == " + jpg.size());

            //if(jpg.size()>0){
            //   System.out.println(jpg.get(0).attr("src"));


            //}

          /*  System.out.println("Title: " + item.getTitle());
            System.out.println("Link: " + item.getLink());
            System.out.println("Plain text description: " + item.getDescriptionAsText());
            System.out.println("HTML description: " + item.getDescriptionAsHTML());
            System.out.println("PubDate: " + item.getPubDate());  */
        }
    }

    @Test
    public void testName() throws Exception {

        String s = "<img src='http://gfx.aftonbladet-cdn.se/image/21246010/250/widescreen/b8d3f6fd9ea4f/beskedet.jpg ' />";


        System.out.printf("Start..");
        Document parse = Jsoup.parse(s);
        System.out.printf("Done.." + parse);

    }

    @Test
    public void newChannel_valid() {
        Assert.assertTrue(Channel.newChannel("http://www.aftonbladet.se/sportbladet/rss.xml", Arrays.asList("Swe", "Sport")).isValidURLSyntax());
        Assert.assertTrue(Channel.newChannel("http://www.aftonbladet.se/nyheter/rss/article11936266.ab", Arrays.asList("Swe", "Sport")).isValidURLSyntax());
    }

    @Test
    public void newChannel_inValid() {
        Assert.assertFalse(Channel.newChannel("sdsdsd.sdsd.rss", Arrays.asList("Swe","Sport")).isValidURLSyntax());
        Assert.assertFalse(Channel.newChannel(" ", Arrays.asList("Swe","Sport")).isValidURLSyntax());
        Assert.assertFalse(Channel.newChannel("http://localhost:8080/  a /common/emxNavigator.jsp#", Arrays.asList("Swe","Sport")).isValidURLSyntax());
        Assert.assertFalse(Channel.newChannel("", Arrays.asList("Swe","Sport")).isValidURLSyntax());
        Assert.assertFalse(Channel.newChannel(null, Arrays.asList("Swe","Sport")).isValidURLSyntax());
    }

    @Test
    public void equalChannels() {
        Channel channel1 = Channel.newChannel("http://www.aftonbladet.se/sportbladet/rss.xml ", Arrays.asList("Swe","Sport"), 22);
        Channel channel2 = Channel.newChannel("http://www.aftonbladet.se/sportbladet/rss.xml", Arrays.asList("Swe","Sport"), 22);

        Assert.assertEquals(channel1, channel2);
    }

    @Test
    public void notEqualChannels_pollFrequency() {
        Channel channel1 = Channel.newChannel("http://www.aftonbladet.se/sportbladet/rss.xml", Arrays.asList("Swe","Sport"), 10);
        Channel channel2 = Channel.newChannel("http://www.aftonbladet.se/sportbladet/rss.xml", Arrays.asList("Swe","Sport"), 20);

        Assert.assertFalse(channel1.equals(channel2));
    }


    @Test
    public void notEqualChannels_pollUrl() {
        Channel channel1 = Channel.newChannel("http://www.aftonbladet.se/sportbladet/rss.xml", Arrays.asList("Swe","Sport"), 10);
        Channel channel2 = Channel.newChannel("http://www.aftonbladet.se/sportbladet/rss.xml", Arrays.asList("Swe","Sport"), 20);

        Assert.assertFalse(channel1.equals(channel2));
    }
}