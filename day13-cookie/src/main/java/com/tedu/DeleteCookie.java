package com.tedu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/DeleteCookie")
public class DeleteCookie extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //TODO ...
        /*
        删除名称为cart的cookie
        向浏览器发送一个同名的cookie(名称也是cart),并设置cookie
	    最大生存时间为零,再将ciikie添加到响应中,发送给浏览器即可
		由于浏览器是根据cookie的名字来区分cookie的,如果前后两次发送了同名的cookie,
		后发送的cookie就会覆盖之前发送的cookie,而后发送的cookie又设置了生存时间为零,
		 因此,浏览器收到后也会立即删除
         */
        Cookie c=new Cookie("cart","");
        c.setMaxAge(0);
        response.addCookie(c);
        out.write("成功删除了名称为cart的cookie");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
