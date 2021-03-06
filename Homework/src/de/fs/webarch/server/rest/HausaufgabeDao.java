package de.fs.webarch.server.rest;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.fs.webarch.server.ContextListener;

public class HausaufgabeDao {

public static HausaufgabeDao instance = new HausaufgabeDao();
	
	public List<Hausaufgabe> getHausaufgaben(int kursid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Hausaufgabe> result = new ArrayList<Hausaufgabe>();
		try {
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("SELECT * FROM Hausaufgabe WHERE k_id = ?");
			ps.setInt(1, kursid);
			rs = ps.executeQuery();
			while(rs.next()) {
				result.add(new Hausaufgabe(rs));
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
	
	public Hausaufgabe getHausaufgabe(int h_id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("SELECT * FROM Hausaufgabe WHERE h_id = ?");
			ps.setInt(1, h_id);
			rs = ps.executeQuery();
			rs.next();
			return new Hausaufgabe(rs);
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try { rs.close(); } catch(SQLException e) { e.printStackTrace(); }
			try { ps.close(); } catch(SQLException e) { e.printStackTrace(); }
			try { con.close(); } catch(SQLException e) { e.printStackTrace(); }
		}
	}
	
	
	
	public void lockUnlock(int h_id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ContextListener.getDataSource().getConnection();
			Hausaufgabe h = getHausaufgabe(h_id);
			boolean lock = !h.isOpen();
			ps = con.prepareStatement("Update Hausaufgabe Set Open = ? WHERE h_id = ?");
			ps.setBoolean(1, lock);
			ps.setInt(2, h_id);
			ps.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try { ps.close(); } catch(SQLException e) { e.printStackTrace(); }
			try { con.close(); } catch(SQLException e) { e.printStackTrace(); }
		}
	}
	
	public void storeHausaufgabe(Hausaufgabe h) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO Hausaufgabe(Titel, Beschreibung, AbgabeTermin, Open, k_id) VALUES(?, ?, ?, ?, ?);";
		try {
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, h.getTitel());
			ps.setString(2, h.getBeschreibung());
			ps.setDate(3, (Date) h.getAbgabeTermin());
			ps.setBoolean(4, h.isOpen());
			ps.setInt(5, h.getK_id());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { ps.close(); } catch(SQLException e) { e.printStackTrace(); }
			try { con.close(); } catch(SQLException e) { e.printStackTrace(); }
		}
	}
	
}
