package com.jenshen.compat.base.view.impl.mvp.lce.component.lce;


import android.support.annotation.NonNull;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.jenshen.compat.base.component.PresenterLceComponent;
import com.jenshen.compat.base.view.BaseLceMvpView;
import com.jenshen.compat.base.view.impl.mvp.lce.BaseLceMvpFragment;

public abstract class BaseDiLceMvpFragment<
        Component extends PresenterLceComponent<M, V, P>,
        CV extends View,
        M,
        V extends BaseLceMvpView<M>,
        P extends MvpPresenter<V>>

        extends BaseLceMvpFragment<CV, M, V, P> {

    protected final Component component;

    public BaseDiLceMvpFragment() {
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
