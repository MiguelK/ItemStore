package com.itemstore;

import java.io.File;

public class Config {

        private static final String OPENSHIFT_DATA_DIR = "OPENSHIFT_DATA_DIR";

        private static final String PODDA_HOME_DIR = "POD_DATA_HOME";//Must exist before starting app! //FIXME



        public File getPodDataHomeDirectory() {

            /*File podDataHomeDir = new File("/home/krantmig/tools/temp" + File.separator + PODDA_HOME_DIR);
        if (isReadAndWriteDirectory(podDataHomeDir)) {
            return podDataHomeDir;
        }

        podDataHomeDir = new File("/Users/miguelkrantz/Documents/temp/" + File.separator + PODDA_HOME_DIR);
        if (isReadAndWriteDirectory(podDataHomeDir)) {
            return podDataHomeDir;
        }*/

            //String openShiftDataDir = System.getenv(OPENSHIFT_DATA_DIR);

            //if (openShiftDataDir != null) {/wildfly/standalone/ /var/run/docker.sock
            File   podDataHomeDir = new File("/tmp/", PODDA_HOME_DIR);
            //}

            if (!podDataHomeDir.exists()) {
                if (!podDataHomeDir.mkdirs()) {
                    throw new IllegalStateException("Unable to create dirs " + podDataHomeDir.getAbsolutePath());
                }
            }

            if (isReadAndWriteDirectory(podDataHomeDir)) {
                return podDataHomeDir;

            }

            throw new IllegalStateException("Unable to locate home directory");

        }

        private boolean isReadAndWriteDirectory(File file) {
            return file.isDirectory() && file.canRead() && file.canWrite();
        }
}
