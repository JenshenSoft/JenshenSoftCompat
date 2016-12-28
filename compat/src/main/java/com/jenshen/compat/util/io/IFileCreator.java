package com.jenshen.compat.util.io;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.IOException;

public interface IFileCreator {

    File createPictureFile(String folderName, String fileName, String suffix) throws IOException;

    File createPictureFile(@NonNull String folderName) throws IOException;

    File createDCIMFile(String folderName, String fileName, String suffix) throws IOException;

    File createDCIMFile(@Nullable String folderName) throws IOException;

    File createCacheFile(String fileName, String suffix) throws IOException;

    File createCacheFile() throws IOException;

    File createFileFromBitmap(Bitmap bitmap, @NonNull String folderName) throws IOException;

    File createFileFromBitmap(Bitmap bitmap, @NonNull String folderName, @NonNull String fileName) throws IOException;

    File createFileFromBitmap(Bitmap bitmap, File file) throws IOException;
}