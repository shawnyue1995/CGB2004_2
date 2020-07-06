package com.tedu.ps;

import com.tedu.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

//模拟用户登录的案例
public class LoginUser {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        //1.提示用户登录,提示用户输入用户名并接收;
        System.out.println("请登录:");
        System.out.println("请输入用户名:");
        String user=sc.nextLine();
        //2.提示用户输入密码
        System.out.println("请输入密码:");
        String psw=sc.nextLine();
        //3.根据用户和密码进行登录
        Login(user,psw);
    }
    private static void Login(String user,String psw){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn= JdbcUtil.getConn();
            ps= (PreparedStatement) conn.createStatement();
            String sql="select * from user where password='"+psw+"'and username='"+user+"'";
            ps.executeQuery(sql);
            rs=ps.executeQuery(sql);
            //处理结果(检查是否允许登录)
            if (rs.next()){
                System.out.println("登陆成功");
            }else{
                System.out.println("登录失败,用户名密码错误!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn,ps,rs);
        }
    }
}
