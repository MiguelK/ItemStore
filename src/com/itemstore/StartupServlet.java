package com.itemstore;

import com.itemstore.commons.EngineConf;
import com.itemstore.engine.FlowEngine;
import com.itemstore.engine.collector.ItemCollector;
import com.itemstore.engine.collector.RSSChannelCollector;
import com.itemstore.engine.loader.ItemLoader;
import com.itemstore.engine.loader.Loader;
import com.itemstore.engine.loader.UserLoader;
import com.itemstore.engine.loader.rss.Channel;
import com.itemstore.engine.loader.rss.ChannelLoader;
import com.itemstore.engine.loader.topnews.TopNewsSweden;

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


    private static final boolean TV_AND_FLOW_MODE = false;

    private static final Logger logger = Logger.getLogger(StartupServlet.class.getName());

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        EngineConf.getInstance().load(config);

        setupHardcodeTestValues();

        //#1 configure engine load from disk etc...index
        FlowEngine.getInstance().start();
    }

    private void setupHardcodeTestValues() {
        logger.info("Starting itemstore engine in ....DEV mode");

        File channelFile = EngineConf.getInstance().getChannelsFile();
        File itemsFile = EngineConf.getInstance().getItemsFile();

        ItemLoader itemLoader = new ItemLoader();
        itemLoader.setLoadSource(itemsFile, Loader.StorageType.Serializable);
        UserLoader userLoader = new UserLoader();//FIXME load from userFile

        ChannelLoader channelLoader = ChannelLoader.create();
        channelLoader.setLoadSource(channelFile, Loader.StorageType.TextRows);
        FlowEngine.getInstance().init(channelLoader, userLoader, itemLoader);

        if (TV_AND_FLOW_MODE) {
            channelLoader.load();
            List<Channel> channels = channelLoader.getChannels();
            List<ItemCollector> channelCollectors = new ArrayList<ItemCollector>();
            for (Channel channel : channels) {
                RSSChannelCollector channelCollector = new RSSChannelCollector(channel.getUnparsedChannelURL(),
                        channel.getTags(), channel.getPollFrequencyInSeconds());
                channelCollectors.add(channelCollector);
            }// //FIXME

            FlowEngine.getInstance().addCollectors(channelCollectors);
            FlowEngine.getInstance().addCollector(new TopNewsSweden());
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("doPost() StartupServlet not implemented");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("doGet() StartupServlet not implemented");
    }
}
