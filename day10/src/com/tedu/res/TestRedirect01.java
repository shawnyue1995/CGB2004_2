package com.tedu.res;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/TestRedirect01")
public class TestRedirect01 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        System.out.println("TestRedirect01.doGet()");
        //response.sendRedirect("http://localhost:8080/day10/TestRedirect02");
        //如果重定向前后,主机名没有变化,重定向时路径中,可以省写主机名
        //response.sendRedirect("/day10/TestRedirect02");
        //也可以写一个相对路径
		//response.sendRedirect("TestRedirect02");
        /*
        2.将请求重定向到day09/HelloServlet
         */
//        response.sendRedirect("/day09/HelloServlet");
        response.sendRedirect("../day09/HelloServlet");
        /*
        3.将请求重定向到百度首页
         */
        response.sendRedirect("http://www.baidu.com");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
