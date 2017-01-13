package com.jenshensoft.compat.di.component;

import com.jenshen.compat.base.component.presenter.PresenterComponent;
import com.jenshensoft.compat.activity.root.RootActivity;
import com.jenshensoft.compat.activity.root.mvp.RootPresenter;
import com.jenshensoft.compat.activity.root.mvp.RootView;

import dagger.MembersInjector;
import dagger.Subcomponent;

@Subcomponent
public interface RootActivityComponent extends PresenterComponent<RootView, RootPresenter>, MembersInjector<RootActivity> {
}
