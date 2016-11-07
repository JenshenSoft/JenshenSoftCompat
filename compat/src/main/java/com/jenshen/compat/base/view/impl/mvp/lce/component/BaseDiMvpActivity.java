package com.jenshen.compat.base.view.impl.mvp.lce.component;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.jenshen.compat.base.component.PresenterComponent;
import com.jenshen.compat.base.view.BaseMvpView;
import com.jenshen.compat.base.view.impl.mvp.BaseMvpActivity;

public abstract class BaseDiMvpActivity<
        Component extends PresenterComponent<V, P>,
        V extends BaseMvpView,
        P extends MvpPresenter<V>>

        extends BaseMvpActivity<V, P> {

    protected final Component component;

    public BaseDiMvpActivity() {
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