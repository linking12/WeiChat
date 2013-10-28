/*
-- MySQL dump 10.13  Distrib 5.6.11, for Win32 (x86)
--
-- Host: localhost    Database: weixin
-- ------------------------------------------------------
-- Server version	5.6.13

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
-- Table structure for table `wx_product_sub_category`
--

DROP TABLE IF EXISTS `wx_product_sub_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_product_sub_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `categoryid` bigint(20) NOT NULL,
  `subcategoryname` varchar(45) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `picurl` varchar(200) DEFAULT NULL,
  `createdate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='子类别表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wx_product_sub_category`
--

LOCK TABLES `wx_product_sub_category` WRITE;
/*!40000 ALTER TABLE `wx_product_sub_category` DISABLE KEYS */;
INSERT INTO `wx_product_sub_category` (`id`, `categoryid`, `subcategoryname`, `description`, `picurl`, `createdate`) VALUES (1,1,'限时优惠',' <p>男女装指定摇粒绒茄克</p>&nbsp;<em>截至10/17</em><br><br>&nbsp;<i>￥149</i><br>&nbsp;<i><</i>','/1/all_img.jpg','2013-01-01'),(2,1,'新品上市','<p class=\"t_tight\">男女装指定摇粒绒茄克</p>&nbsp;<em class=\"t_tight\">截至10/17</em><br>&nbsp;<i class=\"t_tight\">￥149</i><br><br>&nbsp;<i class=\"t_tight\">></i>','/1/all_img1.jpg','2013-01-01'),(3,1,'限时优惠',' <p class=\"big\">Woman</p>&nbsp;<i class=\"white\"><</i>','/1/all_img2.jpg','2013-01-01'),(4,2,'五折专场',NULL,'/1/20131028150354.jpg','2013-01-01'),(5,2,'秋季换装',NULL,'/1/20131028150452.jpg','2013-01-01'),(6,2,'特别推荐',NULL,'/1/20131028150539.jpg','2013-01-01'),(7,3,'摇粒绒夹克',NULL,'/1/sj_img1.jpg','2013-01-01'),(8,3,'法兰绒衬衫',NULL,'/1/sj_img2.jpg','2013-01-01'),(9,3,'精纺系列',NULL,'/1/sj_img3.jpg','2013-01-01'),(10,4,'潮流打底裤','美腿有诀窍','/1/cl_banner.jpg','2013-01-01'),(11,4,'巴黎之旅 ','休闲裤专场','/1/cl_banner1.jpg','2013-01-01');
/*!40000 ALTER TABLE `wx_product_sub_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wx_product`
--

DROP TABLE IF EXISTS `wx_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `productname` varchar(45) NOT NULL,
  `productprice` decimal(10,0) NOT NULL,
  `stock` bigint(20) NOT NULL,
  `descrpiton` varchar(1000) DEFAULT NULL,
  `effectivedate` date DEFAULT NULL,
  `expirydate` date DEFAULT NULL,
  `mallid` bigint(20) NOT NULL,
  `createdate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='商品表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wx_product`
--

LOCK TABLES `wx_product` WRITE;
/*!40000 ALTER TABLE `wx_product` DISABLE KEYS */;
INSERT INTO `wx_product` (`id`, `productname`, `productprice`, `stock`, `descrpiton`, `effectivedate`, `expirydate`, `mallid`, `createdate`) VALUES (1,'条纹彩色波浪打底裤',39,100,'黑底白条','2013-01-01','2099-01-01',1,'2013-01-01'),(2,'日系原宿风星空打底裤',49,100,'日系原宿风星空打底裤星空（紫色）','2013-01-01','2099-01-01',1,'2013-01-01'),(3,'甜美蕾丝修身打底裤',40,100,'甜美蕾丝修身打底裤蓝色','2013-01-01','2099-01-01',1,'2013-01-01'),(4,'条纹彩色波浪打底裤',39,100,'条纹彩色波浪打底裤条纹彩色波浪打底裤','2013-01-01','2099-01-01',1,'2013-01-01'),(5,'韩版糖果色针织打底裤 ',39,100,'果绿色韩版糖果色针织打底裤 ','2013-01-01','2099-01-01',1,'2013-01-01'),(6,'韩版糖果色针织打底裤',39,100,'韩版糖果色针织打底裤天蓝色','2013-01-01','2099-01-01',1,'2013-01-01');
/*!40000 ALTER TABLE `wx_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wx_mall`
--

DROP TABLE IF EXISTS `wx_mall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_mall` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `accountid` bigint(20) NOT NULL,
  `mallname` varchar(45) NOT NULL,
  `effectivedate` date NOT NULL,
  `expirydate` date NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `picurl` varchar(200) DEFAULT NULL,
  `createdate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='微商城信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wx_mall`
--

LOCK TABLES `wx_mall` WRITE;
/*!40000 ALTER TABLE `wx_mall` DISABLE KEYS */;
INSERT INTO `wx_mall` (`id`, `accountid`, `mallname`, `effectivedate`, `expirydate`, `description`, `picurl`, `createdate`) VALUES (1,1,'星星的家园','2013-01-01','2099-01-10','星星的家园微商城','/1/index_banner.jpg','2013-01-01');
/*!40000 ALTER TABLE `wx_mall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wx_prdt_category`
--

DROP TABLE IF EXISTS `wx_prdt_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_prdt_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `productid` bigint(20) NOT NULL,
  `subcategoryid` bigint(20) NOT NULL,
  `createdate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='商品和类别关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wx_prdt_category`
--

LOCK TABLES `wx_prdt_category` WRITE;
/*!40000 ALTER TABLE `wx_prdt_category` DISABLE KEYS */;
INSERT INTO `wx_prdt_category` (`id`, `productid`, `subcategoryid`, `createdate`) VALUES (1,1,1,'2013-01-01'),(2,2,1,'2013-01-01'),(3,3,1,'2013-01-01'),(4,4,1,'2013-01-01'),(5,5,1,'2013-01-01'),(6,6,1,'2013-01-01'),(7,1,2,'2013-01-01'),(8,1,3,'2013-01-01'),(9,1,4,'2013-01-01'),(10,1,5,'2013-01-01'),(11,1,6,'2013-01-01'),(12,1,7,'2013-01-01'),(13,2,8,'2013-01-01'),(14,3,8,'2013-01-01'),(15,4,8,'2013-01-01'),(16,5,8,'2013-01-01'),(17,6,8,'2013-01-01'),(18,2,2,'2013-01-01'),(19,3,2,'2013-01-01'),(20,4,2,'2013-01-01'),(21,5,2,'2013-01-01'),(22,2,3,'2013-01-01'),(23,2,4,'2013-01-01'),(24,2,5,'2013-01-01'),(25,2,6,'2013-01-01'),(26,2,7,'2013-01-01'),(27,2,8,'2013-01-01'),(28,2,9,'2013-01-01'),(29,2,10,'2013-01-01'),(30,2,11,'2013-01-01');
/*!40000 ALTER TABLE `wx_prdt_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wx_product_pic`
--

DROP TABLE IF EXISTS `wx_product_pic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_product_pic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `picname` varchar(45) DEFAULT NULL,
  `picurl` varchar(200) NOT NULL,
  `productid` bigint(20) NOT NULL,
  `flag` varchar(45) NOT NULL,
  `createdate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='商品图片关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wx_product_pic`
--

LOCK TABLES `wx_product_pic` WRITE;
/*!40000 ALTER TABLE `wx_product_pic` DISABLE KEYS */;
INSERT INTO `wx_product_pic` (`id`, `picname`, `picurl`, `productid`, `flag`, `createdate`) VALUES (1,'图片1','/1/sp1.jpg',1,'0','2013-01-01'),(2,'图片2','/1/sp2.jpg',2,'0','2013-01-01'),(3,'图片3','/1/sp3.jpg',3,'0','2013-01-01'),(4,'图片4','/1/sp4.jpg',4,'0','2013-01-01'),(5,'图片5','/1/sp5.jpg',5,'0','2013-01-01'),(6,'图片6','/1/sp6.jpg',6,'0','2013-01-01');
/*!40000 ALTER TABLE `wx_product_pic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wx_product_category`
--

DROP TABLE IF EXISTS `wx_product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_product_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mallid` bigint(20) NOT NULL,
  `categoryname` varchar(45) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `style` varchar(45) DEFAULT NULL,
  `createdate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='主页商品类别';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wx_product_category`
--

LOCK TABLES `wx_product_category` WRITE;
/*!40000 ALTER TABLE `wx_product_category` DISABLE KEYS */;
INSERT INTO `wx_product_category` (`id`, `mallid`, `categoryname`, `description`, `style`, `createdate`) VALUES (1,1,'全部商品','全部商品','1','2013-01-01'),(2,1,'活动专区','活动专区','2','2013-01-01'),(3,1,'最新上架','最新上架','2','2013-01-01'),(4,1,'潮流趋势','潮流趋势','3','2013-01-01');
/*!40000 ALTER TABLE `wx_product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wx_product_price`
--

DROP TABLE IF EXISTS `wx_product_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_product_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productid` bigint(20) NOT NULL,
  `effectivedate` date NOT NULL,
  `expirydate` date NOT NULL,
  `saleprice` decimal(10,0) NOT NULL,
  `expenses` decimal(10,0) DEFAULT NULL,
  `createdate` date NOT NULL,
  `count` bigint(20) DEFAULT NULL,
  `mallid` bigint(20) DEFAULT NULL,
  `malluserid` bigint(20) DEFAULT NULL,
  `orderid` bigint(20) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='商品最终售价表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wx_product_price`
--

LOCK TABLES `wx_product_price` WRITE;
/*!40000 ALTER TABLE `wx_product_price` DISABLE KEYS */;
INSERT INTO `wx_product_price` (`id`, `productid`, `effectivedate`, `expirydate`, `saleprice`, `expenses`, `createdate`, `count`, `mallid`, `malluserid`, `orderid`, `price`) VALUES (1,1,'2013-01-01','2099-01-01',31,5,'2013-01-01',NULL,NULL,NULL,NULL,NULL),(2,2,'2013-01-01','2099-01-01',32,6,'2013-01-01',NULL,NULL,NULL,NULL,NULL),(3,3,'2013-01-01','2099-01-01',33,5,'2013-01-01',NULL,NULL,NULL,NULL,NULL),(4,4,'2013-01-01','2099-01-01',34,5,'2013-01-01',NULL,NULL,NULL,NULL,NULL),(5,5,'2013-01-01','2099-01-01',36,5,'2013-01-01',NULL,NULL,NULL,NULL,NULL),(6,6,'2013-01-01','2099-01-01',37,6,'2013-01-01',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `wx_product_price` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-10-28 18:02:09
