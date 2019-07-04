/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : flowsystem

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-07-05 06:26:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `appnew_record_original`
-- ----------------------------
DROP TABLE IF EXISTS `appnew_record_original`;
CREATE TABLE `appnew_record_original` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `APP_CHANNEL` varchar(16) NOT NULL,
  `APP_VERSION` varchar(10) NOT NULL,
  `DEVICE_ID` varchar(40) NOT NULL,
  `DEVICE_HASH_MOD` varchar(4) NOT NULL,
  `RECORD_IN_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `IN_TIME_INDEX` (`RECORD_IN_TIME`),
  KEY `DEVICE_HASH_INDEX` (`DEVICE_HASH_MOD`)
) ENGINE=MyISAM AUTO_INCREMENT=1728052 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of appnew_record_original
-- ----------------------------

-- ----------------------------
-- Table structure for `appstart_record_original`
-- ----------------------------
DROP TABLE IF EXISTS `appstart_record_original`;
CREATE TABLE `appstart_record_original` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `APP_CHANNEL` varchar(16) NOT NULL,
  `APP_VERSION` varchar(10) NOT NULL,
  `DEVICE_ID` varchar(40) NOT NULL,
  `DEVICE_HASH_MOD` varchar(4) NOT NULL,
  `RECORD_IN_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `IN_TIME_INDEX` (`RECORD_IN_TIME`),
  KEY `DEVICE_HASH_INDEX` (`DEVICE_HASH_MOD`)
) ENGINE=MyISAM AUTO_INCREMENT=29084558 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of appstart_record_original
-- ----------------------------

-- ----------------------------
-- Table structure for `user_exception`
-- ----------------------------
DROP TABLE IF EXISTS `user_exception`;
CREATE TABLE `user_exception` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(20) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  KEY `create_time_index` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_exception
-- ----------------------------

-- ----------------------------
-- Table structure for `user_login`
-- ----------------------------
DROP TABLE IF EXISTS `user_login`;
CREATE TABLE `user_login` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(20) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  KEY `create_time_index` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_login
-- ----------------------------

-- ----------------------------
-- Table structure for `user_register`
-- ----------------------------
DROP TABLE IF EXISTS `user_register`;
CREATE TABLE `user_register` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(20) NOT NULL,
  `mobile` varchar(15) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  KEY `create_time_index` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_register
-- ----------------------------
