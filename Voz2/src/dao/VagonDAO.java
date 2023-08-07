package dao;

import java.util.Collection;

import model.Vagon;
import model.Voz;

public interface VagonDAO {
	
	
	
	public Vagon get(long id) throws Exception;
//	public Collection<Vagon> getPrikazSvihVagonaZaVoz(Voz voz) throws Exception;
	public Collection<Vagon> getVagoneZaVoz(Voz voz) throws Exception;
//	public Collection<Vagon> getVagoneZaVoz(long vozId);
}
