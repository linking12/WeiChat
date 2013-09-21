/*
Navicat MySQL Data Transfer

Source Server         : 172.16.112.166
Source Server Version : 50508
Source Host           : 172.16.112.166:3306
Source Database       : spring_security

Target Server Type    : MYSQL
Target Server Version : 50508
File Encoding         : 65001

Date: 2012-11-16 10:46:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `notice`
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `author` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `addTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `content` varchar(255) DEFAULT NULL,
  `noticeId` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`noticeId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('zzq', 'notice001', '2012-11-05 16:33:01', 'asdfghjkl', '1');

-- ----------------------------
-- Table structure for `resources`
-- ----------------------------
DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources` (
  `memo` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resources
-- ----------------------------
INSERT INTO `resources` VALUES ('测试', '/notice.action', null, null, 'AAA', '1');
INSERT INTO `resources` VALUES ('B', '/b.jsp', null, null, 'BBB', '2');
INSERT INTO `resources` VALUES ('C', '/c.jsp', null, null, 'CCC', '3');

-- ----------------------------
-- Table structure for `roles`
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `enable` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('1', 'Admin', '1');

-- ----------------------------
-- Table structure for `roles_resources`
-- ----------------------------
DROP TABLE IF EXISTS `roles_resources`;
CREATE TABLE `roles_resources` (
  `rsid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  `rrId` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`rrId`),
  KEY `rid` (`rid`),
  KEY `roles_resources_ibfk_2` (`rsid`),
  CONSTRAINT `roles_resources_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `roles` (`id`),
  CONSTRAINT `roles_resources_ibfk_2` FOREIGN KEY (`rsid`) REFERENCES `resources` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles_resources
-- ----------------------------
INSERT INTO `roles_resources` VALUES ('1', '1', '1');
INSERT INTO `roles_resources` VALUES ('3', '1', '2');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `enable` int(11) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'zzq', 'zzq', '1');

-- ----------------------------
-- Table structure for `users_roles`
-- ----------------------------
DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles` (
  `uid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  `urId` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`urId`),
  KEY `rid` (`rid`),
  KEY `uid` (`uid`),
  CONSTRAINT `users_roles_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `roles` (`id`),
  CONSTRAINT `users_roles_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users_roles
-- ----------------------------
INSERT INTO `users_roles` VALUES ('1', '1', '1');
