package com.flow.admin.impl;

import com.flow.engine.FlowEngine;
import com.flow.admin.Action;
import com.flow.admin.dto.ChannelDTO;
import com.flow.engine.loader.rss.Channel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ChannelListAllAction extends Action {
    @Override
    protected Object execute(HttpServletRequest request) {

        List<Channel> channels = FlowEngine.getInstance().getChannels();

        return ChannelDTO.map(channels);
    }
}
