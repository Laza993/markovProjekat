package model;

import java.util.Objects;

public class RazredVoza {
	
	private long id;
	private Razred razred;
	private double cenaKarte;
	
	public RazredVoza(long id, Razred razred, double cenaKarte) {
		
		this.id = id;
		this.razred = razred;
		this.cenaKarte = cenaKarte;
	}
	
	public RazredVoza(Razred razred, double cenaKarte) {
		
		this.id = 0;
		this.razred = razred;
		this.cenaKarte = cenaKarte;
	}
	
	public RazredVoza(){}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Razred getRazred() {
		return razred;
	}

	public void setRazred(Razred razred) {
		this.razred = razred;
	}

	public double getCenaKarte() {
		return cenaKarte;
	}

	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
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
		RazredVoza other = (RazredVoza) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		String temp = "";
		temp += "------------------------ RAZRED VOZA ------------------------\n";
		temp += "ID " + this.id + "\n";
		temp += "Razred " + this.razred +"\n";
		temp += "Cena karte " + this.cenaKarte + "\n";
	
		return temp.trim();
	}
	
	//return "RazredVoza [id=" + id + ", razred=" + razred + ", cenaKarte=" + cenaKarte + "]";
	
	
	

}
