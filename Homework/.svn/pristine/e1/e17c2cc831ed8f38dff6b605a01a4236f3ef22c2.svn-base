package de.fs.webarch.server.rest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.fs.webarch.server.ContextListener;
import de.fs.webarch.server.DBSupport;

public class UserDao {
	
	public static UserDao instance = new UserDao();
	
	public User getUserByUsername(String username) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("SELECT * FROM User WHERE u_name = ?;");
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(!rs.next()) return null;
			return new User(rs);
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try { rs.close(); } catch(SQLException e) { e.printStackTrace(); }
			try { ps.close(); } catch(SQLException e) { e.printStackTrace(); }
			try { con.close(); } catch(SQLException e) { e.printStackTrace(); }
		}	
	}
	
	public User getUserById(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("SELECT * FROM User WHERE u_id = ?;");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(!rs.next()) return null;
			return new User(rs);
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try { rs.close(); } catch(SQLException e) { e.printStackTrace(); }
			try { ps.close(); } catch(SQLException e) { e.printStackTrace(); }
			try { con.close(); } catch(SQLException e) { e.printStackTrace(); }
		}
	}
	
	public List<User> getUsers() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> result = new ArrayList<User>();
		try {
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("SELECT * FROM User");
			rs = ps.executeQuery();
			while(rs.next()) {
				result.add(new User(rs));
			}
			return result;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try { rs.close(); } catch(SQLException e) { e.printStackTrace(); }
			try { ps.close(); } catch(SQLException e) { e.printStackTrace(); }
			try { con.close(); } catch(SQLException e) { e.printStackTrace(); }
		}
	}
	
	public List<User> getStudents() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> result = new ArrayList<User>();
		try {
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("SELECT * FROM User WHERE ug_id = 1");
			rs = ps.executeQuery();
			while(rs.next()) {
				result.add(new User(rs));
			}
			return result;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try { rs.close(); } catch(SQLException e) { e.printStackTrace(); }
			try { ps.close(); } catch(SQLException e) { e.printStackTrace(); }
			try { con.close(); } catch(SQLException e) { e.printStackTrace(); }
		}
	}
	
	public ArrayList<String> getNames() {
		ArrayList<String> result;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("SELECT * FROM User WHERE ug_id = 1 ORDER BY u_id");
			rs = ps.executeQuery();
			result = new ArrayList<>();
			while(rs.next()) {
				result.add(rs.getInt(1) + ", " + rs.getString(5) + ", " + rs.getString(4));
			}
		} catch(SQLException e) {
			e.printStackTrace();
			result = null;
		} finally {
			try { rs.close(); } catch(SQLException e) { e.printStackTrace(); }
			try { ps.close(); } catch(SQLException e) { e.printStackTrace(); }
			try { con.close(); } catch(SQLException e) { e.printStackTrace(); }
		}
		return result;
	}
	
	public String getName(int u_id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name = null;
		try {
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("SELECT CONCAT(first_name, ' ', last_name) FROM User WHERE u_id = ?;");
			ps.setInt(1, u_id);
			rs = ps.executeQuery();
			rs.next();
			name = rs.getString(1);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return name;
	}

}
