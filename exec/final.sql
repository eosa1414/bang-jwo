-- MySQL dump 10.13  Distrib 8.0.41, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: bangjwo
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `address_id` bigint NOT NULL AUTO_INCREMENT,
  `room_id` bigint DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `address_detail` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `postal_code` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `lat` decimal(9,6) DEFAULT NULL,
  `lng` decimal(9,6) DEFAULT NULL,
  `province` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `city` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `district` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  UNIQUE KEY `room_id` (`room_id`),
  KEY `idx_room_id` (`room_id`),
  KEY `idx_lat_lng` (`lat`,`lng`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,1,'서울특별시 강남구 봉은사로 290','102동 843호','04583',37.501277,127.039600,'서울특별시','강남구','역삼동'),(2,2,'서울시 마포구 정원빌라','103동 164호','06466',37.566300,126.901500,'서울특별시','마포구','성산동'),(3,3,'서울시 동작구 신대방원룸','106동 756호','01249',37.499300,126.927200,'서울특별시','동작구','신대방동'),(4,4,'서울시 강서구 테라스오피스텔','102동 705호','01608',37.548100,126.836100,'서울특별시','강서구','화곡동'),(5,5,'서울시 양천구 학군아파트','102동 499호','01649',37.524200,126.856600,'서울특별시','양천구','목동'),(6,6,'서울특별시 강남구 선릉로 2','104동 173호','03161',37.297000,128.315200,'서울특별시','강남구','삼성동'),(7,7,'경기도 분당구 강남대로 189','108동 596호','06160',36.219600,128.102000,'경기도','분당구','정자동'),(8,8,'부산광역시 해운대구 테헤란로 145','106동 762호','06184',36.295900,127.092900,'부산광역시','해운대구','중동'),(9,9,'대구광역시 수성구 봉은사로 134','101동 369호','07626',35.277300,126.854500,'대구광역시','수성구','범어동'),(10,10,'인천광역시 연수구 강남대로 291','107동 865호','01318',37.092200,128.270300,'인천광역시','연수구','송도동'),(11,11,'서울특별시 강남구 도산대로 128','103동 290호','04501',35.210200,127.745000,'서울특별시','강남구','삼성동'),(12,12,'경기도 분당구 봉은사로 254','109동 491호','01205',37.597200,128.487800,'경기도','분당구','정자동'),(13,13,'부산광역시 해운대구 언주로 69','102동 203호','04466',35.111000,127.611300,'부산광역시','해운대구','중동'),(14,14,'대구광역시 수성구 테헤란로 264','108동 520호','06225',35.106600,128.363800,'대구광역시','수성구','범어동'),(15,15,'인천광역시 연수구 도산대로 281','107동 187호','07713',37.156200,127.407700,'인천광역시','연수구','송도동'),(16,16,'서울특별시 강남구 도산대로 262','106동 817호','01568',35.641800,128.552300,'서울특별시','강남구','삼성동'),(17,17,'경기도 분당구 테헤란로 151','106동 235호','04758',35.349800,126.826300,'경기도','분당구','정자동'),(18,18,'부산광역시 해운대구 테헤란로 129','105동 360호','02921',36.741500,128.605700,'부산광역시','해운대구','중동'),(19,19,'대구광역시 수성구 테헤란로 66','109동 424호','02906',35.221900,128.459300,'대구광역시','수성구','범어동'),(20,20,'인천광역시 연수구 봉은사로 28','104동 328호','09654',36.504200,127.604800,'인천광역시','연수구','송도동'),(21,21,'서울특별시 강남구 강남대로 100','107동 597호','07717',36.918700,127.919100,'서울특별시','강남구','삼성동'),(22,22,'경기도 분당구 테헤란로 114','101동 248호','01462',36.889500,128.513000,'경기도','분당구','정자동'),(23,23,'부산광역시 해운대구 강남대로 261','103동 474호','05028',37.162900,128.135400,'부산광역시','해운대구','중동'),(24,24,'대구광역시 수성구 도산대로 198','105동 805호','04331',36.515800,127.832500,'대구광역시','수성구','범어동'),(25,25,'인천광역시 연수구 강남대로 177','101동 473호','06093',36.398700,128.406700,'인천광역시','연수구','송도동'),(26,26,'서울특별시 강남구 역삼로 273','105동 891호','07308',36.818700,127.237800,'서울특별시','강남구','삼성동'),(27,27,'경기도 분당구 역삼로 219','102동 700호','08701',36.081700,127.003100,'경기도','분당구','정자동'),(28,28,'부산광역시 해운대구 역삼로 46','105동 658호','03223',37.474900,128.072800,'부산광역시','해운대구','중동'),(29,29,'대구광역시 수성구 도산대로 38','105동 107호','02116',36.852000,128.315200,'대구광역시','수성구','범어동'),(30,30,'인천광역시 연수구 테헤란로 44','103동 470호','01112',37.005200,128.484000,'인천광역시','연수구','송도동'),(31,31,'서울특별시 강남구 테헤란로 131','107동 818호','08596',35.027200,128.509500,'서울특별시','강남구','삼성동'),(32,32,'경기도 분당구 선릉로 52','104동 477호','06658',35.483000,126.965300,'경기도','분당구','정자동'),(33,33,'부산광역시 해운대구 언주로 101','103동 711호','09481',36.092900,127.141300,'부산광역시','해운대구','중동'),(34,34,'대구광역시 수성구 도산대로 107','108동 477호','05785',36.354700,127.271500,'대구광역시','수성구','범어동'),(35,35,'인천광역시 연수구 도산대로 249','105동 125호','06170',35.639400,126.952500,'인천광역시','연수구','송도동'),(36,36,'서울특별시 강남구 테헤란로 74','102동 120호','08199',37.504209,127.026527,'서울특별시','강남구','역삼동'),(37,37,'서울특별시 강남구 강남대로 105','104동 242호','02613',37.490048,127.036119,'서울특별시','강남구','역삼동'),(38,38,'서울특별시 강남구 언주로 5','104동 838호','01281',37.506431,127.031701,'서울특별시','강남구','역삼동'),(39,39,'서울특별시 강남구 언주로 195','108동 306호','09547',37.490138,127.036174,'서울특별시','강남구','역삼동'),(40,40,'서울특별시 강남구 봉은사로 7','104동 838호','03715',37.495092,127.030957,'서울특별시','강남구','역삼동'),(41,41,'서울특별시 강남구 봉은사로 36','103동 888호','04926',37.491351,127.036883,'서울특별시','강남구','역삼동'),(42,42,'서울특별시 강남구 강남대로 177','104동 256호','07264',37.508336,127.035698,'서울특별시','강남구','역삼동'),(43,43,'서울특별시 강남구 강남대로 143','109동 553호','04268',37.502843,127.034936,'서울특별시','강남구','역삼동'),(44,44,'서울특별시 강남구 강남대로 167','108동 487호','04585',37.497977,127.026814,'서울특별시','강남구','역삼동'),(45,45,'서울특별시 강남구 도산대로 200','107동 585호','06402',37.493582,127.029216,'서울특별시','강남구','역삼동'),(46,46,'서울특별시 강남구 역삼로 96','106동 564호','01524',37.494639,127.024219,'서울특별시','강남구','역삼동'),(47,47,'서울특별시 강남구 언주로 102','106동 390호','09968',37.503295,127.031881,'서울특별시','강남구','역삼동'),(48,48,'서울특별시 강남구 언주로 98','105동 572호','09509',37.497208,127.028668,'서울특별시','강남구','역삼동'),(49,49,'서울특별시 강남구 강남대로 15','105동 192호','04664',37.502539,127.025370,'서울특별시','강남구','역삼동'),(50,50,'서울특별시 강남구 테헤란로 210','104동 187호','09003',37.495689,127.037150,'서울특별시','강남구','역삼동'),(51,51,'서울특별시 강남구 강남대로 204','107동 520호','07647',37.498043,127.027086,'서울특별시','강남구','역삼동'),(52,52,'서울특별시 강남구 테헤란로 28','105동 437호','06860',37.490022,127.031971,'서울특별시','강남구','역삼동'),(53,53,'서울특별시 강남구 봉은사로 48','108동 616호','02022',37.508222,127.029700,'서울특별시','강남구','역삼동'),(54,54,'서울특별시 강남구 선릉로 220','109동 208호','04497',37.498429,127.028845,'서울특별시','강남구','역삼동'),(55,55,'서울특별시 강남구 도산대로 78','103동 327호','01089',37.506780,127.026414,'서울특별시','강남구','역삼동'),(56,56,'서울특별시 강남구 봉은사로 190','103동 566호','05489',37.499174,127.036800,'서울특별시','강남구','강남대로'),(57,57,'서울특별시 강남구 강남대로 139','104동 794호','07375',37.492726,127.021202,'서울특별시','강남구','강남대로'),(58,58,'서울특별시 강남구 강남대로 142','105동 473호','01963',37.490580,127.030071,'서울특별시','강남구','강남대로'),(59,59,'서울특별시 강남구 도산대로 40','106동 403호','07881',37.493693,127.031107,'서울특별시','강남구','강남대로'),(60,60,'서울특별시 강남구 언주로 294','101동 719호','01874',37.508899,127.033343,'서울특별시','강남구','강남대로'),(61,61,'서울특별시 강남구 언주로 263','109동 873호','08238',37.500346,127.031464,'서울특별시','강남구','강남대로'),(62,62,'서울특별시 강남구 도산대로 27','107동 761호','08412',37.496087,127.031119,'서울특별시','강남구','강남대로'),(63,63,'서울특별시 강남구 도산대로 52','103동 215호','09135',37.502330,127.030357,'서울특별시','강남구','강남대로'),(64,64,'서울특별시 강남구 역삼로 260','106동 778호','07792',37.504560,127.027145,'서울특별시','강남구','강남대로'),(65,65,'서울특별시 강남구 역삼로 211','105동 860호','01471',37.506286,127.025610,'서울특별시','강남구','강남대로'),(66,66,'서울특별시 강남구 강남대로 56','104동 634호','08198',37.502017,127.027322,'서울특별시','강남구','강남대로'),(67,67,'서울특별시 강남구 테헤란로 57','102동 544호','02050',37.495089,127.023012,'서울특별시','강남구','강남대로'),(68,68,'서울특별시 강남구 테헤란로 113','106동 286호','02536',37.497475,127.020235,'서울특별시','강남구','강남대로'),(69,69,'서울특별시 강남구 강남대로 39','103동 747호','02144',37.493311,127.022033,'서울특별시','강남구','강남대로'),(70,70,'서울특별시 강남구 언주로 275','101동 186호','03103',37.499465,127.032342,'서울특별시','강남구','강남대로'),(71,71,'서울특별시 강남구 테헤란로 211','102동 213호','05974',37.502269,127.034674,'서울특별시','강남구','강남대로'),(72,72,'서울특별시 강남구 선릉로 259','107동 330호','06068',37.496917,127.038894,'서울특별시','강남구','강남대로'),(73,73,'서울특별시 강남구 테헤란로 219','102동 552호','06442',37.502930,127.036411,'서울특별시','강남구','강남대로'),(74,74,'서울특별시 강남구 테헤란로 179','107동 115호','04331',37.509679,127.030118,'서울특별시','강남구','강남대로'),(75,75,'서울특별시 강남구 강남대로 180','106동 361호','04991',37.490824,127.034018,'서울특별시','강남구','강남대로');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_room`
--

DROP TABLE IF EXISTS `chat_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_room` (
  `chat_room_id` bigint NOT NULL AUTO_INCREMENT,
  `landlord_id` bigint NOT NULL,
  `tenant_id` bigint NOT NULL,
  `room_id` bigint NOT NULL,
  `landload_unread_count` bigint NOT NULL,
  `tenant_unread_count` bigint NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`chat_room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_room`
--

LOCK TABLES `chat_room` WRITE;
/*!40000 ALTER TABLE `chat_room` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contract` (
  `contract_id` bigint NOT NULL AUTO_INCREMENT,
  `room_id` bigint DEFAULT NULL,
  `landlord_id` bigint NOT NULL,
  `tenant_id` bigint NOT NULL,
  `landlord_info_id` bigint NOT NULL,
  `tenant_info_id` bigint NOT NULL,
  `special_clause_id` bigint NOT NULL,
  `ipfs_key` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `aes_key` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `contract_status` enum('BEFORE_WRITE','LANDLORD_COMPLETED','TENANT_COMPLETED','TENANT_SIGNED','COMPLETED') COLLATE utf8mb4_general_ci NOT NULL,
  `landlord_auth` tinyint(1) NOT NULL,
  `tenant_auth` tinyint(1) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`contract_id`),
  UNIQUE KEY `landlord_info_id` (`landlord_info_id`),
  UNIQUE KEY `tenant_info_id` (`tenant_info_id`),
  UNIQUE KEY `special_clause_id` (`special_clause_id`),
  UNIQUE KEY `room_id` (`room_id`),
  KEY `idx_room_id` (`room_id`),
  KEY `idx_landlord_id` (`landlord_id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_special_clause_id` (`special_clause_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `image_id` bigint NOT NULL AUTO_INCREMENT,
  `room_id` bigint NOT NULL,
  `image_url` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  KEY `idx_room_id` (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=226 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,1,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room1.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(2,1,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room2.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(3,1,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room3.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(4,2,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room4.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(5,2,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room5.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(6,2,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room6.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(7,3,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room7.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(8,3,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room8.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(9,3,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room9.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(10,4,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room10.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(11,4,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room11.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(12,4,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room12.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(13,5,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room13.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(14,5,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room14.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(15,5,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room15.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(16,6,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room16.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(17,6,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room17.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(18,6,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room18.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(19,7,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room19.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(20,7,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room20.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(21,7,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room21.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(22,8,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room22.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(23,8,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room23.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(24,8,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room24.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(25,9,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room25.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(26,9,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room26.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(27,9,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room27.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(28,10,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room28.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(29,10,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room29.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(30,10,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room30.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(31,11,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room31.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(32,11,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room32.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(33,11,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room33.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(34,12,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room34.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(35,12,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room35.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(36,12,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room36.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(37,13,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room37.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(38,13,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room38.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(39,13,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room39.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(40,14,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room40.webp','2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(41,14,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room41.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(42,14,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room42.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(43,15,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room43.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(44,15,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room44.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(45,15,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room45.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(46,16,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room46.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(47,16,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room47.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(48,16,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room48.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(49,17,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room49.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(50,17,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room50.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(51,17,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room51.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(52,18,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room52.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(53,18,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room53.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(54,18,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room54.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(55,19,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room55.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(56,19,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room56.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(57,19,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room57.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(58,20,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room58.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(59,20,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room59.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(60,20,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room60.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(61,21,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room61.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(62,21,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room62.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(63,21,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room63.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(64,22,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room64.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(65,22,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room65.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(66,22,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room66.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(67,23,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room67.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(68,23,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room68.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(69,23,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room69.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(70,24,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room70.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(71,24,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room71.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(72,24,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room72.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(73,25,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room73.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(74,25,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room74.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(75,25,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room75.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(76,26,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room76.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(77,26,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room77.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(78,26,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room78.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(79,27,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room79.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(80,27,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room80.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(81,27,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room81.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(82,28,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room82.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(83,28,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room83.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(84,28,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room84.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(85,29,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room85.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(86,29,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room86.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(87,29,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room87.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(88,30,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room88.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(89,30,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room89.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(90,30,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room90.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(91,31,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room91.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(92,31,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room92.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(93,31,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room93.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(94,32,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room94.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(95,32,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room95.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(96,32,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room96.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(97,33,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room97.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(98,33,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room98.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(99,33,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room99.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(100,34,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room100.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(101,34,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room101.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(102,34,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room102.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(103,35,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room103.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(104,35,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room104.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(105,35,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room105.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(106,36,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room106.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(107,36,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room107.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(108,36,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room108.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(109,37,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room109.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(110,37,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room110.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(111,37,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room111.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(112,38,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room112.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(113,38,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room113.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(114,38,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room114.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(115,39,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room115.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(116,39,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room116.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(117,39,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room117.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(118,40,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room118.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(119,40,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room119.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(120,40,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room120.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(121,41,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room121.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(122,41,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room122.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(123,41,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room123.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(124,42,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room124.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(125,42,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room125.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(126,42,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room126.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(127,43,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room127.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(128,43,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room128.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(129,43,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room129.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(130,44,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room130.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(131,44,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room131.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(132,44,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room132.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(133,45,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room133.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(134,45,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room134.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(135,45,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room135.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(136,46,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room136.webp','2025-04-11 05:44:36','2025-04-11 05:44:36',NULL),(137,46,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room137.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(138,46,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room138.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(139,47,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room139.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(140,47,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room140.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(141,47,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room141.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(142,48,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room142.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(143,48,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room143.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(144,48,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room144.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(145,49,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room145.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(146,49,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room146.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(147,49,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room147.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(148,50,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room148.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(149,50,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room149.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(150,50,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room150.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(151,51,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room151.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(152,51,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room152.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(153,51,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room153.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(154,52,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room154.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(155,52,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room155.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(156,52,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room156.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(157,53,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room157.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(158,53,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room158.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(159,53,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room159.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(160,54,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room160.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(161,54,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room161.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(162,54,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room162.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(163,55,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room163.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(164,55,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room164.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(165,55,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room165.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(166,56,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room166.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(167,56,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room167.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(168,56,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room168.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(169,57,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room169.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(170,57,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room170.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(171,57,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room171.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(172,58,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room172.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(173,58,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room173.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(174,58,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room174.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(175,59,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room175.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(176,59,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room176.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(177,59,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room177.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(178,60,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room178.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(179,60,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room179.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(180,60,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room180.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(181,61,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room181.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(182,61,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room182.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(183,61,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room183.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(184,62,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room184.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(185,62,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room185.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(186,62,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room186.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(187,63,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room187.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(188,63,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room188.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(189,63,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room189.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(190,64,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room190.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(191,64,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room191.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(192,64,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room192.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(193,65,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room193.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(194,65,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room194.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(195,65,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room195.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(196,66,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room196.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(197,66,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room197.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(198,66,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room198.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(199,67,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room199.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(200,67,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room200.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(201,67,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room201.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(202,68,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room202.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(203,68,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room203.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(204,68,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room204.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(205,69,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room205.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(206,69,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room206.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(207,69,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room207.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(208,70,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room208.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(209,70,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room209.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(210,70,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room210.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(211,71,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room211.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(212,71,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room212.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(213,71,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room213.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(214,72,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room214.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(215,72,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room215.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(216,72,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room216.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(217,73,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room217.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(218,73,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room218.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(219,73,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room219.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(220,74,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room220.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(221,74,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room221.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(222,74,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room222.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(223,75,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room223.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(224,75,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room224.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL),(225,75,'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room225.webp','2025-04-11 05:44:37','2025-04-11 05:44:37',NULL);
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `landlord_info`
--

DROP TABLE IF EXISTS `landlord_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `landlord_info` (
  `landlord_info_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone_number` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `address` text COLLATE utf8mb4_general_ci,
  `resident_registration_number` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `rental_property_address` text COLLATE utf8mb4_general_ci,
  `property_structure` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `property_purpose` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `property_area` decimal(10,2) DEFAULT NULL,
  `priority_confirmed_date_yn` tinyint(1) DEFAULT NULL,
  `tax_arrears` tinyint(1) DEFAULT NULL,
  `rental_housing_land_type` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `rental_housing_land_area` decimal(10,2) DEFAULT NULL,
  `rental_part_address` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `rental_part_detail_address` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `rental_part_area` decimal(10,2) DEFAULT NULL,
  `contract_type` enum('NEW','RENEW_BY_AGREEMENT','EXTENSION') COLLATE utf8mb4_general_ci DEFAULT NULL,
  `lease_type` enum('MONTHLY_WITH_DEPOSIT','PURE_MONTHLY') COLLATE utf8mb4_general_ci DEFAULT NULL,
  `previous_lease_start_date` date DEFAULT NULL,
  `previous_lease_end_date` date DEFAULT NULL,
  `previous_deposit_amount` bigint DEFAULT NULL,
  `previous_monthly_rent` bigint DEFAULT NULL,
  `deposit_amount` bigint DEFAULT NULL,
  `monthly_rent` bigint DEFAULT NULL,
  `lease_start_date` date DEFAULT NULL,
  `lease_end_date` date DEFAULT NULL,
  `contract_fee` int DEFAULT NULL,
  `middle_fee` int DEFAULT NULL,
  `interim_payment_date` date DEFAULT NULL,
  `balance` int DEFAULT NULL,
  `balance_payment_date` date DEFAULT NULL,
  `monthly_rent_payment_date` varchar(2) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `monthly_rent_type` enum('PREPAID','POSTPAID') COLLATE utf8mb4_general_ci DEFAULT NULL,
  `fixed_management_fee` int DEFAULT NULL,
  `unfixed_management_fee` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `monthly_rent_account_bank` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `monthly_rent_account_number` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `facilities_repair_status` tinyint(1) DEFAULT NULL,
  `facilities_repair_content` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `repair_completion_by_balance_date` date DEFAULT NULL,
  `repair_completion_etc` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `not_repaired_by_balance_date` date DEFAULT NULL,
  `not_repaired_etc` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `landlord_burden` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `tenant_burden` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `contract_written_date` date DEFAULT NULL,
  `landlord_signature_url_1` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `landlord_signature_url_2` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `landlord_signature_url_3` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `landlord_signature_url_4` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`landlord_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `landlord_info`
--

LOCK TABLES `landlord_info` WRITE;
/*!40000 ALTER TABLE `landlord_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `landlord_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likes` (
  `like_id` bigint NOT NULL AUTO_INCREMENT,
  `room_id` bigint NOT NULL,
  `member_id` bigint NOT NULL,
  `flag` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`like_id`),
  KEY `idx_room_id` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maintenance_include`
--

DROP TABLE IF EXISTS `maintenance_include`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `maintenance_include` (
  `maintenance_id` bigint NOT NULL AUTO_INCREMENT,
  `room_id` bigint NOT NULL,
  `maintenance_include_name` enum('WATER','ELECTRICITY','INTERNET','GAS','CLEANING','CABLE_TV','PARKING','HEATING','ELEVATOR_MAINTENANCE') COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`maintenance_id`),
  KEY `idx_room_id` (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maintenance_include`
--

LOCK TABLES `maintenance_include` WRITE;
/*!40000 ALTER TABLE `maintenance_include` DISABLE KEYS */;
INSERT INTO `maintenance_include` VALUES (1,1,'WATER'),(2,1,'INTERNET'),(3,1,'ELECTRICITY'),(4,2,'GAS'),(5,3,'INTERNET'),(6,4,'CLEANING'),(7,5,'HEATING'),(8,6,'WATER'),(9,7,'WATER'),(10,8,'WATER'),(11,9,'WATER'),(12,10,'WATER'),(13,11,'WATER'),(14,12,'WATER'),(15,13,'WATER'),(16,14,'WATER'),(17,15,'WATER'),(18,16,'WATER'),(19,17,'WATER'),(20,18,'WATER'),(21,19,'WATER'),(22,20,'WATER'),(23,21,'WATER'),(24,22,'WATER'),(25,23,'WATER'),(26,24,'WATER'),(27,25,'WATER'),(28,26,'WATER'),(29,27,'WATER'),(30,28,'WATER'),(31,29,'WATER'),(32,30,'WATER'),(33,31,'WATER'),(34,32,'WATER'),(35,33,'WATER'),(36,34,'WATER'),(37,35,'WATER'),(38,36,'WATER'),(39,37,'WATER'),(40,38,'WATER'),(41,39,'WATER'),(42,40,'WATER'),(43,41,'WATER'),(44,42,'WATER'),(45,43,'WATER'),(46,44,'WATER'),(47,45,'WATER'),(48,46,'WATER'),(49,47,'WATER'),(50,48,'WATER'),(51,49,'WATER'),(52,50,'WATER'),(53,51,'WATER'),(54,52,'WATER'),(55,53,'WATER'),(56,54,'WATER'),(57,55,'WATER'),(58,56,'WATER'),(59,57,'WATER'),(60,58,'WATER'),(61,59,'WATER'),(62,60,'WATER'),(63,61,'WATER'),(64,62,'WATER'),(65,63,'WATER'),(66,64,'WATER'),(67,65,'WATER'),(68,66,'WATER'),(69,67,'WATER'),(70,68,'WATER'),(71,69,'WATER'),(72,70,'WATER'),(73,71,'WATER'),(74,72,'WATER'),(75,73,'WATER'),(76,74,'WATER'),(77,75,'WATER');
/*!40000 ALTER TABLE `maintenance_include` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` bigint NOT NULL AUTO_INCREMENT,
  `kakao_id` bigint NOT NULL,
  `name` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `nickname` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `birthday` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `profile_url` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `is_auth` tinyint(1) DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  KEY `idx_member_id` (`member_id`),
  KEY `idx_kakao_id` (`kakao_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,3904577474,'황인준','집주인','19970514','01030222851','https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/members/e1f801c5-b774-4a16-a2a2-22615c0cf8d7_room.png',1,'2025-04-11 05:44:32','2025-04-11 05:44:32',NULL),(2,987654321,'김철수','철수핑','1998-03-22','010-5678-1234','https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/members/e1f801c5-b774-4a16-a2a2-22615c0cf8d7_room.png',1,'2025-04-11 05:44:32','2025-04-11 05:44:32',NULL),(3,123456789,'김성수','테스터','1998-03-22','010-5678-1234','https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/members/e1f801c5-b774-4a16-a2a2-22615c0cf8d7_room.png',1,'2025-04-11 05:44:32','2025-04-11 05:44:32',NULL),(4,4206299294,'하정수','정수핑','1998-03-22','010-5678-1234','https://i.pinimg.com/236x/d8/a6/cb/d8a6cbb02bc2c5c27ae238db2e89425d.jpg',1,'2025-04-11 05:44:32','2025-04-11 05:44:32',NULL);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `memo`
--

DROP TABLE IF EXISTS `memo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `memo` (
  `memo_id` bigint NOT NULL AUTO_INCREMENT,
  `member_id` bigint NOT NULL,
  `room_id` bigint NOT NULL,
  `content` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`memo_id`),
  KEY `idx_room_member` (`room_id`,`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `memo`
--

LOCK TABLES `memo` WRITE;
/*!40000 ALTER TABLE `memo` DISABLE KEYS */;
/*!40000 ALTER TABLE `memo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `options`
--

DROP TABLE IF EXISTS `options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `options` (
  `option_id` bigint NOT NULL AUTO_INCREMENT,
  `room_id` bigint NOT NULL,
  `option_name` enum('ELEVATOR','ROOFTOP','AIR_CONDITIONER','WASHING_MACHINE','REFRIGERATOR','MICROWAVE','GAS_RANGE','INDUCTION','BED') COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`option_id`),
  KEY `idx_room_id` (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `options`
--

LOCK TABLES `options` WRITE;
/*!40000 ALTER TABLE `options` DISABLE KEYS */;
INSERT INTO `options` VALUES (1,1,'AIR_CONDITIONER'),(2,1,'REFRIGERATOR'),(3,1,'WASHING_MACHINE'),(4,2,'MICROWAVE'),(5,3,'INDUCTION'),(6,4,'BED'),(7,5,'ELEVATOR'),(8,6,'AIR_CONDITIONER'),(9,7,'AIR_CONDITIONER'),(10,8,'AIR_CONDITIONER'),(11,9,'AIR_CONDITIONER'),(12,10,'AIR_CONDITIONER'),(13,11,'AIR_CONDITIONER'),(14,12,'AIR_CONDITIONER'),(15,13,'AIR_CONDITIONER'),(16,14,'AIR_CONDITIONER'),(17,15,'AIR_CONDITIONER'),(18,16,'AIR_CONDITIONER'),(19,17,'AIR_CONDITIONER'),(20,18,'AIR_CONDITIONER'),(21,19,'AIR_CONDITIONER'),(22,20,'AIR_CONDITIONER'),(23,21,'AIR_CONDITIONER'),(24,22,'AIR_CONDITIONER'),(25,23,'AIR_CONDITIONER'),(26,24,'AIR_CONDITIONER'),(27,25,'AIR_CONDITIONER'),(28,26,'AIR_CONDITIONER'),(29,27,'AIR_CONDITIONER'),(30,28,'AIR_CONDITIONER'),(31,29,'AIR_CONDITIONER'),(32,30,'AIR_CONDITIONER'),(33,31,'AIR_CONDITIONER'),(34,32,'AIR_CONDITIONER'),(35,33,'AIR_CONDITIONER'),(36,34,'AIR_CONDITIONER'),(37,35,'AIR_CONDITIONER'),(38,36,'AIR_CONDITIONER'),(39,37,'AIR_CONDITIONER'),(40,38,'AIR_CONDITIONER'),(41,39,'AIR_CONDITIONER'),(42,40,'AIR_CONDITIONER'),(43,41,'AIR_CONDITIONER'),(44,42,'AIR_CONDITIONER'),(45,43,'AIR_CONDITIONER'),(46,44,'AIR_CONDITIONER'),(47,45,'AIR_CONDITIONER'),(48,46,'AIR_CONDITIONER'),(49,47,'AIR_CONDITIONER'),(50,48,'AIR_CONDITIONER'),(51,49,'AIR_CONDITIONER'),(52,50,'AIR_CONDITIONER'),(53,51,'AIR_CONDITIONER'),(54,52,'AIR_CONDITIONER'),(55,53,'AIR_CONDITIONER'),(56,54,'AIR_CONDITIONER'),(57,55,'AIR_CONDITIONER'),(58,56,'AIR_CONDITIONER'),(59,57,'AIR_CONDITIONER'),(60,58,'AIR_CONDITIONER'),(61,59,'AIR_CONDITIONER'),(62,60,'AIR_CONDITIONER'),(63,61,'AIR_CONDITIONER'),(64,62,'AIR_CONDITIONER'),(65,63,'AIR_CONDITIONER'),(66,64,'AIR_CONDITIONER'),(67,65,'AIR_CONDITIONER'),(68,66,'AIR_CONDITIONER'),(69,67,'AIR_CONDITIONER'),(70,68,'AIR_CONDITIONER'),(71,69,'AIR_CONDITIONER'),(72,70,'AIR_CONDITIONER'),(73,71,'AIR_CONDITIONER'),(74,72,'AIR_CONDITIONER'),(75,73,'AIR_CONDITIONER'),(76,74,'AIR_CONDITIONER'),(77,75,'AIR_CONDITIONER');
/*!40000 ALTER TABLE `options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `payment_id` bigint NOT NULL AUTO_INCREMENT,
  `imp_uid` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `merchant_uid` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `member_id` bigint NOT NULL,
  `room_id` bigint NOT NULL,
  `payment_status` enum('READY','PAID','FAILED') COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'READY',
  `pdf_url` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `json_url` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `real_estate_pdf`
--

DROP TABLE IF EXISTS `real_estate_pdf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `real_estate_pdf` (
  `pdf_id` bigint NOT NULL AUTO_INCREMENT,
  `member_id` bigint NOT NULL,
  `room_id` bigint NOT NULL,
  `pdf_url` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`pdf_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `real_estate_pdf`
--

LOCK TABLES `real_estate_pdf` WRITE;
/*!40000 ALTER TABLE `real_estate_pdf` DISABLE KEYS */;
/*!40000 ALTER TABLE `real_estate_pdf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `review_id` bigint NOT NULL AUTO_INCREMENT,
  `landlord_id` bigint NOT NULL,
  `tenant_id` bigint NOT NULL,
  `room_id` bigint NOT NULL,
  `real_estate_id` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `address_detail` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `content` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`review_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `room_id` bigint NOT NULL AUTO_INCREMENT,
  `member_id` bigint NOT NULL,
  `building_type` enum('ONEROOM_TWOROOM','APARTMENT','VILLA_HOUSE','OFFICETEL') COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` enum('UNDER_VERIFICATION','ON_SALE','SOLD_OUT') COLLATE utf8mb4_general_ci NOT NULL,
  `real_estate_id` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `deposit` int NOT NULL,
  `monthly_rent` int NOT NULL,
  `exclusive_area` decimal(10,2) DEFAULT NULL,
  `supply_area` decimal(10,2) DEFAULT NULL,
  `total_units` int DEFAULT NULL,
  `floor` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `max_floor` int DEFAULT NULL,
  `parking_spaces` int NOT NULL,
  `available_from` date NOT NULL,
  `permission_date` date NOT NULL,
  `simple_description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` text COLLATE utf8mb4_general_ci,
  `maintenance_cost` int NOT NULL,
  `room_cnt` int NOT NULL,
  `bathroom_cnt` int NOT NULL,
  `direction` enum('EAST','WEST','SOUTH','NORTH','NORTHWEST','NORTHEAST','SOUTHWEST','SOUTHEAST') COLLATE utf8mb4_general_ci NOT NULL,
  `verified` tinyint(1) NOT NULL,
  `registry_paid` tinyint(1) NOT NULL,
  `discussable` tinyint(1) NOT NULL,
  `discuss_detail` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `reviewable` tinyint(1) NOT NULL,
  `is_phone_public` tinyint(1) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`room_id`),
  KEY `idx_member_id` (`member_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,1,'APARTMENT','ON_SALE','1146-1999-533320',1170000,430000,45.75,55.32,100,'5층',15,1,'2025-05-01','2010-08-10','조용한 주택가에 위치한 아파트','가까운 거리에 마트와 지하철역이 있으며, 남향이라 채광이 좋습니다.',10,3,2,'SOUTH',1,1,1,'보증금 조절 협의 가능',1,0,'2025-04-11 05:44:32','2025-04-11 05:44:32',NULL),(2,1,'VILLA_HOUSE','ON_SALE','1146-1999-937154',2420000,630000,38.20,48.00,20,'2층',5,0,'2025-06-01','2015-03-05','아늑한 빌라, 정원 포함','한적한 동네에 위치한 빌라, 마당이 있어 반려동물과 생활하기 좋음.',5,2,1,'WEST',1,1,0,'보증금, 월세 협의 가능',1,1,'2025-04-11 05:44:32','2025-04-11 05:44:32',NULL),(3,1,'ONEROOM_TWOROOM','ON_SALE','1146-2003-052628',2880000,330000,25.00,30.00,10,'1층',3,0,'2025-04-15','2018-01-20','1.5룸 구조의 원룸','역세권에 위치, 풀옵션 원룸으로 자취 초보에게 적합.',8,2,1,'EAST',1,0,1,'단기임대도 가능',1,1,'2025-04-11 05:44:32','2025-04-11 05:44:32',NULL),(4,1,'OFFICETEL','ON_SALE','1146-2004-178014',2190000,920000,42.00,52.00,300,'12층',20,2,'2025-07-01','2020-11-11','테라스 있는 오피스텔','회사와 가까운 위치, 혼자 살기 좋은 구조와 뷰.',15,2,1,'SOUTHEAST',1,1,1,'주차공간 넉넉함',1,0,'2025-04-11 05:44:32','2025-04-11 05:44:32',NULL),(5,1,'APARTMENT','ON_SALE','1146-2020-348901',2700000,720000,50.00,60.00,150,'7층',18,1,'2025-08-10','2005-09-01','학군 좋은 아파트','근처에 초·중·고 위치, 가족 단위 거주에 적합.',12,3,2,'NORTHEAST',1,1,1,'조용한 단지 내 위치',1,1,'2025-04-11 05:44:32','2025-04-11 05:44:32',NULL),(6,3,'APARTMENT','ON_SALE','1146-2006-645729',1160000,1180000,51.44,42.22,72,'12층',18,1,'2025-05-10','2020-04-11','강변 근처 아파트','한강 조망 가능, 산책로와 공원 가까움',11,3,1,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:32','2025-04-11 05:44:32',NULL),(7,3,'ONEROOM_TWOROOM','ON_SALE','1146-1997-595704',2880000,480000,27.21,56.41,52,'2층',20,1,'2025-05-11','2019-04-12','역세권 신축 오피스텔','지하철 도보 3분 거리, 깔끔한 내부 구조',13,1,2,'WEST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:32','2025-04-11 05:44:32',NULL),(8,2,'VILLA_HOUSE','ON_SALE','1146-2008-943212',2670000,1080000,48.88,37.47,199,'8층',17,0,'2025-05-12','2018-04-12','조용한 단독주택','단독 주택 단지, 이웃 간섭 없는 환경',11,2,2,'SOUTH',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:32','2025-04-11 05:44:32',NULL),(9,1,'OFFICETEL','ON_SALE','1146-2004-174942',1970000,710000,42.77,41.24,136,'5층',20,1,'2025-05-13','2017-04-12','학세권 투룸','학교와 도서관 인근, 학생 또는 교직원 추천',5,2,2,'NORTH',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:32','2025-04-11 05:44:32',NULL),(10,3,'APARTMENT','ON_SALE','1146-2021-658417',2290000,1130000,49.57,62.87,11,'9층',10,0,'2025-05-14','2016-04-12','주차 공간 넉넉한 빌라','차량 2대 이상 주차 가능, 도로 접근성 좋음',12,2,2,'NORTHWEST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:32','2025-04-11 05:44:32',NULL),(11,1,'ONEROOM_TWOROOM','ON_SALE','1146-2015-729907',1600000,930000,36.23,42.01,200,'15층',17,0,'2025-05-15','2015-04-13','강변 근처 아파트','한강 조망 가능, 산책로와 공원 가까움',11,2,2,'NORTHEAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:32','2025-04-11 05:44:32',NULL),(12,1,'VILLA_HOUSE','ON_SALE','1146-2010-919524',1250000,1050000,46.10,55.97,39,'6층',19,0,'2025-05-16','2014-04-13','역세권 신축 오피스텔','지하철 도보 3분 거리, 깔끔한 내부 구조',9,1,2,'SOUTHWEST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:32','2025-04-11 05:44:32',NULL),(13,1,'OFFICETEL','ON_SALE','1146-1999-704989',1380000,330000,43.99,64.48,111,'10층',11,1,'2025-05-17','2013-04-13','조용한 단독주택','단독 주택 단지, 이웃 간섭 없는 환경',14,3,1,'SOUTHEAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:33','2025-04-11 05:44:33',NULL),(14,2,'APARTMENT','ON_SALE','1146-2021-096211',2150000,840000,47.10,37.68,20,'11층',15,0,'2025-05-18','2012-04-13','학세권 투룸','학교와 도서관 인근, 학생 또는 교직원 추천',7,2,1,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:33','2025-04-11 05:44:33',NULL),(15,1,'ONEROOM_TWOROOM','ON_SALE','1146-2015-010956',1560000,980000,28.56,36.13,180,'7층',9,1,'2025-05-19','2011-04-14','주차 공간 넉넉한 빌라','차량 2대 이상 주차 가능, 도로 접근성 좋음',7,2,1,'WEST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:33','2025-04-11 05:44:33',NULL),(16,1,'VILLA_HOUSE','ON_SALE','1146-2023-085792',2520000,900000,49.16,43.49,12,'15층',17,2,'2025-05-20','2010-04-14','강변 근처 아파트','한강 조망 가능, 산책로와 공원 가까움',6,2,1,'SOUTH',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:33','2025-04-11 05:44:33',NULL),(17,1,'OFFICETEL','ON_SALE','1146-2024-720020',2310000,510000,51.65,60.10,159,'1층',1,2,'2025-05-21','2009-04-14','역세권 신축 오피스텔','지하철 도보 3분 거리, 깔끔한 내부 구조',5,2,1,'NORTH',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:33','2025-04-11 05:44:33',NULL),(18,3,'APARTMENT','ON_SALE','1146-2019-960903',2170000,740000,51.67,53.92,96,'1층',12,1,'2025-05-22','2008-04-14','조용한 단독주택','단독 주택 단지, 이웃 간섭 없는 환경',12,2,1,'NORTHWEST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:33','2025-04-11 05:44:33',NULL),(19,2,'ONEROOM_TWOROOM','ON_SALE','1146-1995-694557',2320000,300000,25.37,56.21,183,'3층',12,0,'2025-05-23','2007-04-15','학세권 투룸','학교와 도서관 인근, 학생 또는 교직원 추천',9,2,2,'NORTHEAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:33','2025-04-11 05:44:33',NULL),(20,1,'VILLA_HOUSE','ON_SALE','1146-2014-440414',1150000,740000,33.01,59.99,188,'15층',18,2,'2025-05-24','2006-04-15','주차 공간 넉넉한 빌라','차량 2대 이상 주차 가능, 도로 접근성 좋음',14,2,2,'SOUTHWEST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:33','2025-04-11 05:44:33',NULL),(21,3,'OFFICETEL','ON_SALE','1146-2003-882938',1460000,300000,30.74,36.32,200,'8층',19,2,'2025-05-25','2005-04-15','강변 근처 아파트','한강 조망 가능, 산책로와 공원 가까움',12,3,2,'SOUTHEAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:33','2025-04-11 05:44:33',NULL),(22,3,'APARTMENT','ON_SALE','1146-2013-159162',2460000,820000,38.41,50.75,106,'3층',16,1,'2025-05-26','2004-04-15','역세권 신축 오피스텔','지하철 도보 3분 거리, 깔끔한 내부 구조',15,3,2,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:33','2025-04-11 05:44:33',NULL),(23,2,'ONEROOM_TWOROOM','ON_SALE','1146-2010-163505',2640000,500000,50.76,62.34,12,'9층',19,0,'2025-05-27','2003-04-16','조용한 단독주택','단독 주택 단지, 이웃 간섭 없는 환경',9,3,2,'WEST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:33','2025-04-11 05:44:33',NULL),(24,1,'VILLA_HOUSE','ON_SALE','1146-2010-861930',2340000,720000,31.00,48.79,197,'6층',11,2,'2025-05-28','2002-04-16','학세권 투룸','학교와 도서관 인근, 학생 또는 교직원 추천',5,3,1,'SOUTH',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:33','2025-04-11 05:44:33',NULL),(25,2,'OFFICETEL','ON_SALE','1146-2016-023667',1590000,950000,25.47,49.43,138,'1층',16,2,'2025-05-29','2001-04-16','주차 공간 넉넉한 빌라','차량 2대 이상 주차 가능, 도로 접근성 좋음',15,1,1,'NORTH',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:33','2025-04-11 05:44:33',NULL),(26,3,'APARTMENT','ON_SALE','1146-2003-576831',2150000,950000,26.47,46.93,77,'8층',20,0,'2025-05-30','2000-04-16','강변 근처 아파트','한강 조망 가능, 산책로와 공원 가까움',10,2,2,'NORTHWEST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:33','2025-04-11 05:44:33',NULL),(27,2,'ONEROOM_TWOROOM','ON_SALE','1146-2023-009189',1180000,710000,48.99,50.74,31,'14층',15,1,'2025-05-31','1999-04-17','역세권 신축 오피스텔','지하철 도보 3분 거리, 깔끔한 내부 구조',8,1,2,'NORTHEAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:33','2025-04-11 05:44:33',NULL),(28,1,'VILLA_HOUSE','ON_SALE','1146-2000-172495',1480000,480000,28.76,30.07,195,'11층',18,1,'2025-06-01','1998-04-17','조용한 단독주택','단독 주택 단지, 이웃 간섭 없는 환경',5,3,1,'SOUTHWEST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:33','2025-04-11 05:44:33',NULL),(29,2,'OFFICETEL','ON_SALE','1146-2011-574487',1390000,570000,41.48,56.73,124,'15층',18,0,'2025-06-02','1997-04-17','학세권 투룸','학교와 도서관 인근, 학생 또는 교직원 추천',13,3,2,'SOUTHEAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:33','2025-04-11 05:44:33',NULL),(30,1,'APARTMENT','ON_SALE','1146-2016-360971',1470000,640000,25.42,62.61,123,'10층',12,2,'2025-06-03','1996-04-17','주차 공간 넉넉한 빌라','차량 2대 이상 주차 가능, 도로 접근성 좋음',10,2,2,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:33','2025-04-11 05:44:33',NULL),(31,3,'ONEROOM_TWOROOM','ON_SALE','1146-2017-903881',2270000,940000,28.33,40.00,113,'8층',14,2,'2025-06-04','1995-04-18','강변 근처 아파트','한강 조망 가능, 산책로와 공원 가까움',8,1,1,'WEST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:33','2025-04-11 05:44:33',NULL),(32,1,'VILLA_HOUSE','ON_SALE','1146-2024-479534',2580000,440000,31.25,40.21,160,'8층',18,1,'2025-06-05','1994-04-18','역세권 신축 오피스텔','지하철 도보 3분 거리, 깔끔한 내부 구조',6,3,2,'SOUTH',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:33','2025-04-11 05:44:33',NULL),(33,2,'OFFICETEL','ON_SALE','1146-2009-684263',1220000,930000,37.95,56.56,61,'8층',11,1,'2025-06-06','1993-04-18','조용한 단독주택','단독 주택 단지, 이웃 간섭 없는 환경',15,2,1,'NORTH',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:33','2025-04-11 05:44:33',NULL),(34,3,'APARTMENT','ON_SALE','1146-2019-033479',2760000,370000,49.91,31.22,134,'8층',19,1,'2025-06-07','1992-04-18','학세권 투룸','학교와 도서관 인근, 학생 또는 교직원 추천',14,2,2,'NORTHWEST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:33','2025-04-11 05:44:33',NULL),(35,1,'ONEROOM_TWOROOM','ON_SALE','1146-2003-401064',1050000,700000,36.37,50.79,118,'3층',11,2,'2025-06-08','1991-04-19','주차 공간 넉넉한 빌라','차량 2대 이상 주차 가능, 도로 접근성 좋음',14,2,2,'NORTHEAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:33','2025-04-11 05:44:33',NULL),(36,1,'APARTMENT','ON_SALE','1146-2014-621261',9000000,650000,25.46,48.32,129,'4층',6,2,'2025-05-10','2020-04-11','지하철역 근처 근처 빌라, 편의시설 밀접. 인근에 공원과 산책로가 있어 여가 생활에도 좋습니다.','지하철역 근처 빌라, 편의시설 밀접',15,3,2,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:33','2025-04-11 05:44:33',NULL),(37,1,'APARTMENT','ON_SALE','1146-2003-265792',5000000,1350000,35.58,37.44,97,'14층',19,1,'2025-05-10','2020-04-11','강변 리모델링 완료, 주차 가능','강변 근처 리모델링 완료, 주차 가능. 인근에 공원과 산책로가 있어 여가 생활에도 좋습니다.',5,3,2,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:33','2025-04-11 05:44:33',NULL),(38,1,'APARTMENT','ON_SALE','1146-2006-700081',1500000,760000,42.12,52.56,163,'15층',19,1,'2025-05-10','2020-04-11','강변 복층 구조, 햇빛 잘 듬','강변 근처 복층 구조, 햇빛 잘 듬. 최근 리모델링으로 내외부 상태가 매우 우수합니다.',14,2,1,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:34','2025-04-11 05:44:34',NULL),(39,1,'APARTMENT','ON_SALE','1146-2004-164863',20000000,1000000,49.28,61.82,175,'5층',10,1,'2025-05-10','2020-04-11','남산 신축 건물, 층간소음 적음','남산 근처 신축 건물, 층간소음 적음. 교통 접근성이 뛰어나 출퇴근이 편리합니다.',6,1,2,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:34','2025-04-11 05:44:34',NULL),(40,1,'APARTMENT','ON_SALE','1146-2010-677388',1500000,600000,44.66,63.16,30,'8층',15,0,'2025-05-10','2020-04-11','남산 아파트, 편의시설 밀접','남산 근처 아파트, 편의시설 밀접. 생활 편의시설이 가까워 일상생활이 매우 편리합니다.',5,1,2,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:34','2025-04-11 05:44:34',NULL),(41,1,'APARTMENT','ON_SALE','1146-2022-675159',10000000,900000,39.81,36.90,41,'9층',9,1,'2025-05-10','2020-04-11','조용한 주택가 쓰리룸, 보안 철저','조용한 주택가 근처 쓰리룸, 보안 철저. 넓은 실내 공간과 효율적인 구조로 거주 만족도가 높습니다.',6,1,2,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:34','2025-04-11 05:44:34',NULL),(42,1,'APARTMENT','ON_SALE','1146-2018-369670',1460000,950000,32.10,32.42,40,'13층',16,1,'2025-05-10','2020-04-11','강변 테라스 포함, 관리비 저렴','강변 근처 테라스 포함, 관리비 저렴. 넓은 실내 공간과 효율적인 구조로 거주 만족도가 높습니다.',8,3,2,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:34','2025-04-11 05:44:34',NULL),(43,1,'APARTMENT','ON_SALE','1146-1996-218872',1560000,1030000,48.18,41.02,33,'14층',18,1,'2025-05-10','2020-04-11','상업지구 아파트, 엘리베이터 있음','상업지구 근처 아파트, 엘리베이터 있음. 최근 리모델링으로 내외부 상태가 매우 우수합니다.',13,3,1,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:34','2025-04-11 05:44:34',NULL),(44,1,'APARTMENT','ON_SALE','1146-2014-096395',1500000,100000,40.31,64.20,27,'12층',13,1,'2025-05-10','2020-04-11','도심 투룸, 햇빛 잘 듬','도심 근처 투룸, 햇빛 잘 듬. 보안 시스템이 잘 갖춰져 있어 안심하고 생활할 수 있습니다.',14,3,2,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:34','2025-04-11 05:44:34',NULL),(45,1,'APARTMENT','ON_SALE','1146-1999-680470',2000000,800000,34.99,50.91,64,'8층',14,2,'2025-05-10','2020-04-11','조용한 주택가 아파트, 햇빛 잘 듬','조용한 주택가 근처 아파트, 햇빛 잘 듬. 채광이 좋고 통풍이 잘 되어 쾌적한 실내 환경을 제공합니다.',14,3,1,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:34','2025-04-11 05:44:34',NULL),(46,4,'APARTMENT','ON_SALE','1146-2011-821167',3000000,700000,35.12,50.76,71,'8층',8,0,'2025-05-10','2020-04-11','상업지구 복층 구조, 관리비 저렴','상업지구 근처 복층 구조, 관리비 저렴. 주변 환경이 조용하고 안전하여 가족 단위 거주에 적합합니다.',10,3,1,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:34','2025-04-11 05:44:34',NULL),(47,4,'APARTMENT','ON_SALE','1146-2019-678172',2600000,1140000,44.48,46.73,131,'15층',17,0,'2025-05-10','2020-04-11','버스정류장 앞 룸, 햇빛 잘 듬','버스정류장 앞 근처 쓰리룸, 햇빛 잘 듬. 채광이 좋고 통풍이 잘 되어 쾌적한 실내 환경을 제공합니다.',9,3,2,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:34','2025-04-11 05:44:34',NULL),(48,4,'ONEROOM_TWOROOM','ON_SALE','1146-2016-932364',1820000,320000,25.31,46.74,124,'4층',14,0,'2025-05-10','2020-04-11','대학교 근처 테라스 포함, 층간소음 적음','대학교 근처 근처 테라스 포함, 층간소음 적음. 최근 리모델링으로 내외부 상태가 매우 우수합니다.',7,3,1,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:34','2025-04-11 05:44:34',NULL),(49,4,'ONEROOM_TWOROOM','ON_SALE','1146-2018-450116',10000000,700000,39.99,50.37,163,'9층',16,1,'2025-05-10','2020-04-11','지하철역 근처 투룸, 햇빛 잘 듬','지하철역 근처 근처 투룸, 햇빛 잘 듬. 주변 환경이 조용하고 안전하여 가족 단위 거주에 적합합니다.',7,3,2,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:34','2025-04-11 05:44:34',NULL),(50,4,'APARTMENT','ON_SALE','1146-2019-865445',8000000,1000000,38.02,52.36,45,'5층',13,0,'2025-05-10','2020-04-11','한강 아파트, 편의시설 밀접','한강 근처 아파트, 편의시설 밀접. 보안 시스템이 잘 갖춰져 있어 안심하고 생활할 수 있습니다.',5,3,2,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:34','2025-04-11 05:44:34',NULL),(51,4,'APARTMENT','ON_SALE','1146-2014-806449',14000000,880000,43.31,36.73,47,'8층',14,2,'2025-05-10','2020-04-11','남산 신축 건물, 층간소음 적음','남산 근처 신축 건물, 층간소음 적음. 교통 접근성이 뛰어나 출퇴근이 편리합니다.',9,3,2,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:34','2025-04-11 05:44:34',NULL),(52,4,'APARTMENT','ON_SALE','1146-2003-369926',11500000,800000,29.04,57.13,111,'4층',9,0,'2025-05-10','2020-04-11','남산 아파트, 편의시설 밀접','남산 근처 아파트, 편의시설 밀접. 생활 편의시설이 가까워 일상생활이 매우 편리합니다.',15,3,2,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:34','2025-04-11 05:44:34',NULL),(53,4,'ONEROOM_TWOROOM','ON_SALE','1146-2016-829723',2710000,760000,30.54,55.89,98,'14층',17,2,'2025-05-10','2020-04-11','한강 복층 구조, 보안 철저','한강 근처 복층 구조, 보안 철저. 넓은 실내 공간과 효율적인 구조로 거주 만족도가 높습니다.',12,1,1,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:34','2025-04-11 05:44:34',NULL),(54,4,'VILLA_HOUSE','ON_SALE','1146-1996-087710',1190000,1160000,49.47,37.28,162,'13층',19,2,'2025-05-10','2020-04-11','도심 빌라, 주차 가능','도심 근처 빌라, 주차 가능. 보안 시스템이 잘 갖춰져 있어 안심하고 생활할 수 있습니다.',13,3,1,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:34','2025-04-11 05:44:34',NULL),(55,1,'APARTMENT','ON_SALE','1146-1995-660000',15000000,1000000,47.33,58.79,185,'12층',16,2,'2025-05-10','2020-04-11','조용한 주택가 투룸, 주차 가능','조용한 주택가 근처 투룸, 주차 가능. 넓은 실내 공간과 효율적인 구조로 거주 만족도가 높습니다.',14,2,2,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:34','2025-04-11 05:44:34',NULL),(56,1,'ONEROOM_TWOROOM','ON_SALE','1146-2002-684165',5000000,550000,35.22,56.50,187,'10층',15,1,'2025-05-10','2020-04-11','버스정류장 앞 복층 구조, 관리비 저렴','버스정류장 앞 근처 복층 구조, 관리비 저렴. 생활 편의시설이 가까워 일상생활이 매우 편리합니다.',9,3,2,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:34','2025-04-11 05:44:34',NULL),(57,1,'APARTMENT','ON_SALE','1146-1995-625209',2620000,800000,36.69,37.65,144,'7층',7,0,'2025-05-10','2020-04-11','강변 테라스 포함, 층간소음 적음','강변 근처 테라스 포함, 층간소음 적음. 최근 리모델링으로 내외부 상태가 매우 우수합니다.',15,2,2,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:34','2025-04-11 05:44:34',NULL),(58,4,'VILLA_HOUSE','ON_SALE','1146-2010-384042',4000000,700000,29.48,30.21,160,'10층',13,0,'2025-05-10','2020-04-11','지하철역 근처 빌라, 층간소음 적음','지하철역 근처 근처 빌라, 층간소음 적음. 넓은 실내 공간과 효율적인 구조로 거주 만족도가 높습니다.',15,3,1,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:34','2025-04-11 05:44:34',NULL),(59,4,'ONEROOM_TWOROOM','ON_SALE','1146-2002-986125',3000000,700000,53.18,37.84,136,'15층',17,1,'2025-05-10','2020-04-11','조용한 주택가 투룸, 관리비 저렴','조용한 주택가 근처 투룸, 관리비 저렴. 채광이 좋고 통풍이 잘 되어 쾌적한 실내 환경을 제공합니다.',7,3,2,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:34','2025-04-11 05:44:34',NULL),(60,4,'APARTMENT','ON_SALE','1146-2024-897135',4500000,500000,34.64,31.72,179,'10층',17,2,'2025-05-10','2020-04-11','대학교 근처 리모델링 완료, 보안 철저','대학교 근처 근처 리모델링 완료, 보안 철저. 생활 편의시설이 가까워 일상생활이 매우 편리합니다.',12,3,1,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:34','2025-04-11 05:44:34',NULL),(61,4,'ONEROOM_TWOROOM','ON_SALE','1146-1996-115465',6800000,800000,28.01,56.39,51,'11층',16,0,'2025-05-10','2020-04-11','한강 원룸, 주차 가능','한강 근처 원룸, 주차 가능. 최근 리모델링으로 내외부 상태가 매우 우수합니다.',8,1,1,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:34','2025-04-11 05:44:34',NULL),(62,4,'ONEROOM_TWOROOM','ON_SALE','1146-2018-933054',5000000,900000,28.11,51.73,37,'11층',20,2,'2025-05-10','2020-04-11','강변 쓰리룸, 반려동물 가능','강변 근처 쓰리룸, 반려동물 가능. 넓은 실내 공간과 효율적인 구조로 거주 만족도가 높습니다.',5,1,2,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(63,4,'VILLA_HOUSE','ON_SALE','1146-2008-895660',11000000,650000,31.28,56.59,86,'8층',17,0,'2025-05-10','2020-04-11','버스정류장 앞 리모델링 완료, 관리비 저렴','버스정류장 앞 근처 리모델링 완료, 관리비 저렴. 채광이 좋고 통풍이 잘 되어 쾌적한 실내 환경을 제공합니다.',9,2,2,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(64,4,'ONEROOM_TWOROOM','ON_SALE','1146-2008-550343',4000000,600000,44.62,33.11,49,'3층',7,2,'2025-05-10','2020-04-11','조용한 주택가 원룸, 채광 좋음','조용한 주택가 근처 원룸, 채광 좋음. 인근에 공원과 산책로가 있어 여가 생활에도 좋습니다.',11,2,1,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(65,4,'ONEROOM_TWOROOM','ON_SALE','1146-2023-315688',3000000,400000,29.09,52.48,188,'15층',19,0,'2025-05-10','2020-04-11','대학교 근처 원룸, 편의시설 밀접','대학교 근처 근처 원룸, 편의시설 밀접. 생활 편의시설이 가까워 일상생활이 매우 편리합니다.',5,3,2,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(66,4,'VILLA_HOUSE','ON_SALE','1146-1996-540704',7000000,600000,32.32,46.70,192,'6층',7,0,'2025-05-10','2020-04-11','상업지구 리모델링 완료, 반려동물 가능','상업지구 근처 리모델링 완료, 반려동물 가능. 넓은 실내 공간과 효율적인 구조로 거주 만족도가 높습니다.',6,2,1,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(67,4,'APARTMENT','ON_SALE','1146-2021-759644',20000000,1500000,38.26,44.16,88,'3층',3,2,'2025-05-10','2020-04-11','한강 신축 건물, 주차 가능','한강 근처 신축 건물, 주차 가능. 인근에 공원과 산책로가 있어 여가 생활에도 좋습니다.',15,2,2,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(68,4,'ONEROOM_TWOROOM','ON_SALE','1146-2010-318611',5000000,600000,42.47,63.62,118,'4층',19,1,'2025-05-10','2020-04-11','대학교 근처 투룸, 보안 철저','대학교 근처 근처 투룸, 보안 철저. 생활 편의시설이 가까워 일상생활이 매우 편리합니다.',5,2,1,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(69,4,'APARTMENT','ON_SALE','1146-2017-536012',12000000,900000,31.18,55.91,80,'5층',10,0,'2025-05-10','2020-04-11','도심 테라스 포함, 편의시설 밀접','도심 근처 테라스 포함, 편의시설 밀접. 주변 환경이 조용하고 안전하여 가족 단위 거주에 적합합니다.',15,3,2,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(70,4,'ONEROOM_TWOROOM','ON_SALE','1146-1997-259072',2000000,500000,31.41,49.47,36,'4층',7,1,'2025-05-10','2020-04-11','버스정류장 앞 원룸, 햇빛 잘 듬','버스정류장 앞 근처 원룸, 햇빛 잘 듬. 교통 접근성이 뛰어나 출퇴근이 편리합니다.',13,2,2,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(71,4,'ONEROOM_TWOROOM','ON_SALE','1146-2008-812340',8000000,700000,54.08,63.10,127,'8층',16,2,'2025-05-10','2020-04-11','강변 복층 구조, 햇빛 잘 듬','강변 근처 복층 구조, 햇빛 잘 듬. 최근 리모델링으로 내외부 상태가 매우 우수합니다.',6,1,1,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(72,4,'APARTMENT','ON_SALE','1146-2008-911809',13000000,1000000,30.96,36.55,88,'9층',10,2,'2025-05-10','2020-04-11','상업지구 아파트, 엘리베이터 있음','상업지구 근처 아파트, 엘리베이터 있음. 최근 리모델링으로 내외부 상태가 매우 우수합니다.',12,3,1,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(73,4,'ONEROOM_TWOROOM','ON_SALE','1146-1996-826688',4500000,600000,48.04,54.66,78,'9층',12,2,'2025-05-10','2020-04-11','도심 투룸, 햇빛 잘 듬','도심 근처 투룸, 햇빛 잘 듬. 보안 시스템이 잘 갖춰져 있어 안심하고 생활할 수 있습니다.',15,1,1,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(74,4,'OFFICETEL','ON_SALE','1146-2004-476704',15000000,900000,33.94,59.07,59,'8층',13,2,'2025-05-10','2020-04-11','남산 신축 건물, 층간소음 적음','남산 근처 신축 건물, 층간소음 적음. 교통 접근성이 뛰어나 출퇴근이 편리합니다.',6,3,2,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:35','2025-04-11 05:44:35',NULL),(75,4,'OFFICETEL','ON_SALE','1146-2015-430188',10000000,700000,34.97,50.32,181,'12층',15,1,'2025-05-10','2020-04-11','버스정류장 앞 리모델링 완료, 관리비 저렴','버스정류장 앞 근처 리모델링 완료, 관리비 저렴. 채광이 좋고 통풍이 잘 되어 쾌적한 실내 환경을 제공합니다.',9,1,2,'EAST',1,1,1,'임대 조건 협의 가능',1,0,'2025-04-11 05:44:35','2025-04-11 05:44:35',NULL);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `special_clause`
--

DROP TABLE IF EXISTS `special_clause`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `special_clause` (
  `special_clause_id` bigint NOT NULL AUTO_INCREMENT,
  `move_in_registration_date` date DEFAULT NULL,
  `unpaid_amount` int DEFAULT NULL,
  `dispute_resolution` tinyint(1) DEFAULT NULL,
  `is_housing_reconstruction_planned` tinyint(1) DEFAULT NULL,
  `construction_period` date DEFAULT NULL,
  `estimated_construction_duration` int DEFAULT NULL,
  `is_detailed_address_consent_given` tinyint(1) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`special_clause_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `special_clause`
--

LOCK TABLES `special_clause` WRITE;
/*!40000 ALTER TABLE `special_clause` DISABLE KEYS */;
/*!40000 ALTER TABLE `special_clause` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `special_clause_etc`
--

DROP TABLE IF EXISTS `special_clause_etc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `special_clause_etc` (
  `special_clause_id` bigint NOT NULL,
  `etc_value` text COLLATE utf8mb4_general_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `special_clause_etc`
--

LOCK TABLES `special_clause_etc` WRITE;
/*!40000 ALTER TABLE `special_clause_etc` DISABLE KEYS */;
/*!40000 ALTER TABLE `special_clause_etc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_info`
--

DROP TABLE IF EXISTS `tenant_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_info` (
  `tenant_info_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `address` text COLLATE utf8mb4_general_ci,
  `resident_registration_number` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `move_in_date` date DEFAULT NULL,
  `tenant_signature_url` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`tenant_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_info`
--

LOCK TABLES `tenant_info` WRITE;
/*!40000 ALTER TABLE `tenant_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-11  5:49:23
