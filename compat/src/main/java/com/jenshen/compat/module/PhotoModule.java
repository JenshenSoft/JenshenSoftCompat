package com.jenshen.compat.module;


import android.content.Context;

import com.jenshen.compat.manager.photo.IPhotoManager;
import com.jenshen.compat.manager.photo.PhotoManager;
import com.jenshen.compat.util.io.IFileCreator;
import com.jenshen.compat.util.io.IFileSystem;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PhotoModule {

    @Provides
    @Singleton
    public IPhotoManager providesIPhotoManager(Context context, IFileCreator fileCreator, IFileSystem fileSystem) {
        return new PhotoManager(context, fileCreator, fileSystem);
    }
}