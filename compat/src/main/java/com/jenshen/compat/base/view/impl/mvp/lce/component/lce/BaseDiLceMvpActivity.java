package com.jenshen.compat.base.view.impl.mvp.lce.component.lce;


import android.support.annotation.NonNull;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.jenshen.compat.base.component.presenter.PresenterLceComponent;
import com.jenshen.compat.base.view.BaseLceMvpView;
import com.jenshen.compat.base.view.impl.mvp.lce.BaseLceMvpActivity;

import dagger.MembersInjector;

public abstract class BaseDiLceMvpActivity<
        Component extends PresenterLceComponent<M, V, P>,
        CV extends View,
        M,
        V extends BaseLceMvpView<M>,
        P extends MvpPresenter<V>>

        extends BaseLceMvpActivity<CV, M, V, P>
        implements MembersInjector<Component> {

    @NonNull
    protected final Component component;

    public BaseDiLceMvpActivity() {
        component = createComponent();
        injectMembers(component);
    }

    public abstract Component createComponent();

    @NonNull
    @Override
    public P createPresenter() {
        return component.getPresenter();
    }
}
