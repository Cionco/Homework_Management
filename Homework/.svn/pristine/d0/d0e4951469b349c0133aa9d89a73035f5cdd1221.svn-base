package de.fs.webarch.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EchoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, 
    		HttpServletResponse resp) throws ServletException, IOException {
		String webXmlInitParam=getServletConfig().getInitParameter("build");
		String contextXmlParam=getServletContext().getInitParameter("hostname");
        resp.getWriter().print(
                "{\n" +
                        "    \"node\" : \""+contextXmlParam+"\",\n" +
                        "    \"build\" : \""+webXmlInitParam+"\",\n" +
                        "    \"time\" : "+System.currentTimeMillis()+",\n"+
                        "    \"isAlive\" : true\n" +
                        "}"
        );
    }

}
