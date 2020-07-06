<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%--引入JSTL标签库的核心标签--%>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
        <h3>1.c:set标签-- 往域中添加属性</h3>
        <%-- pageContext.setAttribute("name","张三") --%>
        <c:set var="name" value="张三" scope="request" />
        ${ name }
        <c:set var="name" value="李四" scope="request" />
        ${ name }
<h3>1.c:if标签-- 可以模拟简单的if..else分支结构</h3>
    <%--往域中存入一个成绩,根据成绩判断所属等级--%>
    <c:set var="score" value="98" scope="request" />
    <c:if test="${ score>=80 && score<=100 }">您的成绩为:优秀!</c:if>
    <c:if test="${ score>=60 && score<80 }">您的成绩为:中等!</c:if>
    <c:if test="${ score>=0 && score<60 }">您的成绩为:不及格!</c:if>
    <c:if test="${ score>100 && score<0 }">您的成绩为:不合法!</c:if>
<h3>1.c:forEach标签</h3>
    <h5>1.遍历域中的数组或集合中的元素</h5>
    <%
        String[] name={"王海涛","刘沛霞","陈子枢","齐雷"};
        request.setAttribute("names",name);
    %>
        <!--
    items:指定要遍历的数据或集合,如果数组或集合来自域中,可以使用el获取
     var:指定一个变量,用于接收数组或集合中的元素
     for( String name:names ){}
     varstatus表示的是一个对象,该对象表示循环遍历的状态信息,可以属性有:
         first:boolean,如果当前是第一次遍历,则返回true,否则返回false
         last:boolean,如果当前是最后一次遍历,则返回true,否则返回false
         count:用于统计当前是第几次遍历,返回整数值
         begin:起始值,从什么开始遍历
         end:指定到什么结束遍历
         step:指定每次循环之后,自增的值,默认值为1
 -->
    <c:forEach items="${names}" var="name" varStatus="vs">
        ${ vs.count },${vs.first},${ vs.last },${ name }<br/>
    </c:forEach>
    <h5>2.遍历域中的map集合中的元素</h5>
    <%
        //声明一个map集合存入域中
            Map map=new HashMap();
            map.put("name","吴亦凡");
            map.put("age",30);
            map.put("addr","美国");
            request.setAttribute("map1",map);
    %>
            <c:forEach items="${ map1 }" var="entry">
                ${ entry }<br/>
            </c:forEach>

            <c:forEach items="${map1}" var="entry">
                ${entry.key}:${entry.value}<br/>
            </c:forEach>

            <h5>3.遍历1-100之间所有的整数,将是3的倍数的数值输出</h5>
            <%-- for(int begin=1,end=100,i=begin;i<=end;i++)--%>
            <c:forEach begin="1" end="100" var="i" step="1">
                ${i}
            </c:forEach>
            <hr/>
            <c:forEach begin="1" end="100" var="i" step="1">
                    ${i%3==0 ? i : ""}
            </c:forEach>
            <hr/>
            <c:forEach begin="1" end="100" var="i" step="1">
                    <c:if test="${i%3==0 }">
                        ${i}
                    </c:if>
            </c:forEach>

</body>
</html>
