<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="de.fs.webarch.server.*"
    import="java.sql.*"%>
<% Connection con = null; try { con = ContextListener.getDataSource().getConnection();} catch(SQLException e) {response.sendError(503);} finally { con.close(); } %>
<table><tr>
<!--td><a href="status.jsp">Status</a> | </td>
<td><a href="portallist.jsp">Portal Liste</a> | </td>
<td><a href="rest/portals">Portal Liste (XML)</a> | </td>
<td><a href="portal_form.jsp">Neues Portal</a> | </td-->
<td><a href="index.jsp">Zurück zur Startseite</a> | </td>
<td><a href="subjects/">Kurse</a> | </td>
<td><a href="Homework/">Hausaufgaben</a> | </td>
<% if(session.getAttribute("usergroup") != null) if(session.getAttribute("usergroup").toString().equals("4")) { %><td><a href="SqlConsoleServlet">SQL Konsole</a> | </td><% } %>
<td><a href="LoginServlet">Logout: <%= session.getAttribute("loggedInUser") %></a></td>
</tr></table>
<% if(session.getAttribute("loggedInUser") == null) { %>
	<jsp:forward page="login.jsp">
		<jsp:param name="reqcamefrom" value="<%= request.getRequestURL() %>"/>
	</jsp:forward>
<% } %>