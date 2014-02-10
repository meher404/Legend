CREATE DATABASE  IF NOT EXISTS `eshopping` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `eshopping`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: eshopping
-- ------------------------------------------------------
-- Server version	5.1.61-community

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `categoryID` int(11) NOT NULL DEFAULT '0',
  `categoryName` varchar(20) NOT NULL,
  PRIMARY KEY (`categoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (100,'mobile'),(178,'Computers'),(195,'bags'),(200,'camera'),(202,'airconditioners'),(203,'chairs'),(205,'laptops'),(220,'mobiles'),(300,'books'),(400,'watches'),(1187,'Cosmetics'),(1191,'Nikhitha'),(1194,'Mobilr'),(1195,'MobilR'),(1226,'mobiLe');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `name` varchar(20) NOT NULL,
  `PID` varchar(20) NOT NULL DEFAULT '',
  `image` varchar(100) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `quantity` decimal(5,0) DEFAULT NULL,
  `description` varchar(50) NOT NULL,
  `manufactureID` int(11) DEFAULT NULL,
  `rating` decimal(2,0) DEFAULT NULL,
  `discount` decimal(5,2) DEFAULT NULL,
  `categoryID` int(11) DEFAULT NULL,
  PRIMARY KEY (`PID`),
  KEY `categoryID` (`categoryID`),
  KEY `manufactureID` (`manufactureID`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`categoryID`) REFERENCES `category` (`categoryID`),
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`manufactureID`) REFERENCES `manufacturer` (`manufactureID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('Nexus','100147','img/products/Mobiles/nexus4.png',40000.00,85,'SmartPhone',147,4,0.00,100),('Optimals','1187210','img/products/Cosmetics/oriflame.jpg',500.00,500,'SunScreenLotion',210,4,0.00,1187),('Asus-zen','178232','img/products/Computers/asus-zen.jpg',50000.00,45,'touchScreen',232,4,0.00,178),('HarryPotter','300213','img/products/Books/harrypotter.jpg',400.00,850,'Novel',213,5,0.00,300),('bags','413','/images/image.jpg',500.00,54,'Bag',218,3,20.20,195),('letusC','book001',NULL,125.00,100,'C-language',300,4,0.00,300),('headfirstJAVA','book002',NULL,250.00,100,'java',400,3,0.00,300),('galaxy','camera001',NULL,49025.75,100,'samsung',100,4,0.00,200),('DSLR','camera002',NULL,45000.00,100,'canon',200,4,0.00,200),('galaxys2','mob001',NULL,19025.75,100,'samsung',100,4,0.00,100),('galaxynote','mob002',NULL,20525.75,100,'samsung',100,3,0.00,100),('Fastrack','watch001',NULL,1500.00,100,'analog',500,3,0.00,400),('Timex','watch002',NULL,2000.00,100,'digital',600,3,0.00,400);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `addressID` int(11) NOT NULL DEFAULT '0',
  `doorNo` varchar(15) DEFAULT NULL,
  `street` varchar(20) DEFAULT NULL,
  `city` varchar(20) NOT NULL,
  `state` varchar(20) NOT NULL,
  `pincode` decimal(6,0) NOT NULL,
  PRIMARY KEY (`addressID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1001,'6-96','gandhinagar','kalwakurthy','AndhraPradesh',509324),(1002,'5-6-789','gandhinagar','kalwakurthy','andhrapradesh',50932),(1004,'56-8','dsnr','hyd','ap',500004),(1008,'195-7/56','gachibowli','hyderabad','Andhrapradesh',500008),(1009,'//','gandhinagar','kalwakurthy','ap',500089),(2001,'3-98/A','gandhinagar',' hyderabad','AndhraPradesh',500044),(2002,'5-55','dsnr','hyderabad','AndhraPradesh',500004),(51001,'4/89-9','PRnagar','Hyderabad','AndhraPradesh',500016),(51008,'1-1-711/1','GandhiNagar','Hyderabad','AndhraPradesh',500080),(51932,'6-96','Vidyanagar','Kalwakurthy','AndhraPradesh',509324),(53200,'56/8','LakshmiPuran','Guntur','AndhraPradesh',522007),(59963,'589-8','gandhinagar','hyd','ap',589632),(500657,'4-231','GandhiNagar','Hyderabad','AndhraPradesh',500080),(500966,'4/89-9','GandhiNagar','Hyderabad','AndhraPradesh',500080),(509679,'9-78','GandhiNagar','Kalwakurthy','AndhraPradesh',508963);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `userID` varchar(10) DEFAULT NULL,
  `PID` varchar(20) DEFAULT NULL,
  `quantity` decimal(5,0) DEFAULT NULL,
  KEY `userID` (`userID`),
  KEY `PID` (`PID`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`PID`) REFERENCES `product` (`PID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES ('binn9','book001',5);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `name` varchar(20) NOT NULL,
  `userID` varchar(10) NOT NULL DEFAULT '',
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  `phone` decimal(10,0) DEFAULT NULL,
  `addressID` int(11) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `dob` date NOT NULL,
  PRIMARY KEY (`userID`),
  KEY `addressID` (`addressID`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`addressID`) REFERENCES `address` (`addressID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('adepuprash','adep6','a.prashanth18@gmail.com','oukv345',9493568965,500966,'Male','1990-01-14'),('binni','binn9','binni@gmail.com','dkppk4',9494268893,1009,'female','1994-01-24'),('haritha','hari8','haritha207@gmail.com','oukv345',9866441003,51008,'Female','4444-01-04'),('meher','mehe8','meher.almighty@gmail.com','oukv345',9573026690,51008,'Male','1992-01-14'),('meher','meher01','meher@gmail.com','123456',9876543210,2001,'male','1992-01-01'),('nikhila','nikh1','mente.nikhila@gmail.com','pkmjknc24',9493568965,51001,'Female','1990-01-23'),('nikhitha','nikh9','nickyreddy02@gmail.com','oukv345',9494268894,509679,'Female','1992-01-01'),('ravi','ravi01','ravi@gmail.com','123456',9876543210,2002,'male','1992-01-01'),('raviteja','ravi3','singam.999@gmail.com','345678',9866441003,59963,'Male','1989-01-21'),('sriram ','srir3','sriramprasad4u@gmail.com','oukv345',9573026690,59963,'Male','1998-01-04'),('Tapasvi','Tapa0','tapasvi.nallapati@gmail.com','vcrcuxk345',9494268894,53200,'female','1991-01-02'),('tejaswi','teja2','tejusingam@gmail.com','876543',9494247393,51932,'Female','1992-01-02');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetails`
--

DROP TABLE IF EXISTS `orderdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderdetails` (
  `saleid` varchar(10) NOT NULL DEFAULT '',
  `userID` varchar(10) DEFAULT NULL,
  `PID` varchar(20) NOT NULL DEFAULT '',
  `quantity` decimal(5,0) DEFAULT NULL,
  PRIMARY KEY (`saleid`,`PID`),
  KEY `PID` (`PID`),
  KEY `userID` (`userID`),
  CONSTRAINT `orderdetails_ibfk_1` FOREIGN KEY (`PID`) REFERENCES `product` (`PID`),
  CONSTRAINT `orderdetails_ibfk_2` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetails`
--

LOCK TABLES `orderdetails` WRITE;
/*!40000 ALTER TABLE `orderdetails` DISABLE KEYS */;
INSERT INTO `orderdetails` VALUES ('sale001','binn9','book001',2),('sale001','binn9','book002',3),('sale002','binn9','book001',3);
/*!40000 ALTER TABLE `orderdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registration` (
  `userID` varchar(10) NOT NULL,
  `tempCode` decimal(10,0) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  KEY `userID` (`userID`),
  CONSTRAINT `registration_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration`
--

LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` VALUES ('Tapa0',793761,'not-activated'),('ravi3',193240,'not-activated'),('nikh1',506024,'not-activated'),('teja2',469250,'not-activated'),('mehe8',291176,'active'),('srir3',209121,'not-activated'),('hari8',772881,'active'),('adep6',743519,'active'),('nikh9',366705,'active');
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bills`
--

DROP TABLE IF EXISTS `bills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bills` (
  `timeStamp` datetime DEFAULT NULL,
  `addressID` int(11) DEFAULT NULL,
  `saleid` varchar(10) DEFAULT NULL,
  `totalamount` decimal(10,2) NOT NULL,
  `DeliveryStatus` varchar(20) NOT NULL,
  KEY `saleid` (`saleid`),
  KEY `addressID` (`addressID`),
  CONSTRAINT `bills_ibfk_1` FOREIGN KEY (`saleid`) REFERENCES `orderdetails` (`saleid`),
  CONSTRAINT `bills_ibfk_2` FOREIGN KEY (`addressID`) REFERENCES `address` (`addressID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bills`
--

LOCK TABLES `bills` WRITE;
/*!40000 ALTER TABLE `bills` DISABLE KEYS */;
INSERT INTO `bills` VALUES ('1992-01-01 00:00:00',1001,'sale001',375.00,'pending'),('2013-02-02 00:00:00',1001,'sale002',400.00,'delivered');
/*!40000 ALTER TABLE `bills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manufacturer`
--

DROP TABLE IF EXISTS `manufacturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manufacturer` (
  `manufactureID` int(11) NOT NULL DEFAULT '0',
  `manufactureName` varchar(20) NOT NULL,
  `description` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`manufactureID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufacturer`
--

LOCK TABLES `manufacturer` WRITE;
/*!40000 ALTER TABLE `manufacturer` DISABLE KEYS */;
INSERT INTO `manufacturer` VALUES (100,'samsung','Samsung is one of the biggest brand'),(147,'LG','GoogleAssosciation'),(200,'canon','famous camera manufacturer'),(206,'fastrack','watches,bags'),(210,'Oriflame','swedenGroup'),(213,'j.K.rowling','author'),(218,'liviya','bags'),(232,'Asus','Multinational'),(300,'tmh','book publisher'),(400,'oreilly','american book publisher'),(500,'fastrack','India\'s largest fashion brand'),(600,'timex','best rated watches'),(1209,'Nikhitha','Nikhitha');
/*!40000 ALTER TABLE `manufacturer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-02-10 19:32:38
