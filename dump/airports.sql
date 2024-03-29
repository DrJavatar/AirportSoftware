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
-- Table structure for table `Aircraft`
--

DROP TABLE IF EXISTS `Aircraft`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Aircraft` (
  `aircrafttype` char(3) NOT NULL,
  `adescription` varchar(40) DEFAULT NULL,
  `noseats` smallint(6) DEFAULT NULL,
  `home_airport_code` char(3) DEFAULT NULL,
  PRIMARY KEY (`aircrafttype`),
  KEY `Aircraft_home_airport_code_fk` (`home_airport_code`),
  CONSTRAINT `Aircraft_home_airport_code_fk` FOREIGN KEY (`home_airport_code`) REFERENCES `AirportCode` (`airport_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Aircraft`
--

LOCK TABLES `Aircraft` WRITE;
/*!40000 ALTER TABLE `Aircraft` DISABLE KEYS */;
INSERT INTO `Aircraft` VALUES ('DC9','McDonnel Douglas Jet',120,'YUL');
/*!40000 ALTER TABLE `Aircraft` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AirportCode`
--

DROP TABLE IF EXISTS `AirportCode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `AirportCode` (
  `airport_code` char(3) NOT NULL,
  PRIMARY KEY (`airport_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AirportCode`
--

LOCK TABLES `AirportCode` WRITE;
/*!40000 ALTER TABLE `AirportCode` DISABLE KEYS */;
INSERT INTO `AirportCode` VALUES ('YUL'),('YVR'),('YYC'),('YYZ');
/*!40000 ALTER TABLE `AirportCode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Flights`
--

DROP TABLE IF EXISTS `Flights`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Flights` (
  `flight_id` int(11) NOT NULL AUTO_INCREMENT,
  `aircraft_type` char(3) DEFAULT NULL,
  `airport_code` char(3) DEFAULT NULL,
  `airport_code_dest` char(3) DEFAULT NULL,
  `pilot_id` int(11) DEFAULT NULL,
  `co_pilot_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`flight_id`),
  KEY `Flights_aircraft_type_fk` (`aircraft_type`),
  KEY `Flights_co_pilot_id_fk` (`pilot_id`),
  KEY `Flights_pilot_id_fk` (`co_pilot_id`),
  CONSTRAINT `Flights_aircraft_type_fk` FOREIGN KEY (`aircraft_type`) REFERENCES `Aircraft` (`aircrafttype`),
  CONSTRAINT `Flights_pilot_id_fk` FOREIGN KEY (`co_pilot_id`) REFERENCES `Staff` (`id`),
  CONSTRAINT `Flights_pilot_no_fk` FOREIGN KEY (`pilot_id`) REFERENCES `Staff` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Flights`
--

LOCK TABLES `Flights` WRITE;
/*!40000 ALTER TABLE `Flights` DISABLE KEYS */;
INSERT INTO `Flights` VALUES (6,'DC9','YUL','YYC',0,1);
/*!40000 ALTER TABLE `Flights` ENABLE KEYS */;
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
  `flight_no` int(11) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `Passenger_flight_no_fk` (`flight_no`),
  CONSTRAINT `Passenger_flight_no_fk` FOREIGN KEY (`flight_no`) REFERENCES `Flights` (`flight_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Passenger`
--

LOCK TABLES `Passenger` WRITE;
/*!40000 ALTER TABLE `Passenger` DISABLE KEYS */;
INSERT INTO `Passenger` VALUES (10,'D Hamer','1 St Pauls Churchyard','',NULL),(20,'D Avison','5 Chancery Lane','',NULL),(21,'G Davis','25 Allenby Road','',6),(24,'C Evans','63 Kew Green','',6),(26,'J Millar','Englewood Cliffs','061 343 881',6),(28,'J Ullman','1 Microsoft Way','',NULL),(29,'A Smithson','16 Bedford St','071 577 890',NULL),(30,'D Etheridge','4 Maylands Avenue','',NULL),(34,'E Simon','8 Cherry Street','',NULL),(90,'A Smith','81 Digby Crescent','071 321 456',NULL),(91,'T Pittman','The Little House','',NULL),(92,'J Peters','31 Lucas Road','',NULL),(93,'K Kendall','11 Rosedale Avenue','',NULL),(94,'R Miller','155 Kingston Road','0638 1100',NULL);
/*!40000 ALTER TABLE `Passenger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Staff`
--

DROP TABLE IF EXISTS `Staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Staff` (
  `id` int(11) NOT NULL,
  `firstName` varchar(4000) NOT NULL,
  `lastName` varchar(4000) NOT NULL,
  `role` varchar(4000) NOT NULL,
  `flight_no` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Staff_flight_no_fk` (`flight_no`),
  CONSTRAINT `Staff_flight_no_fk` FOREIGN KEY (`flight_no`) REFERENCES `Flights` (`flight_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Staff`
--

LOCK TABLES `Staff` WRITE;
/*!40000 ALTER TABLE `Staff` DISABLE KEYS */;
INSERT INTO `Staff` VALUES (0,'tim','jones','pilot',6),(1,'mark','adams','pilot',6),(2,'arthur','smith','attendant',NULL),(3,'lisa','ant','attendant',NULL),(4,'jenny','michaels','pilot',NULL),(5,'sarah','silver','attendant',NULL),(6,'brent','jefferies','attendant',NULL),(7,'mike','reynolds','pilot',NULL),(8,'evan','young','pilot',NULL),(9,'morgan','moss','attendant',NULL),(10,'brandy','samson','attendant',NULL),(11,'alicia','key','pilot',NULL),(12,'alex','liv','pilot',NULL),(13,'greta','micha','attendant',NULL),(14,'steve','rode','attendant',NULL);
/*!40000 ALTER TABLE `Staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'airpots'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-26  2:30:45
