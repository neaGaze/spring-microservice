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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone_no` varchar(20) DEFAULT NULL,
  `email_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Jacquelyn','Huisman','332 Prairie Avenue \nGrand Blanc, MI 48439\n','(210) 474-4922','jacquelyn.huisman@gmail.com'),(2,'Walker','Segers','64 W. Blackburn Ave. Silver Spring, MD 20901','(716) 945-9481','WalkerSegers@gmail.com'),(3,'Ismael','Villafane','965 Euclid Drive Oklahoma City, OK 73112','(779) 755-5233','IsmaelVillafane@gmail.com'),(4,'Karey','Mattingly','6 W. Devonshire St. North Bergen, NJ 07047','(573) 958-1409','KareyMattingly@gmail.com'),(5,'Laurine','Ravenell','9234 Monroe St. Hamilton, OH 45011','(518) 906-9463','LaurineRavenell@gmail.com'),(6,'Nicholle','Ryerson','7 Princess Lane Batavia, OH 45103','(860) 864-1416','NicholleRyerson@gmail.com'),(7,'Charmain','Lipford','59 Winchester Avenue Moncks Corner, SC 29461','(810) 846-8496','CharmainLipford@gmail.com'),(8,'Ricky','Gowdy','7893 Linden Ave. Grayslake, IL 60030','(563) 328-1281','RickyGowdy@gmail.com'),(9,'Elizebeth','Caverly','163 Fifth Ave. Garfield, NJ 07026','(845) 458-0998','ElizebethCaverly@gmail.com'),(10,'Brook','Rothstein','7768 Central Dr. Maryville, TN 37803','(518) 185-8652','BrookRothstein@gmail.com'),(11,'Bobby','Dummer','8187 South St. Smyrna, GA 30080','(620) 986-6199','BobbyDummer@gmail.com'),(12,'Lorie','Wenner','800 East Linden Ave. Santa Cruz, CA 95060','(479) 182-2541','LorieWenner@gmail.com'),(13,'Britteny','Sink','7405 East Mechanic Dr. Snohomish, WA 98290','(320) 862-4479','BrittenySink@gmail.com'),(14,'Taren','Sauls','43 Plumb Branch Road Norwich, CT 06360','(785) 356-6623','TarenSauls@gmail.com'),(15,'Stormy','Urbanski','9370 Wentworth Court Powder Springs, GA 30127','(617) 125-4694','StormyUrbanski@gmail.com'),(16,'Pearlie','Bizzell','9477 Logan St. Memphis, TN 38106','(541) 259-8567','PearlieBizzell@gmail.com'),(17,'Stacee','Bellew','8382 St Louis St. Norman, OK 73072','(909) 973-1739','StaceeBellew@gmail.com'),(18,'Alva','Mcgrail','832 Highland Lane Arvada, CO 80003','(423) 610-9223','AlvaMcgrail@gmail.com'),(19,'Leana','Sherrow','190 East Wagon St. Cranford, NJ 07016','(681) 671-0096','LeanaSherrow@gmail.com'),(20,'Gaylord','Lovin','9209 Piper Street Groton, CT 06340','(972) 341-3232','GaylordLovin@gmail.com');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-13 15:31:56
