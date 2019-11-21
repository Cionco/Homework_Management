package de.fs.webarch.server.rest;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	
	private int u_id;
	private String u_name;
	private String password;
	private String first_name;
	private String last_name;
	private int ug_id;
	
	public User(int u_id, String u_name, String password, String first_name, String last_name, int ug_id) {
		this.u_id = u_id;
		this.u_name = u_name;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.ug_id = ug_id;
	}
	
	public User(ResultSet rs) throws SQLException {
		this.u_id = rs.getInt(1);
		this.u_name = rs.getString(2);
		this.password = rs.getString(3);
		this.first_name = rs.getString(4);
		this.last_name = rs.getString(5);
		this.ug_id = rs.getInt(6);
	}
	
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public int getUg_id() {
		return ug_id;
	}
	public void setUg_id(int ug_id) {
		this.ug_id = ug_id;
	}
	
	

}
