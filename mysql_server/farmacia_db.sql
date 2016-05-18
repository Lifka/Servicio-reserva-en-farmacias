-- MySQL dump 10.15  Distrib 10.0.23-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: farmacia_db
-- ------------------------------------------------------
-- Server version	10.0.23-MariaDB

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
-- Table structure for table `direccion`
--

DROP TABLE IF EXISTS `direccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `direccion` (
  `codigo` int(8) NOT NULL AUTO_INCREMENT,
  `calle` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `numero` int(3) DEFAULT NULL,
  `letra` varchar(1) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ciudad` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `codigo_postal` int(5) DEFAULT NULL,
  `provincia` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `pais` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `piso` int(2) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccion`
--

LOCK TABLES `direccion` WRITE;
/*!40000 ALTER TABLE `direccion` DISABLE KEYS */;
INSERT INTO `direccion` VALUES (1,'mi calle',5,'a','Granada',18012,'Granada',NULL,4),(2,'mi calle dos',16,NULL,'Granada',18012,'Granada','España',NULL),(3,'calle farmacia',18,NULL,'Granada',18012,'Granada','Granada',NULL),(4,'calle farmacia 2',26,NULL,'Granada',18012,'Granada','España',NULL),(5,'Manuel Santana',3452,'\0','Priego de CÃ³rdoba',14800,'CÃ³rdoba','EspaÃ±a',0),(6,'Manuel Santana',34,'\0','Priego de CÃ³rdoba',14800,'CÃ³rdoba','EspaÃ±a',0),(7,'Manuel Santana',1,'\0','Priego de CÃ³rdoba',14800,'CÃ³rdoba','EspaÃ±a',0),(8,'Manuel Santana',1,'\0','Priego de CÃ³rdoba',14800,'CÃ³rdoba','EspaÃ±a',0),(9,'Manuel Santana',123,'\0','Priego de CÃ³rdoba',14800,'Cordoba','EspaÃ±a',0);
/*!40000 ALTER TABLE `direccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `factura` (
  `num_factura` int(8) NOT NULL AUTO_INCREMENT,
  `pagado` varchar(2) COLLATE utf8_spanish_ci NOT NULL,
  `tipo_pago` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `fecha_factura` datetime NOT NULL,
  `precio_sin_iva` float(6,2) NOT NULL,
  `precio_con_iva` float(6,2) NOT NULL,
  `id_pedido` int(8) NOT NULL,
  PRIMARY KEY (`num_factura`),
  KEY `fk_factura_pedido` (`id_pedido`),
  CONSTRAINT `fk_factura_pedido` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`num_pedido`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura`
--

LOCK TABLES `factura` WRITE;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
INSERT INTO `factura` VALUES (1,'no','CONTRARREMBOLSO','2016-05-10 04:00:00',0.00,0.00,2);
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `farmacia`
--

DROP TABLE IF EXISTS `farmacia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `farmacia` (
  `cif` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `codigo_direccion` int(8) NOT NULL,
  `horario_apertura` time NOT NULL,
  `horario_cierre` time NOT NULL,
  `longitud` float NOT NULL,
  `latitud` float NOT NULL,
  PRIMARY KEY (`cif`),
  UNIQUE KEY `nombre` (`nombre`),
  UNIQUE KEY `longitud` (`longitud`,`latitud`),
  KEY `fk_direccion_farmacia` (`codigo_direccion`),
  CONSTRAINT `fk_direccion_farmacia` FOREIGN KEY (`codigo_direccion`) REFERENCES `direccion` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `farmacia`
--

LOCK TABLES `farmacia` WRITE;
/*!40000 ALTER TABLE `farmacia` DISABLE KEYS */;
INSERT INTO `farmacia` VALUES ('A11122233','Primera farmacia',3,'09:00:00','21:00:00',-4.12,37.26),('Z99955544','Segunda farmacia',4,'09:00:00','21:00:00',-3.52,37.13);
/*!40000 ALTER TABLE `farmacia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `linea_pedido`
--

DROP TABLE IF EXISTS `linea_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `linea_pedido` (
  `num_pedido` int(8) NOT NULL,
  `num_linea` int(8) NOT NULL,
  `id_producto` int(8) NOT NULL,
  `cantidad` int(11) NOT NULL,
  PRIMARY KEY (`num_pedido`,`num_linea`),
  KEY `fk_pedido_producto` (`id_producto`),
  CONSTRAINT `fk_pedido_producto` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`),
  CONSTRAINT `linea_pedido_ibfk_1` FOREIGN KEY (`num_pedido`) REFERENCES `pedido` (`num_pedido`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `linea_pedido`
--

LOCK TABLES `linea_pedido` WRITE;
/*!40000 ALTER TABLE `linea_pedido` DISABLE KEYS */;
INSERT INTO `linea_pedido` VALUES (2,1,1,3),(2,2,4,2);
/*!40000 ALTER TABLE `linea_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido` (
  `num_pedido` int(8) NOT NULL AUTO_INCREMENT,
  `fecha_pedido` datetime NOT NULL,
  `id_usuario` int(8) NOT NULL,
  `cif_farmacia` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`num_pedido`),
  KEY `fk_usuario_pedido` (`id_usuario`),
  KEY `fk_pedido_farmacia` (`cif_farmacia`),
  CONSTRAINT `fk_pedido_farmacia` FOREIGN KEY (`cif_farmacia`) REFERENCES `farmacia` (`cif`),
  CONSTRAINT `fk_usuario_pedido` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (2,'2016-05-10 00:00:00',2,'A11122233'),(4,'2016-05-10 00:00:00',2,'A11122233');
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(200) COLLATE utf8_spanish_ci DEFAULT NULL,
  `precio` float(5,2) DEFAULT NULL,
  `f_creacion` datetime DEFAULT NULL,
  `f_caducidad` datetime DEFAULT NULL,
  `departamento` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `iva` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'Producto 1','Esta es la descripción del producto 1',4.00,'2016-05-09 00:00:00','2016-08-09 00:00:00','MEDICAMENTOS',1.21),(3,'Producto 2','Esta es la descripción para el producto 2',7.30,'2016-05-09 00:00:00','2016-09-21 00:00:00','MEDICAMENTOS',1.21),(4,'Producto 3','Esta es la descripción para el producto 3',6.99,'2016-05-09 00:00:00','2017-02-15 00:00:00','PERFUMERIA',1.21),(5,'Producto 4','Esta es la descripción para el producto 4',3.25,'2016-05-09 00:00:00','2016-07-26 00:00:00','HOMEOPATIA',1.21),(31,'Producto insertado','Producto insertado por el administrador',3.00,'2016-05-13 00:00:00','2020-02-01 00:00:00','HOMEOPATIA',1.21);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_farmacia`
--

DROP TABLE IF EXISTS `stock_farmacia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock_farmacia` (
  `cif_farmacia` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `id_producto` int(8) NOT NULL,
  `cantidad` int(8) NOT NULL,
  PRIMARY KEY (`cif_farmacia`,`id_producto`),
  KEY `id_producto` (`id_producto`),
  CONSTRAINT `stock_farmacia_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stock_farmacia_ibfk_2` FOREIGN KEY (`cif_farmacia`) REFERENCES `farmacia` (`cif`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_farmacia`
--

LOCK TABLES `stock_farmacia` WRITE;
/*!40000 ALTER TABLE `stock_farmacia` DISABLE KEYS */;
INSERT INTO `stock_farmacia` VALUES ('A11122233',1,200),('A11122233',3,100),('A11122233',4,5),('A11122233',5,38),('Z99955544',1,25),('Z99955544',3,140),('Z99955544',4,48),('Z99955544',5,27);
/*!40000 ALTER TABLE `stock_farmacia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `pass` varchar(500) COLLATE utf8_spanish_ci NOT NULL,
  `nombre_completo` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `id_direccion` int(8) DEFAULT NULL,
  `dni` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `forma_pago` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `dni` (`dni`),
  KEY `fk_direccion_usuario` (`id_direccion`),
  CONSTRAINT `fk_direccion_usuario` FOREIGN KEY (`id_direccion`) REFERENCES `direccion` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (2,'jcgallardomorales@gmail.com','19d691da5ea99e50ecb401d91028ff9653a6dd30e3bad167d6cf313821e3f3bd','Juan Carlos Gallardo Morales',1,'22255588H','CONTRARREMBOLSO'),(3,'javierizquierdovera@gmail.com','5464a89e24a026a98c06afa326b04ac6865ff4a9a8c3a334f61089adec6e331a\r\n','Javier Izquierdo Vera',2,'7775511G','CONTRARREMBOLSO'),(4,'nuevo@gmail.com','19d691da5ea99e50ecb401d91028ff9653a6dd30e3bad167d6cf313821e3f3bd','Nuevo usuario',NULL,'11122233T','CONTRARREMBOLSO');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-17  3:48:49
