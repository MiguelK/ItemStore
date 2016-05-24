package com.flow.engine.text.swedish;

import com.flow.engine.text.TextAnayzier;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SwedishTextAnalyzerTest {

    @Test
    public void parseEmptyText() {
        Assert.assertTrue(SwedishTextAnalyzer.create("  ").getWords().isEmpty());
    }

    @Test
    public void parseNullyText() {
        Assert.assertTrue(SwedishTextAnalyzer.create(null).getWords().isEmpty());
    }


    @Test
    public void equalWord_1() {
        TextAnayzier m1 = SwedishTextAnalyzer.create("Glad ");
        TextAnayzier m2 = SwedishTextAnalyzer.create("munter");

        Assert.assertEquals(m1.getWords().get(0), m2.getWords().get(0));
    }

    @Test
    public void match_short_text() {
        TextAnayzier m1 = SwedishTextAnalyzer.create("Faarsvunnen ettåring hittad i Umåalven");
        TextAnayzier m2 = SwedishTextAnalyzer.create("Ettåringen är hittad livlaas");

        Assert.assertTrue(m1.isSimilarText(m2)); //ettåring(synonym), hittad(Lika) = 2
        Assert.assertEquals(m1.getSimilarTextCount(m2), 2); //ettåring(synonym), hittad(Lika) = 2

         m1 = SwedishTextAnalyzer.create("Italienska experter tippar ? där spelar Ibrahimovic i höst");
         m2 = SwedishTextAnalyzer.create("Zlatans avstängning kortas");

        Assert.assertTrue(m1.isSimilarText(m2));
        Assert.assertEquals(m1.getSimilarTextCount(m2), 1); //Zlatan(synonym)

    }

    @Test
    public void detectSwedisText() {
        String text = "Kropp hittad utanf?r Tors?ker ";
//        TextAnayzier m1 = new SwedishTextAnalyzer(test1);
//          m1.isSwedishText()

    }

    @Test
    public void isMatch_true() {
        String test1 = "Kropp hittad utanör Torsåker ";
        String test2 = "Kvarlevor av en kropp hittad i Torsåker";
        String test3 = "Resterna efter en död person har hittats i närheten av Torsåker i Gävleborgs län,";

        TextAnayzier m1 = SwedishTextAnalyzer.create(test1);
        TextAnayzier m2 = SwedishTextAnalyzer.create(test2);
        TextAnayzier m3 = SwedishTextAnalyzer.create(test3);

        isMatch(m1, m2, m3);
    }

    @Test(enabled = false)
    public void isMatch_false() {
        String test1 = "The Filip & Fredrik podcast, episode 2 (14 Aug 2014) The post Ebola chicken race appeared first on Filip & Fredrik ";
        String test2 = "Jag skrev i går om hur jag inte varit förkyld på evigheter, och tror ni inte att jag vaknade i morse och kände mig tjock i halsen och lite rinnig i snoken? Vad fasen. Jag kanske kom att tänka på det för att något var fel, " +
                "men jag hoppas att det inte utvecklas till ? Fortsätt läsa » Inlägget Jinx?! dök först upp på Arga Klara";
        String test3 = "Resterna efter en död person har hittats i närheten av Torsöker i Gävleborgs län,";

        TextAnayzier m1 = SwedishTextAnalyzer.create(test1);
        TextAnayzier m2 = SwedishTextAnalyzer.create(test2);
        TextAnayzier m3 = SwedishTextAnalyzer.create(test3);

        isNoMatch(m1, m2, m3);
    }

    private void isNoMatch(TextAnayzier m1, TextAnayzier m2, TextAnayzier m3) {

        Assert.assertFalse(m1.isSimilarText(m2));
        Assert.assertFalse(m1.isSimilarText(m3));

        Assert.assertFalse(m2.isSimilarText(m1));
        Assert.assertFalse(m2.isSimilarText(m3));

        Assert.assertFalse(m3.isSimilarText(m1));
        Assert.assertFalse(m3.isSimilarText(m2));
    }
    private void isMatch(TextAnayzier m1, TextAnayzier m2, TextAnayzier m3) {

        Assert.assertTrue(m1.isSimilarText(m2),m1 + "=" + m2);
        Assert.assertTrue(m1.isSimilarText(m3),m1 + "=" + m3);
        Assert.assertTrue(m1.isSimilarText(m1),m1 + "=" + m1);

        Assert.assertTrue(m2.isSimilarText(m2),m2 + "=" + m2);
        Assert.assertTrue(m2.isSimilarText(m1),m2 + "=" + m1);
        Assert.assertTrue(m2.isSimilarText(m3),m2 + "=" + m3);

        Assert.assertTrue(m3.isSimilarText(m3),m3 + "=" + m3);
        Assert.assertTrue(m3.isSimilarText(m1),m3 + "=" + m1);
        Assert.assertTrue(m3.isSimilarText(m2),m3 + "=" + m2);
    }
}