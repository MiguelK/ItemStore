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
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "RSSChannels")
public class RSSChannels {

    @XmlElementWrapper(name = "Channels")
    @XmlElement(name="Channel")
    private List<Channel> channels = new ArrayList<>();

    public static RSSChannels loadFromFile(File file) {

        FileInputStream adrFile = null;
        try {
            adrFile = new FileInputStream(file);
            JAXBContext ctx = JAXBContext.newInstance(RSSChannels.class);
            Unmarshaller um = ctx.createUnmarshaller();
            RSSChannels unmarshal = (RSSChannels) um.unmarshal(adrFile);

            for (Channel channel : unmarshal.getChannels()) {
                channel.validate();//FIXME
            }

            return unmarshal;

        }
        catch(IOException | JAXBException e) {
            throw  new RuntimeException(e);
        }finally {
            if(adrFile!=null){
                try {
                    adrFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Channel> getChannels() {
        return channels;
    }
}
