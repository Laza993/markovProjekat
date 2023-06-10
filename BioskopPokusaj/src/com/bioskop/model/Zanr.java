package com.bioskop.model;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Zanr {

	// nepromenljiva polja
	private final long id;

	// promenljiva polja
	private String naziv;
	
	final Set<Film> filmovi = new LinkedHashSet<>();

	public Zanr(long id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}

	public Zanr(String naziv) {
		this(0, naziv);
	}

	public Zanr() {
		this(0, "");
	}
	
	public static int comparNaziv(Zanr naziv1, Zanr naziv2) {
		return naziv1.naziv.compareTo(naziv2.naziv);
	}
	
	public Collection<Film> getFilmove(){
		return Collections.unmodifiableCollection(this.filmovi);
	}
	
	public void addFilm(Film film) {
		this.filmovi.add(film);
		film.zanrovi.add(this);
	}
	
	public void removeFilm(Film film) {
		film.zanrovi.remove(this);
		this.filmovi.remove(film);
	}
	
	public void addAllFilmove(Collection<Film> filmovi) {
		this.filmovi.addAll(filmovi);
		
		for (Film film : filmovi) {
			film.zanrovi.add(this);
		}
	}
	
	public void removeAllFilmove() {
		for (Film film : filmovi) {
			film.zanrovi.remove(this);
			this.filmovi.clear();
		}
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public long getId() {
		return id;
	}

	public Set<Film> getFilmovi() {
		return filmovi;
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
		Zanr other = (Zanr) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Zanr [id=" + id + ", naziv=" + naziv + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
