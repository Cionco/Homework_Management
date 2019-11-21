<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="de.fs.webarch.server.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hausaufgaben Verwaltung - Login</title>
</head>
<body>
	<h1>Please log in to use Frankfurt School's Homework Management Tool</h1>
	<form action="<%= request.getContextPath() %>/LoginServlet" method="post">

		<div><input type="text" id="username" name="username"><label for="username">Username</label></div>
		<div><input type="password" id="password" name="password"><label for="password">Password</label></div>
		<div><input type="submit" value="Done"></div>
		<div><input type="hidden" name="reqcamefrom" value="<%= request.getParameter("reqcamefrom") %>"></div>
		
	</form>
	
</body>
</html>