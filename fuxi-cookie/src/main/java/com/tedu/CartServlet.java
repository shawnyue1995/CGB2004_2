package com.tedu;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.io.PrintWriter;

@javax.servlet.annotation.WebServlet("/CartServlet")
public class CartServlet extends javax.servlet.http.HttpServlet {
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //TODO ...
        //1.获取要加入购物车的商品信息(获取请求参数)
        String prod=request.getParameter("prod");
        //2.创建一个cookie对象,将商品信息保存到cookie中
        Cookie cookie=new Cookie("cart",prod);
        //3.将cookie保存到响应中,发送给浏览器
        response.addCookie(cookie);
        out.write("您已成功将商品["+prod+"]加入购物车");
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }
}
