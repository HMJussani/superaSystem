-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 192.168.100.127    Database: dbSupera
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
  `armazenaModel` varchar(50) NOT NULL,
  `fonteAlimenta` varchar(50) NOT NULL,
  `sParalela` varchar(5) DEFAULT '0',
  `sSerial` varchar(5) DEFAULT '1',
  `redeLan` varchar(50) DEFAULT 'onBoard',
  `wifi` varchar(50) DEFAULT 'nao',
  `tipo` varchar(50) DEFAULT 'ThinClient',
  `processador` varchar(50) NOT NULL,
  `gabinete` varchar(50) NOT NULL,
  `obsoleto` tinyint(1) DEFAULT '0',
  `painel` varchar(50) DEFAULT NULL,
  `memtipo` varchar(30) DEFAULT NULL,
  `armazenaTipo` varchar(20) DEFAULT NULL,
  `so` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idModel`),
  UNIQUE KEY `model` (`model`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbmodelo`
--

LOCK TABLES `tbmodelo` WRITE;
/*!40000 ALTER TABLE `tbmodelo` DISABLE KEYS */;
INSERT INTO `tbmodelo` VALUES (1,'SP4218S Plus','Super Talent 1GB  1066MHz SO-DIMM','Intel D425KT',NULL,'Super Talent 4GB','Mini Box 80W','1','1','onboard','não','ThinClient','Intel Atom D425','ABS Preto V1',1,'aço inox','DDR3','Flash USB ',NULL),(2,'SPD18G1','Markvision 2GB  1333MHz SO-DIMM','PCWare IPX1800G1',NULL,' BIWIN D6210 8GB','Mini Box 80W','0','1','onboard','não','ThinClient','Intel Celeron J1800','ABS Verde Painel P',0,'Perfil P  Supera','DDR3','SATA DOM',NULL),(3,'SP25HN','Super Talent 2GB  1066MHz SO-DIMM','Intel D2500HN',NULL,' Super Talent 4GB','Mini Box 80W','1','1','onboard','não','ThinClient','Intel Atom D2500','ABS Preto V1',1,'Aço Inox','DDR3','Flash USB',NULL),(4,'2TCSD10','Kingston 2GB  1600MHz SO-DIMM','PCWare IPX1800G2','Sem Expansões',' BIWIN D6210 8GB','Mini Box 90W','0','1','onboard','não','ThinClient','Intel Celeron J1800','ABS PRETO V1',0,'Perfil P Supera','DDR3','SATA DOM',NULL),(5,'SPDG84E1','Kingston 2GB  1333MHz SO-DIMM','PCWare IPX847E1',NULL,' SMI 8GB','Mini Box 80W','0','1','onboard','não','ThinClient','Intel Celeron 847','ABS PRETO V1',1,'Aço Inox','DDR3','Flash USB',NULL),(6,'SUPERA D410PTL','Super Talent 1GB  800Mhz DIMM','Intel D410PTL(B)',NULL,'BIWIN D6210 8GB','Mini Box 90W - Amarelo','1','1','onboard','não','ThinClient','Intel Atom D410','ABS PRETO V1',1,'Aço Inox','DDR2','SATA DOM ',NULL),(7,'SP4116S PLUS','Super Talent 1GB  800Mhz DIMM','Intel D410PTL(B)',NULL,' Super Talent 4GB','Mini Box 90W - Amarelo','1','1','onboard','não','ThinClient','Intel Atom D410','ABS PRETO V1',1,'Aço Inox','DDR2','Flash USB',NULL),(8,'SUPERA D945GCLF2D','Super Talent 1GB  533MHz','Intel D945GCLF2D',NULL,' BIWIN D6210 8GB','Mini Box 90W - Amarelo','1','1','onboard','não','ThinClient','Intel Atom 330','ABS PRETO V1',1,'Aço Inox','DDR2','SATA DOM',NULL),(9,'Desktop Izei Pop 4020e Mini','NTC - 8Gb','PCWARE 4020e',NULL,'NTCF6M 120GB','IOASPOW IDD-DIP ver:2.0 120W','0','1','onboard','não','ThinClient','Intel Celeron N2040','Metalico  - KMex',0,'KMex','DDR4',' SSD M2',NULL),(10,'2TPSSD10','Kingston 2GB D 1600MHz SO-DIMM','PCWare IPX1800E2',NULL,'Teikon  16GB','Mini Box 90W','0','1','onboard','não','ThinClient','Intel Celeron J1800','ABS PRETO V1',0,'Perfil P Supera','DR3','SSD Half Slim',NULL),(11,'4TPSSD30','Markvision 2GB  1333MHz SO-DIMM','PCWare IPX1800E2','Sem Expansões','Axis 400 30GB','Lote  5050','1','1','onboard','não','ThinClient','Intel Celeron J1800','ABS PRETO V2',0,'Perfil M NanoPc','DDR3','SSD 2.5','Windows 10 Pro - original de fabrica'),(12,'Popi2743-001','NTC RAM -  4Gb 1600mHZ 1,35V','Asus J1800C/BR',NULL,'NTCF6S 120GB SSD','IOASPOW IDD-DIPS ver:1.1 120W','0','0','onboard','não','ThinClient','Intel Celeron J1800','ABS Preto - Perfil Medio',0,'','DDR3','SSD 2.5',NULL),(13,'Coamo ABS 4020e','Hikvision  2666 SODIM - 4Gb','PCWARE 4020e','Sem Expansões','NTCF6M 128GB ','IOASPOW IDD-DIPS ver:1.1 120W','1','1','onboard','não','ThinClient','Intel Celeron N2040','ABS Preto V2 - Perfil Médio',0,'Perfil M com botão antivandalismo 16mm','DDR4','SSD M2',' Linux (personalizado pelo cliente)'),(14,'R-2TCSD10','NTC 4GB 1600MHz SO-DIMM','PCWare IPX1800G2',NULL,'Hikvision 120GB','IOASPOW IDD-DIPS ver:1.1 120W','1','1','onboard','não','ThinClient','Intel Celeron J1800','ABS Verde V2 - Perfil Medio',0,'','DDR3 ','SSD 2.5',NULL),(15,'SP5116D PLUS','Super Talent 1GB  800Mhz DIMM','Intel D510MO(B)',NULL,'BIWIN D6210 8GB','Mini Box 90W - Amarelo','1','1','onboard','não','ThinClient','Intel Atom D510','ABS PRETO V1',1,'Aço Inox','DDR2','SATA DOM ',NULL),(16,'2TPASSD30','BlueCase 2GB  1333MHz 1,35v SO-DIMM','PCWare IPX1800E2',NULL,' BlueCase 32GB','Lote  5050','1','1','onboard','não','ThinClient','Intel Celeron J1800','ABS Verde V2',0,'','DDR3L','SSD Sata III',NULL),(17,'4TPSSD40','Multilaser 4GB   SO-DIMM','PCWare IPX1800E2','Sem Expansões',' WinMemory 64GB','IOASPOW IDD-DIP ver:2.0 120W','0','0','onboard','não','ThinClient','Intel Celeron J1800','ABS PRETO V2',0,'Perfil M NanoPc','DDR3L','SSD 2.5','Windows 10 Pro - original de fabrica'),(18,'Desktop Izei Pop 4120e Mini','NTC - 8Gb','PCWARE 4120e','Sem Expansões','NTCF6M 120GB','IOASPOW IDD-DIP ver:2.0 120W','0','0','onboard','WiFi Dual band 2,4/5 GHz','ThinClient','Intel Celeron N4120','Metalico  - KMex',0,'KMex','DDR4',' SSD M2','Windows 10 Pro'),(19,'Desktop Izei Pop 1800b Mini ','BLUECASE 4BG UDIMM 1600MHZ','BlueCase PM J1900C V1','Sem Expansões','NTCF6S 120GB','IOASPOW IDD-DIPS ver:1.1 120W','0','1','onboard','não','ThinClient','Intel Celeron J180','Kmex Metálico',0,'não','DDR3','SSD 2.5',''),(20,'POPI+2743-002','8GB ','ASUS J1800I-C/BR MINI-ITX','Sem Expansões','NTCF6M 128GB ','IOASPOW IDD-DIPS ver:1.1 120W','0','1','onboard','PCIe WIFI Dual Band AC1300 ARCHER T6E','ThinClient','Intel Celeron J180','metalico - Kmex ',0,'','DDR3','M2','Sem Sistema'),(21,'POPD610MEW0P01 ','4BG SO-DIMM 1600MHZ','AsRock J4005DC-ITX ','Realtek  RTL8111E GgE LAN ','NTCF6S 120GB','Integrada','0','2','onboard','não','ThinClient','Intel® Celeron J4025','Kmex Metálico',0,'não','DDR4','SSD 2.5','Windows 10 Pro'),(22,'2TPSSD20','BlueCase 2GB  1333MHz 1,35v SO-DIMM','ECS BAT-I V1.2','Sem Expansões','Multilaser 30Gb','Mini Box 90W','1','1','onboard','não','ThinClient','Intel  Bay Trail J1800','ABS Verde  V2',0,'Perfil P ThinAge','DR3','SSD 2.5','Linux ');
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

-- Dump completed on 2023-10-27 17:23:02
