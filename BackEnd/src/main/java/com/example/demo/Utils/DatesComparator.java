package com.example.demo.Utils;

import java.sql.Time;
import java.util.Date;

public class DatesComparator {
    public static boolean isToday(Date appointment_date, Date current_date){
        boolean isToday = false, within_hour = false;
        if(appointment_date.getDay() == current_date.getDay()
        && appointment_date.getMonth() == current_date.getMonth()
        && appointment_date.getYear() == current_date.getYear()){
            isToday = true;
        }

        return isToday;
    }

    public static boolean within_hour(Time startTime, Date current_date){
        System.out.println("appointment time = "  + startTime.getHours() + "and current" + current_date.getHours());
        if(startTime.getHours() - 1 == current_date.getHours())
            return true;
        else
            return false;
    }
}
