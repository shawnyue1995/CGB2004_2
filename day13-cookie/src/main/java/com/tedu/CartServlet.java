package com.tedu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //TODO ...
        //1.获取要加入购物车的商品信息(获取请求参数)
        String prod= request.getParameter("prod");
        //2.创建一个cookie对象,将商品信息保存到cookie中
        Cookie cookie=new Cookie("cart",prod);
        //设置cookie的最大生存时间
        cookie.setMaxAge(60*60*24*30);
        //3.将cookie保存到响应中,发送给浏览器保存
        response.addCookie(cookie);
        out.write("成功将["+prod+"]商品加入到购物车");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
