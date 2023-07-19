package dao;



import model.Karta;


public interface KartaDAO {
	
	public Karta get(long id) throws Exception;
	public void add(Karta karta) throws Exception;
	
	

}

//public Collection<Karta> find(Voz voz) throws Exception;