package com.jenshensoft.compat.app;

import android.support.annotation.NonNull;

import com.jenshen.compat.base.app.BaseApp;
import com.jenshensoft.compat.di.component.AppComponent;
import com.jenshensoft.compat.di.component.DaggerAppComponent;
import com.jenshensoft.compat.di.module.AppModule;

public class App extends BaseApp<App, AppComponent> {

    public static AppComponent component;

    @NonNull
    @Override
    protected AppComponent provideDaggerAppComponent() {
        return DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    @Override
    public void injectMembers(AppComponent instance) {
        App.component = instance;
    }
}
