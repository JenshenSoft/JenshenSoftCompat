package com.jenshen.compat.util.delegate;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;

import com.jenshen.compat.R;
import com.jenshen.compat.manager.ThemeManager;
import com.jenshen.compat.util.DoOnError;

public abstract class ViewDelegate {

    protected final Context context;

    public ViewDelegate(Context context) {
        this.context = context;
    }

    public String getErrorMessage(Throwable throwable) {
        return throwable.getMessage() != null ? throwable.getMessage() : context.getString(R.string.error_unknown);
    }

    public AlertDialog showAlertDialog(String message, final @Nullable DoOnError doOnError) {
        int alertDialogThemeId = ThemeManager.getAlertDialogTheme(context);
        final Context context;
        if (alertDialogThemeId != 0) {
            context = new ContextThemeWrapper(this.context, alertDialogThemeId);
        } else {
            context = this.context;
        }
        return new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.warning))
                .setMessage(message)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface v) {
                        if (doOnError != null)
                            doOnError.doOnError();
                    }
                })
                .setPositiveButton(R.string.ok, null)
                .show();
    }

    public ProgressDialog showProgressDialog() {
        int alertDialogThemeId = ThemeManager.getAlertDialogTheme(context);
        final Context context;
        if (alertDialogThemeId != 0) {
            context = new ContextThemeWrapper(this.context, alertDialogThemeId);
        } else {
            context = this.context;
        }
        return ProgressDialog.show(context, context.getString(R.string.loading), context.getString(R.string.please_wait));
    }

    public abstract void handleError(Throwable e);
}
