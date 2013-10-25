/**

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微商城信息表';

DROP TABLE IF EXISTS `wx_mall_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_mall_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productid` bigint(20) NOT NULL,
  `count` bigint(20) NOT NULL,
  `malluserid` bigint(20) NOT NULL,
  `mallid` bigint(20) NOT NULL,
  `createdate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物车';

DROP TABLE IF EXISTS `wx_mall_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_mall_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderno` varchar(45) NOT NULL,
  `status` varchar(2) NOT NULL,
  `receiver` varchar(200) NOT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `address` varchar(200) NOT NULL,
  `userid` bigint(20) NOT NULL,
  `mallid` bigint(20) NOT NULL,
  `saleprice` decimal(10,0) DEFAULT NULL,
  `expenses` decimal(10,0) DEFAULT NULL,
  `createdate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

DROP TABLE IF EXISTS `wx_mall_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_mall_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `phoneno` varchar(45) NOT NULL,
  `address` varchar(45) DEFAULT NULL,
  `mallid` bigint(20) NOT NULL,
  `createdate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商场用户表';

DROP TABLE IF EXISTS `wx_order_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_order_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderid` bigint(20) NOT NULL,
  `productid` bigint(20) NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `count` bigint(20) NOT NULL,
  `createdate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单商品表	';


DROP TABLE IF EXISTS `wx_prdt_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_prdt_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `productid` bigint(20) NOT NULL,
  `subcategoryid` bigint(20) NOT NULL,
  `createdate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品和类别关联表';

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

DROP TABLE IF EXISTS `wx_product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_product_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mallid` bigint(20) NOT NULL,
  `categoryname` varchar(45) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `createdate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主页商品类别';

DROP TABLE IF EXISTS `wx_product_pic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_product_pic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `picname` varchar(45) DEFAULT NULL,
  `picurl` varchar(200) NOT NULL,
  `productid` bigint(20) NOT NULL,
  `createdate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品图片关系表';

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品最终售价表';

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='子类别表';