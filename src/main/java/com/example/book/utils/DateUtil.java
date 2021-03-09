package com.example.book.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @program: bookmanager
 * @Author: Simon_Cao
 * @Date: 2021/3/6 19:45
 * @Description:
 */
public class DateUtil {
    public static Date getDateAdd(int days){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -days);
        return c.getTime();
    }

    /**
     * 获取前几天的日期
     *
     * @param days 前n天
     * @return 最近几天的日期数组
     */
    public static List<String> getDaysBetwwen(int days){
        List<String> dayss = new ArrayList<>();
        Calendar start = Calendar.getInstance();
        start.setTime(getDateAdd(days));
        long startTime = start.getTimeInMillis();
        Calendar end = Calendar.getInstance();
        end.setTime(new Date());
        long endTime = end.getTimeInMillis();
        long oneDay = 1000 * 60 * 60 * 24L;
        long time = startTime;
        while (time <= endTime) {
            Date d = new Date(time);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            dayss.add(df.format(d));
            time += oneDay;
        }
        return dayss;
    }


    public static void main(String[] args) {
        List<String> daysBetwwen = getDaysBetwwen(2);
        daysBetwwen.forEach(s -> System.out.println(s));
    }
}
