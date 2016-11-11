package com.jenshensoft.compat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.jenshen.compat.manager.photo.IPhotoManager;
import com.jenshen.compat.manager.photo.PhotoManager;
import com.jenshensoft.compat.app.App;

import java.io.IOException;

import javax.inject.Inject;

public class TakePhotoActivity extends AppCompatActivity implements PhotoManager.PhotoManagerCallbacks {

    @Inject
    protected IPhotoManager photoManager;

    private Button btnTakePhoto;
    private ImageView ivPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        App.component.inject(this);
        photoManager.setPhotoManagerCallbacks(this);
        initInstances();
    }

    private void initInstances() {
        btnTakePhoto = (Button) findViewById(R.id.btnTakePhoto);
        ivPreview = (ImageView) findViewById(R.id.ivPreview);

        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    photoManager.takePhotoFromGallery();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
