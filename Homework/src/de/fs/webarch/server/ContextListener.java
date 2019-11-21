package de.fs.webarch.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import de.fs.webarch.server.rest.*;


/**
 * Application Lifecycle Listener implementation class DbPmPlusContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {

	/*private static Properties passwords=new Properties();
	private static Properties usergroups=new Properties();
	private static Properties names=new Properties();*/
	private static DataSource dataSource = null;
	@Deprecated
	private static Statement stmt = null;
	@Deprecated
	private static Connection con = null;

	/**
	 * Default constructor. 
	 */
	public ContextListener() {
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0)  { 
		try {
			stmt.close();
			con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(NullPointerException e) {
			
		}
		System.out.println("und tschuess...");
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event)  { 
		System.getProperties().setProperty("Dorg.apache.cxf.stax.allowInsecureParser", "1");
		System.out.println("sys prop set");

		ServletContext servletContext = event.getServletContext();
		//passwords= readPasswordFile(servletContext);
		
		//usergroups = readUsergroupsFile(servletContext);
		dataSource = getDBConnection(servletContext);
//		try {
//			if(con == null) con = dataSource.getConnection();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}

	/*public static Properties readPasswordFile(ServletContext servletContext) {
		if(passwords!=null && passwords.size()>0) return passwords; // keep Listener class from being GCed
		
		Properties props=new Properties();
		FileReader reader =null;
		try {
			String contextPasswdParam=servletContext.getInitParameter("passwordfileDebug");
			reader = new FileReader(contextPasswdParam);
			props.load(reader);
			System.out.println("Passwords read successfully "+contextPasswdParam);
		} catch(Exception e) {
			e.printStackTrace();
			if(reader!=null)
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}
		return props;
	}
	
	public static Properties readUsergroupsFile(ServletContext servletContext) {
		if(usergroups != null && usergroups.size() > 0) return usergroups;
		
		Properties props = new Properties();
		String contextUsergrpParam=servletContext.getInitParameter("usergrpfileDebug");
		try (FileReader reader = new FileReader(contextUsergrpParam)) {
			props.load(reader);
			System.out.println("Usergroups read successfully " + contextUsergrpParam);
		} catch(IOException e) {
			e.printStackTrace();
		}
		return props;
	}*/
	
	public static DataSource getDBConnection(ServletContext servletContext) {
		DataSource ds = null;
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/Homework");
			System.out.println("Database Connection created successfully");
		} catch(NamingException e) {
			e.printStackTrace();
		}
		return ds;
		
	}
	
	public static DataSource getDataSource() {
		return dataSource;
	}


	public static boolean isUserAuthentic(String username, String passwordFromForm) {		
		User u = UserDao.instance.getUserByUsername(username);
		if(u == null) return false;

		if(passwordFromForm!=null && passwordFromForm.equals(u.getPassword())) {
			return true;
		}

		return false;

	}
	
	/**
	 * @deprecated Use DBSupport.prepareStatement(String, Connection) instead
	 */
	@Deprecated
	public static PreparedStatement prepareStatement(String sql) {
		try {
			if(con == null) con = dataSource.getConnection();
			
			return con.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @deprecated Use PreparedStatements instead. Can be Created with de.fs.webarch.DBSupport.getPreparedStatementFromSql or de.fs.webarch.DBSupport.getPreparedStatement
	 */
	@Deprecated
	public static ResultSet executeQuery(String sql) {
		ResultSet rs = null;
		try {
			if(con == null) con = dataSource.getConnection();
			if(stmt == null) stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println("Anweisung ohne Problem ausgeführt: " + sql);
		} catch(SQLException e) {
			System.err.println("Fehler beim Ausführen: " + sql);
			rs = null;
			e.printStackTrace();
		}
		return rs;
		
	}
	
	/**
	 * @deprecated Use PreparedStatements instead. Can be Created with de.fs.webarch.DBSupport.getPreparedStatementFromSql or de.fs.webarch.DBSupport.getPreparedStatement
	 */
	@Deprecated
	public static boolean execute(String sql) {
		boolean result = false;
		try {
			if(con == null) con = dataSource.getConnection();
			if(stmt == null) stmt = con.createStatement();
			result = stmt.execute(sql);
			System.out.println("Anweisung ohne Problem ausgeführt: " + sql);
		} catch(SQLException e) {
			System.err.println("Fehler beim Ausführen: " + sql);
			result = false;
			e.printStackTrace();
		} 
		return result;
	}
	
	/**
	 * @deprecated Use PreparedStatements instead. Can be Created with de.fs.webarch.DBSupport.getPreparedStatementFromSql or de.fs.webarch.DBSupport.getPreparedStatement
	 */
	@Deprecated
	public static int executeUpdate(String sql) {
		int result = 0;
		try {
			if(con == null) con = dataSource.getConnection();
			if(stmt == null) stmt = con.createStatement();
			result = stmt.executeUpdate(sql);
			System.out.println("Anweisung ohne Problem ausgeführt: " + sql);
		} catch(SQLException e) {
			System.err.println("Fehler beim Ausführen: " + sql);
			result = -1;
			e.printStackTrace();
		}
		return result;
	}
}
