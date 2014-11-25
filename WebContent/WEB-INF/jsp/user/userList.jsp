<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
</head>
<body>
<a href="add">添加</a>
<br>
	<c:forEach items="${users }" var="um">
		${um.value.username }---<a href="${um.value.username }">${um.value.password }</a>--${um.value.email }<br>
	</c:forEach>
</body>
</html>