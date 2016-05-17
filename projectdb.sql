-- MySQL dump 10.13  Distrib 5.6.24, for Win32 (x86)
--
-- Host: localhost    Database: vintage_advantage
-- ------------------------------------------------------
-- Server version	5.6.26-log

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (11,'m.keith@fake.com','11111111'),(12,'j.hu@fake.com','22222222'),(13,'b.white@fake.com','11111111'),(14,'r.elmasri@fake.com','11111111'),(15,'a.hyatt@fake.com','11111111'),(16,'gobetterkx@gmail.com','10610050');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `line1` varchar(100) NOT NULL,
  `line2` varchar(100) DEFAULT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  `country` varchar(50) DEFAULT 'US',
  `zipcode` varchar(10) NOT NULL,
  `lname` varchar(20) NOT NULL,
  `fname` varchar(20) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `addr_fk_cust` (`customer_id`),
  CONSTRAINT `addr_fk_cust` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (20,'300 Boston Post Rd','','West Haven','CT',NULL,'06513','Keith','Mike',7),(22,'211 Humphrey St','#24','New Haven','CT',NULL,'06511','Keith','Mike',7),(23,'36 huntington rd. apt 2r','','new haven','ct',NULL,'06512','kang','xue',12),(24,'267 humphrey st. apt 7','','new haven','ct',NULL,'06511','kang','xue',12);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creditcard`
--

DROP TABLE IF EXISTS `creditcard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `creditcard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ccnumber` varchar(20) NOT NULL,
  `card_expir_date` varchar(7) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `address_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ccnumber` (`ccnumber`),
  KEY `cc_fk_cust` (`customer_id`),
  KEY `cc_fk_addr` (`address_id`),
  CONSTRAINT `cc_fk_addr` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cc_fk_cust` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditcard`
--

LOCK TABLES `creditcard` WRITE;
/*!40000 ALTER TABLE `creditcard` DISABLE KEYS */;
/*!40000 ALTER TABLE `creditcard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `account_id` int(11) DEFAULT NULL,
  `address_id_dft` int(11) DEFAULT NULL,
  `cc_id_dft` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_id` (`account_id`),
  KEY `cust_fk_addr` (`address_id_dft`),
  KEY `cust_fk_cc` (`cc_id_dft`),
  CONSTRAINT `cust_fk_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `cust_fk_addr` FOREIGN KEY (`address_id_dft`) REFERENCES `address` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `cust_fk_cc` FOREIGN KEY (`cc_id_dft`) REFERENCES `creditcard` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (7,'Mike','Keith','2037778888',11,22,NULL),(8,'Jian','Hu',NULL,12,NULL,NULL),(9,'Bob','White',NULL,13,NULL,NULL),(10,'Ramez','Elmasri',NULL,14,NULL,NULL),(11,'Alice','Hyatt',NULL,15,NULL,NULL),(12,'xue','kang','4752275263',16,23,NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_order`
--

DROP TABLE IF EXISTS `customer_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(20) NOT NULL,
  `customer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_fk_customer` (`customer_id`),
  CONSTRAINT `order_fk_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_order`
--

LOCK TABLES `customer_order` WRITE;
/*!40000 ALTER TABLE `customer_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_line`
--

DROP TABLE IF EXISTS `order_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_line` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wine_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `order_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `orderline_fk_wine` (`wine_id`),
  KEY `orderline_fk_order` (`order_id`),
  CONSTRAINT `orderline_fk_order` FOREIGN KEY (`order_id`) REFERENCES `customer_order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orderline_fk_wine` FOREIGN KEY (`wine_id`) REFERENCES `wine` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_line`
--

LOCK TABLES `order_line` WRITE;
/*!40000 ALTER TABLE `order_line` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wine`
--

DROP TABLE IF EXISTS `wine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(4000) NOT NULL,
  `region` varchar(255) NOT NULL,
  `rating` int(11) NOT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `year` varchar(4) NOT NULL,
  `varietal` varchar(500) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wine`
--

LOCK TABLES `wine` WRITE;
/*!40000 ALTER TABLE `wine` DISABLE KEYS */;
INSERT INTO `wine` VALUES (1,'Beringer Private Reserve Cabernet Sauvignon 2012','The 2012 vintage began with textbook conditions that continued through much ofHarvest. Spring bud break was ideal with steady flowering and even fruit set. A lengthy stretch of warm days and cool nights during the summer months allowed for optimal ripeness to develop in the grapes and an exceptional quality overall. Steinhauer vineyard provides the foundation (30%) for the 2012 blend, which has been part of the Private Reserve blend since 1993. A significant portion (17%) comes from Bancroft Ranch atop Howell Mountain as well, and another portion (17%) comes from the St. Helena Home vineyard, which was part of the original acreage that founders Jacob and Frederick Beringer purchased in 1875 with the Beringer estate. Laurie also chose some select lots from the Rutherford appellation to include in the 2012 blend, as she particularly liked the broad, dense and classic Cabernet characteristics that appellation is known for. Each lot is closely watched during the ripening period to determine ideal pick time. Grapes for the 2012 Private Reserve Cabernet Sauvignon were harvested between September 25th and October 27th.\n\n96 Points: \"Revealing the great blending skills of Beringer’s winemaking team,the 2012 Cabernet Sauvignon Private Reserve is a winner... The sensational 2012 (10,000 cases) boasts an opaque purple color along with abundant aromas of charcoal, burning embers, crème de cassis, chocolate, blackberries and licorice. The long, complex, compelling, full-bodied texture along with terrific purity and a savory, rich mouthfeel result in a classic, quintessential Napa Cabernet Sauvignon to drink now and over the next 20-25 years.\"','California',93,165.00,'2012','Wine, Red wine, Cabernet Sauvignon'),(2,'Beringer Modern Heritage Pinot Noir 2013','There’s a deep richness to this wine, with pleasing notes of cola, sassafras, cocoa and vanilla, yet the sweet oak is deftly balanced and the wine has enough acidity to keep it bright and compelling.','California',75,35.00,'2013','Wine, Red wine, Pinot Noir'),(3,'St. Supery Elu Red Meritage 2012','This opulent blend presents with deep shades of reds and purples.Aromas of dark fruit, blackberry and black plum combinewith elements of espresso, vanillin and molasses, as wellas notes of smoky oak and dusty terroir. Flavors of dark plumand cassis follow with anise and toasted barrel attributes. Thisred blend offers a finely textured silky structure with densityand a graceful appeal.  Blend: 72% Cabernet Sauvignon, 13% Merlot, 10% Malbec,3% Cabernet Franc, 2% Petit Verdot','California',91,75.00,'2012','Wine, Red Wine, Bordeaux Blends ');
/*!40000 ALTER TABLE `wine` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-09 16:47:23
