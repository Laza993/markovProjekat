package model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import Voz_Util.Konzola;

public class Stanica {

	private long id;
	private String nazivStanice;
	private LocalTime radnoVremeStanice;
	private List<Voznja> polasci = new ArrayList<>();
	private List<Voznja> dolasci = new ArrayList<>();

	public Stanica(long id, String nazivStanice, LocalTime radnoVremeStanice, List<Voznja> polasci,
			List<Voznja> dolasci) {

		this.id = id;
		this.nazivStanice = nazivStanice;
		this.radnoVremeStanice = radnoVremeStanice;
		this.polasci = polasci;
		this.dolasci = dolasci;
	}

	public Stanica() {
	}

	public Collection<Voznja> getPolasci() {
		return Collections.unmodifiableCollection(this.polasci);
	}

	public void addPolazak(Voznja voznja) {
		this.polasci.add(voznja);
	}

	public void removePolazak(Voznja voznja) { // PRIMER
		this.polasci.remove(voznja);
	}

	public void addAllPolaske(Collection<Voznja> polasci) {
		this.polasci.addAll(polasci);
	}

	public void removeAllSpisakStanica() {
		this.polasci.clear();
	}

	public Collection<Voznja> getDolasci() {
		return Collections.unmodifiableCollection(this.dolasci);
	}

	public void addDolazak(Voznja voznja) {
		this.polasci.add(voznja);
	}

	public void removeDolazak(Voznja voznja) { // PRIMER
		this.dolasci.remove(voznja);
	}

	public void addAllDolaske(Collection<Voznja> polasci) {
		this.dolasci.addAll(polasci);
	}

	public void removeAllDolaske() {
		this.dolasci.clear();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNazivStanice() {
		return nazivStanice;
	}

	public void setNazivStanice(String nazivStanice) {
		this.nazivStanice = nazivStanice;
	}

	public LocalTime getRadnoVremeStanice() {
		return radnoVremeStanice;
	}

	public void setRadnoVremeStanice(LocalTime radnoVremeStanice) {
		this.radnoVremeStanice = radnoVremeStanice;
	}

	public void setPolasci(List<Voznja> polasci) {
		this.polasci = polasci;
	}

	public void setDolasci(List<Voznja> dolasci) {
		this.dolasci = dolasci;
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
		Stanica other = (Stanica) obj;
		return id == other.id;
	}

	@Override
	public String toString() {

		String temp = "";
		temp += "------------------------ STANICA ------------------------\n";
		temp += "ID " + this.id + "\n";
		temp += "Naziv stanice " + this.nazivStanice + "\n";
		temp += "Radno vreme stanice " + Konzola.formatiraj(radnoVremeStanice);
		temp += "Polasci " + this.polasci + "\n";
		temp += "Dolasci " + this.dolasci + "\n";

		return temp.trim();
	}

//		return "Stanica [id=" + id + ", nazivStanice=" + nazivStanice + ", radnoVremeStanice=" + radnoVremeStanice
//				+ ", polasci=" + polasci + ", dolasci=" + dolasci + "]";

}
