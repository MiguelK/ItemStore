package com.itemstore.collector.rss;

import com.itemstore.collector.ItemCollector;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class RSSCollector {

    private static final Logger LOG = Logger.getLogger(RSSCollector.class.getName());

    public static List<ItemCollector> load(File channelFile) {

       /* List<String> channelRows = getChannelRows(channelFile);
        List<Channel> channels = Channel.parseChannelRows(channelRows);

        List<ItemCollector> channelCollectors = new ArrayList<ItemCollector>();
        for (Channel channel : channels) {
            RSSChannelCollector channelCollector = new RSSChannelCollector(channel.get(),
                    channel.getTags(), channel.getPollFrequencyInSeconds());
            channelCollectors.add(channelCollector);
        }// //FIXME

        LOG.info("Parsed " + channels.size() + " RSSChannels from file=" +channelFile.getName()); */
        return null;
    }

    private static List<String> getChannelRows(File channelFile) {

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(channelFile);
            return IOUtils.readLines(inputStream);
        } catch (FileNotFoundException e) {
            LOG.log(Level.SEVERE, "Can not load sourceFile " + channelFile.getAbsolutePath(), e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Can not load sourceFile " + channelFile.getAbsolutePath(), e);
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

}
