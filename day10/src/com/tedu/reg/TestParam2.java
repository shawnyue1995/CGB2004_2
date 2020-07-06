package com.tedu.reg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet("/TestParam2")
public class TestParam2 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String username=request.getParameter("username");
        System.out.println(username);
        String pwd=request.getParameter("pwd");
        System.out.println(pwd);
        String gender=request.getParameter("gender");
        System.out.println(gender);
        String[] like=request.getParameterValues("like");
        System.out.println(Arrays.toString(like));
        String city=request.getParameter("city");
        System.out.println(city);
        String desc=request.getParameter("desc");
        System.out.println(desc);
        out.write("测试成功");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
