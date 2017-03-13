package com.zhanghao.reader.utils;

import android.icu.text.SimpleDateFormat;
import android.util.Log;
import android.widget.Toast;

import com.zhanghao.reader.MyApplication;

import java.text.ParseException;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhanghao on 2016/11/22.
 */

public class TimeUtils {


    /**
     * 得到当前的日期
     * @param pattern
     * @return
     */
    public static String getCurrentDate(String pattern){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat();
        simpleDateFormat.applyPattern(pattern);
        return simpleDateFormat.format(System.currentTimeMillis());
    }

    /**
     * 得到当前的日期
     * @param inputPattern 输入的格式
     * @param outputPattern 输出的格式
     * @param inputString   输入的字符
     * @return
     */
    public static String getCurrentDate(String inputPattern,String outputPattern,String inputString){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat();
        simpleDateFormat.applyPattern(inputPattern);
        Date date,date1;
        Calendar calendar=Calendar.getInstance();
        try {
            date=simpleDateFormat.parse(inputString);
            calendar.setTime(date);
            date1=calendar.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(MyApplication.getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();;
            return null;
        }
        simpleDateFormat.applyPattern(outputPattern);
        return  date1!=null?simpleDateFormat.format(date1):null;
    }


    public static String getBeforeDate(String pattern,String date){
        // TODO: 2017/3/12 修改时间月份出错bug
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(pattern);
        Calendar calendar=Calendar.getInstance();
        try {
            Date datein=simpleDateFormat.parse(date);
            calendar.setTime(datein);

        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("格式错误");
            return null;
        }
        calendar.add(Calendar.DAY_OF_YEAR,-1);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getSessionDate(String pattern, String date){
        StringBuilder builder=new StringBuilder();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat();
        simpleDateFormat.applyPattern(pattern);
        int week;
        int month;
        int day;
        try {
           Date sessionDate=simpleDateFormat.parse(date);
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(sessionDate);
            week=calendar.get(Calendar.DAY_OF_WEEK)-1;
            day=calendar.get(Calendar.DAY_OF_MONTH);
            month=calendar.get(Calendar.MONTH);
        } catch (ParseException e) {
            e.printStackTrace();
            return "格式化错误！";
        }
        return builder.append(month)
                .append("月")
                .append(day)
                .append("日")
                .append("  ")
                .append("星期")
                .append(week)
                .toString();
    }

}
