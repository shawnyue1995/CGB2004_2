<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<!-- JSP表达式(可以写Java代码,但是不能写以;结尾的语句)
    可以将其中的内容输出到浏览器
    JSP表达式中可以书写:常量,变量,表达式
    作用:计算表达式,将计算的结果输出到浏览器中
 -->
        <%= new Date() %>
        <%= 100+123 %>
        <%= "hello" %>
<!-- JSP脚本片段(可以书写若干 java语句)
    作用是:在翻译后的Servlet中,将脚本片段中的java语句复制粘贴到Servlet中对应的位置执行
 -->
        <%
            for (int i = 0; i <5 ; i++) {
                System.out.println("Hello JSP...");
            }
        %>

       <% for (int i = 0; i <5 ; i++) { %>
           Hello JSP~~~~<br/>
       <%}%>
<!-- JSP注释 -->
<%-- JSP注释
<%out.write("aaaaa<br/>"); %>
--%>
    <% //java注释out.write("bbbbb<br/>;")%>
    <!-- html注释
        <% out.write("cccc<br/>");%>
    -->

    <hr/>

<hr/>

</body>
</html>
