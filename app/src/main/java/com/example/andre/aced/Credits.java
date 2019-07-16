package com.example.andre.aced;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class Credits extends AppCompatActivity {

    private ImageView back;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        //Find Corresponding XML Files
        back = (ImageView)findViewById(R.id.optionBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Credits.this, Calendar.class);
                Credits.this.startActivity(intent1);
            }
        });
    }
}
