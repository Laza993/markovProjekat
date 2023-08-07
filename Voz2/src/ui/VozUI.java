package ui;

import java.util.Collection;

import Voz_Util.Konzola;
import dao.VozDAO;
import model.Vagon;
import model.Voz;


public class VozUI {

	private static VozDAO vozDAO;

	public static void setVozDAO(VozDAO vozDAO) {
		VozUI.vozDAO = vozDAO;
	}
//(long id, String broj, String naziv, LocalDateTime datumVremePolaska, double cenaKarte, int brojMesta)
//	private static final Tabela<Voz> TABELA = new Tabela<>(
//	"%2s %10s %15s %-20s %10s %10s",
//	new Object[] {"ID", "Broj", "Naziv", "Datum Vreme Polaska", "Cena Karte", "Broj Mesta"}
//) {
//
//	@Override
//	protected List<Object[]> uredi(Voz vrednost) {
//		List<Object[]> rezultat = new ArrayList<>();
//		rezultat.add(new Object[] {
//			vrednost.getId(), 
//			vrednost.getBroj(),
//			vrednost.getNaziv(),
//			Konzola.formatiraj(vrednost.getDatumVremePolaska()),
//			vrednost.getCenaKarte(),
//			vrednost.getBrojMesta()
//		});
//		return rezultat;
//	}
//
//};

	public static void prikazSvih() {

		try {
			Collection<Voz> vozovi = vozDAO.getAll();

			// TABELA.prikazi(vozovi);

			System.out.println();
			for (Voz voz : vozovi) {
				System.out.println(voz);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Doslo je do greske");
		}


	}

	public static Voz pronalazenje() throws Exception {
		
//		prikazSvih();

		long id = Konzola.ocitajLong("Unesite ID voza");

		Voz voz = vozDAO.get(id);
		if (voz == null) {
			Konzola.prikazi("Voz nije pronadjen");
		}
		return voz;
	}

	public static void prikazSvihVagonaVoza() {
		
		try {
			Voz voz = pronalazenje();
			if (voz == null) {
				return;
			}
			// TABELA.prikazi(voz); ako ide preko tabele
			
			System.out.println();
			System.out.println(voz); // print voz
			
			for (Vagon vagon : voz.getVagoni()) {
				System.out.println(vagon); 
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Doslo je do greske");
		}
		
	}
	
//	public static void prikazVozovaProdateKarte() {
//	    try {
//	        Collection<Voz> vozovi = vozDAO.getAll(); // Pretpostavka da postoji DAO objekat za pristup vozovima
//
//	        for (Voz voz : vozovi) {
//	            Collection<Karta> prodateKarte = kartaDAO.find(voz); // Pretpostavka da postoji DAO objekat za pristup kartama
//	            if (!prodateKarte.isEmpty()) {
//	                System.out.println(voz); // Prikaz detalja o vozu
//	                for (Karta karta : prodateKarte) {
//	                    System.out.println(karta); // Prikaz detalja o svakoj prodatoj karti
//	                }
//	                System.out.println(); // Prazan red između vozova
//	            }
//	        }
//	    } catch (Exception ex) {
//	        ex.printStackTrace();
//	        System.out.println("Došlo je do greške");
//	    }
//	}
	






}
