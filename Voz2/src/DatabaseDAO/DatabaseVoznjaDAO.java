package DatabaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import dao.VozDAO;
import dao.VoznjaDAO;
import model.Grad;
import model.Karta;
import model.Stanica;
import model.TipVoza;
import model.Vagon;
import model.Voz;
import model.Voznja;

public class DatabaseVoznjaDAO implements VoznjaDAO {
	
	private  Connection conn;
	private final VozDAO vozDAO;

	public DatabaseVoznjaDAO(Connection conn, VozDAO vozDAO) {
		this.conn = conn;
		this.vozDAO = vozDAO;
	}
	@Override
	public Collection<Voznja> getAll() throws Exception {
		Collection<Voznja> voznje = new ArrayList<>();
		String sql = 
		"SELECT id, vozId, naziv, cenaKarte, datumVremePolaska, pocetnaStanica, "
		+ "krajnjaStanica FROM voznje";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			try(ResultSet rset = stmt.executeQuery()){
				while(rset.next()) {
					int kolona = 0;
					long id = rset.getLong(++kolona);
					long vozId = rset.getLong(++kolona);
					String naziv = rset.getString(++kolona);
					double cenaKarte = rset.getDouble(++kolona);
					LocalDateTime datumVremePolaska = rset.getTimestamp(++kolona).toLocalDateTime();
					String pocetnaStanica = rset.getString(++kolona);
					String krajnjaStanica = rset.getString(++kolona);
					
					VozDAO vozDAO = new DatabaseVozDAO(conn);
					Voz voz = vozDAO.get(vozId);
					
Voznja voznja = new Voznja(id, voz, naziv, cenaKarte, datumVremePolaska, pocetnaStanica, krajnjaStanica);
					voznje.add(voznja);
				}
			}
		}
		return voznje;
	}

	@Override
	public Collection<Voznja> getKarta(Karta karta) throws Exception {
		Collection<Voznja> voznje = new ArrayList<>();
		String sql = 
	"SELECT v.id, v.vozId, v.naziv, v.cenaKarte, v.datumVremePolaska, v.pocetnaStanica, v.krajnjaStanica "
	+ "FROM voznje v INNER JOIN karte k ON v.id = k.voznjaId WHERE k.id = ?";
//	"SELECT v.id, v.vozId, v.naziv, v.cenaKarte, v.datumVremePolaska, v.pocetnaStanica, v.krajnjaStanica "
//				+ "FROM voznje v WHERE voznjaId = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setLong(++param, karta.getId());
		//	stmt.setLong(++param, karta.getVoznja().getId());
			try(ResultSet rset = stmt.executeQuery()){
				while(rset.next()) {
					int kolona = 0;
					long vId = rset.getLong(++kolona);
					long vVozId = rset.getLong(++kolona);
					String vNaziv = rset.getString(++kolona);
					double vCenaKarte = rset.getDouble(++kolona);
					LocalDateTime vDatumVremePolaska = rset.getTimestamp(++kolona).toLocalDateTime();
					String vPocetnaStanica = rset.getString(++kolona);
					String vKrajnjaStanica = rset.getString(++kolona);
					
					
					Voz voz = vozDAO.get(vVozId);
					
					Voznja voznja = new Voznja(vId, voz, vNaziv, vCenaKarte, vDatumVremePolaska, vPocetnaStanica, vKrajnjaStanica);
					voznje.add(voznja);
				}
			}
		}	
		return voznje;
	}
	
	@Override
	public Voznja get(long id) throws Exception {
		Voznja voznja = null;
		String sql = 
				//vozId		AKO ZELIM DA POVEZEM
		"SELECT naziv, cenaKarte, datumVremePolaska, pocetnaStanica, krajnjaStanica FROM voznje WHERE id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setLong(++param, id);
			try(ResultSet rset = stmt.executeQuery()){
				if(rset.next()) {
					int kolona = 0;
					String naziv = rset.getString(++kolona);
					double cenaKarte = rset.getDouble(++kolona);
					LocalDateTime datumVremePolaska = rset.getTimestamp(++kolona).toLocalDateTime();
					String pocetnaStanica = rset.getString(++kolona);
					String krajnjaStanica = rset.getString(++kolona);
//					long vozId = rset.getLong(++kolona);
//					VozDAO vozDAO = new DatabaseVozDAO(conn);
//					Voz voz = vozDAO.get(vozId);
					if (id > 0) {
						
						voznja = new Voznja(id, null, naziv, cenaKarte, datumVremePolaska, pocetnaStanica, krajnjaStanica);
					}
					
				}
			}
		}
		return voznja;
	}

	@Override
	public Collection<Voznja> prikazVoznjeIzmedjuDvaGrada(Grad polznaTacka, Grad odrediste) throws Exception {
		Collection<Voznja> voznje = new ArrayList<>();
		String sql = 
	"SELECT id, naziv, cenaKarte, datumVremePolaska FROM voznje "
	+ "WHERE pocetnaStanica = ? AND krajnjaStanica = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setString(++param, polznaTacka.getNazivGrada());
			stmt.setString(++param, odrediste.getNazivGrada());
			try(ResultSet rset = stmt.executeQuery()){
				while(rset.next()) {
					int kolona = 0;
					long id = rset.getLong(++kolona);
					String naziv = rset.getString(++kolona);
					double cenaKarte = rset.getDouble(++kolona);
					LocalDateTime datumVremePolaska = rset.getTimestamp(++kolona).toLocalDateTime();
					
					Voznja voznja = new Voznja(id, null, naziv, cenaKarte, datumVremePolaska, polznaTacka.getNazivGrada(), odrediste.getNazivGrada());
							voznje.add(voznja);
				
				}
			}
		}
		return voznje;
	}

	@Override
	public Collection<Voznja> prikazVoznjeIzmedjuDveStanice(Stanica pocetnaStanica, Stanica krajnjaStanica)
			throws Exception {
		Collection<Voznja> voznje = new ArrayList<>();
		String sql = 
		"SELECT id, naziv, cenaKarte, datumVremePolaska FROM voznje "
		+ "WHERE pocetnaStanica = ? AND krajnjaStanica = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setString(++param, pocetnaStanica.getNazivStanice());
			stmt.setString(++param, krajnjaStanica.getNazivStanice());
			try(ResultSet rset = stmt.executeQuery()){
				while(rset.next()) {
					int kolona = 0;
					long id = rset.getLong(++kolona);
					String naziv = rset.getString(++kolona);
					double cenaKarte = rset.getDouble(++kolona);
					LocalDateTime datumVremePolaska = rset.getTimestamp(++kolona).toLocalDateTime();
					
Voznja voznja = new Voznja(id, null, naziv, cenaKarte, datumVremePolaska, pocetnaStanica.getNazivStanice(), krajnjaStanica.getNazivStanice());
		voznje.add(voznja);
				}
			}
		}		
		return voznje;
	}

	@Override
	public Collection<Voznja> prikazSvihVozniZaVoz(Voz voz) throws Exception {
	//	Map<Long, Voznja> voznje = new LinkedHashMap<>();
		Collection<Voznja> voznje = new ArrayList<>();
	String sql = 	
"SELECT v.id, v.naziv, v.cenaKarte, v.datumVremePolaska, v.pocetnaStanica, v.krajnjaStanica FROM voznje v "
+ "WHERE vozId = ?";
	try(PreparedStatement stmt = conn.prepareStatement(sql)){
		int param = 0;
		stmt.setLong(++param, voz.getId());
		try(ResultSet rset = stmt.executeQuery()){
			while(rset.next()) {
				int kolona = 0;
				long vId = rset.getLong(++kolona);
				String vNaziv = rset.getString(++kolona);
				double vCenaKarte = rset.getDouble(++kolona);
				LocalDateTime vDatumVremePolaska = rset.getTimestamp(++kolona).toLocalDateTime();
				String vPocetnaStanica = rset.getString(++kolona);
				String vKrajnjaStanica = rset.getString(++kolona);
				
//				Voznja voznja = voznje.get(vId);
//				if (voznja == null) {
//					voznja = new Voznja(vId, voz, vNaziv, vCenaKarte, vDatumVremePolaska, vPocetnaStanica, vKrajnjaStanica);
//					voznje.put(voznja.getId(), voznja);
	//		}
					
				Voznja voznja = new Voznja(vId, voz, vNaziv, vCenaKarte, vDatumVremePolaska, vPocetnaStanica, vKrajnjaStanica);
				voznje.add(voznja);
			}
			
		}
	}
		return voznje;
	}
	@Override
	public Collection<Voznja> getPrikazPolazakaZaStanicu(LocalDateTime polazak, Stanica stanica) throws Exception {
		Collection<Voznja> voznje = new ArrayList<>();
		String sql = 
	"SELECT v.id, v.vozId, v.naziv, v.cenaKarte, v.krajnjaStanica, voz.id, "
	+ "voz.nazivVoza, voz.tipVoza, voz.kapacitetVoza  FROM voznje v INNER JOIN vozovi voz "
	+ "ON v.vozId = voz.id "
	+ "WHERE v.pocetnaStanica = ? AND v.datumVremePolaska >= ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setString(++param, stanica.getNazivStanice());
			stmt.setTimestamp(++param, Timestamp.valueOf(polazak));
			try(ResultSet rset = stmt.executeQuery()){
				while(rset.next()) {
					int kolona = 0;
					long vId = rset.getLong(++kolona);
					long vVozId = rset.getLong(++kolona);
					String vNaziv = rset.getString(++kolona);
					double vCenaKarte = rset.getDouble(++kolona);
					String vKrajnjaStanica = rset.getString(++kolona);
					
					
					long vozId = rset.getLong(++kolona);
					String vozNazivVoza = rset.getString(++kolona);
					TipVoza vozTipVoza = TipVoza.valueOf(rset.getString(++kolona));
					int vozKapacitetVoza = rset.getInt(++kolona);
					
					
					
			Voz voz = new Voz(vozId, vozNazivVoza, null, vozTipVoza, vozKapacitetVoza);
//					VozDAO vozDAO = new DatabaseVozDAO(conn);
//					Voz voz = vozDAO.get(vVozId);
					Voznja voznja = new Voznja(vId, voz, vNaziv, vCenaKarte, polazak, stanica.getNazivStanice(), vKrajnjaStanica);
				voznje.add(voznja);
				}
			}
		}
		return voznje;
	}
//Voznja(long id, Voz voz, String naziv, double cenaKarte, LocalDateTime datumVremePolaska,
	//	Map<Razred, Integer> brSlobodnihMestaPoRazredu, String pocetnaStanica, String krajnjaStanica,
//	List<Stanica> spisakStanica) 
//Voz(long id, String nazivVoza, List<Vagon> vagoni, TipVoza tipVoza, int kapacitetVoza)
	@Override
	public Collection<Voznja> getPrikazDolazakaZaStanicu(LocalDateTime dolazak, Stanica stanica) throws Exception {
		Collection<Voznja> voznje = new ArrayList<>();
		String sql = 
	"SELECT v.id, v.vozId, v.naziv, v.cenaKarte, v.pocetnaStanica, voz.id, voz.nazivVoza, "
	+ "voz.TipVoza, voz.kapacitetVoza FROM voznje v INNER JOIN vozovi voz ON v.vozId = voz.id "
	+ "WHERE v.datumVremePolaska <= ? AND v.krajnjaStanica = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setTimestamp(++param, Timestamp.valueOf(dolazak));
			stmt.setString(++param, stanica.getNazivStanice());
			try(ResultSet rset = stmt.executeQuery()){
				while(rset.next()) {
					int kolona = 0;
					long vId = rset.getLong(++kolona);
					long vVozId = rset.getLong(++kolona);
					String vNaziv = rset.getString(++kolona);
					double vCenaKarte = rset.getDouble(++kolona);
					String vPocetnaStanica = rset.getString(++kolona);
					
					long vozId = rset.getLong(++kolona);
					String vozNazivVoza = rset.getString(++kolona);
					TipVoza vozTipVoza = TipVoza.valueOf(rset.getString(++kolona));
					int vozKapacitetVoza = rset.getInt(++kolona);
					
					Voz voz = new Voz(vozId, vozNazivVoza, null, vozTipVoza, vozKapacitetVoza);
Voznja voznja = new Voznja(vId, voz, vNaziv, vCenaKarte, dolazak, vPocetnaStanica, stanica.getNazivStanice());
voznje.add(voznja);
				}
			}
		}
		
		
		return voznje;
	}

}
