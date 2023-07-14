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
-- Table structure for table `tbdefsol`
--

DROP TABLE IF EXISTS `tbdefsol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbdefsol` (
  `idDefeito` int NOT NULL AUTO_INCREMENT,
  `nserie` varchar(20) DEFAULT NULL,
  `defeito` varchar(100) DEFAULT 'Verificar.',
  `solucao` varchar(100) DEFAULT 'Verificar.',
  PRIMARY KEY (`idDefeito`),
  KEY `nserie` (`nserie`),
  CONSTRAINT `tbdefsol_ibfk_1` FOREIGN KEY (`nserie`) REFERENCES `tbEquip` (`nserie`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbdefsol`
--

LOCK TABLES `tbdefsol` WRITE;
/*!40000 ALTER TABLE `tbdefsol` DISABLE KEYS */;
INSERT INTO `tbdefsol` VALUES (1,'11330','Obsoleto','Descarte'),(2,'14181','Obsoleto','Descarte'),(3,'34280','Montagem de Equipamento.','Montagem de Equipamento.'),(4,'34281','Montagem de Equipamento.','Montagem de Equipamento.'),(7,'10366','Desligando sozinho.	','Substituição de equipamento completo.'),(8,'14146','NAO LIGA.','Substituição de equipamento completo.'),(9,'14229','não dá VIDEO.','Substituição de equipamento completo.'),(10,'16551','Erro de FLASH.','Equipamento obsoleto. Substituição de equipamento completo.'),(11,'17624','LENTO.','Flash USB padrão substituir por SSD 2.5\".'),(12,'18576','Erro de FLASH.','Flash USB padrão substituir por SSD 2.5\".'),(13,'18630','NAO LIGA.','Flash USB padrão substituir por SSD 2.5\".'),(14,'27912','DESLIGA SOZINHO.','A placa mãe não liga. Substituir placa mãe.  Substituir SSD Half Slim por SSD 2.5\".'),(15,'6306','NAO LIGA','Substituição de equipamento completo.'),(16,'7506','LENTO.','Substituição de equipamento completo.'),(17,'9502','Não dá VIDEO.','A placa mãe não liga. Substituir placa mãe.  Substituir SSD Half Slim por SSD 2.5\".'),(18,'27912','DESLIGA SOZINHO.','A placa mãe não liga.  Substituir placa mãe.  Substituir SSD Half Slim por SSD 2.5\".'),(20,'9502','Não dá Video.','A placa mãe não liga. Substituir placa mãe.  Substituir SSD Half Slim por SSD 2.5\".'),(21,'teste','Obsoleto, descartar. ','Desmontado e descartado.'),(22,'11512','Descartar.','Desmontado e descartado.'),(23,'6346','Obsoleto, descartar.','Desmontado e descartado.'),(24,'6375','Obsoleto, descartar.','Desmontado e descartado.'),(25,'9581','Obsoleto, descartar.','Desmontado e descartado.'),(26,'6374','Obsoleto, descartar.','Desmontado e descartado.'),(27,'8528','Obsoleto, descartar.','Desmontado e descartado.'),(28,'18829','Obsoleto, descartar. ','Desmontado e descartado.'),(29,'18646','Obsoleto, descartar.','Desmontado e descartado.'),(30,'9498','Obsoleto, descartar.','Desmontado e descartado..');
/*!40000 ALTER TABLE `tbdefsol` ENABLE KEYS */;
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
