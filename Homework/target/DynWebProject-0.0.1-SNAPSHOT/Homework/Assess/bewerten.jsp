<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" import="de.fs.webarch.server.*"
    import="de.fs.webarch.server.rest.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<% if(session.getAttribute("usergroup") == null) return; %>
	<% boolean lecturer = session.getAttribute("usergroup").toString().equals("2"); %>
	<% boolean student = session.getAttribute("usergroup").toString().equals("1"); %>
	<% if(request.getParameter("par") == null) %><jsp:forward page=".." />

	<%	Abgabe a = AbgabeDao.instance.getAbgabe(Integer.parseInt(request.getParameter("par").toString())); 	%>
	
	<div><textarea rows="20" cols="50" readonly><%= a.getLoesung() %></textarea></div>
	<form action="AssessServlet" method="post">
		<textarea rows="20" cols="50" placeholder="Kommentare" name="comments" <%= lecturer?"":"readonly" %>><%= a==null?"":a.getAnmerkung()%></textarea>
		<input type="hidden" name="id" value="<%= request.getParameter("par") %>">
		<div><input type="number" placeholder="Punktzahl" name="score" value="<%= a.getPunkte() %>" <%= lecturer?"":"readonly" %>></div>	
		<% if(lecturer) { %><div><input type="submit" value="Bewerten"></div>
		<% } else if(student) { %><div><a href="../">Zurück</a></div> 
		<% } else { %><div><a href="../Assess/index.jsp?par=<%= a.getH_id() %>">Zurück</a></div><% } %>
	</form>
</body>
</html>