package com.jenshen.compat.base.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.jenshen.compat.base.view.BaseView;
import com.jenshen.compat.util.delegate.ViewDelegateFragment;


public abstract class BaseFragment extends Fragment implements BaseView {

    @Nullable
    private ViewDelegateFragment viewDelegate;

    /**
     * invoke this method on child constructor if you want to customise a delegate
     *
     * @param viewDelegate
     */
    public void setViewDelegate(@NonNull ViewDelegateFragment viewDelegate) {
        this.viewDelegate = viewDelegate;
    }

    /* view */

    @Override
    public void handleError(Throwable throwable) {
        createDelegateIfNull().handleError(throwable);
    }

    /* lifecycle */

    @Override
    public void onStart() {
        super.onStart();
        createDelegateIfNull().onStart();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createDelegateIfNull().onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        createDelegateIfNull().onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        createDelegateIfNull().onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        createDelegateIfNull().onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        createDelegateIfNull().onDestroy();
    }

    /* private methods */

    private ViewDelegateFragment createDelegateIfNull() {
        if (viewDelegate == null) {
            viewDelegate = new ViewDelegateFragment(getContext());
        }
        return viewDelegate;
    }
}
