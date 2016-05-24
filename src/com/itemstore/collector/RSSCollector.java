package com.itemstore.collector;

import com.itemstore.collector.loader.rss.Channel;
import com.itemstore.commons.EngineConf;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletConfig;
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

    private static final Logger LOG = Logger.getLogger(RSSCollector.class.getName());


    public static List<ItemCollector> load(ServletConfig servletConfig) {
        return init(servletConfig);
    }

    private static List<ItemCollector> init(ServletConfig servletConfig) {


       /* ChannelLoader channelLoader = ChannelLoader.create();
        channelLoader.setLoadSource(channelFile, Loader.StorageType.TextRows);
        ItemEngine.getInstance().init(channelLoader); //FIXME Collectore's
        channelLoader.load();

        List<Channel> channels1 = channelLoader.getChannels();

        List<Channel> channels = channelLoader.getChannels();
      */

        List<String> strings = loadTextFile(servletConfig);
        Set<Channel> channels = handleLoadedTextRows(strings);

        List<ItemCollector> channelCollectors = new ArrayList<ItemCollector>();
        for (Channel channel : RSSCollector.channels) {
            RSSChannelCollector channelCollector = new RSSChannelCollector(channel.getUnparsedChannelURL(),
                    channel.getTags(), channel.getPollFrequencyInSeconds());
            channelCollectors.add(channelCollector);
        }// //FIXME

        return channelCollectors;
    }

    private static final Set<Channel> channels = new HashSet<Channel>();


    private static List<String> loadTextFile(ServletConfig servletConfig) {
        File channelFile = EngineConf.getInstance().getChannelsFile(servletConfig.getServletContext());

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

    private static Set<Channel> handleLoadedTextRows(List<String> rows) {
        List<Channel> transform = Channel.parseChannelRows(rows);
        channels.addAll(transform);
        return  channels;

    }
}
