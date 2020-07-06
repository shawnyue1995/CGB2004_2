package com.tedu;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestJdbc02 {
        @Test
    public void testFindAll() throws Exception {
            Connection conn = null;
            Statement stat = null;
            ResultSet rs = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jt_db?characterEncoding=utf-8",
                        "root", "root"
                );
                stat=conn.createStatement();
                String sql = "select * from account";
                stat.executeQuery(sql);
                rs = stat.executeQuery(sql);
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    Double money = rs.getDouble("money");
                    System.out.println(id + "," + name + "," + money);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        rs = null;
                    }
                    if (stat != null) {
                        try {
                            stat.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            stat = null;
                        }
                        if (conn != null) {
                            try {
                                conn.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                conn = null;
                            }
                        }
                    }
                }
            }
        }
}