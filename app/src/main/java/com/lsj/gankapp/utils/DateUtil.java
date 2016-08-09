package com.lsj.gankapp.utils;

/**
 * ClassName: DateUtil
 * Desc:
 * Created by lsj on 16/7/28.
 */
public class DateUtil {

    public static String parseDate(String date){
        return date.substring(0,date.lastIndexOf("T"));
    }
}
