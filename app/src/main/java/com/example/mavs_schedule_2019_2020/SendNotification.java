package com.example.mavs_schedule_2019_2020;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import static android.os.Build.ID;

public class SendNotification extends AppCompatActivity {
    static String ID = "01";
    private NotificationManager manage;
    private int i;

    public SendNotification(int k) {
        this.i = k;

    }


    public void createChannels(int s) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(ID, "Gametime Notifications",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Know when the team plays");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationManager manager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"01")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Gametime Alert")
                .setContentText("At " + Timelist.fetchTimes(i)+" we play the "+ OpponentList.fetchOpponents(i));
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            builder.setChannelId(ID);
        }
        manager.notify(01,builder.build());



    }


}

