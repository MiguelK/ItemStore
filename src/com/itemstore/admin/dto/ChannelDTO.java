package com.itemstore.admin.dto;


import com.itemstore.collector.rss.Channel;

import java.util.ArrayList;
import java.util.List;

public class ChannelDTO {

    private String name;


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
