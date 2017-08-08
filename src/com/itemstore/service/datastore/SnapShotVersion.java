package com.itemstore.service.datastore;

import com.itemstore.engine.model.SnapShotItemGroups;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SnapShotVersion {
    private final File langJSON;
    private final File langJSONZipped;
    private final File langDat;

    private transient SnapShotItemGroups podCastCatalog; //Only load latest version in-memory

    private SnapShotVersion(File versionRoot) {
        langDat = new File(versionRoot, "items.dat");
        langJSON = new File(versionRoot, "items.json");
        langJSONZipped = new File(versionRoot, "items_json.zip");
 }

    private void make() {
        try {
            langDat.createNewFile();
            langJSON.createNewFile();
            langJSONZipped.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SnapShotVersion create(File versionRoot) {
        SnapShotVersion snapShotVersion = new SnapShotVersion(versionRoot);
        snapShotVersion.make();
        return snapShotVersion;
    }

    static SnapShotVersion load(File versionRoot) {
        return new SnapShotVersion(versionRoot);
    }

    /*
    void loadPodCastCatalogFromDisc() {

        ObjectInputStream in = null;
        FileInputStream fileIn = null;
        try {
            fileIn = new FileInputStream(langDat);
            in = new ObjectInputStream(fileIn);
            podCastCatalog = ((SnapShotItemGroups) in.readObject());
        } catch (Exception e) {

            throw new RuntimeException("Unable to read PodCastCatalog " + langDat.getAbsolutePath(), e);
        } finally {
            if (in != null) {
                IOUtils.closeQuietly(in);
            }
            if (fileIn != null) {
                IOUtils.closeQuietly(fileIn);
            }
        }
    }
*/

    public File getLangJSON() {
        return langJSON;
    }

    public File getLangJSONZipped() {
        return langJSONZipped;
    }

    @Override
    public String toString() {
        return "SnapShotVersion{" +
                ", langJSON=" + langJSON +
                ", langJSONZipped=" + langJSONZipped +
                ", langDat=" + langDat +
                ", podCastCatalog=" + podCastCatalog +
                '}';
    }
}
