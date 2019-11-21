package de.fs.webarch.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.fs.webarch.server.rest.Abgabe;
import de.fs.webarch.server.rest.AbgabeDao;

/**
 * Servlet implementation class UploadHomeworkServlet
 */
public class UploadHomeworkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadHomeworkServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//-----------------------------------------------------------------------------------------------
		//						Create DB Entry for table `Abgabe`
		//-----------------------------------------------------------------------------------------------
		int s_id = (int) request.getSession().getAttribute("loggedInUserID");
		int h_id = Integer.parseInt(request.getParameter("h_id"));
		
		Abgabe a = new Abgabe(s_id, h_id, request.getParameter("loesung"));
		AbgabeDao.instance.storeAbgabe(a);
		
		response.sendRedirect("index.jsp");
		
	}

}
