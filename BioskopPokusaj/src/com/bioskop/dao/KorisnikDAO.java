package com.bioskop.dao;

import java.util.Collection;

import com.bioskop.model.Korisnik;

public interface KorisnikDAO {

	Korisnik get(String korisnickoIme) throws Exception;
	Collection<Korisnik> getAll() throws Exception;
	void add(Korisnik korisnik) throws Exception;
	void update(Korisnik korisnik) throws Exception;
	void delete(String korisnickoIme) throws Exception;

}
