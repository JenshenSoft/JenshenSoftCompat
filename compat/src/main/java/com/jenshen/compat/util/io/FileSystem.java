package com.jenshen.compat.util.io;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.support.annotation.AnyRes;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileSystem implements IFileSystem {

    private final static int COMPRESS_QUALITY = 90;
    private final static Bitmap.CompressFormat COMPRESS_FORMAT = Bitmap.CompressFormat.PNG;

    private Context context;

    public FileSystem(Context context) {
        this.context = context;
    }

    @Override
    public File createFile(String prefix, String suffix, File directory) throws IOException {
        return File.createTempFile(
                prefix,         /* prefix */
                suffix,         /* suffix */
                directory);     /* directory */
    }

    @Override
    public String getPath(File image) {
        return "file:" + image.getAbsolutePath();
    }

    @Override
    public Uri getUriFromFile(String applicationId, File file) throws IOException {
        return FileProvider.getUriForFile(context,
                applicationId + ".provider",
                file);
    }

    /**
     * get uri to drawable or any other resource type if u wish
     *
     * @param drawableId - drawable res id
     * @return - uri
     */
    @Override
    public final Uri getUriToDrawable(@AnyRes int drawableId) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + context.getResources().getResourcePackageName(drawableId)
                + '/' + context.getResources().getResourceTypeName(drawableId)
                + '/' + context.getResources().getResourceEntryName(drawableId));
    }

    @Override
    public File saveBitmap(@NonNull Bitmap bitmap, @NonNull File file) throws IOException {
        FileOutputStream fOut = new FileOutputStream(file);
        bitmap.compress(COMPRESS_FORMAT, COMPRESS_QUALITY, fOut);
        fOut.flush();
        fOut.close();
        return file;
    }


    /**
     * ScanFile so it will be appeared on Gallery
     *
     * @param uri
     */
    @Override
    public void scan(Uri uri) {
        // ScanFile so it will be appeared on Gallery
        MediaScannerConnection.scanFile(context,
                new String[]{uri.getPath()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String path, Uri uri1) {
                    }
                });
    }
}
