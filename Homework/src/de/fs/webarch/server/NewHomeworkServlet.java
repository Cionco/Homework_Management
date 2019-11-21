package de.fs.webarch.server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.fs.webarch.server.rest.Hausaufgabe;
import de.fs.webarch.server.rest.HausaufgabeDao;

/**
 * Servlet implementation class NewHomeworkServlet
 */
public class NewHomeworkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewHomeworkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("utf-8");
		HausaufgabeDao.instance.lockUnlock(Integer.parseInt(request.getParameter("par").toString()));
		
		response.sendRedirect(request.getContextPath() + "/Homework/");
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//-----------------------------------------------------------------------------------------------
		//						Create DB Entry for table `Hausaufgabe`
		//-----------------------------------------------------------------------------------------------	
		CharSequence cs = request.getParameter("Abgabedatum");
		LocalDate ld = LocalDate.parse(cs);
		Date date = java.sql.Date.valueOf(ld);
		boolean locked = false;
		try { locked = request.getParameter("locked").toString()!=null; } catch(NullPointerException e) { locked = false; }
		Hausaufgabe h = new Hausaufgabe(request.getParameter("title"), request.getParameter("beschreibung"), date, !locked, Integer.parseInt(request.getParameter("kurs").toString()));
		HausaufgabeDao.instance.storeHausaufgabe(h);
		
		response.sendRedirect(request.getContextPath() + "/Homework/");
	}

}
