package com.bioskop.dao.impl.file;

import java.util.Collection;

import com.bioskop.dao.KorisnikDAO;
import com.bioskop.model.Bioskop;
import com.bioskop.model.Korisnik;

public class FileKorisnikDAO implements KorisnikDAO {

	@Override
	public Korisnik get(String korisnickoIme) throws Exception {
		
		return Bioskop.getKorisnici().get(korisnickoIme);
	}

	@Override
	public Collection<Korisnik> getAll() throws Exception {
		
		return Bioskop.getKorisnici().values();
	}

	@Override
	public void add(Korisnik korisnik) throws Exception {

		Korisnik noviKorisnik = new Korisnik(
				korisnik.getKorisnickoIme(),
				korisnik.getLozinka(), 
				korisnik.geteMail(), 
				korisnik.getPol(), 
				korisnik.isAdministrator());
		
		Bioskop.getKorisnici().put(noviKorisnik.getKorisnickoIme(), noviKorisnik);
		Bioskop.sacuvaj();
	}

	@Override
	public void update(Korisnik korisnik) throws Exception {
		
		Korisnik noviKorisnik = Bioskop.getKorisnici().get(korisnik.getKorisnickoIme());
		noviKorisnik.setLozinka(korisnik.getLozinka());
		noviKorisnik.seteMail(korisnik.geteMail());
		noviKorisnik.setPol(korisnik.getPol());
		noviKorisnik.setAdministrator(korisnik.isAdministrator());
		Bioskop.sacuvaj();	
	}

	@Override
	public void delete(String korisnickoIme) throws Exception {
		Bioskop.getKorisnici().remove(korisnickoIme);
		Bioskop.sacuvaj();
		
	}

}
