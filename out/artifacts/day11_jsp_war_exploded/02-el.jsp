<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.tedu.pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
        <h3>EL用法:1.获取常量表达式,变量的值(变量得先存入域中)</h3>
        ${ "Hello EL" }
        Hello EL
        ${ 23*32 >700 ? "yes" : "no" }
        <hr/>
        <%
            String name="马云";
            request.setAttribute("name",name);
            request.setAttribute("age",30);
        %>
        ${ name }
        ${ age }
        <%-- 在EL中书写变量,底层会根据变量的名字,到四个作用域中,寻找同名的属性,
        如果可以找到,就会输出这个属性对应的value值,如果找不到,就什么也不做(也不会报错)
        在寻找时,会按照如下作用域的顺序去寻找对应的属性,如果找到就直接返回,即使后面有也不再寻找,
        如果找不到就什么也不输出
        优先级顺序:pageContext,request,session,application
        --%>
        <h3>EL用法2:获取作用域中的数组或集合中的元素</h3>
        <%
            //声明一个数组
            String[] names={"陈冠希","余文乐","周杰伦","陈小春"};
            request.setAttribute("names",names);
            List list=new ArrayList<String>();
            list.add("安琪拉");
            list.add("王昭君");
            list.add("小乔");
            list.add("大乔");
            request.setAttribute( "list" , list );
        %>
        ${ names[0] }
        ${ names[1] }
        ${ names[2] }
        ${ names[3] }
        <hr/>
        ${ list[0] }
        ${ list[1] }
        ${ list[2] }
        ${ list[3] }
        <h3>EL用法3:获取作用域中map集合中的元素</h3>
        <%
            Map map=new HashMap();
            map.put("name","吴亦凡");
            map.put("age",30);
            map.put("addr","美国");
            request.setAttribute("map1",map);
        %>
            ${map1.name}
            ${map1.age}
            ${map1.addr}
        <h3>EL用法4:获取作用域中JavaBena对象的属性值</h3>
        <%
            User u1=new User();
            u1.setName("刘德华");
            u1.setAge(18);
            u1.setAddr("香港");
            request.setAttribute("user",u1);
        %>
        ${ user.name }
        ${ user.age }
        ${ user.addr }
</body>
</html>
