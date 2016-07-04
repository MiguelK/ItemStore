package com.itemstore.collector;

import com.itemstore.engine.model.Item;

import com.itemstore.engine.model.tag3.ItemTagTree;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopNewsSwedenCollector extends HTMLDocumentBase {
    private static final String URL_AFTONBLADET_SE = "http://www.aftonbladet.se";
    private static final String URL_EXPRESSEN_SE = "http://www.expressen.se";

    @Override
    public List<Item> collect() {
        List<Item> items = new ArrayList<Item>();
        items.addAll(parseAftonbladet(get(URL_AFTONBLADET_SE)));
        // items.addAll(parseExpressen(get(URL_EXPRESSEN_SE)));

        return items;
    }

    private List<Item> parseAftonbladet(Document document) {
        if (document == null) {
            return Collections.emptyList();
        }

        List<Item> items = new ArrayList<Item>();
        //abItemHLine

        //Elements article = document.getElementsByTag("h2");
        Elements article = document.getElementsByTag("article");

        //all article + h2 contains just nu or extra

        //abPfxPrimary abIconArrow
        for (Element element : article) {
            Elements h2 = element.getElementsByTag("h2");

            int size = h2.size();
            // System.out.println("size == " + size + " " + element.text());

            //System.out.println("SSSS " + element.getElementsContainingText("JUST NU").text());

            String text = StringUtils.trimToEmpty(element.text());

            boolean plusArticle = text.contains("PLUS") || text.contains("Plus");

            //System.out.println(plusArticle + ", " + size + " AAA **** " + text);

            if (!plusArticle && size > 0 && StringUtils.isNotEmpty(text) &&
                    text.contains("JUST NU") || text.contains("EXTRA")) {
                Item.Builder builder = new Item.Builder();
                String breakingNewsImage = "http://www.esportsm.se/images/4669-extra.jpg";
                builder.imageURL1(breakingNewsImage)
                        .publishedDate(LocalDateTime.now()).
                        sourceURL(URL_AFTONBLADET_SE).
                        targetURL(URL_AFTONBLADET_SE).
                        itemTagTree(new ItemTagTree.Builder("swe_news").build()).
                        title(text);
                //System.out.println("**** " + text);
                items.add(builder.build());
            }
        }


      /*  Elements cls1 = document.getElementsByClass("abItemHLine");       //abS51
        for (Element element : cls1) {
            Elements extra = element.getElementsContainingText("EXTRA");
            Elements jusNow = element.getElementsContainingText("JUST NU");

            if (StringUtils.isNotEmpty(extra.text())) {
                Item.Builder builder = new Item.Builder();
                builder.sourceURL(URL_AFTONBLADET_SE).title(extra.text());
                items.add(builder.build());
            }
            if (StringUtils.isNotEmpty(jusNow.text())) {
                Item.Builder builder = new Item.Builder();
                System.out.println("***** " + jusNow.first().text());
                builder.sourceURL(URL_AFTONBLADET_SE).title(jusNow.text());
                items.add(builder.build());
            }
        }
 */
        return items;
    }

    private List<Item> parseExpressen(Document document) {
        if (document == null) {
            return Collections.emptyList();
        }


        //b-headline b-headline_ateaser b-headline_normal-big b-headline_big-headline

        List<Item> items = new ArrayList<Item>();

        Elements cls1 = document.getElementsByClass("b-headline");       //abS51
        for (Element element : cls1) {
            //   Elements extra = element.getElementsContainingText("EXTRA");
            if (StringUtils.isNotEmpty(element.text())) {
                Item.Builder builder = new Item.Builder().itemTagTree(new ItemTagTree.Builder("TOP_NEWS_SWE").build())
                        .sourceURL(URL_EXPRESSEN_SE).title(element.text());
                items.add(builder.build());
            }
        }

        return items;
    }
}

