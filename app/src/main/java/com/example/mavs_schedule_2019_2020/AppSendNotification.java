package com.example.mavs_schedule_2019_2020;

import android.app.Notification;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;

public class AppSendNotification extends AppCompatActivity {
    // Not Needed Class
    int integer;
    private SendNotification s;

    public AppSendNotification(int d){
        this.integer = d;
        create();
    }

    public void create(){
       /* s = new SendNotification(this);
        Notification.Builder n = s.getAndroidChannelNotification(integer);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            s.getManager().notify(02, n.build());
        }
        else{
            s.notify();
        }*/
    }
}
