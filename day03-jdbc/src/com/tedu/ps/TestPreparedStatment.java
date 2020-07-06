package com.tedu.ps;

import com.tedu.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//练习preparedstatment对象的使用
public class TestPreparedStatment {
        //练习1.往user表红插入一条用户信息:孙尚香 123456
        @Test
        public void testInsert(){
            Connection conn=null;
            PreparedStatement ps=null;
            try {
                conn= JdbcUtil.getConn();
                String sql="insert into user value(null,?,?)";
                ps=conn.prepareStatement(sql);
                ps.setString(1,"孙尚香");
                ps.setString(2,"123456");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                JdbcUtil.close(conn,ps,null);
            }
        }

        //练习2:查询user表中所有用户信息,并输出到控制台
    //提示:如果sql语句中没有参数(也就没有?),也就不需要设置参数
    //ps.setString(1,xxx);
    @Test
    public void testFindAll(){
            Connection conn=null;
            PreparedStatement ps=null;
            ResultSet rs=null;
            try {
                conn=JdbcUtil.getConn();
                String sql="select * from user";
                ps=conn.prepareStatement(sql);
                rs=ps.executeQuery();
                while (rs.next()){
                    int id=rs.getInt("id");
                    String username=rs.getString("username");
                    String password=rs.getString("password");
                    System.out.println(id+","+username+","+password);
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                JdbcUtil.close(conn,ps,null);
            }
    }

    //将user表中username为张飞你的记录,password修改为123456
    @Test
    public void testUpdate(){
            Connection conn=null;
            PreparedStatement ps=null;
            ResultSet rs=null;
            try {
                conn=JdbcUtil.getConn();
                String sql="update user set password=123456 where username='张飞'";
                ps=conn.prepareStatement(sql);
                int rows=ps.executeUpdate();
                System.out.println("影响行数"+rows);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                JdbcUtil.close(conn,ps,rs);
            }
    }

}
