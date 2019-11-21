<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="de.fs.webarch.server.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Status</title>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
</head>
<body>
<jsp:include page="navbar.jsp"/>

<b>Server:</b>
<div id="version"></div>
<br/>
<b>Platforms:</b>
<ol id="platforms"></ol>
<br/>
<script>
$( "#version" ).load( "/DynWebProject/rest/mobile" );
</script>
<script>
$( "#platforms" ).load( "/DynWebProject/rest/mobile/platforms.html #platforms li" );
</script>
</body>
</html>