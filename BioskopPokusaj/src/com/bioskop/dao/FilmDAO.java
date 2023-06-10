package com.bioskop.dao;

import java.util.Collection;

import com.bioskop.model.Film;

public interface FilmDAO {
	
	
	Film getId(long id) throws Exception;
	Collection<Film> getAll() throws Exception;
	void add(Film film) throws Exception;
	void update(Film film) throws Exception;
	void delete(long id) throws Exception;

}
