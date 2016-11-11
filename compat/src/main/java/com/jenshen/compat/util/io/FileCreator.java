package com.jenshen.compat.util.io;


import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCreator implements IFileCreator {

    private final static int COMPRESS_QUALITY = 90;
    private final static Bitmap.CompressFormat COMPRESS_FORMAT = Bitmap.CompressFormat.PNG;

    private IFileSystem fileSystem;

    public FileCreator(IFileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public File createTempFile(String name) {
        return fileSystem.createFileInInternalStorage(Environment.DIRECTORY_PICTURES, name);
    }

    @Override
    public File createImageFile() throws IOException {
        return fileSystem.createImageFile();
    }

    @Override
    public File createFileFromBitmap(Bitmap bitmap, File file) throws IOException {
        return createFileFromBitmap(bitmap, file.getAbsolutePath());
    }

    @Override
    public  File createFileFromBitmap(Bitmap bitmap, String path) throws IOException {
        File file = new File(path);
        FileOutputStream fOut = new FileOutputStream(file);
        bitmap.compress(COMPRESS_FORMAT, COMPRESS_QUALITY, fOut);
        fOut.flush();
        fOut.close();
        return file;
    }
}
