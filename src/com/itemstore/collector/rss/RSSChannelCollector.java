package com.itemstore.collector.rss;

import com.itemstore.collector.ItemCollector;
import com.itemstore.collector.ItemCollectorBase;
import com.itemstore.engine.model.Item;
import com.itemstore.engine.model.tag3.TagTree;
import it.sauronsoftware.feed4j.FeedParser;
import it.sauronsoftware.feed4j.bean.Feed;
import it.sauronsoftware.feed4j.bean.FeedHeader;
import it.sauronsoftware.feed4j.bean.FeedItem;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RSSChannelCollector extends ItemCollectorBase {

    private static final Logger logger = Logger.getLogger(RSSChannelCollector.class.getName());

    private final URL channelURL;

    private final String tags;

    private final int pollFrequencyInSeconds;


    public static List<ItemCollector> parseFile(File channelFile){
        List<Channel> channels = RSSChannels.loadFromFile(channelFile).getChannels();

        List<ItemCollector> channelCollectors = new ArrayList<ItemCollector>();
        for (Channel channel : channels) {
            RSSChannelCollector channelCollector = new RSSChannelCollector(channel.getUrl(),
                    channel.getTag(), channel.getRefreshPeridInSeconds());
            channelCollectors.add(channelCollector);
        }

        return channelCollectors;
    }

    private RSSChannelCollector(URL channelURL, String tags, int pollFrequencyInSeconds) {
        this.channelURL = channelURL;
        this.tags = tags;
        this.pollFrequencyInSeconds = pollFrequencyInSeconds;
    }

    @Override
    public List<Item> getItems() {

        Feed feed;
        try {
            feed = FeedParser.parse(channelURL);
        } catch (Exception e) {
            logger.log(Level.FINE, "failed parsing ", e);
            return Collections.emptyList();
        }

        List<Item> items = new ArrayList<Item>();
      //  logger.info("** HEADER **");
        FeedHeader header = feed.getHeader();
        //  logger.info("Title: " + header.getTitle());
        // logger.info("Link: " + header.getLink());
        // logger.info("Description: " + header.getDescription());
        // logger.info("Language: " + header.getLanguage());
        // logger.info("PubDate: " + header.getPubDate());

        int itemCount = feed.getItemCount();
        for (int i = 0; i < itemCount; i++) {
            FeedItem rssItem = feed.getItem(i);

            logger.fine(rssItem.getPubDate() + " Parsing feed " + i + " of " + itemCount + ", url=" + channelURL);
            String imageUrl1 = null;
            String description = null;
            String title = null;
            String rssItemLink;
            try {
                description = StringUtils.trimToNull(rssItem.getDescriptionAsText());
                String descriptionAsHTML = StringUtils.trimToNull(rssItem.getDescriptionAsHTML());
                rssItemLink = rssItem.getLink() != null ? rssItem.getLink().toString() : null; //FIXME use URL?
                title = StringUtils.trimToNull(rssItem.getTitle());
                //rssItem.getComments()

                if (descriptionAsHTML != null) { //FIXME bug??? hang
                    Document doc = Jsoup.parse(descriptionAsHTML);
                    Elements imageURL1 = doc.select("img[src]");
                    if (imageURL1.size() > 0) {
                        imageUrl1 = StringUtils.trimToNull(imageURL1.get(0).attr("src"));
                    }
                }

                if(imageUrl1==null || title==null || description == null) {
                    continue; //FIXME filter
                }


                TagTree tagTree = new TagTree.Builder(tags).build(); //FIXME add to root??
                //FIXME Tag and TagCollector extract tags...rssItemLink(Video or articleUrl)
                Item item = new Item.Builder().imageURL1(imageUrl1).targetURL(rssItemLink).sourceURL(channelURL.toString())
                        .tags(tagTree).title(title).description(description)
                        .publishedDate(rssItem.getPubDate()).build();

                items.add(item);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error parsing feed ", e);
                logger.fine("Unabe to parse: imageUrl1=" + imageUrl1 + ",description=" + description + " , title=" + title);
                logger.fine("HTML= " + rssItem.getDescriptionAsHTML());
                logger.fine("Title: " + rssItem.getTitle());
                logger.fine("Link: " + rssItem.getLink());
                logger.fine("Plain text description: " + rssItem.getDescriptionAsText());
                logger.fine("HTML description: " + rssItem.getDescriptionAsHTML());
                logger.fine("PubDate: " + rssItem.getPubDate());
            }
        }
        logger.fine("Parsed " + items);

        return items;
    }

    @Override
    public int getPollFrequencyInSeconds() {
        return pollFrequencyInSeconds;
    }
}