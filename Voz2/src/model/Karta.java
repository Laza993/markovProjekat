package model;

import java.time.LocalDateTime;
import java.util.Objects;

import Voz_Util.Konzola;

public class Karta {

	private long id;
	private LocalDateTime datumVremeProdaje;
	private Kupac kupac;
	private Razred razred;
	Voz voz;
	Voznja voznja;

	public Karta(long id, LocalDateTime datumVremeProdaje, Kupac kupac, Razred razred, Voz voz, Voznja voznja) {

		this.id = id;
		this.datumVremeProdaje = datumVremeProdaje;
		this.kupac = kupac;
		this.razred = razred;
		this.voz = voz;
		this.voznja = voznja;
	}
	
	

	public Karta(LocalDateTime datumVremeProdaje, Kupac kupac, Razred razred, Voz voz, Voznja voznja) {
		super();
		this.datumVremeProdaje = datumVremeProdaje;
		this.kupac = kupac;
		this.razred = razred;
		this.voz = voz;
		this.voznja = voznja;
	}



	public Karta() {
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getDatumVremeProdaje() {
		return datumVremeProdaje;
	}

	public void setDatumVremeProdaje(LocalDateTime datumVremeProdaje) {
		this.datumVremeProdaje = datumVremeProdaje;
	}

	public Kupac getKupac() {
		return kupac;
	}

	public void setKupac(Kupac kupac) {
		this.kupac = kupac;
	}

	public Razred getRazred() {
		return razred;
	}

	public void setRazred(Razred razred) {
		this.razred = razred;
	}

	public Voz getVoz() {
		return voz;
	}

	public void setVoz(Voz voz) {
		this.voz = voz;
	}

	public Voznja getVoznja() {
		return voznja;
	}

	public void setVoznja(Voznja voznja) {
		this.voznja = voznja;
		voznja.addKartu(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Karta other = (Karta) obj;
		return id == other.id;
	}

	@Override
	public String toString() {

		String temp = "";
		temp += "------------------------ KARTA ------------------------\n";
		temp += "ID " + this.id + "\n";
		temp += "Datum i vreme prodaje " + Konzola.formatiraj(datumVremeProdaje);
		temp += "Kupac " + this.kupac + "\n";
		temp += "Razred " + this.razred + "\n";
		temp += "Voz " + this.voz + "\n";
		temp += "Voznja " + this.voznja + "\n";
		return temp.trim();

//		return "Karta [id=" + id + ", datumVremeProdaje=" + datumVremeProdaje + ", kupac=" + kupac + ", Razred="
//				+ Razred + ", voz=" + voz + ", voznje=" + voznje + "]";
	}

//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public LocalDateTime getDatumVremeProdaje() {
//		return datumVremeProdaje;
//	}
//
//	public void setDatumVremeProdaje(LocalDateTime datumVremeProdaje) {
//		this.datumVremeProdaje = datumVremeProdaje;
//	}
//
//	public String getKupac() {
//		return kupac;
//	}
//
//	public void setKupac(String kupac) {
//		this.kupac = kupac;
//	}
//
//	public int getRazred() {
//		return razred;
//	}
//
//	public void setRazred(int razred) {
//		this.razred = razred;
//	}
//
//	public Voz getVoz() {
//		return voz;
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(id);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Karta other = (Karta) obj;
//		return id == other.id;
//	}
//
//	@Override
//	public String toString() {
//		return "Karta [id=" + id + ", datumVremeProdaje=" + Konzola.formatiraj(datumVremeProdaje) + ", kupac=" + kupac + ", razred="
//				+ razred + ", voz=" + voz.getNaziv() + "]";
//	}

//	public double cenaKarte() {
//		if (razred == 1) {
//			return voz.getCenaKarte();
//		} else if (razred == 2) {
//			return voz.getCenaKarte() * 0.85;
//		}
//		return Double.MAX_VALUE;
//	}

}
