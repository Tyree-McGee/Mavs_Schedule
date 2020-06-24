package com.example.mavs_schedule_2019_2020;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.util.Date;

import static android.app.NotificationManager.IMPORTANCE_DEFAULT;

public class Nofity extends BroadcastReceiver {
    private static final String ID = "01";
    @Override
    public void onReceive(Context context, Intent intent) {
        Notification.Builder builder = new Notification.Builder(context);


        int s = intent.getIntExtra("index",10);
        Long temp = (Timelist.fetchTimes(s).getTime()) - (getTime.HOUR * 12);
        Date t = new Date(temp);
        String q = t.toString();
        q = q.substring(0, q.length() - 12);
        Notification notification = builder.setContentTitle("Gametime Reminder")
                .setContentText("At " + q + " PM"+" we play the "+ OpponentList.fetchOpponents(s))
                .setStyle(
                        new Notification.BigTextStyle()
                        .bigText("At " + q + " PM"+" we play the "+ OpponentList.fetchOpponents(s))
                        .setBigContentTitle("Gametime Reminder")
                )
                .setTicker("GameTime Alert!!")
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(ID);
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    ID,
                    "Gametime Reminders",
                    IMPORTANCE_DEFAULT
            );
            notificationManager.createNotificationChannel(channel);
            Log.d("Sent ", OpponentList.fetchOpponents(s));

        }
        notificationManager.notify(0, notification);
    }
}
