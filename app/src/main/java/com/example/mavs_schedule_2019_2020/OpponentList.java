package com.example.mavs_schedule_2019_2020;

import java.util.ArrayList;
import java.util.Date;

public class OpponentList {
    public static ArrayList<String> opponents = new ArrayList<String>();

    public static void add (String s)
    {
        opponents.add(s);
    }

    public static void print()
    {
        for(String g: opponents)
        {
            System.out.println(g);
        }
    }
    public static String fetchOpponents(int i){
        return opponents.get(i);
    }
}
