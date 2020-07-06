package com.tedu.reflect;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tedu.pojo.Emp;

public class TestReflect2 {
    public static void main(String[] args) throws Exception {
        //1.注册数据库驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取数据库连接
        Connection conn=DriverManager.getConnection("jdbc:mysql:///yonghedb?characterEncoding=utf-8","root","root");
        //3.获取传输器
        Statement stat=conn.createStatement();
        //4.发送SQL到服务器执行并返回执行结果
        String sql="select * from emp where id=1";
        ResultSet rs=stat.executeQuery(sql);
        //5.处理结果(利用反射将结果集中第一行数据封装到第一个Emp对象中,再将Emp对象封装到List集合中)
        List list=new ArrayList();
        while (rs.next()) {
            //5.1根据权限定类名获取该类的字节码对象
            Class empClz=Class.forName("com.tedu.pojo.Emp");
            //5.2根据类的字节码对象获取该类的实例:Emp emp=new Emp();
            Emp emp=new Emp();
            System.out.println(emp);
            //5.3根据字节码对象获取该类的所有属性字段组成的数组
            Field[]  Empfields=empClz.getDeclaredFields();
            //5.4遍历所有的字段数组
            for (Field field : Empfields) {
                //设置属性的访问权限,即使private属性也可以进行访问
                field.setAccessible(true);
                //遍历结果集中第一行中的所有列
                ResultSetMetaData rsmd=rs.getMetaData();
                //根据rs中列的个数
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    //获取每一列的列名
                    String colName=rsmd.getCatalogName(i+1);
                    //判断列名和当前外层循环正在便利的属性名是否相同
                    if (colName.equals(field.getName())) {
                        System.out.println("列名"+colName+"---属性名:"+field.getName());
                        //如果相同则将当前行及当前列中的值,设置给当前属性
                        field.set(emp, rs.getObject(i+1));
                    }
                }
            }
            list.add(emp);
        }
        //6.释放资源
        rs.close();
        stat.close();
        conn.close();
        System.out.println("TestJdbc.main()...");
    }
}
