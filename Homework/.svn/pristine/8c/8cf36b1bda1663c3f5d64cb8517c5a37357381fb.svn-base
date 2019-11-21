package de.fs.webarch.server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.fs.webarch.server.rest.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(
		name="LoginServlet",
		description="Servlet for logging in",
		urlPatterns= {"/LoginServlet"}
)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("loggedInUser");
		session.removeAttribute("loggedInUserID");
		session.removeAttribute("usergroup");
		session.invalidate();
		
		response.sendRedirect((request.getParameter("reqcamefrom") == null)?request.getHeader("referer"):request.getParameter("reqcamefrom"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = "{\"username\":\"" + request.getParameter("username") + "\", \"password\":\""+ request.getParameter("password") + "\"}";
		ObjectMapper mapper = new ObjectMapper();
		LoginBean bean = mapper.readValue(json, LoginBean.class);
		HttpSession session = request.getSession();
		
		User u = null;
		if(bean.checkLogin()) {
			u = UserDao.instance.getUserByUsername(request.getParameter("username"));
			
			session.setAttribute("loggedInUser", u.getU_name());
			session.setAttribute("loggedInUserID", u.getU_id());
			session.setAttribute("usergroup", u.getUg_id());
		}
		else {
			session.removeAttribute("loggedInUser");
			session.removeAttribute("loggedInUserID");
			session.removeAttribute("usergroup");
			
			session.invalidate();
		}
		
		response.sendRedirect((request.getParameter("reqcamefrom") == null)?request.getHeader("referer"):request.getParameter("reqcamefrom"));
	}

}
