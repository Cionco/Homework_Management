<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="de.fs.webarch.server.*"
    import="de.fs.webarch.server.rest.*" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="navbar.jsp"/>
	<p>Kurs Liste</p>
	<% if(session.getAttribute("usergroup") == null) return; %>
	<% boolean employer = session.getAttribute("usergroup").toString().equals("3"); %>
	<% boolean lecturer = session.getAttribute("usergroup").toString().equals("2"); %>
	<% boolean student = session.getAttribute("usergroup").toString().equals("1"); %>
	<% boolean authorized = lecturer || session.getAttribute("usergroup").toString().equals("4"); %>
	<% String[] paramArray = new String[]{session.getAttribute("loggedInUser").toString(), session.getAttribute("loggedInUserID").toString(), session.getAttribute("usergroup").toString()}; %>
	   
	<table border="1">
		<tr>
			<th>Kürzel</th>
			<th>Bezeichnung</th>
			<th>Dozent</th>
		</tr>
	<% 	Connection con = null;
		try {con = ContextListener.getDataSource().getConnection();} catch(SQLException e) {response.sendError(503);return;}
		
		for(Kurs k : KursDao.instance.getKurse(Integer.parseInt(session.getAttribute("loggedInUserID").toString()))) { 
			User dozent = UserDao.instance.getUserById(k.getDozent_id()); %>
		<tr>
			<td><%= k.getKuerzel() %></td>
			<td><%= k.getName() %></td>
			<td><%= dozent.getFirst_name() + " " + dozent.getLast_name() %></td>
			<td><a href="setup.jsp?par=<%= k.getK_id() %>"><%= authorized?"Bearbeiten":"Teilnehmerliste" %></a></td>
		</tr>
	<% } %>
	<% if(authorized) { %>
		<tr><td colspan=3 align=center><a href="setup.jsp">Neuer Kurs</a></td></tr>
	<% } %>
	</table>


</body>
</html>