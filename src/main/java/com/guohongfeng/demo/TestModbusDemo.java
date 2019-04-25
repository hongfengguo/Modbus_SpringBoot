package com.guohongfeng.demo;

//modbus核心程序传输和传入接口
//可适用于多个传感器，通过列表返回

import java.util.Arrays;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.serotonin.io.serial.SerialParameters;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.msg.ReadDiscreteInputsRequest;
import com.serotonin.modbus4j.msg.ReadDiscreteInputsResponse;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersRequest;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersResponse;
import com.serotonin.modbus4j.msg.WriteRegistersRequest;
import com.serotonin.modbus4j.msg.WriteRegistersResponse;
public class TestModbusDemo {

    //设定MODBUS网络上从站地址
    private final static int SLAVE_ADDRESS = 1;
    // 串行波特率
    private final static int BAUD_RATE = 9600;
    public  static String i;
    public static Short accept01;
    public static short[] list01;
    public static long time01;
//    private static long time02;

    public static void main(String[] args) {
        SerialParameters serialParameters = new SerialParameters();
        // 设定MODBUS通讯的串行口
        serialParameters.setCommPortId("COM3");
        // 设定成无奇偶校验
        serialParameters.setParity(0);
        // 设定成数据位是8位
        serialParameters.setDataBits(8);
        // 设定为1个停止位
        serialParameters.setStopBits(1);
        // 设定端口名称
        serialParameters.setPortOwnerName("Numb nuts");
        // 设定端口波特率
        serialParameters.setBaudRate(BAUD_RATE);


        // 创建ModbusFactory工厂实例
        ModbusFactory modbusFactory = new ModbusFactory();
        // 创建ModbusMaster实例
        ModbusMaster master = modbusFactory.createRtuMaster(serialParameters);


        // 初始化
        try {
            master.init();
            readHoldingRegistersTest(master,SLAVE_ADDRESS,0,100);


        } catch (ModbusInitException e) {
            e.printStackTrace();
        } finally {
            master.destroy();
        }

    }

    /**
     * 读开关量型的输入信号
     * @param master 主站
     * @param slaveId 从站地址
     * @param start 起始偏移量
     * @param len 待读的开关量的个数
     */
    private static void readDiscreteInputTest(ModbusMaster master, int slaveId, int start, int len) {
        try {
            ReadDiscreteInputsRequest request = new ReadDiscreteInputsRequest(slaveId, start, len);
            ReadDiscreteInputsResponse response = (ReadDiscreteInputsResponse) master.send(request);
            if (response.isException())
                System.out.println("Exception response: message=" + response.getExceptionMessage());
            else
                System.out.println(Arrays.toString(response.getBooleanData()));
        }
        catch (ModbusTransportException e) {
            e.printStackTrace();
        }
    }




    /**
     * 读保持寄存器上的内容
     * @param master 主站
     * @param slaveId 从站地址
     * @param start 起始地址的偏移量
     * @param len 待读寄存器的个数
     */
    public static int readHoldingRegistersTest(ModbusMaster master,
                                               int slaveId, int start, int len) {
        try {
            ReadHoldingRegistersRequest request = new ReadHoldingRegistersRequest(
                    slaveId, start, len);
            ReadHoldingRegistersResponse response = (ReadHoldingRegistersResponse) master
                    .send(request);
            if (response.isException()) {
                System.out.println("Exception response: message="
                        + response.getExceptionMessage());
            } else {
                i = Arrays.toString(response.getShortData());

//数据的接入：返回值与public规定的static后面的类型有关，需要返回值要注意
//                i = "123";
//                double data_doble = Double.parseDouble(i);
//                System.out.println(Arrays.toString(response.getShortData()));
//                System.out.println(i);
                short[] list = response.getShortData();
                for(int i = 0; i < list.length; i++){
                    System.out.print(list[i] + "\r\n");
                    System.out.println("\r\n");
//                    System.out.print("完后数据传输"+"\r\n");
                    list01 = list;
                    //时间戳
                    time01 = System.currentTimeMillis();
//                    time02 = System.currentTimeMillis();
//                    System.out.println(time01);
                    accept01 = list[i];
                    return list[i];

                }

            }
        } catch (ModbusTransportException e) {
            e.printStackTrace();
        }
        return slaveId;
    }
//    @RequestMapping("hello1")
//    @ResponseBody
//    public String hello1(){
//        return "hello world";
//    }
    /**
     * 批量写数据到保持寄存器
     * @param master 主站
     * @param slaveId 从站地址
     * @param start 起始地址的偏移量
     * @param values 待写数据
     */
    public static void writeRegistersTest(ModbusMaster master, int slaveId, int start, short[] values) {
        try {
            WriteRegistersRequest request = new WriteRegistersRequest(slaveId, start, values);
            WriteRegistersResponse response = (WriteRegistersResponse) master.send(request);
            if (response.isException()){
                System.out.println("Exception response: message=" + response.getExceptionMessage());
            }
            else {
                System.out.println("Success");
            }
        }
        catch (ModbusTransportException e) {
            e.printStackTrace();
        }
    }
}
