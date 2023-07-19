package ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import DatabaseDAO.DatabaseKartaDAO;
import DatabaseDAO.DatabaseVozDAO;
import Voz_Util.Meni;
import Voz_Util.Meni.FunkcionalnaStavkaMenija;
import Voz_Util.Meni.IzlaznaStavkaMenija;
import Voz_Util.Meni.StavkaMenija;
import dao.KartaDAO;
import dao.VozDAO;

public class Application {
	
	

	private static void initDatabase() throws SQLException {
		
		Connection conn = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/voz2?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Belgrade",
			"root",
			"root");
		
		KartaDAO kartaDAO = new DatabaseKartaDAO(conn);
		VozDAO vozDAO = new DatabaseVozDAO(conn, kartaDAO);
		
		KartaUI.setKartaDAO(kartaDAO);
		VozUI.setVozDAO(vozDAO);
		IzvestajUI.setVozDAO(vozDAO);
		
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
				new FunkcionalnaStavkaMenija("Prikaz svih vozova") {

					@Override
					public void izvrsi() { VozUI.prikazSvih(); }
					
				}, 
				new FunkcionalnaStavkaMenija("Prikaz voza sa prodatim kartama") {

					@Override
					public void izvrsi() { VozUI.prikaz(); }
					
				}, 
				new FunkcionalnaStavkaMenija("Prodaja karata") {

					@Override
					public void izvrsi() { KartaUI.prodaja(); }
					
				}, 
				new FunkcionalnaStavkaMenija("Izveštavanje") {

					@Override
					public void izvrsi() { IzvestajUI.izvestavanje(); }
					
				}
			});
		
	}

}
