package com.itemstore.admin.impl;

import com.itemstore.admin.Action;
import com.itemstore.admin.dto.ChannelDTO;
import com.itemstore.collector.rss.RSSChannels;
import com.itemstore.commons.EngineConf;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

public class ChannelListAllAction extends Action {
    @Override
    protected Object execute(HttpServletRequest request) {

        File channelFile = EngineConf.getInstance().getChannelsFile(request.getServletContext(),"channels_swe.xml");

        List<com.itemstore.collector.rss.Channel> channels = RSSChannels.loadFromFile(channelFile).getChannels();

        return ChannelDTO.map(channels);
    }
}
