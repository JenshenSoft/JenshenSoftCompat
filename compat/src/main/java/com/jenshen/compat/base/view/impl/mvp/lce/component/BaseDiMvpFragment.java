package com.jenshen.compat.base.view.impl.mvp.lce.component;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.jenshen.compat.base.component.presenter.PresenterComponent;
import com.jenshen.compat.base.view.BaseMvpView;
import com.jenshen.compat.base.view.impl.mvp.BaseMvpFragment;

import dagger.MembersInjector;

public abstract class BaseDiMvpFragment<
        Component extends PresenterComponent<V, P>,
        V extends BaseMvpView,
        P extends MvpPresenter<V>>

        extends BaseMvpFragment<V, P>
        implements MembersInjector<Component> {

    @NonNull
    protected final Component component;

    public BaseDiMvpFragment() {
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