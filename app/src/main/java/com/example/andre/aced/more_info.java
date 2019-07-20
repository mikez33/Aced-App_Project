package com.example.andre.aced;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import data.DatabaseHelper;

public class more_info extends AppCompatActivity {
    private  static final int ACTIVITY_NUM = 2;
    private Button addImage;
    private Button addCalendarEvent;
    private Button addTask;
    private static final int IMAGE_PICK_CODE= 1000;
    private static final int PERMISSION_CODE= 1001;
    private ImageView mImageView;

    private DatabaseHelper db;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_info);

        //Find corresponding XML Components
        addImage= (Button) findViewById(R.id.addImage);
        addCalendarEvent= (Button) findViewById(R.id.addCalendar);
        addTask= (Button) findViewById(R.id.addTask);
        mImageView= (ImageView) findViewById(R.id.imageNote);



        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED) {
                        //Permission is not granted, must request permission
                        String[] permissions= {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_CODE);
                    }
                }
                pickFromGallery();
            }
        });
    }

    private void pickFromGallery() {
        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickFromGallery();
                }
                else {
                    Toast.makeText(this, "Aced must be able to access photo album", Toast.LENGTH_SHORT);
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            mImageView.setImageURI(data.getData());
        }
    }
}
