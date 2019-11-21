package de.fs.webarch.server.rest;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Kurs {

	private int k_id;
	private String kuerzel;
	private String name;
	private int dozent_id;
	
	public Kurs(int k_id, String kuerzel, String name, int dozent_id) {
		this.k_id = k_id;
		this.kuerzel = kuerzel;
		this.name = name;
		this.dozent_id = dozent_id;
	}
	
	public Kurs(String kuerzel, String name, int dozent_id) {
		this.kuerzel = kuerzel;
		this.name = name;
		this.dozent_id = dozent_id;
	}
	
	public Kurs(ResultSet rs) throws SQLException {
		this.k_id = rs.getInt(1);
		this.kuerzel = rs.getString(2);
		this.name = rs.getString(3);
		this.dozent_id = rs.getInt(4);
	}
	
	public int getK_id() {
		return k_id;
	}
	public void setK_id(int k_id) {
		this.k_id = k_id;
	}
	public String getKuerzel() {
		return kuerzel;
	}
	public void setKuerzel(String kuerzel) {
		this.kuerzel = kuerzel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDozent_id() {
		return dozent_id;
	}
	public void setDozent_id(int dozent_id) {
		this.dozent_id = dozent_id;
	}
	
}
