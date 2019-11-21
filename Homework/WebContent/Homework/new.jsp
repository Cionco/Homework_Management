<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8" import="de.fs.webarch.server.*"
    import="de.fs.webarch.server.rest.*"
    import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>

	<h1>Neue Hausaufgabe</h1>
	<form action="NewHomeworkServlet" method="post">
		<div><input type="text" placeholder="Titel" name="title"/></div>
		<div><textarea rows=5 cols=50 placeholder="Beschreibung" name="beschreibung"></textarea></div>
		<div><input type="date" name="Abgabedatum" placeholder="Abgabedatum"/></div>
		<div><input type="checkbox" name="locked" id="locked" value=1><label for="locked">Gesperrt</label></div>
		<div><select name="kurs">
		<% HashMap<Integer, String> kurse = KursDao.instance.getKurseWithId(Integer.parseInt(session.getAttribute("loggedInUserID").toString()));  
			for(int key : kurse.keySet()) { %>
			<option value="<%= key %>"><%= kurse.get(key) %></option>
			<% } %>
		</select></div>
		<div><input type="submit" value="Erstellen"/></div>
	</form>
</body>
</html>