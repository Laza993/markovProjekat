package DatabaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import dao.VozDAO;
import dao.VoznjaDAO;
import model.Grad;
import model.Karta;
import model.Stanica;
import model.Voz;
import model.Voznja;

public class DatabaseVoznjaDAO implements VoznjaDAO {
	
	private final Connection conn;
	

	public DatabaseVoznjaDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	@Override
	public Collection<Voznja> getAll() throws Exception {
		Collection<Voznja> voznje = new ArrayList<>();
		String sql = 
		"SELECT id, naziv, cenaKarte, datumVremePolaska, pocetnaStanica, "
		+ "krajnjaStanica FROM voznji";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			try(ResultSet rset = stmt.executeQuery()){
				while(rset.next()) {
					int kolona = 0;
					long id = rset.getLong(++kolona);
					String naziv = rset.getString(++kolona);
					double cenaKarte = rset.getDouble(++kolona);
					LocalDateTime datumVremePolaska = rset.getTimestamp(++kolona).toLocalDateTime();
					String pocetnaStanica = rset.getString(++kolona);
					String krajnjaStanica = rset.getString(++kolona);
					
Voznja voznja = new Voznja(id, null, naziv, cenaKarte, datumVremePolaska, pocetnaStanica, krajnjaStanica);
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
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setLong(++param, karta.getId());
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
					
					VozDAO vozDAO = new DatabaseVozDAO(conn);
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
					
					voznja = new Voznja(id, null, naziv, cenaKarte, datumVremePolaska, pocetnaStanica, krajnjaStanica);
					
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
	public Collection<Stanica> prikazVoznjeIzmedjuDveStanice(String pocetnaStanica, String krajnjaStanica)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

//Voznja(long id, Voz voz, String naziv, double cenaKarte, LocalDateTime datumVremePolaska,
	//	Map<Razred, Integer> brSlobodnihMestaPoRazredu, String pocetnaStanica, String krajnjaStanica,
//	List<Stanica> spisakStanica) 
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

}
