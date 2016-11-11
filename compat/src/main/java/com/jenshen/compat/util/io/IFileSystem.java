package com.jenshen.compat.util.io;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.AnyRes;

import java.io.File;
import java.io.IOException;

public interface IFileSystem {

    File createFileInInternalStorage(String filePath, String fileName);

    File createImageFile() throws IOException;

    String getPathFromFile(File file);

    Uri getUriFromFile(File file);

    Uri getUriToDrawable(@AnyRes int drawableId);

    void scan(Uri uri);

    Bitmap decodeSampledBitmapFromFile(String filePath, int maxWidth, int maxHeight) throws IOException;
}