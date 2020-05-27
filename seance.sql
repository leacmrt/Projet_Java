-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mer. 27 mai 2020 à 07:52
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `essaiprojet`
--

-- --------------------------------------------------------

--
-- Structure de la table `seance`
--

DROP TABLE IF EXISTS `seance`;
CREATE TABLE IF NOT EXISTS `seance` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Date` date NOT NULL,
  `Jour` int(11) NOT NULL,
  `Semaine` int(11) NOT NULL,
  `Heure_Debut` int(11) NOT NULL,
  `Heure_Fin` int(11) NOT NULL,
  `Etat` text NOT NULL,
  `ID_Cours` int(11) NOT NULL,
  `ID_Type` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `seance`
--

INSERT INTO `seance` (`ID`, `Date`, `Jour`, `Semaine`, `Heure_Debut`, `Heure_Fin`, `Etat`, `ID_Cours`, `ID_Type`) VALUES
(1, '0000-00-00', 4, 1, 8, 10, 'passe', 1, 1),
(2, '2020-06-12', 0, 1, 14, 16, 'attente', 1, 1),
(3, '2020-06-08', 2, 1, 16, 18, 'attente', 2, 1),
(4, '2020-05-18', 3, 1, 10, 12, 'attente', 3, 1);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
