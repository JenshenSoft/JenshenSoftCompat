package com.jenshen.compat.util.delegate;

import android.support.annotation.NonNull;

public interface HasDelegateView<Type extends ViewDelegate> {

    Type getViewDelegate();

    /**
     * invoke this method on child constructor if you want to customise a delegate
     *
     * @param viewDelegate
     */
    void setViewDelegate(@NonNull Type viewDelegate);
}
