package ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Voz_Util.Konzola;
import Voz_Util.Tabela;
import dao.KartaDAO;
import model.Karta;
import model.Voz;

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
			Voz voz = VozUI.pronalazenje();
			if (voz == null) {
				return;
			}
			if (voz.isPopunjen()) {
				System.out.println("Nema slobodnih mesta");
				return;
			}
			if (voz.isPosao()) {
				System.out.println("Voz je vec krenuo");
				return;
			}
			int razredVoza = Konzola.ocitajInt("Unesite razred voza");
			if (razredVoza <= 0 || razredVoza > 2) {
				System.out.println("Razred voza moze biti 1 ili 2");
				return;
			}
			String kupac = Konzola.ocitajString("Unesite kupca");
			
			LocalDateTime datumVremeProdaje = LocalDateTime.now();
			
			Karta karta = new Karta(datumVremeProdaje, kupac, razredVoza, voz);
			kartaDAO.add(karta);
			System.out.println("Karta uspesno dodata");
			
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Doslo je do greske");
		}
		
		
		
		
		
		
		
	}
}
