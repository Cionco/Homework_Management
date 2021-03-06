package de.fs.webarch.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.fs.webarch.server.rest.Abgabe;
import de.fs.webarch.server.rest.AbgabeDao;

/**
 * Servlet implementation class AssessServlet
 */
public class AssessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Abgabe a = new Abgabe(Integer.parseInt(request.getParameter("id")), request.getParameter("comments"), Float.parseFloat(request.getParameter("score")));
		AbgabeDao.instance.assess(a);
		response.sendRedirect("index.jsp?par=" + a.getH_id());
	}

}
