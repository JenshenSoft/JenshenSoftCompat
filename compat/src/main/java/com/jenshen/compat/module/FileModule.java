package com.jenshen.compat.module;


import android.content.Context;

import com.jenshen.compat.util.io.FileCreator;
import com.jenshen.compat.util.io.FileSystem;
import com.jenshen.compat.util.io.IFileCreator;
import com.jenshen.compat.util.io.IFileSystem;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FileModule {

    @Provides
    @Singleton
    public IFileSystem providesFileSystem(Context context) {
        return new FileSystem(context);
    }

    @Provides
    @Singleton
    public IFileCreator providesFileCreator(IFileSystem fileSystem) {
        return new FileCreator(fileSystem);
    }
}