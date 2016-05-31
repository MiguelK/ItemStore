package com.itemstore.admin.impl;

import com.itemstore.admin.Action;

import javax.servlet.http.HttpServletRequest;

public class ChannelReloadAction extends Action {

    @Override
    protected Object execute(HttpServletRequest request) {
        //FIXME ItemEngine.getInstance().reloadChannels();
        //return ChannelDTO.map(channels);
        throw new UnsupportedOperationException();
    }
}
