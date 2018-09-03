/*
Navicat MySQL Data Transfer

Source Server         : 75
Source Server Version : 50719
Source Host           : 120.25.69.75:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-09-04 00:50:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `remark` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', 'zaka', 'nillocal', '1384264578', '218195@gmail.com', 'zaka love sport');
INSERT INTO `customer` VALUES ('2', 'mike', 'billon', '1478902343', '31146@gmail.com', 'mike love children');
INSERT INTO `customer` VALUES ('3', 'danny', null, '14760928387', '78977@gmail.com', 'danny love swim');
