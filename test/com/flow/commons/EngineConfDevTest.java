package com.flow.commons;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EngineConfDevTest {

    @BeforeMethod
    public void setUp() {
        EngineConf.useDevProfile();
    }

    @Test
    public void poolSize_ProdProfile() {
     //   Assert.assertEquals(EngineConf.getInstance().getThreadPoolSize(), 25);
    }
    @Test
    public void validImageCaleDir() {
        //    Assert.assertTrue(EngineConf.getInstance().getImageScaleDir().exists());
        //   Assert.assertTrue(EngineConf.getInstance().getImageScaleDir().isDirectory());
    }
}