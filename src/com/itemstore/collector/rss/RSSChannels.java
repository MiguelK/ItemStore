package com.itemstore.collector.rss;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@XmlRootElement(name = "RSSChannels")
public class RSSChannels {

    @XmlElementWrapper(name = "Channels")
    @XmlElement(name = "Channel")
    private List<Channel> channels = new ArrayList<>();

    @XmlElement(name = "ChannelGroup")
    private List<ChannelGroup> channelGroups = new ArrayList<>();

    public static RSSChannels loadFromFile(File file) {

        FileInputStream adrFile = null;
        try {
            adrFile = new FileInputStream(file);
            JAXBContext ctx = JAXBContext.newInstance(RSSChannels.class);
            Unmarshaller um = ctx.createUnmarshaller();
            RSSChannels unmarshal = (RSSChannels) um.unmarshal(adrFile);

            validate(unmarshal);

            return unmarshal;

        } catch (IOException | JAXBException e) {
            throw new RuntimeException(e);
        } finally {
            if (adrFile != null) {
                try {
                    adrFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void validate(RSSChannels unmarshal) {

        Set<URL> uniqueURLS = new HashSet<>();
        for (Channel channel : unmarshal.getChannels()) {
            channel.validate();//FIXME
            if (uniqueURLS.contains(channel.getUrl())){
                throw new RuntimeException("Duplicated channel found " + channel.getUrl());
            }
            uniqueURLS.add(channel.getUrl());
        }
    }

    public List<Channel> getChannels() {

        List<Channel> allChannels = new ArrayList<>();

        allChannels.addAll(channels);

        for (ChannelGroup channelGroup : channelGroups) {
            allChannels.addAll(channelGroup.getChannels());
        }


        return allChannels;
    }
}
