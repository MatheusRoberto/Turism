-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: turism
-- ------------------------------------------------------
-- Server version	5.7.17-0ubuntu0.16.04.1

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
-- Table structure for table `assento`
--

DROP TABLE IF EXISTS `assento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assento` (
  `idassento` int(11) NOT NULL,
  `idcontrato` int(11) NOT NULL,
  `idveiculo` int(11) NOT NULL,
  `nome` varchar(90) DEFAULT NULL,
  `rg` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`idassento`,`idcontrato`,`idveiculo`),
  KEY `fk_assento_contrato1_idx` (`idcontrato`),
  KEY `fk_assento_veiculo1_idx` (`idveiculo`),
  CONSTRAINT `fk_assento_contrato1_idx` FOREIGN KEY (`idcontrato`) REFERENCES `contrato` (`idcontrato`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_assento_veiculo1_idx` FOREIGN KEY (`idveiculo`) REFERENCES `veiculo` (`idveiculo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assento`
--

LOCK TABLES `assento` WRITE;
/*!40000 ALTER TABLE `assento` DISABLE KEYS */;
INSERT INTO `assento` VALUES (1,4,2,'Matheus Aparecido da Silva Roberto','41.462.496-8'),(2,4,2,'Matheus Aparecido da Silva Roberto','41.462.496-8'),(3,5,2,'Jairo Aparecido Roberto','11.425.937-7'),(4,5,2,'Jairo Aparecido Roberto','11.425.937-7'),(5,5,2,'Jairo Aparecido Roberto','11.425.937-7'),(6,5,2,'Jairo Aparecido Roberto','11.425.937-7');
/*!40000 ALTER TABLE `assento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cidade`
--

DROP TABLE IF EXISTS `cidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cidade` (
  `idcidade` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(75) DEFAULT NULL,
  `idestado` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcidade`),
  KEY `fk_cidade_estado1_idx` (`idestado`),
  CONSTRAINT `fk_cidade_estado1` FOREIGN KEY (`idestado`) REFERENCES `estado` (`idestado`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cidade`
--

LOCK TABLES `cidade` WRITE;
/*!40000 ALTER TABLE `cidade` DISABLE KEYS */;
INSERT INTO `cidade` VALUES (1,'Arandu',1),(2,'Aparecida',1),(3,'Rio de Janeiro',2),(4,'Avaré',1);
/*!40000 ALTER TABLE `cidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `idcliente` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(90) DEFAULT NULL,
  `rg` varchar(15) DEFAULT NULL,
  `telefone` varchar(20) NOT NULL,
  PRIMARY KEY (`idcliente`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Matheus Aparecido da Silva Roberto','41.462.496-8','(42) 99837-1383'),(2,'Jairo Aparecido Roberto','11.425.937-7',''),(3,'Valdeli Aparecida da Silva Roberto','21.435.937-8','');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuracoes`
--

DROP TABLE IF EXISTS `configuracoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuracoes` (
  `idorigem` int(11) DEFAULT NULL,
  `databackup` date DEFAULT NULL,
  `agendadostatusbackup` tinyint(1) DEFAULT '0',
  `idconfiguracao` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idconfiguracao`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuracoes`
--

LOCK TABLES `configuracoes` WRITE;
/*!40000 ALTER TABLE `configuracoes` DISABLE KEYS */;
INSERT INTO `configuracoes` VALUES (0,'2017-02-12',1,1);
/*!40000 ALTER TABLE `configuracoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contrato`
--

DROP TABLE IF EXISTS `contrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contrato` (
  `idcontrato` int(11) NOT NULL AUTO_INCREMENT,
  `idforma` int(11) DEFAULT NULL,
  `idcliente` int(11) DEFAULT NULL,
  `idusuario` int(11) DEFAULT NULL,
  `idviagem` int(11) DEFAULT NULL,
  `dataemissao` date DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `paga` tinyint(1) DEFAULT NULL,
  `desconto` double DEFAULT '0',
  PRIMARY KEY (`idcontrato`),
  KEY `fk_contrato_formapagamento1_idx` (`idforma`),
  KEY `fk_contrato_cliente1_idx` (`idcliente`),
  KEY `fk_contrato_usuario1_idx` (`idusuario`),
  KEY `fk_contrato_viagem1_idx` (`idviagem`),
  CONSTRAINT `fk_contrato_cliente1` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`idcliente`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_contrato_formapagamento1` FOREIGN KEY (`idforma`) REFERENCES `formapagamento` (`idformapagamento`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_contrato_usuario1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_contrato_viagem1` FOREIGN KEY (`idviagem`) REFERENCES `viagem` (`idviagem`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contrato`
--

LOCK TABLES `contrato` WRITE;
/*!40000 ALTER TABLE `contrato` DISABLE KEYS */;
INSERT INTO `contrato` VALUES (4,1,1,1,6,'2017-01-31',2,0,0),(5,1,2,1,6,'2017-01-31',4,0,0);
/*!40000 ALTER TABLE `contrato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `depedente`
--

DROP TABLE IF EXISTS `depedente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `depedente` (
  `iddepedente` int(11) NOT NULL AUTO_INCREMENT,
  `idcliente` int(11) DEFAULT NULL,
  `nome` varchar(90) DEFAULT NULL,
  `rg` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`iddepedente`),
  KEY `fk_depedente_cliente1_idx` (`idcliente`),
  CONSTRAINT `fk_depedente_cliente1` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`idcliente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `depedente`
--

LOCK TABLES `depedente` WRITE;
/*!40000 ALTER TABLE `depedente` DISABLE KEYS */;
INSERT INTO `depedente` VALUES (3,3,'Matheus Aparecido da Silva Roberto','41.462.496-8');
/*!40000 ALTER TABLE `depedente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empresa` (
  `idempresa` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`idempresa`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES (1,'San Carlos'),(3,'Rapido Campinas');
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado`
--

DROP TABLE IF EXISTS `estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado` (
  `idestado` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(75) DEFAULT NULL,
  `uf` varchar(3) DEFAULT NULL,
  `idpais` int(11) DEFAULT NULL,
  PRIMARY KEY (`idestado`),
  KEY `fk_estado_pais_idx` (`idpais`),
  CONSTRAINT `fk_estado_pais` FOREIGN KEY (`idpais`) REFERENCES `pais` (`idpais`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado`
--

LOCK TABLES `estado` WRITE;
/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
INSERT INTO `estado` VALUES (1,'São Paulo','SP',1),(2,'Rio de Janeiro','RJ',1);
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formapagamento`
--

DROP TABLE IF EXISTS `formapagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `formapagamento` (
  `idformapagamento` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idformapagamento`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formapagamento`
--

LOCK TABLES `formapagamento` WRITE;
/*!40000 ALTER TABLE `formapagamento` DISABLE KEYS */;
INSERT INTO `formapagamento` VALUES (1,'Parcelado'),(2,'Á Vista');
/*!40000 ALTER TABLE `formapagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoteiscontratados`
--

DROP TABLE IF EXISTS `hoteiscontratados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hoteiscontratados` (
  `hotel_idHotel` int(11) NOT NULL,
  `viagem_idviagem` int(11) NOT NULL,
  `valor` double NOT NULL,
  PRIMARY KEY (`viagem_idviagem`,`hotel_idHotel`),
  KEY `fk_viagem_has_hotel_hotel1_idx` (`hotel_idHotel`),
  KEY `fk_viagem_has_hotel_viagem1_idx` (`viagem_idviagem`),
  CONSTRAINT `fk_viagem_has_hotel_hotel1` FOREIGN KEY (`hotel_idHotel`) REFERENCES `hotel` (`idhotel`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_viagem_has_hotel_viagem1` FOREIGN KEY (`viagem_idviagem`) REFERENCES `viagem` (`idviagem`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoteiscontratados`
--

LOCK TABLES `hoteiscontratados` WRITE;
/*!40000 ALTER TABLE `hoteiscontratados` DISABLE KEYS */;
INSERT INTO `hoteiscontratados` VALUES (1,3,250),(1,4,250),(2,4,250),(3,5,500),(1,6,300);
/*!40000 ALTER TABLE `hoteiscontratados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotel` (
  `idhotel` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idhotel`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES (1,'Redentor Palace Hotel'),(2,'Monte Carmelo'),(3,'Três irmãs');
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lembrete`
--

DROP TABLE IF EXISTS `lembrete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lembrete` (
  `idlembrete` int(11) NOT NULL AUTO_INCREMENT,
  `idusuario` int(11) DEFAULT NULL,
  `descricao` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idlembrete`),
  KEY `fk_lembrete_usuario1_idx` (`idusuario`),
  CONSTRAINT `fk_lembrete_usuario1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lembrete`
--

LOCK TABLES `lembrete` WRITE;
/*!40000 ALTER TABLE `lembrete` DISABLE KEYS */;
/*!40000 ALTER TABLE `lembrete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logs`
--

DROP TABLE IF EXISTS `logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logs` (
  `idlogs` int(11) NOT NULL AUTO_INCREMENT,
  `idusuario` int(11) DEFAULT NULL,
  `acao` varchar(150) DEFAULT NULL,
  `data` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  PRIMARY KEY (`idlogs`),
  KEY `fk_logs_usuario1_idx` (`idusuario`),
  CONSTRAINT `fk_logs_usuario1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs`
--

LOCK TABLES `logs` WRITE;
/*!40000 ALTER TABLE `logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ocupantes`
--

DROP TABLE IF EXISTS `ocupantes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ocupantes` (
  `idocupantes` int(11) NOT NULL AUTO_INCREMENT,
  `contrato_idcontrato` int(11) NOT NULL,
  `quarto_idquarto` int(11) NOT NULL,
  `nome` varchar(90) DEFAULT NULL,
  PRIMARY KEY (`idocupantes`,`contrato_idcontrato`,`quarto_idquarto`),
  KEY `fk_contrato_has_quarto_quarto1_idx` (`quarto_idquarto`),
  KEY `fk_contrato_has_quarto_contrato1_idx` (`contrato_idcontrato`),
  CONSTRAINT `fk_contrato_has_quarto_contrato1` FOREIGN KEY (`contrato_idcontrato`) REFERENCES `contrato` (`idcontrato`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_contrato_has_quarto_quarto1` FOREIGN KEY (`quarto_idquarto`) REFERENCES `quarto` (`idquarto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ocupantes`
--

LOCK TABLES `ocupantes` WRITE;
/*!40000 ALTER TABLE `ocupantes` DISABLE KEYS */;
INSERT INTO `ocupantes` VALUES (3,5,2,'Jairo Aparecido Roberto'),(4,4,2,'Matheus Aparecido da Silva Roberto');
/*!40000 ALTER TABLE `ocupantes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pais` (
  `idpais` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) DEFAULT NULL,
  `sigla` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`idpais`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` VALUES (1,'Brasil','BR'),(2,'Argentinha','ARG');
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parcela`
--

DROP TABLE IF EXISTS `parcela`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parcela` (
  `idparcela` int(11) NOT NULL AUTO_INCREMENT,
  `valor` double DEFAULT NULL,
  `datavencimento` date DEFAULT NULL,
  `datapagamento` date DEFAULT NULL,
  `paga` tinyint(1) DEFAULT NULL,
  `idcontrato` int(11) NOT NULL,
  PRIMARY KEY (`idparcela`,`idcontrato`),
  KEY `fk_parcela_contrato1_idx` (`idcontrato`),
  CONSTRAINT `fk_parcela_contrato1` FOREIGN KEY (`idcontrato`) REFERENCES `contrato` (`idcontrato`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parcela`
--

LOCK TABLES `parcela` WRITE;
/*!40000 ALTER TABLE `parcela` DISABLE KEYS */;
INSERT INTO `parcela` VALUES (131,210,'2017-01-31','2017-01-31',1,4),(132,210,'2017-02-28','2017-01-31',1,4),(133,234,'2017-03-31','2017-01-31',1,4),(134,186,'2017-04-30',NULL,0,4),(143,168,'2017-01-31',NULL,0,5),(144,168,'2017-02-28',NULL,0,5),(145,168,'2017-03-31',NULL,0,5),(146,168,'2017-04-30',NULL,0,5),(147,168,'2017-05-31',NULL,0,5),(148,168,'2017-06-30',NULL,0,5),(149,168,'2017-07-31',NULL,0,5),(150,168,'2017-08-31',NULL,0,5),(151,168,'2017-09-30',NULL,0,5),(152,168,'2017-10-31',NULL,0,5);
/*!40000 ALTER TABLE `parcela` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quarto`
--

DROP TABLE IF EXISTS `quarto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quarto` (
  `idquarto` int(11) NOT NULL AUTO_INCREMENT,
  `idhotel` int(11) DEFAULT NULL,
  `camacasal` int(11) DEFAULT NULL,
  `camasolteiro` int(11) DEFAULT NULL,
  `observacao` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idquarto`),
  KEY `fk_quarto_hotel1_idx` (`idhotel`),
  CONSTRAINT `fk_quarto_hotel1` FOREIGN KEY (`idhotel`) REFERENCES `hotel` (`idhotel`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quarto`
--

LOCK TABLES `quarto` WRITE;
/*!40000 ALTER TABLE `quarto` DISABLE KEYS */;
INSERT INTO `quarto` VALUES (2,1,1,2,'');
/*!40000 ALTER TABLE `quarto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) DEFAULT NULL,
  `login` varchar(45) DEFAULT NULL,
  `senha` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Administrador','admin','admin');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `veiculo`
--

DROP TABLE IF EXISTS `veiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `veiculo` (
  `idveiculo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) DEFAULT NULL,
  `tipo` varchar(60) DEFAULT NULL,
  `lotacao` int(11) DEFAULT NULL,
  `idempresa` int(11) NOT NULL,
  PRIMARY KEY (`idveiculo`),
  KEY `fk_veiculo_empresa1_idx` (`idempresa`),
  CONSTRAINT `fk_veiculo_empresa1` FOREIGN KEY (`idempresa`) REFERENCES `empresa` (`idempresa`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `veiculo`
--

LOCK TABLES `veiculo` WRITE;
/*!40000 ALTER TABLE `veiculo` DISABLE KEYS */;
INSERT INTO `veiculo` VALUES (1,'25000','Ônibus',46,3),(2,'21300','Ônibus',50,1),(3,'22300','Ônibus',50,1),(4,'18300','Ônibus',50,1),(5,'23300','Ônibus',56,1);
/*!40000 ALTER TABLE `veiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `veiculoscontratados`
--

DROP TABLE IF EXISTS `veiculoscontratados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `veiculoscontratados` (
  `viagem_idviagem` int(11) NOT NULL,
  `veiculo_idveiculo` int(11) NOT NULL,
  `valor` double NOT NULL,
  PRIMARY KEY (`viagem_idviagem`,`veiculo_idveiculo`),
  KEY `fk_viagem_has_veiculo_veiculo1_idx` (`veiculo_idveiculo`),
  KEY `fk_viagem_has_veiculo_viagem1_idx` (`viagem_idviagem`),
  CONSTRAINT `fk_viagem_has_veiculo_veiculo1` FOREIGN KEY (`veiculo_idveiculo`) REFERENCES `veiculo` (`idveiculo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_viagem_has_veiculo_viagem1` FOREIGN KEY (`viagem_idviagem`) REFERENCES `viagem` (`idviagem`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `veiculoscontratados`
--

LOCK TABLES `veiculoscontratados` WRITE;
/*!40000 ALTER TABLE `veiculoscontratados` DISABLE KEYS */;
INSERT INTO `veiculoscontratados` VALUES (3,1,100),(3,2,100),(4,2,100),(4,3,100),(4,4,100),(5,2,300),(6,2,120),(6,5,120);
/*!40000 ALTER TABLE `veiculoscontratados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `viagem`
--

DROP TABLE IF EXISTS `viagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `viagem` (
  `idviagem` int(11) NOT NULL AUTO_INCREMENT,
  `dataida` date DEFAULT NULL,
  `datavolta` date DEFAULT NULL,
  `horariosaida` time DEFAULT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  `idorigem` int(11) DEFAULT NULL,
  `iddestino` int(11) DEFAULT NULL,
  `vagasextras` int(11) DEFAULT NULL,
  PRIMARY KEY (`idviagem`),
  KEY `fk_viagem_cidade1_idx` (`idorigem`),
  KEY `fk_viagem_cidade2_idx` (`iddestino`),
  CONSTRAINT `fk_viagem_cidade1` FOREIGN KEY (`idorigem`) REFERENCES `cidade` (`idcidade`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_viagem_cidade2` FOREIGN KEY (`iddestino`) REFERENCES `cidade` (`idcidade`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `viagem`
--

LOCK TABLES `viagem` WRITE;
/*!40000 ALTER TABLE `viagem` DISABLE KEYS */;
INSERT INTO `viagem` VALUES (3,'2017-01-28','2017-01-30','22:00:00','',1,4,0),(4,'2017-01-31','2017-02-06','02:03:33','',1,3,0),(5,'2018-01-22','2018-01-28','02:07:02','',1,3,0),(6,'2018-01-05','2018-01-08','20:30:00','',1,2,0);
/*!40000 ALTER TABLE `viagem` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-12 20:43:11
