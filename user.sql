/*
Navicat MySQL Data Transfer

Source Server         : bc
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : user

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2018-06-29 18:29:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
  `empno` int(11) NOT NULL AUTO_INCREMENT,
  `ename` varchar(255) DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL,
  `mgr` int(11) DEFAULT NULL,
  `hireDate` date DEFAULT NULL,
  `sal` float DEFAULT NULL,
  `comm` float DEFAULT NULL,
  `deptno` int(11) DEFAULT NULL,
  PRIMARY KEY (`empno`)
) ENGINE=InnoDB AUTO_INCREMENT=1017 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES ('1001', '曾智琴', '龙王', '1', '2000-06-07', '100', '10', '3');
INSERT INTO `emp` VALUES ('1002', '扶摇', '皇后', '1', '2016-07-01', '99', '1', '2');
INSERT INTO `emp` VALUES ('1003', '魏征', '丞相', '2', '2018-06-06', '98', '12', '1');
INSERT INTO `emp` VALUES ('1004', '奥巴马', '丞相', '3', '2018-06-13', '97', '13', '4');
INSERT INTO `emp` VALUES ('1005', '无极', '侍卫', '2', '2018-06-04', '96', '1', '5');
INSERT INTO `emp` VALUES ('1006', '纳兰性德', '侍卫', '4', '2018-06-11', '96', '4', '6');
INSERT INTO `emp` VALUES ('1007', '夜华', '侍卫', '5', '2018-06-05', '96', '7', '7');
INSERT INTO `emp` VALUES ('1008', '郑爽', '宫女', null, '2018-01-01', '90', null, null);
INSERT INTO `emp` VALUES ('1009', '陈思', '爱妃', null, '2018-06-01', '3', null, null);
INSERT INTO `emp` VALUES ('1010', '郑安然', '美人', null, '2018-06-01', '3', null, null);
INSERT INTO `emp` VALUES ('1011', '特朗普', '马夫', null, '2018-06-19', '1', null, null);
INSERT INTO `emp` VALUES ('1012', '黄星宇', '尚书', null, '2018-06-29', '88', null, null);
INSERT INTO `emp` VALUES ('1013', '王旨', '尚书', null, '2018-06-20', '88', null, null);
INSERT INTO `emp` VALUES ('1014', 'c罗', '大将军', null, '2018-06-23', '101', null, null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) NOT NULL,
  `password` varchar(10) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '杨幂', '1234', '1234567');
INSERT INTO `user` VALUES ('2', '胡歌', '123', '7654321');
