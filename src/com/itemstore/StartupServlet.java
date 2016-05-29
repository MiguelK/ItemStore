package com.itemstore;

import com.itemstore.collector.ItemCollector;
import com.itemstore.collector.RSSCollector;
import com.itemstore.collector.loader.topnews.TopNewsSweden;
import com.itemstore.commons.EngineConf;
import com.itemstore.engine.ItemEngine;

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

        LOG.info("Starting ItemStore...");

        File channelFile = EngineConf.getInstance().getChannelsFile(servletConfig.getServletContext());
        LOG.info("Start loading RSSChannels from file=" + channelFile.getAbsolutePath());

        //Register collectors, crawls Internet for Item(s)
        List<ItemCollector> rssChannelCollectors = RSSCollector.load(channelFile);
        ItemEngine.getInstance().addCollectors(rssChannelCollectors);
        ItemEngine.getInstance().addCollector(new TopNewsSweden());

        ItemEngine.getInstance().start();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("doPost() StartupServlet not implemented");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("doGet() StartupServlet not implemented");
    }
}
