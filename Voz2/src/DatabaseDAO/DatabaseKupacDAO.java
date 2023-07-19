package DatabaseDAO;

import java.sql.Connection;
import java.util.Collection;

import dao.KupacDAO;
import model.Karta;
import model.Kupac;
import model.Voznja;

public class DatabaseKupacDAO implements KupacDAO {
	
	private final Connection conn;
	

	public DatabaseKupacDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Kupac get(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Collection<Karta> getPrikazIstorijeKarata(Kupac kupac) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Voznja> getPrikazPredstojecihVoznji(Kupac kupac) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
