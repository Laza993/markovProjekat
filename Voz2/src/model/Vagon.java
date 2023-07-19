package model;

import java.util.Objects;


public class Vagon {
	
	private long id;
	private int brojVagona;
	Voz voz;
	private Razred razred;
	private int brojSedista;
	
	public Vagon(long id, int brojVagona, Voz voz, Razred razred, int brojSedista) {
		
		this.id = id;
		this.brojVagona = brojVagona;
		this.voz = voz;
		this.razred = razred;
		this.brojSedista = brojSedista;
	}
	public Vagon(int brojVagona, Voz voz, Razred razred, int brojSedista) {
		
		this.id = 0;
		this.brojVagona = brojVagona;
		this.voz = voz;
		this.razred = razred;
		this.brojSedista = brojSedista;
	}
	
	public Vagon() {}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getBrojVagona() {
		return brojVagona;
	}
	public void setBrojVagona(int brojVagona) {
		this.brojVagona = brojVagona;
	}
	public Voz getVoz() {
		return voz;
	}
	public void setVoz(Voz voz) {
		this.voz = voz;
		voz.addVagon(this);
	}
	public Razred getRazred() {
		return razred;
	}
	public void setRazred(Razred razred) {
		this.razred = razred;
	}
	public int getBrojSedista() {
		return brojSedista;
	}
	public void setBrojSedista(int brojSedista) {
		this.brojSedista = brojSedista;
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
		Vagon other = (Vagon) obj;
		return id == other.id;
	}
	@Override
	public String toString() {
		String temp = "";
		temp += "------------------------ VAGON ------------------------\n";
		temp += "ID " + this.id + "\n";
		temp += "Broj Vagona " + this.brojVagona + "\n";
		temp += "Voz " + this.voz + "\n";
		temp += "Razred " + this.razred +"\n";
		temp += "Broj sedista " + this.brojSedista + "\n";
	
		return temp.trim();
	}
	
	
//		return "Vagon [id=" + id + ", brojVagona=" + brojVagona + ", voz=" + voz + ", razred=" + razred
//				+ ", brojSedista=" + brojSedista + "]";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
