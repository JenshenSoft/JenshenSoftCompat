package com.jenshen.compat.base.app;

import android.app.Application;
import android.support.annotation.NonNull;

import com.jenshen.compat.base.component.app.BaseAppComponent;

import dagger.MembersInjector;


public abstract class BaseApp<App extends BaseApp, AppComponent extends BaseAppComponent<App>>
        extends Application
        implements MembersInjector<AppComponent> {

    @NonNull
    protected AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = provideDaggerAppComponent();
        injectMembers(appComponent);
    }

    @NonNull
    public AppComponent getAppComponent() {
        return appComponent;
    }

    @NonNull
    protected abstract AppComponent provideDaggerAppComponent();
}
