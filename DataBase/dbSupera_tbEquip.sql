-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 192.168.100.101    Database: dbSupera
-- ------------------------------------------------------
-- Server version	8.0.33-0ubuntu0.20.04.2

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
-- Table structure for table `tbEquip`
--

DROP TABLE IF EXISTS `tbEquip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbEquip` (
  `nserie` varchar(20) NOT NULL,
  `idOrdServ` varchar(10) NOT NULL,
  `model` varchar(50) NOT NULL,
  `patEquip` varchar(20) NOT NULL,
  `idcli` varchar(20) NOT NULL,
  `garantia` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`nserie`),
  KEY `model` (`model`),
  KEY `idOrdServ` (`idOrdServ`),
  KEY `idcli` (`idcli`),
  CONSTRAINT `tbEquip_ibfk_1` FOREIGN KEY (`model`) REFERENCES `tbmodelo` (`model`),
  CONSTRAINT `tbEquip_ibfk_2` FOREIGN KEY (`idOrdServ`) REFERENCES `tbOrdServ` (`idOrdServ`),
  CONSTRAINT `tbEquip_ibfk_3` FOREIGN KEY (`idcli`) REFERENCES `tbclientes` (`idcli`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbEquip`
--

LOCK TABLES `tbEquip` WRITE;
/*!40000 ALTER TABLE `tbEquip` DISABLE KEYS */;
INSERT INTO `tbEquip` VALUES ('10366','28040','SUPERA D945GCLF2D','61763','435742',0),('11330','6072023','SP4218S Plus','sem pat','1',0),('11512','11072023','SP4218S Plus',' sem pat','1',0),('14146','28040','SP25HN','80110','435742',0),('14181','6072023','SP25HN','sem pat','1',0),('14229','28040','2TCSD10','80111','435742',0),('16551','28040','SPDG84E1','86649','435742',0),('17624','28040','SPD18G1','89707','435742',0),('18174','3072023','SPD18G1','sem pat','1',0),('18576','28040','SPD18G1','90961','435742',0),('18630','28040','SPD18G1','91100','435742',0),('18646','11072023','SPD18G1','sem pat','1',0),('18658','3072023','SPD18G1','sem pat','1',0),('18829','11072023','SPD18G1','91340','1',0),('27912','28040','2TPSSD10','123256','435742',0),('34280','27986','Desktop Izei Pop 4020e Mini','SEM Pat','86.445.822/0005-25',0),('34281','27986','Desktop Izei Pop 4020e Mini','SEM Pat','86.445.822/0005-25',0),('6306','28040','SUPERA D410PTL','67080','435742',0),('6346','11072023','SUPERA D410PTL',' sem pat','1',0),('6374','11072023','SUPERA D410PTL',' sem pat','1',0),('6375','11072023','SUPERA D410PTL',' sem pat','1',0),('7506','28040','SP4116S PLUS','68860','435742',0),('8528','11072023','SP4116S PLUS',' sem pat','1',0),('9498','11072023','2TPSSD10','sem pat','1',0),('9502','28040','2TCSD10','72466','435742',0),('9581','11072023','SP4218S Plus',' sem pat','1',0),('D-16551','6072023','SPDG84E1','ex-coamo','1',0),('teste','6072023','Desktop Izei Pop 4020e Mini','sem pat','1',1);
/*!40000 ALTER TABLE `tbEquip` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-14 17:14:03
