-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Gegenereerd op: 19 okt 2020 om 12:07
-- Serverversie: 10.4.14-MariaDB
-- PHP-versie: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fit4udb2`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `aanvraag`
--

CREATE TABLE `aanvraag` (
  `Aanvraag_id` int(255) NOT NULL,
  `Sporter_id` int(255) NOT NULL,
  `Trainingslot_id` int(255) NOT NULL,
  `Tijdstip_aanvraag` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Gegevens worden geëxporteerd voor tabel `aanvraag`
--

INSERT INTO `aanvraag` (`Aanvraag_id`, `Sporter_id`, `Trainingslot_id`, `Tijdstip_aanvraag`) VALUES
(1, 5000, 1, '2020-10-09 15:24:32.000000'),
(2, 5000, 2, '2020-10-18 12:22:31.487000'),
(3, 5000, 7, '2020-10-18 12:21:31.687000'),
(4, 5001, 7, '2020-10-19 13:49:31.647000'),
(5, 5001, 10, '2020-10-22 15:49:31.000000'),
(6, 5001, 5, '2020-10-19 15:49:31.000000');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `favorietetrainer`
--

CREATE TABLE `favorietetrainer` (
  `Favoriet_id` int(255) NOT NULL,
  `Sporter_id` int(255) NOT NULL,
  `Trainer_id` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Gegevens worden geëxporteerd voor tabel `favorietetrainer`
--

INSERT INTO `favorietetrainer` (`Favoriet_id`, `Sporter_id`, `Trainer_id`) VALUES
(1, 5000, 6001),
(2, 5001, 6001),
(3, 5001, 6002),
(4, 5000, 6003),
(5, 5000, 6002);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `gebruiker`
--

CREATE TABLE `gebruiker` (
  `Gebruiker_ID` int(255) NOT NULL,
  `Naam` char(20) NOT NULL,
  `Email` char(20) NOT NULL,
  `Wachtwoord` char(20) NOT NULL,
  `Leeftijd` int(255) NOT NULL,
  `Geslacht_id` int(255) NOT NULL,
  `Adres` char(50) NOT NULL,
  `Profielfoto` varchar(45) DEFAULT NULL,
  `Bio` char(255) DEFAULT NULL,
  `Geslacht_Geslacht_id` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Gegevens worden geëxporteerd voor tabel `gebruiker`
--

INSERT INTO `gebruiker` (`Gebruiker_ID`, `Naam`, `Email`, `Wachtwoord`, `Leeftijd`, `Geslacht_id`, `Adres`, `Profielfoto`, `Bio`, `Geslacht_Geslacht_id`) VALUES
(1000, 'Job vd Hoek', 'job@gmail.com', 'ww123', 20, 1, 'Jacoba v Beijerlaan 89', NULL, NULL, 1),
(1001, 'Pepijn de Kruijff', 'pepijn@gmail.com', 'ww124', 21, 1, 'Phoenixstraat 30', NULL, NULL, 1),
(1002, 'Isaac Hall', 'isaac@gmail.com', 'ww125', 21, 1, 'E du perronlaan 36', NULL, NULL, 1),
(1003, 'Jasper ter Horst', 'jasperterhorst@gmail', 'pass234', 19, 1, 'Dwarsstraat 23', NULL, 'Ik ben op zoek naar een personal trainer, maar ben door mijn werk niet consistent op dezelfde momenten beschikbaar om te trainen.', 1),
(1004, 'Paul Post', 'paulpost@hotmail.com', 'Post0456.', 33, 1, 'Fluwelensingel 124a', NULL, NULL, 1),
(1005, 'Fredje Flintstone', 'fredflint@knpmail.nl', 'xkO28>d', 41, 2, 'Klaverhoeve 15', NULL, NULL, 2),
(1006, 'Arie Aerobics', 'arie@gmail.com', 'ww123123', 55, 1, 'Prinsmauritslaan 30', NULL, NULL, 1),
(1007, 'Berry Bicepcurl', 'berry@gmail.com', 'ww123123', 26, 2, 'Sint Paulus plein 1', NULL, NULL, 2);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `geslacht`
--

CREATE TABLE `geslacht` (
  `Geslacht_id` int(255) NOT NULL,
  `Geslacht` char(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Gegevens worden geëxporteerd voor tabel `geslacht`
--

INSERT INTO `geslacht` (`Geslacht_id`, `Geslacht`) VALUES
(1, 'Man'),
(2, 'Vrouw');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `report`
--

CREATE TABLE `report` (
  `Report_id` int(255) NOT NULL,
  `Sporter_id` int(255) NOT NULL,
  `Trainer_id` int(255) NOT NULL,
  `Beschrijving` char(255) NOT NULL,
  `Status_id` int(255) NOT NULL,
  `Aanvraag_id` int(255) DEFAULT NULL,
  `Datum_indiening` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Gegevens worden geëxporteerd voor tabel `report`
--

INSERT INTO `report` (`Report_id`, `Sporter_id`, `Trainer_id`, `Beschrijving`, `Status_id`, `Aanvraag_id`, `Datum_indiening`) VALUES
(1, 1000, 6001, 'Hij kneep in me billen', 1, 1, '2020-10-11');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `sporter`
--

CREATE TABLE `sporter` (
  `Sporter_id` int(255) NOT NULL,
  `Gebruiker_id` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Gegevens worden geëxporteerd voor tabel `sporter`
--

INSERT INTO `sporter` (`Sporter_id`, `Gebruiker_id`) VALUES
(5000, 1000),
(5001, 1001);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `status`
--

CREATE TABLE `status` (
  `Status_id` int(255) NOT NULL,
  `Status_naam` char(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Gegevens worden geëxporteerd voor tabel `status`
--

INSERT INTO `status` (`Status_id`, `Status_naam`) VALUES
(1, 'Onder behandeling'),
(2, 'Behandeld');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `trainer`
--

CREATE TABLE `trainer` (
  `Trainer_id` int(255) NOT NULL,
  `Uurloon` int(3) NOT NULL,
  `Gebruiker_id` int(255) DEFAULT NULL,
  `KVK_nummer` int(12) DEFAULT NULL,
  `Geverifieerd` tinyint(4) DEFAULT NULL,
  `Gemiddelde review` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Gegevens worden geëxporteerd voor tabel `trainer`
--

INSERT INTO `trainer` (`Trainer_id`, `Uurloon`, `Gebruiker_id`, `KVK_nummer`, `Geverifieerd`, `Gemiddelde review`) VALUES
(6001, 13, 1002, NULL, NULL, 3.6),
(6002, 13, 1003, NULL, NULL, 4.8),
(6003, 10, 1004, NULL, NULL, 4.2),
(6004, 25, 1006, 0, NULL, 2.3),
(6005, 10, 1007, NULL, NULL, 5);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `training`
--

CREATE TABLE `training` (
  `Training_id(255)` int(255) NOT NULL,
  `Aanvraag_id` int(255) NOT NULL,
  `Afgerond` tinyint(4) NOT NULL,
  `Beoordeling` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Gegevens worden geëxporteerd voor tabel `training`
--

INSERT INTO `training` (`Training_id(255)`, `Aanvraag_id`, `Afgerond`, `Beoordeling`) VALUES
(1, 1, 1, 5),
(2, 2, 0, 5),
(3, 3, 0, 3);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `trainingslot`
--

CREATE TABLE `trainingslot` (
  `Trainingslot_id` int(255) NOT NULL,
  `Trainer_id` int(255) NOT NULL,
  `Datum` datetime(6) NOT NULL,
  `Duur` int(255) NOT NULL,
  `Beschrijving` char(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Gegevens worden geëxporteerd voor tabel `trainingslot`
--

INSERT INTO `trainingslot` (`Trainingslot_id`, `Trainer_id`, `Datum`, `Duur`, `Beschrijving`) VALUES
(1, 6001, '2020-10-10 15:23:54.000000', 2, 'aerobics'),
(2, 6001, '2020-10-22 13:45:00.094000', 2, 'football'),
(3, 6001, '2020-10-25 17:30:00.094000', 2, 'football'),
(4, 6001, '2020-10-28 11:15:00.094000', 2, 'football'),
(5, 6002, '2020-10-21 10:30:00.094000', 1, 'back'),
(6, 6002, '2020-10-23 07:45:00.094000', 1, 'back'),
(7, 6002, '2020-10-27 18:45:00.094000', 2, 'frontal cortex'),
(8, 6003, '2020-10-24 20:00:00.094000', 2, 'squash'),
(9, 6003, '2020-10-25 13:00:00.094000', 1, 'squash'),
(10, 6003, '2020-10-26 10:00:00.094000', 2, 'squash'),
(11, 6003, '2020-10-27 11:45:00.094000', 2, 'squash'),
(12, 6005, '2020-12-11 11:59:30.000000', 5, 'Biceps'),
(13, 6004, '2020-12-26 11:59:30.000000', 10, 'Ultra Aerobics');

--
-- Indexen voor geëxporteerde tabellen
--

--
-- Indexen voor tabel `aanvraag`
--
ALTER TABLE `aanvraag`
  ADD PRIMARY KEY (`Aanvraag_id`),
  ADD UNIQUE KEY `Aanvraag_id_UNIQUE` (`Aanvraag_id`),
  ADD KEY `fk_Aanvraag_Sporter1_idx` (`Sporter_id`),
  ADD KEY `fk_Aanvraag_Trainingslot1_idx` (`Trainingslot_id`);

--
-- Indexen voor tabel `favorietetrainer`
--
ALTER TABLE `favorietetrainer`
  ADD PRIMARY KEY (`Favoriet_id`),
  ADD UNIQUE KEY `Favoriet_id_UNIQUE` (`Favoriet_id`),
  ADD KEY `fk_FavorieteTrainer_Sporter1_idx` (`Sporter_id`),
  ADD KEY `fk_FavorieteTrainer_Trainer1_idx` (`Trainer_id`);

--
-- Indexen voor tabel `gebruiker`
--
ALTER TABLE `gebruiker`
  ADD PRIMARY KEY (`Gebruiker_ID`),
  ADD KEY `fk_Gebruiker_Geslacht` (`Geslacht_Geslacht_id`);

--
-- Indexen voor tabel `geslacht`
--
ALTER TABLE `geslacht`
  ADD PRIMARY KEY (`Geslacht_id`) USING BTREE;

--
-- Indexen voor tabel `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`Report_id`),
  ADD UNIQUE KEY `Report_id_UNIQUE` (`Report_id`),
  ADD KEY `fk_Report_Sporter1_idx` (`Sporter_id`),
  ADD KEY `fk_Report_Trainer1_idx` (`Trainer_id`),
  ADD KEY `fk_Report_Status1_idx` (`Status_id`),
  ADD KEY `fk_Report_Training1_idx` (`Aanvraag_id`);

--
-- Indexen voor tabel `sporter`
--
ALTER TABLE `sporter`
  ADD PRIMARY KEY (`Sporter_id`),
  ADD UNIQUE KEY `Sporter_id_UNIQUE` (`Sporter_id`),
  ADD KEY `fk_Sporter_Gebruiker1_idx` (`Gebruiker_id`);

--
-- Indexen voor tabel `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`Status_id`),
  ADD UNIQUE KEY `Status_id_UNIQUE` (`Status_id`);

--
-- Indexen voor tabel `trainer`
--
ALTER TABLE `trainer`
  ADD PRIMARY KEY (`Trainer_id`),
  ADD UNIQUE KEY `Trainer_id_UNIQUE` (`Trainer_id`),
  ADD UNIQUE KEY `KVK_nummer_UNIQUE` (`KVK_nummer`),
  ADD KEY `fk_Trainer_Gebruiker1_idx` (`Gebruiker_id`);

--
-- Indexen voor tabel `training`
--
ALTER TABLE `training`
  ADD PRIMARY KEY (`Training_id(255)`),
  ADD UNIQUE KEY `Training_id(255)_UNIQUE` (`Training_id(255)`),
  ADD KEY `fk_Training_Aanvraag1_idx` (`Aanvraag_id`);

--
-- Indexen voor tabel `trainingslot`
--
ALTER TABLE `trainingslot`
  ADD PRIMARY KEY (`Trainingslot_id`),
  ADD UNIQUE KEY `Trainingslot_id_UNIQUE` (`Trainingslot_id`),
  ADD KEY `fk_Trainingslot_Trainer1_idx` (`Trainer_id`);

--
-- Beperkingen voor geëxporteerde tabellen
--

--
-- Beperkingen voor tabel `aanvraag`
--
ALTER TABLE `aanvraag`
  ADD CONSTRAINT `fk_Aanvraag_Sporter1` FOREIGN KEY (`Sporter_id`) REFERENCES `sporter` (`Sporter_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Aanvraag_Trainingslot1` FOREIGN KEY (`Trainingslot_id`) REFERENCES `trainingslot` (`Trainingslot_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Beperkingen voor tabel `favorietetrainer`
--
ALTER TABLE `favorietetrainer`
  ADD CONSTRAINT `fk_FavorieteTrainer_Sporter1` FOREIGN KEY (`Sporter_id`) REFERENCES `sporter` (`Sporter_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_FavorieteTrainer_Trainer1` FOREIGN KEY (`Trainer_id`) REFERENCES `trainer` (`Trainer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Beperkingen voor tabel `gebruiker`
--
ALTER TABLE `gebruiker`
  ADD CONSTRAINT `fk_Gebruiker_Geslacht` FOREIGN KEY (`Geslacht_Geslacht_id`) REFERENCES `geslacht` (`Geslacht_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Beperkingen voor tabel `report`
--
ALTER TABLE `report`
  ADD CONSTRAINT `fk_Report_Sporter1` FOREIGN KEY (`Sporter_id`) REFERENCES `sporter` (`Gebruiker_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Report_Status1` FOREIGN KEY (`Status_id`) REFERENCES `status` (`Status_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Report_Trainer1` FOREIGN KEY (`Trainer_id`) REFERENCES `trainer` (`Trainer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Report_Training1` FOREIGN KEY (`Aanvraag_id`) REFERENCES `training` (`Aanvraag_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Beperkingen voor tabel `sporter`
--
ALTER TABLE `sporter`
  ADD CONSTRAINT `fk_Sporter_Gebruiker1` FOREIGN KEY (`Gebruiker_id`) REFERENCES `gebruiker` (`Gebruiker_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Beperkingen voor tabel `trainer`
--
ALTER TABLE `trainer`
  ADD CONSTRAINT `fk_Trainer_Gebruiker1` FOREIGN KEY (`Gebruiker_id`) REFERENCES `gebruiker` (`Gebruiker_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Beperkingen voor tabel `training`
--
ALTER TABLE `training`
  ADD CONSTRAINT `fk_Training_Aanvraag1` FOREIGN KEY (`Aanvraag_id`) REFERENCES `aanvraag` (`Aanvraag_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Beperkingen voor tabel `trainingslot`
--
ALTER TABLE `trainingslot`
  ADD CONSTRAINT `fk_Trainingslot_Trainer1` FOREIGN KEY (`Trainer_id`) REFERENCES `trainer` (`Trainer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
