package com.itemstore.service.datastore;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itemstore.engine.model.SnapShotItemGroups;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceDataStorageDisk implements ServiceDataStorage {
    private final static Logger LOG = Logger.getLogger(ServiceDataStorageDisk.class.getName());

    private final File homeDirectory;

    ServiceDataStorageDisk() {
        this(new LocatorProduction().getHomeDirectory());
    }

    public ServiceDataStorageDisk(File homeDirectory) {
        if (homeDirectory == null) {
            throw new IllegalArgumentException("homeDirectory is null");
        }
        if (!homeDirectory.isDirectory()) {
            throw new IllegalArgumentException("homeDirectory is not a dir " + homeDirectory.getAbsolutePath());
        }
        this.homeDirectory = homeDirectory;

    }

    @Override
    public void save(SnapShotItemGroups snapShotItemGroups) {

        LOG.info("Saving SnapShot to " + homeDirectory.getAbsolutePath() +
                " ItemGroups=" + snapShotItemGroups.getAllItemGroupsSortedByDate().size());

        SnapShotVersion snapShotVersion = SnapShotVersion.create(homeDirectory);

        //saveAsObject(snapShotItemGroups, homeDirectory);
        File json = saveAsJSON(snapShotItemGroups, snapShotVersion);

        ZipFile.zip(json, snapShotVersion.getLangJSONZipped());
    }


    @Override
    public File getHomeDirectory() {
        return homeDirectory;
    }

    @Override
    public SnapShotVersion getCurrentVersion() {
        return SnapShotVersion.load(homeDirectory);
    }


    private void saveAsObject(Object object, File targetFile) {
        FileOutputStream fileOut = null;
        ObjectOutputStream out = null;
        try {
            fileOut =
                    new FileOutputStream(targetFile);
            out = new ObjectOutputStream(fileOut);
            out.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
            LOG.log(Level.SEVERE, "Unable to save object " + object + " to = " + targetFile.getAbsolutePath(), e);
        } finally {
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(fileOut);
        }
    }

    private static final Gson GSON;// = new Gson();

    static {
        List<String> fieldExclusions = new ArrayList<String>();
        fieldExclusions.add("podCastEpisodesInternal");

        List<Class<?>> classExclusions = new ArrayList<Class<?>>();
       // classExclusions.add(PodCast.class);
        GSON = GsonFactory.build(fieldExclusions, classExclusions);
    }

    public static class GsonFactory {

        public static Gson build(final List<String> fieldExclusions, final List<Class<?>> classExclusions) {
            GsonBuilder b = new GsonBuilder();
            b.addSerializationExclusionStrategy(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    return fieldExclusions == null ? false : fieldExclusions.contains(f.getName());
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return classExclusions == null ? false : classExclusions.contains(clazz);
                }
            });
            return b.create();

        }
    }

    private File saveAsJSON(SnapShotItemGroups snapShotItemGroups, SnapShotVersion versionDirectory) {

        try {
            try (Writer writer = new OutputStreamWriter(new FileOutputStream(versionDirectory.getLangJSON()), "UTF-8")) {
                GSON.toJson(snapShotItemGroups, writer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return versionDirectory.getLangJSON();
    }

}
