package DatabaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;


import dao.KartaDAO;
import dao.KupacDAO;
import dao.VozDAO;
import dao.VoznjaDAO;
import model.Karta;
import model.Kupac;
import model.Razred;
import model.Voz;
import model.Voznja;

public class DatabaseKartaDAO implements KartaDAO {
	
	private Connection conn;
	private VozDAO vozDAO;
	private VoznjaDAO voznjaDAO;
	private KupacDAO kupacDAO;
	
public DatabaseKartaDAO(Connection conn, VozDAO vozDAO, VoznjaDAO voznjaDAO, KupacDAO kupacDAO) {

		this.conn = conn;
		this.vozDAO = vozDAO;
		this.voznjaDAO = voznjaDAO;
		this.kupacDAO = kupacDAO;
	}
	@Override
	public Karta get(long id) throws Exception {
		Karta karta = null;
		String sql = 
//				"SELECT k.datumVremeProdaje, k.kupac, k.razred, v.id, v.broj, v.naziv, v.datumVremePolaska, "
//				+ "v.cenaKarte, v.brojMesta FROM karte k "
//				+ "LEFT JOIN vozovi v ON k.vozId = v.id WHERE k.id = ?";
		"SELECT datumVremeProdaje, kupacId, razred, vozId, voznjaId FROM karte WHERE id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setLong(++param, id);
			try(ResultSet rset = stmt.executeQuery()){
				if (rset.next()) {
					int kolona = 0;
					LocalDateTime datumVremeProdaje = rset.getTimestamp(++kolona).toLocalDateTime();
					long kupacId = rset.getLong(++kolona);
					Razred razred = Razred.valueOf(rset.getString(++kolona));
					long vozId = rset.getLong(++kolona);
					long voznjaId = rset.getLong(++kolona);
					
				
					Voznja voznja = voznjaDAO.get(voznjaId);

					Voz voz = vozDAO.get(vozId);
				
					Kupac kupac = kupacDAO.get(kupacId);
					
					
					if (karta == null) {
						karta = new Karta(id, datumVremeProdaje, kupac, razred, voz, voznja);
					}
				}
			}
		}
		return karta;
	}

//	@Override
//	public Collection<Karta> find(Voz voz) throws Exception {
//	Collection<Karta> karte = new ArrayList<>();
	
//	String sql = 														// Placeholder ?
//			"SELECT id, datumVremeProdaje, kupac, razred FROM karte WHERE vozId = ?";
//U konkretnom kodu koji ste naveli, placeholder ? se koristi u SQL upitu 
//SELECT id, datumVremeProdaje, kupac, razred FROM karte WHERE vozId = ?. Ovde se ? 
//koristi kao zamena za vozId koji će biti postavljen u upitu prilikom izvršavanja.
	
//	try(PreparedStatement stmt = conn.prepareStatement(sql)){
//		int param = 0;
//		stmt.setLong(++param, voz.getId());
		
//Nakon definisanja upita, korišćenjem metode stmt.setLong(++param, voz.getId()), 
//vrednost vozId-a (iz objekta voz) se postavlja kao vrednost za placeholder ?. 
//Ovo osigurava da se pravilan vozId koristi u upitu prilikom izvršavanja.
		
//		try(ResultSet rset = stmt.executeQuery()){
//			while(rset.next()) {
//				int kolona = 0;
//				long id = rset.getLong(++kolona);
//				LocalDateTime datumVremeProdaje = rset.getTimestamp(++kolona).toLocalDateTime();
//				String kupac = rset.getString(++kolona);
//				int razred = rset.getInt(++kolona);
//				
//				Karta karta = new Karta(id, datumVremeProdaje, kupac, razred, voz);
//				karte.add(karta);
//			}
//		}
//	}
//		return karte;
//	}

//Karta(long id, LocalDateTime datumVremeProdaje, Kupac kupac, Razred razred, Voz voz, Voznja voznja)
	@Override
	public void add(Karta karta) throws Exception {
		String sql = 				// Voz voz ZBOG VEZE
				"INSERT INTO karte (datumVremeProdaje, kupac, razred, vozId, voznjaId) VALUES (?, ?, ?, ?, ?)";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setTimestamp(++param, Timestamp.valueOf(karta.getDatumVremeProdaje()));
			stmt.setLong(++param, karta.getKupac().getId());
			stmt.setString(++param, karta.getRazred().name());
			stmt.setLong(++param, karta.getVoz().getId()); 
			stmt.setLong(++param, karta.getVoznja().getId());
			stmt.executeUpdate();
		}	
	}
}
