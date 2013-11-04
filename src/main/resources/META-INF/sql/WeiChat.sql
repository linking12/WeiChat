/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50611
 Source Host           : 127.0.0.1
 Source Database       : WeiChat

 Target Server Type    : MySQL
 Target Server Version : 50611
 File Encoding         : utf-8

 Date: 09/27/2013 23:04:08 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `T_RESOURCES`
-- ----------------------------

BEGIN;
delete from  `T_RESOURCES`;
delete from  `T_ROLES`;
delete from  `T_ROLE_RESOURCES`;
delete from  `T_USER_ROLE`;
delete from  `T_USER`;
INSERT INTO `T_RESOURCES` VALUES ('1', 'account', '1', '1', '/account'), ('2', 'game', '1', '1', '/game'), ('3', 'message', '1', '1', '/message'), ('4', 'user', '1', '1', '/user'), ('5', 'replymsg', '1', '1', '/replymsg');
INSERT INTO `T_ROLES` VALUES ('1', '1', 'Admin');
INSERT INTO `T_ROLES` VALUES ('2', '1', 'SimpleUser');
INSERT INTO `T_ROLE_RESOURCES` VALUES ('1', '1'), ('1', '2'), ('1', '3'), ('1', '4'), ('1', '5');
INSERT INTO `T_ROLE_RESOURCES` VALUES ('2', '1'),  ('2', '3'),  ('2', '5');
INSERT INTO `T_USER` VALUES ('1', 'admin', '1', '899899');
INSERT INTO `T_USER_ROLE` VALUES ('1', '1');
delete from `wx_game`;
INSERT INTO `wx_game`(id,name,url,note,gtype) VALUES (1,'聊天机器人','autoreply','一上一下','program');
INSERT INTO `wx_game`(id,name,url,note,gtype) VALUES (2,'刮刮乐','game_ggl','刮刮乐','html5游戏');
COMMIT;

