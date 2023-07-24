package dao;


import java.util.Collection;

import model.Grad;
import model.Stanica;


public interface StanicaDAO {

	public Collection<Stanica> getPrikazStanicaZaGrad(Grad grad) throws Exception;
	
	
	
}
