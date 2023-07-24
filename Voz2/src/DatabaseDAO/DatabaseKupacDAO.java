package DatabaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import dao.KupacDAO;
import dao.VozDAO;
import dao.VoznjaDAO;
import model.Karta;
import model.Kategorija;
import model.Kupac;
import model.Razred;
import model.Stanica;
import model.Voz;
import model.Voznja;

public class DatabaseKupacDAO implements KupacDAO {
	
	private  Connection conn;
	

	public DatabaseKupacDAO(Connection conn) {
		this.conn = conn;
	}
	@Override
	public Kupac get(long id) throws Exception {
		Kupac kupac = null;
		String sql = 
"SELECT ime, prezime, datumRodjenja, jMBG, kategorija FROM kupci WHERE id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setLong(++param, id);
			try(ResultSet rset = stmt.executeQuery()){
				if(rset.next()) {
					int kolona = 0;
					String ime = rset.getString(++kolona);
					String prezime = rset.getString(++kolona);
					LocalDate datumRodjenja = rset.getDate(++kolona).toLocalDate();
					long jmbg = rset.getLong(++kolona);
					Kategorija kategorija = Kategorija.valueOf(rset.getString(++kolona));
					kupac = new Kupac(id, ime, prezime, datumRodjenja, jmbg, kategorija);
				}
			}
		}
		return kupac;
	}
//Karta(long id, LocalDateTime datumVremeProdaje, Kupac kupac, Razred razred, Voz voz, Voznja voznja)	
	@Override
	public Collection<Karta> getPrikazIstorijeKarata(Kupac kupac) throws Exception {
		Collection<Karta> karte = new ArrayList<>();
		String sql = 
"SELECT k.id, k.razred, k.vozId, k.voznjaId, c.id, c.ime, c.prezime, "
+ "c.datumRodjenja, c.jMBG, c.kategorija FROM karte k INNER JOIN kupci c "
+ "ON k.kupacId = c.id WHERE datumVremeProdaje <= ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setTimestamp(++param, Timestamp.valueOf(LocalDateTime.now()));
			try(ResultSet rset = stmt.executeQuery()){
				while(rset.next()) {
					int kolona = 0;
					long kId = rset.getLong(++kolona);
					Razred kRazred = Razred.valueOf(rset.getString(++kolona));
					long kVozId = rset.getLong(++kolona);
					VozDAO vozDAO = new DatabaseVozDAO(conn);
					Voz voz = vozDAO.get(kVozId);
					long kVoznjaId = rset.getLong(++kolona);
					VoznjaDAO voznjaDAO = new DatabaseVoznjaDAO(conn, vozDAO);
					Voznja voznja = voznjaDAO.get(kVoznjaId);
					
					long cId = rset.getLong(++kolona);
					String cIme = rset.getString(++kolona);
					String cPrezime = rset.getString(++kolona);
					LocalDate cDatumRodjenja = rset.getDate(++kolona).toLocalDate();
					long cJMBG = rset.getLong(++kolona);
					Kategorija cKategorija = Kategorija.valueOf(rset.getString(++kolona));
					
				Kupac kupac1 = new Kupac(cId, cIme, cPrezime, cDatumRodjenja, cJMBG, cKategorija);
				LocalDateTime datumVremeProdaje = LocalDateTime.now();
					Karta karta = new Karta(kId, datumVremeProdaje, kupac1, kRazred, voz, voznja);
					karte.add(karta);
				}
			}
		}
		
		return karte;
	}
//	 Voznja(long id, Voz voz, String naziv, double cenaKarte, LocalDateTime datumVremePolaska,
//	Map<Razred, Integer> brSlobodnihMestaPoRazredu, String pocetnaStanica, String krajnjaStanica,
//	List<Stanica> spisakStanica)
//Kupac(long id, String ime, String prezime, LocalDate datumRodjenja, long jMBG, Kategorija kategorija)
	@Override
	public Collection<Voznja> getPrikazPredstojecihVoznji(Kupac kupac) throws Exception {
		Collection<Voznja> voznje = new ArrayList<>();
		String sql = 
"SELECT id, vozId, naziv, cenaKarte, pocetnaStanica, krajnjaStanica, spisakStanica "
+ "FROM voznje "
+ "WHERE datumVremePolaska > ? "
+ "AND id "
+ "IN (SELECT voznjaId FROM karte WHERE kupacId = ?)";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setTimestamp(++param, Timestamp.valueOf(LocalDateTime.now()));
			stmt.setLong(++param, kupac.getId());
			try(ResultSet rset = stmt.executeQuery()){
				while(rset.next()) {
					int kolona = 0;
					long id = rset.getLong(++kolona);
					long vozId = rset.getLong(++kolona);
					VoznjaDAO voznjaDAO = new DatabaseVoznjaDAO(conn, null);
					Voznja voznja = voznjaDAO.get(vozId);
					String naziv = rset.getString(++kolona);
					double cenaKarte = rset.getDouble(++kolona);
					String pocetnaStanica = rset.getString(++kolona);
					String krajnjaStanica = rset.getString(++kolona);
					List<Stanica> spisakStanica = new ArrayList<>();
					
					LocalDateTime trenutnoVreme = LocalDateTime.now();
		//			LocalDateTime datumVremePolaska = LocalDateTime.now().isAfter(trenutnoVreme);
							
				}
			}
		}
		
	
		
		
		return voznje;
	}

}
