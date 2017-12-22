package com.itemstore.service.datastore;

import java.io.File;

public class LocatorProduction implements HomeDirectoryLocator {

    private static final String ITEM_STORE_DATA_HOME = "ITEM_STORE_DATA_HOME";//Must exist before starting app! //FIXME

    @Override
    public File getHomeDirectory() {

        File   homeDirectory = new File("/tmp/", ITEM_STORE_DATA_HOME);

        if (!homeDirectory.exists()) {
            if (!homeDirectory.mkdirs()) {
                throw new IllegalStateException("Unable to create dirs " + homeDirectory.getAbsolutePath());
            }
        }

        if (isReadAndWriteDirectory(homeDirectory)) {
            return homeDirectory;

        }

        throw new IllegalStateException("Unable to locate home directory " + homeDirectory.getAbsolutePath());

    }

    private boolean isReadAndWriteDirectory(File file) {
        return file.isDirectory() && file.canRead() && file.canWrite();
    }
}
