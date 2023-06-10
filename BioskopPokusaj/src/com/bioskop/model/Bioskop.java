package com.bioskop.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Bioskop {

	private static final String DATA_FOLDER = "data";
	private static final Path ZANROVI_PATH = Paths.get(DATA_FOLDER, "zanrovi.csv");
	private static final Path FILMOVI_PATH = Paths.get(DATA_FOLDER, "filmovi.csv");
	private static final Path FILM_ZANR_PATH = Paths.get(DATA_FOLDER, "filmZanr.csv");
	private static final Path PROJEKCIJE_PATH = Paths.get(DATA_FOLDER, "projekcije.csv");
	private static final Path KORISNICI_PATH = Paths.get(DATA_FOLDER, "korisnici.csv");
	
	private static final Map<Long, Zanr> zanrovi = new LinkedHashMap<>();
	private static final Map<Long, Film> filmovi = new LinkedHashMap<>();
	private static final Map<Long, Projekcija> projekcije = new LinkedHashMap<>();
	private static final Map<String, Korisnik> korisnici = new LinkedHashMap<>();

	private static long maxZanrId = 0;
	private static long maxFilmId = 0;
	private static long maxProjekcijaId = 0;
	
	private static void inicializuj() {
		
		zanrovi.put(1L, new Zanr(1L, "Naucna fantastika"));
		zanrovi.put(2L, new Zanr(2L, "Akcijia"));
		zanrovi.put(3L, new Zanr(3L, "Komedija"));
		zanrovi.put(4L, new Zanr(4L, "Horor"));
		zanrovi.put(5L, new Zanr(5L, "Avantura"));
		
		filmovi.put(1L, new Film(1L, "Avengers: Endgame", 182));
		filmovi.put(2L, new Film(2L, "Life", 110));
		filmovi.put(3L, new Film(3L, "It: Chapter 2", 170));
		filmovi.put(4L, new Film(4L, "Pirates of the Caribbean: Dead Men Tell No Tales", 153));
		
		filmovi.get(1L).addZanr(zanrovi.get(1L));
		filmovi.get(2L).addZanr(zanrovi.get(1L));
		filmovi.get(1L).addZanr(zanrovi.get(2L));
		filmovi.get(4L).addZanr(zanrovi.get(2L));
		filmovi.get(4L).addZanr(zanrovi.get(3L));
		filmovi.get(2L).addZanr(zanrovi.get(4L));
		filmovi.get(3L).addZanr(zanrovi.get(4L));
		filmovi.get(1L).addZanr(zanrovi.get(5L));
		filmovi.get(4L).addZanr(zanrovi.get(5L));
		
		projekcije.put(1L, new Projekcija(1L, LocalDateTime.parse("2020-06-22T20:00:00"), 1, "2D", 380.0, filmovi.get(1L)));
		projekcije.put(2L, new Projekcija(2L, LocalDateTime.parse("2020-06-22T23:30:00"), 1, "2D", 380.00, filmovi.get(3L)));
		projekcije.put(3L, new Projekcija(3L, LocalDateTime.parse("2020-06-22T20:00:00"), 2, "3D", 420.00, filmovi.get(1L)));
		projekcije.put(4L, new Projekcija(4L, LocalDateTime.parse("2020-06-22T23:30:00"), 2, "3D", 420.00, filmovi.get(2L)));
		projekcije.put(5L, new Projekcija(5L, LocalDateTime.parse("2020-06-22T20:00:00"), 3, "4D", 580.00, filmovi.get(3L)));
		projekcije.put(6L, new Projekcija(6L, LocalDateTime.parse("2020-06-23T20:00:00"), 1, "2D", 380.00, filmovi.get(2L)));
		projekcije.put(7L, new Projekcija(7L, LocalDateTime.parse("2020-06-23T22:00:00"), 1, "2D", 380.00, filmovi.get(4L)));
		projekcije.put(8L, new Projekcija(8L, LocalDateTime.parse("2020-06-23T20:00:00"), 2, "3D", 420.00, filmovi.get(2L)));
		projekcije.put(9L, new Projekcija(9L, LocalDateTime.parse("2020-06-23T22:00:00"), 2, "3D", 420.00, filmovi.get(4L)));
		projekcije.put(10L, new Projekcija(10L, LocalDateTime.parse("2020-06-23T20:00:00"),3, "4D", 580.00, filmovi.get(1L)));
		
		korisnici.put("mina", new Korisnik("mina", "mina123", "mina@gmail.com", "Z", true));
		korisnici.put("deki", new Korisnik("deki", "deki123", "deki@gmail.com", "M", false));
		korisnici.put("raca", new Korisnik("raca", "raca123", "raca@gmail.com", "M", false));
		
		maxZanrId = 5L;
		maxFilmId = 4L;
		maxProjekcijaId = 10L;
	}
	
	private static void sacuvajZanrove() throws IOException {
		List<String> line = new ArrayList<>();
		for (Zanr zanr : zanrovi.values()) {
			String linija = String.join(",", String.valueOf(zanr.getId()), zanr.getNaziv());
			line.add(linija);
		}
		Files.write(ZANROVI_PATH, line);
	}
	
	private static void sacuvajFilmove() throws IOException {
		List<String> line = new ArrayList<>();
		for (Film film : filmovi.values()) {
			String linija = String.join(".", String.valueOf(film.getId()), film.getNaziv(),
					String.valueOf(film.getTrajanje()));
			line.add(linija);
		}
		Files.write(FILMOVI_PATH, line);
	}
	
	private static void sacuvajFilmZanr() throws IOException {
		List<String> line = new ArrayList<>();
		for (Film film : filmovi.values()) {
			for (Zanr zanr : film.zanrovi) {
				String linija = String.join(",", String.valueOf(film.getId()), String.valueOf(zanr.getId()));
				line.add(linija);
			}
		}
		Files.write(FILM_ZANR_PATH, line);
	}
	
	private static void sacuvajProjekcije() throws IOException {
		List<String> line = new ArrayList<>();
		for (Projekcija projekcija : projekcije.values()) {
			String linija = String.join(",", String.valueOf(projekcija.getId()), 
					projekcija.getDatumVreme().toString(), 
					String.valueOf(projekcija.getSala()), 
					projekcija.getTip(), 
					String.valueOf(projekcija.getCenaKarte()), 
					String.valueOf(projekcija.getFilm().getId()));
			line.add(linija);
		}
		Files.write(PROJEKCIJE_PATH, line);
	}
	
	private static void sacuvajKorisnike() throws IOException {
		List<String> line = new ArrayList<>();
		for (Korisnik korisnik : korisnici.values()) {
			String linija = String.join(",", korisnik.getKorisnickoIme(), 
					korisnik.getLozinka(), korisnik.geteMail(), korisnik.getPol(), 
					String.valueOf(korisnik.isAdministrator()));
			line.add(linija);
		}
		Files.write(KORISNICI_PATH, line);
	}
	
	public static void sacuvaj() throws IOException {
		sacuvajZanrove();
		sacuvajFilmove();
		sacuvajFilmZanr();
		sacuvajProjekcije();
		sacuvajKorisnike();
	}
	
	private static void ucitajZanrove() throws IOException {
		for (String line : Files.readAllLines(ZANROVI_PATH)) {
			String[] tokeni = line.split(",");
			long id = Long.parseLong(tokeni[0]);
			String naziv = tokeni[1];
			
			Zanr zanr = new Zanr(id, naziv);
			zanrovi.put(zanr.getId(), zanr);
			maxZanrId++;
		}
	}
	
	private static void ucitajFilmove() throws IOException {
		for (String line : Files.readAllLines(FILMOVI_PATH)) {
			String[] tokeni = line.split(",");
			long id  =  Long.parseLong(tokeni[0]);
			String naziv = tokeni[1];
			int trajanje = Integer.parseInt(tokeni[2]);
			
			Film film = new Film(id, naziv, trajanje);
			filmovi.put(film.getId(), film);
			maxFilmId++;			
		}
	}
	
	private static void ucitajFilmZanr() throws IOException {
		for (String line  : Files.readAllLines(FILM_ZANR_PATH)) {
		String[] tokeni = line.split(",");
		long filmID = Long.parseLong(tokeni[0]);
		long zanrID = Long.parseLong(tokeni[1]);
		
		filmovi.get(filmID).addZanr(zanrovi.get(zanrID));
		}
	}
	
	private static void ucitajProjekciju() throws IOException {
		for (String line : Files.readAllLines(PROJEKCIJE_PATH)) {
			String[] tokeni = line.split(",");
			long id = Long.parseLong(tokeni[0]);
			LocalDateTime datumVreme = LocalDateTime.parse(tokeni[1]);
			int sala = Integer.parseInt(tokeni[2]);
			String tip = tokeni[3];
			double cenaKarte = Double.parseDouble(tokeni[4]);
			Film film = filmovi.get(Long.parseLong(tokeni[5]));
			
			Projekcija projekcija = new Projekcija(id, datumVreme, sala, tip, cenaKarte, film);
			projekcije.put(projekcija.getId(), projekcija);
			maxProjekcijaId++;
		}
	}
	
	private static void ucitajKorisnike() throws IOException {
		for (String line : Files.readAllLines(KORISNICI_PATH)) {
			String[] tokeni = line.split(",");
			String korisnickoIme = tokeni[0];
			String lozinka = tokeni[1];
			String email = tokeni[2];
			String pol = tokeni[3];
			boolean administrator = Boolean.parseBoolean(tokeni[4]);
			
			Korisnik korisnik = new Korisnik(korisnickoIme, lozinka, email, pol, administrator);
			korisnici.put(korisnik.getKorisnickoIme(), korisnik);
		}
	}
	
	public static void ucitaj() throws IOException {
		try {
			ucitajZanrove();
			ucitajFilmove();
			ucitajFilmZanr();
			ucitajProjekciju();
			ucitajKorisnike();
		} catch (IOException e) {
		//	inicializuj();
			sacuvaj();
			e.printStackTrace();
		}
	}
	
	public static Map<Long, Zanr> getZanrovi() {
		return zanrovi;
	}
	
	public static Map<Long, Film> getFilmovi() {
		return filmovi;
	}

	public static Map<Long, Projekcija> getProjekcije() {
		return projekcije;
	}
	
	public static Map<String, Korisnik> getKorisnici() {
		return korisnici;
	}

	public static long nextZanrId() {
		maxZanrId++;
		return maxZanrId;
	}

	public static long nextFilmId() {
		maxFilmId++;
		return maxFilmId;
	}

	public static long nextProjekcijaId() {
		maxProjekcijaId++;
		return maxProjekcijaId;
	}
}
