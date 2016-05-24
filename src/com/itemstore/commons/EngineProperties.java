package com.itemstore.commons;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EngineProperties {
    private static final String ENGINE_CORE_THREAD_POOL_SIZE_KEY = "engine.core.ThreadPoolSize";
    private static final String ENGINE_CORE_ITEMS_FILE_KEY = "engine.core.ItemsFile";

    private static final String WEB_INF_CONF_PATH = "conf/%s";

    private Properties properties;

    private EngineProperties() {
    }

    static EngineProperties loadProperties(String propertyFileName) {
        EngineProperties engineProperties = new EngineProperties();

        engineProperties.load(propertyFileName);
        return engineProperties;
    }

    private void load(String propertyFileName) {

        InputStream in = null;
        try {
            in = EngineConf.getInstance().getResourceAsStreamFromWebInf(String.format(WEB_INF_CONF_PATH, propertyFileName));
            properties = new Properties();
            properties.load(in);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    public int getThreadPoolSize() {
        return Integer.parseInt(properties.getProperty(ENGINE_CORE_THREAD_POOL_SIZE_KEY));
    }



    public File getItemsFilePath() {
        File file = new File(properties.getProperty(ENGINE_CORE_ITEMS_FILE_KEY));

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileValidator.validateFile(file, "Invalid Items file " + ENGINE_CORE_ITEMS_FILE_KEY);
        return file;
    }

}