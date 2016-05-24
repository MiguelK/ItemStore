package com.itemstore.commons;

import java.io.File;

public class FileValidator {


    public static void validateDirectory(File file, String message) {
        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist=" + file.getAbsolutePath() + ", " + message + file);
        }

        if (!file.canRead()) {
            throw new IllegalArgumentException("Unable to read file " + file.getAbsolutePath() + ", " + message + file);
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("Not a directory " + file.getAbsolutePath() + ", " + message + file);
        }

    }

    public static void validateFile(File file, String message) {
        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist=" + file.getAbsolutePath() + ", " + message + file);
        }

        if (!file.canRead()) {
            throw new IllegalArgumentException("Unable to read file " + file.getAbsolutePath() + ", " + message + file);
        }
        if (file.isDirectory()) {
            throw new IllegalArgumentException("Not a file " + file.getAbsolutePath() + ", " + message + file);
        }

    }

    public static boolean isInvalidFile(File file) {
        try {
            validateFile(file, "");
        } catch (Exception e) {
            return true;
        }

        return false;
    }
}
