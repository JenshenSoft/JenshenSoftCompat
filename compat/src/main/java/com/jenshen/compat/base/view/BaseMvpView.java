package com.jenshen.compat.base.view;

import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpView;

public interface BaseMvpView extends MvpView, BaseView {
    Context getContext();

    void showProgress();

    void hideProgress();
}
