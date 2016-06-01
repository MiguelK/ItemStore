package com.itemstore.collector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

abstract class HTMLDocumentBase extends ItemCollectorBase {
    Document get(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return doc;
    }

    @Override
    public int getPollFrequencyInSeconds() {
        return 15;
    }
}

