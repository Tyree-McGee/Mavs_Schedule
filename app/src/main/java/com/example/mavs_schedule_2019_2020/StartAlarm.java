package com.example.mavs_schedule_2019_2020;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StartAlarm extends Thread{
   /*
   // Not Needed For PC Only
   private static int i = 0;
    private static int time = 2;

    public static void setTime(int t){
        time = t;
    }
    public static void go(){
       //  ScheduledExecutorService s = Executors.newScheduledThreadPool(1);
        for( i = 0; i < fileInput.seasonLength; i++)
        {
            if(Timelist.fetchTimes(i).getTime() - (getTime.HOUR * time)> System.currentTimeMillis())
            {
                // Call function that takes over for processing notification
                //Process.p(i);
            }
        }
    }

    public static int igetter()
    {
        return i;
    }*/
}
