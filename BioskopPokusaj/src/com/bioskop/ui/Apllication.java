package com.bioskop.ui;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.bioskop.dao.FilmDAO;
import com.bioskop.dao.KorisnikDAO;
import com.bioskop.dao.ProjekcijaDAO;
import com.bioskop.dao.ZanrDAO;
import com.bioskop.dao.impl.database.DatabaseFilmDAO;
import com.bioskop.dao.impl.database.DatabaseKorisnikDAO;
import com.bioskop.dao.impl.database.DatabaseProjekcijaDAO;
import com.bioskop.dao.impl.database.DatabaseZanrDAO;
import com.bioskop.dao.impl.file.FileFilmDAO;
import com.bioskop.dao.impl.file.FileKorisnikDAO;
import com.bioskop.dao.impl.file.FileProjekcijaDAO;
import com.bioskop.dao.impl.file.FileZanrDAO;
import com.bioskop.model.Bioskop;
import com.novisad.jwd.modul1.util.Meni;
import com.novisad.jwd.modul1.util.Meni.FunkcionalnaStavkaMenija;
import com.novisad.jwd.modul1.util.Meni.IzlaznaStavkaMenija;
import com.novisad.jwd.modul1.util.Meni.StavkaMenija;

public class Apllication {

	private static void initFile() throws IOException {
		Bioskop.ucitaj();

		ZanrDAO zanrDAO = new FileZanrDAO();
		FilmDAO filmDAO = new FileFilmDAO();
		ProjekcijaDAO projekcijaDAO = new FileProjekcijaDAO();
		KorisnikDAO korisnikDAO = new FileKorisnikDAO();

		ZanrUI.setZanrDAO(zanrDAO);
		FilmoviUI.setFilm(filmDAO);
		ProjekcijaUI.setProjekcijaDAO(projekcijaDAO);
		KorisnikUI.setKorisnikDAO(korisnikDAO);
	}

	private static void initDatabase() throws SQLException  {
		
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/bioskop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Belgrade", 
				"root", 
				"root");

		ZanrDAO zanrDAO = new DatabaseZanrDAO(conn);
		FilmDAO filmDAO = new DatabaseFilmDAO(conn);
		ProjekcijaDAO projekcijaDAO = new DatabaseProjekcijaDAO(conn);
		KorisnikDAO korisnikDAO = new DatabaseKorisnikDAO(conn);

		ZanrUI.setZanrDAO(zanrDAO);
		FilmoviUI.setFilm(filmDAO);
		ProjekcijaUI.setProjekcijaDAO(projekcijaDAO);
		KorisnikUI.setKorisnikDAO(korisnikDAO);

		
			
			//initFile();
	

	}

	public static void main(String[] args) {
		try {
			initDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Doslo je dogreske prilikom povezivanja sa bazom");
		}
		Meni.pokreni("Bioskop",
				new StavkaMenija[] { new IzlaznaStavkaMenija("Izlaz"), 
						new FunkcionalnaStavkaMenija("Žanrovi") {

					@Override
					public void izvrsi() {
						ZanrUI.meni();
					}

				}, new FunkcionalnaStavkaMenija("Filmovi") {

					@Override
					public void izvrsi() {
						FilmoviUI.meni();
					}

				}, new FunkcionalnaStavkaMenija("Projekcije") {

					@Override
					public void izvrsi() {
						ProjekcijaUI.meni();
					}

				}, new FunkcionalnaStavkaMenija("Korisnici") {

					@Override
					public void izvrsi() {
						KorisnikUI.meni();
					}

				} });
	}

}
