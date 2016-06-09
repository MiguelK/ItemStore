package com.itemstore;

import com.itemstore.collector.ItemCollector;
import com.itemstore.collector.TopNewsSwedenCollector;
import com.itemstore.collector.rss.RSSChannelCollector;
import com.itemstore.collector.web.TopNewsSweden;
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

        List<ItemCollector> channelCollectors = RSSChannelCollector.parseFile(channelFile);

        ItemEngine.getInstance().addCollectors(channelCollectors);
      //  ItemEngine.getInstance().addCollector(new TopNewsSwedenCollector()); //FIXME

        ItemEngine.getInstance().start();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("doPost() StartupServlet not implemented");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("doGet() StartupServlet not implemented");
    }
}
