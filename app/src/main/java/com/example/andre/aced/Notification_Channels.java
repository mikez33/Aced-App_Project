package com.example.andre.aced;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class Notification_Channels extends Application {

    //Constants and Fields
    public static final String checkListUserInput = "checkList_priority";
    public static final String checkListUserPick = "checkList_user_priority";

    @Override
    public void onCreate() {
        super.onCreate();

        //Call Creation of Notification Channels
        createNotificationChannels();
    }

    private void createNotificationChannels(){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O){

            //Priority (Checklist) Notification Channel Creation
            NotificationChannel class_checkList_priority = new NotificationChannel(checkListUserInput, "priorityNotifications", NotificationManager.IMPORTANCE_HIGH);
            class_checkList_priority.setDescription("Notification for User Inputed Time");

            //User Custom Priority Notification Channel Creation
           // NotificationChannel class_checkList_userPriority = new NotificationChannel(checkListUserPick, "customUserNotification", NotificationManager.IMPORTANCE_HIGH);
            //class_checkList_userPriority.setDescription("Notification for User Entered Time to Complete Task");

            //Call to Create Notification Channel
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(class_checkList_priority);
            //manager.createNotificationChannel(class_checkList_userPriority);


        }
    }
}
