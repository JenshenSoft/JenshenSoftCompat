package com.jenshen.compat.base.view.impl.mvp.lce;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceFragment;
import com.jenshen.compat.R;
import com.jenshen.compat.base.view.BaseLceMvpView;
import com.jenshen.compat.util.delegate.HasDelegateView;
import com.jenshen.compat.util.delegate.ViewDelegateFragment;


public abstract class BaseLceMvpFragment<
        CV extends View,
        M,
        V extends BaseLceMvpView<M>,
        P extends MvpPresenter<V>>

        extends MvpLceFragment<CV, M, V, P>

        implements BaseLceMvpView<M>, HasDelegateView<ViewDelegateFragment> {

    @Nullable
    protected ViewDelegateFragment viewDelegate;
    @Nullable
    private ProgressDialog dialog;

    @Override
    public ViewDelegateFragment getViewDelegate() {
        return createDelegateIfNull();
    }

    /**
     * invoke this method on child constructor if you want to customise a delegate
     *
     * @param viewDelegate
     */
    @Override
    public void setViewDelegate(@NonNull ViewDelegateFragment viewDelegate) {
        this.viewDelegate = viewDelegate;
    }


    /* view */

    @Override
    public void showProgress() {
        if (dialog != null) {
            hideProgress();
        }
        dialog = createDelegateIfNull().showProgressDialog();
    }

    @Override
    public void hideProgress() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    public void handleError(Throwable throwable) {
        createDelegateIfNull().handleError(throwable);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return createDelegateIfNull().getErrorMessage(e);
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