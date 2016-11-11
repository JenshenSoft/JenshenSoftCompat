package com.jenshen.compat.manager.photo;

import android.support.annotation.Nullable;
import android.support.annotation.StringDef;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.jenshen.compat.manager.photo.IPhotoManager.PhotoMode.PHOTO_FROM_CAMERA;
import static com.jenshen.compat.manager.photo.IPhotoManager.PhotoMode.PHOTO_FROM_GALLERY;

public interface IPhotoManager {

    void setPhotoManagerCallbacks(@Nullable PhotoManager.PhotoManagerCallbacks photoManagerCallbacks);

    String takePhotoFromCamera() throws IOException;

    void takePhotoFromGallery() throws IOException;

    String cropPhoto(File file) throws IOException;

    @StringDef({PHOTO_FROM_CAMERA, PHOTO_FROM_GALLERY})
    @Retention(RetentionPolicy.SOURCE)
     @interface PhotoMode {
        String PHOTO_FROM_CAMERA = "PHOTO_FROM_CAMERA";
        String PHOTO_FROM_GALLERY = "PHOTO_FROM_GALLERY";
    }
}
