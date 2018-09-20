/*
Navicat MySQL Data Transfer

Source Server         : mobile2
Source Server Version : 50635
Source Host           : 192.168.1.43:3306
Source Database       : 3u

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2018-09-20 16:47:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_useropinion`
-- ----------------------------
DROP TABLE IF EXISTS `t_useropinion`;
CREATE TABLE `t_useropinion` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Pcguid` varchar(200) DEFAULT ' ',
  `Pcos` varchar(100) DEFAULT ' ',
  `Pcvs` varchar(100) DEFAULT ' ',
  `Ip` varchar(50) DEFAULT ' ',
  `Type` varchar(20) DEFAULT ' ' COMMENT '意见分类',
  `Context` varchar(1000) DEFAULT ' ',
  `ContactWay` varchar(200) DEFAULT ' ',
  `Image` varchar(2000) DEFAULT ' ',
  `ServerNum` int(11) DEFAULT NULL COMMENT '服务器标识',
  `Status` int(11) DEFAULT '1' COMMENT '状态：1：未处理，2：处理中，3：已处理，4：已关闭',
  `Note` varchar(200) DEFAULT NULL COMMENT '备注',
  `OperUser` varchar(50) DEFAULT NULL COMMENT '操作人',
  `OperTime` datetime DEFAULT NULL,
  `CreateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_useropinion
-- ----------------------------
INSERT INTO `t_useropinion` VALUES ('69', '%7BA6F93865-E1BD-4543-BFB7-43D8A7C5C356%7D', '10.0_0_64', '2.25.006', '192.168.1.200', '2,4', '123123', '123123', '[\"2018/09/18/10/1537239594503_270938.jpeg\",\"2018/09/18/10/1537239594545_673939.jpeg\"]', '1', '3', 'cehishceshi', '刘晓龙(lxl)', '2018-09-19 18:58:46', null);
INSERT INTO `t_useropinion` VALUES ('70', '%7B1128741D-9628-4878-8087-C284F8A66C94%7D', '5.1_0_32', '2.25.011', '192.168.1.119', '3', '            czxc x', '2356226222@163.cvb', '', '1', '1', null, null, null, null);
INSERT INTO `t_useropinion` VALUES ('71', '%7BA6F93865-E1BD-4543-BFB7-43D8A7C5C356%7D', '10.0_0_64', '2.25.006', '192.168.1.200', '1,6', '请3请3', '123123123', '[\"2018/09/19/17/1537351122088_375463.jpeg\"]', '1', '1', null, null, null, null);
