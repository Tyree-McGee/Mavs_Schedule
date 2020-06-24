package com.example.mavs_schedule_2019_2020;

import android.app.Notification;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.TimerTask;

public class AndroidNoti extends TimerTask {
    // For Debugging Only
    private int i;
    private SendNotification s;
    public AndroidNoti(int i){this.i = i;}


    @Override
    public void run() {
        // Delay if set to 1 hour or 30 minutes
        // Kill if set to never
        Log.d("Notification","At " + Timelist.fetchTimes(i)+" we play the "+ OpponentList.fetchOpponents(i));
        Bundle b = new Bundle();
        b.putInt("Index",i);
        Message m = new Message();
        m.setData(b);
    }


}

