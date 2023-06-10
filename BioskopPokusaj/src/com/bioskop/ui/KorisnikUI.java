package com.bioskop.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bioskop.dao.KorisnikDAO;
import com.bioskop.model.Korisnik;
import com.novisad.jwd.modul1.util.Konzola;
import com.novisad.jwd.modul1.util.Meni;
import com.novisad.jwd.modul1.util.Tabela;
import com.novisad.jwd.modul1.util.Meni.FunkcionalnaStavkaMenija;
import com.novisad.jwd.modul1.util.Meni.IzlaznaStavkaMenija;
import com.novisad.jwd.modul1.util.Meni.StavkaMenija;

public class KorisnikUI {

	private static KorisnikDAO korisnikDAO;

	public static void setKorisnikDAO(KorisnikDAO korisnikDAO) {
		KorisnikUI.korisnikDAO = korisnikDAO;
	}

	private static final Tabela<Korisnik> TABELA = new Tabela<>("%-20s %-20S %-15s %-10s %-15s",

			new Object[] { "Korisnicko ime", "Lozinka", "E-mail", "Pol", "Administrator" }) {
		protected List<Object[]> uredi(Korisnik vrednost) {
			List<Object[]> result = new ArrayList<>();
			result.add(new Object[] { vrednost.getKorisnickoIme(), vrednost.getLozinka(), vrednost.geteMail(),
					vrednost.getPol(), vrednost.isAdministrator() ? "da" : "ne" });
			return result;
		}
	};

	private static Korisnik pronalazenje() throws Exception {
		prikazSvih();
		String korisnickoIme = Konzola.ocitajString("Unesite korisnicko ime");

		Korisnik korisnik = korisnikDAO.get(korisnickoIme);
		if (korisnik == null) {
			Konzola.prikazi("Korisnik nije pronadjen");
		}
		return korisnik;
	}

	private static void prikazSvih() {

		try {
			Collection<Korisnik> korisnici = korisnikDAO.getAll();
			TABELA.prikazi(korisnici);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
	}

	private static void prikaz() {

		try {
			Korisnik korisnik = pronalazenje();
			if (korisnik == null) {
				return;
			}
			TABELA.prikazi(korisnik);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
	}

	private static void dodavanje() {

		String korisnickoIme = "";
		while (korisnickoIme.equals("")) {
			korisnickoIme = Konzola.ocitajString("Unesite korisnicko ime");
		}
		String lozinka = "";
		while (lozinka.equals("")) {
			lozinka = Konzola.ocitajString("Unesite lozinku");
		}
		String eMail = "";
		while (eMail.equals("")) {
			eMail = Konzola.ocitajString("Unesite E-mail");
		}
		String pol = "";
		while (pol.equals("") || !(pol.equals("m") || pol.equals("z"))) {
			pol = Konzola.ocitajString("Unesite pol (m/z)");
			pol = pol.equals("m") ? "muski" : "zenski";
		}

		boolean administrator = Konzola.ocitajIzbor("Da li je korisnik administrator");

		Korisnik korisnik = new Korisnik(korisnickoIme, lozinka, eMail, pol, administrator);

		try {
			korisnikDAO.add(korisnik);
			Konzola.prikazi("Uspesno dodavanje");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
	}

	private static void izmena() {

		try {
			Korisnik korisnik = pronalazenje();
			if (korisnik == null) {
				return;
			}

			String lozinka = "";
			while (lozinka.equals("")) {
				lozinka = Konzola.ocitajString("Unesite lozinku");
				korisnik.setLozinka(lozinka);
			}
			String eMail = "";
			while (eMail.equals("")) {
				eMail = Konzola.ocitajString("Unesite E-mail");
				korisnik.seteMail(eMail);
			}
			String pol = "";
			while (pol.equals("") || !(pol.equals("m") || pol.equals("z"))) {
				pol = Konzola.ocitajString("Unesite pol (m/z)");
				pol = pol.equals("m") ? "muski" : "zenski";
				korisnik.setPol(pol);
			}

			boolean administrator = Konzola.ocitajIzbor("Da li je korisnik administrator");
			korisnik.setAdministrator(administrator);

			korisnikDAO.update(korisnik);
			Konzola.prikazi("Uspesno dodavanje");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
	}

	private static void brisanje() {

		try {
			Korisnik korisnik = pronalazenje();
			if (korisnik == null) {
				return;
			}
			korisnikDAO.delete(korisnik.getKorisnickoIme());
			Konzola.prikazi("Uspesno obrisano");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}

	}

	public static void meni() {
		Meni.pokreni("Korisnici",
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
