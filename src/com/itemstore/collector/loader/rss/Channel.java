package com.itemstore.collector.loader.rss;

import com.itemstore.commons.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class Channel {
    private static final String DELIMITER = ";;";

    private static final Logger logger = Logger.getLogger(Channel.class.getName());
    private static final int DEFAULT_POLL_FREQUENCY_SECONDS = 15;

    private URL channelURL;
    private final String unparsedChannelURL;
    private final List<String> tags;
    private final int pollFrequencyInSeconds;

    private Channel(String unparsedChannelURL, List<String> tags, int pollFrequencyInSeconds) {
        this.unparsedChannelURL = StringUtils.trimToNull(unparsedChannelURL);
        this.tags = tags;
        this.pollFrequencyInSeconds = pollFrequencyInSeconds <= 0 ? DEFAULT_POLL_FREQUENCY_SECONDS : pollFrequencyInSeconds;
    }

    public int getPollFrequencyInSeconds() {
        return pollFrequencyInSeconds;
    }

    public static Channel newChannel(String unparsedChannelURL, List<String> tagFilter, int pollFrequencyInSeconds) {
        return new Channel(unparsedChannelURL, tagFilter, pollFrequencyInSeconds);
    }

    public static Channel newChannel(String unparsedChannelURL, List<String> tagFilter) {
        return new Channel(unparsedChannelURL, tagFilter, DEFAULT_POLL_FREQUENCY_SECONDS);
    }

    public boolean isValidURLSyntax() {
        if (channelURL == null) {
            try {
                channelURL = new URL(unparsedChannelURL);
            } catch (MalformedURLException e) {
                logger.fine(e.getMessage());
                return false;
            }
        }

        try {
            channelURL.toURI();
        } catch (URISyntaxException e) {
            return false;
        }

        return true;
    }


    private void createChannelUrlLazy() {
        if (channelURL == null) {
            try {
                channelURL = new URL(unparsedChannelURL);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static List<Channel> parseChannelRows(List<String> channelRow) {
        List<Channel> channels = new ArrayList<Channel>();

        for (String row : channelRow) {
            String[] split = row.split(DELIMITER);
            if (split.length != 3) {
                continue;
            }

            String url = StringUtils.trimToNull(split[0]); //FIXME mgic number
            List<String> tags = StringUtil.split(StringUtils.trimToNull(split[1]));
            int poll = Integer.parseInt(split[2]);

            channels.add(Channel.newChannel(url, tags, poll)); //FIXME poll
        }

        return channels;
    }

    @Override
    public String toString() {
        return unparsedChannelURL + DELIMITER + tags + DELIMITER + pollFrequencyInSeconds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Channel channel = (Channel) o;

        if (pollFrequencyInSeconds != channel.pollFrequencyInSeconds) return false;
        if (!unparsedChannelURL.equals(channel.unparsedChannelURL)) return false;
        return tags.equals(channel.tags);

    }

    public String getUnparsedChannelURL() {
        return unparsedChannelURL;
    }

    public List<String> getTags() {
        return tags;
    }

    @Override
    public int hashCode() {
        int result = unparsedChannelURL.hashCode();
        result = 31 * result + tags.hashCode();
        result = 31 * result + pollFrequencyInSeconds;
        return result;
    }
}
