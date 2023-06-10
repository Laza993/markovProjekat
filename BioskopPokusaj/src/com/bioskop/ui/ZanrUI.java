package com.bioskop.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bioskop.dao.ZanrDAO;
import com.bioskop.model.Zanr;
import com.novisad.jwd.modul1.util.Konzola;
import com.novisad.jwd.modul1.util.Meni;
import com.novisad.jwd.modul1.util.Meni.FunkcionalnaStavkaMenija;
import com.novisad.jwd.modul1.util.Meni.IzlaznaStavkaMenija;
import com.novisad.jwd.modul1.util.Meni.StavkaMenija;
import com.novisad.jwd.modul1.util.Tabela;

public class ZanrUI {
	
	private static ZanrDAO zanrDAO;
	
	public static void setZanrDAO(ZanrDAO zanrDAO) {
		ZanrUI.zanrDAO = zanrDAO;
	}
	
	
	private static final Tabela<Zanr> TABELA = new Tabela<>("%2s %-20S",

			new Object[] { "ID", "Naziv" }) {
		protected List<Object[]> uredi(Zanr vrednost) {
			List<Object[]> result = new ArrayList<>();
			result.add(new Object[] { vrednost.getId(), vrednost.getNaziv() });
			return result;
		}
	};
	
	public static Zanr pronalazenje() throws Exception {
		prikazSvih();
		
		long id = Konzola.ocitajLong("Unesite ID zanra");
		
		Zanr zanr = zanrDAO.get(id);
		if (zanr == null) {
			Konzola.prikazi("Zanr nije pronadjen");
		}
		return zanr;
	}

	private static void prikazSvih() {

		try {
			Collection<Zanr> zanrovi = zanrDAO.getAll();
			TABELA.prikazi(zanrovi);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
		
	}
	
	private static void prikaz() {
		
		try {
			Zanr zanr =  pronalazenje();
			if (zanr == null) {
				return;
			}
			TABELA.prikazi(zanr);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
	}
	
	private static void dodavanje() {
		
		String naziv = "";
		while(naziv.equals("")) {
			naziv = Konzola.ocitajString("Unesite naziv");
		}
		Zanr zanr = new Zanr(naziv);
		try {
			zanrDAO.add(zanr);
			Konzola.prikazi("Uspesno dodavanje");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
	}
	
	private static void izmena() {
		
		try {
			Zanr zanr = pronalazenje();
			if (zanr == null) {
				return;
			}
			String naziv = "";
			while(naziv.equals("")) {
				naziv = Konzola.ocitajString("Unesite naziv");
				zanr.setNaziv(naziv);
			}
			zanrDAO.update(zanr);
			Konzola.prikazi("Uspesna izmena");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
		
	}
	
	private static void brisanje() {
		
		try {
			Zanr zanr = pronalazenje();
			if (zanr == null) {
				return;
			}
			zanrDAO.delete(zanr.getId());
			Konzola.prikazi("Uspesno brisanje");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
	}
	
	public static void meni() {
		Meni.pokreni("Zanrovi", new StavkaMenija[] {
			new IzlaznaStavkaMenija("Povratak"),
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
