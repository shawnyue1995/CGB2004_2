<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h1>day16-springmvc...test.jsp</h1>
<!-- 将域对象中list集合中的数据取出来进行显示 -->
${list[0].name},${list[0].age},${list[0].addr}
${list[1].name},${list[1].age},${list[1].addr}
${list[2].name},${list[2].age},${list[2].addr}
</body>
</html>