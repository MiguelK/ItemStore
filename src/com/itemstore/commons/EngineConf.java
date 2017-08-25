package com.itemstore.commons;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.InputStream;
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

    private static final EngineConf INSTANCE = new EngineConf();
    private static final String CHANNELS_FILE_NAME = "channels.txt";

    private ServletContext servletContext;
    private int threadPoolSize;
    private File itemsFile;


    private EngineConf() {
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


    public InputStream getResourceAsStreamFromWebInf(String path) {
        return servletContext.getResourceAsStream("WEB-INF/" + path);
    }


    public File getChannelsFile(ServletContext servletContext, String fileName) {
        String realPath = servletContext.getRealPath("/" + fileName);
        File file = new File(realPath);
        FileValidator.validateFile(file, String.format("Unable to read Tag %s file", CHANNELS_FILE_NAME));
        return file;
    }

}
