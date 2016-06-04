package com.itemstore.collector.web;

import com.itemstore.collector.ItemCollectorBase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


/**
 *

 API
 TopNewsResponse getTopNewsByRegion("Swe",lastResultId)
 -TopNewsResponse(resultId, int numberOfNewItems,List<Item> items)

 TopNews topNews = FlowEngine.getInstance().getTopNewsByRegion("Swe",lastResultId);
 - TopNewsCollector c;//running,Map<"Region", TopNews)
 -TopNews (resultId, int numberOfNewItems,List<Item> items)
 topNews.isEmpty(); //if nothing new has arrived, compared with passed in lastResultId
 return TopNewsResponse.map(topNews);
 *
 *
 * TopNewsCollecor.instance().regsiter(new SwedishTopNews()) //running...
 * TopNewsCollecor.instance().regsiter(new DefaultTopNews()) //running...
 * TopNewsCollecor.instance().start(15sec poll);
 * <p/>
 * abstract class TopNews
 * String getKey() //Sweden.Stockholm
 * List<Item> getItems();
 * void List<String> getUrls() //called from base class
 * <p/>
 * <p/>
 * TopNewsCollecor.instance().getByLocation("2323.33","2323.66") //Sweden
 * Map<String,List<Item>> data....key="Sweden" value = items
 * TopNews t= TopNews.getByLocation("")
 * GeoLocation.Sweden.
 * //headLine,source //Item
 * List<String> s = t.getItems(; //dn, afton,exp etc...
 */
public abstract class ItemCollectorWebBase extends ItemCollectorBase {

     Document get(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return doc;
    }

}
