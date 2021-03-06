package com.guohongfeng.demo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class x_2_1 {
    //正态分布平均值
    public static double u;
    //方差
    public static double derta;
    //样本的个数
    public static int n;
    public static double X;
    //样本存放数组
    public static String[] strs;
    public static Float[] numbers;
    //定义常量
    static final double pi = 3.141593;
    static final double e = 2.71829;

    private static List<Double> func(){
        List<Double> list = new ArrayList<>();
        List<Double> list2 = new ArrayList<>();
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            String str = "";
            String str1 = "";
            //获取文本文件
            fis = new FileInputStream("F:\\datatotxt\\WaterLevel.txt");
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
//            for (int i = 0;i<20; i++){
//            while ((str = br.readLine()) != null) {
//                //获取文本中的数据
//                list.add(X);
//            }
            for (int i = 0;i<2; i++){
                str = br.readLine();
                strs = str.split(" ");
            }
            for (int j = 0;j<strs.length;j++){
                double y = Float.parseFloat(strs[j]);
                list.add(y);
//                System.out.println(list.get(j));
            }
            for (int b=0;b<list.size();b++){
                if (list.get(b) == 0){
                    list2.add(list.get(b)+0.5);
                }
                else if (list.get(b)<=1.5){
                    list2.add(list.get(b)*100);
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("找不到该文件");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("无法读取");
        }
        return list2;
    }
    //计算组距函数
    private static double zuju(){
        double x=0;
        //数据为1440个，则确定组数为120个每组，即划分成12组
        //运用方差求出组数
        double jicha = 150;
        x = 150/5;
        //组距为30
        System.out.println(x);
        return x;
    }
    //计算每组出现的频数
    private static int[] pinshu(){
        double x = zuju();
        List<Double> list2 = func();
        int []array = new int[]{0,0,0,0,0,};
        for(int i = 0; i<list2.size(); i++){
            if (list2.get(i) >0 && list2.get(i) < x){
                array[0] += 1;
                continue;
            }
            else if (list2.get(i)>=x && list2.get(i)<2*x){
                array[1] += 1;
                continue;
            }
            else if (list2.get(i)>=2*x && list2.get(i)<3*x){
                array[2] += 1;
                continue;
            }
            else if (list2.get(i)>=3*x && list2.get(i)<4*x){
                array[3] += 1;
                continue;
            }
            else if (list2.get(i)>=4*x && list2.get(i)<5*x){
                array[4] += 1;
                continue;
            }
        }
        for(int ll=0;ll<array.length;ll++){
            System.out.println("array:"+array[ll]);
        }
        return array;
    }

    //正态分布函构造
    private static double zhengtai(double x){
//        double a = Math.pow(2*pi,-1/2);
//        double b = Math.pow(e,-(x*x)/2);
//        double zhengtaizhi = a*b;
        double y=Math.abs(x);
        double y2 = y*y;
        double z=Math.exp(-0.5 * y2) * 0.398942280401432678;
        double p=0;
        int k=28;
        double s = -1;
        double fj=k;

        if(y>3){
//当y>3时
            for(int i=1;i<=k;i++){
                p = fj / (y+p);
                fj=fj - 1.0;
            }
            p = z / (y+p);
        }else{
//当y<3时
            for(int i=1;i<=k;i++){
                p = fj * y2 / (2.0*fj +1.0 + s * p);
                s = -s;
                fj = fj - 1.0;
            }
            p = 0.5 - z * y / ( 1 - p );
        }
        if(x>0) p = 1.0 - p;
            return p;
    }


//        double a = x*x;
//        double zhengtaizhi = Math.exp(-0.5*a)*0.3984942280401432678;
//        System.out.println("这个值是"+zhengtaizhi);
//        return zhengtaizhi;
//    }
    //计算卡方
    private static  double x2text(){
        List<Double> p = new ArrayList<>();
        int []arry1 = pinshu();
        double kafang=0;
        double x =zuju();
        double i = 0;
        double derta1 = Math.sqrt(derta);
        while (i < 150){
            double ui = (i-u)/derta1;
            double fi = zhengtai(ui);
            double ui_1 = (i+x-u)/derta1;
            double fi_1 = zhengtai(ui_1);
            double fs = fi_1-fi;
            p.add(fs);
            i += x;
//            System.out.println("循环制："+i);
        }
        System.out.println("p:"+p);
        for (int m=0;m<p.size();m++ ){
//            kafang += (arry1[m] - 12*Math.pow(p.get(m),2))/(12*p.get(m));
            kafang += Math.pow(arry1[m]-507*p.get(m),2)/(507*p.get(m));
            System.out.println("卡方的计算值"+kafang);
        }
        return kafang;
    }

    public static void main(String arg[]) {
        List<Double> list1 = func();
        double s = 0;
        double e1 = 0;
        n = list1.size();
        //平均值
        for (int i = 0;i<list1.size();i++){
            s  += list1.get(i);
        }
        u = s/n;

        System.out.println("这是平均值"+u);
        System.out.println(n);

        //方差
        for (int i = 0;i<list1.size();i++){
            e1 += (list1.get(i) - u)*(list1.get(i) - u);
        }
        derta = e1/(n-1);
        System.out.println("这是方差值"+derta);
        double kafang = x2text();
        System.out.println("kafang:"+kafang);
    }
}

//查表得到t检测的数据为无穷时候，约为2.0，上下浮动为（2,6），最高的阈值为6
