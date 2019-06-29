/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : exercise

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-06-29 17:17:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `password` varchar(36) NOT NULL,
  `mobile` varchar(15) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  KEY `create_time_index` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('138', 'xiaoli', 'e10adc3949ba59abbe56e057f20f883e', '16163555311', '2019-06-29 16:49:59');
INSERT INTO `user` VALUES ('139', 'xiaowang', 'e10adc3949ba59abbe56e057f20f883e', '13152924178', '2019-06-29 16:50:02');
INSERT INTO `user` VALUES ('140', 'xiaoqiang', 'e10adc3949ba59abbe56e057f20f883e', '15144509396', '2019-06-29 16:50:09');
INSERT INTO `user` VALUES ('141', 'tingting', 'e10adc3949ba59abbe56e057f20f883e', '18606118208', '2019-06-29 16:50:11');
INSERT INTO `user` VALUES ('142', 'xiaoming', 'e10adc3949ba59abbe56e057f20f883e', '16109835499', '2019-06-29 16:50:12');
INSERT INTO `user` VALUES ('143', 'jingjing', 'e10adc3949ba59abbe56e057f20f883e', '13574459873', '2019-06-29 16:50:12');
INSERT INTO `user` VALUES ('144', 'xiaohong', 'e10adc3949ba59abbe56e057f20f883e', '18931155970', '2019-06-29 16:50:13');
