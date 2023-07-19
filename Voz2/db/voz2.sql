DROP SCHEMA IF EXISTS voz2;
CREATE SCHEMA voz2 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE voz2;
--Voz(long id, String broj, String naziv, LocalDateTime datumVremePolaska, double cenaKarte, int brojMesta)
CREATE TABLE vozovi (
	id BIGINT AUTO_INCREMENT,
    broj VARCHAR(10) NOT NULL,
	naziv VARCHAR(30) NOT NULL,
	datumVremePolaska DATETIME NOT NULL,
	cenaKarte DECIMAL(10,2) NOT NULL,
	brojMesta INT NOT NULL,
	PRIMARY KEY(id)
);
--Karta(long id, LocalDateTime datumVremeProdaje, String kupac, int razred, Voz voz)
CREATE TABLE karte (
	id BIGINT AUTO_INCREMENT,
	datumVremeProdaje DATETIME NOT NULL,
	kupac VARCHAR(50) NOT NULL,	
	razred INT NOT NULL,
	vozId BIGINT NOT NULL,
	PRIMARY KEY(id), 
    FOREIGN KEY(vozId) REFERENCES vozovi(id)
);


INSERT INTO vozovi (id, broj, naziv, datumVremePolaska, cenaKarte, brojMesta) VALUES (1, '150', 'Soko', '2022-05-06 07:43', 800.00, 13);
INSERT INTO vozovi (id, broj, naziv, datumVremePolaska, cenaKarte, brojMesta) VALUES (2, '300', 'Regio', '2022-05-06 13:00', 1000.00, 13);
INSERT INTO vozovi (id, broj, naziv, datumVremePolaska, cenaKarte, brojMesta) VALUES (3, '12', 'Brzi', '2022-05-06 21:30', 600.00, 13);
INSERT INTO vozovi (id, broj, naziv, datumVremePolaska, cenaKarte, brojMesta) VALUES (4, '25', 'Munja', '2022-05-07 07:04', 1300.00, 13);
INSERT INTO vozovi (id, broj, naziv, datumVremePolaska, cenaKarte, brojMesta) VALUES (5, '50', 'Brzi', '2022-05-07 10:30', 600.00, 13);
INSERT INTO vozovi (id, broj, naziv, datumVremePolaska, cenaKarte, brojMesta) VALUES (6, '2405', 'Regio', '2022-05-08 11:30', 1000.00, 13);
INSERT INTO vozovi (id, broj, naziv, datumVremePolaska, cenaKarte, brojMesta) VALUES (7, '553', 'Soko', '2022-05-08 21:30', 800.00, 13);



INSERT INTO karte (datumVremeProdaje, kupac, razred, vozId) VALUES ('2022-05-01 10:00', 'Kupac1', 1, 1);
INSERT INTO karte (datumVremeProdaje, kupac, razred, vozId) VALUES ('2022-05-02 10:00', 'Kupac2', 2, 4);
INSERT INTO karte (datumVremeProdaje, kupac, razred, vozId) VALUES ('2022-05-03 10:00', 'Kupac3', 1, 6);
INSERT INTO karte (datumVremeProdaje, kupac, razred, vozId) VALUES ('2022-05-04 10:00', 'Kupac4', 2, 3);
INSERT INTO karte (datumVremeProdaje, kupac, razred, vozId) VALUES ('2022-05-05 10:00', 'Kupac5', 1, 1);
INSERT INTO karte (datumVremeProdaje, kupac, razred, vozId) VALUES ('2022-05-06 10:00', 'Kupac6', 1, 2);
INSERT INTO karte (datumVremeProdaje, kupac, razred, vozId) VALUES ('2022-05-05 10:00', 'Kupac7', 2, 5);
INSERT INTO karte (datumVremeProdaje, kupac, razred, vozId) VALUES ('2022-05-04 10:00', 'Kupac8', 1, 7);
INSERT INTO karte (datumVremeProdaje, kupac, razred, vozId) VALUES ('2022-05-03 10:00', 'Kupac9', 2, 4);
INSERT INTO karte (datumVremeProdaje, kupac, razred, vozId) VALUES ('2022-05-02 10:00', 'Kupac10', 1, 7);
INSERT INTO karte (datumVremeProdaje, kupac, razred, vozId) VALUES ('2022-05-01 10:00', 'Kupac11', 1, 1);
INSERT INTO karte (datumVremeProdaje, kupac, razred, vozId) VALUES ('2022-05-02 10:00', 'Kupac12', 1, 1);
INSERT INTO karte (datumVremeProdaje, kupac, razred, vozId) VALUES ('2022-05-03 10:00', 'Kupac13', 2, 3);
INSERT INTO karte (datumVremeProdaje, kupac, razred, vozId) VALUES ('2022-05-04 10:00', 'Kupac14', 2, 5);
INSERT INTO karte (datumVremeProdaje, kupac, razred, vozId) VALUES ('2022-05-05 10:00', 'Kupac15', 2, 4);
INSERT INTO karte (datumVremeProdaje, kupac, razred, vozId) VALUES ('2022-05-06 10:00', 'Kupac16', 1, 6);
INSERT INTO karte (datumVremeProdaje, kupac, razred, vozId) VALUES ('2022-05-05 10:00', 'Kupac17', 2, 1);
INSERT INTO karte (datumVremeProdaje, kupac, razred, vozId) VALUES ('2022-05-04 10:00', 'Kupac18', 2, 3);
INSERT INTO karte (datumVremeProdaje, kupac, razred, vozId) VALUES ('2022-05-03 10:00', 'Kupac19', 1, 7);
INSERT INTO karte (datumVremeProdaje, kupac, razred, vozId) VALUES ('2022-05-02 10:00', 'Kupac20', 1, 2);



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

