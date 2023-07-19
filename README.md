# markovProjekat

Uputstvo za predaju zadatka: 
 
Eclipse projekat nazvati Modul1TestImePrezime. 
Projekat treba da bude izrađen za verziju Java 14. i da sadrži sledeće: 
1.	Izvorni kod u odgovarajućem source poddirektorijumu organizovan u odgovarajuću hijerarhiju paketa 
2.	SQL sktiptu za kreiranje/prepisivanje šeme baze i inicijalizaciju/reinicijalizaciju podataka u odgovarajućem poddirektorijumu 
3.	3rd party biblioteke u odgovarajućem poddirektorijumu, uvezane u build path. 
Po završetku projekat zapakovati u arhivu pod nazivom Modul1TestImePrezime.zip. Arhivu postaviti na https://enastava.ftninformatika.com u sekciju Assignments. 

Problem: sistem za evidenciju zeleznickog saobracaja Republike Srbije.
 
Tekst zadatka: 
 
Napraviti konzolnu Java aplikaciju za evidenciju prodaje karata u železničkom saobraćaju. Potrebno je evidentirati Voznje. Za svaku voznju se evidentira: 
1.	ID 
2.	Voz 
3.	naziv 
4.	datum i vreme polaska 
5.	cena karte 
6.	broj slobodnih mesta po razredima (mapa razred, broj mesta)
7.	pocetna stanica
8.	krajnja stanica
9.	Spisak stanica
Potrebno je evidentirati i karte. Za svaku kartu se evidentira: 
1.	ID 
2.	voz 
3.	voznja
4.	datum i vreme prodaje 
5.	kupac 
6.	razred 
Potrebno je evidentirati stanice. Za svaku stanicu se evidentira:
1.	ID
2.	Naziv stanice
3.	Grad
4.	Radno vreme stanice
5.	Polasci (lista Voznji) 
6.	Dolasci (lista Voznji)
Potrebno evidentirati gradove. 
1.	ID,
2.	Naziv grada
3.	Spisak stanica
Potrebno je evidentirati Vozove. Za svaki Voz se evidentira
1.	ID
2.	Naziv voza
3.	Vagoni (Lista vagona)
4.	Tip Voza (putnicki, teretni, regionalni, ekspres)
5.	Kapacitet voza (koliko putnika vozi)
Potrebno je evidentirati Kupca. Za svakog kupca se evidentira:
1.	ID
2.	Ime
3.	Prezime
4.	Datum rodjenja
5.	JMBG
6.	Kategorija (Student, Penzioner, Invalid, Dete…)

Evidentirati vagon:
1.	ID,
2.	Broj vagona
3.	Voz
4.	Razred
5.	Broj sedista

Evidentriati razred.
1.	ID
2.	Naziv (Enum razred)
3.	Cena karte

Podaci perzistiraju u MySQL bazi podataka. Primeri povezanih podataka se nalaze u datoteci voz.sql. 
 
Korisniku su dostupne sledeće osnovne funkcionalnosti: 
1.	prikaz svih voznji; prikaziju se sve voznje sa svim podacima
2.	prikaz jedne voznje sa prodatim kartama; unosi se broj voznje, a zatim se prikazuju svi podaci voznje (potrebno je dopuniti toString() metodu da prikazuje i broj slobodnih mesta) sa svim prodatim kartama (svi podaci o svakoj prodatoj karti u novom redu za svaku kartu – po razredima) 
3.	prodaja karte; unosi se broj voznje (ako voz sa tim brojem ne postoji ili je popunjen ili je već pošao, unos se prekida sa odgovarajućom porukom), zatim se unosi razred (ako razred nije jednak 1 ili 2, unos se prekida sa odgovarajućom porukom), a zatim kupac, nakon čega aplikacija instancira trenutni datum i vreme kao datum i vreme prodaje karte i zaključuje njeno dodavanje, čuvajući je u bazi. 

Dodate funkcionalnosti, laza:

4.	Prikaz voznji na relaciji izmedju dva grada (unose se polazno i zavrsno odrediste - gradovi)
2)	odabirom voznje prikazati, cene karte, stanice na kojima se zaustavlja, trajanje voznje, broj dostupnih karata po kategorijama
3)	kupovina karte
5.	Prikaz voznji na relaciji izmedju dve stanice (unose se polazno i zavrsno odrediste - stanice)
2)	odabirom voznje prikazati, cene karte, stanice na kojima se zaustavlja, trajanje voznje, broj dostupnih karata po kategorijama
6.	Omoguciti prikaz istorije karata kupca.
7.	Omoguciti prikaz predstojecih voznji za kupca
8.	Omoguciti prikaz voznji svih za zadati voz 
9.	Omoguciti dodavanje vagona vozu. Cime bi se osvezili podaci o broju mesta na vozu i na voznji.
10.	Prikaz svih vagona za voz
11.	Prikaz stanica za grad
12.	Prikaz Polazaka za stanicu (proslednjivanjem parametra polazak, stanica)
13.	Prikaz Dolazaka za stanicu (prosledjivanjem parametra dolazak, stanica)




14.	OVO CEMO NA KRAJU OSMISLITI DA BUDE ZANIMLJIVIJE Implementirati izveštavanje; korisnik unosi vremenski interval (zadat početim i krajnjim datumom i vremenom), a aplikacija zatim za svaki naziv voza (voditi računa da se više vozova vodi pod istim nazivom) prikazuje (u istom redu svaku od sledećih stavki, pa ponovo u novom redu za svaki sledeći voz): 1) naziv voza 
2)	ukupan prihod po svim prodatim kartama za vozove čiji su polasci u zadatom vremenskom intervalu; cena karte u 2. razredu iznosi 85% redovne cene 1 
3)	datum polaska voza u zadatom intervalu sa najvećim brojem prodatih karata Vozove je potrebno sortirati u opadajućem redosledu stavke 2.  
 
