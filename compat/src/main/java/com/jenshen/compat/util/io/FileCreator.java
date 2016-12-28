package com.jenshen.compat.util.io;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileCreator implements IFileCreator {

    private final Context context;
    private IFileSystem fileSystem;

    public FileCreator(Context context, IFileSystem fileSystem) {
        this.context = context;
        this.fileSystem = fileSystem;
    }

    @Override
    public File createPictureFile(@NonNull String folderName, @NonNull String fileName, @NonNull String suffix) throws IOException {
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), folderName);
        return fileSystem.createFile(fileName, suffix, storageDir);
    }

    @Override
    public File createPictureFile(@NonNull String folderName) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "PNG_" + timeStamp + "_";
        return createPictureFile(folderName, imageFileName, ".png");
    }

    @Override
    public File createDCIMFile(String folderName, String fileName, String suffix) throws IOException {
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), folderName);
        return fileSystem.createFile(fileName, suffix, storageDir);
    }

    @Override
    public File createDCIMFile(@Nullable String folderName) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "PNG_" + timeStamp + "_";
        return createPictureFile(folderName == null ? "Camera" : folderName, imageFileName, ".png");
    }

    @Override
    public File createCacheFile(String fileName, String suffix) throws IOException {
        File storageDir = context.getCacheDir();
        return fileSystem.createFile(fileName, suffix, storageDir);
    }

    @Override
    public File createCacheFile() throws IOException {
        String fileName = "File_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File storageDir = context.getCacheDir();
        return fileSystem.createFile(fileName, ".tmp", storageDir);
    }

    @Override
    public File createFileFromBitmap(Bitmap bitmap, @NonNull String folderName) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "PNG_" + timeStamp + "_";
        return createFileFromBitmap(bitmap, createPictureFile(folderName, imageFileName, ".png"));
    }

    @Override
    public File createFileFromBitmap(Bitmap bitmap, @NonNull String folderName, @NonNull String fileName) throws IOException {
        return createFileFromBitmap(bitmap, createPictureFile(folderName, fileName, ".png"));
    }

    @Override
    public File createFileFromBitmap(Bitmap bitmap, File file) throws IOException {
        return fileSystem.saveBitmap(bitmap, file);
    }
}
