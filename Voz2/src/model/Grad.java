package model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import Voz_Util.Konzola;

public class Grad {

	private long id;
	private String nazivGrada;
	private Set<Stanica> stanice = new HashSet<>();

	public Grad(long id, String nazivGrada, Set<Stanica> stanice) {
		this.id = id;
		this.nazivGrada = nazivGrada;
		this.stanice = stanice;
	}

	public Grad(long id, String nazivGrada) {

		this.id = id;
		this.nazivGrada = nazivGrada;
		this.stanice = null;
	}

	public Collection<Stanica> getStanice() {
		return Collections.unmodifiableSet(this.stanice);
	}

	public void addStanicu(Stanica stanica) {
		this.stanice.add(stanica);
	}

	public void removeStanicu(Stanica stanica) { // PRIMER
		this.stanice.remove(stanica);
	}

	public void addAllStanice(Collection<Stanica> stanice) {
		this.stanice.addAll(stanice);
	}

	public void removeAllStanice() {
		this.stanice.clear();
	}

	public Grad() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNazivGrada() {
		return nazivGrada;
	}

	public void setNazivGrada(String nazivGrada) {
		this.nazivGrada = nazivGrada;
	}

	public void setStanice(Set<Stanica> stanice) {
		this.stanice = stanice;
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
		Grad other = (Grad) obj;
		return id == other.id;
	}

	@Override
	public String toString() {

		String temp = "";
		temp += "------------------------ GRAD ------------------------\n";
		temp += "ID " + this.id + "\n";
		temp += "Naziv grada " + this.nazivGrada + "\n";
		temp += "Stanice " + this.stanice + "\n";
		return temp.trim();
	}
//		return "Grad [id=" + id + ", nazivGrada=" + nazivGrada + ", stanice=" + stanice + "]";

}
