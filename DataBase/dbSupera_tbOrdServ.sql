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
-- Table structure for table `tbOrdServ`
--

DROP TABLE IF EXISTS `tbOrdServ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbOrdServ` (
  `idOrdServ` varchar(10) NOT NULL,
  `idcli` varchar(20) NOT NULL,
  `dataAbertura` date NOT NULL,
  `dataFechamento` date DEFAULT NULL,
  `garantia` tinyint(1) DEFAULT '0',
  `aberta` tinyint(1) DEFAULT '1',
  `tecnico` varchar(30) NOT NULL,
  `valor` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`idOrdServ`),
  KEY `idcli` (`idcli`),
  CONSTRAINT `tbOrdServ_ibfk_1` FOREIGN KEY (`idcli`) REFERENCES `tbclientes` (`idcli`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbOrdServ`
--

LOCK TABLES `tbOrdServ` WRITE;
/*!40000 ALTER TABLE `tbOrdServ` DISABLE KEYS */;
INSERT INTO `tbOrdServ` VALUES ('1','1','2023-06-30','2023-07-11',0,0,'Tecnico','0'),('11072023','1','2023-07-11','2023-07-12',0,0,'Administrador',''),('27986','86.445.822/0005-25','2023-07-10','2023-07-11',0,0,'Tecnico','0'),('28040','435742','2023-07-07','2023-07-10',0,1,'Administrador','0'),('3072023','1','2023-07-03','2023-07-11',0,0,'Tecnico','0'),('6072023','1','2023-07-06','2023-07-11',0,0,'Administrador','0');
/*!40000 ALTER TABLE `tbOrdServ` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-14 17:14:02
