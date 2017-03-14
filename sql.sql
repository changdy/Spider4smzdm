/*
Navicat MySQL Data Transfer

Source Server         : 读写
Source Server Version : 100119
Source Host           : localhost:3306
Source Database       : smzdm_commodity

Target Server Type    : MYSQL
Target Server Version : 100119
File Encoding         : 65001

Date: 2017-03-09 21:37:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `title` varchar(32) DEFAULT NULL COMMENT '标题',
  `parent_id` int(11) DEFAULT NULL COMMENT '上级目录ID',
  `url_nicktitle` varchar(32) DEFAULT NULL COMMENT 'url名称',
  `level` int(11) DEFAULT NULL COMMENT '等级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='目录表';

-- ----------------------------
-- Table structure for channel
-- ----------------------------
DROP TABLE IF EXISTS `channel`;
CREATE TABLE `channel` (
  `id` int(11) NOT NULL,
  `url` varchar(32) NOT NULL COMMENT '频道URL',
  `title` varchar(32) DEFAULT NULL COMMENT '频道名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='频道表';

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
	`id` BIGINT(20) NOT NULL COMMENT 'IDIDIDIDID',
	`title` VARCHAR(128) NULL DEFAULT NULL COMMENT '标题',
	`content` VARCHAR(1024) NULL DEFAULT NULL COMMENT '内容',
	`tags` VARCHAR(64) NULL DEFAULT NULL COMMENT 'article_tese_tags.name的拼接',
	`info_title` VARCHAR(64) NULL DEFAULT NULL COMMENT 'mall_more_info.title的拼接',
	`brand` VARCHAR(64) NULL DEFAULT NULL COMMENT 'gtm.brand 商标名称',
	`price_string` VARCHAR(128) NULL DEFAULT NULL COMMENT 'article_price 显示价格',
	`price_number` BIGINT(20) NULL DEFAULT NULL COMMENT 'gtm.rmb_price 原价',
	`last_category_id` INT(11) NULL DEFAULT NULL COMMENT '最后的目录ID',
	`referral_name` VARCHAR(64) NULL DEFAULT NULL COMMENT '爆料人',
	`pic_url` VARCHAR(128) NULL DEFAULT NULL COMMENT '照片url',
	`info_url` VARCHAR(64) NULL DEFAULT NULL COMMENT '详情页',
	`channel_id` INT(11) NULL DEFAULT NULL COMMENT '频道ID',
	`mall` VARCHAR(64) NULL DEFAULT NULL COMMENT '商城名称',
	`mall_url` VARCHAR(128) NULL DEFAULT NULL COMMENT '商城介绍',
	`shopping_url` VARCHAR(512) NULL DEFAULT NULL COMMENT '购买链接',
	`referral_date` DATETIME NULL DEFAULT NULL COMMENT '爆料时间',
	`time_sort` BIGINT(20) NULL DEFAULT NULL COMMENT '排序的',
	PRIMARY KEY (`id`)
)
COMMENT='物品表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

-- ----------------------------
-- Table structure for commodity_time_info
-- ----------------------------
DROP TABLE IF EXISTS `commodity_time_info`;
CREATE TABLE `commodity_time_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长ID',
  `commodity_id` bigint(20) DEFAULT NULL COMMENT '不解释了',
  `comment` int(11) DEFAULT NULL COMMENT '评论数',
  `collection` int(11) DEFAULT NULL COMMENT '收藏数',
  `worthy` int(11) DEFAULT NULL COMMENT '值',
  `unworthy` int(11) DEFAULT NULL COMMENT '不值',
  `sold_out` int(11) DEFAULT NULL COMMENT '售罄',
  `timeout` int(11) DEFAULT NULL COMMENT '过时',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物品更新表';

-- ----------------------------
-- Table structure for jsons
-- ----------------------------
DROP TABLE IF EXISTS `jsons`;
CREATE TABLE `jsons` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增长ID',
  `content` text COMMENT '所有内容',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `original_date` varchar(32) DEFAULT NULL COMMENT '字符串时间',
  `time_sort` bigint(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='原字符串';

-- ----------------------------
-- Table structure for relation
-- ----------------------------
DROP TABLE IF EXISTS `relation`;
CREATE TABLE `relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commodity_id` bigint(20) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='对应目录表';

-- ----------------------------
-- Table structure for timesort
-- ----------------------------
DROP TABLE IF EXISTS `timesort`;
CREATE TABLE `timesort` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '1 首页',
  `timesort` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
