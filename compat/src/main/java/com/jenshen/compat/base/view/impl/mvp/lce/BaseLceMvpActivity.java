package com.jenshen.compat.base.view.impl.mvp.lce;


import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceActivity;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.jenshen.compat.R;
import com.jenshen.compat.base.view.BaseLceMvpView;


public abstract class BaseLceMvpActivity<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>>
        extends MvpLceActivity<CV, M, V, P>
        implements BaseLceMvpView<M> {

    /* callbacks */

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void handleError(Throwable throwable) {
        throwable.printStackTrace();
        createAlertDialog(getErrorMessage(throwable), null).show();
    }

    protected String getErrorMessage(Throwable throwable) {
        return throwable.getMessage() != null ? throwable.getMessage() : getString(R.string.error_unknown);
    }

    protected AlertDialog.Builder createAlertDialog(String message, final @Nullable DoOnError doOnError) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder.setTitle(getString(R.string.warning))
                .setMessage(message)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface v) {
                        if (doOnError != null)
                            doOnError.doOnError();
                    }
                })
                .setPositiveButton(R.string.ok, null);
    }


    /* Functional interface */

    public interface DoOnError {
        void doOnError();
    }
}
