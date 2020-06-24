package com.example.mavs_schedule_2019_2020;

import android.util.Log;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class fileInput {
    final static int seasonLength = 82;

    private static String gameInfo[] = new String[seasonLength+1];


    public static void readFile() throws Exception{
        File schedule = new File("C:\\Users\\ut10r\\IdeaProjects\\test\\src\\com\\example\\mavs_schedule_2019_2020\\schedule.txt");
        Scanner s = new Scanner(schedule);
        SimpleDateFormat fo =new SimpleDateFormat("MM dd, yyyy HH:mm");
        int i = 0;

        while(s.hasNextLine())
        {
            gameInfo[i] = s.nextLine();
            i++;
        }
    }

    public static String getGameInfo(int i)
    {
        return gameInfo[i];
    }

    public static void add(String s, int in)
    {
        gameInfo[in] = s;
        Log.d("fileinput added", s);
    }



}
