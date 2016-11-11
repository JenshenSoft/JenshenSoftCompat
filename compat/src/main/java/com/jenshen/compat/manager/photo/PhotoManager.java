package com.jenshen.compat.manager.photo;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;

import com.jenshen.compat.util.BitmapUtils;
import com.jenshen.compat.util.io.IFileCreator;
import com.jenshen.compat.util.io.IFileSystem;

import java.io.File;
import java.io.IOException;

public class PhotoManager implements IPhotoManager {

    public static final int REQUEST_TAKE_PHOTO_FROM_CAMERA = 1;
    public static final int REQUEST_TAKE_PHOTO_FROM_GALLERY = 2;
    public static final int REQUEST_CROP_PHOTO = 3;

    private static final int MAX_PHOTO_SIZE = 720;

    private static final int ASPECT_WIDTH = 1;
    private static final int ASPECT_HEIGHT = 1;
    private static final int CROP_WIDTH = 90;
    private static final int CROP_HEIGHT = 90;
    private static final String PREVIEW_PHOTO_NAME = "preview_photo";
    private final Context context;
    private final IFileCreator fileCreator;
    private final IFileSystem fileSystem;
    @Nullable
    private PhotoManagerCallbacks photoManagerCallbacks;

    public PhotoManager(Context context, IFileCreator fileCreator, IFileSystem fileSystem) {
        this.context = context;
        this.fileCreator = fileCreator;
        this.fileSystem = fileSystem;
    }

    @Override
    public void setPhotoManagerCallbacks(@Nullable PhotoManagerCallbacks photoManagerCallbacks) {
        this.photoManagerCallbacks = photoManagerCallbacks;
    }

    @Override
    public String takePhotoFromCamera() throws IOException {
        return takePhotoFromCameraRequest();
    }

    @Override
    public void takePhotoFromGallery() throws IOException {
         takePhotoFromGalleryRequest();
    }

    @Override
    public String cropPhoto(File file) throws IOException {
        Bitmap bitmap = fileSystem.decodeSampledBitmapFromFile(file.getPath(), MAX_PHOTO_SIZE, MAX_PHOTO_SIZE);
        bitmap = BitmapUtils.rotateBitmap(bitmap, BitmapUtils.getImageOrientation(file));
        file = fileCreator.createFileFromBitmap(bitmap, file.getPath());
        return cropPhotoRequest(file);
    }


    /* private methods */

    private String takePhotoFromCameraRequest() throws IOException {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = fileCreator.createImageFile();
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = Uri.fromFile(photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                if (photoManagerCallbacks != null) {
                    photoManagerCallbacks.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO_FROM_CAMERA);
                    return fileSystem.getPathFromFile(photoFile);
                } else {
                    throw new RuntimeException("PhotoManagerCallbacks can't be null");
                }
            } else {
                throw new RuntimeException("Can't create a file");
            }
        } else {
            throw new RuntimeException("A camera activity don't handle the intent " + takePictureIntent.toString());
        }
    }

    private void takePhotoFromGalleryRequest() {
        Intent takePhotoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (photoManagerCallbacks != null) {
            photoManagerCallbacks.startActivityForResult(takePhotoIntent, REQUEST_TAKE_PHOTO_FROM_GALLERY);
        } else {
            throw new RuntimeException("PhotoManagerCallbacks can't be null");
        }
    }

    private String cropPhotoRequest(File file) throws IOException {
        File cropPhotoFile = fileCreator.createTempFile(PREVIEW_PHOTO_NAME);
        Intent cropIntent = new Intent("com.android.camera.action.CROP");
        cropIntent.putExtra("aspectX", ASPECT_WIDTH);
        cropIntent.putExtra("aspectY", ASPECT_HEIGHT);
        cropIntent.putExtra("outputX", CROP_WIDTH);
        cropIntent.putExtra("outputY", CROP_HEIGHT);
        cropIntent.setDataAndType(Uri.fromFile(file), "image/*");
        cropIntent.putExtra("return-data", false);
        cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cropPhotoFile));

        if (photoManagerCallbacks != null) {
            photoManagerCallbacks.startActivityForResult(cropIntent, REQUEST_CROP_PHOTO);
            return fileSystem.getPathFromFile(cropPhotoFile);
        } else {
            throw new RuntimeException("PhotoManagerCallbacks can't be null");
        }
    }

    private String getImagePathFromGallary(Intent data) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            if (cursor == null) {
                throw new RuntimeException("Cursor can't be null");
            }
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            return cursor.getString(columnIndex);
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }

    /* inner types */

    public interface PhotoManagerCallbacks {
        void startActivityForResult(Intent intent, int requestCode);
    }
}
