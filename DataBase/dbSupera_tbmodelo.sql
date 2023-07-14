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
-- Table structure for table `tbmodelo`
--

DROP TABLE IF EXISTS `tbmodelo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbmodelo` (
  `idModel` int NOT NULL AUTO_INCREMENT,
  `model` varchar(50) NOT NULL,
  `mem` varchar(50) NOT NULL,
  `mBoard` varchar(50) NOT NULL,
  `expansao` varchar(50) DEFAULT 'Sem expansões',
  `armazenaTipo` varchar(10) DEFAULT 'USB Flash',
  `armazenaModel` varchar(50) NOT NULL,
  `fonteAlimenta` varchar(50) NOT NULL,
  `sParalela` varchar(5) DEFAULT '0',
  `sSerial` varchar(5) DEFAULT '1',
  `redeLan` varchar(50) DEFAULT 'onBoard',
  `wifi` varchar(50) DEFAULT 'nao',
  `tipo` varchar(50) DEFAULT 'ThinClient',
  `processador` varchar(50) NOT NULL,
  `gabinete` varchar(50) NOT NULL,
  PRIMARY KEY (`idModel`),
  UNIQUE KEY `model` (`model`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbmodelo`
--

LOCK TABLES `tbmodelo` WRITE;
/*!40000 ALTER TABLE `tbmodelo` DISABLE KEYS */;
INSERT INTO `tbmodelo` VALUES (1,'SP4218S Plus','Super Talent 1GB DDR3 1066MHz SO-DIMM','Intel D425KT',NULL,NULL,'Flash USB Super Talent 4GB','Mini Box 80W','1','1','onboard','não','ThinClient','Intel Atom D425','ABS Preto V1'),(2,'SPD18G1','Markvision 2GB DDR3 1333MHz SO-DIMM','PCWare IPX1800G1',NULL,NULL,'SATA DOM BIWIN D6210 8GB','Mini Box 80W','0','1','onboard','não','ThinClient','Intel Celeron J1800','ABS Verde Painel P'),(3,'SP25HN','Super Talent 2GB DDR3 1066MHz SO-DIMM','Intel D2500HN',NULL,NULL,'Flash USB Super Talent 4GB','Mini Box 80W','1','1','onboard','não','ThinClient','Intel Atom D2500','ABS Preto V1'),(4,'2TCSD10','Kingston 2GB DDR3 1600MHz SO-DIMM','PCWare IPX1800G2',NULL,NULL,'SATA DOM BIWIN D6210 8GB','Mini Box 90W','0','1','onboard','não','ThinClient','Intel Celeron J1800','ABS PRETO V1'),(5,'SPDG84E1','Kingston 2GB DDR3 1333MHz SO-DIMM','PCWare IPX847E1',NULL,NULL,'Flash USB SMI 8GB','Mini Box 80W','0','1','onboard','não','ThinClient','Intel Celeron 847','ABS PRETO V1'),(6,'SUPERA D410PTL','Super Talent 1GB DDR2 800Mhz DIMM','Intel D410PTL(B)',NULL,NULL,'SATA DOM BIWIN D6210 8GB','Mini Box 90W - Amarelo','1','1','onboard','não','ThinClient','Intel Atom D410','ABS PRETO V1'),(7,'SP4116S PLUS','Super Talent 1GB DDR2 800Mhz DIMM','Intel D410PTL(B)',NULL,NULL,'Flash USB Super Talent 4GB','Mini Box 90W - Amarelo','1','1','onboard','não','ThinClient','Intel Atom D410','ABS PRETO V1'),(8,'SUPERA D945GCLF2D','Super Talent 1GB DDR2 533MHz','Intel D945GCLF2D',NULL,NULL,'SATA DOM BIWIN D6210 8GB','Mini Box 90W - Amarelo','1','1','onboard','não','ThinClient','Intel Atom 330','ABS PRETO V1'),(9,'Desktop Izei Pop 4020e Mini','DDR4 - 8Gb','PCWARE 4020e',NULL,NULL,'120GB SSD','Pico','0','1','onboard','não','ThinClient','Intel Celeron N2040','Metalico  - KMex'),(10,'2TPSSD10','Kingston 2GB DDR3 1600MHz SO-DIMM','PCWare IPX1800E2',NULL,NULL,'SSD Teikon Half Slim 16GB','Mini Box 90W','0','1','onboard','não','ThinClient','Intel Celeron J1800','ABS PRETO V1');
/*!40000 ALTER TABLE `tbmodelo` ENABLE KEYS */;
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
