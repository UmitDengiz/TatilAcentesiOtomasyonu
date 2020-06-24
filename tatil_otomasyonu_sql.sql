
CREATE DATABASE tatil_otomasyonu CHARACTER SET utf8 COLLATE utf8_general_ci;
use tatil_otomasyonu;

-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: tatil_otomasyonu
-- ------------------------------------------------------
-- Server version	5.7.29-log

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
-- Table structure for table `bilet`
--

DROP TABLE IF EXISTS `bilet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bilet` (
  `bilet_id` int(11) NOT NULL AUTO_INCREMENT,
  `tutar` int(11) NOT NULL,
  `nereden` varchar(255) DEFAULT NULL,
  `nereye` varchar(255) DEFAULT NULL,
  `yontem` varchar(245) DEFAULT NULL,
  PRIMARY KEY (`bilet_id`),
  UNIQUE KEY `yontem_UNIQUE` (`yontem`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bilet`
--

LOCK TABLES `bilet` WRITE;
/*!40000 ALTER TABLE `bilet` DISABLE KEYS */;
INSERT INTO `bilet` VALUES (1,300,'Istanbul','Ankara','Uçak');
/*!40000 ALTER TABLE `bilet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bilet_satis`
--

DROP TABLE IF EXISTS `bilet_satis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bilet_satis` (
  `user_id` int(11) DEFAULT NULL,
  `bilet_id` int(11) DEFAULT NULL,
  KEY `bilet_satis_user_fk_idx` (`user_id`),
  KEY `bilet_satis_bilet_fk_idx` (`bilet_id`),
  CONSTRAINT `bilet_satis_bilet_fk` FOREIGN KEY (`bilet_id`) REFERENCES `bilet` (`bilet_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bilet_satis_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bilet_satis`
--

LOCK TABLES `bilet_satis` WRITE;
/*!40000 ALTER TABLE `bilet_satis` DISABLE KEYS */;
INSERT INTO `bilet_satis` VALUES (1,1);
/*!40000 ALTER TABLE `bilet_satis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dosya`
--

DROP TABLE IF EXISTS `dosya`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dosya` (
  `dosya_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `path` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dosya_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dosya`
--

LOCK TABLES `dosya` WRITE;
/*!40000 ALTER TABLE `dosya` DISABLE KEYS */;
INSERT INTO `dosya` VALUES (28,'20-06-2020-041345.jpg','C:\\Users\\Cypher\\Documents\\NetBeansProjects\\Tatil_Otomasyonu_v100\\web\\resources\\files','image/jpeg'),(29,'20-06-2020-042212.jpg','C:\\Users\\Cypher\\Documents\\NetBeansProjects\\Tatil_Otomasyonu_v100\\web\\resources\\files','image/jpeg'),(30,'21-06-2020-124458.jpg','C:\\Users\\Cypher\\Documents\\NetBeansProjects\\Tatil_Otomasyonu_v100\\web\\resources\\files','image/jpeg'),(31,'21-06-2020-124536.jpg','C:\\Users\\Cypher\\Documents\\NetBeansProjects\\Tatil_Otomasyonu_v100\\web\\resources\\files','image/jpeg'),(32,'21-06-2020-124604.jpg','C:\\Users\\Cypher\\Documents\\NetBeansProjects\\Tatil_Otomasyonu_v100\\web\\resources\\files','image/jpeg'),(33,'21-06-2020-124916.jpg','C:\\Users\\Cypher\\Documents\\NetBeansProjects\\Tatil_Otomasyonu_v100\\web\\resources\\files','image/jpeg'),(34,'21-06-2020-124941.jpg','C:\\Users\\Cypher\\Documents\\NetBeansProjects\\Tatil_Otomasyonu_v100\\web\\resources\\files','image/jpeg'),(35,'21-06-2020-125016.jpg','C:\\Users\\Cypher\\Documents\\NetBeansProjects\\Tatil_Otomasyonu_v100\\web\\resources\\files','image/jpeg'),(36,'21-06-2020-125042.jpg','C:\\Users\\Cypher\\Documents\\NetBeansProjects\\Tatil_Otomasyonu_v100\\web\\resources\\files','image/jpeg'),(37,'21-06-2020-125115.jpg','C:\\Users\\Cypher\\Documents\\NetBeansProjects\\Tatil_Otomasyonu_v100\\web\\resources\\files','image/jpeg'),(38,'21-06-2020-125147.jpg','C:\\Users\\Cypher\\Documents\\NetBeansProjects\\Tatil_Otomasyonu_v100\\web\\resources\\files','image/jpeg'),(39,'21-06-2020-020007.jpg','C:\\Users\\Cypher\\Documents\\NetBeansProjects\\Tatil_Otomasyonu_v100\\web\\resources\\files','image/jpeg'),(40,'21-06-2020-020028.jpg','C:\\Users\\Cypher\\Documents\\NetBeansProjects\\Tatil_Otomasyonu_v100\\web\\resources\\files','image/jpeg');
/*!40000 ALTER TABLE `dosya` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kampanya`
--

DROP TABLE IF EXISTS `kampanya`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kampanya` (
  `kampanya_id` int(11) NOT NULL AUTO_INCREMENT,
  `isim` varchar(255) NOT NULL,
  `ucret` int(11) DEFAULT NULL,
  `tur` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`kampanya_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kampanya`
--

LOCK TABLES `kampanya` WRITE;
/*!40000 ALTER TABLE `kampanya` DISABLE KEYS */;
INSERT INTO `kampanya` VALUES (1,'Ister aileniz ile ya da yalniz, ister sevdiginizle bas basa ya da kalabalik arkadas grubunuz ile... Uluslararasi Benzersiz Hersey Dahil Club Med tatilleri, dünyanin dört bir kösesine yayilmis ayricalikli destinasyonlarda sizlerin hizmetine sunuluyor',20,'Club Med Destinasyonlari'),(3,'Ister aileniz ile ya da yalniz, ister sevdiginizle bas basa ya da kalabalik arkadas grubunuz ile... Uluslararasi Benzersiz Hersey Dahil Club Med tatilleri, dünyanin dört bir kösesine yayilmis ayricalikli destinasyonlarda sizlerin hizmetine sunuluyor',30,'Genclik Burada'),(4,'Ister aileniz ile ya da yalniz, ister sevdiginizle bas basa ya da kalabalik arkadas grubunuz ile... Uluslararasi Benzersiz Hersey Dahil Club Med tatilleri, dünyanin dört bir kösesine yayilmis ayricalikli destinasyonlarda sizlerin hizmetine sunuluyor',50,'Yaz Eglenceleri Basliyor');
/*!40000 ALTER TABLE `kampanya` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otel`
--

DROP TABLE IF EXISTS `otel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `otel` (
  `otel_id` int(11) NOT NULL AUTO_INCREMENT,
  `isim` varchar(255) NOT NULL,
  `adres` varchar(255) DEFAULT NULL,
  `dosya_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`otel_id`),
  KEY `dosya_otel_fk_idx` (`dosya_id`),
  CONSTRAINT `dosya_otel_fk` FOREIGN KEY (`dosya_id`) REFERENCES `dosya` (`dosya_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otel`
--

LOCK TABLES `otel` WRITE;
/*!40000 ALTER TABLE `otel` DISABLE KEYS */;
INSERT INTO `otel` VALUES (6,'Trump International Hotel & Tower, Toronto – Ontario','New York',30),(7,'The Oberoi Udaivilas Udaipur','Hindistan',31),(8,'Four Seasons Resort, Bora Bora','Society Islands',32),(9,'Burj Al Arab','Dubai',33),(10,'Four Seasons George V','Paris',34),(11,'The Atlantis Resort','Bahamas',35),(12,'Ritz-Carlton','Tokyo',36),(13,'The Westin Excelsior','Roma',37),(14,'InterContinental','Hong Kong',38);
/*!40000 ALTER TABLE `otel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otel_ozellik`
--

DROP TABLE IF EXISTS `otel_ozellik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `otel_ozellik` (
  `otel_ozellik_id` int(11) NOT NULL AUTO_INCREMENT,
  `otel_id` int(11) DEFAULT NULL,
  `ozellik_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`otel_ozellik_id`),
  KEY `otel_ozellik_otel_fk_idx` (`otel_id`),
  KEY `otel_ozellik_ozellik_fk_idx` (`ozellik_id`),
  CONSTRAINT `otel_ozellik_otel_fk` FOREIGN KEY (`otel_id`) REFERENCES `otel` (`otel_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `otel_ozellik_ozellik_fk` FOREIGN KEY (`ozellik_id`) REFERENCES `ozellik` (`ozellik_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otel_ozellik`
--

LOCK TABLES `otel_ozellik` WRITE;
/*!40000 ALTER TABLE `otel_ozellik` DISABLE KEYS */;
INSERT INTO `otel_ozellik` VALUES (19,6,1),(20,6,2),(21,6,4),(22,7,1),(23,7,2),(24,7,4),(25,8,1),(26,8,2),(27,8,4),(28,9,1),(29,9,2),(30,9,4),(31,10,1),(32,10,2),(33,10,4),(34,11,1),(35,11,2),(36,11,4),(37,12,1),(38,12,2),(39,12,4),(40,13,1),(41,13,2),(42,13,4),(43,14,1),(44,14,2),(45,14,4);
/*!40000 ALTER TABLE `otel_ozellik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ozellik`
--

DROP TABLE IF EXISTS `ozellik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ozellik` (
  `ozellik_id` int(11) NOT NULL AUTO_INCREMENT,
  `isim` varchar(255) NOT NULL,
  PRIMARY KEY (`ozellik_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ozellik`
--

LOCK TABLES `ozellik` WRITE;
/*!40000 ALTER TABLE `ozellik` DISABLE KEYS */;
INSERT INTO `ozellik` VALUES (1,'Havuzlu'),(2,'Müzik Sistemi'),(4,'Kahvalti Dahil');
/*!40000 ALTER TABLE `ozellik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privilege`
--

DROP TABLE IF EXISTS `privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `privilege` (
  `privilege_id` int(11) NOT NULL AUTO_INCREMENT,
  `p_module` varchar(255) NOT NULL,
  `yetki_c` tinyint(1) DEFAULT '0',
  `yetki_r` tinyint(1) DEFAULT '0',
  `yetki_u` tinyint(1) DEFAULT '0',
  `yetki_d` tinyint(1) DEFAULT '0',
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`privilege_id`),
  KEY `privilege_role_fk_idx` (`role_id`),
  CONSTRAINT `privilege_role_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilege`
--

LOCK TABLES `privilege` WRITE;
/*!40000 ALTER TABLE `privilege` DISABLE KEYS */;
INSERT INTO `privilege` VALUES (1,'Bilet',1,1,1,1,1);
/*!40000 ALTER TABLE `privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rezervasyon`
--

DROP TABLE IF EXISTS `rezervasyon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rezervasyon` (
  `rezervasyon_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `tur_id` int(11) NOT NULL,
  `tarih` date DEFAULT NULL,
  PRIMARY KEY (`rezervasyon_id`),
  KEY `rezervasyon_user_fk_idx` (`user_id`),
  KEY `rezervasyon_tur_fk_idx` (`tur_id`),
  CONSTRAINT `rezervasyon_tur_fk` FOREIGN KEY (`tur_id`) REFERENCES `tur` (`tur_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rezervasyon_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rezervasyon`
--

LOCK TABLES `rezervasyon` WRITE;
/*!40000 ALTER TABLE `rezervasyon` DISABLE KEYS */;
INSERT INTO `rezervasyon` VALUES (2,1,7,'2020-06-18');
/*!40000 ALTER TABLE `rezervasyon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(245) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Admin'),(17,'Musteri'),(2,'Super Admin');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tur`
--

DROP TABLE IF EXISTS `tur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tur` (
  `tur_id` int(11) NOT NULL AUTO_INCREMENT,
  `bas_tarih` date NOT NULL,
  `son_tarih` date DEFAULT NULL,
  `guzergah` text,
  `kapasite` int(11) DEFAULT NULL,
  `ucret` int(11) DEFAULT NULL,
  `dosya_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`tur_id`),
  KEY `tur_dosya_fk_idx` (`dosya_id`),
  CONSTRAINT `tur_dosya_fk` FOREIGN KEY (`dosya_id`) REFERENCES `dosya` (`dosya_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tur`
--

LOCK TABLES `tur` WRITE;
/*!40000 ALTER TABLE `tur` DISABLE KEYS */;
INSERT INTO `tur` VALUES (7,'2020-09-06','2020-10-03','Malatyadan antepe ulkenin dort bir tarafini gezme firsati elinizde',10,12,28),(16,'2020-08-20','2020-09-15','Adalar turu 20 farkli ulke gezme firsatini karcirmayin',20,400,39),(17,'2020-08-20','2020-09-15','Kapadokya Turu',35,400,40);
/*!40000 ALTER TABLE `tur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name_surname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `user_role_fk_idx` (`role_id`),
  CONSTRAINT `user_role_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Adem Kartal','adem@gmail.com','1234','05069996322',17),(10,'Panel','panel@gmail.com','1234','0565254515',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_kampanya`
--

DROP TABLE IF EXISTS `user_kampanya`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_kampanya` (
  `user_id` int(11) NOT NULL,
  `kampanya_id` int(11) NOT NULL,
  KEY `user_has_kampanya_user_fk_idx` (`user_id`),
  KEY `user_has_kampanya_kampanya_fk_idx` (`kampanya_id`),
  CONSTRAINT `user_has_kampanya_kampanya_fk` FOREIGN KEY (`kampanya_id`) REFERENCES `kampanya` (`kampanya_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_has_kampanya_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_kampanya`
--

LOCK TABLES `user_kampanya` WRITE;
/*!40000 ALTER TABLE `user_kampanya` DISABLE KEYS */;
INSERT INTO `user_kampanya` VALUES (10,1),(1,4);
/*!40000 ALTER TABLE `user_kampanya` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yorum`
--

DROP TABLE IF EXISTS `yorum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yorum` (
  `yorum_id` int(11) NOT NULL AUTO_INCREMENT,
  `yorum` text NOT NULL,
  `otel_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`yorum_id`),
  KEY `yorum_otel_fk_idx` (`otel_id`),
  KEY `yorum_user_fk_idx` (`user_id`),
  CONSTRAINT `yorum_otel_fk` FOREIGN KEY (`otel_id`) REFERENCES `otel` (`otel_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `yorum_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yorum`
--

LOCK TABLES `yorum` WRITE;
/*!40000 ALTER TABLE `yorum` DISABLE KEYS */;
INSERT INTO `yorum` VALUES (3,'Bu oteli hiç begenmedim\n',6,10),(8,'sdrheds',6,1),(9,'dhdh',6,1);
/*!40000 ALTER TABLE `yorum` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-21 20:34:09
