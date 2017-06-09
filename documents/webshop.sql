/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : webshop

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-05-15 07:33:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `price` double(11,2) DEFAULT NULL,
  `unit` varchar(50) DEFAULT NULL,
  `category` varchar(100) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `seller` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES ('1', '中式童装', '120.00', '套', '童装', '中式童装', '1');
INSERT INTO `commodity` VALUES ('2', '女士套装', '200.00', '套', '女装', '女士职业套装', '1');
INSERT INTO `commodity` VALUES ('3', '男士西服', '200.00', '套', '男装', '男士西服套装', '1');
INSERT INTO `commodity` VALUES ('4', '笔记本电脑', '4000.00', '台', '电脑', '双核笔记本电脑', '2');
INSERT INTO `commodity` VALUES ('5', '移动硬盘', '400.00', '块', '电脑周边', '1t移动硬盘', '2');
INSERT INTO `commodity` VALUES ('6', '液晶电视', '5000.00', '台', '电视', '4k高清液晶电视', '3');
INSERT INTO `commodity` VALUES ('7', '滚筒洗衣机', '4000.00', '台', '洗衣机', '滚筒洗衣机', '3');
INSERT INTO `commodity` VALUES ('8', '《hibernate编程》', '30.00', '本', '实体书', '介绍hibernate编程', '4');
INSERT INTO `commodity` VALUES ('9', '《Java核心》', '50.00', '本', '实体书', '介绍Java编程核心', '4');
INSERT INTO `commodity` VALUES ('10', '《海底两万里》', '40.00', '本', '电子书', '经典科幻小说', '4');
INSERT INTO `commodity` VALUES ('11', '优盘', '30.00', '个', '电脑周边', '16G优盘', '2');

-- ----------------------------
-- Table structure for country
-- ----------------------------
DROP TABLE IF EXISTS `country`;
CREATE TABLE `country` (
  `cid` int(5) NOT NULL,
  `cname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of country
-- ----------------------------
INSERT INTO `country` VALUES ('1', 'England');
INSERT INTO `country` VALUES ('2', 'USA');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `tel` varchar(50) DEFAULT NULL,
  `address` varchar(1000) DEFAULT NULL,
  `email` varchar(300) DEFAULT NULL,
  `sex` varchar(30) DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', '张三', '13800000000', '中国上海××区××路', '13800000000@138.com', '男', '热爱编程的程序员', '25', '1990-01-01');
INSERT INTO `customer` VALUES ('2', '李四', '13888888888', '中国北京××区××路', '13888888888@138.com', '女', '酷爱网购的白领', '20', '1995-02-21');
INSERT INTO `customer` VALUES ('3', '王五', '15888888888', '中国深圳××区××路', '15888888888@158.com', '男', '这个家伙很懒，什么也没有留下', '35', '1980-04-14');
INSERT INTO `customer` VALUES ('4', '赵六', '13900000000', null, null, '男', null, '40', '1975-01-01');

-- ----------------------------
-- Table structure for minister
-- ----------------------------
DROP TABLE IF EXISTS `minister`;
CREATE TABLE `minister` (
  `mid` int(5) NOT NULL,
  `mname` varchar(20) DEFAULT NULL,
  `countryid` int(11) DEFAULT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of minister
-- ----------------------------
INSERT INTO `minister` VALUES ('1', 'aaa', '1');
INSERT INTO `minister` VALUES ('2', 'bbb', '1');
INSERT INTO `minister` VALUES ('3', 'ccc', '1');
INSERT INTO `minister` VALUES ('4', 'ddd', '2');
INSERT INTO `minister` VALUES ('5', 'eee', '2');

-- ----------------------------
-- Table structure for newlabel
-- ----------------------------
DROP TABLE IF EXISTS `newlabel`;
CREATE TABLE `newlabel` (
  `id` int(5) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `pid` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of newlabel
-- ----------------------------
INSERT INTO `newlabel` VALUES ('1', '娱乐新闻', '0');
INSERT INTO `newlabel` VALUES ('2', '体育新闻', '0');
INSERT INTO `newlabel` VALUES ('3', 'NBA', '2');
INSERT INTO `newlabel` VALUES ('4', 'CBA', '2');
INSERT INTO `newlabel` VALUES ('5', '火箭', '3');
INSERT INTO `newlabel` VALUES ('6', '湖人', '3');
INSERT INTO `newlabel` VALUES ('7', '北京金欧', '4');
INSERT INTO `newlabel` VALUES ('8', '浙江广夏', '4');
INSERT INTO `newlabel` VALUES ('9', '青岛双星', '4');
INSERT INTO `newlabel` VALUES ('10', '港台明星', '1');
INSERT INTO `newlabel` VALUES ('11', '内地影视', '1');

-- ----------------------------
-- Table structure for orderform
-- ----------------------------
DROP TABLE IF EXISTS `orderform`;
CREATE TABLE `orderform` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `customer` int(11) DEFAULT NULL,
  `tradedate` date DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `amount` double(11,2) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderform
-- ----------------------------
INSERT INTO `orderform` VALUES ('1', '1', '2015-04-30', '已收货', '4400.00');
INSERT INTO `orderform` VALUES ('2', '2', '2015-05-11', '已发货', '520.00');
INSERT INTO `orderform` VALUES ('3', '3', '2015-05-13', '已付款', '9120.00');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ORDERID` int(11) DEFAULT NULL,
  `COMMODITY` int(11) DEFAULT NULL,
  `DISCOUNT` double(11,2) DEFAULT NULL,
  `ACTPRICE` double(11,2) DEFAULT NULL,
  `AMOUNT` double(11,2) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('1', '1', '4', '1.00', '4000.00', '1.00');
INSERT INTO `orderitem` VALUES ('2', '1', '5', '1.00', '400.00', '1.00');
INSERT INTO `orderitem` VALUES ('3', '2', '1', '1.00', '120.00', '1.00');
INSERT INTO `orderitem` VALUES ('4', '2', '2', '1.00', '200.00', '1.00');
INSERT INTO `orderitem` VALUES ('5', '2', '3', '1.00', '200.00', '1.00');
INSERT INTO `orderitem` VALUES ('6', '3', '6', '1.00', '5000.00', '1.00');
INSERT INTO `orderitem` VALUES ('7', '3', '7', '1.00', '4000.00', '1.00');
INSERT INTO `orderitem` VALUES ('8', '3', '8', '1.00', '30.00', '1.00');
INSERT INTO `orderitem` VALUES ('9', '3', '9', '1.00', '50.00', '1.00');
INSERT INTO `orderitem` VALUES ('10', '3', '10', '1.00', '40.00', '1.00');

-- ----------------------------
-- Table structure for seller
-- ----------------------------
DROP TABLE IF EXISTS `seller`;
CREATE TABLE `seller` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `tel` varchar(1000) DEFAULT NULL,
  `address` varchar(2000) DEFAULT NULL,
  `website` varchar(500) DEFAULT NULL,
  `star` int(11) DEFAULT NULL,
  `business` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seller
-- ----------------------------
INSERT INTO `seller` VALUES ('1', 'A服装店', '13000000000', '中国北京××区', 'www.a.com', '5', '经营各式服装');
INSERT INTO `seller` VALUES ('2', 'B数码店', '15800000000', '中国浙江杭州市××区', 'www.b.com', '4', '经营各类数码电子产品');
INSERT INTO `seller` VALUES ('3', 'C电器店', '13012341234', '中国广东深圳市××区', 'www.c.com', '4', '经营各类家电');
INSERT INTO `seller` VALUES ('4', 'D书店', '18600000000', '中国陕西西安市××区', 'www.d.com', '5', '经营各类实体书与电子书');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `score` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', 'aa', '24', '98');
INSERT INTO `student` VALUES ('2', '张', '23', '94');

-- ----------------------------
-- Table structure for students
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students` (
  `sid` varchar(8) NOT NULL,
  `sname` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of students
-- ----------------------------
INSERT INTO `students` VALUES ('S0000001', '张三丰', '男', '2015-06-24', '武当山');
INSERT INTO `students` VALUES ('S0000002', '张无忌', '男', '2015-06-24', '武当山');
INSERT INTO `students` VALUES ('S0000004', '李白', '女', '2015-06-06', '西安长安区');
INSERT INTO `students` VALUES ('S0000006', '水立方', '男', '2017-04-18', '水电费三');

-- ----------------------------
-- Table structure for test1
-- ----------------------------
DROP TABLE IF EXISTS `test1`;
CREATE TABLE `test1` (
  `id` int(11) NOT NULL,
  `VALUE` char(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test1
-- ----------------------------
INSERT INTO `test1` VALUES ('1', 'testaa');
INSERT INTO `test1` VALUES ('2', 'testaa');
INSERT INTO `test1` VALUES ('3', 'testaa');

-- ----------------------------
-- Table structure for test2
-- ----------------------------
DROP TABLE IF EXISTS `test2`;
CREATE TABLE `test2` (
  `id` int(11) NOT NULL,
  `VALUE` char(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test2
-- ----------------------------
INSERT INTO `test2` VALUES ('1', 'testaa2');
INSERT INTO `test2` VALUES ('2', 'testaa2');
INSERT INTO `test2` VALUES ('4', 'testaa2');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '1', '1');

-- ----------------------------
-- Procedure structure for myproc
-- ----------------------------
DROP PROCEDURE IF EXISTS `myproc`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `myproc`(OUT s int)
BEGIN
select COUNT(*) into s from users;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for myproc2
-- ----------------------------
DROP PROCEDURE IF EXISTS `myproc2`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `myproc2`(IN num int)
BEGIN
SELECT num;
SET num=num+1;
SELECT num;
END
;;
DELIMITER ;
