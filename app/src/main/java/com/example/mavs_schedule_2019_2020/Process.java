package com.example.mavs_schedule_2019_2020;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Process extends AppCompatActivity /*implements SharedPreferences.OnSharedPreferenceChangeListener */{
    // Not Needed
    /*public static Timer t[] = new Timer[fileInput.seasonLength];
    public static void p(int i) {

        for( i = 0; i < fileInput.seasonLength; i++)
        {
            if(Timelist.fetchTimes(i).getTime() > System.currentTimeMillis())
            {
                // Call function that takes over for processing notification
                t[i] = new Timer();
                long twoHoursBefore = (Timelist.fetchTimes(i).getTime()) - (getTime.MINUTE * 120);
                long current = System.currentTimeMillis();
                long nextGame = twoHoursBefore - current ;
                TimerTask k = new AndroidNoti(i);
                t[i].schedule(k, nextGame);

            }
        }
    }



    /*@Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        changeNotification(sharedPreferences.getString(getString(R.string.pref_notification),getString(R.string.two_value)));
    }

    private void changeNotification(String pref_notification){
        Log.d("Change Notification", "I'm in");
        if(pref_notification == "120")
        {
            for( int i = 0; i < fileInput.seasonLength; i++)
            {
                if(Timelist.fetchTimes(i).getTime() - (getTime.MINUTE * 120)> System.currentTimeMillis())
                {
                    long twoHoursBefore = (Timelist.fetchTimes(i).getTime()) - (getTime.MINUTE * 120);
                    long current = System.currentTimeMillis();
                    long nextGame = twoHoursBefore - current ;
                    t[i].cancel();
                    t[i].schedule(new AndroidNoti(i), nextGame);
                }
            }
        }

        if(pref_notification == "60")
        {
            for( int i = 0; i < fileInput.seasonLength; i++)
            {
                if(Timelist.fetchTimes(i).getTime() - (getTime.MINUTE * 60)> System.currentTimeMillis())
                {
                    long oneHourBefore = (Timelist.fetchTimes(i).getTime()) - (getTime.MINUTE * 60);
                    long current = System.currentTimeMillis();
                    long nextGame = oneHourBefore - current ;
                    t[i].cancel();
                    t[i].schedule(new AndroidNoti(i), nextGame);
                }
            }
        }

        if(pref_notification == "30")
        {
            for( int i = 0; i < fileInput.seasonLength; i++)
            {
                if(Timelist.fetchTimes(i).getTime() - (getTime.MINUTE * 30)> System.currentTimeMillis())
                {
                    long halfHourBefore = (Timelist.fetchTimes(i).getTime()) - (getTime.MINUTE * 30);
                    long current = System.currentTimeMillis();
                    long nextGame = halfHourBefore - current ;
                    t[i].cancel();
                    t[i].schedule(new AndroidNoti(i), nextGame);
                }
            }
        }
        if(pref_notification == "0")
        {
            for(int i = 0; i < fileInput.seasonLength; i++)
            {
                t[i].cancel();
            }
        }
    }*/
}
