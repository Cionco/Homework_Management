package de.fs.webarch.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SqlConsoleServlet
 */
@WebServlet("/SqlConsoleServlet")
public class SqlConsoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String CONSTRUCT = "<html><body onload=\"document.console.input.focus();\">"
			+ "<form name=\"console\" action=\"SqlConsoleServlet\" method=\"post\">"
			+ "<div><textarea rows=20 cols=100 readonly></textarea></div>"
			+ "<div><input type=\"text\" style=\"width: 1000px;\" name=\"input\" id=\"input\"><input hidden type=\"submit\" value=\"cmd\"></div></form></body></html>";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SqlConsoleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		if(session.getAttribute("loggedInUser") == null) { response.sendRedirect("index.jsp"); return; }
		if(!session.getAttribute("usergroup").toString().equals("4")) response.sendRedirect("index.jsp");
		request.getRequestDispatcher("navbar.jsp").include(request, response);
		write(CONSTRUCT, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		if(session.getAttribute("loggedInUser") == null) { response.sendRedirect("index.jsp"); return; }
		Connection con = null;
		request.getRequestDispatcher("navbar.jsp").include(request, response);
		String cmd = (String) request.getParameter("input");
		
		String output = null;
		PreparedStatement ps = null;
		try {con = ContextListener.getDataSource().getConnection();} catch(SQLException e) {response.sendError(503); return;}
		try {
			ps = con.prepareStatement(cmd);
			if(cmd.toUpperCase().startsWith("SELECT")) {
				write(swapAreaForTable(buildRSTable(ps)), response);
			} else {
				int changes = 0;
				changes = ps.executeUpdate();
				output = changes + " Zeilen wurden geändert";
				
				write(initializedConstruct(output), response);
			}
			
		} catch(SQLException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			output += sw.toString().split("\n")[0];
		} finally {
			try { ps.close(); } catch(SQLException e) { e.printStackTrace(); }
			try { con.close(); } catch(SQLException e) { e.printStackTrace(); }
		}
		
	}

	private String buildRSTable(PreparedStatement cmd) {
		String table = "";
		ResultSet rs = null;
		try {
			rs = cmd.executeQuery();
		} catch (SQLException e1) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e1.printStackTrace(pw);
			return "<textarea rows=20 cols=100 readonly>Fehler in der Anweisung:\n" + sw.toString().split("\n")[0] +"</textarea>"; //In case there's an Error print the First line of the StackTrace into a textarea
		}
		try {
				
			ResultSetMetaData rsmd = rs.getMetaData();
			table += "<table><tr>";
			for(int i = 1; i <= rsmd.getColumnCount(); i++) {
				table += "<th>" + rsmd.getColumnName(i) + "</th>";
			}
			table += "</tr>";
			while(rs.next()) {
				table += "<tr>";
				for(int i = 1; i <= rsmd.getColumnCount(); i++) table += "<td>" + rs.getString(i) + "</td>";
				table += "</tr>";
			}
			table += "</table>";
		} catch (SQLException e) {
			e.printStackTrace();
			table = e.toString();
		} finally {
			DBSupport.close(cmd, rs);	
		}
		return table;
	}
	
	private void write(String html, HttpServletResponse response) throws IOException {
		response.getWriter().append(html);
	}
	
	private String initializedConstruct(String content) {
		String construct = CONSTRUCT;
		
		return insertAfter(content, "readonly>", construct);
	}
	
	private String insertAfter(String insert, String after, String into) {
		String[] split = into.split(after);
		return split[0] + after + insert + split[1];
	}
	
	private String swapAreaForTable(String table) {
		String construct = CONSTRUCT;
		return construct.replaceAll("<textarea rows=20 cols=100 readonly></textarea>", table);
	}

}
