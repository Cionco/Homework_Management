<%@ taglib prefix="su" uri="/WEB-INF/setup.tld" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="de.fs.webarch.server.*"
    import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Neues Fach/Vorlesung</title>
</head>
<body>
	<jsp:include page="navbar.jsp"/>
	<% boolean authorized = session.getAttribute("usergroup").toString().equals("2") || session.getAttribute("usergroup").toString().equals("4"); %>
	<% boolean newone = request.getParameter("par") == null; 
		int par = !newone?Integer.parseInt(request.getParameter("par")):-1; %>
	<h1><%= newone?"Neues Fach/Vorlesung":"Kurs Info ändern" %></h1>
	<h2>Bitte geben Sie dem Kurs einen Namen und fügen Sie Teilnehmer hinzu</h2>
	<% if(authorized) { %><form method="post" action="SetupSubjectServlet<%= !newone?("?par=" + par):""%>"><% } %>
	
		<su:header newcourse="<%= newone %>" courseid="<%= !newone?par:' ' %>" editable="<%= authorized %>"/>		
		
		<su:participants newcourse="<%= newone %>" courseid="<%= !newone?par:' ' %>" editable="<%= authorized %>" />		
		
	<% if(authorized) { %>
		<div class="submit-section">
			<input type="submit" value="<%= (request.getParameter("par") == null)?"Erstellen":"Ändern" %>"/>
		</div>
	</form>
	<% } %>

</body>
</html>