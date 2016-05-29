package com.itemstore.collector;

import com.itemstore.collector.loader.rss.Channel;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RSSCollector {

    //private static final Set<Channel> channels = new HashSet<Channel>();

    private static final Logger LOG = Logger.getLogger(RSSCollector.class.getName());

    public static List<ItemCollector> load(File channelFile) {

        List<String> channelRows = getChannelRows(channelFile);
        List<Channel> channels = Channel.parseChannelRows(channelRows);
       // channels.addAll(transform);

        List<ItemCollector> channelCollectors = new ArrayList<ItemCollector>();
        for (Channel channel : channels) {
            RSSChannelCollector channelCollector = new RSSChannelCollector(channel.getUnparsedChannelURL(),
                    channel.getTags(), channel.getPollFrequencyInSeconds());
            channelCollectors.add(channelCollector);
        }// //FIXME

        LOG.info("Parsed " + channels.size() + " RSSChannels");
        return channelCollectors;
    }

    private static List<String> getChannelRows(File channelFile) {
       // File channelFile = EngineConf.getInstance().getChannelsFile(servletConfig.getServletContext());

        FileInputStream inputStream = null;
        try {
            LOG.info("Loading file " + channelFile.getAbsolutePath());
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
