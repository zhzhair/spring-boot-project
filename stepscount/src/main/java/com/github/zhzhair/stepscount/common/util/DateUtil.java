package com.github.zhzhair.stepscount.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhaozh01
 * on 2018/1/8.
 */
public class DateUtil {

    /**
     * 是否是日期字符串
     */
    private static boolean isDate(String dateStr){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 日期字符串比较
     */
    public static boolean compareDate(String beginDate, String endDate){
         return (isDate(beginDate) && isDate(endDate) && parseDate(beginDate).before(parseDate(endDate)))
                 || beginDate.equals(endDate);
    }

    /**
     * 字符串转日期
     */
    public static Date parseDate(String dateStr){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 两个日期相差天数
     */
    public static int getDayCount(String beginDate, String endDate){
        if(compareDate(beginDate, endDate)){
            long mseconds = parseDate(endDate).getTime()-parseDate(beginDate).getTime();
            return (int)(mseconds/86400000L);
        }
        return 0;
    }

    /**
     * 给定日期字符串n天前的日期
     */
    public static String getDateStr(String dateStr, int n){
        if(isDate(dateStr)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(parseDate(dateStr).getTime() - n * 86400000L);
        }
        return null;
    }

    /**
     * 获取两个日期之间的所有日期字符串
     */
    public static String[] getDateStr(String beginDate, String endDate){
        if(compareDate(beginDate, endDate)){
            int dayOfCount = getDayCount(beginDate, endDate) + 1;
            String[] dateStrArr = new String[dayOfCount];
            for (int i = 0; i < dayOfCount; i++) {
                dateStrArr[i] = getDateStr(beginDate, -i);
            }
            return dateStrArr;
        }
        return null;
    }

    /**
     * n天前的日期
     */
    public static String getDateStr(int n){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(System.currentTimeMillis() - n * 86400000L);
    }

    /**
     * n天前的日期
     */
    public static String getDateStr(long time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(time);
    }

}
