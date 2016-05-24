package com.itemstore;

import com.itemstore.collector.RSSCollector;
import com.itemstore.collector.loader.topnews.TopNewsSweden;
import com.itemstore.engine.ItemEngine;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class StartupServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(StartupServlet.class.getName());

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        LOG.info("Starting ItemStore...");

        //Register collectors, crawls Internet for Item(s)
        ItemEngine.getInstance().addCollectors(RSSCollector.load(config));
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
