package com.zcgx.ticNews.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    /**
     * 获取昨天的日期
     * @param date
     * @return
     * @throws ParseException
     */
    public static String getYesterDay(String date) throws ParseException {
        Date currentDate = dateFormat2.parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        currentDate = calendar.getTime();
        return dateFormat2.format(currentDate);
    }

    /**
     * 获取当天的星期
     * @param date
     * @return
     * @throws ParseException
     */
    public static String dateToWeek(Date date) throws ParseException  {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

}
