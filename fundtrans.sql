/*
 Navicat Premium Data Transfer

 Source Server         : 恒生
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : 10.20.100.75:3306
 Source Schema         : fundtrans

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 01/06/2022 13:39:12
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for card
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card`
(
    `id`      int(0) NOT NULL AUTO_INCREMENT,
    `card_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `account` decimal(10, 2)                                         NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of card
-- ----------------------------
INSERT INTO `card`
VALUES (1, '521521515', '478f1f540b', 1000392.70);
INSERT INTO `card`
VALUES (3, '735312513123', 'cbb370be9e', 309.05);
INSERT INTO `card`
VALUES (4, '15125151515', '6941ea864b', 1875.65);
INSERT INTO `card`
VALUES (6, '723414112', 'd0e5c4c456', 1422.61);
INSERT INTO `card`
VALUES (7, '51252151515', '23ee33f042', 200.11);
INSERT INTO `card`
VALUES (9, '125412125', '23ee33f042', 1941.32);
INSERT INTO `card`
VALUES (10, '11151252151125', '1578b2ae2e', 1896.39);
INSERT INTO `card`
VALUES (11, '276641825127223', '1e4bf82adf', 1766.01);
INSERT INTO `card`
VALUES (13, '512376621312', '6fda9d4dbf', 450.37);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`
(
    `id`       varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `name`     varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `detail`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `networth` decimal(10, 4) NULL DEFAULT NULL COMMENT '净值',
    `type`     varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `security` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `prange`   decimal(10, 4) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product`
VALUES ('111111', '余额宝', NULL, 0.9305, '专户产品', '谨慎型产品(R1)', -0.0084);
INSERT INTO `product`
VALUES ('112233', '理财宝', '222', 0.8839, '普通', '平衡型产品(R3)', -0.0566);
INSERT INTO `product`
VALUES ('123123', '日日盈', '111', 0.9705, '普通', '谨慎型产品(R1)', -0.0813);
INSERT INTO `product`
VALUES ('525212', '涨涨乐', '嗨嗨嗨', 1.0679, '普通', '稳健型产品(R2)', -0.0035);

-- ----------------------------
-- Table structure for ptrans
-- ----------------------------
DROP TABLE IF EXISTS `ptrans`;
CREATE TABLE `ptrans`
(
    `id`           int(0) NOT NULL AUTO_INCREMENT,
    `user_id`      varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `user_name`    varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `product_id`   varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `product_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `card_id`      varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `time`         datetime(0) NULL DEFAULT NULL,
    `method`       varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `amount`       decimal(10, 2) NULL DEFAULT NULL,
    `state`        int(0) NULL DEFAULT NULL COMMENT '0表示未处理 1表示已处理 2表示已撤回',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ptrans
-- ----------------------------
INSERT INTO `ptrans`
VALUES (22, '23ee33f042', '低氧', '112233', '112233', '51252151515', '2022-05-31 13:41:16', '柜台委托', 30.00, 1);
INSERT INTO `ptrans`
VALUES (23, '23ee33f042', '低氧', '112233', '112233', '125412125', '2022-05-31 13:44:08', '柜台委托', 30.00, 1);
INSERT INTO `ptrans`
VALUES (24, '23ee33f042', '低氧', '112233', '112233', '125412125', '2022-05-31 14:26:04', '柜台委托', 100.00, 1);
INSERT INTO `ptrans`
VALUES (25, '23ee33f042', '低氧', '123123', '日日盈', '51252151515', '2022-05-31 15:20:15', '柜台委托', 30.00, 1);
INSERT INTO `ptrans`
VALUES (26, '478f1f540b', '浪费', '112233', '理财宝', '521521515', '2022-05-31 16:11:13', '柜台委托', 10.00, 2);
INSERT INTO `ptrans`
VALUES (27, '23ee33f042', '低氧', '112233', '理财宝', '51252151515', '2022-05-31 16:12:45', '柜台委托', 10.00, 1);
INSERT INTO `ptrans`
VALUES (28, '23ee33f042', '低氧', '111111', '余额宝', '51252151515', '2022-05-31 16:22:37', '柜台委托', 10.00, 1);
INSERT INTO `ptrans`
VALUES (29, '23ee33f042', '低氧', '111111', '余额宝', '51252151515', '2022-05-31 16:22:52', '柜台委托', 20.00, 1);
INSERT INTO `ptrans`
VALUES (30, '23ee33f042', '低氧', '111111', '余额宝', '51252151515', '2022-05-31 16:25:46', '柜台委托', 30.00, 1);
INSERT INTO `ptrans`
VALUES (31, '23ee33f042', '低氧', '111111', '余额宝', '51252151515', '2022-05-27 16:38:57', '柜台委托', 10.00, 1);
INSERT INTO `ptrans`
VALUES (32, '23ee33f042', '低氧', '111111', '余额宝', '125412125', '2022-05-30 12:48:01', '柜台委托', 60.00, 1);
INSERT INTO `ptrans`
VALUES (33, '23ee33f042', '低氧', '112233', '理财宝', '125412125', '2022-05-30 12:48:20', '柜台委托', 30.00, 1);
INSERT INTO `ptrans`
VALUES (34, '23ee33f042', '低氧', '112233', '理财宝', '125412125', '2022-05-30 12:48:25', '柜台委托', 30.00, 1);
INSERT INTO `ptrans`
VALUES (35, '23ee33f042', '低氧', '123123', '日日盈', '51252151515', '2022-05-30 12:48:45', '柜台委托', 19.00, 1);
INSERT INTO `ptrans`
VALUES (36, '23ee33f042', '低氧', '111111', '余额宝', '51252151515', '2022-05-30 12:00:00', '柜台委托', 20.00, 1);

-- ----------------------------
-- Table structure for purchase
-- ----------------------------
DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase`
(
    `id`           int(0) NOT NULL,
    `user_id`      varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `user_name`    varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `product_id`   varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `product_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `card_id`      varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `time`         datetime(0) NOT NULL,
    `amount`       decimal(10, 2)                                         NOT NULL,
    `count`        decimal(10, 2)                                         NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of purchase
-- ----------------------------
INSERT INTO `purchase`
VALUES (22, '23ee33f042', '低氧', '112233', '112233', '51252151515', '2022-05-31 13:41:16', 30.00, 29.01);
INSERT INTO `purchase`
VALUES (23, '23ee33f042', '低氧', '112233', '112233', '125412125', '2022-05-31 13:44:08', 30.00, 29.01);
INSERT INTO `purchase`
VALUES (24, '23ee33f042', '低氧', '112233', '112233', '125412125', '2022-05-31 14:26:04', 100.00, 96.68);
INSERT INTO `purchase`
VALUES (25, '23ee33f042', '低氧', '123123', '日日盈', '51252151515', '2022-05-31 15:20:15', 30.00, 28.48);
INSERT INTO `purchase`
VALUES (27, '23ee33f042', '低氧', '112233', '理财宝', '51252151515', '2022-05-31 16:12:45', 10.00, 10.17);
INSERT INTO `purchase`
VALUES (28, '23ee33f042', '低氧', '111111', '余额宝', '51252151515', '2022-05-31 16:22:37', 10.00, 9.30);
INSERT INTO `purchase`
VALUES (29, '23ee33f042', '低氧', '111111', '余额宝', '51252151515', '2022-05-31 16:22:52', 20.00, 18.60);
INSERT INTO `purchase`
VALUES (30, '23ee33f042', '低氧', '111111', '余额宝', '51252151515', '2022-05-31 16:25:46', 30.00, 31.97);
INSERT INTO `purchase`
VALUES (32, '23ee33f042', '低氧', '111111', '余额宝', '125412125', '2022-05-31 12:48:01', 60.00, 60.00);
INSERT INTO `purchase`
VALUES (33, '23ee33f042', '低氧', '112233', '理财宝', '125412125', '2022-05-31 12:48:20', 30.00, 30.30);
INSERT INTO `purchase`
VALUES (34, '23ee33f042', '低氧', '112233', '理财宝', '125412125', '2022-05-31 12:48:25', 30.00, 30.30);
INSERT INTO `purchase`
VALUES (35, '23ee33f042', '低氧', '123123', '日日盈', '51252151515', '2022-05-31 12:48:45', 19.00, 19.00);
INSERT INTO `purchase`
VALUES (36, '23ee33f042', '低氧', '111111', '余额宝', '51252151515', '2022-05-30 12:00:00', 20.00, 19.30);

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`
(
    `id`         varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `user_id`    varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `product_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `card_id`    varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `num`        varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `time`       datetime(0) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record`
VALUES ('10p', '478f1f540b', '112233', '521521515', '+44.00', '2022-05-30 10:12:12');
INSERT INTO `record`
VALUES ('11p', '478f1f540b', '112233', '521521515', '+33.00', '2022-05-30 10:12:12');
INSERT INTO `record`
VALUES ('12p', '23ee33f042', '112233', '51252151515', '+11.00', '2022-05-30 10:12:12');
INSERT INTO `record`
VALUES ('13p', '23ee33f042', '112233', '51252151515', '+49.00', '2022-05-30 10:12:12');
INSERT INTO `record`
VALUES ('14p', '23ee33f042', '112233', '125412125', '+12.00', '2022-05-30 10:12:12');
INSERT INTO `record`
VALUES ('22p', '23ee33f042', '112233', '51252151515', '+29.01', '2022-05-31 13:41:16');
INSERT INTO `record`
VALUES ('23p', '23ee33f042', '112233', '125412125', '+29.01', '2022-05-31 13:44:08');
INSERT INTO `record`
VALUES ('24p', '23ee33f042', '112233', '125412125', '+96.68', '2022-05-31 14:26:04');
INSERT INTO `record`
VALUES ('25p', '23ee33f042', '123123', '51252151515', '+28.48', '2022-05-31 15:20:15');
INSERT INTO `record`
VALUES ('27p', '23ee33f042', '112233', '51252151515', '+10.17', '2022-05-31 16:12:45');
INSERT INTO `record`
VALUES ('28p', '23ee33f042', '111111', '51252151515', '+9.30', '2022-05-31 16:22:37');
INSERT INTO `record`
VALUES ('29p', '23ee33f042', '111111', '51252151515', '+18.60', '2022-05-31 16:22:52');
INSERT INTO `record`
VALUES ('2r', '23ee33f042', '112233', '51252151515', '-11.00', '2022-05-31 15:17:27');
INSERT INTO `record`
VALUES ('30p', '23ee33f042', '111111', '51252151515', '+31.97', '2022-05-31 16:25:46');
INSERT INTO `record`
VALUES ('32p', '23ee33f042', '111111', '125412125', '+60.00', '2022-05-31 12:48:01');
INSERT INTO `record`
VALUES ('33p', '23ee33f042', '112233', '125412125', '+30.30', '2022-05-31 12:48:20');
INSERT INTO `record`
VALUES ('34p', '23ee33f042', '112233', '125412125', '+30.30', '2022-05-31 12:48:25');
INSERT INTO `record`
VALUES ('35p', '23ee33f042', '123123', '51252151515', '+19.00', '2022-05-31 12:48:45');
INSERT INTO `record`
VALUES ('36p', '23ee33f042', '111111', '51252151515', '+22.06', '2022-05-30 12:00:00');
INSERT INTO `record`
VALUES ('3r', '23ee33f042', '112233', '51252151515', '-3.00', '2022-05-31 15:17:27');
INSERT INTO `record`
VALUES ('4r', '23ee33f042', '112233', '125412125', '-22.95', '2022-05-31 15:17:27');
INSERT INTO `record`
VALUES ('7p', '478f1f540b', '112233', '521521515', '+55.80', '2022-05-30 15:57:40');

-- ----------------------------
-- Table structure for redemption
-- ----------------------------
DROP TABLE IF EXISTS `redemption`;
CREATE TABLE `redemption`
(
    `id`           int(0) NOT NULL,
    `user_id`      varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `user_name`    varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `product_id`   varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `product_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `card_id`      varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `time`         datetime(0) NULL DEFAULT NULL,
    `count`        decimal(10, 2) NULL DEFAULT NULL,
    `amount`       decimal(10, 2) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of redemption
-- ----------------------------
INSERT INTO `redemption`
VALUES (2, '23ee33f042', '低氧', '112233', '112233', '51252151515', '2022-05-31 13:58:39', 11.00, 11.38);
INSERT INTO `redemption`
VALUES (3, '23ee33f042', '低氧', '112233', '112233', '51252151515', '2022-05-31 14:05:06', 3.00, 3.10);
INSERT INTO `redemption`
VALUES (4, '23ee33f042', '低氧', '112233', '112233', '125412125', '2022-05-31 14:29:44', 22.95, 23.74);

-- ----------------------------
-- Table structure for rtrans
-- ----------------------------
DROP TABLE IF EXISTS `rtrans`;
CREATE TABLE `rtrans`
(
    `id`           int(0) NOT NULL AUTO_INCREMENT,
    `user_id`      varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `user_name`    varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `product_id`   varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `product_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `card_id`      varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `time`         datetime(0) NULL DEFAULT NULL,
    `method`       varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `count`        decimal(10, 2) NULL DEFAULT NULL,
    `state`        int(0) NULL DEFAULT NULL COMMENT '记录状态：0表示未处理 1表示已处理 2表示已撤回 3表示交易失败',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rtrans
-- ----------------------------
INSERT INTO `rtrans`
VALUES (1, '23ee33f042', '低氧', '112233', '112233', '125412125', '2022-05-31 13:57:47', '柜台委托', 1.00, 2);
INSERT INTO `rtrans`
VALUES (2, '23ee33f042', '低氧', '112233', '112233', '51252151515', '2022-05-31 13:58:39', '柜台委托', 11.00, 1);
INSERT INTO `rtrans`
VALUES (3, '23ee33f042', '低氧', '112233', '112233', '51252151515', '2022-05-31 14:05:06', '柜台委托', 3.00, 1);
INSERT INTO `rtrans`
VALUES (4, '23ee33f042', '低氧', '112233', '112233', '125412125', '2022-05-31 14:29:44', '柜台委托', 22.95, 1);
INSERT INTO `rtrans`
VALUES (5, '23ee33f042', '低氧', '112233', '112233', '125412125', '2022-05-31 14:38:41', '柜台委托', 22.95, 2);
INSERT INTO `rtrans`
VALUES (6, '23ee33f042', '低氧', '112233', '理财宝', '125412125', '2022-05-31 16:13:26', '柜台委托', 1.00, 0);

-- ----------------------------
-- Table structure for share
-- ----------------------------
DROP TABLE IF EXISTS `share`;
CREATE TABLE `share`
(
    `user_id`    varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `product_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `name`       varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `card_id`    varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `value`      decimal(10, 2) NULL DEFAULT NULL,
    `frozen_num` decimal(10, 2) NULL DEFAULT NULL,
    PRIMARY KEY (`user_id`, `product_id`, `card_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of share
-- ----------------------------
INSERT INTO `share`
VALUES ('1', '1', NULL, '1', 1.00, 1.00);
INSERT INTO `share`
VALUES ('2', '2', NULL, '2', 2.00, 2.00);
INSERT INTO `share`
VALUES ('23ee33f042', '111111', '余额宝', '125412125', 60.00, 0.00);
INSERT INTO `share`
VALUES ('23ee33f042', '111111', '余额宝', '51252151515', 91.93, 0.00);
INSERT INTO `share`
VALUES ('23ee33f042', '112233', '112233', '125412125', 418.04, -44.90);
INSERT INTO `share`
VALUES ('23ee33f042', '112233', '112233', '51252151515', 122.48, -14.00);
INSERT INTO `share`
VALUES ('23ee33f042', '123123', '日日盈', '51252151515', 47.48, 0.00);
INSERT INTO `share`
VALUES ('478f1f540b', '112233', '112233', '521521515', 88.80, 0.00);

-- ----------------------------
-- Table structure for trend
-- ----------------------------
DROP TABLE IF EXISTS `trend`;
CREATE TABLE `trend`
(
    `id`         datetime(0) NOT NULL,
    `product_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `price`      decimal(10, 4)                                         NOT NULL,
    `name`       varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`, `product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of trend
-- ----------------------------
INSERT INTO `trend`
VALUES ('2022-05-01 15:00:00', '1', 1.0312, NULL);
INSERT INTO `trend`
VALUES ('2022-05-02 15:00:00', '1', 2.0000, NULL);
INSERT INTO `trend`
VALUES ('2022-05-03 15:00:00', '1', 1.9343, NULL);
INSERT INTO `trend`
VALUES ('2022-05-27 15:00:00', '111111', 1.0000, '余额宝');
INSERT INTO `trend`
VALUES ('2022-05-30 15:00:00', '111111', 1.0361, '余额宝');
INSERT INTO `trend`
VALUES ('2022-05-30 15:00:00', '112233', 1.0000, '2233');
INSERT INTO `trend`
VALUES ('2022-05-30 15:00:00', '151515', 1.0000, '222');
INSERT INTO `trend`
VALUES ('2022-05-30 15:00:00', '321321', 1.0000, '1231');
INSERT INTO `trend`
VALUES ('2022-05-30 15:00:00', '525212', 1.0000, '涨涨乐');
INSERT INTO `trend`
VALUES ('2022-05-31 15:00:00', '111111', 1.0000, '余额宝');
INSERT INTO `trend`
VALUES ('2022-05-31 15:00:00', '112233', 0.9902, '112233');
INSERT INTO `trend`
VALUES ('2022-05-31 15:00:00', '123123', 1.0000, '日日盈');
INSERT INTO `trend`
VALUES ('2022-05-31 15:00:00', '525212', 1.0656, '涨涨乐');
INSERT INTO `trend`
VALUES ('2022-05-31 15:00:00', '671322', 1.0000, 'test');
INSERT INTO `trend`
VALUES ('2022-06-01 15:00:00', '111111', 1.0375, '余额宝');
INSERT INTO `trend`
VALUES ('2022-06-01 15:00:00', '112233', 1.0577, '112233');
INSERT INTO `trend`
VALUES ('2022-06-01 15:00:00', '123123', 0.9315, '日日盈');
INSERT INTO `trend`
VALUES ('2022-06-01 15:00:00', '525212', 1.0802, '涨涨乐');
INSERT INTO `trend`
VALUES ('2022-06-02 15:00:00', '111111', 0.9305, '余额宝');
INSERT INTO `trend`
VALUES ('2022-06-02 15:00:00', '112233', 0.8839, '理财宝');
INSERT INTO `trend`
VALUES ('2022-06-02 15:00:00', '123123', 0.9705, '日日盈');
INSERT INTO `trend`
VALUES ('2022-06-02 15:00:00', '525212', 1.0679, '涨涨乐');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`       varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `name`     varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `type`     varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `ctype`    varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '证件类型',
    `cid`      varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '证件号',
    `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `phone`    varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `age`      int(0) NULL DEFAULT NULL,
    `sex`      varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `address`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `job`      varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `security` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `state`    int(0) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES ('0', 'mjm', '机构', '0', '0', '', '1234555', 0, '', '', '', 'R1（谨慎型）', 1);
INSERT INTO `user`
VALUES ('1578b2ae2e', '楚楚', '个人', '居民身份证', '512312312125221', '123123', '13823431413', 20, '女', '1', '1', 'R1（谨慎型）', 1);
INSERT INTO `user`
VALUES ('1e4bf82adf', '楚楚', '个人', '居民身份证', '330128728299712', '123123', '13572782682', 20, '女', '浙江工业大学', '学生',
        'R1（谨慎型）', 1);
INSERT INTO `user`
VALUES ('2', '2', '机构', '2', '2', '2', '2456766', 2, '2', '2', '2', 'R5（激进型）', 2);
INSERT INTO `user`
VALUES ('23ee33f042', '低氧', '个人', '居民身份证', '123125235123135511', '123123', '17791486172', 21, '男', '321312', '学生',
        'R2（稳健型）', 0);
INSERT INTO `user`
VALUES ('30d3a9e62d', '马甲', '个人', '居民身份证', '312312312131231', '123456', '15312312312', 21, '男', '123123', '学生',
        'R4（进取型）', 1);
INSERT INTO `user`
VALUES ('478f1f540b', '浪费', '个人', '居民身份证', '223125112515251', '123123', '15342112322', 21, '男', '51251', '学生',
        'R2（稳健型）', 0);
INSERT INTO `user`
VALUES ('6941ea864b', '俊豪', '个人', '居民身份证', '312312525151212', '123123', '15312414242', 20, '男', '3213', '2', 'R5（激进型）',
        0);
INSERT INTO `user`
VALUES ('6fda9d4dbf', '楚楚', '机构', '居民身份证', '330128221392182193', '123123', '13523123211', 21, '女', 'zjut', '学生',
        'R5（激进型）', 1);
INSERT INTO `user`
VALUES ('8fe32e3d17', '俊豪', '个人', '居民身份证', '123123412412412', '123123', '15312312412', 20, '男', '12351251', '213123',
        'R5（激进型）', 1);
INSERT INTO `user`
VALUES ('cbb370be9e', '楚楚', '个人', '居民身份证', '124215251251125', '123123', '18124512511', 20, '女', '1215', '学生', 'R5（激进型）',
        1);
INSERT INTO `user`
VALUES ('d0e5c4c456', '马甲', '个人', '居民身份证', '125151512521515', '123123', '15351551522', 21, '男', '213', '1', 'R3（平衡型）',
        0);

-- ----------------------------
-- View structure for record_product_view
-- ----------------------------
DROP VIEW IF EXISTS `record_product_view`;
CREATE
ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `record_product_view` AS
select `record`.`user_id`    AS `user_id`,
       `record`.`product_id` AS `product_id`,
       `product`.`name`      AS `product_name`,
       sum(`record`.`num`)   AS `count`
from (`record` join `product`)
where (`record`.`product_id` = `product`.`id`);

SET
FOREIGN_KEY_CHECKS = 1;
