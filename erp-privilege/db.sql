/*
Navicat MySQL Data Transfer

Source Server         : ifrog
Source Server Version : 50720
Source Host           : 192.168.1.140:3306
Source Database       : erp

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2017-12-12 21:11:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_element
-- ----------------------------
DROP TABLE IF EXISTS `sys_element`;
CREATE TABLE `sys_element` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(16) NOT NULL COMMENT '编码',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `url` varchar(50) DEFAULT NULL COMMENT '功能地址',
  `element_type` varchar(20) DEFAULT NULL COMMENT '元素类型(BUTTON,CSS,JS,ALINK)',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(16) NOT NULL COMMENT '名称',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `url` varchar(50) DEFAULT NULL COMMENT '页面地址',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级项',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_privilege
-- ----------------------------
DROP TABLE IF EXISTS `sys_privilege`;
CREATE TABLE `sys_privilege` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) NOT NULL COMMENT '资源类型(BUTTON,MENU...)',
  `name` int(11) DEFAULT NULL COMMENT '资源ID',
  `source_type` varchar(20) DEFAULT NULL COMMENT '资源类型(BUTTON,MENU,JS...)',
  `source_id` int(11) DEFAULT NULL COMMENT '资源类型ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL COMMENT '用户名',
  `passwd` varchar(20) DEFAULT NULL COMMENT '密码(加密)',
  `mobile` varchar(12) DEFAULT NULL COMMENT '手机',
  `real_name` varchar(16) DEFAULT NULL COMMENT '真实姓名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
