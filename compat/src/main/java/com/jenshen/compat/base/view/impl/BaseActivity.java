package com.jenshen.compat.base.view.impl;

import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.jenshen.compat.R;
import com.jenshen.compat.base.view.BaseView;
import com.jenshen.compat.base.view.impl.mvp.lce.BaseLceMvpActivity;


public abstract class BaseActivity extends AppCompatActivity implements BaseView {


    /* view */

    @Override
    public void handleError(Throwable throwable) {
        throwable.printStackTrace();
        String errorMessage = throwable.getMessage() != null ? throwable.getMessage() : getString(R.string.error_unknown);
        createAlertDialog(errorMessage, null).show();
    }

    protected AlertDialog.Builder createAlertDialog(String message, final @Nullable BaseLceMvpActivity.DoOnError doOnError) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
}
