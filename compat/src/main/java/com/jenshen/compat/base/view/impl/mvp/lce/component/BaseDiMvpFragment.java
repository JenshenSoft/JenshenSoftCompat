package com.jenshen.compat.base.view.impl.mvp.lce.component;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.jenshen.compat.base.component.PresenterComponent;
import com.jenshen.compat.base.view.BaseMvpView;
import com.jenshen.compat.base.view.impl.mvp.BaseMvpFragment;

public abstract class BaseDiMvpFragment<
        Component extends PresenterComponent<V, P>,
        V extends BaseMvpView,
        P extends MvpPresenter<V>>

        extends BaseMvpFragment<V, P> {

    protected final Component component;

    public BaseDiMvpFragment() {
        component = createComponent();
        inject(component);
    }

    public abstract Component createComponent();

    public abstract void inject(Component fragmentComponent);

    @NonNull
    @Override
    public P createPresenter() {
        return component.getPresenter();
    }
}