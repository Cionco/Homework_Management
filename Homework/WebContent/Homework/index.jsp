<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="de.fs.webarch.server.*"
    import="java.sql.*" import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="navbar.jsp"/>
	
	<p>Meine Hausaufgaben</p>
	<% if(session.getAttribute("usergroup") == null) return; %>
	<% boolean admin = session.getAttribute("usergroup").toString().equals("4"); %>
	<% boolean authorized = session.getAttribute("usergroup").toString().equals("2") || session.getAttribute("usergroup").toString().equals("4"); %>
	<% boolean employer = session.getAttribute("usergroup").toString().equals("3"); %>
	<% boolean student = session.getAttribute("usergroup").toString().equals("1"); %>
	   
	<%  Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
	try {con = ContextListener.getDataSource().getConnection(); } catch(SQLException e) {response.sendError(503); return;}
	if(!(employer || admin)) {
		sql = 	"SELECT h.h_id, Titel, Beschreibung, AbgabeTermin, name, Open" + (student?", Punkte, a_id":"")
				+ 	" FROM Hausaufgabe as h INNER JOIN Kurs as k on h.k_id = k.k_id" 
				+ 	(student?" LEFT JOIN Abgabe as a on h.h_id = a.h_id AND a.s_id = ?":"") 							//Studenten wollen ihre eigenen Abgaben und Hausaufgaben sehen
				+ 	" WHERE h.k_id in (SELECT k_id from "
				+	(student?"StudentRelKurs as s INNER JOIN User as u On s.s_id":"Kurs as k INNER JOIN User as u on k.dozent_id") //Studenten wollen die Hausaufgaben sehen, die ihren Kursen zugeordnet sind, Dozenten die Kurse, die sie selbst halten
				+ 		" = u.u_id WHERE u_name = ?) "
				+	(student?"AND (a.attempt = (SELECT MAX(a.attempt) FROM Abgabe AS a WHERE a.h_id = h.h_id AND s_id = ? GROUP BY h.h_id) OR a.attempt IS NULL);":";"); //Studenten wollen jede Hausaufgabe nur einmal in der Liste sehen - ohne diesen Teil kann bei mehreren oder keiner Abgabe eine Hausaufgabe aus der Liste ausgelassen werden
		ps = con.prepareStatement(sql);
			
		if(student) {
			ps.setInt(1, Integer.parseInt(session.getAttribute("loggedInUserID").toString()));
			ps.setString(2, session.getAttribute("loggedInUser").toString());
			ps.setInt(3, Integer.parseInt(session.getAttribute("loggedInUserID").toString()));
		} else ps.setString(1, session.getAttribute("loggedInUser").toString());
	} else {
		sql = "SELECT h_id, Titel, Beschreibung, AbgabeTermin, name, Open FROM Hausaufgabe as h INNER JOIN Kurs as k on h.k_id = k.k_id;";
		ps = con.prepareStatement(sql);
	}
	rs = ps.executeQuery();

	
		boolean bewertet = false;
		if(student) {
			while(rs.next()) if(rs.getInt(7) != 0) bewertet = true;
			rs.beforeFirst();
		}%>
		
	<table border="1">
		<tr>
			<th>Titel</th>
			<th>Beschreibung</th>
			<th>Abgabe</th>
			<th>Kurs</th>
			<% if(bewertet) { %><th>Punkte</th> <% } %>
		</tr>
		<%
		while(rs.next()) { %>
		<% boolean thisbewertet = false; if(bewertet) if(rs.getInt(7) != 0) thisbewertet = true; %>
		<tr>
			<td><%= rs.getString(2) %></td>
			<td><%= rs.getString(3) %></td>
			<td><%= rs.getDate(4) %></td>
			<td><%= rs.getString(5) %></td>
			<% if(student && thisbewertet) { if(rs.getInt(7) != 0) {%><td><a href="Assess/bewerten.jsp?par=<%= rs.getInt(8) %>"><%= rs.getInt(7) %></a></td><% } else { %><td></td><% }} %>
			<% if(authorized) { %><td><a href="NewHomeworkServlet?par=<%= rs.getInt(1) %>"><%= rs.getInt(6)==1?"Sperren":"Öffnen" %></a></td><td><a href="Assess/index.jsp?par=<%= rs.getInt(1) %>">Bewerten</a></td>
			<% } else if(employer) { %><td><%= rs.getInt(6)==1?"Offen":"Gesperrt" %></td><td><a href="Assess/index.jsp?par=<%= rs.getInt(1) %>">Ansehen</a></td>
			<% } else if(student && rs.getInt(6) == 1 && rs.getDate(4).after(new Date()) && !thisbewertet) { %><%= bewertet?"<td></td>":"" %><td><a href="abgabe.jsp?par=<%= rs.getInt(1)%>">Abgeben</a></td><% } %>
		</tr>
		<% }  rs.close(); %>
		<% if(authorized) { %>
			<tr><td colspan=3 align=center><a href="new.jsp">Neue Hausaufgabe</a></td></tr>
		<% } %>
		<% 
		try { rs.close(); } catch(SQLException e) { e.printStackTrace(); }
		try { ps.close(); } catch(SQLException e) { e.printStackTrace(); }
		try { con.close(); } catch(SQLException e) { e.printStackTrace(); }
		%>
	</table>
	


</body>
</html>