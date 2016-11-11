package com.jenshen.compat.base.component.activity;

import android.app.Activity;

import dagger.MembersInjector;

public interface ActivityComponent<A extends Activity> extends MembersInjector<A> {
}
