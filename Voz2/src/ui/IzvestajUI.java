package ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import Voz_Util.Konzola;
import dao.VozDAO;
import model.Izvestaj;
import model.Karta;
import model.Voz;

public class IzvestajUI {

	private static VozDAO vozDAO;

	public static void setVozDAO(VozDAO vozDAO) {
		IzvestajUI.vozDAO = vozDAO;
	}

	public static void izvestavanje() {

		LocalDateTime min = Konzola.ocitajDateTime("Unesite pocetni datum i vreme: ");
		LocalDateTime max = Konzola.ocitajDateTime("Unesite krajnji datum i vreme: ");

		try {
			Collection<Voz> vozovi = vozDAO.getAll();

			Set<String> nazivVoza = new LinkedHashSet<>(); // smestam nazive voza ovde
			for (Voz voz : vozovi) {
				nazivVoza.add(voz.getNaziv());
			}

			List<Izvestaj> izvestaji = new ArrayList<>();

			for (String nazivVozaSaPodacima : nazivVoza) { // prebacujem nazive voza ovde: nazivVozaSaPodacima!

				double ukupanPrihod = 0;
				int maxBrojProdatihKarata = Integer.MIN_VALUE;
				LocalDate datumMaxBrojemProdatihKarata = null;

				for (Voz vozZ : vozovi) {
					if (vozZ.getNaziv().equals(nazivVozaSaPodacima) 
							&& vozZ.getDatumVremePolaska().compareTo(min) >=0 
							&& vozZ.getDatumVremePolaska().compareTo(max) >= 0) { 
						
						for (Karta  karta : vozZ.getKarte()) {
							ukupanPrihod += karta.cenaKarte();
						}
						int brojProdatihKarata = vozZ.getKarte().size();
						if (brojProdatihKarata > maxBrojProdatihKarata) {
							maxBrojProdatihKarata = brojProdatihKarata;
							datumMaxBrojemProdatihKarata = vozZ.getDatumPolaska();
						}
					}
				}
		Izvestaj izvestaj = new Izvestaj(nazivVozaSaPodacima, ukupanPrihod, datumMaxBrojemProdatihKarata);
		izvestaji.add(izvestaj);
			}
			izvestaji.sort(Izvestaj :: commperUkupanPrihod);
			System.out.println();
			
			for (Izvestaj izvestaj : izvestaji) {
				System.out.println(izvestaj);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Doslo je do greske");
		}

	}

}
