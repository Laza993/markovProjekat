DROP SCHEMA IF EXISTS voz3;
CREATE SCHEMA voz3 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE voz3;

CREATE TABLE vozovi (
	id BIGINT AUTO_INCREMENT,
	nazivVoza VARCHAR(50) NOT NULL,
    tipVoza ENUM('PUTNICKI', 'TERETNI', 'REGIONALNI', 'EKSPRES'),
	kapacitetVoza INT NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE kupci (
	id BIGINT AUTO_INCREMENT,
	ime VARCHAR(50) NOT NULL,
	prezime VARCHAR(50) NOT NULL,
	datumRodjenja DATE NOT NULL,
	jmbg BIGINT AUTO_INCREMENT,
    kategorija ENUM('STUDENT', 'PENZIONER', 'DETE', 'INVALID'),
	PRIMARY KEY(id)
);

CREATE TABLE voznje (
	id BIGINT AUTO_INCREMENT,
	vozId BIGINT NOT NULL,
	cenaKarte DECIMAL(10,2) NOT NULL,
	datumVremePolaska DATETIME NOT NULL,
	pocetnaStanica VARCHAR(70) NOT NULL,
	krajnjaStanica VARCHAR(70) NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(vozId) REFERENCES vozovi(id) 
);


CREATE TABLE karte (
	id BIGINT AUTO_INCREMENT,
	datumVremeProdaje DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	kupacId BIGINT NOT NULL,	
	razred ENUM('PRVI', 'DRUGI', 'TRECI'),
	vozId BIGINT NOT NULL,
	voznjaId BIGINT NOT NULL,
	PRIMARY KEY(id), 
	FOREIGN KEY(kupacId) REFERENCES kupci(id),
	FOREIGN KEY(vozId) REFERENCES vozovi(id),
    FOREIGN KEY(voznjaId) REFERENCES voznje(id)
);

INSERT INTO voznje (id, vozId, cenaKarte, datumVremePolaska, pocetnaStanica, krajnjaStanica) VALUES (1, 1, 1200.00, '2022-05-06 07:15', 'Beograd', 'Nis');
INSERT INTO voznje (id, vozId, cenaKarte, datumVremePolaska, pocetnaStanica, krajnjaStanica) VALUES (2, 2, 900.00, '2022-05-06 07:20', 'Beograd', 'Uzice');
INSERT INTO voznje (id, vozId, cenaKarte, datumVremePolaska, pocetnaStanica, krajnjaStanica) VALUES (3, 3, 1100.00, '2022-05-07 08:43', 'Zrenjanin', 'Subotica');
INSERT INTO voznje (id, vozId, cenaKarte, datumVremePolaska, pocetnaStanica, krajnjaStanica) VALUES (4, 6, 900.00, '2022-05-07 08:55', 'Novi Sad', 'Subotica');
INSERT INTO voznje (id, vozId, cenaKarte, datumVremePolaska, pocetnaStanica, krajnjaStanica) VALUES (5, 4, 1500.00, '2022-04-08 06:33', 'Subotica', 'Nis');
INSERT INTO voznje (id, vozId, cenaKarte, datumVremePolaska, pocetnaStanica, krajnjaStanica) VALUES (6, 10, 700.00, '2022-04-08 07:00', 'Subotica', 'Kragujevac');
INSERT INTO voznje (id, vozId, cenaKarte, datumVremePolaska, pocetnaStanica, krajnjaStanica) VALUES (7, 9, 1300.00, '2022-04-08 07:13', 'Kragujevac', 'Kruševac');
INSERT INTO voznje (id, vozId, cenaKarte, datumVremePolaska, pocetnaStanica, krajnjaStanica) VALUES (8, 8, 600.00, '2022-06-09 07:43', 'Novi Sad', 'Beograd');
INSERT INTO voznje (id, vozId, cenaKarte, datumVremePolaska, pocetnaStanica, krajnjaStanica) VALUES (9, 9, 900.00, '2022-06-09 07:55', 'Novi Sad', 'Zrenjanin');
INSERT INTO voznje (id, vozId, cenaKarte, datumVremePolaska, pocetnaStanica, krajnjaStanica) VALUES (10, 10, 400.00, '2022-06-09 09:43', 'Novi Sad', 'Beocin');
INSERT INTO voznje (id, vozId, cenaKarte, datumVremePolaska, pocetnaStanica, krajnjaStanica) VALUES (11, 13, 1500.00, '2022-06-09 10:43', 'Novi Sad', 'Nis');
INSERT INTO voznje (id, vozId, cenaKarte, datumVremePolaska, pocetnaStanica, krajnjaStanica) VALUES (12, 1, 1000.00, '2022-06-10 07:43', 'Novi Sad', 'Krusevac');
INSERT INTO voznje (id, vozId, cenaKarte, datumVremePolaska, pocetnaStanica, krajnjaStanica) VALUES (13, 10, 4500.00, '2022-05-11 07:43', 'Novi Sad', 'Crna Gora');

INSERT INTO vozovi (id, nazivVoza, tipVoza, kapacitetVoza) VALUES (1, 'Soko', 'PUTNICKI', 350);
INSERT INTO vozovi (id, nazivVoza, tipVoza, kapacitetVoza) VALUES (2, 'Jastreb', 'EKSPRES', 250);
INSERT INTO vozovi (id, nazivVoza, tipVoza, kapacitetVoza) VALUES (3, 'Brzi', 'EKSPRES', 200);
INSERT INTO vozovi (id, nazivVoza, tipVoza, kapacitetVoza) VALUES (4, 'Orao', 'REGIONALNI', 500);
INSERT INTO vozovi (id, nazivVoza, tipVoza, kapacitetVoza) VALUES (5, 'B-13', 'TERETNI', 8000);
INSERT INTO vozovi (id, nazivVoza, tipVoza, kapacitetVoza) VALUES (6, 'Beli', 'PUTNICKI', 350);
INSERT INTO vozovi (id, nazivVoza, tipVoza, kapacitetVoza) VALUES (7, 'Red', 'REGIONALNI', 450);
INSERT INTO vozovi (id, nazivVoza, tipVoza, kapacitetVoza) VALUES (8, 'M-13', 'TERETNI', 7000);
INSERT INTO vozovi (id, nazivVoza, tipVoza, kapacitetVoza) VALUES (9, 'Black', 'EKSPRES', 250);
INSERT INTO vozovi (id, nazivVoza, tipVoza, kapacitetVoza) VALUES (10, 'Kornjaca', 'TERETNI', 9000);

INSERT INTO kupci (id, ime, prezime, datumRodjenja, jmbg, kategorija) VALUES (1, 'Marina', 'Popovic', '1987-11-07', 1234567891596, 'STUDENT');
INSERT INTO kupci (id, ime, prezime, datumRodjenja, jmbg, kategorija) VALUES (2, 'Sara', 'Novakovic', '2001-01-01', 1234797891596, 'INVALID');
INSERT INTO kupci (id, ime, prezime, datumRodjenja, jmbg, kategorija) VALUES (3, 'Nebojsa', 'Radumilo', '1960-10-17', 1234567893996, 'PENZIONER');
INSERT INTO kupci (id, ime, prezime, datumRodjenja, jmbg, kategorija) VALUES (4, 'Milos', 'Fajsi', '2001-03-03', 1234567891511, 'STUDENT');
INSERT INTO kupci (id, ime, prezime, datumRodjenja, jmbg, kategorija) VALUES (5, 'Viktorija', 'Milovanovic', '1945-11-07', 1234567891596, 'PENZIONER');
INSERT INTO kupci (id, ime, prezime, datumRodjenja, jmbg, kategorija) VALUES (6, 'Nikolina', 'Bozic', '2020-10-05', 4314567891596, 'DETE');
INSERT INTO kupci (id, ime, prezime, datumRodjenja, jmbg, kategorija) VALUES (7, 'Alek', 'Nikolic', '1995-02-28', 1234567899596, 'STUDENT');
INSERT INTO kupci (id, ime, prezime, datumRodjenja, jmbg, kategorija) VALUES (8, 'Kasija', 'Milutinovic', '2000-11-07', 1234567891597, 'INVALID');
INSERT INTO kupci (id, ime, prezime, datumRodjenja, jmbg, kategorija) VALUES (9, 'Milica', 'Kobilarov', '2002-01-15', 1234567891589, 'STUDENT');
INSERT INTO kupci (id, ime, prezime, datumRodjenja, jmbg, kategorija) VALUES (10, 'Filip', 'Elhag', '2012-04-26', 1234565461596, 'DETE');

INSERT INTO karte (datumVremeProdaje, kupacId, razred, vozId, voznjaId) VALUES ('2022-05-01 10:00', 1, 'PRVI', 1, 1);
INSERT INTO karte (datumVremeProdaje, kupacId, razred, vozId, voznjaId) VALUES ('2022-05-02 11:55', 2, 'PRVI', 2, 2);
INSERT INTO karte (datumVremeProdaje, kupacId, razred, vozId, voznjaId) VALUES ('2022-05-03 12:00', 3, 'DRUGI', 10, 13);
INSERT INTO karte (datumVremeProdaje, kupacId, razred, vozId, voznjaId) VALUES ('2022-05-04 15:00', 4, 'TRECI', 9, 3);
INSERT INTO karte (datumVremeProdaje, kupacId, razred, vozId, voznjaId) VALUES ('2022-05-05 10:00', 5, 'DRUGI', 7, 5);
INSERT INTO karte (datumVremeProdaje, kupacId, razred, vozId, voznjaId) VALUES ('2022-05-06 17:00', 6, 'TRECI', 5, 7);
INSERT INTO karte (datumVremeProdaje, kupacId, razred, vozId, voznjaId) VALUES ('2022-05-05 22:00', 7, 'TRECI', 4, 12);
INSERT INTO karte (datumVremeProdaje, kupacId, razred, vozId, voznjaId) VALUES ('2022-05-04 12:55', 8, 'PRVI', 3, 9);
INSERT INTO karte (datumVremeProdaje, kupacId, razred, vozId, voznjaId) VALUES ('2022-05-03 05:55', 9, 'DRUGI', 6, 3);
INSERT INTO karte (datumVremeProdaje, kupacId, razred, vozId, voznjaId) VALUES ('2022-05-01 09:35', 10, 'DRUGI', 8, 4);
INSERT INTO karte (datumVremeProdaje, kupacId, razred, vozId, voznjaId) VALUES ('2022-05-02 15:15', 9, 'TRECI', 9, 6);
INSERT INTO karte (datumVremeProdaje, kupacId, razred, vozId, voznjaId) VALUES ('2022-05-03 08:43', 8, 'PRVI', 2, 8);
INSERT INTO karte (datumVremeProdaje, kupacId, razred, vozId, voznjaId) VALUES ('2022-05-04 10:00', 7, 'PRVI', 2, 11);


INSERT INTO karte (datumVremeProdaje, kupacId, razred, vozId, voznjaId) VALUES ('2022-05-05 10:00', 'Kupac5', 1, 1);
INSERT INTO karte (datumVremeProdaje, kupacId, razred, vozId, voznjaId) VALUES ('2022-05-06 10:00', 'Kupac6', 1, 2);
INSERT INTO karte (datumVremeProdaje, kupacId, razred, vozId, voznjaId) VALUES ('2022-05-05 10:00', 'Kupac7', 2, 5);
INSERT INTO karte (datumVremeProdaje, kupacId, razred, vozId, voznjaId) VALUES ('2022-05-04 10:00', 'Kupac8', 1, 7);
INSERT INTO karte (datumVremeProdaje, kupacId, razred, vozId, voznjaId) VALUES ('2022-05-03 10:00', 'Kupac9', 2, 4);



-- # tarife: naziv, opis, cena
-- 'Potok 150', '150 min. 150 SMS, 2GB', 800
-- 'Potok 300', '300 min. 300 SMS, 2.5GB', 1200
-- 'Maslačak 12', 'neograničeni min., neograničeni SMS, 27GB', 1900
-- 'Maslačak 25', 'neograničeni min., neograničeni SMS, 40GB', 2300
-- 'Maslačak 50', 'neograničeni min., neograničeni SMS, 100GB', 2600
-- 'Vrabac lite', 'neograničeni min., neograničeni SMS, neograničeni GB', 3000
-- 'Vrabac max', 'neograničeni min., neograničeni SMS, neograničeni GB, besplatni Phoebus', 4500


-- # pretplate: tarifa, pretplatnički broj, datum početka, trajanje ugovora
-- PRVA, '+381641111111', '2020-01-01', 12
-- PRVA, '+381642222222', '2020-06-01', 36
-- DRUGA, '+381643333333', '2020-01-01', 24
-- DRUGA, '+381644444444', '2022-06-01', 12
-- TREĆA, '+381645555555', '2022-01-01', 24
-- ČETVRTA, '+381641111111', '2021-01-01', 12
-- ČETVRTA, '+381643333333', '2022-01-01', 12
-- ČETVRTA, '+381646666666', '2022-06-01', 24
-- ČETVRTA, '+381647777777', '2022-01-01', 36
-- PETA, '+381641111111', '2022-01-01', 24
-- PETA, '+381648888888', '2023-01-01', 36
-- PETA, '+381647777777', '2023-01-01', 36
-- ŠESTA, '+381643333333', '2023-01-01', 12
-- ŠESTA, '+381642222222', '2023-01-01', 24
-- ŠESTA, '+381645555555', '2023-01-01', 12

