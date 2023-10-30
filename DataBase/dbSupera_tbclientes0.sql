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
-- Table structure for table `tbclientes`
--

DROP TABLE IF EXISTS `tbclientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbclientes` (
  `idcli` varchar(20) NOT NULL,
  `nomecli` varchar(100) NOT NULL,
  `contatocli` varchar(50) NOT NULL,
  `endcli` varchar(100) NOT NULL,
  `telcli` varchar(20) DEFAULT NULL,
  `emailCli` varchar(50) NOT NULL DEFAULT 'contato@contato.com',
  `cidadeCli` varchar(50) NOT NULL DEFAULT 'Maringá',
  `estadoCli` varchar(2) NOT NULL DEFAULT 'PR',
  PRIMARY KEY (`idcli`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbclientes`
--

LOCK TABLES `tbclientes` WRITE;
/*!40000 ALTER TABLE `tbclientes` DISABLE KEYS */;
INSERT INTO `tbclientes` VALUES ('1','Descarte','Descarte','Rua do descarte, 123','4430304455','descarte@descarte.com','Maringá','PR'),('1142',' TUPER S/A  FILIALTOG','Kleber','RUA PREFEITO ORNITH BOLLMANN,1441','(47)3631-5000','xml.tubos@tuper.com.br','São Bento do Sul','RS'),('134',' MERCADOMOVEIS LTDA','RENAN','RUA VICTOR MEIRELES,288',' (42) 3220-6074','renan.rosa@lojasmm.com.br','Ponta Grossa','PR'),('2265','Rudolph Usinados S/A',' Rosileide Steffens','Rua Pe. Martinho Stein,2661 ','(47) 3281-2800','rosileide@rudolph.com.br','Timbó','SC'),('227','ZAIRAM AGROCOMM ASSES COR IMP EXPORT','Ana Lucena','AV AD HORACIO RACCANELLO FILHO,5570','(44) 3027-3900','ana@zairam.com.br','Maringá','PR'),('2808','EMPLACA AUTOMACAO E TECNOLOGIA LTDA','Luciano','RUA RAPHAEL DE MARCO,142','(11) 4788-7770','lcardoso@emplaca.com.br','Taboao da Serra','SP'),('288','SGS GEOSOL LABORATORIOS LTDA','Susan','RODOVIA MG 010 - KM 24,5,S/N','(31) 3045-0234','susan.cordeiro@sgsgeosol.com.br','Vespasiano','MG'),('2954','GBS Informática LTDA','Flavio','Rua Jose Bianch, 555','16 34469844','flavio.faria@gbsinformatica.com.br','Ribeirao Preto','SP'),('3579','Centro Endosc Prof. Akira Nakadaira Ltda','AGEU DUARTE JUNIOR','AVENIDA VICENTE FERREIRA,828','(14)3402-5555','ageu@adjsistemas.com.br','MARILIA ','SP'),('435742','Coamo','Rose','Campo Mourão','4435998000','rsabotto@coamo.com.br','Campo Mourão','PR'),('4639','CHAPEMEC INDUSTRIA METALURGICA LTDA','Maicon','ROD RS-344,2090','(55) 3513-5600','CHAPEMEC@CHAPEMEC.COM.BR','Santa Rosa','RS'),('5048','Tomasoni Industria de Máquinas LTDA.','Ulisses Janses','Rua Wanda dos Santos Mullmann, 1604','41 3667-2063','','Pinhais','PR'),('5089','UNIMED CRICIUMA COOPERATIVA TRABALHO MEDICO','  Osvaldo Cardoso do Canto','RUA VITAL BRASIL,455','(48) 3461-6100','osvaldo@criciuma.unimedsc.com.br','Maringá','PR'),('5261','Brame desenvolvimento e fabricacao de tecnologias ltda','Giuliano','ROD JOSE CARLOS DAUX,600','(48) 3024-6077','giuliano@brametec.com.br','Florianopolis','SC'),('5329','CEDIA TERAPIA APLICADA A INFANCIA LTDA','FABIO BASSO','R EURILEMOS,361, Centro','(43) 3316-0469','fabio@apoenatec.com.br','Arapongas','PR'),('533','Euro Administradora de Condominios LTDA.','Renato Gimenes de Oliveira','AV Duque de Caxias, 882','(44) 30139400 ','financeiro1@euro.adm.br','Maringá','PR'),('5331',' B.DROPS S.A.','Jonathan','R JERONIMO DA VEIGA,164','(11) 3078-0879','jonathan.silva@bdrops.tv','São Paulo','SP'),('86.445.822/0005-25','COPOBRAS S/A. IND COM EMBALAGENS','SIDNEI','ROD BR 376 KM 198 CONTORNO SUL','44 32328080','sidnei@copobras.com.br','Marialva','PR');
/*!40000 ALTER TABLE `tbclientes` ENABLE KEYS */;
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
