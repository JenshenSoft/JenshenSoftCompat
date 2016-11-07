package com.jenshen.compat.util.delegate;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ViewDelegateActivity extends ViewDelegate {

    public ViewDelegateActivity(Context context) {
        super(context);
    }

    @Override
    public void handleError(Throwable throwable) {
        throwable.printStackTrace();
        createAlertDialog(getErrorMessage(throwable), null).show();
    }

    public void onStart() {

    }

    public void onCreate(Bundle savedInstanceState) {

    }

    public void onResume() {

    }

    public void onPause() {

    }

    public void onStop() {

    }

    public void onDestroy() {

    }

    public void onNewIntent(Intent intent) {

    }
}
