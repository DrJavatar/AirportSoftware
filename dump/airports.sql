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
  PRIMARY KEY (`flight_id`),
  KEY `Flights_aircraft_type_fk` (`aircraft_type`),
  CONSTRAINT `Flights_aircraft_type_fk` FOREIGN KEY (`aircraft_type`) REFERENCES `Aircraft` (`aircrafttype`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Flights`
--

LOCK TABLES `Flights` WRITE;
/*!40000 ALTER TABLE `Flights` DISABLE KEYS */;
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-25  1:19:20
