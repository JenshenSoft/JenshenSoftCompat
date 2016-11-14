package com.jenshen.compat.base.view.impl.mvp.lce;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceActivity;
import com.jenshen.compat.R;
import com.jenshen.compat.base.view.BaseLceMvpView;
import com.jenshen.compat.util.delegate.HasDelegateView;
import com.jenshen.compat.util.delegate.ViewDelegateActivity;


public abstract class BaseLceMvpActivity<CV extends View, M, V extends BaseLceMvpView<M>, P extends MvpPresenter<V>>
        extends MvpLceActivity<CV, M, V, P>
        implements BaseLceMvpView<M>, HasDelegateView<ViewDelegateActivity> {

    @Nullable
    protected ViewDelegateActivity viewDelegate;
    @Nullable
    private ProgressDialog dialog;

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

    @Override
    public void showProgress() {
        if (dialog != null) {
            hideProgress();
        }
        dialog = ProgressDialog.show(getContext(), getString(R.string.loading), getString(R.string.please_wait));
    }

    @Override
    public void hideProgress() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return createDelegateIfNull().getErrorMessage(e);
    }

    @Override
    public Context getContext() {
        return this;
    }


    /* lifecycle */

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        createDelegateIfNull().onNewIntent(intent);
    }

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
