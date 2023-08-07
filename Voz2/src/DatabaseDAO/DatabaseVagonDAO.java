package DatabaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import dao.VagonDAO;
import dao.VozDAO;
import model.Razred;
import model.Vagon;
import model.Voz;

public class DatabaseVagonDAO implements VagonDAO {

	private final Connection conn;
	private VozDAO vozDAO;

	public DatabaseVagonDAO(Connection conn, VozDAO vozDAO) {
		super();
		this.conn = conn;
		this.vozDAO = vozDAO;
	}

//	public Voz(long id, String nazivVoza, List<Vagon> vagoni, TipVoza tipVoza, int kapacitetVoza)
	// public Vagon(long id, int brojVagona, Voz voz, Razred razred, int
	// brojSedista)


	@Override
	public Collection<Vagon> getVagoneZaVoz(Voz voz) throws Exception{
//		Map<Long, Vagon> vagoni = new LinkedHashMap<>();
		List<Vagon> vagoni = new ArrayList();
		
		List<Long> listaIdevaVagona = new ArrayList();
		
		String sql = "SELECT id FROM vagoni WHERE vozId = ?";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {		
			int param = 0;
			stmt.setLong(++param, voz.getId());
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					
					Long id = rset.getLong(1);
					
					listaIdevaVagona.add(id);
				}
			}
		}
		
		for(Long id : listaIdevaVagona) {
			Vagon vagon = get(id);
			vagoni.add(vagon);
		}
		
		return vagoni;
	}
// Vagon(long id, int brojVagona, Voz voz, Razred razred, int brojSedista)
	@Override
	public Vagon get(long id) throws Exception {
		Vagon vagon = null;
		String sql="SELECT brojVagona, vozId, razred, brojSedista FROM vagoni WHERE id = ?";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setLong(++param, id);
			
			try(ResultSet rset = stmt.executeQuery()){
				
				if (rset.next()) {
					int kolona = 0;
					int brojVagona = rset.getInt(++kolona);
					long vozId = rset.getLong(++kolona);
					VozDAO vozDAO = new DatabaseVozDAO(conn);
					Voz voz = vozDAO.get(vozId);
					Razred razred = Razred.valueOf(rset.getString(++kolona));
					int brojSedista = rset.getInt(++kolona);
					
					vagon = new Vagon(id, brojVagona, voz, razred, brojSedista);
					
				}
			}
		}
		return vagon;
	}

}
