package com.flow.commons;

import junit.framework.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class FileValidatorTest {

    @Test
    public void isInvalidFile_true() {
        Assert.assertTrue(FileValidator.isInvalidFile(null));
        Assert.assertTrue(FileValidator.isInvalidFile(new File("C:\\hjhsjahj\\jasjhajs")));
        Assert.assertTrue(FileValidator.isInvalidFile(new File("C:\\hjhsjahj\\testzxz.txt")));
        Assert.assertTrue(FileValidator.isInvalidFile(new File(System.getProperty("java.io.tmpdir"))));
    }

    /*@Test
    public void isInvalidFile_false() {
        String s = "/Users/miguelkrantz/Documents/Flow/FlowServer/test/com/flow/commons/" + FileValidatorTest.class.getSimpleName() + ".java";
        Assert.assertFalse(FileValidator.isInvalidFile(new File(s)));

    }*/
}