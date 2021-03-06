package de.fs.webarch.server.rest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.fs.webarch.server.ContextListener;

public class AbgabeDao {

	public static AbgabeDao instance = new AbgabeDao();
	
	public void storeAbgabe(Abgabe a) {
		Connection con = null;
		PreparedStatement ps = null;
		//-----------------------------------------------------------------------------------------------
		//						Create DB Entry for table `Abgabe`
		//-----------------------------------------------------------------------------------------------
		
		String sql = "INSERT INTO Abgabe (s_id, h_id, Attempt, Loesung) VALUES(?, ?, ?, ?);";
		try {
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, a.getS_id());
			ps.setInt(2, a.getH_id());
			ps.setInt(3, getAttempt(a.getH_id(), a.getS_id()));
			ps.setString(4, a.getLoesung());
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { ps.close(); } catch(SQLException e) { e.printStackTrace(); }
			try { con.close(); } catch(SQLException e) { e.printStackTrace(); }
		}
	}	
	
	public void assess(Abgabe bewertung) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int h_id = 0;
		String sql = "UPDATE Abgabe SET Anmerkung = ?, Punkte = ? WHERE a_id = ?;";
		try {
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, bewertung.getAnmerkung());
			ps.setFloat(2, bewertung.getPunkte());
			ps.setInt(3, bewertung.getA_id());
			ps.execute();
			try { ps.close(); } catch(SQLException e) { e.printStackTrace(); }
		
			//-----------------------------------------------------------------------------------------------
			//						Get h_id for redirecting
			//-----------------------------------------------------------------------------------------------
			ps = con.prepareStatement("SELECT h_id FROM Abgabe WHERE a_id = ? LIMIT 1;");
			ps.setInt(1, bewertung.getA_id());
			rs = ps.executeQuery();
			rs.next();
			h_id = rs.getInt(1);
			bewertung.setH_id(h_id);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try { rs.close(); } catch(SQLException e) { e.printStackTrace(); }
			try { ps.close(); } catch(SQLException e) { e.printStackTrace(); }
			try { con.close(); } catch(SQLException e) { e.printStackTrace(); }
		}
	}
	
	public Abgabe getAbgabe(int a_id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Abgabe> result = new ArrayList<Abgabe>();
		String sql = "SELECT Loesung, Anmerkung, Punkte, h_id FROM Abgabe WHERE a_id = ?;";
		try {
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, a_id);
			rs = ps.executeQuery();
			
			if(!rs.next()) return null;
			return new Abgabe(rs);
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try { rs.close(); } catch(SQLException e) { e.printStackTrace(); }
			try { ps.close(); } catch(SQLException e) { e.printStackTrace(); }
			try { con.close(); } catch(SQLException e) { e.printStackTrace(); }
		}
	}
	
	public int getAttempt(int h_id, int s_id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT Attempt FROM Abgabe WHERE h_id = ? AND s_id = ? ORDER BY Attempt desc LIMIT 1";
		try {
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, h_id);
			ps.setInt(2, s_id);
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1) + 1;
		} catch(SQLException e) {
			e.printStackTrace();
			return 1; //If there is none yet
		} finally {
			try { rs.close(); } catch(SQLException e) { e.printStackTrace(); }
			try { ps.close(); } catch(SQLException e) { e.printStackTrace(); }
			try { con.close(); } catch(SQLException e) { e.printStackTrace(); }
		}
	}
}
