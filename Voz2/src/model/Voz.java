package model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;




public class Voz {

	private long id;

	private String nazivVoza;
	private List<Vagon> vagoni = new ArrayList<>();
	private TipVoza tipVoza;
	private int kapacitetVoza;
	
	public Voz(long id, String nazivVoza, List<Vagon> vagoni, TipVoza tipVoza, int kapacitetVoza) {
		this.id = id;
		this.nazivVoza = nazivVoza;
		this.vagoni = vagoni;
		this.tipVoza = tipVoza;
		this.kapacitetVoza = kapacitetVoza;
	}

	public Voz(String nazivVoza, List<Vagon> vagoni, TipVoza tipVoza, int kapacitetVoza) {
		this.id = 0;
		this.nazivVoza = nazivVoza;
		this.vagoni = vagoni;
		this.tipVoza = tipVoza;
		this.kapacitetVoza = kapacitetVoza;
	}
	
	public Voz() {}
	
	public Collection<Vagon> getVagoni() {
		return Collections.unmodifiableList(this.vagoni);
	}

	public void addVagon(Vagon vagon) {
		this.vagoni.add(vagon);
	}

	public void removeVagon(Vagon vagon) {
		this.vagoni.remove(vagon);
	}

	public void addAllVagone(Collection<Vagon> vagoni) {
		this.vagoni.addAll(vagoni);
	}

	public void removeAllVagone() {
		this.vagoni.clear();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNazivVoza() {
		return nazivVoza;
	}

	public void setNazivVoza(String nazivVoza) {
		this.nazivVoza = nazivVoza;
	}

	public TipVoza getTipVoza() {
		return tipVoza;
	}

	public void setTipVoza(TipVoza tipVoza) {
		this.tipVoza = tipVoza;
	}

	public int getKapacitetVoza() {
		return kapacitetVoza;
	}

	public void setKapacitetVoza(int kapacitetVoza) {
		this.kapacitetVoza = kapacitetVoza;
	}

	public void setVagoni(List<Vagon> vagoni) {
		this.vagoni = vagoni;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voz other = (Voz) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		String temp = "";
		temp += "\n------------------------ VOZ ------------------------\n";
		temp += "ID " + this.id + "\n";
		temp += "Naziv voza " + this.nazivVoza + "\n";
		temp += "Vagon " + this.vagoni + "\n";
		temp += "Tip voza " + this.tipVoza + "\n";
		temp += "Kapacitet voza " + this.kapacitetVoza + "\n";
		return temp.trim();
	}
	
//		return "Voz [id=" + id + ", nazivVoza=" + nazivVoza + ", vagoni=" + vagoni + ", tipVoza=" + tipVoza
//				+ ", kapacitetVoza=" + kapacitetVoza + "]";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public LocalDate getDatumPolaska() {
//	return datumVremePolaska.toLocalDate();
//}

//	public boolean isPopunjen() {
//		return brojMesta > karte.size();
//	}

//	public boolean isPosao() {
//		return LocalDateTime.now().isAfter(datumVremePolaska);
//	}

//	public int brojSlobodnihMesta() {
//	return this.brojMesta - karte.size();
//}
}
