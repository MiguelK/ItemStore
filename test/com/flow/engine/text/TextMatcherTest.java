package com.flow.engine.text;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TextMatcherTest {

    @Test
    public void match_all_3() throws Exception {
        String test1 ="Kropp hittad utanför Torsåker ";
        String test2 ="Kvarlevor av en kropp hittad i Torsåker";
        String test3 ="Resterna efter en död person har hittats i närheten av Torsåker i Gävleborgs län,";

        TextMatcher m1 = TextMatcher.getMatcher(test1);
        TextMatcher m2 = TextMatcher.getMatcher(test2);
        TextMatcher m3 = TextMatcher.getMatcher(test3);

        Assert.assertTrue(m1.match(m2));
        Assert.assertTrue(m1.match(m3));
        Assert.assertTrue(m1.match(m1));

        Assert.assertTrue(m2.match(m2));
        Assert.assertTrue(m2.match(m1));
        Assert.assertTrue(m2.match(m3));

        Assert.assertTrue(m3.match(m3));
        Assert.assertTrue(m3.match(m1));
        Assert.assertTrue(m3.match(m2));
    }

    /*@Test
    public void verifySwedishMatcher()  {
        TextMatcher m2 = TextMatcher.getMatcher("asas", Language.Swe);

        //Assert.assertTrue(m2 instanceof SwedishTextAnalyzer);


    }*/
}