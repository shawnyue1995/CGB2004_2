<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>day16-springmvc...home.jsp</h1>
	<!-- 取出域对象中的user对象的属性值 -->
	${ user.getName() }
	${ user.getAge() }
	${ user.getAddr() }
	<hr/>
	${user.name }
	${user.age }
	${user.addr }
</body>
</html>