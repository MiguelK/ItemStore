package com.itemstore.engine.loader.topnews;

import com.itemstore.model.Item;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class TopNewsTest {

    @Test
    public void getItemsSwden() {
        TopNews topNews = new TopNewsSweden();
        List<Item> items = topNews.getItems();

        for (Item item : items) {
            System.out.println(item);
        }

//        Assert.assertTrue(items.size() > 0);

    }

    //http://www.geonames.org/about.html
    //   @Test
    public void dfdff() throws IOException {
//        String url = "http://www.dn.se";
        String url = "http://www.aftonbladet.se";    //class abS60
        print("Fetching %s...", url);

        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");

//        Elements cls1 = doc.getElementsByClass("abS60");       //abS51
//        Elements cls1 = doc.getElementsByClass("abS51");       //abS51
//        Elements cls1 = doc.getElementsByClass("abStreamerExtraImage");       //abS51
        Elements cls1 = doc.getElementsByClass("abItemHLine");       //abS51
        for (Element element : cls1) {
            Elements extra = element.getElementsContainingText("EXTRA");
            Elements jusNow = element.getElementsContainingText("JUST NU");
//            System.out.println("Class **** =" +   element.text());
            if (StringUtils.isNotEmpty(extra.text())) {
                System.out.println(" EXTRA Class **** =" + extra.text());
            }
            if (StringUtils.isNotEmpty(jusNow.text())) {
                System.out.println("JUST NU Class **** =" + jusNow.text());
            }
        }

//        Elements dansk = doc.getElementsContainingText("Dansk");
        Elements dansk = doc.getElementsByTag("h2");
        for (Element element : dansk) {
            System.out.println("H2 **** =" + element.text());
        }
        Elements h1 = doc.getElementsByTag("h1");
        for (Element element : h1) {
            System.out.println("H1 **** =" + element.text());
        }


        print("\nMedia: (%d)", media.size());
        for (Element src : media) {
            if (src.tagName().equals("img"))
                print(" * %s: <%s> %sx%s (%s)",
                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                        trim(src.attr("alt"), 20));
            else
                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
        }

        print("\nImports: (%d)", imports.size());
        for (Element link : imports) {
            print(" * %s <%s> (%s)", link.tagName(), link.attr("abs:href"), link.attr("rel"));
        }

        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
        }

    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width - 1) + ".";
        else
            return s;
    }


}