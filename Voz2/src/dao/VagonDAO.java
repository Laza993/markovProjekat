package dao;

import java.util.Collection;

import model.Vagon;
import model.Voz;

public interface VagonDAO {
	
	
	
	
	public Collection<Vagon> getPrikazSvihVagonaZaVoz(Voz voz) throws Exception;

}
