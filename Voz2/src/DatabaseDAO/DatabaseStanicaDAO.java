package DatabaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import dao.StanicaDAO;
import model.Grad;
import model.Stanica;
import model.Voznja;

public class DatabaseStanicaDAO implements StanicaDAO {
	
	private final Connection conn;
	

	public DatabaseStanicaDAO(Connection conn) {

		this.conn = conn;
	}

//Grad(long id, String nazivGrada, Set<Stanica> stanice)
//Stanica(long id, String nazivStanice, LocalTime radnoVremeStanice, List<Voznja> polasci,
//	List<Voznja> dolasci)
	@Override
	public Collection<Stanica> getPrikazStanicaZaGrad(Grad grad) throws Exception {
		Collection<Stanica> stanice = new ArrayList<>();
		String sql = 
	"SELECT id, nazivStanice, radnoVremeStanice FROM stanice WHERE nazivGrada = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setString(++param, grad.getNazivGrada());
			try(ResultSet rset = stmt.executeQuery()){
				while(rset.next()) {
					int kolona = 0;
					long id = rset.getLong(++kolona);
					String nazivStanice = rset.getString(++kolona);
					LocalTime radnoVremeStanice = rset.getTime(++kolona).toLocalTime();
					List<Voznja> polasci = new ArrayList<>();
					List<Voznja> dolasci = new ArrayList<>();
					Stanica stanica = new Stanica(id, nazivStanice, radnoVremeStanice, polasci, dolasci);
					stanice.add(stanica);
				}
			}
		}
		return stanice;
	}

}
