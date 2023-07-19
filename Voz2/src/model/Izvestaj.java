package model;

import java.time.LocalDate;

import Voz_Util.Konzola;

public class Izvestaj {

	private String nazivVoza;
	private double ukupanPrihod;
	private LocalDate datumMaxBrojemProdatihKarata;

	public String getNazivVoza() {
		return nazivVoza;
	}

	public void setNazivVoza(String nazivVoza) {
		this.nazivVoza = nazivVoza;
	}

	public double getUkupanPrihod() {
		return ukupanPrihod;
	}

	public void setUkupanPrihod(double ukupanPrihod) {
		this.ukupanPrihod = ukupanPrihod;
	}

	public LocalDate getDatumMaxBrojemProdatihKarata() {
		return datumMaxBrojemProdatihKarata;
	}

	public void setDatumMaxBrojemProdatihKarata(LocalDate datumMaxBrojemProdatihKarata) {
		this.datumMaxBrojemProdatihKarata = datumMaxBrojemProdatihKarata;
	}

	public Izvestaj(String nazivVoza, double ukupanPrihod, LocalDate datumMaxBrojemProdatihKarata) {
		super();
		this.nazivVoza = nazivVoza;
		this.ukupanPrihod = ukupanPrihod;
		this.datumMaxBrojemProdatihKarata = datumMaxBrojemProdatihKarata;
	}

	@Override
	public String toString() {
		return "Izvestaj [nazivVoza=" + nazivVoza + ", ukupanPrihod=" + ukupanPrihod + ", datumMaxBrojemProdatihKarata="
				+ (datumMaxBrojemProdatihKarata != null ? Konzola.formatiraj(datumMaxBrojemProdatihKarata) : "") + "]";
	}
	
	public static int commperUkupanPrihod(Izvestaj stavka1, Izvestaj stavka2) {
		return -Double.compare(stavka1.ukupanPrihod, stavka2.ukupanPrihod);
	}

}
