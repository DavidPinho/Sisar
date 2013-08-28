-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.25a


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema sisar
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ sisar;
USE sisar;

--
-- Table structure for table `sisar`.`agendamento`
--

DROP TABLE IF EXISTS `agendamento`;
CREATE TABLE `agendamento` (
  `idagendamento` int(11) NOT NULL AUTO_INCREMENT,
  `data` date NOT NULL DEFAULT '0000-00-00',
  `idusuario` int(11) NOT NULL,
  `hora` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idagendamento`,`idusuario`),
  KEY `fk_agendamento_usuario1` (`idusuario`),
  CONSTRAINT `fk_agendamento_usuario1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sisar`.`agendamento`
--

/*!40000 ALTER TABLE `agendamento` DISABLE KEYS */;
INSERT INTO `agendamento` (`idagendamento`,`data`,`idusuario`,`hora`) VALUES 
 (13,'2013-10-01',2,11),
 (15,'2013-08-27',2,19),
 (16,'2013-08-15',2,12),
 (18,'2013-08-16',2,18),
 (20,'2013-08-21',1,19),
 (21,'2013-08-21',1,18);
/*!40000 ALTER TABLE `agendamento` ENABLE KEYS */;


--
-- Table structure for table `sisar`.`avaliacao`
--

DROP TABLE IF EXISTS `avaliacao`;
CREATE TABLE `avaliacao` (
  `idavaliacao` int(11) NOT NULL AUTO_INCREMENT,
  `data` datetime NOT NULL,
  `nota` int(11) NOT NULL,
  `comentario` varchar(500) DEFAULT NULL,
  `idusuario` int(11) NOT NULL,
  `iditem` int(11) NOT NULL,
  PRIMARY KEY (`idavaliacao`,`idusuario`,`iditem`),
  KEY `fk_evaluation_user1` (`idusuario`),
  KEY `fk_evaluation_item1` (`iditem`),
  CONSTRAINT `fk_evaluation_item1` FOREIGN KEY (`iditem`) REFERENCES `item` (`iditem`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_evaluation_user1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sisar`.`avaliacao`
--

/*!40000 ALTER TABLE `avaliacao` DISABLE KEYS */;
INSERT INTO `avaliacao` (`idavaliacao`,`data`,`nota`,`comentario`,`idusuario`,`iditem`) VALUES 
 (1,'2013-08-27 00:00:00',10,'Muito Bom!',2,1),
 (2,'2013-08-27 00:00:00',6,'Estava muito grudado',2,2);
/*!40000 ALTER TABLE `avaliacao` ENABLE KEYS */;


--
-- Table structure for table `sisar`.`cardapio`
--

DROP TABLE IF EXISTS `cardapio`;
CREATE TABLE `cardapio` (
  `idcardapio` int(11) NOT NULL AUTO_INCREMENT,
  `data` datetime NOT NULL,
  `turno` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcardapio`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sisar`.`cardapio`
--

/*!40000 ALTER TABLE `cardapio` DISABLE KEYS */;
INSERT INTO `cardapio` (`idcardapio`,`data`,`turno`) VALUES 
 (1,'2013-08-01 00:00:00',1),
 (2,'2013-08-02 00:00:00',2);
/*!40000 ALTER TABLE `cardapio` ENABLE KEYS */;


--
-- Table structure for table `sisar`.`cardapio_item`
--

DROP TABLE IF EXISTS `cardapio_item`;
CREATE TABLE `cardapio_item` (
  `idcardapio` int(11) NOT NULL,
  `iditem` int(11) NOT NULL,
  PRIMARY KEY (`idcardapio`,`iditem`),
  KEY `fk_menu_has_item_menu1` (`idcardapio`),
  KEY `fk_menu_has_item_item1` (`iditem`),
  CONSTRAINT `fk_menu_has_item_menu1` FOREIGN KEY (`idcardapio`) REFERENCES `cardapio` (`idcardapio`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_menu_has_item_item1` FOREIGN KEY (`iditem`) REFERENCES `item` (`iditem`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sisar`.`cardapio_item`
--

/*!40000 ALTER TABLE `cardapio_item` DISABLE KEYS */;
INSERT INTO `cardapio_item` (`idcardapio`,`iditem`) VALUES 
 (1,1),
 (1,2),
 (1,3),
 (1,4),
 (1,5),
 (1,6),
 (1,7),
 (2,1),
 (2,2),
 (2,3),
 (2,4),
 (2,5),
 (2,6),
 (2,7);
/*!40000 ALTER TABLE `cardapio_item` ENABLE KEYS */;


--
-- Table structure for table `sisar`.`item`
--

DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `iditem` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(300) NOT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`iditem`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sisar`.`item`
--

/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` (`iditem`,`nome`,`categoria`) VALUES 
 (1,'Feijão','Guarnicao'),
 (2,'Arroz','Guarnição2'),
 (3,'Frango','Proteína'),
 (4,'Charuto de Soja','Vegetariano'),
 (5,'Manga','Suco'),
 (6,'Pé de moça','Sobremesa'),
 (7,'Alface','Salada');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;


--
-- Table structure for table `sisar`.`usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(300) NOT NULL,
  `senha` varchar(45) NOT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sisar`.`usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`idusuario`,`nome`,`senha`) VALUES 
 (1,'admin','ufba'),
 (2,'Teste','teste');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
