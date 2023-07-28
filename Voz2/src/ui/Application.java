package ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import DatabaseDAO.DatabaseKartaDAO;
import DatabaseDAO.DatabaseKupacDAO;
import DatabaseDAO.DatabaseVagonDAO;
import DatabaseDAO.DatabaseVozDAO;
import DatabaseDAO.DatabaseVoznjaDAO;
import Voz_Util.Meni;
import Voz_Util.Meni.FunkcionalnaStavkaMenija;
import Voz_Util.Meni.IzlaznaStavkaMenija;
import Voz_Util.Meni.StavkaMenija;
import dao.KartaDAO;
import dao.KupacDAO;
import dao.VagonDAO;
import dao.VozDAO;
import dao.VoznjaDAO;

public class Application {
	
	

	private static void initDatabase() throws SQLException {
		
		Connection conn = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/voz3?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Belgrade",
			"root",
			"root");
		
		VozDAO vozDAO = new DatabaseVozDAO(conn);
		KupacDAO kupacDAO = new DatabaseKupacDAO(conn);
		VoznjaDAO voznjaDAO = new DatabaseVoznjaDAO(conn, vozDAO);
		KartaDAO kartaDAO = new DatabaseKartaDAO(conn, vozDAO, voznjaDAO, kupacDAO);
		VagonDAO vagonDAO = new DatabaseVagonDAO(conn, vozDAO);
		
		VozUI.setVozDAO(vozDAO);
		VoznjaUI.setVoznjaDAO(voznjaDAO);
		KartaUI.setKartaDAO(kartaDAO);
		VagonUI.setVagonDAO(vagonDAO);
		
		
	}
	
	static {
		try {
			initDatabase();
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Doslo je do greske prilikom povezivanja sa bazom podataka");
			System.exit(1); // prekid programa (u suprotnom bi se započela main metoda)
		}
	}
public static void main(String[] args) {
		
		Meni.pokreni("VOZ", new StavkaMenija[] {
				new IzlaznaStavkaMenija("Izlaz"),
				new FunkcionalnaStavkaMenija("Prikaz svih voznji") {

					@Override
					public void izvrsi() { VoznjaUI.prikazSvih(); }
					
				}, 
				new FunkcionalnaStavkaMenija("Prikaz jedne voznje sa prodatim kartama") {

					@Override
					public void izvrsi() { VoznjaUI.PrikazVoznjeSaProdatimKartama();; }
					
				}, 
				new FunkcionalnaStavkaMenija("Prodaja karata") {

					@Override
					public void izvrsi() { KartaUI.prodaja(); }
					
				}, 
				new FunkcionalnaStavkaMenija("Prikaz svih vagona za voz") {

					@Override
					public void izvrsi() { VozUI.prikazSvihVagonaVoza(); }
					
				}, 
				new FunkcionalnaStavkaMenija("Prikaz svih vozova") {

					@Override
					public void izvrsi() { VozUI.prikazSvih(); }
					
				}
			});
		
	}

}
