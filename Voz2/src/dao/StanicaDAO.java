package dao;

import java.time.LocalDateTime;
import java.util.Collection;

import model.Grad;
import model.Stanica;
import model.Voznja;

public interface StanicaDAO {

	public Collection<Stanica> getPrikazStanicaZaGrad(Grad grad) throws Exception;
	public Collection<Voznja> getPrikazPolazagaZaStanicu(LocalDateTime polazak, Stanica stanica) throws Exception;
	public Collection<Voznja> getPrikazDolazakaZaStanicu(LocalDateTime dolazak, Stanica stanica) throws Exception;
	
	
}
