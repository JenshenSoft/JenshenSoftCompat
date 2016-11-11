package com.jenshen.compat.util.io;

import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.AnyRes;
import android.support.v4.content.FileProvider;

import com.jenshen.compat.BuildConfig;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileSystem implements IFileSystem {

    private Context context;

    public FileSystem(Context context) {
        this.context = context;
    }

    @Override
    public File createFileInInternalStorage(String filePath, String fileName) {
        ContextWrapper contextWrapper = new ContextWrapper(context);
        File directory = contextWrapper.getExternalFilesDir(filePath);
        return new File(directory, fileName);
    }

    @Override
    public File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "Camera");
        return File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
    }

    @Override
    public String getPathFromFile(File file) {
        return "file:" + file.getAbsolutePath();
    }

    @Override
    public Uri getUriFromFile(File file) {
        return FileProvider.getUriForFile(context,
                BuildConfig.APPLICATION_ID + ".provider",
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

    /**
     * ScanFile so it will be appeared on Gallery
     *
     * @param uri - uri
     */
    @Override
    public void scan(Uri uri) {
        // ScanFile so it will be appeared on Gallery
        MediaScannerConnection.scanFile(context,
                new String[]{uri.getPath()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String path, Uri uri1) {
                        //ignored
                    }
                });
    }

    @Override
    public Bitmap decodeSampledBitmapFromFile(String filePath, int maxWidth, int maxHeight) throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        options.inSampleSize = calculateInSampleSize(options, maxWidth, maxHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }


    /* private methods  */

    private static int calculateInSampleSize(BitmapFactory.Options options, int maxWidth, int maxHeight) {
        int height = options.outHeight;
        int width = options.outWidth;

        int heightInSampleSize = (int) Math.ceil((double) height / maxHeight);
        int widthInSampleSize = (int) Math.ceil((double) width / maxWidth);

        return Math.min(heightInSampleSize, widthInSampleSize);
    }
}
