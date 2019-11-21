<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="de.fs.webarch.server.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Portal-List</title>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
</head>
<body>

<b>Portals:</b>
<ul id="portal-list"></ul>
<br/>
<button id="json">json</button>

<script>
$('#json').click(function(){ 
    
     $.getJSON("/DynWebProject/rest/portals/json",
     function(data) {
    	 	// DOM API
    	 	var ul = document.getElementById("portal-list");
    	 	//empty list (jQuery)
    	 	$(ul).empty();
    	 	
        for(var i in data) {
        		var title = data[i].title;
        		console.log("portal:"+title);
        		var li = document.createElement("li");
        		li.appendChild(document.createTextNode("Portal: "+title));
        		ul.appendChild(li);
        }
      });   
});
</script>

</body>
</html>