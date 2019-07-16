package com.example.andre.aced;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;

public class Tutorial extends AppIntro2 {

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance("Welcome", "Ready to Get Academically Organized?",
                R.drawable.pt3, Color.parseColor("#FF6961")));
        addSlide(AppIntroFragment.newInstance("Organize With Calendar", "Plan out your study sessions, schedule projects  meetings etc",
                R.drawable.tutorial_cal, Color.parseColor("#00BCD4")));
        addSlide(AppIntroFragment.newInstance("Stay on Task", "Stay on top of your tasks, set a deadline and get notify!",
                R.drawable.tutorial_task, Color.parseColor("#5C6BC0")));
        addSlide(AppIntroFragment.newInstance("Take Notes", "Have a great idea? Jot it down!",
                R.drawable.tutorial_notes, Color.parseColor("#4CAF50")));

        // OPTIONAL METHODS

        // SHOW or HIDE the statusbar
        showStatusBar(true);

        // Edit the color of the nav bar on Lollipop+ devices
        //setNavBarColor(Color.parseColor("#3F51B5"));

        // Turn vibration on and set intensity
        // NOTE: you will need to ask VIBRATE permission in Manifest if you haven't already
        //setVibrate(true);
        //setVibrateIntensity(30);

        // Animations -- use only one of the below. Using both could cause errors.
        setFadeAnimation(); // OR
        //setZoomAnimation(); // OR
        //setFlowAnimation(); // OR
        //setSlideOverAnimation(); // OR
        //setDepthAnimation(); // OR
        //setCustomTransformer(yourCustomTransformer);

        // Permissions -- takes a permission and slide number
        //askForPermissions(new String[]{Manifest.permission.CAMERA}, 3);
    }

    @Override
    public void onNextPressed() {
        // Do something when users tap on Next button.
    }

    @Override
    public void onDonePressed() {
        // Do something when users tap on Done button.
        finish();
        Intent intent = new Intent(Tutorial.this, Calendar.class);
        Tutorial.this.startActivity(intent);
    }

    @Override
    public void onSlideChanged() {
        // Do something when slide is changed
    }
}
