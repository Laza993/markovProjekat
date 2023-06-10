package com.bioskop.dao.impl.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import com.bioskop.dao.KorisnikDAO;
import com.bioskop.model.Korisnik;

public class DatabaseKorisnikDAO implements KorisnikDAO {
	
	private final Connection conn;
	public DatabaseKorisnikDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Korisnik get(String korisnickoIme) throws Exception {
		Korisnik korisnik = null;
		
		String sql = "SELECT lozinka, eMail, pol, administrator FROM korisnici WHERE korisnickoIme = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setString(++param, korisnickoIme);
			try(ResultSet rset = stmt.executeQuery()){
				if (rset.next()) {
					int kolona = 0;
					String lozinka = rset.getString(++kolona);
					String email = rset.getString(++kolona);
					String pol = rset.getString(++kolona);
					boolean administrator = rset.getBoolean(++kolona);
					
					korisnik = new Korisnik(korisnickoIme, lozinka, email, pol, administrator);
				}
			}
		}
		
		return korisnik;
	}

	@Override
	public Collection<Korisnik> getAll() throws Exception {
		Collection<Korisnik> korisnici = new ArrayList<>();
		
		String sql = "SELECT korisnickoIme, lozinka, eMail, pol, administrator FROM korisnici";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			try(ResultSet rset = stmt.executeQuery()){
				while(rset.next()) {
					int kolona = 0;
					String korisnickoIme = rset.getString(++kolona);
					String lozinka = rset.getString(++kolona);
					String email = rset.getString(++kolona);
					String pol = rset.getString(++kolona);
					boolean administrator = rset.getBoolean(++kolona);
					Korisnik k = new Korisnik(korisnickoIme, lozinka, email, pol, administrator);
					korisnici.add(k);
				}
			}
		}
		return korisnici;
	}

	@Override
	public void add(Korisnik korisnik) throws Exception {

		String sql = "INSERT INTO korisnici (korisnickoIme, lozinka, eMail, pol, administrator) VALUES (?, ?, ?, ?, ?)";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setString(++param, korisnik.getKorisnickoIme());
			stmt.setString(++param, korisnik.getLozinka());
			stmt.setString(++param, korisnik.geteMail());
			stmt.setString(++param, korisnik.getPol());
			stmt.setBoolean(++param, korisnik.isAdministrator());
			
			stmt.executeUpdate();
		}
	}

	@Override
	public void update(Korisnik korisnik) throws Exception {

		String sql = "UPDATE korisnici SET lozinka = ?, eMail = ?, pol = ?, administrator = ? WHERE korisnickoIme = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setString(++param, korisnik.getLozinka());
			stmt.setString(++param, korisnik.geteMail());
			stmt.setString(++param, korisnik.getPol());
			stmt.setBoolean(++param, korisnik.isAdministrator());
			stmt.setString(++param, korisnik.getKorisnickoIme());

			stmt.executeUpdate();
		}
	}

	@Override
	public void delete(String korisnickoIme) throws Exception {
		
		String sql = "DELETE FROM korisnici WHERE korisnickoIme = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setString(++param, korisnickoIme);
			stmt.executeUpdate();
		}
		
	}
	
	

}
