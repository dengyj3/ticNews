package com.zcgx.ticNews.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String getDateStr(Date date){
        return dateFormat.format(date);
    }
}
