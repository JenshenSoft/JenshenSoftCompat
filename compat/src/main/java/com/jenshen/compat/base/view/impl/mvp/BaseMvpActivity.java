package com.jenshen.compat.base.view.impl.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.jenshen.compat.base.view.BaseMvpView;
import com.jenshen.compat.util.delegate.ViewDelegateActivity;


public abstract class BaseMvpActivity<V extends MvpView, P extends MvpPresenter<V>> extends MvpActivity<V, P> implements BaseMvpView {

    @Nullable
    private ViewDelegateActivity viewDelegate;

    /**
     * invoke this method on child constructor if you want to customise a delegate
     *
     * @param viewDelegate
     */
    public void setViewDelegate(@NonNull ViewDelegateActivity viewDelegate) {
        this.viewDelegate = viewDelegate;
    }

    /* view */

    @Override
    public Context getContext() {
        return this;
    }

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