package com.example.mavs_schedule_2019_2020;

public class GameTime {

    public static void getTipoff()throws Exception{
        for(int i = 0; i < fileInput.seasonLength; i++) {
            String test = fileInput.getGameInfo(i);
            test = (test.substring(test.lastIndexOf(',') + 1, test.indexOf('p')));
            // System.out.println(test);
            getTime.calcTime(test);
        }
        getOpponent();
    }

    public static void getOpponent()throws Exception{
        for(int i = 0; i < fileInput.seasonLength; i++){
            String o = fileInput.getGameInfo(i);
            o = (o.substring(o.lastIndexOf('$') +1 ));
            OpponentList.add(o);
        }
    }

}
