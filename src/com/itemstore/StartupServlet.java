package com.itemstore;

import com.itemstore.commons.EngineConf;
import com.itemstore.engine.ItemEngine;
import com.itemstore.collector.ItemCollector;
import com.itemstore.collector.RSSChannelCollector;
import com.itemstore.collector.loader.Loader;
import com.itemstore.collector.loader.rss.Channel;
import com.itemstore.collector.loader.rss.ChannelLoader;
import com.itemstore.collector.loader.topnews.TopNewsSweden;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class StartupServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(StartupServlet.class.getName());

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        logger.info("Starting ItemStore...");

        File channelFile = EngineConf.getInstance().getChannelsFile(config.getServletContext());

        ChannelLoader channelLoader = ChannelLoader.create();
        channelLoader.setLoadSource(channelFile, Loader.StorageType.TextRows);
        ItemEngine.getInstance().init(channelLoader); //FIXME Collectore's

        channelLoader.load();

        List<Channel> channels = channelLoader.getChannels();
        List<ItemCollector> channelCollectors = new ArrayList<ItemCollector>();
        for (Channel channel : channels) {
            RSSChannelCollector channelCollector = new RSSChannelCollector(channel.getUnparsedChannelURL(),
                    channel.getTags(), channel.getPollFrequencyInSeconds());
            channelCollectors.add(channelCollector);
        }// //FIXME

        ItemEngine.getInstance().addCollectors(channelCollectors);
        ItemEngine.getInstance().addCollector(new TopNewsSweden());

        //#1 configure engine load from disk etc...index
        ItemEngine.getInstance().start();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("doPost() StartupServlet not implemented");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("doGet() StartupServlet not implemented");
    }
}
