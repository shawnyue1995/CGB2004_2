package com.tedu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/PayServlet")
public class PayServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //TODO ...
        //1.获取一个session对象(获取之前的session)
        HttpSession session=request.getSession();
        //创建一个名称为JESSIONID的cookie,值为session的id
        Cookie c=new Cookie("JESSIONID",session.getId());
        //设置cookie的存活时间为1天
        c.setMaxAge(60*606*24);
        //将cookie添加到响应中
        response.addCookie(c);
        //2.从session中获取商品信息
        String prod= (String) session.getAttribute("cart");
        //3.模拟支付,并做出响应
        if (prod==null){
            out.write("您还没有添加任何商品");
        }else{
            out.write("您已成功为["+prod+"]支付2000元");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
