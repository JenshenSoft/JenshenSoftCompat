package com.jenshen.compat.base.component;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.jenshen.compat.base.view.BaseLceMvpView;


public interface PresenterLceComponent<
        M,
        V extends BaseLceMvpView<M>,
        P extends MvpPresenter<V>> {

    P getPresenter();
}
