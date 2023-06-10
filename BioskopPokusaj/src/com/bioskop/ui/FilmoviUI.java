package com.bioskop.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bioskop.dao.FilmDAO;
import com.bioskop.model.Film;
import com.bioskop.model.Zanr;
import com.novisad.jwd.modul1.util.Konzola;
import com.novisad.jwd.modul1.util.Meni;
import com.novisad.jwd.modul1.util.Meni.FunkcionalnaStavkaMenija;
import com.novisad.jwd.modul1.util.Meni.IzlaznaStavkaMenija;
import com.novisad.jwd.modul1.util.Meni.StavkaMenija;
import com.novisad.jwd.modul1.util.Tabela;

public class FilmoviUI {

	private static FilmDAO filmDAO;

	public static void setFilm(FilmDAO filmDAO) {
		FilmoviUI.filmDAO = filmDAO;
	}

	private static final Tabela<Film> TABELA = new Tabela<>("%2s %-50s %-10s %-20s",
			new Object[] { "ID", "Naziv", "Trajanje", "Zanrovi" }) {
		@Override
		protected List<Object[]> uredi(Film vrednost) {
			List<Object[]> result = new ArrayList<>();
			result.add(new Object[] { vrednost.getId(), vrednost.getNaziv(), vrednost.getTrajanje(), "" });
			for (Zanr zanr : vrednost.getZanrovi()) {
				result.add(new Object[] { "", "", "", zanr.getNaziv() });
			}
			return result;
		}
	};

	public static Film pronalazenje() throws Exception {
		prikazSvih();

		long id = Konzola.ocitajLong("Unesite ID filma");

		Film film = filmDAO.getId(id);
		if (film == null) {
			Konzola.prikazi("Film nije pronadjen");
		}
		return film;
	}

	private static void prikazSvih() {

		try {
			Collection<Film> filmovi = filmDAO.getAll();
			TABELA.prikazi(filmovi);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
	}

	private static void prikaz() {
		try {
			Film film = pronalazenje();
			if (film == null) {
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
	}

	private static void dodavanje() {

		String naziv = "";
		while (naziv.equals("")) {
			naziv = Konzola.ocitajString("Unesite naziv");
		}

		int trajanje = 0;
		while (trajanje <= 5) {
			trajanje = Konzola.ocitajInt("Unesite trajanje");
		}
		Film film = new Film(naziv, trajanje);
		try {
			filmDAO.add(film);
			Konzola.prikazi("Uspesno dodavanje");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
	}

	private static void izmena() {

		try {
			Film film = pronalazenje();
			if (film == null) {
				return;
			}
			String naziv = "";
			while (naziv.equals("")) {
				naziv = Konzola.ocitajString("Unesite naziv");
				film.setNaziv(naziv);
			}
			int trajanje = 0;
			while (trajanje <= 5) {
				trajanje = Konzola.ocitajInt("Unesite trajanje");
				film.setTrajanje(trajanje);
			}

			filmDAO.update(film);
			Konzola.prikazi("Uspesna izmena");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
	}

	private static void brisanje() {

		try {
			Film film = pronalazenje();
			if (film == null)
				return;

			filmDAO.delete(film.getId());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}

	}

	public static void meni() {
		Meni.pokreni("Filmovi",
				new StavkaMenija[] { new IzlaznaStavkaMenija("Porvratak"), 
						new FunkcionalnaStavkaMenija("Prikaz svih") {

					@Override
					public void izvrsi() {
						prikazSvih();
					}

				}, new FunkcionalnaStavkaMenija("Prikaz") {

					@Override
					public void izvrsi() {
						prikaz();
					}

				}, new FunkcionalnaStavkaMenija("Dodavanje") {

					@Override
					public void izvrsi() {
						dodavanje();
					}

				}, new FunkcionalnaStavkaMenija("Izmena") {

					@Override
					public void izvrsi() {
						izmena();
					}

				}, new FunkcionalnaStavkaMenija("Brisanje") {

					@Override
					public void izvrsi() {
						brisanje();
					}

				} });
	}
}
