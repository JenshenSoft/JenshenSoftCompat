package com.jenshensoft.compat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jenshensoft.compat.R;
import com.jenshensoft.compat.app.App;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        App.component.inject(this);
    }
}
