package de.fs.webarch.server.rest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Hausaufgabe {
	
	private int h_id;
	private String titel;
	private String beschreibung;
	private Date abgabeTermin;
	private boolean open;
	
	public Hausaufgabe(int h_id, String titel, String beschreibung, Date abgabeTermin, boolean open, int k_id) {
		this.h_id = h_id;
		this.titel = titel;
		this.beschreibung = beschreibung;
		this.abgabeTermin = abgabeTermin;
		this.open = open;
		this.k_id = k_id;
	}
	
	public Hausaufgabe(String titel, String beschreibung, Date abgabeTermin, boolean open, int k_id) {
		this.titel = titel;
		this.beschreibung = beschreibung;
		this.abgabeTermin = abgabeTermin;
		this.open = open;
		this.k_id = k_id;
	}
	
	public Hausaufgabe(ResultSet rs) throws SQLException {
		this.h_id = rs.getInt(1);
		this.titel = rs.getString(2);
		this.beschreibung = rs.getString(3);
		this.abgabeTermin = rs.getDate(4);
		this.open = rs.getBoolean(5);
		this.k_id = rs.getInt(6);
	}
	
	public int getH_id() {
		return h_id;
	}
	public void setH_id(int h_id) {
		this.h_id = h_id;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	public Date getAbgabeTermin() {
		return abgabeTermin;
	}
	public void setAbgabeTermin(Date abgabeTermin) {
		this.abgabeTermin = abgabeTermin;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public int getK_id() {
		return k_id;
	}
	public void setK_id(int k_id) {
		this.k_id = k_id;
	}
	private int k_id;

}
