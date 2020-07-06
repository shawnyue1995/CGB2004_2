package com.tedu.ps;

import com.tedu.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

//模拟用户登录
public class LoginUser2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        //1.提示用户登录,提示用户输入用户名并接收用户名
        System.out.println("请登录:");
        System.out.println("请输入用户名:");
        String user=sc.nextLine();
        //2.提示用户输入密码
        System.out.println("请输入密码:");
        String psw=sc.nextLine();
        //3.根据用户名和密码登录
        Login(user,psw);
    }
    private static void Login(String user,String psw){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn= JdbcUtil.getConn();
            ps= (PreparedStatement) conn.createStatement();
            String sql="Select * from where username=? and password=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,user);
            ps.setString(2,psw);
            rs=ps.executeQuery();
            if (rs.next()){
                System.out.println("登陆成功!");
            }else{
                System.out.println("登录失败,用户名莫密码错误!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn,ps,rs);
        }
    }
}
