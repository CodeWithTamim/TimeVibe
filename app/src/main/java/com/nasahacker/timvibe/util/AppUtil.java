package com.nasahacker.timvibe.util;


public final class AppUtil {

    public static String getFormatedTime(int time) {
        int seconds = ((time % 86400) % 3600) % 60;
        int minutes = ((time % 86400) % 3600) / 60;
        int hours = ((time % 86400) / 3600);
        return String.format("%02d", hours)
                + ":" + String.format("%02d", minutes)
                + ":" + String.format("%02d", seconds);
    }


    public static int getMinutePercentage(int time) {
        return ((time % 86400) % 3600) % 60;
    }


}
