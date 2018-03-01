-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ach_stargate
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bank`
--

DROP TABLE IF EXISTS `bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bank` (
  `bank_id` int(11) NOT NULL AUTO_INCREMENT,
  `account_no` varchar(255) DEFAULT NULL,
  `account_type` varchar(255) DEFAULT NULL,
  `available_balance` double DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `routing_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bank_id`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank`
--

LOCK TABLES `bank` WRITE;
/*!40000 ALTER TABLE `bank` DISABLE KEYS */;
INSERT INTO `bank` VALUES (101,'5784280195134310','checking',NULL,'Citizen Service Bank ',5,'228199559'),(102,'3593359822843810','savings',5000013347,'Ascension Bank',12,'297780350'),(103,'2413116579431930','checking',22408,'Repose Holdings Bnak',17,'190794803'),(104,'7064689794255120','checking',5353,'Midland Bank ',7,'246694504'),(105,'6391245699115150','savings',839,'New Edge Bank',5,'241408768'),(106,'8898384283508000','savings',3242,'City Ward Bank',1,'175491203'),(107,'5351778396072530','checking',4831,'Kindred Bank',16,'260877807'),(108,'9741792350589470','savings',5500,'City Ward Bank',2,'282374075'),(109,'6590168499992250','savings',1562,'Citizen Service Bank ',15,'189517912'),(110,'2248734357879480','savings',2826,'Midland Bank ',6,'247977176'),(111,'5791445200722250','savings',411,'Citizen Service Bank ',17,'219669604'),(112,'6889843530771100','savings',1889,'Citizen Service Bank ',14,'290587742'),(113,'4838016857014070','checking',2418,'Repose Holdings Bnak',4,'248410966'),(114,'5140465010797900','savings',1455,'Ascension Bank',18,'282444772'),(115,'3990435879343290','checking',4483,'New Edge Bank',9,'223244274'),(116,'8966551917187920','savings',90,'Repose Holdings Bnak',10,'232846069'),(117,'4440487065970290','savings',2150,'Citizen Service Bank ',12,'200954094'),(118,'3054885489604910','savings',2656,'Ascension Bank',11,'144883663'),(119,'1980059152930360','savings',689,'Citizen Service Bank ',13,'161098820'),(120,'5071582483230180','savings',5506,'City Ward Bank',8,'200056515'),(121,'6927443765853850','savings',8689,'Repose Holdings Bnak',19,'132747393'),(123,'8224620395672060','savings',7543,'Kindred Bank',8,'173054315'),(124,'1701831021276890','savings',3368,'Midland Bank ',10,'297857422'),(125,'9.10855E+15','savings',5941,'Repose Holdings Bnak',20,'256433716');
/*!40000 ALTER TABLE `bank` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-13 15:31:58
