-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: flotamysql
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ladunek`
--

DROP TABLE IF EXISTS `ladunek`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ladunek` (
  `ID_LADUNKU` int NOT NULL AUTO_INCREMENT,
  `NAZWA_LADUNKU` mediumtext NOT NULL,
  `CENA_ZA_TONE_USD` double NOT NULL,
  `OBJETOSC_TONY` double NOT NULL,
  `GRAIN_STANDARD` mediumtext NOT NULL,
  `UWAGI_DOT_LADUNKU` mediumtext,
  UNIQUE KEY `ID_LADUNKU_UNIQUE` (`ID_LADUNKU`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ladunek`
--

LOCK TABLES `ladunek` WRITE;
/*!40000 ALTER TABLE `ladunek` DISABLE KEYS */;
INSERT INTO `ladunek` VALUES (1,'DRI',2000,0.3,'false',NULL),(2,'WHEAT',3000,2,'true',NULL),(3,'COAL',500,1.5,'false',NULL),(4,'RICE',2600,1.9,'true',NULL),(5,'PET COKE',1500,0.5,'false',NULL),(6,'BILLETS',1700,0.3,'false',NULL),(7,'SALT',2000,0.7,'true',NULL),(8,'STEAL COIL',1800,0.4,'false',NULL),(9,'STONES',1000,0.8,'false',NULL);
/*!40000 ALTER TABLE `ladunek` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `port`
--

DROP TABLE IF EXISTS `port`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `port` (
  `ID_PORTU` int DEFAULT NULL,
  `NAZWA_PORTU` mediumtext NOT NULL,
  `KRAJ` mediumtext NOT NULL,
  `KRAJ_PELNA_NAZWA` mediumtext,
  `SZEROKOSC_GEOGRAFICZNA` mediumtext,
  `DLUGOSC_GEOGRAFICZNA` mediumtext,
  UNIQUE KEY `ID_PORTU_UNIQUE` (`ID_PORTU`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `port`
--

LOCK TABLES `port` WRITE;
/*!40000 ALTER TABLE `port` DISABLE KEYS */;
INSERT INTO `port` VALUES (1,'BALTIMORE','USA','UNITED STATES OF AMERICA','39.17','76.37'),(2,'BOSTON','USA','UNITED STATES OF AMERICA','42.21','71.03'),(3,'NORFOLK','USA','UNITED STATES OF AMERICA','36.54','76.12'),(4,'BARANQUILLA','COL','COLUMBIA','10.57','74.47'),(5,'CARTAGHENA','COL','COLUMBIA','10.24','75.3'),(6,'RIO DE JANEIRO','BRA','BRASIL','22.54','43.11'),(7,'SANTA MARTA','COL','COLUMBIA','11.14','74.12'),(8,'GDAŃSK','POL','POLAND','54.2','18.38'),(9,'GDYNIA','POL','POLAND','54.31','18.32'),(10,'SAN THOMAS','USA','UNITED STATES OF AMERICA','43.27','3.48'),(11,'ST MAARTEN','NED','NETHERLANDS','46.1','1.09'),(12,'SANTANDER','ESP','SPAIN','43.27','3.48'),(13,'LA ROCHELE','FRA','FRANCE','46.1','1.09'),(14,'FREETOWN','SLE','SIERRA LEONE','8.29','13.14'),(NULL,'HAVANA','CUBA',NULL,NULL,NULL),(NULL,'HAVANA','CUBA',NULL,NULL,NULL),(NULL,'dd','fsf','gfd','32.12','21.23'),(NULL,'ffff','ffff','fff','ff','ff'),(NULL,'GGG','POL','Polska','23.44','21.21'),(NULL,'HHHH','POL','POLSKA','32.31','23.23');
/*!40000 ALTER TABLE `port` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statek`
--

DROP TABLE IF EXISTS `statek`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `statek` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NAZWA_STATKU` mediumtext NOT NULL,
  `LADOWNOSC_STATKU_DWT` double NOT NULL,
  `LADOWNOSC_STATKU_OBJETOSC_M3` double NOT NULL,
  `DOSTĘPNY DO ZALADUNKU` mediumtext,
  `SZCOWANA_DATA_DOSTEPNOSCI` mediumtext NOT NULL,
  `DOBOWY_KOSZY_PALIWA_USD` double NOT NULL,
  `ŚREDNIA PREDKOŚĆ (KN)` double DEFAULT NULL,
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statek`
--

LOCK TABLES `statek` WRITE;
/*!40000 ALTER TABLE `statek` DISABLE KEYS */;
INSERT INTO `statek` VALUES (1,'CURACAO PEARL',37000,48500,'true','15-07-2021',19000,10.1),(2,'HOUSTON PEARL',32000,42300,'true','17-07-2021',17500,9.7),(3,'HAMBURG PEARL',32000,42300,'true','23-07-2021',17500,9.7),(4,'ADRIATIC PEARL',35000,45600,'true','20-07-2021',21000,10.3),(5,'WINTERSET',23000,32000,'false','25-07-2021',20500,9.8),(6,'CYMBIDIUM',7000,12000,'false','16-07-2021',7500,11),(7,'AQUA PEARL',32000,46900,'false','14-07-2021',23000,9.8),(8,'PACIFIC PEARL',32000,46800,'false','21-07-2021',22000,10.1),(9,'HOLLAND PEARL',42000,55000,'true','21-07-2021',15000,11),(10,'POLAND PEARL',45000,60000,'true','21-07-2021',15500,11);
/*!40000 ALTER TABLE `statek` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zamowienie`
--

DROP TABLE IF EXISTS `zamowienie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zamowienie` (
  `ID_ZAMOWIENIA` int NOT NULL AUTO_INCREMENT,
  `LADUNEK` mediumtext NOT NULL,
  `STATEK` mediumtext NOT NULL,
  `ILOSC_LADUNKU` double NOT NULL,
  `OBJETOSC_LADUNKU` double NOT NULL,
  `KOSZT` double NOT NULL,
  `ZAROBEK` double NOT NULL,
  `ZYSK_STRATA` double NOT NULL,
  PRIMARY KEY (`ID_ZAMOWIENIA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zamowienie`
--

LOCK TABLES `zamowienie` WRITE;
/*!40000 ALTER TABLE `zamowienie` DISABLE KEYS */;
/*!40000 ALTER TABLE `zamowienie` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-25 17:02:38
