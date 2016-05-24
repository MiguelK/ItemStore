package com.flow.admin.dto;

import com.flow.engine.loader.rss.Channel;

import java.util.ArrayList;
import java.util.List;

public class ChannelDTO {

    public String name;


    public static List<ChannelDTO> map(List<Channel> channels) {

        List<ChannelDTO> result = new ArrayList<ChannelDTO>();
        for (Channel channel : channels) {
            ChannelDTO e = new ChannelDTO();
            e.name = channel.toString(); //FIXME test
            result.add(e);

        }

        return result;
    }
}
