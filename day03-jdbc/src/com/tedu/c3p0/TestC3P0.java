package com.tedu.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/*
测试C3P0连接池的使用,往user表中插入一条用户信息
 */
public class TestC3P0 {
    @Test
    public void testInsert(){
        try {
            Connection conn=null;
            PreparedStatement ps=null;
            //创建一个连接池(存放连接的容器)对象
            ComboPooledDataSource pool=new ComboPooledDataSource();
            //设置连接数据库基本信息
            //注册驱动并获取连接(创建链接对象--造船)
            //conn=JdbcUtil.getConn();
            //从连接池中获取一个连接对象
            conn=pool.getConnection();
            //获取传输器,执行sql语句,并返回执行结果
            String sql="insert into user value(null,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1,"赵云");
            ps.setString(2,"123456");
            int rows=ps.executeUpdate();
            System.out.println("影响行数"+rows);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
