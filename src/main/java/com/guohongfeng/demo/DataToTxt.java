package com.guohongfeng.demo;
import java.sql.*;
//实现数据存入txt中
// 表示为[数据]+[当前数据产生的日期]+[两时间间隔的变化率]
//import org.springframework.stereotype.Service;
import java.util.List;
import com.sun.xml.internal.bind.v2.TODO;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
public class DataToTxt {
    public static  String datanumber;
    public static short accept01;
    TestModbusDemo testModbusDemo = new TestModbusDemo();
    TestRateOfWaterLevel testRateOfWaterLevel = new TestRateOfWaterLevel();
    DateTimeget dateTimeget = new DateTimeget();
    public  static void main(String[] args){
        File file = null;
        FileWriter fw = null;
        file = new File("F:\\datatotxt\\ModbusData.txt");
        try {
            if (!file.exists()){
                file.createNewFile();
            }
            fw = new FileWriter(file,true);

//            for (int i =1;i<=3000;i++){
//                fw.write("abcdefg"+i+",");
//                fw.write("ssssssssss"+i+"\r\n");
//                fw.flush();
//            }
            datanumber = TestModbusDemo.i;//将modbus类中的数据接收过来，采用公共变量，若采用私有变量则使用get_set函数
            accept01 = TestModbusDemo.accept01;
//            System.out.println("当前测试数据"+accept01);
//            fw.write("当前水位数据和时间为："+datanumber+ "  " +DateTimeget.time01+"\r\n");//数据接收和日期
            fw.write("当前水位数据和时间为： "+accept01+ " " +DateTimeget.time01+"\r\n");//数据接收和日期
            fw.write("变化率："+TestRateOfWaterLevel.rate+"\r\n");
            fw.flush();
            System.out.println("\n"+"写数据成功");
        }catch (IOException e){
//            TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            if (fw!=null){
                try {
                    fw.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
