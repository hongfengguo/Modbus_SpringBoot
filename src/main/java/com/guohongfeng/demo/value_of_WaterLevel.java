package com.guohongfeng.demo;
//****************************
//水位报警阈值值
//***************************
public class value_of_WaterLevel {

//    h1和h2分别作用于巷道顶部和底部的安全水头
    public static  double h1;
//    k1和k2分别为巷道顶，底板岩层的抗张强度
    public static double K1;
    //T1和T2为巷道顶，和底部岩层的厚度；
    public static double T1;
//    p1和p2是巷道顶部和地板压层的密度
    public static double p1;
    //l1和l2分别为巷道顶、底部之宽度
    public static double l1;

    //    h1和h2分别作用于巷道顶部和底部的安全水头
    public static  double h2;
    //k1和k2分别为巷道顶，底板岩层的抗张强度
    public static double K2;
    //T1和T2为巷道顶，和底部岩层的厚度；
    public static double T2;
    //    p1和p2是巷道顶部和地板压层的密度
    public static double p2;
    //l1和l2分别为巷道顶、底部之宽度
    public static double l2;

//    巷道顶的安全水头，不造成隔水顶底板突水的的承压水水头最大值
    private static double func(double k, double t, double p, double l){
        System.out.println("开始计算");
        double a_process = (t*t)/(l*l);
        double b_process = 2*k*a_process;
        double h=b_process - p*t;
        return h;
    }

    public static double[] main(String[] arg){
        h1 = func(K1,T1,p1,l1);
        h2 = func(K2,T2,p2,l1);
        double []array = new double[2];
        array[0]=h1;
        array[1]=h2;
        return array;
    }


}
