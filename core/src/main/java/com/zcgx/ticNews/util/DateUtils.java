package com.zcgx.ticNews.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static DateFormat dateFormat1 = new SimpleDateFormat("yyyy.MM.dd");
    private static DateFormat dateFormat2 = new SimpleDateFormat("yyyyMMdd");
    public static String getDateStr(Date date){
        return dateFormat.format(date);
    }

    public static String getDateYMD(Date date){
        return dateFormat1.format(date);
    }
    public static String getDateYMD1(Date date){
        return dateFormat2.format(date);
    }
}
