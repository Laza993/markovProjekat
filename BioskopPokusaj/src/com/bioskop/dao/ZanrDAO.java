package com.bioskop.dao;

import java.util.Collection;

import com.bioskop.model.Zanr;

public interface ZanrDAO {
	
	Zanr get(long id) throws Exception;
	Collection<Zanr> getAll() throws Exception;
	void add(Zanr zanr) throws Exception;
	void update(Zanr zanr) throws Exception;
	void delete(long id) throws Exception;

}
