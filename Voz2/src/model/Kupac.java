package model;

import java.time.LocalDate;
import java.util.Objects;

import Voz_Util.Konzola;

public class Kupac {
	
	private long id;
	private String ime;
	private String prezime;
	private LocalDate datumRodjenja;
	private long JMBG;
	private Kategorija kategorija;
	
	public Kupac(long id, String ime, String prezime, LocalDate datumRodjenja, long jMBG, Kategorija kategorija) {
		
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.datumRodjenja = datumRodjenja;
		JMBG = jMBG;
		this.kategorija = kategorija;
	}
	public Kupac(String ime, String prezime, LocalDate datumRodjenja, long jMBG, Kategorija kategorija) {
		
		this.id = 0;
		this.ime = ime;
		this.prezime = prezime;
		this.datumRodjenja = datumRodjenja;
		JMBG = jMBG;
		this.kategorija = kategorija;
	}
	
	public Kupac() {}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public long getJMBG() {
		return JMBG;
	}
	public void setJMBG(long jMBG) {
		JMBG = jMBG;
	}
	public Kategorija getKategorija() {
		return kategorija;
	}
	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
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
		Kupac other = (Kupac) obj;
		return id == other.id;
	}
	@Override
	public String toString() {
		String temp = "";
		temp += "------------------------ KUPAC ------------------------\n";
		temp += "ID " + this.id + "\n";
		temp += "Ime " + this.ime + "\n";
		temp += "Prezime " + this.prezime + "\n";
		temp += "Datum rodjenja " + Konzola.formatiraj(datumRodjenja) + "\n";
		temp += "JMBG " + this.JMBG + "\n";
		temp += "Kategorija " + this.kategorija + "\n";
		return temp.trim();
	}
		
		
		
	
//		return "Kupac [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", datumRodjenja=" + datumRodjenja
//				+ ", JMBG=" + JMBG + ", kategorija=" + kategorija + "]";
	
	
	
	
	
	
	
	
	
	

}
