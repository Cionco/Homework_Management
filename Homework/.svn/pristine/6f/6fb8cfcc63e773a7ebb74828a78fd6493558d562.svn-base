package de.fs.webarch.server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.fs.webarch.server.rest.Kurs;
import de.fs.webarch.server.rest.KursDao;

/**
 * Servlet implementation class SetupSubjectServlet
 */
public class SetupSubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetupSubjectServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		if(request.getParameter("par")!= null) { 
			alterExistingCourse(request, response);
		} else {			
			createNewCourse(request, response);
		}
		response.sendRedirect(request.getContextPath() + "/subjects/");
	}

	private void createNewCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sql = "INSERT INTO Kurs (kuerzel, name, dozent_id) VALUES(?, ?, ?);"; 
		String[] params = new String[]{request.getParameter("abb"), request.getParameter("title"), request.getSession().getAttribute("loggedInUserID").toString()};
		
		Kurs k = new Kurs(request.getParameter("abb"), request.getParameter("title"), Integer.parseInt(request.getSession().getAttribute("loggedInUserID").toString()));
		ArrayList<Integer> teilnehmer = new ArrayList<Integer>();
		String[] results = request.getParameterValues("names");
		if(results != null)
			for(String result : results)
				teilnehmer.add(Integer.parseInt(result));
		
		KursDao.instance.storeKurs(k, teilnehmer);
	}

	private void alterExistingCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String par = request.getParameter("par");
		//-----------------------------------------------------------------------------------------------
		//							Update DB Entry for table `Kurs`
		//-----------------------------------------------------------------------------------------------
		Kurs k = new Kurs(Integer.parseInt(par), request.getParameter("abb"), request.getParameter("title"), Integer.parseInt(request.getSession().getAttribute("loggedInUserID").toString()));
		ArrayList<Integer> teilnehmer = new ArrayList<Integer>();
		String[] results = request.getParameterValues("names");
		if(results != null) 
			for(String result : results) 
				teilnehmer.add(Integer.parseInt(result));
		
		KursDao.instance.updateKurs(k, teilnehmer);
	}

}
