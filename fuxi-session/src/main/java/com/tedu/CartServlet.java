package com.tedu;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@javax.servlet.annotation.WebServlet("/CartServlet")
public class CartServlet extends javax.servlet.http.HttpServlet {
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //TODO ...
        //1.获取要加入购物车得信息
        String prod = request.getParameter("prod");
        //2.获取一个session对象,将商品信息保存到session中
        HttpSession session=request.getSession();
        session.setAttribute("cart",prod);
        //3.做出响应
        out.write("您已成功将["+prod+"]加入购物车");
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }
}
