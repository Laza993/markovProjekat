package com.bioskop.dao.impl.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import com.bioskop.dao.ZanrDAO;
import com.bioskop.model.Zanr;

public class DatabaseZanrDAO implements ZanrDAO{
	
	private final Connection conn;
	
	public DatabaseZanrDAO(Connection conn) {
		this.conn = conn;	
	}

	@Override
	public Zanr get(long id) throws Exception {
		Zanr zanr = null;
		
		String sql = "SELECT naziv FROM zanrovi WHERE id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setLong(++param, id);
			try(ResultSet rset = stmt.executeQuery()){
				if (rset.next()) {
					int kolona = 0;
					String naziv = rset.getString(++kolona);
					zanr = new Zanr(id, naziv);
				}
			}
		}
		return zanr;
	}

	@Override
	public Collection<Zanr> getAll() throws Exception {
		Collection<Zanr> zanrovi = new ArrayList<>();
		
		String sql = "SELECT id, naziv FROM zanrovi";
		try(PreparedStatement stmt = conn.prepareStatement(sql)) {
			try(ResultSet rset = stmt.executeQuery()){
				while(rset.next()) {
					int kolona = 0;
					long id = rset.getLong(++kolona );
					String naziv = rset.getString(++kolona);
					
					Zanr zanr = new Zanr(id, naziv);
					zanrovi.add(zanr);
				}
			}
		}	
		return zanrovi;
	}

	@Override
	public void add(Zanr zanr) throws Exception {

		String sql = "INSERT INTO zanrovi (naziv) VALUES (?)";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setString(++param, zanr.getNaziv());
			
			stmt.executeUpdate();
		}
		
	}

	@Override
	public void update(Zanr zanr) throws Exception {

		String sql = "UPDATE zanrovi SET naziv = ?, WHERE id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setString(++param, zanr.getNaziv());
			stmt.setLong(++param, zanr.getId());
			
			stmt.executeUpdate();
		}
		
	}

	@Override
	public void delete(long id) throws Exception {

		String sql = "DELETE FROM zanrovi WHERE id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setLong(++param, id);
			
			stmt.executeUpdate();
		}
		
	}
	
	

}
