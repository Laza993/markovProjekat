package com.bioskop.model;

import java.time.LocalDateTime;
import java.util.Objects;



public class Projekcija {
	
	// nepromenljiva polja
		private final long id;

		// promenljiva polja
		private LocalDateTime datumVreme;
		private int sala;
		private String tip;
		private double cenaKarte;

		// reference; vidljive u ovom paketu (model) ali ne i spolja
		Film film;

		public Projekcija(long id, LocalDateTime datumIVreme, int sala, String tip, double cenaKarte, Film film) {
			super();
			this.id = id;
			this.datumVreme = datumIVreme;
			this.sala = sala;
			this.tip = tip;
			this.cenaKarte = cenaKarte;
			
			this.film = film;
			if (film != null) {
				film.projekcije.add(this);
			}
		}

		public Projekcija(LocalDateTime datumVreme, int sala, String tip, double cenaKarte, Film film) {
	this(0, datumVreme, sala, tip, cenaKarte, film);
		}
		
		public Projekcija() {
		this(0, LocalDateTime.now(), 0, "", Double.MAX_VALUE, null);
		}

		public static int compareDatumVreme(Projekcija dV1, Projekcija dV2) {
			return dV1.datumVreme.compareTo(dV2.datumVreme);
		}
		
		public static int compareSala(Projekcija sala1, Projekcija sala2) {
			return Integer.compare(sala1.sala, sala2.sala);
		}
		
		private static int tip(Projekcija tip1, Projekcija tip2) {
			return tip1.tip.compareTo(tip2.tip);
		}
		
		private static int cenaKarte(Projekcija cena1, Projekcija cena2) {
			return Double.compare(cena1.cenaKarte, cena2.cenaKarte);
		}
		
		private static int comperFilm(Projekcija projekcija1, Projekcija projekcija2) {
			return Film.comperNaziv(projekcija1.film, projekcija2.film);
		}
		
		public void setFilm(Film film) {
			if (this.film != null) {
				this.film.projekcije.remove(this);
				
				this.film = film;
				if (film != null) {
					film.projekcije.add(this);
				}
			}
		}

		public LocalDateTime getDatumVreme() {
			return datumVreme;
		}

		public void setDatumVreme(LocalDateTime datumVreme) {
			this.datumVreme = datumVreme;
		}

		public int getSala() {
			return sala;
		}

		public void setSala(int sala) {
			this.sala = sala;
		}

		public String getTip() {
			return tip;
		}

		public void setTip(String tip) {
			this.tip = tip;
		}

		public double getCenaKarte() {
			return cenaKarte;
		}

		public void setCenaKarte(double cenaKarte) {
			this.cenaKarte = cenaKarte;
		}

		public long getId() {
			return id;
		}

		public Film getFilm() {
			return film;
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
			Projekcija other = (Projekcija) obj;
			return id == other.id;
		}

		@Override
		public String toString() {
			return "Projekcija [id=" + id + ", datumVreme=" + datumVreme + ", sala=" + sala + ", tip=" + tip
					+ ", cenaKarte=" + cenaKarte + ", film=" + film.getNaziv() + "]";
		}

	
		
		
		
		
		
		
		
		
		
		
		
		
}
