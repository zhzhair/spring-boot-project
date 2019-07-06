/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : flowsystem

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-07-06 08:57:28
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
) ENGINE=MyISAM AUTO_INCREMENT=352979 DEFAULT CHARSET=utf8;

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
) ENGINE=MyISAM AUTO_INCREMENT=5476317 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of appstart_record_original
-- ----------------------------

-- ----------------------------
-- Table structure for `appuse_sum`
-- ----------------------------
DROP TABLE IF EXISTS `appuse_sum`;
CREATE TABLE `appuse_sum` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `APP_CHANNEL` varchar(16) NOT NULL,
  `APP_VERSION` varchar(10) NOT NULL,
  `START_COUNT` int(11) NOT NULL,
  `USE_SECONDS` int(11) NOT NULL,
  `DAY_STR` varchar(10) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of appuse_sum
-- ----------------------------
