package com.bioskop.dao.impl.file;

import java.util.Collection;

import com.bioskop.dao.ProjekcijaDAO;
import com.bioskop.model.Bioskop;
import com.bioskop.model.Projekcija;

public class FileProjekcijaDAO implements ProjekcijaDAO {

	@Override
	public Projekcija get(long id) throws Exception {
		
		return Bioskop.getProjekcije().get(id);
	}

	@Override
	public Collection<Projekcija> getAll() throws Exception {
		
		return Bioskop.getProjekcije().values();
	}

	@Override
	public void add(Projekcija projekcija) throws Exception {
		
		long id = Bioskop.nextProjekcijaId();
		
		Projekcija novaProjekcija = new Projekcija(id,
				projekcija.getDatumVreme(),
				projekcija.getSala(), 
				projekcija.getTip(), 
				projekcija.getCenaKarte(), 
				projekcija.getFilm());
		
		Bioskop.getProjekcije().put(novaProjekcija.getId(), novaProjekcija);
		Bioskop.sacuvaj();	
	}

	@Override
	public void update(Projekcija projekcija) throws Exception {
		
		Projekcija postojecaProjekcija = Bioskop.getProjekcije().get(projekcija.getId());
		postojecaProjekcija.setDatumVreme(projekcija.getDatumVreme());
		postojecaProjekcija.setSala(projekcija.getSala());
		postojecaProjekcija.setTip(projekcija.getTip());
		postojecaProjekcija.setCenaKarte(projekcija.getCenaKarte());
		postojecaProjekcija.setFilm(projekcija.getFilm());
		
		Bioskop.sacuvaj();
	}

	@Override
	public void delete(long id) throws Exception {
		
		Projekcija postojecaPro = Bioskop.getProjekcije().get(id);
		postojecaPro.setFilm(null);   // razvezivanje
		
		Bioskop.getProjekcije().remove(id);
		Bioskop.sacuvaj();		
	}
	
	

}
