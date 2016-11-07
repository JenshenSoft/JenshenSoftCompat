package com.jenshen.compat.util.delegate;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.jenshen.compat.base.view.BaseView;

public class ViewDelegateFragment extends ViewDelegate {

    public ViewDelegateFragment(Context context) {
        super(context);
    }

    @Override
    public void handleError(Throwable e) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (activity instanceof BaseView) {
                ((BaseView) activity).handleError(e);
            } else {
                throw new RuntimeException("Can't support this activity class " + activity.getPackageName());
            }
        }
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
}
