package com.example.andre.aced;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.victor.loading.newton.NewtonCradleLoading;

public class Splash_Screen extends AppCompatActivity {

    private NewtonCradleLoading newtonCradleLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        newtonCradleLoading = (NewtonCradleLoading)findViewById(R.id.newton_cradle_loading);
        newtonCradleLoading.setLoadingColor(Color.parseColor("#5AC9DD"));
        newtonCradleLoading.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash_Screen.this, Calendar.class);
                Splash_Screen.this.startActivity(intent);
                finish();
                newtonCradleLoading.stop();
            }
        },2500);

    }
}
