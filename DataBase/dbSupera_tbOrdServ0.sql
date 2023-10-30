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
  `aberta` tinyint(1) DEFAULT '1',
  `tecnico` varchar(30) NOT NULL,
  `valor` varchar(10) DEFAULT NULL,
  `defeito` varchar(256) DEFAULT 'Verificar.',
  `solucao` varchar(256) DEFAULT 'Verificar.',
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
INSERT INTO `tbOrdServ` VALUES ('11072023','1','2023-07-11','2023-07-12',0,'Administrador','','Verificar.','Verificar.'),('17072023','1','2023-07-17','2023-07-17',0,'Administrador','','Verificar.','Verificar.'),('20072023','1','2023-07-20','2023-08-04',1,'Tecnico','','Verificar.','Verificar.'),('20823','435742','2023-08-02','2023-08-02',0,'Tecnico','','Verificar.','Verificar.'),('25658','134','2023-08-10','2023-08-10',1,'Tecnico','0','Montagem de  equipamento novo.','Montagem de  equipamento novo.'),('27904','435742','2023-07-18','2023-07-24',0,'Administrador','','Verificar.','Verificar.'),('27905','435742','2023-07-19','2023-07-24',0,'Tecnico','','Verificar.','Verificar.'),('27986','86.445.822/0005-25','2023-07-10','2023-07-11',0,'Tecnico','0','Verificar.','Verificar.'),('28040','435742','2023-07-07','2023-08-02',0,'Administrador','0','Verificar.','Verificar.'),('28133','4639','2023-08-03','2023-08-07',1,'Henrique Marega Jussani','','Montagem de 05 equipamentos novos.','Montagem de 05 equipamentos novos.'),('28186','3579','2023-08-08','2023-08-08',1,'Henrique Marega Jussani','','Montagem de 03 equipamentos.','verificar'),('28213','5048','2023-07-18','2023-07-20',0,'Administrador','','Verificar.','Verificar.'),('28219','2808','2023-08-04','2023-09-04',1,'Henrique Marega Jussani','','Montagem de 02 equipamentos novos.','Montagem de 02 equipamentos novos'),('28235','435742','2023-07-21','2023-08-30',1,'Administrador','','Verificar.','Verificar.'),('28236','435742','2023-07-21','2023-07-21',1,'Administrador','','Verificar.','Verificar.'),('28238','5329','2023-08-01','2023-08-14',1,'Henrique Marega Jussani','','Verificar.','Verificar.'),('28264','5331','2023-08-03','2023-08-03',0,'Henrique Marega Jussani','','Verificar.','Verificar.'),('28280','5089','2023-08-02','2023-08-02',1,'Henrique Marega Jussani','','Verificar.','Verificar.'),('28281','5089','2023-08-03','2023-08-03',1,'Henrique Marega Jussani','','Montagem de equipamento novo.','Montagem de equipamento novo.'),('28293','2954','2023-07-28','2023-08-02',0,'Henrique Marega Jussani','','Verificar.','Verificar.'),('28416','86.445.822/0005-25','2023-08-21','2023-08-21',1,'Henrique Marega Jussani','','Verificar','verificar'),('28419','288','2023-09-05','2023-09-05',1,'Henrique Marega Jussani','0','Verificar','verificar'),('28456','435742','2023-09-12','2023-09-12',1,'Henrique Marega Jussani','0','Manutenção e Upgrade de 12 equipamentos.','Manutenção e Upgrade de 12 equipamentos.'),('28457','435742','2023-08-30','2023-08-30',1,'Tecnico','0','Verificar','verificar'),('28458','435742','2023-08-31','2023-08-31',1,'Henrique Marega Jussani','0','Verificar','verificar'),('28459','1142','2023-09-01','2023-09-01',1,'Administrador','0','Verificar','verificar'),('28462','2808','2023-09-04','2023-09-04',1,'Henrique Marega Jussani','','Verificar','verificar'),('28469','2808','2023-09-04','2023-09-04',1,'Henrique Marega Jussani','','Verificar','verificar'),('28535','2265','2023-09-26','2023-09-26',1,'Henrique Marega Jussani','0','Montagem de equipamento.','Montagem de equipamento.'),('28540','227','2023-09-15','2023-09-18',1,'Henrique Marega Jussani','0','Verificar','verificar'),('28726','435742','2023-10-27','2023-10-27',1,'Henrique Marega Jussani','0','Verificar','verificar'),('28730','533','2023-10-27','2023-10-27',1,'Tecnico','0','Verificar','verificar'),('3072023','1','2023-07-03','2023-07-11',0,'Tecnico','0','Verificar.','Verificar.'),('6072023','1','2023-07-06','2023-07-11',0,'Administrador','0','Verificar.','Verificar.');
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

-- Dump completed on 2023-10-27 17:23:02
