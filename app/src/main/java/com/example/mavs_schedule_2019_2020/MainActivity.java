package com.example.mavs_schedule_2019_2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.preference.PreferenceManager;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.renderscript.RenderScript;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    final static int seasonLength = 82;
    private static String gameInfo[] = new String[seasonLength + 1];
    private static ArrayList<String> combo = new ArrayList<>();
    private ListView save[] = new ListView[seasonLength];
    ArrayAdapter<String> ad;
    String pref = null;
    public static Timer t[] = new Timer[fileInput.seasonLength];
    private static boolean started = false;
    AlarmManager alarmManager;
    PendingIntent pendingIntent[] = new PendingIntent[seasonLength];
     // The request code in it
    // is unique just use that for cancelling and incrementing


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("action","Create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            this.combo.clear();
            for (int i = 0; i < fileInput.seasonLength; i++) {
                t[i] = new Timer();
            }

            setupSharedPreferences();


            try {
                StringBuffer buf = new StringBuffer();
                InputStream fin = this.getResources().openRawResource(R.raw.schedule);
                BufferedReader read = new BufferedReader(new InputStreamReader(fin));
                String line = "";
                int i = 0;
                if (fin != null) {
                    while ((line = read.readLine()) != null) {
                        fileInput.add(line, i);
                        i++;
                        Log.d("Added", line);
                    }
                }
                fin.close();
            } catch (Exception e) {
                Log.d("ERROR", "readFile");
            }
            try {
                GameTime.getTipoff();
            } catch (Exception e) {
                Log.d("Error", "Tipoff");
            }

            makeNotifications();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.settings_1) {
            Intent i = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        pref = sharedPreferences.getString(getString(R.string.pref_notification), getString(R.string.two_value));
        Log.d("setupValue", sharedPreferences.getString(getString(R.string.pref_notification), getString(R.string.two_value)));
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        loadNotificationsFromPreference(sharedPreferences);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.preference.PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
        Log.d("destroy","destroyed");
    }

    private void loadNotificationsFromPreference(SharedPreferences sharedPreferences) {
        Log.d("Noti", sharedPreferences.getString(getString(R.string.pref_notification), getString(R.string.two_value)));
        changeNotification(sharedPreferences.getString(getString(R.string.pref_notification), getString(R.string.two_value)));
    }

    private void changeNotification(String pref_notification) {
        Log.d("Value", pref_notification);
        if (pref_notification.equals("120")) {
            Log.d("two","120");
            changeNotificationLength(120);
        }

        if (pref_notification.equals("60")) {
            Log.d("one", "60");
            changeNotificationLength(60);
        }

        if (pref_notification.equals("30")) {
            Log.d("thirty", "30");
            changeNotificationLength(30);
        }
        if(pref_notification.equals("15")){
            Log.d("fifteen", "15");
            changeNotificationLength(15);
        }
        if(pref_notification.equals("5")){
            Log.d("five", "5");
            changeNotificationLength(5);
        }
        if (pref_notification.equals("0")) {
            Log.d("0", "None");
            purgeSystem();
            Log.d("AndroidNoti", pref_notification);

        }
    }

    private void makeNotifications() {
        if (!started) {
            if(alarmManager == null){
                alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            }
            Long localtime = Long.parseLong(pref);
            Log.d("makeNotifications", pref);
            for (int i = 0; i < fileInput.seasonLength; i++) {
                if (Timelist.fetchTimes(i).getTime() > System.currentTimeMillis() + (localtime * getTime.MINUTE)) {
                    // Call function that takes over for processing notification

                    if ((Timelist.fetchTimes(i).getTime()) > (getTime.MINUTE * localtime)) {
                        long twoHoursBefore = (Timelist.fetchTimes(i).getTime()) - (getTime.MINUTE * localtime);

                        Intent notificationIntent = new Intent(this, Nofity.class);
                        notificationIntent.putExtra("index",i);
                        PendingIntent broadcast = PendingIntent.getBroadcast(this, i, notificationIntent,0);

                        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, twoHoursBefore, broadcast);

                        pendingIntent[i] = broadcast;
                        Log.d("AndroidNoti", pref);
                    }

                }
            }

        }
        started = true;
    }

    private void purgeSystem() {

        for (int i = 0; i < seasonLength; i++) {
            if(pendingIntent[i] != null)
            {
                alarmManager.cancel(pendingIntent[i]);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("combo", combo);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle outState) {
        super.onRestoreInstanceState(outState);
        this.combo.clear();
        for (int i = 0; i < fileInput.seasonLength; i++) {
            Long temp = (Timelist.fetchTimes(i).getTime()) - (getTime.HOUR * 12);
            Date t = new Date(temp);
            String s = t.toString();
            s = s.substring(0, s.length() - 12);
            combo.add(i + 1 + ". " + OpponentList.fetchOpponents(i) + " " + s + " PM");
            Log.d("Combo", combo.get(i));
            ListView v = findViewById(R.id.myView);
            ad = new ArrayAdapter<String>(MainActivity.this,
                    android.R.layout.simple_list_item_1, combo);
            v.setAdapter((ad));
            save[i] = v;
        }
        this.ad.notifyDataSetChanged();
    }

    private void changeNotificationLength(long timeVar){
        Log.d("Change Notification", Long.toString(timeVar));
        purgeSystem();
        if(alarmManager == null){
            alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        }
        for (int i = 0; i < fileInput.seasonLength; i++) {
            if (Timelist.fetchTimes(i).getTime() - (getTime.MINUTE * timeVar) > System.currentTimeMillis()) {
                long notificationBeforeTipoff = (Timelist.fetchTimes(i).getTime()) - (getTime.MINUTE * timeVar);
                Intent notificationIntent = new Intent(this, Nofity.class);
                notificationIntent.setAction("android.media.action.DISPLAY_NOTIFICATION");
                notificationIntent.putExtra("index",i);
                PendingIntent broadcast = PendingIntent.getBroadcast(this, i, notificationIntent,0);

                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, notificationBeforeTipoff, broadcast);

                pendingIntent[i] = broadcast;

                Log.d("AndroidNoti", pref);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        for (int i = 0; i < fileInput.seasonLength; i++) {
            Long temp = (Timelist.fetchTimes(i).getTime()) - (getTime.HOUR * 12);
            Date t = new Date(temp);
            String s = t.toString();
            s = s.substring(0, s.length() - 12);
            combo.add(i + 1 + ". " + OpponentList.fetchOpponents(i) + " " + s + " PM");
            Log.d("Combo", combo.get(i));
            ListView v = findViewById(R.id.myView);
            ad = new ArrayAdapter<String>(MainActivity.this,
                    android.R.layout.simple_list_item_1, combo);
            v.setAdapter((ad));
            save[i] = v;
        }
    }
}
