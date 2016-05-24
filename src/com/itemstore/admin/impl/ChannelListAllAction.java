package com.itemstore.admin.impl;

import com.itemstore.engine.FlowEngine;
import com.itemstore.admin.Action;
import com.itemstore.admin.dto.ChannelDTO;
import com.itemstore.engine.loader.rss.Channel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ChannelListAllAction extends Action {
    @Override
    protected Object execute(HttpServletRequest request) {

        List<Channel> channels = FlowEngine.getInstance().getChannels();

        return ChannelDTO.map(channels);
    }
}
