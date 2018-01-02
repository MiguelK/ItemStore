package com.itemstore;

import com.itemstore.collector.ItemCollector;
import com.itemstore.collector.TopNewsSwedenCollector;
import com.itemstore.collector.rss.RSSChannelCollector;
import com.itemstore.collector.web.TopNewsSweden;
import com.itemstore.commons.EngineConf;
import com.itemstore.engine.ItemEngine;
import com.pusher.ChannelSubscriptionService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class StartupServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(StartupServlet.class.getName());

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);


        LOG.info("Starting PushServer...");

        //FIXME Move to Game API
        ChannelSubscriptionService.getInstance().addSubscriber("device_token", "match1");




       /* File channelFileSwe = EngineConf.getInstance().getChannelsFile(servletConfig.getServletContext(), "channels_swe.xml");
        List<ItemCollector> channelCollectorsSwe = RSSChannelCollector.parseFile(channelFileSwe);
        ItemEngine.getInstance().addCollectors(channelCollectorsSwe);

        LOG.info("Starting ItemStore SWE channelCollectorsSwe=" + channelCollectorsSwe.size());

        File channelFileEng = EngineConf.getInstance().getChannelsFile(servletConfig.getServletContext(), "channels_eng.xml");
        List<ItemCollector> channelCollectorsEng = RSSChannelCollector.parseFile(channelFileEng);
        ItemEngine.getInstance().addCollectors(channelCollectorsEng);

        LOG.info("Starting ItemStore ENG channelCollectorsSwe=" + channelCollectorsEng.size());

        ItemEngine.getInstance().addCollector(new TopNewsSwedenCollector()); //FIXME

        ItemEngine.getInstance().start(); */
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("doPost() StartupServlet not implemented");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("doGet() StartupServlet not implemented");
    }
}
