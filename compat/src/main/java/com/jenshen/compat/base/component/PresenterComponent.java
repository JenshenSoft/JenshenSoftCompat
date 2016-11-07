package com.jenshen.compat.base.component;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.jenshen.compat.base.view.BaseMvpView;


public interface PresenterComponent<
        V extends BaseMvpView,
        P extends MvpPresenter<V>> {

    P getPresenter();
}
