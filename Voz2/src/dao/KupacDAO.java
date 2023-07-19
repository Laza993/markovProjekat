package dao;

import java.util.Collection;

import model.Karta;
import model.Kupac;
import model.Voznja;

public interface KupacDAO {
	
	public Collection<Karta> getPrikazIstorijeKarata(Kupac kupac) throws Exception;
	public Collection<Voznja> getPrikazPredstojecihVoznji(Kupac kupac) throws Exception;
	Kupac get(long id) throws Exception;

}
