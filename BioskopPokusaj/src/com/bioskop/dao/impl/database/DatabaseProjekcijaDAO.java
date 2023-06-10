package com.bioskop.dao.impl.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import com.bioskop.dao.ProjekcijaDAO;
import com.bioskop.model.Projekcija;

public class DatabaseProjekcijaDAO implements ProjekcijaDAO {
	
	private final Connection conn;
	
	public DatabaseProjekcijaDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Projekcija get(long id) throws Exception {
		Projekcija projekcija = null;
		
		String sql = "SELECT datumVreme, sala, tip, cenaKarte FROM projekcije WHERE id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setLong(++param, id);
			try(ResultSet rset = stmt.executeQuery()){
				if (rset.next()) {
					int kolona = 0;
					LocalDateTime datumVreme = rset.getTimestamp(++kolona).toLocalDateTime();
					int sala = rset.getInt(++kolona);
					String tip = rset.getString(++kolona);
					double cenaKarte = rset.getDouble(++kolona);
					
					projekcija = new Projekcija(id, datumVreme, sala, tip, cenaKarte, null);
				}
			}
		}	
		return projekcija;
	}

	@Override
	public Collection<Projekcija> getAll() throws Exception {
		Collection<Projekcija> projekcije = new ArrayList<>();
		
		String sql = "SELECT id, datumVreme, sala, tip, cenaKarte FROM projekcije";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			try(ResultSet rset = stmt.executeQuery()){
				while(rset.next()) {
					int kolona = 0;
					long id = rset.getLong(++kolona);
					LocalDateTime datumVreme = rset.getTimestamp(++kolona).toLocalDateTime();
					int sala = rset.getInt(++kolona);
					String tip = rset.getString(++kolona);
					double cenaKarte = rset.getDouble(++kolona);
					
				Projekcija	projekcija = new Projekcija(id, datumVreme, sala, tip, cenaKarte, null);
				projekcije.add(projekcija);
				}
			}
		}
		
		
		return projekcije;
	}

	@Override
	public void add(Projekcija projekcija) throws Exception {
		
		String sql = "INSERT INTO projekcije(datumVreme, sala, tip, cenaKarte) VALUES (?, ?, ?, ?)";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setTimestamp(++param, Timestamp.valueOf(projekcija.getDatumVreme()));
			stmt.setInt(++param, projekcija.getSala());
			stmt.setString(++param, projekcija.getTip());
			stmt.setDouble(++param, projekcija.getCenaKarte());
			
			stmt.executeUpdate();
		}
	}

	@Override
	public void update(Projekcija projekcija) throws Exception {

		String sql = "UPDATE projekcije SET datumVreme = ?, sala = ?, tip = ?, cenaKarte = ? WHERE id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setTimestamp(++param, Timestamp.valueOf(projekcija.getDatumVreme()));
			stmt.setInt(++param, projekcija.getSala());
			stmt.setString(++param, projekcija.getTip());
			stmt.setDouble(++param, projekcija.getCenaKarte());
			stmt.setLong(++param, projekcija.getId());

			stmt.executeUpdate();
		}
		
	}

	@Override
	public void delete(long id) throws Exception {

		String sql = "DELETE FROM projekcije WHERE id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setLong(++param, id);
			stmt.executeUpdate();
		}
		
	}

}
