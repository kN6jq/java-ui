/*
 Navicat Premium Data Transfer

 Source Server         : 82.157.168.181_3306
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 82.157.168.181:3306
 Source Schema         : db

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 06/10/2022 17:37:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auths
-- ----------------------------
DROP TABLE IF EXISTS `auths`;
CREATE TABLE `auths`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL,
  `password` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL,
  `created_at` datetime(3) NULL DEFAULT NULL,
  `updated_at` datetime(3) NULL DEFAULT NULL,
  `deleted_at` datetime(3) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_auths_deleted_at`(`deleted_at`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for weaks
-- ----------------------------
DROP TABLE IF EXISTS `weaks`;
CREATE TABLE `weaks`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `company` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL,
  `product` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL,
  `version` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL,
  `type` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL,
  `info` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL,
  `remarks` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL,
  `created_at` datetime(3) NULL DEFAULT NULL,
  `updated_at` datetime(3) NULL DEFAULT NULL,
  `deleted_at` datetime(3) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_weaks_deleted_at`(`deleted_at`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
