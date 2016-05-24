package com.itemstore.admin.impl;

import com.itemstore.engine.ItemEngine;
import com.itemstore.admin.Action;
import com.itemstore.admin.dto.ChannelDTO;
import com.itemstore.collector.loader.rss.Channel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ChannelListAllAction extends Action {
    @Override
    protected Object execute(HttpServletRequest request) {

        List<Channel> channels = null; //FIXMEItemEngine.getInstance().getChannels();

        return ChannelDTO.map(channels);
    }
}
