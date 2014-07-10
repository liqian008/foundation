/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Version : 50165
 Source Host           : localhost
 Source Database       : security

 Target Server Version : 50165
 File Encoding         : utf-8

 Date: 07/28/2013 22:26:05 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

DROP DATABASE security;
CREATE DATABASE security;
use security;

-- ----------------------------
--  Table structure for `admin_resource`
-- ----------------------------
DROP TABLE IF EXISTS `admin_resource`;
CREATE TABLE `admin_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'ç¼–å·',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT 'çˆ¶èœå•ID',
  `resource_name` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT 'èœå•åç§°',
  `url` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'èœå•URL',
  `url_target` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'é¡µé¢æ‰“å¼€ä½ç½®',
  `nav_menu` smallint(11) DEFAULT '0' COMMENT '0:ä¸æ˜¾ç¤ºåœ¨å¯¼èˆªèœå•ä¸­,1:æ˜¾ç¤ºåœ¨å¯¼èˆªèœå•ä¸­',
  `sort` int(11) DEFAULT '0' COMMENT 'æŽ’åº',
  `remark` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'å¤‡æ³¨',
  `status` smallint(6) DEFAULT '1',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Records of `admin_resource`
-- ----------------------------
BEGIN;
INSERT INTO `admin_resource` VALUES ('1', '', '0', '主页', '/home', '', '1', '0', null, '1', '2013-07-26 20:09:47', '2013-07-26 20:09:49'), ('2', '', '1', '欢迎页', '/home/index', 'null', '1', '0', null, '1', '2013-07-26 20:10:30', '2013-07-26 20:10:33'), ('3', '', '0', '权限管理', '/sys', 'null', '1', '0', null, '1', '2013-07-23 11:10:45', '2013-07-23 11:10:48'), ('4', '', '3', 'userEdit1', '/sys/userEdit', '123', '0', '0', null, '1', '2013-07-23 11:14:35', '2013-07-28 21:23:28'), ('5', '', '3', '用户管理', '/sys/users', '', '1', '0', '', '1', '2013-07-23 11:14:32', '2013-07-23 11:11:35'), ('6', '', '3', '角色管理', '/sys/roles', '', '1', '0', '', '1', '2013-07-23 11:14:32', '2013-07-23 11:11:35'), ('7', '', '3', '权限管理', '/sys/resources', '', '1', '0', '', '1', '2013-07-23 11:14:32', '2013-07-23 11:11:35');
COMMIT;

-- ----------------------------
--  Table structure for `admin_role`
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ç®¡ç†è§’è‰²æ ‡è¯†',
  `role_name` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT 'è§’è‰²åç§°',
  `status` smallint(11) DEFAULT '1' COMMENT '0:ç¦ç”¨,1:å¯ç”¨',
  `create_time` datetime NOT NULL COMMENT 'è§’è‰²åˆ›å»ºæ—¶é—´',
  `update_time` datetime NOT NULL COMMENT 'è§’è‰²æœ€è¿‘ä¿®æ”¹æ—¶é—´',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_rolename` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Records of `admin_role`
-- ----------------------------
BEGIN;
INSERT INTO `admin_role` VALUES ('1', '权限管理角色', '1', '2013-07-23 11:09:42', '2013-07-23 11:09:45'), ('2', '2', '1', '2013-07-26 12:16:41', '2013-07-26 12:16:43'), ('3', '3', '1', '2013-07-26 12:16:50', '2013-07-26 12:16:55');
COMMIT;

-- ----------------------------
--  Table structure for `admin_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_resource`;
CREATE TABLE `admin_role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT 'è§’è‰²id',
  `resource_id` int(11) NOT NULL COMMENT 'èœå•ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_roleid_resourceid` (`role_id`,`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Records of `admin_role_resource`
-- ----------------------------
BEGIN;
INSERT INTO `admin_role_resource` VALUES ('12', '1', '1'), ('13', '1', '2'), ('14', '2', '1');
COMMIT;

-- ----------------------------
--  Table structure for `admin_user`
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `nickname` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `status` smallint(11) DEFAULT '1' COMMENT '0:ç¦ç”¨,1:å¯ç”¨',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `last_login_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`) USING BTREE,
  UNIQUE KEY `uk_nickname` (`nickname`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Records of `admin_user`
-- ----------------------------
BEGIN;
INSERT INTO `admin_user` VALUES ('1', 'admin', 'admin', 'b444e82d17a54d53a9e623fa586bf23ad7087df5e6f44848d01150fb0d5d523ea07ed99abdd074fc', '1', '2013-07-18 09:34:52', '2013-07-18 09:34:55', '2013-07-18 09:34:58'), ('2', 'test', 'test', 'bccc2abb45a4b1311694c63f1b721ac48824db066d079203ce00e41bba6bc5c4f4ab9be74927db41', '1', '2013-07-28 10:34:35', '2013-07-28 10:34:35', null);
COMMIT;

-- ----------------------------
--  Table structure for `admin_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_role`;
CREATE TABLE `admin_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT 'ç”¨æˆ·id',
  `role_id` int(11) NOT NULL COMMENT 'è§’è‰²id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_userid_roleid` (`user_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Records of `admin_user_role`
-- ----------------------------
BEGIN;
INSERT INTO `admin_user_role` VALUES ('8', '1', '1'), ('9', '1', '2'), ('6', '2', '1'), ('7', '2', '2');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
