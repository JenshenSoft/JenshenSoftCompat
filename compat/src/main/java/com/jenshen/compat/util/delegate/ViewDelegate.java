package com.jenshen.compat.util.delegate;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.jenshen.compat.R;
import com.jenshen.compat.util.DoOnError;

public abstract class ViewDelegate {

    protected final Context context;

    public ViewDelegate(Context context) {
        this.context = context;
    }

    protected String getErrorMessage(Throwable throwable) {
        return throwable.getMessage() != null ? throwable.getMessage() : context.getString(R.string.error_unknown);
    }

    protected AlertDialog.Builder createAlertDialog(String message, final @Nullable DoOnError doOnError) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        return builder.setTitle(context.getString(R.string.warning))
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

    public abstract void handleError(Throwable e);
}
