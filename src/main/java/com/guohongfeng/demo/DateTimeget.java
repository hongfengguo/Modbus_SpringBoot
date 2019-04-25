package com.guohongfeng.demo;
//当前日期的获取，表示为时间戳，供DataToTxt中的存储的时间显示：
//显示格式为：yyy-MM-dd HH:mm:ss
import java.util.Date;
import java.text.SimpleDateFormat;
public class DateTimeget {
    static SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
    public static String time01 = df.format(new Date());
    public  static void main(String[] args){
        SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        time01 = df.format(new Date());
        System.out.println(df.format(new Date()));
    }
}
