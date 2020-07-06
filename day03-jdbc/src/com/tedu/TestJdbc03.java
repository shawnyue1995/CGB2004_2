package com.tedu;

import com.tedu.util.JdbcUtil;
import org.junit.Test;
/*
完成JDBC的增删改查操作
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestJdbc03 {
    //1.新增:往account表中添加一个名称为john,money为3500的记录
    @Test
    public void testInsert(){
        Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        try {
            conn= JdbcUtil.getConn();
            stat=conn.createStatement();
            String sql="insert into account value(null,'john',3500)";
            int rows=stat.executeUpdate(sql);
            System.out.println("影响行数"+rows);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JdbcUtil.close(conn,stat,rs);
        }

    }
    //2.修改:将account表中名称为john的记录,money修改成1500
    @Test
    public void testUpdate(){
        Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        try {
            conn=JdbcUtil.getConn();
            stat=conn.createStatement();
            String sql="update account set money=1500 where name='john'";
            int rows=stat.executeUpdate(sql);
            System.out.println(rows);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn,stat,rs);
        }
    }
    //3.查询:查询account表中名称为john的记录
    @Test
    public void testFind(){
        Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        try {
            conn=JdbcUtil.getConn();
            stat=conn.createStatement();
            String sql="select * from account where name='john'";
            rs=stat.executeQuery(sql);
            while (rs.next()){
                int id=rs.getInt("id");
                String name=rs.getString("name");
                Double money=rs.getDouble("money");
                System.out.println(id+","+name+","+money);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn,stat,rs);
        }

    }
    //4.删除:删除account表中名称为john的记录
    @Test
    public void testDelete(){
        Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        try {
            conn=JdbcUtil.getConn();
            stat=conn.createStatement();
            String sql="delete from account where name='john'";
            int rows=stat.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn,stat,rs);
        }

    }
}
