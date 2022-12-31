package com.example.demo.Utils;

import java.sql.Time;
import java.util.Date;

public class DatesComparator {
    public static boolean isToday(Date appointment_date, Date current_date){
        return appointment_date.getDay() == current_date.getDay()
                && appointment_date.getMonth() == current_date.getMonth()
                && appointment_date.getYear() == current_date.getYear();
    }

    public static boolean within_hour(Time startTime, Date current_date){
        return startTime.getHours() - 1 == current_date.getHours();
    }
}
