package com.flow.engine.loader;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Loader {

    private static final Logger logger = Logger.getLogger(Loader.class.getName());

    public enum StorageType {Serializable, TextRows, None}

    private File sourceFile;
    private StorageType storageType;

    public void setLoadSource(File sourceFile, StorageType storageType) {
        logger.info(getClass().getSimpleName() + " Loader from " + sourceFile.getAbsolutePath() + " StorageType=" + storageType);

        this.sourceFile = sourceFile;
        this.storageType = storageType;

        createFileIfNotExist(sourceFile);
    }

    private void createFileIfNotExist(File sourceFile) {
        if (!sourceFile.exists()) {
            try {
                logger.info(getClass().getSimpleName() + " Creating new loader file " + sourceFile);
                sourceFile.createNewFile(); //FIXME
            } catch (IOException e) {
                throw new RuntimeException("Unable to create file " + sourceFile.getAbsolutePath(), e);
            }
        }
    }

    public final void load() {
        if (sourceFile == null) {
            logger.warning("No sourceFile for channel loader");
            return;
        }

        switch (storageType) {
            case TextRows:
                List<String> rows = loadTextFile();
                handleLoadedTextRows(rows);
                break;
            case Serializable:
                Object serialData = loadSerialData();
                if (serialData != null) {
                    handledLoadedDate(serialData);
                }
                break;
            case None:
                logger.info("Loader is not persistent");
            default:
                throw new IllegalStateException("");

        }

        logger.fine("Load from disk.." + getClass().getSimpleName());
    }

    protected void handleLoadedTextRows(List<String> rows) {
    }

    private Collection<?> getRowsToSave() {
        return Collections.emptyList();
    }

    protected void handledLoadedDate(Object object) {
    }

    protected Object getData() {
        throw new UnsupportedOperationException();
    }


    public final void save() {
        if (sourceFile == null) {
            logger.warning("No sourceFile for channel loader");
            return;
        }

        switch (storageType) {
            case TextRows:
                Collection<?> rowsToSave = getRowsToSave();
                if (rowsToSave.size() > 0) {
                    saveRows(rowsToSave);
                }
                break;
            case Serializable:
                Object data = getData();
                if (data != null) {
                    saveObject(data);
                }

                break;
            case None:
                logger.info("Loader is not persistent");
            default:
                throw new IllegalStateException("");

        }

        logger.fine("Store to dsik");
    }

    private void saveObject(Object data) {
        logger.fine("About to save object of type " + data.getClass());
        ObjectOutputStream objectOutputStream = null;

        try {
            objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(sourceFile));

            objectOutputStream.writeObject(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(objectOutputStream);
        }

    }

    private Object loadSerialData() {

        ObjectInputStream objectInputStream = null;
        try {
            logger.info("Try reading " + sourceFile.getAbsolutePath());
            objectInputStream = new ObjectInputStream(
                    new FileInputStream(sourceFile));


            return objectInputStream.readObject();
        } catch (EOFException eof) {
            logger.info("Empty file " + sourceFile.getAbsolutePath());
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "file " + sourceFile.getAbsolutePath(), e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "file " + sourceFile.getAbsolutePath(), e);
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "file " + sourceFile.getAbsolutePath(), e);
        } finally {
            IOUtils.closeQuietly(objectInputStream);
        }
        return null;

    }

    private List<String> loadTextFile() {
        FileInputStream inputStream = null;
        try {
            logger.info("Loading file " + sourceFile.getAbsolutePath());
            inputStream = new FileInputStream(sourceFile);
            return IOUtils.readLines(inputStream);
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "Can not load sourceFile " + sourceFile.getAbsolutePath(), e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Can not load sourceFile " + sourceFile.getAbsolutePath(), e);
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    private void saveRows(Collection<?> rowsToSave) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(sourceFile);
            IOUtils.writeLines(rowsToSave, null, outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(outputStream);
        }
    }


}
