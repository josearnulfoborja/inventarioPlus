CREATE DATABASE  IF NOT EXISTS `inventarioplus` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `inventarioplus`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: inventarioplus
-- ------------------------------------------------------
-- Server version	9.4.0

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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id_cliente` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `documento` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'jose',NULL,'77474574','Col Los Laureles',NULL);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `devoluciones`
--

DROP TABLE IF EXISTS `devoluciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `devoluciones` (
  `id_devolucion` int NOT NULL AUTO_INCREMENT,
  `id_prestamo` int DEFAULT NULL,
  `fecha_registro_devolucion` datetime DEFAULT NULL,
  `fecha_devolucion_real` datetime DEFAULT NULL,
  `condicion_al_devolver` text,
  `observaciones` text,
  `solicitar_inspeccion` varchar(10) DEFAULT NULL,
  `especialista_asignado_id` int DEFAULT NULL,
  `fecha_inspeccion_programada` datetime DEFAULT NULL,
  `inspeccion_realizada` varchar(10) DEFAULT NULL,
  `estado_inspeccion` varchar(50) DEFAULT NULL,
  `creado_por` int DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT CURRENT_TIMESTAMP,
  `fecha_actualizacion` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_devolucion`),
  KEY `id_prestamo` (`id_prestamo`),
  KEY `especialista_asignado_id` (`especialista_asignado_id`),
  KEY `creado_por` (`creado_por`),
  CONSTRAINT `devoluciones_ibfk_1` FOREIGN KEY (`id_prestamo`) REFERENCES `prestamos` (`id_prestamo`),
  CONSTRAINT `devoluciones_ibfk_2` FOREIGN KEY (`especialista_asignado_id`) REFERENCES `usuarios2` (`id_usuario`),
  CONSTRAINT `devoluciones_ibfk_3` FOREIGN KEY (`creado_por`) REFERENCES `usuarios2` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devoluciones`
--

LOCK TABLES `devoluciones` WRITE;
/*!40000 ALTER TABLE `devoluciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `devoluciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipos`
--

DROP TABLE IF EXISTS `equipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipos` (
  `id_equipo` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) NOT NULL,
  `numero_serial` varchar(100) DEFAULT NULL,
  `estado` varchar(100) DEFAULT NULL,
  `requiere_inspeccion` tinyint(1) NOT NULL DEFAULT '0',
  `fecha_creacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_actualizacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `costo_dia` decimal(12,2) DEFAULT NULL,
  `descripcion` text,
  `fecha_disponible` datetime(6) DEFAULT NULL,
  `fecha_uso_hora` datetime DEFAULT NULL,
  `requiere_especialista` tinyint(1) NOT NULL DEFAULT '0',
  `estado_id` int DEFAULT NULL,
  `tipo_id` int DEFAULT NULL,
  `modelo_id` int DEFAULT NULL,
  `ubicacion_id` int DEFAULT NULL,
  `activo` bit(1) DEFAULT NULL,
  `categoria` varchar(100) DEFAULT NULL,
  `codigo` varchar(100) DEFAULT NULL,
  `fecha_adquisicion` date DEFAULT NULL,
  `imagen_url` varchar(500) DEFAULT NULL,
  `numero_serie` varchar(100) DEFAULT NULL,
  `valor_estimado` decimal(12,2) DEFAULT NULL,
  `marca_id` int DEFAULT NULL,
  `costo_creacion` decimal(12,2) DEFAULT NULL,
  `fecha_dia` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id_equipo`),
  UNIQUE KEY `uq_equipos_numero_serial` (`numero_serial`),
  KEY `idx_equipos_estado_id` (`estado_id`),
  KEY `idx_equipos_tipo_id` (`tipo_id`),
  KEY `idx_equipos_modelo_id` (`modelo_id`),
  KEY `idx_equipos_ubicacion_id` (`ubicacion_id`),
  KEY `FK9506w2guqyqbhcnyi2spapo61` (`marca_id`),
  CONSTRAINT `FK9506w2guqyqbhcnyi2spapo61` FOREIGN KEY (`marca_id`) REFERENCES `marcas` (`id`),
  CONSTRAINT `fk_equipos_estado` FOREIGN KEY (`estado_id`) REFERENCES `estados_equipo` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_equipos_modelo` FOREIGN KEY (`modelo_id`) REFERENCES `modelos` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_equipos_tipo` FOREIGN KEY (`tipo_id`) REFERENCES `tipos_equipo` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_equipos_ubicacion` FOREIGN KEY (`ubicacion_id`) REFERENCES `ubicaciones` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipos`
--

LOCK TABLES `equipos` WRITE;
/*!40000 ALTER TABLE `equipos` DISABLE KEYS */;
INSERT INTO `equipos` VALUES (8,'Laptop Dell Latitude 5430','DL5430SN001','Disponible',1,'2025-11-06 00:00:00','2025-11-06 00:00:00',15.00,'Laptop para tareas administrativas','2025-11-06 00:00:00.000000','2025-11-06 00:00:00',1,1,1,19,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `equipos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipos_v2`
--

DROP TABLE IF EXISTS `equipos_v2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipos_v2` (
  `id_equipo` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `tipo` varchar(100) DEFAULT NULL,
  `modelo` varchar(100) DEFAULT NULL,
  `numero_serial` varchar(100) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `ubicacion` varchar(100) DEFAULT NULL,
  `requiere_inspeccion` varchar(10) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT CURRENT_TIMESTAMP,
  `fecha_actualizacion` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `costo_dia` decimal(38,2) DEFAULT NULL,
  `descripcion` text,
  `fecha_disponible` date DEFAULT NULL,
  `fecha_uso_hora` datetime(6) DEFAULT NULL,
  `requiere_especialista` bit(1) DEFAULT NULL,
  `estado_id` int DEFAULT NULL,
  `modelo_id` int DEFAULT NULL,
  `ubicacion_id` int DEFAULT NULL,
  PRIMARY KEY (`id_equipo`),
  UNIQUE KEY `numero_serial` (`numero_serial`),
  KEY `idx_equipos_estado_id` (`estado_id`),
  KEY `idx_equipos_modelo_id` (`modelo_id`),
  KEY `idx_equipos_ubicacion_id` (`ubicacion_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipos_v2`
--

LOCK TABLES `equipos_v2` WRITE;
/*!40000 ALTER TABLE `equipos_v2` DISABLE KEYS */;
INSERT INTO `equipos_v2` VALUES (1,'Prueba 1',NULL,NULL,NULL,'1',NULL,NULL,'2025-11-02 21:33:22','2025-11-02 21:33:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'Taladro Percutor Bosch','Herramienta','GBH 2-26','SN-BOSCH-001','DISPONIBLE','Bodega A','NO','2024-08-10 09:15:00','2025-10-01 12:00:00',15.00,'Taladro para trabajo pesado','2025-11-04','2025-11-04 09:00:00.000000',_binary '\0',NULL,NULL,NULL),(3,'Soldador Inverter Xtreme','Soldadura','XTI-200','SN-XTI-045','MANTENIMIENTO','Taller 2','SI','2023-03-22 14:30:00','2025-09-20 16:20:00',25.50,'Soldador para ensamblaje metálico','2025-11-10','2025-11-10 08:30:00.000000',_binary '',NULL,NULL,NULL),(4,'Generador Honda','Generador','EU22i','SN-HON-210','DISPONIBLE','Patio','NO','2022-11-05 11:00:00','2025-07-12 10:00:00',45.00,'Generador portátil silencioso','2025-11-05','2025-11-05 07:00:00.000000',_binary '\0',NULL,NULL,NULL),(5,'Teléfono Rugerizado','Electrónica','RugPhone X','SN-RUG-300','PRESTADO','Oficina Central','NO','2024-01-18 09:00:00','2025-11-01 09:45:00',5.00,'Teléfono para uso de campo','2025-11-20','2025-11-03 13:15:00.000000',_binary '\0',NULL,NULL,NULL),(6,'Cinta Métrica Laser','Medición','LM-100','SN-LM-100','DISPONIBLE','Bodega B','NO','2025-02-02 10:10:00','2025-10-20 08:00:00',2.50,'Medidor laser 100m','2025-11-03','2025-11-03 10:00:00.000000',_binary '\0',NULL,NULL,NULL),(7,'Grua Compacta','Maquinaria','GRC-500','SN-GRC-500','BAJA','Yarda','SI','2020-06-12 08:00:00','2025-05-01 15:00:00',120.00,'Grua para maniobras pesadas (fuera de servicio)',NULL,NULL,_binary '',NULL,NULL,NULL),(8,'Cámara Termográfica','Instrumento','ThermoCam T5','SN-TC-987','DISPONIBLE','Laboratorio','SI','2024-04-15 13:20:00','2025-10-30 11:00:00',60.00,'Cámara para inspección térmica','2025-11-06','2025-11-06 09:30:00.000000',_binary '\0',NULL,NULL,NULL),(9,'Equipo de Soldadura TIG','Soldadura','TIG-180','SN-TIG-180','DISPONIBLE','Taller 1','SI','2021-09-01 07:45:00','2025-08-15 14:00:00',30.75,'Equipo TIG profesional','2025-11-02','2025-11-02 08:00:00.000000',_binary '',NULL,NULL,NULL),(10,'Compresor de Aire','Maquinaria','CMP-150','SN-CMP-150','PRESTADO','Obra Norte','NO','2023-12-20 09:00:00','2025-10-25 10:30:00',40.00,'Compresor para herramientas neumáticas','2025-12-01','2025-11-03 14:00:00.000000',_binary '\0',NULL,NULL,NULL),(11,'Lámpara Equipo Inspección','Iluminación','LED-Inspection','SN-LED-11','DISPONIBLE','Bodega A','NO','2025-06-10 10:00:00','2025-10-10 09:00:00',3.20,'Lámpara para inspecciones en sitio','2025-11-03','2025-11-03 08:30:00.000000',_binary '\0',NULL,NULL,NULL);
/*!40000 ALTER TABLE `equipos_v2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especialistas`
--

DROP TABLE IF EXISTS `especialistas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `especialistas` (
  `id_especialista` bigint NOT NULL AUTO_INCREMENT,
  `disponibilidad` bit(1) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_especialista`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especialistas`
--

LOCK TABLES `especialistas` WRITE;
/*!40000 ALTER TABLE `especialistas` DISABLE KEYS */;
/*!40000 ALTER TABLE `especialistas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estados_equipo`
--

DROP TABLE IF EXISTS `estados_equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estados_equipo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `descripcion` text COLLATE utf8mb4_unicode_ci,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estados_equipo`
--

LOCK TABLES `estados_equipo` WRITE;
/*!40000 ALTER TABLE `estados_equipo` DISABLE KEYS */;
INSERT INTO `estados_equipo` VALUES (1,'Disponible','Equipo disponible para préstamo',1,'2025-11-03 22:27:09','2025-11-03 22:27:09'),(2,'Prestado','Equipo actualmente prestado',1,'2025-11-03 22:27:09','2025-11-03 22:27:09'),(3,'Mantenimiento','Equipo en mantenimiento',1,'2025-11-03 22:27:09','2025-11-03 22:27:09'),(4,'Baja','Equipo fuera de servicio / dado de baja',1,'2025-11-03 22:27:09','2025-11-03 22:27:09'),(5,'Reservado','Equipo reservado',1,'2025-11-03 22:27:09','2025-11-03 22:27:09'),(6,'En reparación','Equipo en reparación',1,'2025-11-03 22:27:09','2025-11-03 22:27:09');
/*!40000 ALTER TABLE `estados_equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluaciones_tecnicas`
--

DROP TABLE IF EXISTS `evaluaciones_tecnicas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evaluaciones_tecnicas` (
  `id_evaluacion` bigint NOT NULL AUTO_INCREMENT,
  `aprobado` bit(1) DEFAULT NULL,
  `observaciones` text,
  `id_equipo` bigint DEFAULT NULL,
  `id_usuario` bigint DEFAULT NULL,
  PRIMARY KEY (`id_evaluacion`),
  KEY `FKt27yij9ei3rsbvqwaekts0jy9` (`id_usuario`),
  KEY `FKa1ju82kwkxtfxk6mg5x67hmdl` (`id_equipo`),
  CONSTRAINT `FKa1ju82kwkxtfxk6mg5x67hmdl` FOREIGN KEY (`id_equipo`) REFERENCES `equipos` (`id_equipo`),
  CONSTRAINT `FKt27yij9ei3rsbvqwaekts0jy9` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluaciones_tecnicas`
--

LOCK TABLES `evaluaciones_tecnicas` WRITE;
/*!40000 ALTER TABLE `evaluaciones_tecnicas` DISABLE KEYS */;
/*!40000 ALTER TABLE `evaluaciones_tecnicas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historial`
--

DROP TABLE IF EXISTS `historial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historial` (
  `id_historial` bigint NOT NULL AUTO_INCREMENT,
  `fecha_accion` datetime(6) DEFAULT NULL,
  `texto_accion` text,
  `id_usuario` bigint DEFAULT NULL,
  PRIMARY KEY (`id_historial`),
  KEY `FKek6x4bxyi1ipmpa5teiejpngg` (`id_usuario`),
  CONSTRAINT `FKek6x4bxyi1ipmpa5teiejpngg` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historial`
--

LOCK TABLES `historial` WRITE;
/*!40000 ALTER TABLE `historial` DISABLE KEYS */;
/*!40000 ALTER TABLE `historial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inspecciones`
--

DROP TABLE IF EXISTS `inspecciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inspecciones` (
  `id_inspeccion` int NOT NULL AUTO_INCREMENT,
  `id_devolucion` int DEFAULT NULL,
  `id_prestamo` int DEFAULT NULL,
  `especialista_id` int DEFAULT NULL,
  `fecha_inspeccion` datetime DEFAULT NULL,
  `resultado` text,
  `observaciones` text,
  `creado_por` int DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT CURRENT_TIMESTAMP,
  `fecha_actualizacion` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_inspeccion`),
  KEY `id_devolucion` (`id_devolucion`),
  KEY `id_prestamo` (`id_prestamo`),
  KEY `especialista_id` (`especialista_id`),
  KEY `creado_por` (`creado_por`),
  CONSTRAINT `inspecciones_ibfk_1` FOREIGN KEY (`id_devolucion`) REFERENCES `devoluciones` (`id_devolucion`),
  CONSTRAINT `inspecciones_ibfk_2` FOREIGN KEY (`id_prestamo`) REFERENCES `prestamos` (`id_prestamo`),
  CONSTRAINT `inspecciones_ibfk_3` FOREIGN KEY (`especialista_id`) REFERENCES `usuarios2` (`id_usuario`),
  CONSTRAINT `inspecciones_ibfk_4` FOREIGN KEY (`creado_por`) REFERENCES `usuarios2` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inspecciones`
--

LOCK TABLES `inspecciones` WRITE;
/*!40000 ALTER TABLE `inspecciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `inspecciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marcas`
--

DROP TABLE IF EXISTS `marcas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marcas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_marcas_nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marcas`
--

LOCK TABLES `marcas` WRITE;
/*!40000 ALTER TABLE `marcas` DISABLE KEYS */;
INSERT INTO `marcas` VALUES (1,'Dell',1,'2025-11-04 03:35:25','2025-11-04 03:35:25'),(2,'Lenovo',1,'2025-11-04 03:35:25','2025-11-04 03:35:25'),(3,'HP',1,'2025-11-04 03:35:25','2025-11-04 03:35:25'),(4,'Apple',1,'2025-11-04 03:35:25','2025-11-04 03:35:25'),(5,'Asus',1,'2025-11-04 03:35:25','2025-11-04 03:35:25'),(6,'Acer',1,'2025-11-04 03:35:25','2025-11-04 03:35:25'),(7,'Samsung',1,'2025-11-04 03:35:25','2025-11-04 03:35:25'),(8,'Epson',1,'2025-11-04 03:35:25','2025-11-04 03:35:25'),(9,'Canon',1,'2025-11-04 03:35:25','2025-11-04 03:35:25'),(10,'Brother',1,'2025-11-04 03:35:25','2025-11-04 03:35:25');
/*!40000 ALTER TABLE `marcas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modelos`
--

DROP TABLE IF EXISTS `modelos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `modelos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `marca_id` int NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `codigo_modelo` varchar(100) DEFAULT NULL,
  `descripcion` text,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_modelo_marca_nombre` (`marca_id`,`nombre`),
  CONSTRAINT `fk_modelo_marca` FOREIGN KEY (`marca_id`) REFERENCES `marcas` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modelos`
--

LOCK TABLES `modelos` WRITE;
/*!40000 ALTER TABLE `modelos` DISABLE KEYS */;
INSERT INTO `modelos` VALUES (19,1,'Latitude 5430','LAT5430','Laptop empresarial de alto rendimiento',1,'2025-11-04 03:35:44','2025-11-04 03:35:44'),(20,1,'Inspiron 15','INSP15','Laptop de uso general para estudiantes y oficina',1,'2025-11-04 03:35:44','2025-11-04 03:35:44'),(21,2,'ThinkPad X1 Carbon','TPX1C','Ultrabook premium para ejecutivos',1,'2025-11-04 03:35:44','2025-11-04 03:35:44'),(22,2,'IdeaCentre AIO 3','IDCAIO3','Computadora todo-en-uno para espacios reducidos',1,'2025-11-04 03:35:44','2025-11-04 03:35:44'),(23,3,'HP LaserJet Pro M404','HPLJM404','Impresora monocromática rápida y eficiente',1,'2025-11-04 03:35:44','2025-11-04 03:35:44'),(24,3,'HP DeskJet 2755e','HPDJ2755E','Impresora color compacta para uso doméstico',1,'2025-11-04 03:35:44','2025-11-04 03:35:44');
/*!40000 ALTER TABLE `modelos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modelos_v2`
--

DROP TABLE IF EXISTS `modelos_v2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `modelos_v2` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codigo` varchar(100) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `descripcion` text,
  `especificaciones` json DEFAULT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_modelo_nombre_marca` (`marca`,`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modelos_v2`
--

LOCK TABLES `modelos_v2` WRITE;
/*!40000 ALTER TABLE `modelos_v2` DISABLE KEYS */;
INSERT INTO `modelos_v2` VALUES (1,NULL,'GBH 2-26',NULL,NULL,NULL,1,'2025-11-03 22:28:55','2025-11-03 22:28:55'),(2,NULL,'XTI-200',NULL,NULL,NULL,1,'2025-11-03 22:28:55','2025-11-03 22:28:55'),(3,NULL,'EU22i',NULL,NULL,NULL,1,'2025-11-03 22:28:55','2025-11-03 22:28:55'),(4,NULL,'RugPhone X',NULL,NULL,NULL,1,'2025-11-03 22:28:55','2025-11-03 22:28:55'),(5,NULL,'LM-100',NULL,NULL,NULL,1,'2025-11-03 22:28:55','2025-11-03 22:28:55'),(6,NULL,'GRC-500',NULL,NULL,NULL,1,'2025-11-03 22:28:55','2025-11-03 22:28:55'),(7,NULL,'ThermoCam T5',NULL,NULL,NULL,1,'2025-11-03 22:28:55','2025-11-03 22:28:55'),(8,NULL,'TIG-180',NULL,NULL,NULL,1,'2025-11-03 22:28:55','2025-11-03 22:28:55'),(9,NULL,'CMP-150',NULL,NULL,NULL,1,'2025-11-03 22:28:55','2025-11-03 22:28:55'),(10,NULL,'LED-Inspection',NULL,NULL,NULL,1,'2025-11-03 22:28:55','2025-11-03 22:28:55');
/*!40000 ALTER TABLE `modelos_v2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamos`
--

DROP TABLE IF EXISTS `prestamos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamos` (
  `id_prestamo` int NOT NULL AUTO_INCREMENT,
  `fecha_prestamo` datetime NOT NULL,
  `fecha_devolucion_estimada` datetime DEFAULT NULL,
  `fecha_devolucion_real` datetime DEFAULT NULL,
  `id_usuario` int DEFAULT NULL,
  `id_equipo` int DEFAULT NULL,
  `estado_prestamo` varchar(255) DEFAULT NULL,
  `condicion_al_prestar` text,
  `condicion_al_devolver` text,
  `observaciones` text,
  `inspeccion_requerida` varchar(10) DEFAULT NULL,
  `inspeccion_realizada` varchar(10) DEFAULT NULL,
  `especialista_asignado_id` int DEFAULT NULL,
  `fecha_inspeccion_programada` datetime DEFAULT NULL,
  `estado_inspeccion` varchar(50) DEFAULT NULL,
  `observaciones_inspeccion` text,
  `fecha_creacion` datetime DEFAULT CURRENT_TIMESTAMP,
  `fecha_actualizacion` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `costo_total` decimal(38,2) DEFAULT NULL,
  `fecha_devolucion` date DEFAULT NULL,
  `fecha_entrega` date DEFAULT NULL,
  `fecha_prevista` date DEFAULT NULL,
  `id_cliente` bigint DEFAULT NULL,
  `id_especialista` bigint DEFAULT NULL,
  PRIMARY KEY (`id_prestamo`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_equipo` (`id_equipo`),
  KEY `especialista_asignado_id` (`especialista_asignado_id`),
  KEY `FKr39piepds6x3dlmd1ba0byf2r` (`id_cliente`),
  KEY `FK7871wyde3h1y2mqofjswig7bv` (`id_especialista`),
  CONSTRAINT `FK7871wyde3h1y2mqofjswig7bv` FOREIGN KEY (`id_especialista`) REFERENCES `especialistas` (`id_especialista`),
  CONSTRAINT `FKr39piepds6x3dlmd1ba0byf2r` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`),
  CONSTRAINT `prestamos_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios2` (`id_usuario`),
  CONSTRAINT `prestamos_ibfk_2` FOREIGN KEY (`id_equipo`) REFERENCES `equipos_v2` (`id_equipo`),
  CONSTRAINT `prestamos_ibfk_3` FOREIGN KEY (`especialista_asignado_id`) REFERENCES `usuarios2` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamos`
--

LOCK TABLES `prestamos` WRITE;
/*!40000 ALTER TABLE `prestamos` DISABLE KEYS */;
/*!40000 ALTER TABLE `prestamos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT 'NO ESPECIFICADO',
  `sku` varchar(50) DEFAULT 'NO ESPECIFICADO',
  `descripcion` text,
  `precio` decimal(10,2) DEFAULT NULL,
  `vencimiento` date DEFAULT NULL,
  `categoria` varchar(50) DEFAULT 'NO ESPECIFICADO',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id_rol` int NOT NULL AUTO_INCREMENT,
  `nombre_rol` varchar(100) NOT NULL,
  `descripcion` text,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMINISTRADOR','Administrador del sistema con acceso completo'),(2,'ESPECIALISTA','Especialista técnico para inspecciones y mantenimiento'),(3,'USUARIO','Usuario regular con acceso limitado'),(4,'CLIENTE','Cliente externo que solicita préstamos de equipos'),(5,'ADMIN','Rol con acceso completo al sistema, incluyendo gestión de usuarios, equipos y préstamos.');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos_equipo`
--

DROP TABLE IF EXISTS `tipos_equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipos_equipo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codigo` varchar(80) DEFAULT NULL,
  `nombre` varchar(150) NOT NULL,
  `descripcion` text,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_tipos_nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_equipo`
--

LOCK TABLES `tipos_equipo` WRITE;
/*!40000 ALTER TABLE `tipos_equipo` DISABLE KEYS */;
INSERT INTO `tipos_equipo` VALUES (1,'LAPTOP','Laptop','Computadora portátil para uso general',1,'2025-11-04 03:07:25','2025-11-04 03:07:25'),(2,'DESKTOP','Computadora de escritorio','Equipo fijo para estaciones de trabajo',1,'2025-11-04 03:07:25','2025-11-04 03:07:25'),(3,'IMPRESORA','Impresora','Dispositivo para impresión de documentos',1,'2025-11-04 03:07:25','2025-11-04 03:07:25'),(4,'PROYECTOR','Proyector','Equipo para proyección de imágenes o presentaciones',1,'2025-11-04 03:07:25','2025-11-04 03:07:25'),(5,'TABLET','Tablet','Dispositivo táctil portátil',1,'2025-11-04 03:07:25','2025-11-04 03:07:25'),(6,'ESCANER','Escáner','Equipo para digitalizar documentos físicos',1,'2025-11-04 03:07:25','2025-11-04 03:07:25');
/*!40000 ALTER TABLE `tipos_equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ubicaciones`
--

DROP TABLE IF EXISTS `ubicaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ubicaciones` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codigo` varchar(80) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nombre` varchar(180) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tipo` varchar(180) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `direccion` varchar(180) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `piso` varchar(180) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sector` varchar(180) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `lat` decimal(10,8) DEFAULT NULL,
  `lng` decimal(11,8) DEFAULT NULL,
  `responsable` varchar(180) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `telefono` varchar(180) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `descripcion` text COLLATE utf8mb4_unicode_ci,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKoqasvfy12901076hbxu5xaw5d` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ubicaciones`
--

LOCK TABLES `ubicaciones` WRITE;
/*!40000 ALTER TABLE `ubicaciones` DISABLE KEYS */;
INSERT INTO `ubicaciones` VALUES (1,NULL,'Bodega A',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2025-11-03 22:33:16','2025-11-03 22:33:16'),(2,NULL,'Taller 2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2025-11-03 22:33:16','2025-11-03 22:33:16'),(3,NULL,'Patio',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2025-11-03 22:33:16','2025-11-03 22:33:16'),(4,NULL,'Oficina Central',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2025-11-03 22:33:16','2025-11-03 22:33:16'),(5,NULL,'Bodega B',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2025-11-03 22:33:16','2025-11-03 22:33:16'),(6,NULL,'Yarda',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2025-11-03 22:33:16','2025-11-03 22:33:16'),(7,NULL,'Laboratorio',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2025-11-03 22:33:16','2025-11-03 22:33:16'),(8,NULL,'Taller 1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2025-11-03 22:33:16','2025-11-03 22:33:16'),(9,NULL,'Obra Norte',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2025-11-03 22:33:16','2025-11-03 22:33:16'),(16,'BOD-A','Bodega A','Bodega','Calle 1 #100','PB','Zona A',NULL,NULL,'Juan Perez','+341234567','Bodega principal de herramientas',1,'2025-11-03 22:33:22','2025-11-03 22:33:22'),(17,'OFI-CEN','Oficina Central','Oficina','Av. Central 200','3','Recepción',40.41677500,-3.70379000,'María Ruiz','+341112223','Oficina administrativa central',1,'2025-11-03 22:33:22','2025-11-03 22:33:22'),(18,'TALL-1','Taller 1','Taller','Polígono Industrial 5','N/A','Sector Norte',NULL,NULL,'Carlos López','+349998887','Area de mantenimiento y reparación',1,'2025-11-03 22:33:22','2025-11-03 22:33:22'),(19,'OBRA-N','Obra Norte','Obra','Carretera 45 km 10','N/A','Sitio de obra',NULL,NULL,'Encargado Obra','+349912345','Ubicación temporal de obra',1,'2025-11-03 22:33:22','2025-11-03 22:33:22');
/*!40000 ALTER TABLE `ubicaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id_usuario` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `correo_electronico` varchar(150) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `rol_id` int NOT NULL DEFAULT '3',
  `activo` tinyint(1) DEFAULT '1',
  `fecha_creacion` datetime DEFAULT CURRENT_TIMESTAMP,
  `fecha_actualizacion` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dui` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `correo_electronico` (`correo_electronico`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `UK4bgxhyx9uovocdgkfxivrtnr2` (`dui`),
  KEY `rol_id` (`rol_id`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Administrador','del Sistema','admin@inventarioplus.com',NULL,'admin','0192023a7bbd73250516f069df18b500',1,1,'2025-10-13 21:57:11','2025-10-13 21:57:11',NULL),(2,'Juan','Pérez','juan.perez@inventarioplus.com',NULL,'jperez','6ad14ba9986e3615423dfca256d04e3f',3,1,'2025-10-13 21:57:11','2025-10-13 21:57:11',NULL),(3,'Carlos','Técnico','carlos.tecnico@inventarioplus.com',NULL,'ctecnico','5f35dc7f50c58d67c94f87d99de5b26e',2,1,'2025-10-13 21:57:11','2025-10-13 21:57:11',NULL),(4,'Usuario','Demo','demo@inventarioplus.com',NULL,'demo','62cc2d8b4bf2d8728120d052163a77df',3,1,'2025-10-13 21:57:11','2025-10-13 21:57:11',NULL),(5,'Usuario','Test','test@inventarioplus.com',NULL,'test','cc03e747a6afbbcbf8be7668acfebee5',3,1,'2025-10-13 21:57:11','2025-10-13 21:57:11',NULL),(6,'Usuario','Invitado','guest@inventarioplus.com',NULL,'guest','fcf41657f02f88137a1bcf068a32c0a3',3,1,'2025-10-13 21:57:11','2025-10-13 21:57:11',NULL),(7,'Especialista','Principal','especialista@inventarioplus.com',NULL,'especialista','6ad14ba9986e3615423dfca256d04e3f',2,1,'2025-10-13 21:57:11','2025-10-13 21:57:11',NULL),(8,'Super','Admin','superadmin@inventarioplus.com',NULL,'superadmin','0192023a7bbd73250516f069df18b500',1,1,'2025-10-13 21:57:11','2025-10-13 21:57:11',NULL);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios2`
--

DROP TABLE IF EXISTS `usuarios2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios2` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `correo_electronico` varchar(150) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `rol_id` int DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT CURRENT_TIMESTAMP,
  `fecha_actualizacion` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `correo_electronico` (`correo_electronico`),
  KEY `rol_id` (`rol_id`),
  CONSTRAINT `usuarios2_ibfk_1` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`id_rol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios2`
--

LOCK TABLES `usuarios2` WRITE;
/*!40000 ALTER TABLE `usuarios2` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios2` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-04  5:22:18
