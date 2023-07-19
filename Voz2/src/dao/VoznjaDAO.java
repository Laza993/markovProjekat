package dao;

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
	public Collection<Stanica> prikazVoznjeIzmedjuDveStanice(String pocetnaStanica, String krajnjaStanica) throws Exception;
	public Collection<Voznja> prikazSvihVozniZaVoz(Voz voz) throws Exception;

}
