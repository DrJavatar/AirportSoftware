-- MySQL dump 10.13  Distrib 8.0.18, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: airpots
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ACode`
--

DROP TABLE IF EXISTS `ACode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ACode` (
  `Code` varchar(4000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACode`
--

LOCK TABLES `ACode` WRITE;
/*!40000 ALTER TABLE `ACode` DISABLE KEYS */;
INSERT INTO `ACode` VALUES ('YVR'),('YYC'),('YYZ'),('YUL'),('YVR'),('YYC'),('YYZ'),('YUL'),('YVR'),('YYC'),('YYZ'),('YUL'),('YVR'),('YYC'),('YYZ'),('YUL'),('YVR'),('YYC'),('YYZ'),('YUL'),('YVR'),('YYC'),('YYZ'),('YUL');
/*!40000 ALTER TABLE `ACode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Aircraft`
--

DROP TABLE IF EXISTS `Aircraft`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Aircraft` (
  `aircrafttype` char(3) NOT NULL,
  `adescription` varchar(40) DEFAULT NULL,
  `noseats` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`aircrafttype`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Aircraft`
--

LOCK TABLES `Aircraft` WRITE;
/*!40000 ALTER TABLE `Aircraft` DISABLE KEYS */;
INSERT INTO `Aircraft` VALUES ('737','Boeing 737-300 Jet',300),('ATP','Advanced Turbo Prop',48),('DC9','McDonnel Douglas Jet',120);
/*!40000 ALTER TABLE `Aircraft` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Airport`
--

DROP TABLE IF EXISTS `Airport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Airport` (
  `airport` char(4) NOT NULL,
  `aname` varchar(20) DEFAULT NULL,
  `checkin` varchar(50) DEFAULT NULL,
  `resvtns` varchar(12) DEFAULT NULL,
  `flightinfo` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`airport`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Airport`
--

LOCK TABLES `Airport` WRITE;
/*!40000 ALTER TABLE `Airport` DISABLE KEYS */;
INSERT INTO `Airport` VALUES ('AMST','Amsterdam South Hall','Departures 20 mins before flight','06 022 2426','20 178299'),('BELF','Belfast','Desks 18 and 19, 15 mins before flight','023 2325151','08494 22888'),('BRUS','Brussels Sabena','check-in desks 30 mins before flight','2 511 9030','2 7207167'),('COV','Coventry','Check-in desks 20 mins before flight','024 768 123',''),('DUBL','Dublin','Check-in 20 mins prior to departure','01 423 555',''),('EDIN','Edinburgh','Gate 1 at least 10 mins before departure','031 447 1000',''),('EMID','East Midlands','Check-in 15 mins before flight for heavy bags','0332 810552','0332 852614'),('GLAS','Glasgow','Desks 60-64/Gate 8 20 mins before departure','041 204 2436',''),('HROW','Heathrow Island','A/B Terminal-1 20 mins before flight','081 5895599','081 7457321'),('LBDR','Leeds/Bradford','Check-in 15 mins before flight for baggage','0532 451991',''),('LVPL','Liverpool','15 mins heavy baggages,10 mins hand baggage','051 494 0200',''),('PARI','Paris Hall','22 Terminal 1 30 mins before flight','14742 14444','14862 2280'),('TEES','Teeside','15 mins heavy baggages,10 mins hand baggage','0642 219444','');
/*!40000 ALTER TABLE `Airport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Fares`
--

DROP TABLE IF EXISTS `Fares`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Fares` (
  `faretype` char(3) NOT NULL,
  `fdescription` varchar(25) DEFAULT NULL,
  `conditions` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`faretype`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Fares`
--

LOCK TABLES `Fares` WRITE;
/*!40000 ALTER TABLE `Fares` DISABLE KEYS */;
INSERT INTO `Fares` VALUES ('APR','Advanced Purchase Return','60 days advanced booking'),('BUR','Business Return','Business use only'),('EUR','Eurobudget Return','Available Paris Brussels Amsterdam'),('EXR','Excursion Return','Same day return'),('KFS','Key Fare Single',''),('PXR','Super Key Return',''),('SBS','Standby Single',''),('SDR','Standard Return',''),('SDS','Standard Single',''),('SXR','Superpex Return','90 days advanced booking');
/*!40000 ALTER TABLE `Fares` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Flight`
--

DROP TABLE IF EXISTS `Flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Flight` (
  `aircrafttype` char(3) DEFAULT NULL,
  `routeno` int(11) DEFAULT NULL,
  `flightno` varchar(5) NOT NULL,
  `fromairport` char(4) DEFAULT NULL,
  `toairport` char(4) DEFAULT NULL,
  `deptime` char(4) DEFAULT NULL,
  `arrtime` char(4) DEFAULT NULL,
  `service` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`flightno`),
  KEY `aircrafttype` (`aircrafttype`),
  KEY `routeno` (`routeno`),
  CONSTRAINT `Flight_ibfk_1` FOREIGN KEY (`aircrafttype`) REFERENCES `Aircraft` (`aircrafttype`),
  CONSTRAINT `Flight_ibfk_2` FOREIGN KEY (`routeno`) REFERENCES `Route` (`routeno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Flight`
--

LOCK TABLES `Flight` WRITE;
/*!40000 ALTER TABLE `Flight` DISABLE KEYS */;
/*!40000 ALTER TABLE `Flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Passenger`
--

DROP TABLE IF EXISTS `Passenger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Passenger` (
  `pid` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `address` varchar(40) DEFAULT NULL,
  `telno` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Passenger`
--

LOCK TABLES `Passenger` WRITE;
/*!40000 ALTER TABLE `Passenger` DISABLE KEYS */;
INSERT INTO `Passenger` VALUES (10,'D N Hamer','1 St Pauls Churchyard',''),(20,'D E Avison','5 Chancery Lane',''),(21,'G B Davis','25 Allenby Road',''),(24,'C Evans','63 Kew Green',''),(26,'J Millar','Englewood Cliffs','061 343 881'),(28,'J D Ullman','1 Microsoft Way',''),(29,'A Smithson','16 Bedford St','071 577 890'),(30,'D Etheridge','4 Maylands Avenue',''),(34,'E Simon','8 Cherry Street',''),(90,'A N Smith','81 Digby Crescent','071 321 456'),(91,'T Pittman','The Little House',''),(92,'J Peters','31 Lucas Road',''),(93,'K E Kendall','11 Rosedale Avenue',''),(94,'R H Miller','155 Kingston Road','0638 4672');
/*!40000 ALTER TABLE `Passenger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Route`
--

DROP TABLE IF EXISTS `Route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Route` (
  `routeno` int(11) NOT NULL,
  `rdescription` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`routeno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Route`
--

LOCK TABLES `Route` WRITE;
/*!40000 ALTER TABLE `Route` DISABLE KEYS */;
INSERT INTO `Route` VALUES (3,'Heathrow-Belfast'),(4,'Heathrow-Edinburgh'),(6,'Heathrow-Leeds/Bradford'),(7,'Heathrow-Liverpool'),(8,'Heathrow-Teeside'),(9,'Coventry-Brussels'),(11,'East Midlands-Belfast'),(13,'East Midlands-Paris'),(14,'Heathrow-Coventry'),(15,'Heathrow-East Midlands');
/*!40000 ALTER TABLE `Route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Staff`
--

DROP TABLE IF EXISTS `Staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Staff` (
  `id` decimal(10,0) NOT NULL,
  `firstName` varchar(4000) NOT NULL,
  `lastName` varchar(4000) NOT NULL,
  `role` varchar(4000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Staff`
--

LOCK TABLES `Staff` WRITE;
/*!40000 ALTER TABLE `Staff` DISABLE KEYS */;
INSERT INTO `Staff` VALUES (0,'tim','jones','pilot'),(1,'mark','adams','co-pilot'),(2,'arthur','smith','attendant'),(3,'lisa','ant','attendant'),(4,'jenny','michaels','pilot'),(5,'sarah','silver','attendant'),(6,'brent','jefferies','attendant'),(7,'mike','reynolds','pilot'),(8,'evan','young','co-pilot'),(9,'morgan','moss','attendant'),(10,'brandy','samson','attendant'),(11,'alicia','key','pilot'),(12,'alex','liv','co-pilot'),(13,'greta','micha','attendant'),(14,'steve','rode','attendant');
/*!40000 ALTER TABLE `Staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tariff`
--

DROP TABLE IF EXISTS `Tariff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Tariff` (
  `routeno` int(11) NOT NULL,
  `faretype` char(3) NOT NULL,
  `price` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`routeno`,`faretype`),
  KEY `faretype` (`faretype`),
  CONSTRAINT `Tariff_ibfk_1` FOREIGN KEY (`routeno`) REFERENCES `Route` (`routeno`),
  CONSTRAINT `Tariff_ibfk_2` FOREIGN KEY (`faretype`) REFERENCES `Fares` (`faretype`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tariff`
--

LOCK TABLES `Tariff` WRITE;
/*!40000 ALTER TABLE `Tariff` DISABLE KEYS */;
INSERT INTO `Tariff` VALUES (3,'BUR',117.00),(3,'SDR',158.00),(3,'SDS',79.00),(4,'SBS',49.00),(4,'SDR',162.00),(6,'BUR',117.00),(6,'KFS',53.00),(6,'SBS',42.00),(7,'SDR',128.00),(8,'SDS',74.00),(9,'APR',95.00),(9,'EUR',179.00),(9,'PXR',153.00),(11,'KFS',360.00),(13,'EXR',121.00),(14,'SBS',33.00),(14,'SDR',110.00),(15,'SBS',400.00);
/*!40000 ALTER TABLE `Tariff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ticket`
--

DROP TABLE IF EXISTS `Ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Ticket` (
  `ticketno` int(11) NOT NULL,
  `pid` int(11) DEFAULT NULL,
  `ticketdate` date DEFAULT NULL,
  PRIMARY KEY (`ticketno`),
  KEY `pid` (`pid`),
  CONSTRAINT `Ticket_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `Passenger` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ticket`
--

LOCK TABLES `Ticket` WRITE;
/*!40000 ALTER TABLE `Ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `Ticket` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-24 21:08:09
