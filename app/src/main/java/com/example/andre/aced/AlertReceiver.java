package com.example.andre.aced;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class AlertReceiver extends BroadcastReceiver {

    @SuppressLint("ResourceAsColor")
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        android.support.v4.app.NotificationCompat.Builder builder = new android.support.v4.app.NotificationCompat.Builder
                (context, Notification_Channels.checkListUserInput);

        Intent broadCastIntent = new Intent(context, Checklist.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, broadCastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent broadCastIntent1 = new Intent(context, Checklist.class);
        broadCastIntent1.setAction("menuFragment");
        PendingIntent pendingIntent1 = PendingIntent.getActivity(context, 1 , broadCastIntent1, PendingIntent.FLAG_UPDATE_CURRENT);


        //Builds Notification
        builder.setSmallIcon(R.drawable.noti);
        builder.setContentTitle("You Have A Incomplete Task!");
        builder.setContentText(com.example.andre.aced.Checklist.titleTask);
        builder.setPriority(android.support.v4.app.NotificationCompat.PRIORITY_HIGH);
        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent );
        builder.setColor(R.color.colorPrimaryDark);

        notificationManager.notify(1, builder.build());
    }
}
