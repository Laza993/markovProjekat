package com.bioskop.dao;

import java.util.Collection;

import com.bioskop.model.Projekcija;

public interface ProjekcijaDAO {
	
	Projekcija get(long id) throws Exception;
	Collection<Projekcija> getAll() throws Exception;
	void add(Projekcija projekcija) throws Exception;
	void update(Projekcija projekcija) throws Exception;
	void delete(long id) throws Exception;

}
