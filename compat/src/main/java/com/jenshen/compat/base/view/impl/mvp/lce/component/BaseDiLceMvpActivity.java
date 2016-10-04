package com.jenshen.compat.base.view.impl.mvp.lce.component;


import android.support.annotation.NonNull;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.jenshen.compat.base.component.PresenterComponent;
import com.jenshen.compat.base.view.impl.mvp.lce.BaseLceMvpActivity;

public abstract class BaseDiLceMvpActivity<
        Component extends PresenterComponent<M, V, P>,
        CV extends View,
        M,
        V extends MvpLceView<M>,
        P extends MvpPresenter<V>>

        extends BaseLceMvpActivity<CV, M, V, P> {

    public final Component component;

    public BaseDiLceMvpActivity() {
        component = createComponent();
        inject(component);
    }

    public abstract Component createComponent();

    public abstract void inject(Component activityComponent);

    @NonNull
    @Override
    public P createPresenter() {
        return component.getPresenter();
    }
}
