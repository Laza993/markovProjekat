package dao;

import java.time.LocalDateTime;
import java.util.Collection;

import model.Grad;
import model.Karta;
import model.Stanica;
import model.Voz;
import model.Voznja;

public interface VoznjaDAO {
	
	public Collection<Voznja> getAll() throws Exception;
	public Collection<Voznja> getKarta(Karta karta) throws Exception;
	public Voznja get(long id) throws Exception;
	public Collection<Voznja> prikazVoznjeIzmedjuDvaGrada(Grad polznaTacka, Grad odrediste) throws Exception;
	public Collection<Voznja> prikazVoznjeIzmedjuDveStanice(Stanica pocetnaStanica, Stanica krajnjaStanica) throws Exception;
	public Collection<Voznja> prikazSvihVozniZaVoz(Voz voz) throws Exception;
	public Collection<Voznja> getPrikazPolazakaZaStanicu(LocalDateTime polazak, Stanica stanica) throws Exception;
	public Collection<Voznja> getPrikazDolazakaZaStanicu(LocalDateTime dolazak, Stanica stanica) throws Exception;

}
