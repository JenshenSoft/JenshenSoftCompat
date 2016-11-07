/*
 *  Copyright 2015 Hannes Dorfmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.jenshen.compat.base.presenter;

import android.support.annotation.Nullable;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public abstract class MvpRxPresenter<V extends MvpView> extends MvpBasePresenter<V> {

    @Nullable
    private CompositeDisposable compositeDisposable;

    /**
     * Unsubscribes the observers and set it to null
     */
    protected void unsubscribe() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
    }

    public void addDisposible(Disposable disposable) {
        getCompositeDisposable().add(disposable);
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (!retainInstance) {
            unsubscribe();
        }
    }

    protected CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null) {
            compositeDisposable =  new CompositeDisposable();
        }
        return compositeDisposable;
    }
}
