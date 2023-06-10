package com.bioskop.dao.impl.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import com.bioskop.dao.FilmDAO;
import com.bioskop.model.Film;

public class DatabaseFilmDAO implements FilmDAO {
	
	private final Connection conn;  //Connection je interfejs u Javi koji predstavlja vezu sa bazom podataka.

	public DatabaseFilmDAO(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Film getId(long id) throws Exception {
		Film film = null;
		
		String sql = "SELECT  naziv, trajanje FROM filmovi WHERE id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setLong(++param, id);
			try(ResultSet rset = stmt.executeQuery()){
				if(rset.next()) {
					int kolona = 0;
					String naziv = rset.getString(++kolona);
					int trajanje = rset.getInt(++kolona);
					
					 film = new Film(id, naziv, trajanje);
				}
			}
		}
		return film;
	}

	@Override
	public Collection<Film> getAll() throws Exception {
		Collection<Film> filmovi = new ArrayList<>();
		
		String sql = "SELECT id, naziv, trajanje FROM filmovi";
		try(PreparedStatement stmt = conn.prepareStatement(sql)) {
			try(ResultSet rset = stmt.executeQuery()) {
				while(rset.next()) {
					int kolona = 0;
					long id = rset.getLong(++kolona);
					String naziv = rset.getString(++kolona);
					int trajanje = rset.getInt(++kolona);
					
				Film film = new Film(id, naziv, trajanje);
				filmovi.add(film);
				}
			}
		}
		return filmovi;
	}

	@Override
	public void add(Film film) throws Exception {

		String sql = "INSERT INTO filmovi (naziv, trajanje) VALUES (?, ?)";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setString(++param, film.getNaziv());
			stmt.setInt(++param, film.getTrajanje());
			
			stmt.executeUpdate();
		}
		
	}

	@Override
	public void update(Film film) throws Exception {

		String sql = "UPDATE filmovi SET naziv = ?, trajanje = ?, WHERE id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setString(++param, film.getNaziv());
			stmt.setInt(++param, film.getTrajanje());
			stmt.setLong(++param, film.getId());
			
			stmt.executeUpdate();
		}
		
	}

	@Override
	public void delete(long id) throws Exception {
		
		String sql = "DELETE FROM filmovi WHERE id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setLong(++param, id);
			stmt.executeUpdate();
		}
		
	}

}
