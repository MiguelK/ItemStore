package com.itemstore.engine.collector;

import com.itemstore.model.Item;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class SvtTextTvCollector extends HTMLDocumentBase {


    //svt.se/svttext/tv/pages/100.html
    @Override
    public List<Item> getItems() {

        Document document = get("http://www.svt.se/svttext/tv/pages/100.html");


        //Y DH störst
        Elements root = document.getElementsByClass("root");

        Elements links = document.select("a[href]");
        Elements media = document.select("[src]");
        Elements imports = document.select("link[href]");



        for (Element link : imports) {
            print(" * %s <%s> (%s)", link.tagName(), link.attr("abs:href"), link.attr("rel"));
        }

        for (Element element : root) {
            element.setBaseUri("test");

        }


        System.out.println(root);
        return null;
    }

    @Override
    public int getPollFrequencyInSeconds() {
        return 20;
    }
}
