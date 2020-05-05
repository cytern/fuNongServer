package com.funong.funong.plugin;

import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangeDate {
    static SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public  Date getDate() {
        Date date = new Date();
        return date;
    }


    public  Date changeDate(String date) throws ParseException {
        Date date1 = myFmt2.parse(date);
        return date1;
    }


    public  String changeTime(Date date) {
        String date2 = myFmt2.format(date);
        return date2;
    }


    public  String getTime() {
        Date date3 = new Date();
        String time = myFmt2.format(date3);
        return time;
    }
}
