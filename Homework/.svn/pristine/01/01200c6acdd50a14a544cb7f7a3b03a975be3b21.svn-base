package de.fs.webarch.server.rest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Abgabe {

	private int a_id;
	private int s_id;
	private int h_id;
	private int attempt;
	private String loesung;
	private Date eingegangen;
	private String anmerkung;
	private float punkte;
	
	public Abgabe(int a_id, int s_id, int h_id, int attempt, String loesung, Date eingegangen, String anmerkung, float punkte) {
		this.a_id = a_id;
		this.s_id = s_id;
		this.h_id = h_id;
		this.attempt = attempt;
		this.loesung = loesung;
		this.eingegangen = eingegangen;
		this.anmerkung = anmerkung;
		this.punkte = punkte;
	}
	
	public Abgabe(int s_id, int h_id, String loesung) {
		this.s_id = s_id;
		this.h_id = h_id;
		this.loesung = loesung;
	}

	public Abgabe(int a_id, String anmerkung, float punkte) {
		this.a_id = a_id;
		this.anmerkung = anmerkung;
		this.punkte = punkte;
	}

	public Abgabe(ResultSet rs) {
		try { this.a_id = rs.getInt("a_id"); } catch(SQLException e) { }
		try { this.s_id = rs.getInt("s_id"); } catch(SQLException e) { }
		try { this.h_id = rs.getInt("h_id"); } catch(SQLException e) { }
		try { this.attempt = rs.getInt("attempt"); } catch(SQLException e) { }
		try { this.loesung = rs.getString("loesung"); } catch(SQLException e) { }
		try { this.anmerkung = rs.getString("anmerkung"); } catch(SQLException e) { }
		try { this.punkte = rs.getFloat("punkte"); } catch(SQLException e) { }
		
	}
	
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public int getH_id() {
		return h_id;
	}
	public void setH_id(int h_id) {
		this.h_id = h_id;
	}
	public int getAttempt() {
		return attempt;
	}
	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}
	public String getLoesung() {
		return loesung;
	}
	public void setLoesung(String loesung) {
		this.loesung = loesung;
	}
	public Date getEingegangen() {
		return eingegangen;
	}
	public void setEingegangen(Date eingegangen) {
		this.eingegangen = eingegangen;
	}
	public String getAnmerkung() {
		return anmerkung;
	}
	public void setAnmerkung(String anmerkung) {
		this.anmerkung = anmerkung;
	}
	public float getPunkte() {
		return punkte;
	}
	public void setPunkte(float punkte) {
		this.punkte = punkte;
	}
	
}
