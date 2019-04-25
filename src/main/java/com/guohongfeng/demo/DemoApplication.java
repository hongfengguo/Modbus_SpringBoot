package com.guohongfeng.demo;

//主函数运行入口
//五部分类运行 + 一个重复运行获取代码 + 时间延长代码
//四部分类：
//TestModbusDemo testModbusDemo = new TestModbusDemo();
//TestController testController = new TestController();
//DataToTxt dataToTxt = new DataToTxt();
//DateTimeget dateTimeget = new DateTimeget();
//testRateOfWaterLevel.RateWater(Water_number_1,time_011,Water_number_2,time_012);

//一个重复运行代码
//重复运行modbus类代码
//作用：表示modbus多次获取数据，将数据存入后一个变化的水位中，利用时间间隔求变化率

//时间延长码：
//作用：增加时间间隔，保证数据的准确性

//重点强调参数：
//testRateOfWaterLevel.RateWater(Water_number_1,time_011,Water_number_2,time_012);
//参数列表：前一个水位，前一个水位录制的时间，后一个水位，后一个水位录制的时间

import com.guohongfeng.demo.TestModbusDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DemoApplication {
    public static long time_011;
    public static long time_012;
    public static int Water_number_1;
    public static int Water_number_2;
    public static short[] Water_Data01;
    public static short[] Water_Data02;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);//主函数运行开始
        // write your code here
        TestModbusDemo testModbusDemo = new TestModbusDemo();
        TestController testController = new TestController();
        DataToTxt dataToTxt = new DataToTxt();
        DateTimeget dateTimeget = new DateTimeget();
        TestRateOfWaterLevel testRateOfWaterLevel = new TestRateOfWaterLevel();
//        testController.hello1(Model model);
        while (true) {//循环抽取数据
            testModbusDemo.main(args);
            Water_Data01 = TestModbusDemo.list01;
//普遍性，针对多个传感器共同传过来的数据，使用list传输，此时调用用for循环依次取出，当前为一个传感器
            for (int i = 0; i < Water_Data01.length; i++) {
                Water_number_1 = Water_Data01[i];
//                System.out.println(time011);
            }
            time_011 =  TestModbusDemo.time01;
//            System.out.println("数据进入一号："+Water_number_1);
            dataToTxt.main(args);//数据的存入文本
            dateTimeget.main(args);//日期存入

            try{//时间休息
                Thread.sleep(2000);
            }catch (Exception e){
                System.exit(0);
            }
            for (int n=0;n<20;n++) {//重复循环，获取多个数据
                testModbusDemo.main(args);
//                System.out.println("循环时候的数据："+TestModbusDemo.list01[0]);
            }


            Water_Data02 = TestModbusDemo.list01;
//            System.out.println("这个时候的数据"+Water_Data02[0]);
            for (int j = 0; j < Water_Data02.length; j++) {
                Water_number_2 = Water_Data02[j];
//                System.out.println("数据进入二号"+Water_Data01[j]);
            }
            time_012 =  TestModbusDemo.time01;
//            System.out.println("数据："+Water_number_1);

            testRateOfWaterLevel.RateWater(Water_number_1,time_011,Water_number_2,time_012);//调用水位变化率的类

        }
    }
}