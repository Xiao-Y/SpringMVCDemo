<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户添加</title>
</head>
<body>
	<sf:form method="post" modelAttribute="user">
		username:<sf:input path="username"/><sf:errors path="username"/> <br>
		password:<sf:password path="password"/><sf:errors path="password"/> <br>
		email:<sf:input path="email"/><sf:errors path="email"/><br>
		<input type="submit" value="提交">
	</sf:form>
</body>
</html>