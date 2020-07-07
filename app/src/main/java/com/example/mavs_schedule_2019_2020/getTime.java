package com.example.mavs_schedule_2019_2020;

import java.text.SimpleDateFormat;
import java.util.Date;

public class getTime {
    public static final long HOUR = 3600*1000;
    public static final long MINUTE = 1000*60;
    public static void calcTime(String sTime) throws Exception
    {
        SimpleDateFormat f = new SimpleDateFormat("MM dd yyyy HH:mm");
        Date tipoff = f.parse(sTime);
        Date realTip = new Date(tipoff.getTime() + 12 * HOUR);
        Timelist.add(realTip);
    }
}
