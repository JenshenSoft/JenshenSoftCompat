package com.jenshen.compat.util.io;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.AnyRes;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.IOException;

public interface IFileSystem {

    File createFile(String prefix, String suffix, File directory) throws IOException;

    String getPath(File image);

    Uri getUriFromFile(String applicationId, File file) throws IOException;

    Uri getUriToDrawable(@AnyRes int drawableId);

    File saveBitmap(@NonNull Bitmap bitmap, @NonNull File file) throws IOException;

    void scan(Uri uri);
}