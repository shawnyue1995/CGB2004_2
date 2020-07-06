package com.tedu.studentmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import org.junit.Test;

import java.sql.Statement;
import com.tedu.util.JdbcUtil;
/*
 * 基于jdbc+mysql完成学生信息管理系统
 */
public class StudentManager {
    public static void main(String[] args) {
        boolean flag=false;
        while(!flag) {
            Scanner input=new Scanner(System.in);
            System.out.print("请输入操作,abcd任选一项:");
            System.out.print("a:查询学生信息    ");
            System.out.print("b:添加学生信息    ");
            System.out.print("c:修改学生信息    ");
            System.out.println("d:删除学生信息");
            String opt = input.nextLine();//a,b,c,d中的任意一项
            //接收用户输入的操作选项
            if (opt.equalsIgnoreCase("a")) {
                //查询学生信息
                findAll();
            }else if(opt.equalsIgnoreCase("b")) {
                //新增学生信息
                addStu();
            }else if (opt.equalsIgnoreCase("c")) {
                //根据id修改学生信息
                UpdateStu();
            }else if (opt.equalsIgnoreCase("d")) {
                //根据id删除学生信息
                deleteStu();
            }else if(opt.equalsIgnoreCase("exit")) {
                flag=true;
            } else {
                System.out.println("您的输入有误,请重新输入!!");
            }
        }
    }
    /*
     * 1.查询所有的学生信息,将查询到的学生信息直接打印在控制台
     */
    public static  void findAll() {
        Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        try {
            //注册驱动并获取连接
            conn=JdbcUtil.getConn();
            //获取传输器,并执行SQL语句
            stat=conn.createStatement();
            String sql="select * from stu";
            rs=stat.executeQuery(sql);
            while(rs.next()) {
                int id=rs.getInt("id");
                String name=rs.getString("name");
                String gender=rs.getString("gender");
                String addr=rs.getString("addr");
                double score=rs.getDouble("score");
                System.out.println(id+","+name+","+gender+","+addr+","+score);
            }
            System.out.println("============================================");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //释放资源
            JdbcUtil.close(conn, stat, rs);
        }
    }
    /*
     * 2.接收用户输入的学生信息,并添加学生信息
     */
    public static void addStu() {
        Scanner input1=new Scanner(System.in);
        //提示用户要添加的学生信息
        System.out.println("请输入要添加的学生编号,姓名,性别,地址,成绩(以空格分隔):");
        int id=input1.nextInt();//获取用户输入的id
        String name=input1.next();//获取用户输入的name
        String gender=input1.next();//获取用户输入的gender
        String addr=input1.next();//获取用户输入的addr
        double score=input1.nextDouble();//获取用户输入的score

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            //注册驱动并获取连接
            conn=JdbcUtil.getConn();
            //获取传输器,并执行SQL语句
            String sql="insert into stu value(?,?,?,?,?) ";
            ps= conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2,name);
            ps.setString(3,gender);
            ps.setString(4,addr);
            ps.setDouble(5,score);
            int rows=ps.executeUpdate();//不要二次传入sql
            if (rows>0) {
                System.out.println("添加成功!");
            }
            System.out.println("===============================");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //释放资源
            JdbcUtil.close(conn, ps, rs);
        }

    }
    /*
     * 3.根据id修改学生信息
     */
    public static void UpdateStu() {
        Scanner input=new Scanner(System.in);
        //提示用户输入修改后的学生信息
        System.out.println("请输入修改的学生编号,姓名,性别,地址,成绩(以空格分隔)");
        int id=input.nextInt();
        String name=input.next();
        String gender=input.next();
        String addr=input.next();
        double score=input.nextDouble();

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            //注册驱动并获取连接
            conn=JdbcUtil.getConn();
            //获取传输器,并执行SQL语句
            String sql="update stu set name=?,gender=?,addr=?,score=? where id=?";
            ps= conn.prepareStatement(sql);
            //设置sql语句中的参数
            ps.setString(1, name);
            ps.setString(2, gender);
            ps.setString(3, addr);
            ps.setDouble(4, score);
            ps.setInt(5, id);
            //执行sql语句
            int rows=ps.executeUpdate();
            if (rows>0) {
                System.out.println("学生信息修改成!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //释放资源
            JdbcUtil.close(conn, ps, rs);
        }

    }
    /*
     * 4.根据id删除学生信息
     */
    public static void deleteStu() {
        Scanner input=new Scanner(System.in);
        System.out.println("请输入要删除的学生编号:");
        int id=input.nextInt();

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            //注册驱动并获取连接
            conn=JdbcUtil.getConn();
            //获取传输器,并执行SQL语句
            String sql="delete from stu where id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1, id);
            int rows=ps.executeUpdate();
            if (rows>0) {
                System.out.println("删除成功!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //释放资源
            JdbcUtil.close(conn, ps, rs);
        }

    }


}
