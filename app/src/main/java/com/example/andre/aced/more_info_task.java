package com.example.andre.aced;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import data.DatabaseHelper;

public class more_info_task extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private ImageView deleteTask;
    private ImageView back;
    private ImageView completeTask;
    private TextView reschedule;
    private TextView task_desciption;
    public int position;
    private DatabaseHelper db1;
    public static String more_info_taskUpdate;
    public Checklist checklist;
    private TextView displayTime;
    private int min;
    private int hr;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_task_info);

        //Find Corresponding XML components
        deleteTask = (ImageView)findViewById(R.id.more_info_delete);
        back = (ImageView)findViewById(R.id.more_info_back);
        completeTask = (ImageView)findViewById(R.id.more_info_completetask);
        reschedule = (TextView)findViewById(R.id.more_info_time);
        task_desciption = (TextView)findViewById(R.id.more_info_task);
        displayTime = (TextView)findViewById(R.id.more_info_displaytime);

        checklist = new Checklist();
        checklist.checklistList.addAll(Checklist.db.getAllTasks());

        Intent i = getIntent();
        more_info_taskUpdate = i.getStringExtra("taskname");
        min = i.getIntExtra("minute", 0);
        hr = i.getIntExtra("hour", 0);



        //back Button functionality
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(more_info_task.this, Checklist.class);
                more_info_task.this.startActivity(intent);
            }
        });

        deleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checklist.deleteTask(position);
                deleteTask.setEnabled(false);
                completeTask.setEnabled(false);
                Toast.makeText(more_info_task.this, "Task Deleted", Toast.LENGTH_LONG).show();

                min = 0;
                hr = 0;
                displayTime.setText("Done!");
                checklist.checklistAdapter.notifyDataSetChanged();
            }
        });

        completeTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checklist.deleteTask(position);
                completeTask.setEnabled(false);
                deleteTask.setEnabled(false);
                Toast.makeText(more_info_task.this, "Task Completed!", Toast.LENGTH_LONG).show();

                min = 0;
                hr = 0;
                displayTime.setText("Done!");
                checklist.checklistAdapter.notifyDataSetChanged();
            }
        });

        reschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        task_desciption.setText(more_info_taskUpdate);
        displayTime.setText(formatTime());

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        //user_minute = minute;
        //user_hour = hourOfDay;

        java.util.Calendar c = java.util.Calendar.getInstance();
        c.set(java.util.Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(java.util.Calendar.MINUTE, minute);
        c.set(java.util.Calendar.SECOND, 0);

        //Adds a Day for Midnight
        if(c.before(java.util.Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }

        checklist.startAlarm(c, more_info_taskUpdate);

    }

    private String formatTime(){
        int m = min;
        int hour = hr;

        if(hour > 12){
            hour = hr - 12;
            String result = (hour + ":" + m);
            return result;
        }

        if(m == 0){
            String result = hour + ":" + m + "0";
            return result;
        }
        else {
            String result = (hour + ":" + m);
            return result;
        }
    }

    //Countdown Timer Implementation
    /*private void count_timer(){
        countDownTimer = new CountDownTimer(mTimeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeft = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimer = false;
            }
        }.start();
    }

    private void updateCountDownText(){
        //TODO
        int minute = (int)(mTimeLeft /  1000) / 60;
        int seconds = (int) (mTimeLeft / 1000) % 60;

        String timeLeftFormatted = String.format("%02d:%02d", minute, seconds);
        displayTime.setText(timeLeftFormatted);

    }*/


}
