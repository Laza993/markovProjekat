package com.bioskop.ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bioskop.dao.ProjekcijaDAO;
import com.bioskop.model.Projekcija;
import com.novisad.jwd.modul1.util.Konzola;
import com.novisad.jwd.modul1.util.Meni;
import com.novisad.jwd.modul1.util.Meni.FunkcionalnaStavkaMenija;
import com.novisad.jwd.modul1.util.Meni.IzlaznaStavkaMenija;
import com.novisad.jwd.modul1.util.Meni.StavkaMenija;
import com.novisad.jwd.modul1.util.Tabela;

public class ProjekcijaUI {
	
	private static ProjekcijaDAO projekcijaDAO;
	
	public static void setProjekcijaDAO(ProjekcijaDAO projekcijaDAO) {
		ProjekcijaUI.projekcijaDAO = projekcijaDAO;
	}
	
	private static final Tabela<Projekcija> TABELA = new Tabela<>("%5s %20S %5s %5s %10s %-50s",

			new Object[] { "ID", "Datum vreme", "Sala", "Tip", "Cena karte", "Film" }) {
		protected List<Object[]> uredi(Projekcija vrednost) {
			List<Object[]> result = new ArrayList<>();
			result.add(new Object[] { 
					vrednost.getId(), 
				Konzola.formatiraj(vrednost.getDatumVreme()), // DATA TIME
					vrednost.getSala(),
					vrednost.getTip(), 
					vrednost.getCenaKarte(),
					vrednost.getFilm()
						});
			return result;
		}
	};

	public static Projekcija pronalazenje() throws Exception {
		prikazSvih();
		long id = Konzola.ocitajLong("Unesite ID");
		
		Projekcija projekcija = projekcijaDAO.get(id);
		if (projekcija == null) {
			Konzola.prikazi("Projekcija nije pronadjena");
		}
		return projekcija;	
	}

	private static void prikazSvih() {

		try {
			Collection<Projekcija> projekcije = projekcijaDAO.getAll();
			TABELA.prikazi(projekcije);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}	
	}
	
	private static void prikaz() {
		
		try {
			Projekcija projekcija = pronalazenje();
			if (projekcija == null) {
				return;
			}
			TABELA.prikazi(projekcija);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
	}
	
	private static void dodavanje() {
		
		LocalDateTime datumVreme = LocalDateTime.now();
		while(datumVreme.compareTo(LocalDateTime.now()) <= 0) {
			datumVreme = Konzola.ocitajDateTime("Unesite datum i vreme");
		}
		int sala = 0;
		while(sala <= 0 || sala > 3) {
			sala = Konzola.ocitajInt("Unesite salu");
		}
		String tip = "";
		while(!(tip.equals("2D") || tip.equals("3D") || tip.equals("4D"))) {
			tip = Konzola.ocitajString("Unesite tip");
		}
		double cenaKarte = 0;
		while(cenaKarte <= 0) {
			cenaKarte = Konzola.ocitajDouble("Unesite cenu karte");
		}
		Projekcija projekcija = new Projekcija(sala, datumVreme, sala, tip, cenaKarte, null);
		
		try {
			projekcijaDAO.add(projekcija);
			Konzola.prikazi("Uspesno dodavanje");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
	}
	
	private static void izmena() {
		
		try {
			Projekcija projekcija = pronalazenje();
			if (projekcija == null) {
				return;
			}
			LocalDateTime datumVreme = LocalDateTime.now();
			while(datumVreme.compareTo(LocalDateTime.now()) <= 0) {
				datumVreme = Konzola.ocitajDateTime("Unesite datum i vreme");
				projekcija.setDatumVreme(datumVreme);
			}
			int sala = 0;
			while(sala <= 0 || sala > 3) {
				sala = Konzola.ocitajInt("Unesite salu");
				projekcija.setSala(sala);
			}
			String tip = "";
			while(!(tip.equals("2D") || tip.equals("3D") || tip.equals("4D"))) {
				tip = Konzola.ocitajString("Unesite tip");
				projekcija.setTip(tip);
			}
			double cenaKarte = 0;
			while(cenaKarte <= 0) {
				cenaKarte = Konzola.ocitajDouble("Unesite cenu karte");
				projekcija.setCenaKarte(cenaKarte);
			}
			projekcijaDAO.update(projekcija);
			Konzola.prikazi("Uspesna izmena");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
	}
	
	private static void brisanje() {
		
		try {
			Projekcija projekcija = pronalazenje();
			if (projekcija == null) {
				return;
			}
			projekcijaDAO.delete(projekcija.getId());
			Konzola.prikazi("Uspesno brisanje");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}	
	}
	
	public static void meni() {
		Meni.pokreni("Projekcije", new StavkaMenija[] {
			new IzlaznaStavkaMenija("Porvratak"),
			new FunkcionalnaStavkaMenija("Prikaz svih") {

				@Override
				public void izvrsi() { prikazSvih(); }

			},
			new FunkcionalnaStavkaMenija("Prikaz") {

				@Override
				public void izvrsi() { prikaz(); }

			},
			new FunkcionalnaStavkaMenija("Dodavanje") {

				@Override
				public void izvrsi() { dodavanje(); }

			},
			new FunkcionalnaStavkaMenija("Izmena") {

				@Override
				public void izvrsi() { izmena(); }

			},
			new FunkcionalnaStavkaMenija("Brisanje") {

				@Override
				public void izvrsi() { brisanje(); }

			}
		});
	}
}
