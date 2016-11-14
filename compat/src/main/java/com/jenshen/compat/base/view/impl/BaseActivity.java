package com.jenshen.compat.base.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jenshen.compat.base.view.BaseView;
import com.jenshen.compat.util.delegate.HasDelegateView;
import com.jenshen.compat.util.delegate.ViewDelegateActivity;


public abstract class BaseActivity extends AppCompatActivity implements BaseView, HasDelegateView<ViewDelegateActivity> {

    @Nullable
    protected ViewDelegateActivity viewDelegate;

    @Override
    public ViewDelegateActivity getViewDelegate() {
        return createDelegateIfNull();
    }

    /**
     * invoke this method on child constructor if you want to customise a delegate
     *
     * @param viewDelegate
     */
    @Override
    public void setViewDelegate(@NonNull ViewDelegateActivity viewDelegate) {
        this.viewDelegate = viewDelegate;
    }


    /* view */

    @Override
    public void handleError(Throwable throwable) {
        createDelegateIfNull().handleError(throwable);
    }

    /* lifecycle */

    @Override
    protected void onStart() {
        super.onStart();
        createDelegateIfNull().onStart();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createDelegateIfNull().onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        createDelegateIfNull().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        createDelegateIfNull().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        createDelegateIfNull().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        createDelegateIfNull().onDestroy();
    }

    /* private methods */

    private ViewDelegateActivity createDelegateIfNull() {
        if (viewDelegate == null) {
            viewDelegate = new ViewDelegateActivity(this);
        }
        return viewDelegate;
    }
}
