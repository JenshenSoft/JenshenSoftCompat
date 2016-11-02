package com.jenshen.compat.base.view.impl.mvp.lce;


import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceFragment;
import com.jenshen.compat.base.view.BaseLceMvpView;


public abstract class BaseLceMvpFragment<
        CV extends View,
        M,
        V extends BaseLceMvpView<M>,
        P extends MvpPresenter<V>>

        extends MvpLceFragment<CV, M, V, P>

        implements BaseLceMvpView<M> {
}