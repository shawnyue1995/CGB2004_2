package com.tedu;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@javax.servlet.annotation.WebServlet("/PayServlet")
public class PayServlet extends javax.servlet.http.HttpServlet {
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //1.获取一个Session对象(获取之前的Session)
        HttpSession session=request.getSession();
        //创建一个名称为JSESSIONID的cookie,值为session的id
        Cookie c=new Cookie("JESSIONID",session.getId());
        //设置cookie的存活时间为1天
        c.setMaxAge(60*60*24);
        //将cookie添加到响应中
        response.addCookie(c);
        //2.从Session中获取商品信息
        String prod=(String)session.getAttribute("cart");
        //3.模拟支付,并做出响应
        if (prod==null){
            out.write("您还没有将任何商品加入购物车");
        }else{
        //将session销毁(将购物车中的商品一起移除)
            session.invalidate();
            out.write("成功为["+prod+"]支付了2000元");
        }
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }
}
