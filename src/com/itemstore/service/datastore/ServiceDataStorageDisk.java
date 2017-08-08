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

    private final File podDataHomeDir;

    ServiceDataStorageDisk() {
        this(new LocatorProduction().getHomeDirectory());
    }

    public ServiceDataStorageDisk(File podDataHomeDir) {
        if (podDataHomeDir == null) {
            throw new IllegalArgumentException("podDataHomeDir is null");
        }
        if (!podDataHomeDir.isDirectory()) {
            throw new IllegalArgumentException("podDataHomeDir is not a dir " + podDataHomeDir.getAbsolutePath());
        }
        this.podDataHomeDir = podDataHomeDir;

    }

    @Override
    public void save(SnapShotItemGroups snapShotItemGroups) {

        LOG.info("Saving PodCastCatalog to " + podDataHomeDir.getAbsolutePath());

        SnapShotVersion snapShotVersion = SnapShotVersion.create(podDataHomeDir);

        saveAsObject(snapShotItemGroups, podDataHomeDir);
        File json = saveAsJSON(snapShotItemGroups, snapShotVersion);

        ZipFile.zip(json, snapShotVersion.getLangJSONZipped());
    }

    public File getPodDataHomeDir() {
        return podDataHomeDir;
    }

    @Override
    public Optional<SnapShotVersion> getCurrentVersion() {

        SnapShotItemGroups snapShotItemGroups = load(podDataHomeDir, SnapShotItemGroups.class);

        //SnapShotVersion.create()

        return null;
    }


    private <T> T load(File sourceFile, Class<T> sourceType) {

        ObjectInputStream in = null;
        FileInputStream fileIn = null;
        try {
            try {
                fileIn = new FileInputStream(sourceFile);
                in = new ObjectInputStream(fileIn);
                return ((T) in.readObject());
            } catch (IOException | ClassNotFoundException e) {
                LOG.log(Level.INFO, "Unable to load object=" + sourceType + " from=" + sourceFile.getAbsolutePath(), e.getMessage());
            }

        } finally {
            if (in != null) {
                IOUtils.closeQuietly(in);
            }
            if (fileIn != null) {
                IOUtils.closeQuietly(fileIn);
            }
        }
        return null;
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
