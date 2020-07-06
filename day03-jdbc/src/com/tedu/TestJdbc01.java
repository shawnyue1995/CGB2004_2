package com.tedu;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestJdbc01 {
    @Test
    public void testFindAll() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306//jt_db?characterEncoding=utf-8",
                "root","root"
        );
        Statement stat=conn.createStatement();
        String sql="select * from account";
        stat.executeQuery(sql);
        ResultSet rs=stat.executeQuery(sql);
        while(rs.next()) {
            int id=rs.getInt("id");
            String name=rs.getString("name");
            Double money=rs.getDouble("money");
            System.out.println(id+","+name+","+money);
            }
            rs.close();
            stat.close();
            conn.close();
        System.out.println("TetstJdbc01.testFindAll()");
        }
    }
