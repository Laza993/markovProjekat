package ui;

import java.time.LocalDateTime;

import Voz_Util.Konzola;
import dao.KartaDAO;
import model.Karta;
import model.Kategorija;
import model.Kupac;
import model.Razred;
import model.Voz;
import model.Voznja;

public class KartaUI {
	
	private static KartaDAO kartaDAO;
	
	public static void setKartaDAO(KartaDAO kartaDAO) {
		KartaUI.kartaDAO = kartaDAO;
	}
	//Karta(long id, LocalDateTime datumVremeProdaje, String kupac, int razred, Voz voz)
//	private static final Tabela<Karta> TABELA = new Tabela<>(
//	"%2s %-20s %-25s %8s %-30s",
//	new Object[] {"ID", "Datum Vreme Prodaje", "Kupac", "Razred", "Voz"}
//) {
//
//	@Override
//	protected List<Object[]> uredi(Karta vrednost) {
//		List<Object[]> rezultat = new ArrayList<>();
//		rezultat.add(new Object[] {
//			vrednost.getId(), 
//			Konzola.formatiraj(vrednost.getDatumVremeProdaje()),
//			vrednost.getKupac(),
//			vrednost.getRazred(),
//			vrednost.getVoz().getId()
//		});
//		return rezultat;
//	}
//
//};
	
	public static void prodaja() {
		
		try {
			Voznja voznja = VoznjaUI.pronalazenje();
			if (voznja == null) {
				System.out.println("Voznja nije pronadjena");
				return;
			}
			if (voznja.isPopunjen()) {
				System.out.println("Nema slobodnih mesta");
				return;
			}
			if (voznja.isPosao()) {
				System.out.println("Voz je vec krenuo");
				return;
			}
			
			Voz voz = VozUI.pronalazenje();
			if (voz == null) {
				System.out.println("Voz nije pronadjen");
				return;
			}
			
			Razred razredVoza = Konzola.ocitajEnum("Unesite razred (PRVI, DRUGI, TRECI): ", Razred.class);
			
			Kategorija kategorija = Konzola.ocitajEnum("Unesite kategoriju kupca(STUDENT, PENZIONER, DETE, INVALID): ", Kategorija.class);
			Kupac kupac = new Kupac();
			kupac.setKategorija(kategorija);
			
			LocalDateTime datumVremeProdaje = LocalDateTime.now();
			
		
			Karta karta = new Karta(datumVremeProdaje, kupac, razredVoza, voz, voznja);
			kartaDAO.add(karta);
	
			System.out.println("Karta uspesno dodata");
			
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Doslo je do greske");
		}
		
		
		
		
		
		
		
	}
}
