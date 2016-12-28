package com.jenshensoft.compat.di.component;

import com.jenshen.compat.base.component.app.BaseAppComponent;
import com.jenshen.compat.module.FileModule;
import com.jenshensoft.compat.TakePhotoActivity;
import com.jenshensoft.compat.app.App;
import com.jenshensoft.compat.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, FileModule.class, PhotoModule.class})
public interface AppComponent extends BaseAppComponent<App> {
    void inject(TakePhotoActivity takePhotoActivity);
}
