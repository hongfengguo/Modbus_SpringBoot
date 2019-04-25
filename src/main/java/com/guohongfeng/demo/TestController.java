package com.guohongfeng.demo;

//网页显示
//启动web端口，将数据传入互联网上，
//地址：127.0.0.1:8082
//数据参数：水位，变化率

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
@Configuration
public class TestController {

    public static String statenumber ;
    public static double rate_water;
    TestModbusDemo testModbusDemo = new TestModbusDemo();
    @RequestMapping(value = {"/","view_of_waterlevel.html","/home"})
//    @ResponseBody
    public String hello1(Model model){
        statenumber = TestModbusDemo.i;
        rate_water = TestRateOfWaterLevel.rate;
        System.out.println(statenumber);
        model.addAttribute("name",statenumber);//当前水位
        model.addAttribute("name1",rate_water);//水位变化率
        return "view_of_waterlevel.html";
//          return "hello";
    }
}
