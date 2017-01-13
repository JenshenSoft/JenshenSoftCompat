package com.jenshensoft.compat.activity.root;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jenshen.compat.base.view.impl.mvp.lce.component.BaseDiMvpActivity;
import com.jenshensoft.compat.R;
import com.jenshensoft.compat.activity.root.mvp.RootPresenter;
import com.jenshensoft.compat.activity.root.mvp.RootView;
import com.jenshensoft.compat.app.App;
import com.jenshensoft.compat.di.component.RootActivityComponent;

public class RootActivity extends BaseDiMvpActivity<RootActivityComponent, RootView, RootPresenter> implements RootView {


    @Override
    public RootActivityComponent createComponent() {
        return App.component.plus();
    }

    @Override
    public void injectMembers(RootActivityComponent instance) {
        instance.injectMembers(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        findViewById(R.id.btnTakePhoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleError(new RuntimeException("Some error"));
            }
        });
    }
}
