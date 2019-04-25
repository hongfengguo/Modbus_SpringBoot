package com.guohongfeng.demo;

//水位变化率计算
//依据公式：
//                                 当前水位 - 前一个水位
//          rate = -------------------------------------------------
//                           前一个水位*(当前时刻 - 前一个时刻)

//数据的接入：从DemoApplication中接入参数，共计四个Water_number_1,time_011,Water_number_2,time_012

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestRateOfWaterLevel {
    public static short[] Water_Data;
    private static int Water_number;
    private static int Water_number01;
    public static double rate;
    private static long time011;
    private static long time012;
    public static long changeliang;
    public static long changeliang01;
    public static double change_double;
    public static double change_double1;


    TestModbusDemo testModbusDemo = new TestModbusDemo();
    public static double RateWater(int water_number,long time011, int water_number01, long time012) {
//        Water_Data = TestModbusDemo.list01;
        int water_true = water_number;
        int water_true01 = water_number01;
        System.out.println("第一个数据进入时："+Water_number);
        System.out.println("第二个数据进入时："+Water_number01);
//        for (int i = 0; i < Water_Data.length; i++) {
//            Water_number = Water_Data[i];
//            time011 =  TestModbusDemo.time01;

            System.out.println(time011);
            changeliang = water_true*(time012-time011);
            change_double = (double)changeliang;
        if (water_true < water_true01){
                changeliang01 = (water_true01 - water_true)*10000;
            }

            else {
                changeliang01 = (water_true - water_true01)*10000;
            }
            change_double1 = (double)changeliang01;
            rate = (double) (change_double1/change_double)*100;
            System.out.println(rate);
//        }

        return rate;
    }
}
