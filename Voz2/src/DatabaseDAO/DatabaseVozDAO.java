package DatabaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import dao.KartaDAO;
import dao.VozDAO;
import model.Karta;
import model.Razred;
import model.TipVoza;
import model.Vagon;
import model.Voz;

public class DatabaseVozDAO implements VozDAO {
	
	private final Connection conn;
	

	public DatabaseVozDAO(Connection conn) {
		this.conn = conn;
		
	}
	@Override
	public Voz get(long id) throws Exception {
		Voz voz = null;
		
		String sql = "SELECT v.nazivVoza, v.tipVoza, v.kapacitetVoza FROM vozovi v WHERE id = ?";
			
		
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setLong(++param, id);
			try(ResultSet rset = stmt.executeQuery()){
				if (rset.next()) {
					int kolone = 0;
					String vNazivVoza = rset.getString(++kolone);
					TipVoza vTipVoza = TipVoza.valueOf(rset.getString(++kolone));
					int vKapacitetVoza = rset.getInt(++kolone);
					
					if (voz == null) {
						voz = new Voz(id, vNazivVoza, null, vTipVoza, vKapacitetVoza);
					}		
				}
			}
		}
		return voz;
	}

// public Voz(long id, String nazivVoza, List<Vagon> vagoni, TipVoza tipVoza, int kapacitetVoza)
	//public Vagon(long id, int brojVagona, Voz voz, Razred razred, int brojSedista) 
	@Override
	public Collection<Voz> getAll() throws Exception {
		Map<Long, Voz> vozovi = new LinkedHashMap<>();
		
		String sql = "SELECT v.id, v.nazivVoza, v.tipVoza, v.kapacitetVoza, "
				+ "g.id, g.brojVagona, g.razred, g.brojSedista FROM vozovi v "
				+ "INNER JOIN vagoni g ON v.id = g.vozId";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			try(ResultSet rset = stmt.executeQuery()){
				while(rset.next()) {
					int kolone = 0;
					long vId = rset.getLong(++kolone);
					String vNazivVoza = rset.getString(++kolone);
					TipVoza vTipVoza = TipVoza.valueOf(rset.getString(++kolone));
					int vKapacitetVoza = rset.getInt(++kolone);
					
					if (vKapacitetVoza <= 0 || vNazivVoza == null || vNazivVoza.isEmpty()) {
						return vozovi.values();
					}
					
					Voz voz = vozovi.get(vId); //dobavljam id smestam u voz
					if (voz == null) { // proveravam da li je id null ako jeste novi se kreira
						voz = new Voz(vId, vNazivVoza, null, vTipVoza, vKapacitetVoza);
						vozovi.put(voz.getId(), voz); // kad se kreira smesta se
					}
					long gId = rset.getLong(++kolone);
					if (gId != 0) {
						int gBrojVagona = rset.getInt(++kolone);
						Razred gRazred = Razred.valueOf(rset.getString(++kolone));
						int gBrojSedista = rset.getInt(++kolone);
						
						Vagon vagon = new Vagon(gId, gBrojVagona, voz, gRazred, gBrojSedista);
						voz.addVagon(vagon);
					}
				}
			}
		}	
		return vozovi.values();
	}

	@Override
	public Collection<Vagon> addDodavanjeVagonaVozu(Voz voz, Vagon addVagon) throws Exception {
		String sql = "INSERT INTO vagoni(brojVagona, vozId, razred, brojSedista) VALUES (?, ?, ?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int param = 0;
			stmt.setInt(++param, addVagon.getBrojSedista());
			stmt.setLong(++param, voz.getId());
			stmt.setString(++param, addVagon.getRazred().name());
			stmt.setInt(++param, addVagon.getBrojSedista());
			stmt.executeUpdate();
		}
		// Cime bi se osvezili podaci o broju mesta na vozu i na voznji. OVO FALI OVDE
		// NE ZNAM METODU 
		return voz.getVagoni();
	}

}
