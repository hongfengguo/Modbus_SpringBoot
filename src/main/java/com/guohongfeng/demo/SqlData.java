package com.guohongfeng.demo;

import java.sql.*;

public class SqlData {
    public static void main(String[] args) {
        Connection con;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/modbusdata";
        String user = "root";
        String password = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
                System.out.println("Succeeded connecting to the Database");
            }
            Statement statement = con.createStatement();
            String sql = "inseert into modbusdata(time,shuiwei,rate) values(?,?,?)";  //待会补充
            ResultSet rs = statement.executeQuery(sql);
            String time = null;
            String shuiwei = null;
            String rate = null;
            while (rs.next()){

            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("数据库没有安装");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
    }
    }
