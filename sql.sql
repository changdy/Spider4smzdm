/*
Navicat MySQL Data Transfer

Source Server         : 本地测试
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : smzdm_commodity

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-05-16 18:31:36
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
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `article_id` bigint(20) DEFAULT NULL,
  `discovery_flag` int(11) DEFAULT NULL COMMENT '是否是发现频道（0不是，1是）',
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `content` varchar(1024) DEFAULT NULL COMMENT '内容',
  `tags` varchar(64) DEFAULT NULL COMMENT 'article_tese_tags.name的拼接',
  `info_title` varchar(64) DEFAULT NULL COMMENT 'mall_more_info.title的拼接',
  `brand` varchar(64) DEFAULT NULL COMMENT 'gtm.brand 商标名称',
  `price_string` varchar(128) DEFAULT NULL COMMENT 'article_price 显示价格',
  `price_number` bigint(20) DEFAULT NULL COMMENT 'gtm.rmb_price 原价',
  `top_category_id` int(11) DEFAULT NULL COMMENT '最后的目录ID',
  `referral_name` varchar(64) DEFAULT NULL COMMENT '爆料人',
  `pic_url` varchar(128) DEFAULT NULL COMMENT '照片url',
  `info_url` varchar(64) DEFAULT NULL COMMENT '详情页',
  `channel_id` int(11) DEFAULT NULL COMMENT '频道ID',
  `mall` varchar(64) DEFAULT NULL COMMENT '商城名称',
  `mall_url` varchar(128) DEFAULT NULL COMMENT '商城介绍',
  `shopping_url` varchar(512) DEFAULT NULL COMMENT '购买链接',
  `referral_date` datetime DEFAULT NULL COMMENT '爆料时间',
  `time_sort` bigint(20) DEFAULT NULL COMMENT '排序的',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8 COMMENT='物品表';

-- ----------------------------
-- Table structure for commodity_last_info
-- ----------------------------
DROP TABLE IF EXISTS `commodity_last_info`;
CREATE TABLE `commodity_last_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长ID',
  `article_id` bigint(20) DEFAULT NULL COMMENT '不解释了',
  `comment` int(11) DEFAULT NULL COMMENT '评论数',
  `collection` int(11) DEFAULT NULL COMMENT '收藏数',
  `worthy` int(11) DEFAULT NULL COMMENT '值',
  `unworthy` int(11) DEFAULT NULL COMMENT '不值',
  `sold_out` int(11) DEFAULT NULL COMMENT '售罄',
  `timeout` int(11) DEFAULT NULL COMMENT '过时',
  `discovery_flag` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=301 DEFAULT CHARSET=utf8 COMMENT='物品更新表';

-- ----------------------------
-- Table structure for commodity_time_info
-- ----------------------------
DROP TABLE IF EXISTS `commodity_time_info`;
CREATE TABLE `commodity_time_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长ID',
  `article_id` bigint(20) DEFAULT NULL COMMENT '不解释了',
  `comment` int(11) DEFAULT NULL COMMENT '评论数',
  `collection` int(11) DEFAULT NULL COMMENT '收藏数',
  `worthy` int(11) DEFAULT NULL COMMENT '值',
  `unworthy` int(11) DEFAULT NULL COMMENT '不值',
  `sold_out` int(11) DEFAULT NULL COMMENT '售罄',
  `timeout` int(11) DEFAULT NULL COMMENT '过时',
  `discovery_flag` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=301 DEFAULT CHARSET=utf8 COMMENT='物品更新表';

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
) ENGINE=InnoDB AUTO_INCREMENT=477 DEFAULT CHARSET=utf8 COMMENT='原字符串';

-- ----------------------------
-- Table structure for relation
-- ----------------------------
DROP TABLE IF EXISTS `relation`;
CREATE TABLE `relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commodity_id` bigint(20) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=341 DEFAULT CHARSET=utf8 COMMENT='对应目录表';

-- ----------------------------
-- Table structure for time_sort
-- ----------------------------
DROP TABLE IF EXISTS `time_sort`;
CREATE TABLE `time_sort` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '1 首页',
  `time_sort` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
