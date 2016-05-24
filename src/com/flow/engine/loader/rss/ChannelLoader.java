package com.flow.engine.loader.rss;

import com.flow.engine.loader.Loader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class ChannelLoader extends Loader {

    private static final Logger logger = Logger.getLogger(ChannelLoader.class.getName());

    private final Set<Channel> channels = new HashSet<Channel>();

    private ChannelLoader() {
    }

    public static ChannelLoader create() {
        return new ChannelLoader();
    }

    @Override
    protected void handleLoadedTextRows(List<String> rows) {
        List<Channel> transform = Channel.parseChannelRows(rows);
        channels.addAll(transform);

    }

    public void addChannel(String url, List<String> tags, int pollFrequencyInSeconds) {
        channels.add(Channel.newChannel(url, tags, pollFrequencyInSeconds));
    }

    public List<Channel> getChannels() {
        return new ArrayList<Channel>(channels);
    }
}
