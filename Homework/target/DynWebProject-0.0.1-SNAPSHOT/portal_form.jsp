<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="de.fs.webarch.server.*"%>
<!DOCTYPE html>
<html>
 <head>
  <title>Form to create a new resource</title>
 </head>
<body>
    <form action="rest/portals" method="POST">
    <label for="id">ID</label>
    <input name="id" />
    <br/>
    <label for="title">Title</label>
    <input name="title" />
    <br/>
    <label for="summary">Summary</label>
    <input name="summary" />
    <br/>
    Description:
    <TEXTAREA NAME="description" COLS=40 ROWS=6></TEXTAREA>
    <br/>
    <input type="submit" value="Submit" />
    </form>
</body>
</html>