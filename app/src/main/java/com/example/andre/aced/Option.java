package com.example.andre.aced;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;

import com.google.firebase.auth.FirebaseAuth;

public class Option extends AppCompatActivity {

    private ImageView backButton;
    private Switch notification;
    private ImageView logout;
    private ImageView credits;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        //mAuth = FirebaseAuth.getInstance();

        backButton = (ImageView) findViewById(R.id.optionBack);
        notification = (Switch)findViewById(R.id.toggleNoti);
        //logout = (ImageView)findViewById(R.id.optionLogout);
        credits = (ImageView)findViewById(R.id.optionCredit);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Option.this, Calendar.class);
                Option.this.startActivity(intent);
            }
        });

        /*logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAuth.getCurrentUser() != null){
                    mAuth.signOut();
                    Intent intent = new Intent(Option.this, Login.class);
                    Option.this.startActivity(intent);

                }
            }
        });*/

        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Option.this, Credits.class);
                Option.this.startActivity(intent);
            }
        });


    }
}
