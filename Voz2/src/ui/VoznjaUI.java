package ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Voz_Util.Konzola;
import Voz_Util.Tabela;
import dao.VoznjaDAO;
import model.Karta;
import model.Voznja;

public class VoznjaUI {

	private static VoznjaDAO voznjaDAO;

	public static void setVoznjaDAO(VoznjaDAO voznjaDAO) {
		VoznjaUI.voznjaDAO = voznjaDAO;
	}

//	Voznja(long id, Voz voz, String naziv, double cenaKarte, LocalDateTime datumVremePolaska,
//			Map<Razred, Integer> brSlobodnihMestaPoRazredu, String pocetnaStanica, String krajnjaStanica,
//			List<Stanica> spisakStanica)

	private static final Tabela<Voznja> TABELA = new Tabela<>("%2s %-20s %-20s %-20s %-20s %-22s, %-15s, %-15s, %-15s",
			new Object[] { "ID", "Voz", "Naziv", "Cena Karte", "Datum Vreme Polaska", "Broj Slobodnih Mesta",
					"Pocetna Stanica", "Krajnja Stanica", "Spisak Stanica" }) {

		@Override
		protected List<Object[]> uredi(Voznja vrednost) {
			List<Object[]> rezultat = new ArrayList<>();
			rezultat.add(new Object[] { vrednost.getId(),
					vrednost.getVoz(),//.getNazivVoza(),
					vrednost.getNaziv(),
					vrednost.getCenaKarte(),
					Konzola.formatiraj(vrednost.getDatumVremePolaska()),
					vrednost.getBrSlobodnihMestaPoRazredu(),
					vrednost.getPocetnaStanica(),
					vrednost.getKrajnjaStanica(),
					vrednost.getSpisakStanica()
			});
			return rezultat;
		}

	};

	public static Voznja pronalazenje() throws Exception {
		prikazSvih();
		
		long id = Konzola.ocitajLong("Unesite ID voznje");
		Voznja voznja = voznjaDAO.get(id);
		if (voznja == null) {
			Konzola.prikazi("Voznja nije pronadjena");
		}
		return voznja;
	}

	public static void prikazSvih() {
		try {
			Collection<Voznja> voznje = voznjaDAO.getAll();
			
		//	TABELA.prikazi(voznje);
			System.out.println();
			
			for (Voznja voznja : voznje) {
				System.out.println(voznja);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
		
	}
	
	public static void PrikazVoznjeNaRelacijiIzmedjuDvaGrada() {
		
	}
	
	
	public static void PrikazVoznjeSaProdatimKartama() {
		
		try {
			Collection<Voznja> voznje = voznjaDAO.getAll();
			
			System.out.println();
			for (Voznja voznja : voznje) {
				System.out.println(voznja);
				for (Karta karta : voznja.getKarte()) {
					System.out.println(karta);
				}
				System.out.println();
			
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
