package dao;

import java.util.Collection;

import model.Vagon;
import model.Voz;

public interface VozDAO {

	
	public Voz get(long id) throws Exception;
	public Collection<Voz> getAll() throws Exception;
	public Collection<Vagon> addDodavanjeVagonaVozu(Voz voz, Vagon addVagon) throws Exception;
	
	
	
}
