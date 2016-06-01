package com.itemstore;

import java.io.File;
import java.net.URISyntaxException;

public class TestUtil {

    public static File readFile(String fileName) {
        java.net.URL url = TestUtil.class.getResource("/resources/" + fileName);
        try {
            java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
            return resPath.toFile();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }
}
