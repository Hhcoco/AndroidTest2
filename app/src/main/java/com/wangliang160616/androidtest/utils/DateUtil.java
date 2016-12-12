package com.wangliang160616.androidtest.utils;

import android.util.Log;
import android.widget.Toast;

/**
 * Created by wangliang on 2016/9/20.
 */
public class DateUtil {
    public static String [] weekName = {"日" ,"一" ,
    "二" , "三" , "四" , "五" , "六"};
    /*计算每一个月有多少天*/
    public static int getMonthDay(int year , int month){
        if(month > 12){
            year += 1;
            month = 1;
        }else if(month <1){
            year -=1;
            month = 12;
        }
        Log.v("out" , "当前年月："+year+"/"+month);
        /*默认每个月的天数*/
        int days [] = {31 , ((year % 4 == 0&& year %100 == 0) && year % 400 == 0)?29:28 , 31 ,30 , 31 , 30 , 31, 31 ,30 , 31 ,30,31};
        return days[month-1];
    }
}
