package com.example.mavs_schedule_2019_2020;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Timelist extends AppCompatActivity {
    private static ArrayList <Date> gameTimes = new ArrayList<Date> ();
    private static ArrayList <String> combo = new ArrayList<>();

    public static void add(Date d)
    {
        gameTimes.add(d);
    }


    protected static void print()
    {
        for(int i = 0; i < fileInput.seasonLength; i++)
        {
            Long temp = (fetchTimes(i).getTime()) - (getTime.HOUR *12);
            Date t = new Date(temp);
            combo.add(OpponentList.fetchOpponents(i) + " " + t.toString());
            Log.d("Combo", combo.get(i));
        }
    }
    public static Date fetchTimes(int i){
        return gameTimes.get(i);
    }
}
