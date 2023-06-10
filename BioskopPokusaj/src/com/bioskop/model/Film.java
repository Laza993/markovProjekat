package com.bioskop.model;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Film {
	
	private final long id;
	private String naziv;
	private int trajanje;
	
	final Set<Zanr> zanrovi = new LinkedHashSet<>(); // sprečava duplikate
	final Set<Projekcija> projekcije = new LinkedHashSet<>(); // sprečava duplikate
	
	public Film(long id, String naziv, int trajanje) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.trajanje = trajanje;
	}
	
	public Film(String naziv, int trajanje) {
		this(0, naziv, trajanje);
	}
	
	public Film() {
		this(0, "", 0);
	}
	
	public static int comperNaziv(Film naziv1, Film naziv2) {
		return naziv1.naziv.compareTo(naziv2.naziv);
	}

	public static int comperTrajanje(Film film1, Film film2) {
		return Integer.compare(film1.trajanje, film2.trajanje);
	}

	public Collection<Zanr> getZanrovi(){
		return Collections.unmodifiableCollection(this.zanrovi);
	}
	
	public void addZanr(Zanr zanr) {
		this.zanrovi.add(zanr);
		zanr.filmovi.add(this);
	}
	
	public void removeZanr(Zanr zanr) {
		zanr.filmovi.remove(this);
		this.zanrovi.remove(zanr);
	}
	
	public void addAllZanrove(Collection<Zanr> zanrovi) {
		this.zanrovi.addAll(zanrovi);
		
		for (Zanr zanr : zanrovi) {
			zanr.filmovi.add(this);
		}
	}
	
	public void removeAllZanrove() {
		for (Zanr zanr : zanrovi) {
			zanr.filmovi.remove(this);
			this.zanrovi.clear();
		}
	}
	
	public Collection<Projekcija> getProjekcije(){
		return Collections.unmodifiableCollection(this.projekcije);
	}
	
	public void addProjekciju(Projekcija projekcija) {
		this.projekcije.add(projekcija);
		
		if (projekcija != null) {
			projekcija.film.removeProjekciju(projekcija);
			projekcija.film = this;
		}
	}
	
	public void removeProjekciju(Projekcija projekcija) {
		projekcija.film = null;
		this.projekcije.remove(projekcija);
	}
	
	public void addAllProjekcije(Collection<Projekcija> projekcije) {
		this.projekcije.addAll(projekcije);
		
		for (Projekcija projekcija : projekcije) {
			if (projekcija.film != null) {
				projekcija.film.removeProjekciju(projekcija);
				projekcija.film = this;
			}
		}
	}
	
	public void removeAllProjekcije() {
		
		for (Projekcija projekcija : projekcije) {
			projekcija.film = null;
			this.projekcije.clear();
		}
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}

	public long getId() {
		return id;
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
		Film other = (Film) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", naziv=" + naziv + ", trajanje=" + trajanje + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
