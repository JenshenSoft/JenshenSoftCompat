package com.jenshen.compat.base.view.impl.mvp.lce;


import android.app.Activity;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceFragment;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.jenshen.compat.base.view.BaseLceMvpView;
import com.jenshen.compat.base.view.BaseView;


public abstract class BaseLceMvpFragment<
        CV extends View,
        M,
        V extends MvpLceView<M>,
        P extends MvpPresenter<V>>

        extends MvpLceFragment<CV, M, V, P>

        implements BaseLceMvpView<M> {

    @Override
    public void handleError(Throwable e) {
        Activity activity = getActivity();
        if (activity instanceof BaseView) {
            ((BaseView) activity).handleError(e);
        } else {
            throw new RuntimeException("Can't support this activity class " + activity.getPackageName());
        }
    }
}