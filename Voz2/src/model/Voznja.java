package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import Voz_Util.Konzola;

public class Voznja {

	private long id;
	Voz voz;
	private String naziv;
	private double cenaKarte;
	private LocalDateTime datumVremePolaska;
	private Map<Razred, Integer> brSlobodnihMestaPoRazredu = new HashMap<>();
	private String pocetnaStanica;
	private String krajnjaStanica;

	private List<Stanica> spisakStanica = new ArrayList<>();
	private final Collection<Karta> karte = new ArrayList<>();

	public Voznja(long id, Voz voz, String naziv, double cenaKarte, LocalDateTime datumVremePolaska,
			Map<Razred, Integer> brSlobodnihMestaPoRazredu, String pocetnaStanica, String krajnjaStanica,
			List<Stanica> spisakStanica) {
		this.id = id;
		this.voz = voz;
		this.naziv = naziv;
		this.cenaKarte = cenaKarte;
		this.datumVremePolaska = datumVremePolaska;
		this.brSlobodnihMestaPoRazredu = brSlobodnihMestaPoRazredu;
		this.pocetnaStanica = pocetnaStanica;
		this.krajnjaStanica = krajnjaStanica;
		this.spisakStanica = spisakStanica;
		
		brSlobodnihMestaPoRazredu.put(Razred.PRVI, 80);
		brSlobodnihMestaPoRazredu.put(Razred.DRUGI, 120);
		brSlobodnihMestaPoRazredu.put(Razred.TRECI, 200);
	}
	

	public Voznja(long id, Voz voz, String naziv, double cenaKarte, LocalDateTime datumVremePolaska,
			String pocetnaStanica, String krajnjaStanica) {
		super();
		this.id = id;
		this.voz = voz;
		this.naziv = naziv;
		this.cenaKarte = cenaKarte;
		this.datumVremePolaska = datumVremePolaska;
		this.pocetnaStanica = pocetnaStanica;
		this.krajnjaStanica = krajnjaStanica;
	}



	public Voznja() {
	}

	public Collection<Stanica> getspisakStanica() {
		return Collections.unmodifiableCollection(this.spisakStanica);
	}

	public void addSpisakStanice(Stanica stanica) {
		this.spisakStanica.add(stanica);
	}

	public void removeSpisakStanice(Stanica stanica) { // PRIMER
		this.spisakStanica.remove(stanica);
	}

	public void addAllSpisakStanica(Collection<Stanica> spisakStanica) {
		this.spisakStanica.addAll(spisakStanica);
	}

	public void removeAllSpisakStanica() {
		this.spisakStanica.clear();
	}

	public Collection<Karta> getKarte() {
		return Collections.unmodifiableCollection(this.karte);
	}

	public void addKartu(Karta karta) {
		this.karte.add(karta);
		karta.voznja = this;

	}

	public void removeKartu(Karta karta) { // PRIMER
		karta.voznja = null; // karta.setVoz(null);
		this.karte.remove(karta); // this.karte.remove(karta);
	}

	public void addAllKarte(Collection<Karta> karte) {
		this.karte.addAll(karte);
		for (Karta karta : karte) {
			karta.voznja = this;
		}
	}

	public void removeAllKarte() {
		for (Karta karta : karte) {
			karta.voznja = null;
		}
		this.karte.clear();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Voz getVoz() {
		return voz;
	}

	public void setVoz(Voz voz) {
		this.voz = voz;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getCenaKarte() {
		return cenaKarte;
	}

	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}

	public List<Stanica> getSpisakStanica() {
		return spisakStanica;
	}

	public void setSpisakStanica(List<Stanica> spisakStanica) {
		this.spisakStanica = spisakStanica;
	}

	public LocalDateTime getDatumVremePolaska() {
		return datumVremePolaska;
	}

	public void setDatumVremePolaska(LocalDateTime datumVremePolaska) {
		this.datumVremePolaska = datumVremePolaska;
	}

	public Map<Razred, Integer> getBrSlobodnihMestaPoRazredu() {
		return brSlobodnihMestaPoRazredu;
	}

	public void setBrSlobodnihMestaPoRazredu(Map<Razred, Integer> brSlobodnihMestaPoRazredu) {
		this.brSlobodnihMestaPoRazredu = brSlobodnihMestaPoRazredu;
	}

	public String getPocetnaStanica() {
		return pocetnaStanica;
	}

	public void setPocetnaStanica(String pocetnaStanica) {
		this.pocetnaStanica = pocetnaStanica;
	}

	public String getKrajnjaStanica() {
		return krajnjaStanica;
	}

	public void setKrajnjaStanica(String krajnjaStanica) {
		this.krajnjaStanica = krajnjaStanica;
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
		Voznja other = (Voznja) obj;
		return id == other.id;
	}

	@Override
	public String toString() {

		String temp = "";
		temp += "------------------------ VOZNJA ------------------------\n";
		temp += "ID " + this.id + "\n";
		temp += "Voz: " + this.voz.getNazivVoza() + "\n";
		temp += "Naziv: " + this.naziv + "\n";
		temp += "Datum i vreme polaska: " + Konzola.formatiraj(datumVremePolaska) + "\n";
		temp += "Br. Slobodnih mesta po Razredu: " + this.brSlobodnihMestaPoRazredu + "\n";
		temp += "Pocetna stranica: " + this.pocetnaStanica + "\n";
		temp += "Krajnja Stanica: " + this.krajnjaStanica + "\n";
		temp += "Spisak stanica: " + this.spisakStanica + "\n";
		// temp += "Broj slobodnih mesta" + this.brSlobodnihMesta + "\n";
		return temp.trim();
	}

//	Voz voz1 = new Voz();
//	int brSlobodnihMesta = voz1.brojSlobodnihMesta();

//		return "Voznja [id=" + id + ", voz=" + voz + ", naziv=" + naziv + ", datumVremePolaska=" + datumVremePolaska
//				+ ", brSlobodnihMestaPoRazredu=" + brSlobodnihMestaPoRazredu + ", pocetnaStanica=" + pocetnaStanica
//				+ ", krajnjaStanica=" + krajnjaStanica + ", spisakStanica=" + spisakStanica + "]";

	public boolean isPopunjen() {
		return brSlobodnihMestaPoRazredu.size() > karte.size();
	}
	
	public boolean isPosao() {
		return LocalDateTime.now().isAfter(datumVremePolaska);
	}
}
