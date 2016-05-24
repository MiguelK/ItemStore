package com.itemstore.commons;

import com.itemstore.model.User;
import com.itemstore.model.tag.TagContainer;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.InputStream;
import java.util.Collections;
import java.util.logging.Logger;

/**
 * -DEngineProfile=DEV (otherwies, )
 * WEB-INF/config/engine-dev.properties
 * path to cards.xml
 * path to channels.etc
 * Image path
 * Threads
 * Build number+ version
 */
public class EngineConf {
    private static final Logger logger = Logger.getLogger(EngineConf.class.getName());

    private static final String DEV_PROFILE_KEY = "EngineProfileDev"; //Set -DEngineProfileDev=something
    private static final String DEV_PROPERTIES_FILE_NAME = "engine-dev.properties";
    private static final String PROD_PROPERTIES_FILE_NAME = "engine-prod.properties";

    private static final EngineConf INSTANCE = new EngineConf();
    private static final String CHANNELS_FILE_NAME = "channels.txt";

    private ServletContext servletContext;
    private int threadPoolSize;
    private File itemsFile;

    private final User tvAppOlsStockholmUser = new User();
    private final User testUser1 = new User();

    private EngineConf() {
        tvAppOlsStockholmUser.setId("2");
        tvAppOlsStockholmUser.addExcludeTag(TagContainer.EXCLUDE_ALL);
        tvAppOlsStockholmUser.addFavoriteTags(Collections.singletonList(TagContainer.TV_USER_OLD_STOCKHOLM));

        testUser1.setId("1");
        testUser1.addExcludeTag(TagContainer.TV_USER_OLD_STOCKHOLM);
        testUser1.addFavoriteTags(Collections.singletonList(TagContainer.TOP_NEWS_SWE));
        testUser1.addExcludeTag("Eng");
        testUser1.setFirstName("Test User_1");
    }

    public User getTvAppOlsStockholmUser() {
        return tvAppOlsStockholmUser;
    }

    public User getTestUser1() {
        return testUser1;
    }

    public static EngineConf getInstance() {
        return INSTANCE;
    }

    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    static void useDevProfile() {
        System.setProperty(DEV_PROFILE_KEY, "Any Value");
    }


    public File getItemsFile() {
        return itemsFile;
    }


    private void printConf() {
        logger.info("EngineConf values {");
        logger.info("ThreadPool Size : " + getThreadPoolSize());
        logger.info("ItemsFile Size : " + getItemsFile().getAbsolutePath());

        logger.info("}");
    }


    public InputStream getResourceAsStreamFromWebInf(String path) {
        return servletContext.getResourceAsStream("WEB-INF/" + path);
    }


    public File getChannelsFile(ServletContext servletContext) {
        String realPath = servletContext.getRealPath("/" + CHANNELS_FILE_NAME);
        File file = new File(realPath);
        FileValidator.validateFile(file, String.format("Unable to read Channels %s file", CHANNELS_FILE_NAME));
        return file;
    }

}
