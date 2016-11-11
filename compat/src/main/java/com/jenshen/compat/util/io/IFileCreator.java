package com.jenshen.compat.util.io;

import android.graphics.Bitmap;

import java.io.File;
import java.io.IOException;

public interface IFileCreator {

    File createTempFile(String name);

    File createImageFile() throws IOException;

    File createFileFromBitmap(Bitmap bitmap, File file) throws IOException;

    File createFileFromBitmap(Bitmap bitmap, String path) throws IOException;
}