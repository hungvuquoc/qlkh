/*
 Navicat Premium Data Transfer

 Source Server         : PC_MY_SQL
 Source Server Type    : MySQL
 Source Server Version : 80035
 Source Host           : localhost:3306
 Source Schema         : qlkh

 Target Server Type    : MySQL
 Target Server Version : 80035
 File Encoding         : 65001

 Date: 18/01/2024 07:41:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for databasechangelog
-- ----------------------------
DROP TABLE IF EXISTS `databasechangelog`;
CREATE TABLE `databasechangelog`  (
  `ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `AUTHOR` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `FILENAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int NOT NULL,
  `EXECTYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `MD5SUM` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `DESCRIPTION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `COMMENTS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `TAG` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `LIQUIBASE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `CONTEXTS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `LABELS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of databasechangelog
-- ----------------------------
INSERT INTO `databasechangelog` VALUES ('202308290931', 'quochung', 'db/changelogs/01_db_change.xml', '2023-11-05 17:39:55', 1, 'EXECUTED', '8:b635ac340c1905e8ffc73c6d712ed998', 'sqlFile; sqlFile', '', NULL, '3.6.3', NULL, NULL, '9180794387');
INSERT INTO `databasechangelog` VALUES ('202308312251', 'quochung', 'db/changelogs/01_db_change.xml', '2023-11-05 17:39:59', 2, 'EXECUTED', '8:6f4070fd83d81dc8c85831d78df71665', 'sqlFile', '', NULL, '3.6.3', NULL, NULL, '9180794387');
INSERT INTO `databasechangelog` VALUES ('202309252212', 'quochung', 'db/changelogs/01_db_change.xml', '2023-11-05 17:39:59', 3, 'EXECUTED', '8:bf22a0955c0a1fb7031e040e95dea483', 'sqlFile', '', NULL, '3.6.3', NULL, NULL, '9180794387');
INSERT INTO `databasechangelog` VALUES ('202309252220', 'quochung', 'db/changelogs/01_db_change.xml', '2023-11-05 17:40:00', 4, 'EXECUTED', '8:ad6d72b90e17f6d272db9037e630131f', 'sqlFile', '', NULL, '3.6.3', NULL, NULL, '9180794387');
INSERT INTO `databasechangelog` VALUES ('202310131143', 'quochung', 'db/changelogs/01_db_change.xml', '2023-11-05 17:40:00', 5, 'EXECUTED', '8:f3cc652838cf4f1ef97700b932ae319a', 'sqlFile', '', NULL, '3.6.3', NULL, NULL, '9180794387');
INSERT INTO `databasechangelog` VALUES ('202310141021', 'quochung', 'db/changelogs/01_db_change.xml', '2023-11-09 06:45:35', 6, 'EXECUTED', '8:8eb2f7e8246f4300d09cd99315de6251', 'sqlFile', '', NULL, '3.6.3', NULL, NULL, '9487134830');
INSERT INTO `databasechangelog` VALUES ('202310141031', 'quochung', 'db/changelogs/01_db_change.xml', '2023-11-11 15:57:48', 7, 'EXECUTED', '8:2ce7a6fc8e7848b33d50f90716efb0bc', 'sqlFile', '', NULL, '3.6.3', NULL, NULL, '9693058833');
INSERT INTO `databasechangelog` VALUES ('202310141041', 'quochung', 'db/changelogs/01_db_change.xml', '2023-11-11 15:57:49', 8, 'EXECUTED', '8:7ff31d576a434b5b14a87db653e71ba8', 'sqlFile', '', NULL, '3.6.3', NULL, NULL, '9693058833');
INSERT INTO `databasechangelog` VALUES ('202310141051', 'quochung', 'db/changelogs/01_db_change.xml', '2023-11-11 15:57:50', 9, 'EXECUTED', '8:6aa15ea018197403476a666f8b16f985', 'sqlFile', '', NULL, '3.6.3', NULL, NULL, '9693058833');
INSERT INTO `databasechangelog` VALUES ('202311090628', 'quochung', 'db/changelogs/01_db_change.xml', '2023-11-11 15:57:50', 10, 'EXECUTED', '8:4a6801ef43191afc4f5055cd2d84c255', 'sqlFile', '', NULL, '3.6.3', NULL, NULL, '9693058833');
INSERT INTO `databasechangelog` VALUES ('202311220905', 'quochung', 'db/changelogs/01_db_change.xml', '2023-11-22 09:26:36', 11, 'EXECUTED', '8:e1dbfa9c711480428919996f26ca5454', 'sqlFile', '', NULL, '3.6.3', NULL, NULL, '0619995915');
INSERT INTO `databasechangelog` VALUES ('202312101434', 'quochung', 'db/changelogs/01_db_change.xml', '2023-12-10 15:09:52', 12, 'EXECUTED', '8:6ea7dd34ab36fd971e3acaa7e4ccd59d', 'sqlFile', '', NULL, '3.6.3', NULL, NULL, '2195792588');
INSERT INTO `databasechangelog` VALUES ('202312101459', 'quochung', 'db/changelogs/01_db_change.xml', '2023-12-10 15:09:53', 13, 'EXECUTED', '8:cc5e07d90ba75e09ff9cd57d69a0afc0', 'sqlFile', '', NULL, '3.6.3', NULL, NULL, '2195792588');

-- ----------------------------
-- Table structure for databasechangeloglock
-- ----------------------------
DROP TABLE IF EXISTS `databasechangeloglock`;
CREATE TABLE `databasechangeloglock`  (
  `ID` int NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime NULL DEFAULT NULL,
  `LOCKEDBY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of databasechangeloglock
-- ----------------------------
INSERT INTO `databasechangeloglock` VALUES (1, b'0', NULL, NULL);

-- ----------------------------
-- Table structure for tbl_authority
-- ----------------------------
DROP TABLE IF EXISTS `tbl_authority`;
CREATE TABLE `tbl_authority`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_tbl_authority_name`(`name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_authority
-- ----------------------------
INSERT INTO `tbl_authority` VALUES (1, 'USER_VIEW', NULL);
INSERT INTO `tbl_authority` VALUES (2, 'USER_SEARCH', NULL);
INSERT INTO `tbl_authority` VALUES (3, 'USER_CREATE', NULL);
INSERT INTO `tbl_authority` VALUES (4, 'USER_UPDATE', NULL);
INSERT INTO `tbl_authority` VALUES (5, 'USER_DELETE', NULL);
INSERT INTO `tbl_authority` VALUES (6, 'USER_UPLOAD_FILE', NULL);
INSERT INTO `tbl_authority` VALUES (7, 'USER_DOWNLOAD_FILE', NULL);
INSERT INTO `tbl_authority` VALUES (8, 'USER_VIEW_FILE', NULL);
INSERT INTO `tbl_authority` VALUES (9, 'WH_ROOT', NULL);
INSERT INTO `tbl_authority` VALUES (10, 'ROLE_SEARCH', NULL);
INSERT INTO `tbl_authority` VALUES (11, 'ROLE_CREATE', NULL);
INSERT INTO `tbl_authority` VALUES (12, 'ROLE_UPDATE', NULL);
INSERT INTO `tbl_authority` VALUES (13, 'ROLE_DELETE', NULL);
INSERT INTO `tbl_authority` VALUES (14, 'WH_IMPORT_VIEW_MY_CREATE', 'Xem phiếu nhập do mình tạo');
INSERT INTO `tbl_authority` VALUES (15, 'WH_IMPORT_VIEW_WAREHOUSE', 'Xem phiếu nhập toàn bộ kho');
INSERT INTO `tbl_authority` VALUES (16, 'WH_IMPORT_SEARCH_MY_CREATE', 'Tìm kiếm phiếu nhập do mình tạo');
INSERT INTO `tbl_authority` VALUES (17, 'WH_IMPORT_SEARCH_WAREHOUSE', 'Tìm kiếm phiếu nhập toàn bộ kho');
INSERT INTO `tbl_authority` VALUES (18, 'WH_IMPORT_CREATE', 'Tạo mới phiếu nhập');
INSERT INTO `tbl_authority` VALUES (19, 'WH_IMPORT_UPDATE_MY_CREATE', 'Sửa phiếu nhập do mình tạo');
INSERT INTO `tbl_authority` VALUES (20, 'WH_IMPORT_UPDATE_WAREHOUSE', 'Sửa phiếu nhập toàn bộ kho');
INSERT INTO `tbl_authority` VALUES (21, 'WH_IMPORT_DELETE_MY_CREATE', 'Xóa phiếu nhập do mình tạo');
INSERT INTO `tbl_authority` VALUES (22, 'WH_IMPORT_DELETE_WAREHOUSE', 'Xóa phiếu nhập toàn bộ kho');
INSERT INTO `tbl_authority` VALUES (23, 'WH_IMPORT_UPLOAD_FILE', 'Thêm file');
INSERT INTO `tbl_authority` VALUES (24, 'WH_IMPORT_VIEW_FILE', 'Xem file');
INSERT INTO `tbl_authority` VALUES (25, 'WH_EXPORT_VIEW_MY_CREATE', 'Xem phiếu nhập do mình tạo');
INSERT INTO `tbl_authority` VALUES (26, 'WH_EXPORT_VIEW_WAREHOUSE', 'Xem phiếu nhập toàn bộ kho');
INSERT INTO `tbl_authority` VALUES (27, 'WH_EXPORT_SEARCH_MY_CREATE', 'Tìm kiếm phiếu nhập do mình tạo');
INSERT INTO `tbl_authority` VALUES (28, 'WH_EXPORT_SEARCH_WAREHOUSE', 'Tìm kiếm phiếu nhập toàn bộ kho');
INSERT INTO `tbl_authority` VALUES (29, 'WH_EXPORT_CREATE', 'Tạo mới phiếu nhập');
INSERT INTO `tbl_authority` VALUES (30, 'WH_EXPORT_UPDATE_MY_CREATE', 'Sửa phiếu nhập do mình tạo');
INSERT INTO `tbl_authority` VALUES (31, 'WH_EXPORT_UPDATE_WAREHOUSE', 'Sửa phiếu nhập toàn bộ kho');
INSERT INTO `tbl_authority` VALUES (32, 'WH_EXPORT_DELETE_MY_CREATE', 'Xóa phiếu nhập do mình tạo');
INSERT INTO `tbl_authority` VALUES (33, 'WH_EXPORT_DELETE_WAREHOUSE', 'Xóa phiếu nhập toàn bộ kho');
INSERT INTO `tbl_authority` VALUES (34, 'WH_EXPORT_UPLOAD_FILE', 'Thêm file');
INSERT INTO `tbl_authority` VALUES (35, 'WH_EXPORT_VIEW_FILE', 'Xem file');
INSERT INTO `tbl_authority` VALUES (36, 'WH_TRANSFER_VIEW_MY_CREATE', 'Xem phiếu nhập do mình tạo');
INSERT INTO `tbl_authority` VALUES (37, 'WH_TRANSFER_VIEW_WAREHOUSE', 'Xem phiếu nhập toàn bộ kho');
INSERT INTO `tbl_authority` VALUES (38, 'WH_TRANSFER_SEARCH_MY_CREATE', 'Tìm kiếm phiếu nhập do mình tạo');
INSERT INTO `tbl_authority` VALUES (39, 'WH_TRANSFER_SEARCH_WAREHOUSE', 'Tìm kiếm phiếu nhập toàn bộ kho');
INSERT INTO `tbl_authority` VALUES (40, 'WH_TRANSFER_CREATE', 'Tạo mới phiếu nhập');
INSERT INTO `tbl_authority` VALUES (41, 'WH_TRANSFER_UPDATE_MY_CREATE', 'Sửa phiếu nhập do mình tạo');
INSERT INTO `tbl_authority` VALUES (42, 'WH_TRANSFER_UPDATE_WAREHOUSE', 'Sửa phiếu nhập toàn bộ kho');
INSERT INTO `tbl_authority` VALUES (43, 'WH_TRANSFER_DELETE_MY_CREATE', 'Xóa phiếu nhập do mình tạo');
INSERT INTO `tbl_authority` VALUES (44, 'WH_TRANSFER_DELETE_WAREHOUSE', 'Xóa phiếu nhập toàn bộ kho');
INSERT INTO `tbl_authority` VALUES (45, 'WH_TRANSFER_UPLOAD_FILE', 'Thêm file');
INSERT INTO `tbl_authority` VALUES (46, 'WH_TRANSFER_VIEW_FILE', 'Xem file');
INSERT INTO `tbl_authority` VALUES (47, 'WH_MAP', 'Sơ đồ kho');
INSERT INTO `tbl_authority` VALUES (48, 'WH_CARD', 'Thẻ kho');
INSERT INTO `tbl_authority` VALUES (49, 'WH_REPORT', 'Báo cáo nhập xuất tồn');

-- ----------------------------
-- Table structure for tbl_employee
-- ----------------------------
DROP TABLE IF EXISTS `tbl_employee`;
CREATE TABLE `tbl_employee`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `modify_date` datetime NULL DEFAULT NULL,
  `user_id` bigint UNSIGNED NULL DEFAULT NULL,
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `birthday` date NOT NULL,
  `gender` int NOT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `deleted` bit(1) NULL DEFAULT b'0',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_tbl_employee_user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_employee_user_id` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_employee
-- ----------------------------
INSERT INTO `tbl_employee` VALUES (1, 'admin', '2023-11-09 18:47:07', NULL, '2023-11-09 18:47:07', 1, '00001', 'ROOT', '2000-02-02', 0, '0123456789', b'0');
INSERT INTO `tbl_employee` VALUES (2, 'root', '2023-11-24 15:11:24', 'root', '2023-11-28 19:11:37', 3, '00002', 'Quoc Hung ', '2001-02-20', 0, '0987654321', b'0');
INSERT INTO `tbl_employee` VALUES (3, 'root', '2023-11-28 19:25:45', 'root', '2023-11-28 20:10:04', 4, '00003', 'Ngoc Thinh', '2001-02-20', 0, '0987654321', b'0');
INSERT INTO `tbl_employee` VALUES (4, 'root', '2023-11-29 06:32:39', 'root', '2023-11-29 06:48:20', 5, '00004', 'Dang Khoa', '2001-02-20', 0, '0987654321', b'1');
INSERT INTO `tbl_employee` VALUES (5, 'root', '2023-11-29 06:52:43', 'root', '2023-11-29 06:53:18', 6, '00005', 'Vu Quoc Hung', '2001-02-20', 0, '0987654321', b'0');
INSERT INTO `tbl_employee` VALUES (6, 'nhanvien01', '2023-11-29 17:14:26', 'nhanvien01', '2023-11-29 18:20:38', 7, '00006', 'Đăng Thành Sơn', '1999-05-03', 0, '0908185491', b'0');
INSERT INTO `tbl_employee` VALUES (7, 'nhanvien01', '2023-11-29 18:30:20', 'nhanvien01', '2023-11-29 18:30:20', NULL, '00007', 'Ngọc Thanh', '1998-05-08', 1, '0908185491', b'1');

-- ----------------------------
-- Table structure for tbl_file_description
-- ----------------------------
DROP TABLE IF EXISTS `tbl_file_description`;
CREATE TABLE `tbl_file_description`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `modify_date` datetime NULL DEFAULT NULL,
  `content_size` bigint NULL DEFAULT NULL,
  `content_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `extension` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_file_description
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_file_old
-- ----------------------------
DROP TABLE IF EXISTS `tbl_file_old`;
CREATE TABLE `tbl_file_old`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `created_at` datetime NULL DEFAULT NULL,
  `path` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_file_old
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_organization
-- ----------------------------
DROP TABLE IF EXISTS `tbl_organization`;
CREATE TABLE `tbl_organization`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `enterprise_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `manager_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `address` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_organization
-- ----------------------------
INSERT INTO `tbl_organization` VALUES (1, 'TH', 'Cửa hàng tạp hóa Chung Lan', '0001', 'Vũ Quốc Hưng', 'Bắc Ninh', '0973892148', 'vquhung@gmail.com', 'Cửa hàng tạp hóa');

-- ----------------------------
-- Table structure for tbl_product
-- ----------------------------
DROP TABLE IF EXISTS `tbl_product`;
CREATE TABLE `tbl_product`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `modify_date` datetime NULL DEFAULT NULL,
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name_print` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `note` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type_id` bigint UNSIGNED NOT NULL,
  `deleted` bit(1) NULL DEFAULT b'0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UQ_tbl_product_code`(`code` ASC) USING BTREE,
  INDEX `FK_tbl_product_type_id`(`type_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_product_type_id` FOREIGN KEY (`type_id`) REFERENCES `tbl_product_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_product
-- ----------------------------
INSERT INTO `tbl_product` VALUES (1, 'admin', '2023-11-07 20:37:05', 'root', '2023-12-12 08:32:52', '00001', 'Củ cải', 'Củ cải', 'note', 1, b'0');
INSERT INTO `tbl_product` VALUES (2, 'admin', '2023-11-07 20:51:25', 'root', '2023-12-12 08:33:20', '00002', 'Hạt hướng dương', 'Hạt hướng dương', 'sdf', 1, b'0');
INSERT INTO `tbl_product` VALUES (3, 'root', '2023-12-12 08:14:04', NULL, NULL, '00003', 'Bột mì', 'Bột mì', 'Bột mì', 1, b'0');
INSERT INTO `tbl_product` VALUES (4, 'root', '2023-12-12 08:18:04', NULL, NULL, '00004', 'Chất tạo màu 238', 'Chất tạo màu 238', 'Bột mì', 1, b'0');
INSERT INTO `tbl_product` VALUES (5, 'root', '2023-12-12 08:19:01', NULL, NULL, '00005', 'Đậu xanh', 'Đậu xanh', 'Bột mì', 1, b'0');

-- ----------------------------
-- Table structure for tbl_product_detail
-- ----------------------------
DROP TABLE IF EXISTS `tbl_product_detail`;
CREATE TABLE `tbl_product_detail`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `modify_date` datetime NULL DEFAULT NULL,
  `product_id` bigint UNSIGNED NOT NULL,
  `price` double NULL DEFAULT NULL,
  `quality` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `size` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_tbl_product_detail_product_id`(`product_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_product_detail_product_id` FOREIGN KEY (`product_id`) REFERENCES `tbl_product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_product_detail
-- ----------------------------
INSERT INTO `tbl_product_detail` VALUES (1, 'admin', '2023-11-07 20:37:05', NULL, '2023-11-07 20:37:05', 1, 223, 'A', '1 - 2');
INSERT INTO `tbl_product_detail` VALUES (2, 'admin', '2023-11-07 20:51:25', NULL, '2023-11-07 20:51:25', 2, 223, 'A', '1 - 2');
INSERT INTO `tbl_product_detail` VALUES (3, 'root', '2023-12-12 08:14:04', NULL, NULL, 3, 20, 'Tot', NULL);
INSERT INTO `tbl_product_detail` VALUES (4, 'root', '2023-12-12 08:18:04', NULL, NULL, 4, 20, 'Tot', NULL);
INSERT INTO `tbl_product_detail` VALUES (5, 'root', '2023-12-12 08:19:01', NULL, NULL, 5, 30, 'Tot', NULL);

-- ----------------------------
-- Table structure for tbl_product_file_description_connect
-- ----------------------------
DROP TABLE IF EXISTS `tbl_product_file_description_connect`;
CREATE TABLE `tbl_product_file_description_connect`  (
  `product_id` bigint UNSIGNED NOT NULL,
  `file_id` bigint UNSIGNED NOT NULL,
  `order_by` int UNSIGNED NOT NULL,
  PRIMARY KEY (`product_id`, `file_id`) USING BTREE,
  UNIQUE INDEX `UK_c30fdvm0kkvcvevlrnmllvqtg`(`file_id` ASC) USING BTREE,
  INDEX `FK_tbl_product_file_description_product_id`(`product_id` ASC) USING BTREE,
  INDEX `FK_tbl_product_file_description_file_id`(`file_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_product_file_description_file_id` FOREIGN KEY (`file_id`) REFERENCES `tbl_file_description` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_product_file_description_product_id` FOREIGN KEY (`product_id`) REFERENCES `tbl_product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_product_file_description_connect
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_product_floor
-- ----------------------------
DROP TABLE IF EXISTS `tbl_product_floor`;
CREATE TABLE `tbl_product_floor`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` bigint UNSIGNED NOT NULL,
  `floor_id` bigint UNSIGNED NOT NULL,
  `unit_target_id` bigint UNSIGNED NOT NULL,
  `unit_source_id` bigint UNSIGNED NOT NULL,
  `product_detail_id` bigint UNSIGNED NULL DEFAULT NULL,
  `consignment_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `quantity_target` double NOT NULL,
  `quantity_source` double NOT NULL,
  `input_date` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_tbl_product_floor_product_id`(`product_id` ASC) USING BTREE,
  INDEX `FK_tbl_product_floor_unit_target_id`(`unit_target_id` ASC) USING BTREE,
  INDEX `FK_tbl_product_floor_unit_source_id`(`unit_source_id` ASC) USING BTREE,
  INDEX `FK_tbl_product_floor_floor_id`(`floor_id` ASC) USING BTREE,
  INDEX `FK_tbl_product_floor_product_detail_id`(`product_detail_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_product_floor_floor_id` FOREIGN KEY (`floor_id`) REFERENCES `tbl_warehouse_floor` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_product_floor_product_detail_id` FOREIGN KEY (`product_detail_id`) REFERENCES `tbl_product_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_product_floor_product_id` FOREIGN KEY (`product_id`) REFERENCES `tbl_product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_product_floor_unit_source_id` FOREIGN KEY (`unit_source_id`) REFERENCES `tbl_product_unit_connect` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_product_floor_unit_target_id` FOREIGN KEY (`unit_target_id`) REFERENCES `tbl_product_unit_connect` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_product_floor
-- ----------------------------
INSERT INTO `tbl_product_floor` VALUES (1, 1, 267, 1, 1, 1, '', 22, 22, '2023-11-09 19:01:00');
INSERT INTO `tbl_product_floor` VALUES (2, 1, 302, 5, 1, 1, 'L01', 1, 0.5, '2023-11-15 00:00:00');
INSERT INTO `tbl_product_floor` VALUES (3, 1, 304, 5, 1, 1, 'L01', 1, 0.5, '2023-11-15 00:00:00');
INSERT INTO `tbl_product_floor` VALUES (4, 1, 312, 2, 1, 1, '', 0, 0, '2023-11-15 00:00:00');
INSERT INTO `tbl_product_floor` VALUES (5, 1, 303, 2, 1, 1, '', 1, 0.1, '2023-11-15 00:00:00');
INSERT INTO `tbl_product_floor` VALUES (6, 1, 301, 2, 1, 1, '', 1, 0.1, '2023-11-15 00:00:00');
INSERT INTO `tbl_product_floor` VALUES (7, 1, 312, 2, 1, 1, 'L02', 2, 0.2, '2023-11-15 00:00:00');
INSERT INTO `tbl_product_floor` VALUES (8, 1, 303, 2, 1, 1, 'L02', 2, 0.2, '2023-11-15 00:00:00');
INSERT INTO `tbl_product_floor` VALUES (9, 1, 301, 2, 1, 1, 'L02', 2, 0.2, '2023-11-15 00:00:00');
INSERT INTO `tbl_product_floor` VALUES (10, 1, 327, 1, 1, 1, '', 0, 0, '2023-11-18 00:00:00');
INSERT INTO `tbl_product_floor` VALUES (11, 1, 329, 1, 1, 1, '', 0, 0, '2023-11-18 00:00:00');
INSERT INTO `tbl_product_floor` VALUES (12, 1, 337, 2, 1, 1, '', 0, 0, '2023-11-18 00:00:00');
INSERT INTO `tbl_product_floor` VALUES (13, 1, 339, 2, 1, 1, '', 0, 0, '2023-11-18 00:00:00');
INSERT INTO `tbl_product_floor` VALUES (14, 1, 341, 2, 1, 1, '', 0, 0, '2023-11-18 00:00:00');
INSERT INTO `tbl_product_floor` VALUES (15, 1, 268, 1, 1, 1, '', 5, 5, '2023-11-09 19:01:00');
INSERT INTO `tbl_product_floor` VALUES (16, 1, 308, 2, 1, 1, 'L03', 2, 0.2, '2023-11-25 00:00:00');
INSERT INTO `tbl_product_floor` VALUES (17, 1, 267, 2, 1, 1, 'L03', 1, 0.1, '2023-11-25 00:00:00');
INSERT INTO `tbl_product_floor` VALUES (18, 1, 316, 5, 1, 1, '', 2, 1, '2023-12-08 00:00:00');
INSERT INTO `tbl_product_floor` VALUES (19, 1, 346, 2, 1, 1, 'L0001', 10, 1, '2023-12-11 00:00:00');
INSERT INTO `tbl_product_floor` VALUES (20, 5, 272, 8, 8, 5, '', 80, 80, '2023-12-12 00:00:00');
INSERT INTO `tbl_product_floor` VALUES (21, 5, 402, 8, 8, 5, '', 5, 5, '2023-12-12 00:00:00');
INSERT INTO `tbl_product_floor` VALUES (22, 5, 400, 8, 8, 5, '', 5, 5, '2023-12-12 00:00:00');
INSERT INTO `tbl_product_floor` VALUES (23, 5, 297, 8, 8, 5, '', 5, 5, '2023-12-12 00:00:00');
INSERT INTO `tbl_product_floor` VALUES (24, 5, 296, 8, 8, 5, '', 5, 5, '2023-12-12 00:00:00');

-- ----------------------------
-- Table structure for tbl_product_group
-- ----------------------------
DROP TABLE IF EXISTS `tbl_product_group`;
CREATE TABLE `tbl_product_group`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `modify_date` datetime NULL DEFAULT NULL,
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `deleted` bit(1) NULL DEFAULT b'0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UQ_tbl_product_group`(`code` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_product_group
-- ----------------------------
INSERT INTO `tbl_product_group` VALUES (1, 'admin', '2023-11-07 20:34:28', 'root', '2023-12-12 08:08:57', '00001', 'Chế phẩm', b'0');
INSERT INTO `tbl_product_group` VALUES (2, 'admin', '2023-11-07 20:34:35', 'root', '2023-12-12 08:07:29', '00002', 'Nông sản', b'0');
INSERT INTO `tbl_product_group` VALUES (3, 'root', '2023-12-12 08:16:50', NULL, NULL, '00003', 'Hóa chất', b'0');

-- ----------------------------
-- Table structure for tbl_product_group_connect
-- ----------------------------
DROP TABLE IF EXISTS `tbl_product_group_connect`;
CREATE TABLE `tbl_product_group_connect`  (
  `product_id` bigint UNSIGNED NOT NULL,
  `group_id` bigint UNSIGNED NOT NULL,
  PRIMARY KEY (`product_id`, `group_id`) USING BTREE,
  INDEX `FK_tbl_product_group_connect_product_id`(`product_id` ASC) USING BTREE,
  INDEX `FK_tbl_product_group_connect_group_id`(`group_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_product_group_connect_group_id` FOREIGN KEY (`group_id`) REFERENCES `tbl_product_group` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_product_group_connect_product_id` FOREIGN KEY (`product_id`) REFERENCES `tbl_product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_product_group_connect
-- ----------------------------
INSERT INTO `tbl_product_group_connect` VALUES (1, 2);
INSERT INTO `tbl_product_group_connect` VALUES (2, 1);
INSERT INTO `tbl_product_group_connect` VALUES (3, 1);
INSERT INTO `tbl_product_group_connect` VALUES (4, 3);
INSERT INTO `tbl_product_group_connect` VALUES (5, 2);

-- ----------------------------
-- Table structure for tbl_product_supplier_connect
-- ----------------------------
DROP TABLE IF EXISTS `tbl_product_supplier_connect`;
CREATE TABLE `tbl_product_supplier_connect`  (
  `product_id` bigint UNSIGNED NOT NULL,
  `supplier_id` bigint UNSIGNED NOT NULL,
  PRIMARY KEY (`product_id`, `supplier_id`) USING BTREE,
  INDEX `FK_tbl_product_supplier_connect_product_id`(`product_id` ASC) USING BTREE,
  INDEX `FK_tbl_product_supplier_connect_supplier_id`(`supplier_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_product_supplier_connect_product_id` FOREIGN KEY (`product_id`) REFERENCES `tbl_product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_product_supplier_connect_supplier_id` FOREIGN KEY (`supplier_id`) REFERENCES `tbl_supplier` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_product_supplier_connect
-- ----------------------------
INSERT INTO `tbl_product_supplier_connect` VALUES (1, 1);
INSERT INTO `tbl_product_supplier_connect` VALUES (2, 1);
INSERT INTO `tbl_product_supplier_connect` VALUES (2, 2);
INSERT INTO `tbl_product_supplier_connect` VALUES (3, 2);
INSERT INTO `tbl_product_supplier_connect` VALUES (4, 1);
INSERT INTO `tbl_product_supplier_connect` VALUES (5, 2);

-- ----------------------------
-- Table structure for tbl_product_type
-- ----------------------------
DROP TABLE IF EXISTS `tbl_product_type`;
CREATE TABLE `tbl_product_type`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `modify_date` datetime NULL DEFAULT NULL,
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `deleted` bit(1) NULL DEFAULT b'0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UQ_tbl_product_type_code`(`code` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_product_type
-- ----------------------------
INSERT INTO `tbl_product_type` VALUES (1, 'admin', '2023-11-07 20:34:11', 'root', '2023-12-12 08:06:17', '00001', 'Nguyên liệu', b'0');
INSERT INTO `tbl_product_type` VALUES (2, 'admin', '2023-11-07 20:34:22', 'root', '2023-12-12 08:05:58', '00002', 'Bán thành phẩm', b'0');
INSERT INTO `tbl_product_type` VALUES (3, 'admin', '2023-11-07 21:23:50', 'root', '2023-12-12 08:05:49', '00003', 'Thành phẩm', b'0');

-- ----------------------------
-- Table structure for tbl_product_unit
-- ----------------------------
DROP TABLE IF EXISTS `tbl_product_unit`;
CREATE TABLE `tbl_product_unit`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `modify_date` datetime NULL DEFAULT NULL,
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `deleted` bit(1) NULL DEFAULT b'0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UQ_tbl_product_unit_code`(`code` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_product_unit
-- ----------------------------
INSERT INTO `tbl_product_unit` VALUES (1, 'admin', '2023-11-07 20:34:49', NULL, '2023-11-07 20:34:49', '00001', 'KG', b'0');
INSERT INTO `tbl_product_unit` VALUES (2, 'admin', '2023-11-07 20:34:56', NULL, '2023-11-07 20:34:56', '00002', 'Thùng', b'0');
INSERT INTO `tbl_product_unit` VALUES (3, 'root', '2023-12-12 08:10:28', NULL, NULL, '00003', 'Hộp', b'0');

-- ----------------------------
-- Table structure for tbl_product_unit_connect
-- ----------------------------
DROP TABLE IF EXISTS `tbl_product_unit_connect`;
CREATE TABLE `tbl_product_unit_connect`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `modify_date` datetime NULL DEFAULT NULL,
  `product_id` bigint UNSIGNED NOT NULL,
  `unit_id` bigint UNSIGNED NOT NULL,
  `ratio` double NULL DEFAULT 0,
  `is_default` bit(1) NULL DEFAULT b'0',
  `is_use_report` bit(1) NULL DEFAULT b'0',
  `deleted` bit(1) NULL DEFAULT b'0',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_tbl_product_unit_connect_product_id`(`product_id` ASC) USING BTREE,
  INDEX `FK_tbl_product_unit_connect_unit_id`(`unit_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_product_unit_connect_product_id` FOREIGN KEY (`product_id`) REFERENCES `tbl_product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_product_unit_connect_unit_id` FOREIGN KEY (`unit_id`) REFERENCES `tbl_product_unit` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_product_unit_connect
-- ----------------------------
INSERT INTO `tbl_product_unit_connect` VALUES (1, 'admin', '2023-11-07 20:37:05', NULL, '2023-11-07 20:51:49', 1, 1, 1, b'1', b'1', b'0');
INSERT INTO `tbl_product_unit_connect` VALUES (2, 'admin', '2023-11-07 20:37:05', NULL, '2023-11-07 20:51:49', 1, 2, 10, b'0', b'0', b'0');
INSERT INTO `tbl_product_unit_connect` VALUES (3, 'admin', '2023-11-07 20:51:25', NULL, '2023-11-07 20:51:25', 2, 1, 4, b'1', b'1', b'0');
INSERT INTO `tbl_product_unit_connect` VALUES (4, 'admin', '2023-11-07 20:51:25', NULL, '2023-11-07 20:51:25', 2, 2, 8, b'0', b'0', b'0');
INSERT INTO `tbl_product_unit_connect` VALUES (5, 'admin', '2023-11-07 20:51:49', NULL, '2023-11-07 20:51:49', 1, 1, 2, b'0', b'0', b'0');
INSERT INTO `tbl_product_unit_connect` VALUES (6, 'root', '2023-12-12 08:14:04', NULL, NULL, 3, 3, 1, b'1', b'1', b'0');
INSERT INTO `tbl_product_unit_connect` VALUES (7, 'root', '2023-12-12 08:18:04', NULL, NULL, 4, 2, 1, b'1', b'1', b'0');
INSERT INTO `tbl_product_unit_connect` VALUES (8, 'root', '2023-12-12 08:19:01', NULL, NULL, 5, 1, 1, b'1', b'1', b'0');

-- ----------------------------
-- Table structure for tbl_role
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role`;
CREATE TABLE `tbl_role`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `modify_date` datetime NULL DEFAULT NULL,
  `description` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `warehouse_id` bigint UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_tbl_role_name`(`name` ASC) USING BTREE,
  INDEX `FK_tbl_role_warehouse_id`(`warehouse_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_role_warehouse_id` FOREIGN KEY (`warehouse_id`) REFERENCES `tbl_warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_role
-- ----------------------------
INSERT INTO `tbl_role` VALUES (1, 'admin', '2022-02-02 00:00:00', 'admin', '2022-02-02 00:00:00', 'ROOT', 'ROLE_ROOT', NULL);
INSERT INTO `tbl_role` VALUES (4, 'admin', '2022-02-02 00:00:00', 'root', '2023-11-29 17:22:16', 'WA002_ROOT', 'ROLE_WA002_ROOT', 6);
INSERT INTO `tbl_role` VALUES (5, 'admin', '2022-02-02 00:00:00', 'root', '2023-11-29 17:12:49', 'WA003_ROOT', 'ROLE_WA003_ROOT', 7);
INSERT INTO `tbl_role` VALUES (7, 'root', '2023-12-12 08:50:02', NULL, NULL, 'WH01_VIEW_MAP', 'WH01_VIEW_MAP', 6);

-- ----------------------------
-- Table structure for tbl_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role_authority`;
CREATE TABLE `tbl_role_authority`  (
  `role_id` bigint UNSIGNED NOT NULL,
  `authority_id` bigint UNSIGNED NOT NULL,
  PRIMARY KEY (`role_id`, `authority_id`) USING BTREE,
  INDEX `FK_tbl_role_authority_user_id`(`role_id` ASC) USING BTREE,
  INDEX `FK_tbl_role_authority_authority_id`(`authority_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_role_authority_authority_id` FOREIGN KEY (`authority_id`) REFERENCES `tbl_authority` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_role_authority_role_id` FOREIGN KEY (`role_id`) REFERENCES `tbl_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_role_authority
-- ----------------------------
INSERT INTO `tbl_role_authority` VALUES (1, 1);
INSERT INTO `tbl_role_authority` VALUES (1, 2);
INSERT INTO `tbl_role_authority` VALUES (1, 3);
INSERT INTO `tbl_role_authority` VALUES (1, 4);
INSERT INTO `tbl_role_authority` VALUES (1, 5);
INSERT INTO `tbl_role_authority` VALUES (1, 6);
INSERT INTO `tbl_role_authority` VALUES (1, 7);
INSERT INTO `tbl_role_authority` VALUES (1, 8);
INSERT INTO `tbl_role_authority` VALUES (1, 9);
INSERT INTO `tbl_role_authority` VALUES (1, 10);
INSERT INTO `tbl_role_authority` VALUES (1, 11);
INSERT INTO `tbl_role_authority` VALUES (1, 12);
INSERT INTO `tbl_role_authority` VALUES (1, 13);
INSERT INTO `tbl_role_authority` VALUES (4, 9);
INSERT INTO `tbl_role_authority` VALUES (5, 9);
INSERT INTO `tbl_role_authority` VALUES (7, 47);

-- ----------------------------
-- Table structure for tbl_supplier
-- ----------------------------
DROP TABLE IF EXISTS `tbl_supplier`;
CREATE TABLE `tbl_supplier`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `modify_date` datetime NULL DEFAULT NULL,
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `deleted` bit(1) NULL DEFAULT b'0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UQ_tbl_supplier_code`(`code` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_supplier
-- ----------------------------
INSERT INTO `tbl_supplier` VALUES (1, 'admin', '2023-11-07 20:35:31', 'root', '2023-12-12 08:12:44', '00001', 'AGRGEX SAIGON', b'0');
INSERT INTO `tbl_supplier` VALUES (2, 'admin', '2023-11-07 20:35:40', 'root', '2023-12-12 08:12:11', '00002', 'vinafood', b'0');
INSERT INTO `tbl_supplier` VALUES (3, 'root', '2023-12-12 08:12:57', NULL, NULL, '00003', 'VINACONIC', b'0');

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `modify_date` datetime NULL DEFAULT NULL,
  `account_non_locked` bit(1) NOT NULL,
  `active` bit(1) NOT NULL,
  `email` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `failed_attempt` tinyint NULL DEFAULT NULL,
  `lock_time` datetime NULL DEFAULT NULL,
  `need_fix_pass` bit(1) NULL DEFAULT b'0',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_tbl_user_username`(`username` ASC) USING BTREE,
  UNIQUE INDEX `UK_tbl_user_email`(`email` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES (1, 'admin', '2022-02-02 00:00:00', 'root', '2023-11-29 18:39:33', b'1', b'1', 'root@gmail.com', 0, NULL, b'0', '$2a$10$BWi6mJVbU7JsY60Y3nhpVusoSZB8suV9U/BoNu3XfVPcX0Dxdgeiq', 'root');
INSERT INTO `tbl_user` VALUES (3, 'root', '2023-11-28 16:16:18', 'root', '2023-11-29 17:13:18', b'1', b'1', 'nv01@gmail.com', 0, NULL, b'0', '$2a$12$lVQ0sSUWMULheopP.iISWeTPTYv7YC1zZ5D39FCPS.AWhl/pPrKqi', 'nhanvien01');
INSERT INTO `tbl_user` VALUES (4, 'root', '2023-11-28 19:18:25', 'root', '2023-11-29 17:27:47', b'1', b'1', 'nv02@gmail.com', 0, NULL, b'0', '$2a$12$lVQ0sSUWMULheopP.iISWeTPTYv7YC1zZ5D39FCPS.AWhl/pPrKqi', 'nhanvien02');
INSERT INTO `tbl_user` VALUES (5, 'root', '2023-11-29 06:33:06', 'root', '2023-11-29 06:48:19', b'1', b'0', 'nv03@gmail.com', 0, NULL, b'0', '$2a$12$lVQ0sSUWMULheopP.iISWeTPTYv7YC1zZ5D39FCPS.AWhl/pPrKqi', '00004');
INSERT INTO `tbl_user` VALUES (6, 'root', '2023-11-29 06:53:01', 'root', '2023-11-29 17:49:47', b'1', b'1', 'nv04@gmail.com', 0, NULL, b'0', '$2a$12$lVQ0sSUWMULheopP.iISWeTPTYv7YC1zZ5D39FCPS.AWhl/pPrKqi', '00005');
INSERT INTO `tbl_user` VALUES (7, 'nhanvien01', '2023-11-29 18:20:38', 'root', '2023-12-12 08:50:26', b'1', b'1', 'nv00006@gmail.com', 0, NULL, b'0', '$2a$12$lVQ0sSUWMULheopP.iISWeTPTYv7YC1zZ5D39FCPS.AWhl/pPrKqi', '00006');

-- ----------------------------
-- Table structure for tbl_user_authority
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_authority`;
CREATE TABLE `tbl_user_authority`  (
  `user_id` bigint UNSIGNED NOT NULL,
  `authority_id` bigint UNSIGNED NOT NULL,
  PRIMARY KEY (`user_id`, `authority_id`) USING BTREE,
  INDEX `FK_tbl_user_authority_user_id`(`user_id` ASC) USING BTREE,
  INDEX `FK_tbl_user_authority_authority_id`(`authority_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_user_authority_authority_id` FOREIGN KEY (`authority_id`) REFERENCES `tbl_authority` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_user_authority_user_id` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_user_authority
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_role`;
CREATE TABLE `tbl_user_role`  (
  `user_id` bigint UNSIGNED NOT NULL,
  `role_id` bigint UNSIGNED NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `FK_tbl_user_role_tbl_role_role_id`(`role_id` ASC) USING BTREE,
  INDEX `FK_tbl_user_role_tbl_user_user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_user_role_tbl_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `tbl_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_user_role_tbl_user_user_id` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_user_role
-- ----------------------------
INSERT INTO `tbl_user_role` VALUES (1, 1);
INSERT INTO `tbl_user_role` VALUES (3, 4);
INSERT INTO `tbl_user_role` VALUES (4, 5);
INSERT INTO `tbl_user_role` VALUES (7, 7);

-- ----------------------------
-- Table structure for tbl_verification_codes
-- ----------------------------
DROP TABLE IF EXISTS `tbl_verification_codes`;
CREATE TABLE `tbl_verification_codes`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `modify_date` datetime NULL DEFAULT NULL,
  `code_sms` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `expired` datetime NULL DEFAULT NULL,
  `type` int NULL DEFAULT NULL,
  `user_id` bigint NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_verification_codes
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `tbl_warehouse`;
CREATE TABLE `tbl_warehouse`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `modify_date` datetime NULL DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `address` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UC_tbl_warehouse_code`(`code` ASC) USING BTREE,
  INDEX `IND_tbl_warehouse_deleted`(`deleted` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_warehouse
-- ----------------------------
INSERT INTO `tbl_warehouse` VALUES (6, 'admin', '2023-11-07 20:27:25', NULL, '2023-11-07 20:52:23', 'KHO 01', 'WA002', 'YP - BN', b'0');
INSERT INTO `tbl_warehouse` VALUES (7, 'root', '2023-11-18 10:13:56', 'nhanvien02', '2023-12-12 00:23:25', 'KHO 02', 'WA003', 'YP - BN', b'0');
INSERT INTO `tbl_warehouse` VALUES (8, 'root', '2023-11-25 06:07:07', 'root', '2023-12-05 07:28:54', 'WH 04', 'WA004', 'YP - BN', b'0');

-- ----------------------------
-- Table structure for tbl_warehouse_area
-- ----------------------------
DROP TABLE IF EXISTS `tbl_warehouse_area`;
CREATE TABLE `tbl_warehouse_area`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `modify_date` datetime NULL DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `warehouse_id` bigint UNSIGNED NOT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IND_tbl_warehouse_area_deleted`(`deleted` ASC) USING BTREE,
  INDEX `FK_tbl_warehouse_area_tbl_warehouse_warehouse_id`(`warehouse_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_warehouse_area_tbl_warehouse_warehouse_id` FOREIGN KEY (`warehouse_id`) REFERENCES `tbl_warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_warehouse_area
-- ----------------------------
INSERT INTO `tbl_warehouse_area` VALUES (11, 'admin', '2023-11-07 20:27:25', NULL, '2023-11-07 20:52:23', 'A', 'WA002/AR000', 6, b'0');
INSERT INTO `tbl_warehouse_area` VALUES (13, 'admin', '2023-11-07 20:30:44', NULL, '2023-11-07 20:52:23', 'B', 'WA002/AR000', 6, b'0');
INSERT INTO `tbl_warehouse_area` VALUES (14, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'C', 'WA002/AR000', 6, b'0');
INSERT INTO `tbl_warehouse_area` VALUES (15, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'A', 'WA003/AR000', 7, b'0');
INSERT INTO `tbl_warehouse_area` VALUES (16, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'B', 'WA003/AR001', 7, b'0');
INSERT INTO `tbl_warehouse_area` VALUES (17, 'root', '2023-11-25 06:07:07', 'root', '2023-12-03 17:33:17', 'A', 'WA004/AR000', 8, b'0');
INSERT INTO `tbl_warehouse_area` VALUES (18, 'root', '2023-11-25 06:07:07', 'root', '2023-12-03 17:33:17', 'B', 'WA004/AR001', 8, b'0');

-- ----------------------------
-- Table structure for tbl_warehouse_export
-- ----------------------------
DROP TABLE IF EXISTS `tbl_warehouse_export`;
CREATE TABLE `tbl_warehouse_export`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `warehouse_id` bigint UNSIGNED NOT NULL,
  `employee_id` bigint UNSIGNED NOT NULL,
  `product_type_id` bigint UNSIGNED NOT NULL,
  `warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `employee_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `input_date` datetime NOT NULL,
  `status` int NOT NULL,
  `document_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `document_date` datetime NULL DEFAULT NULL,
  `container_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `seal_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `quality` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `order_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `consumer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `note` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `modify_date` datetime NULL DEFAULT NULL,
  `type` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_tbl_warehouse_export_warehouse_id`(`warehouse_id` ASC) USING BTREE,
  INDEX `FK_tbl_warehouse_export_employee_id`(`employee_id` ASC) USING BTREE,
  INDEX `FK_tbl_warehouse_export_product_type_id`(`product_type_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_warehouse_export_employee_id` FOREIGN KEY (`employee_id`) REFERENCES `tbl_employee` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_warehouse_export_product_type_id` FOREIGN KEY (`product_type_id`) REFERENCES `tbl_product_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_warehouse_export_warehouse_id` FOREIGN KEY (`warehouse_id`) REFERENCES `tbl_warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_warehouse_export
-- ----------------------------
INSERT INTO `tbl_warehouse_export` VALUES (8, 7, 1, 1, 'KHO 02', 'ROOT', 'EXP202311000006', '2023-11-20 00:00:00', 1, 'SCT001', '2023-11-20 00:00:00', 'CTN0001', 'SL0123', NULL, 'MDH001', NULL, 'note', 'root', '2023-11-20 04:07:37', 'root', '2023-11-20 04:07:37', 0);
INSERT INTO `tbl_warehouse_export` VALUES (9, 7, 1, 1, 'KHO 02', 'ROOT', 'EXP202311000007', '2023-11-21 00:00:00', 1, 'SCT001', '2023-11-21 00:00:00', 'CTN0001', 'SL0123', NULL, 'MDH001', NULL, 'note', 'root', '2023-11-21 16:23:08', 'root', '2023-11-21 16:23:08', 0);
INSERT INTO `tbl_warehouse_export` VALUES (10, 7, 1, 1, 'KHO 02', 'ROOT', 'EXP202311000008', '2023-11-21 00:00:00', 1, 'SCT001', '2023-11-21 00:00:00', 'CTN0001', 'SL0123', NULL, 'MDH001', NULL, 'note', 'root', '2023-11-21 16:24:26', 'root', '2023-11-21 18:56:46', 0);
INSERT INTO `tbl_warehouse_export` VALUES (11, 6, 1, 1, 'KHO 01', 'ROOT', 'EXP202312000009', '2023-12-08 00:00:00', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'root', '2023-12-08 05:46:39', 'root', '2023-12-08 05:46:39', NULL);
INSERT INTO `tbl_warehouse_export` VALUES (13, 6, 1, 1, 'KHO 01', 'ROOT', 'EXP202312000010', '2023-12-09 00:00:00', 0, 'SCT001', '2023-12-09 00:00:00', 'CTN0001', '0123', NULL, 'MDH001', NULL, 'note', 'root', '2023-12-09 14:48:40', 'root', '2023-12-10 08:54:42', NULL);
INSERT INTO `tbl_warehouse_export` VALUES (14, 6, 1, 1, 'KHO 01', 'ROOT', 'EXP202312000011', '2023-12-10 00:00:00', 1, 'SCT001', '2023-12-10 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, 'root', '2023-12-10 08:46:22', 'root', '2023-12-10 08:52:06', NULL);
INSERT INTO `tbl_warehouse_export` VALUES (15, 6, 1, 1, 'KHO 01', 'ROOT', 'EXP202312000012', '2023-12-10 00:00:00', 0, NULL, NULL, NULL, NULL, NULL, 'MDH001', 'Trương Đình Vũ', 'note', 'root', '2023-12-10 08:57:14', 'root', '2023-12-10 08:57:19', NULL);

-- ----------------------------
-- Table structure for tbl_warehouse_export_file_connect
-- ----------------------------
DROP TABLE IF EXISTS `tbl_warehouse_export_file_connect`;
CREATE TABLE `tbl_warehouse_export_file_connect`  (
  `file_id` bigint UNSIGNED NOT NULL,
  `wh_export_id` bigint UNSIGNED NOT NULL,
  PRIMARY KEY (`file_id`, `wh_export_id`) USING BTREE,
  UNIQUE INDEX `UK_cmndxn9xs99ry2eqbovu54290`(`file_id` ASC) USING BTREE,
  INDEX `tbl_wh_export_file_connect_file_id`(`file_id` ASC) USING BTREE,
  INDEX `tbl_wh_export_file_connect_wh_import_id`(`wh_export_id` ASC) USING BTREE,
  CONSTRAINT `tbl_wh_export_file_connect_file_id` FOREIGN KEY (`file_id`) REFERENCES `tbl_file_description` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tbl_wh_export_file_connect_wh_import_id` FOREIGN KEY (`wh_export_id`) REFERENCES `tbl_warehouse_export` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_warehouse_export_file_connect
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_warehouse_export_item
-- ----------------------------
DROP TABLE IF EXISTS `tbl_warehouse_export_item`;
CREATE TABLE `tbl_warehouse_export_item`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `parent_id` bigint UNSIGNED NOT NULL,
  `product_id` bigint UNSIGNED NOT NULL,
  `unit_target_id` bigint UNSIGNED NOT NULL,
  `unit_source_id` bigint UNSIGNED NOT NULL,
  `product_detail_id` bigint UNSIGNED NULL DEFAULT NULL,
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `consignment_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `quantity_target` double NOT NULL,
  `price` double NULL DEFAULT NULL,
  `quantity_source` double NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_tbl_wh_export_item_product_id`(`product_id` ASC) USING BTREE,
  INDEX `FK_tbl_wh_export_item_unit_target_id`(`unit_target_id` ASC) USING BTREE,
  INDEX `FK_tbl_wh_export_item_unit_source_id`(`unit_source_id` ASC) USING BTREE,
  INDEX `FK_tbl_wh_export_item_parent_id`(`parent_id` ASC) USING BTREE,
  INDEX `FK_tbl_wh_export_item_product_detail_id`(`product_detail_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_wh_export_item_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `tbl_warehouse_export` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_wh_export_item_product_detail_id` FOREIGN KEY (`product_detail_id`) REFERENCES `tbl_product_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_wh_export_item_product_id` FOREIGN KEY (`product_id`) REFERENCES `tbl_product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_wh_export_item_unit_source_id` FOREIGN KEY (`unit_source_id`) REFERENCES `tbl_product_unit_connect` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_wh_export_item_unit_target_id` FOREIGN KEY (`unit_target_id`) REFERENCES `tbl_product_unit_connect` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_warehouse_export_item
-- ----------------------------
INSERT INTO `tbl_warehouse_export_item` VALUES (6, 8, 1, 2, 1, 1, 'Hàng hóa 1', '', 2, 223, 0.2);
INSERT INTO `tbl_warehouse_export_item` VALUES (7, 9, 1, 2, 1, 1, 'Hàng hóa 1', '', 1, 223, 0.1);
INSERT INTO `tbl_warehouse_export_item` VALUES (14, 10, 1, 1, 1, 1, 'Hàng hóa 1', '', 2, 223, 2);
INSERT INTO `tbl_warehouse_export_item` VALUES (15, 11, 1, 2, 1, 1, 'Hàng hóa 1', '', 1, 223, 0.1);
INSERT INTO `tbl_warehouse_export_item` VALUES (19, 14, 1, 1, 1, 1, 'Hàng hóa 1', '', 1, 223, 1);
INSERT INTO `tbl_warehouse_export_item` VALUES (21, 13, 1, 1, 1, 1, 'Hàng hóa 1', '', 1, 223, 1);
INSERT INTO `tbl_warehouse_export_item` VALUES (23, 15, 1, 5, 1, 1, 'Hàng hóa 1', 'L01', 1, 223, 0.5);

-- ----------------------------
-- Table structure for tbl_warehouse_export_item_detail
-- ----------------------------
DROP TABLE IF EXISTS `tbl_warehouse_export_item_detail`;
CREATE TABLE `tbl_warehouse_export_item_detail`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `parent_id` bigint UNSIGNED NOT NULL,
  `floor_id` bigint UNSIGNED NOT NULL,
  `floor_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `input_date` datetime NOT NULL,
  `quantity` double NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_tbl_wh_export_item_detail_parent_id`(`parent_id` ASC) USING BTREE,
  INDEX `FK_tbl_wh_export_item_detail_floor_id`(`floor_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_wh_export_item_detail_floor_id` FOREIGN KEY (`floor_id`) REFERENCES `tbl_warehouse_floor` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_wh_export_item_detail_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `tbl_warehouse_export_item` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_warehouse_export_item_detail
-- ----------------------------
INSERT INTO `tbl_warehouse_export_item_detail` VALUES (11, 6, 337, 'B-0-0', '2023-11-18 00:00:00', 1);
INSERT INTO `tbl_warehouse_export_item_detail` VALUES (12, 6, 341, 'B-2-0', '2023-11-18 00:00:00', 1);
INSERT INTO `tbl_warehouse_export_item_detail` VALUES (13, 7, 339, 'B-1-0', '2023-11-18 00:00:00', 1);
INSERT INTO `tbl_warehouse_export_item_detail` VALUES (23, 14, 327, 'A-0-0', '2023-11-18 00:00:00', 1);
INSERT INTO `tbl_warehouse_export_item_detail` VALUES (24, 14, 329, 'A-1-0', '2023-11-18 00:00:00', 1);
INSERT INTO `tbl_warehouse_export_item_detail` VALUES (25, 15, 312, 'B-2-1', '2023-11-15 00:00:00', 1);
INSERT INTO `tbl_warehouse_export_item_detail` VALUES (29, 19, 267, 'A-1-0', '2023-11-09 19:01:00', 1);
INSERT INTO `tbl_warehouse_export_item_detail` VALUES (31, 21, 267, 'A-1-0', '2023-11-09 19:01:00', 1);
INSERT INTO `tbl_warehouse_export_item_detail` VALUES (33, 23, 304, 'A-1-1', '2023-11-15 00:00:00', 1);

-- ----------------------------
-- Table structure for tbl_warehouse_floor
-- ----------------------------
DROP TABLE IF EXISTS `tbl_warehouse_floor`;
CREATE TABLE `tbl_warehouse_floor`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `modify_date` datetime NULL DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `warehouse_location_id` bigint UNSIGNED NOT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IND_tbl_tbl_warehouse_floor_deleted`(`deleted` ASC) USING BTREE,
  INDEX `FK_tbl_warehouse_floor_tbl_wh_location_warehouse_location_id`(`warehouse_location_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_warehouse_floor_tbl_wh_location_warehouse_location_id` FOREIGN KEY (`warehouse_location_id`) REFERENCES `tbl_warehouse_location` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 402 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_warehouse_floor
-- ----------------------------
INSERT INTO `tbl_warehouse_floor` VALUES (267, 'admin', '2023-11-07 20:27:25', NULL, '2023-11-07 20:27:25', 'A-1-0', 'WA002/AR000/LO001/FL000', 134, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (268, 'admin', '2023-11-07 20:27:25', NULL, '2023-11-07 20:27:25', 'A-1-1', 'WA002/AR000/LO001/FL001', 134, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (269, 'admin', '2023-11-07 20:27:25', NULL, '2023-11-07 20:27:25', 'A-2-0', 'WA002/AR000/LO002/FL000', 135, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (270, 'admin', '2023-11-07 20:27:25', NULL, '2023-11-07 20:27:25', 'A-2-1', 'WA002/AR000/LO002/FL001', 135, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (271, 'admin', '2023-11-07 20:27:25', NULL, '2023-11-07 20:27:25', 'A-3-0', 'WA002/AR000/LO003/FL000', 136, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (272, 'admin', '2023-11-07 20:27:25', NULL, '2023-11-07 20:27:25', 'A-3-1', 'WA002/AR000/LO003/FL001', 136, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (273, 'admin', '2023-11-07 20:27:25', NULL, '2023-11-07 20:27:25', 'A-4-0', 'WA002/AR000/LO004/FL000', 137, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (274, 'admin', '2023-11-07 20:27:25', NULL, '2023-11-07 20:27:25', 'A-4-1', 'WA002/AR000/LO004/FL001', 137, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (275, 'admin', '2023-11-07 20:27:25', NULL, '2023-11-07 20:27:25', 'A-5-0', 'WA002/AR000/LO005/FL000', 138, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (276, 'admin', '2023-11-07 20:27:25', NULL, '2023-11-07 20:27:25', 'A-5-1', 'WA002/AR000/LO005/FL001', 138, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (289, 'admin', '2023-11-07 20:30:44', NULL, '2023-11-07 20:30:44', 'B-0-0', 'WA002/AR000/LO000/FL000', 145, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (290, 'admin', '2023-11-07 20:30:44', NULL, '2023-11-07 20:30:44', 'B-0-1', 'WA002/AR000/LO000/FL001', 145, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (291, 'admin', '2023-11-07 20:30:44', NULL, '2023-11-07 20:30:44', 'B-1-0', 'WA002/AR000/LO001/FL000', 146, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (292, 'admin', '2023-11-07 20:30:44', NULL, '2023-11-07 20:30:44', 'B-1-1', 'WA002/AR000/LO001/FL001', 146, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (293, 'admin', '2023-11-07 20:30:44', NULL, '2023-11-07 20:30:44', 'B-2-0', 'WA002/AR000/LO002/FL000', 147, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (294, 'admin', '2023-11-07 20:30:44', NULL, '2023-11-07 20:30:44', 'B-2-1', 'WA002/AR000/LO002/FL001', 147, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (295, 'admin', '2023-11-07 20:30:44', NULL, '2023-11-07 20:30:44', 'B-3-0', 'WA002/AR000/LO003/FL000', 148, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (296, 'admin', '2023-11-07 20:30:44', NULL, '2023-11-07 20:30:44', 'B-3-1', 'WA002/AR000/LO003/FL001', 148, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (297, 'admin', '2023-11-07 20:30:44', NULL, '2023-11-07 20:30:44', 'B-4-0', 'WA002/AR000/LO004/FL000', 149, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (298, 'admin', '2023-11-07 20:30:44', NULL, '2023-11-07 20:30:44', 'B-4-1', 'WA002/AR000/LO004/FL001', 149, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (299, 'admin', '2023-11-07 20:30:44', NULL, '2023-11-07 20:30:44', 'B-5-0', 'WA002/AR000/LO005/FL000', 150, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (300, 'admin', '2023-11-07 20:30:44', NULL, '2023-11-07 20:30:44', 'B-5-1', 'WA002/AR000/LO005/FL001', 150, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (301, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'A-0-0', 'WA002/AR000/LO000/FL000', 151, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (302, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'A-0-1', 'WA002/AR000/LO000/FL001', 151, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (303, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'A-1-0', 'WA002/AR000/LO001/FL000', 152, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (304, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'A-1-1', 'WA002/AR000/LO001/FL001', 152, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (305, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'A-2-0', 'WA002/AR000/LO002/FL000', 153, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (306, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'A-2-1', 'WA002/AR000/LO002/FL001', 153, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (307, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'B-0-0', 'WA002/AR000/LO000/FL000', 154, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (308, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'B-0-1', 'WA002/AR000/LO000/FL001', 154, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (309, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'B-1-0', 'WA002/AR000/LO001/FL000', 155, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (310, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'B-1-1', 'WA002/AR000/LO001/FL001', 155, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (311, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'B-2-0', 'WA002/AR000/LO002/FL000', 156, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (312, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'B-2-1', 'WA002/AR000/LO002/FL001', 156, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (313, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'B-3-0', 'WA002/AR000/LO003/FL000', 157, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (314, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'B-3-1', 'WA002/AR000/LO003/FL001', 157, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (315, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'C-0-0', 'WA002/AR000/LO000/FL000', 158, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (316, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'C-0-1', 'WA002/AR000/LO000/FL001', 158, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (317, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'C-1-0', 'WA002/AR000/LO001/FL000', 159, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (318, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'C-1-1', 'WA002/AR000/LO001/FL001', 159, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (319, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'C-2-0', 'WA002/AR000/LO002/FL000', 160, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (320, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'C-2-1', 'WA002/AR000/LO002/FL001', 160, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (321, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'C-3-0', 'WA002/AR000/LO003/FL000', 161, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (322, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'C-3-1', 'WA002/AR000/LO003/FL001', 161, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (323, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'C-4-0', 'WA002/AR000/LO004/FL000', 162, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (324, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'C-4-1', 'WA002/AR000/LO004/FL001', 162, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (325, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'C-5-0', 'WA002/AR000/LO005/FL000', 163, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (326, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'C-5-1', 'WA002/AR000/LO005/FL001', 163, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (327, 'root', '2023-11-18 10:13:58', 'nhanvien02', '2023-12-12 00:23:25', 'A-0-0', 'WA003/AR000/LO000/FL000', 164, b'1');
INSERT INTO `tbl_warehouse_floor` VALUES (328, 'root', '2023-11-18 10:13:58', 'nhanvien02', '2023-12-12 00:23:25', 'A-0-1', 'WA003/AR000/LO000/FL001', 164, b'1');
INSERT INTO `tbl_warehouse_floor` VALUES (329, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'A-1-0', 'WA003/AR000/LO001/FL000', 165, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (330, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'A-1-1', 'WA003/AR000/LO001/FL001', 165, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (331, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'A-2-0', 'WA003/AR000/LO002/FL000', 166, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (332, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'A-2-1', 'WA003/AR000/LO002/FL001', 166, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (333, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'A-3-0', 'WA003/AR000/LO003/FL000', 167, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (334, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'A-3-1', 'WA003/AR000/LO003/FL001', 167, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (335, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'A-4-0', 'WA003/AR000/LO004/FL000', 168, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (336, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'A-4-1', 'WA003/AR000/LO004/FL001', 168, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (337, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'B-0-0', 'WA003/AR001/LO000/FL000', 169, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (338, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'B-0-1', 'WA003/AR001/LO000/FL001', 169, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (339, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'B-1-0', 'WA003/AR001/LO001/FL000', 170, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (340, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'B-1-1', 'WA003/AR001/LO001/FL001', 170, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (341, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'B-2-0', 'WA003/AR001/LO002/FL000', 171, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (342, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'B-2-1', 'WA003/AR001/LO002/FL001', 171, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (343, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'B-3-0', 'WA003/AR001/LO003/FL000', 172, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (344, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'B-3-1', 'WA003/AR001/LO003/FL001', 172, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (345, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'B-4-0', 'WA003/AR001/LO004/FL000', 173, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (346, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'B-4-1', 'WA003/AR001/LO004/FL001', 173, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (383, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'A-7-0', 'WA004/AR000/LO007/FL000', 192, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (384, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'A-7-1', 'WA004/AR000/LO007/FL001', 192, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (385, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'A-8-0', 'WA004/AR000/LO008/FL000', 193, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (386, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'A-8-1', 'WA004/AR000/LO008/FL001', 193, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (387, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'A-9-0', 'WA004/AR000/LO009/FL000', 194, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (388, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'A-9-1', 'WA004/AR000/LO009/FL001', 194, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (389, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'A-10-0', 'WA004/AR000/LO010/FL000', 195, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (390, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'A-10-1', 'WA004/AR000/LO010/FL001', 195, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (391, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'A-11-0', 'WA004/AR000/LO011/FL000', 196, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (392, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'A-11-1', 'WA004/AR000/LO011/FL001', 196, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (393, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'B-7-0', 'WA004/AR001/LO007/FL000', 197, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (394, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'B-7-1', 'WA004/AR001/LO007/FL001', 197, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (395, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'B-8-0', 'WA004/AR001/LO008/FL000', 198, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (396, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'B-8-1', 'WA004/AR001/LO008/FL001', 198, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (397, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'B-9-0', 'WA004/AR001/LO009/FL000', 199, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (398, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'B-9-1', 'WA004/AR001/LO009/FL001', 199, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (399, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'B-10-0', 'WA004/AR001/LO010/FL000', 200, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (400, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'B-10-1', 'WA004/AR001/LO010/FL001', 200, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (401, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'B-11-0', 'WA004/AR001/LO011/FL000', 201, b'0');
INSERT INTO `tbl_warehouse_floor` VALUES (402, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'B-11-1', 'WA004/AR001/LO011/FL001', 201, b'0');

-- ----------------------------
-- Table structure for tbl_warehouse_import
-- ----------------------------
DROP TABLE IF EXISTS `tbl_warehouse_import`;
CREATE TABLE `tbl_warehouse_import`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `warehouse_id` bigint UNSIGNED NOT NULL,
  `employee_id` bigint UNSIGNED NOT NULL,
  `supplier_id` bigint UNSIGNED NULL DEFAULT NULL,
  `product_type_id` bigint UNSIGNED NOT NULL,
  `warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `employee_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `input_date` datetime NOT NULL,
  `status` int NOT NULL,
  `document_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `document_date` datetime NULL DEFAULT NULL,
  `container_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `seal_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `order_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `note` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `modify_date` datetime NULL DEFAULT NULL,
  `type` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_tbl_warehouse_import_warehouse_id`(`warehouse_id` ASC) USING BTREE,
  INDEX `FK_tbl_warehouse_import_employee_id`(`employee_id` ASC) USING BTREE,
  INDEX `FK_tbl_warehouse_import_supplier_id`(`supplier_id` ASC) USING BTREE,
  INDEX `FK_tbl_warehouse_import_product_type_id`(`product_type_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_warehouse_import_employee_id` FOREIGN KEY (`employee_id`) REFERENCES `tbl_employee` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_warehouse_import_product_type_id` FOREIGN KEY (`product_type_id`) REFERENCES `tbl_product_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_warehouse_import_supplier_id` FOREIGN KEY (`supplier_id`) REFERENCES `tbl_supplier` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_warehouse_import_warehouse_id` FOREIGN KEY (`warehouse_id`) REFERENCES `tbl_warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_warehouse_import
-- ----------------------------
INSERT INTO `tbl_warehouse_import` VALUES (2, 6, 1, 1, 1, 'KHO 01', 'ROOT', 'IMP202311000001', '2023-11-09 19:01:00', 1, 'DOC0001', '2023-11-09 19:01:00', 'CTN0001', '0123', '0123', '', 'admin', '2023-11-09 19:47:34', NULL, '2023-11-09 19:51:59', 0);
INSERT INTO `tbl_warehouse_import` VALUES (3, 6, 1, 1, 1, 'KHO 01', 'ROOT', 'IMP202311000002', '2023-11-15 00:00:00', 0, 'SCT001', '2023-11-15 00:00:00', 'CTN0001', 'SL0123', 'MDH001', 'note', 'root', '2023-11-15 06:52:28', 'root', '2023-11-15 06:52:28', 0);
INSERT INTO `tbl_warehouse_import` VALUES (4, 6, 1, 1, 1, 'KHO 01', 'ROOT', 'IMP202311000003', '2023-11-15 00:00:00', 0, 'SCT001', '2023-11-15 00:00:00', 'CTN0001', 'SL0123', 'MDH001', 'note', 'root', '2023-11-15 07:11:11', 'root', '2023-11-15 07:11:11', 0);
INSERT INTO `tbl_warehouse_import` VALUES (5, 6, 1, 1, 1, 'KHO 01', 'ROOT', 'IMP202311000004', '2023-11-15 00:00:00', 1, 'SCT001', '2023-11-15 00:00:00', 'CTN0001', 'SL0123', 'MDH001', 'note', 'root', '2023-11-15 09:03:14', 'root', '2023-11-15 09:03:14', 0);
INSERT INTO `tbl_warehouse_import` VALUES (6, 6, 1, 1, 1, 'KHO 01', 'ROOT', 'IMP202311000005', '2023-11-15 00:00:00', 1, 'SCT001', '2023-11-15 00:00:00', 'CTN0001', 'SL0123', 'MDH001', 'note', 'root', '2023-11-15 09:04:03', 'root', '2023-11-15 09:04:03', 0);
INSERT INTO `tbl_warehouse_import` VALUES (7, 6, 1, 1, 1, 'KHO 01', 'ROOT', 'IMP202311000006', '2023-11-15 00:00:00', 1, 'SCT001', '2023-11-15 00:00:00', 'CTN0001', 'SL0123', 'MDH001', 'note', 'root', '2023-11-15 09:05:47', 'root', '2023-11-15 09:05:47', 0);
INSERT INTO `tbl_warehouse_import` VALUES (8, 6, 1, 1, 1, 'KHO 01', 'ROOT', 'IMP202311000007', '2023-11-15 00:00:00', 1, 'SCT001', '2023-11-15 00:00:00', 'CTN0001', 'SL0123', 'MDH001', 'note', 'root', '2023-11-15 09:06:15', 'root', '2023-11-15 09:06:15', 0);
INSERT INTO `tbl_warehouse_import` VALUES (9, 6, 1, 1, 1, 'KHO 01', 'ROOT', 'IMP202311000008', '2023-11-15 00:00:00', 1, 'SCT001', '2023-11-15 00:00:00', 'CTN0001', 'SL0123', 'MDH001', 'note', 'root', '2023-11-15 09:07:14', 'root', '2023-11-15 09:07:14', 0);
INSERT INTO `tbl_warehouse_import` VALUES (10, 6, 1, 1, 1, 'KHO 01', 'ROOT', 'IMP202311000009', '2023-11-15 00:00:00', 1, 'SCT001', '2023-11-15 00:00:00', 'CTN0001', 'SL0123', 'MDH001', 'note', 'root', '2023-11-15 09:09:17', 'root', '2023-11-15 09:09:17', 0);
INSERT INTO `tbl_warehouse_import` VALUES (11, 6, 1, 1, 1, 'KHO 01', 'ROOT', 'IMP202311000010', '2023-11-15 00:00:00', 1, 'SCT001', '2023-11-15 00:00:00', 'CTN0001', 'SL0123', 'MDH001', 'note', 'root', '2023-11-15 09:09:26', 'root', '2023-11-15 09:09:26', 0);
INSERT INTO `tbl_warehouse_import` VALUES (12, 6, 1, 1, 1, 'KHO 01', 'ROOT', 'IMP202311000011', '2023-11-15 00:00:00', 1, 'SCT001', '2023-11-15 00:00:00', 'CTN0001', 'SL0123', 'MDH001', 'note', 'root', '2023-11-15 09:13:44', 'root', '2023-11-15 09:13:44', 0);
INSERT INTO `tbl_warehouse_import` VALUES (13, 6, 1, 1, 1, 'KHO 01', 'ROOT', 'IMP202311000012', '2023-11-15 00:00:00', 1, 'SCT001', '2023-11-15 00:00:00', 'CTN0001', 'SL0123', 'MDH001', 'note', 'root', '2023-11-15 10:02:41', 'root', '2023-11-15 10:02:41', 0);
INSERT INTO `tbl_warehouse_import` VALUES (14, 6, 1, 1, 1, 'KHO 01', 'ROOT', 'IMP202311000013', '2023-11-15 00:00:00', 1, 'SCT001', '2023-11-15 00:00:00', 'CTN0001', 'SL0123', 'MDH001', 'note', 'root', '2023-11-15 10:05:37', 'root', '2023-11-15 10:05:37', 0);
INSERT INTO `tbl_warehouse_import` VALUES (15, 6, 1, 1, 1, 'KHO 01', 'ROOT', 'IMP202311000014', '2023-11-09 00:00:00', 0, 'DOC0001', '2023-11-09 00:00:00', 'CTN0001', '0123', '0123', NULL, 'root', '2023-11-15 10:34:46', 'root', '2023-11-16 07:38:19', 0);
INSERT INTO `tbl_warehouse_import` VALUES (16, 6, 1, 1, 1, 'KHO 01', 'ROOT', 'IMP202311000015', '2023-11-09 19:01:00', 1, 'DOC0001', '2023-11-09 19:01:00', 'CTN0001', '0123', '0123', '', 'root', '2023-11-15 10:35:02', 'root', '2023-11-15 10:35:02', 0);
INSERT INTO `tbl_warehouse_import` VALUES (17, 6, 1, 1, 1, 'KHO 01', 'ROOT', 'IMP202311000016', '2023-11-09 19:01:00', 1, 'DOC0001', '2023-11-09 19:01:00', 'CTN0001', '0123', '0123', '', 'root', '2023-11-15 10:39:17', 'root', '2023-11-15 10:39:17', 0);
INSERT INTO `tbl_warehouse_import` VALUES (18, 6, 1, 1, 1, 'KHO 01', 'ROOT', 'IMP202311000017', '2023-11-09 19:01:00', 1, 'DOC0001', '2023-11-09 19:01:00', 'CTN0001', '0123', '0123', '', 'root', '2023-11-15 10:43:40', 'root', '2023-11-15 10:43:40', 0);
INSERT INTO `tbl_warehouse_import` VALUES (19, 6, 1, 1, 1, 'KHO 01', 'ROOT', 'IMP202311000018', '2023-11-09 19:01:00', 1, 'DOC0001', '2023-11-09 19:01:00', 'CTN0001', '0123', '0123', '', 'root', '2023-11-15 10:46:36', 'root', '2023-11-15 10:46:36', 0);
INSERT INTO `tbl_warehouse_import` VALUES (20, 6, 1, 1, 1, 'KHO 01', 'ROOT', 'IMP202311000019', '2023-11-09 19:01:00', 1, 'DOC0001', '2023-11-09 19:01:00', 'CTN0001', '0123', '0123', '', 'root', '2023-11-15 10:53:57', 'root', '2023-11-15 10:53:57', 0);
INSERT INTO `tbl_warehouse_import` VALUES (21, 6, 1, 1, 1, 'KHO 01', 'ROOT', 'IMP202311000020', '2023-11-15 00:00:00', 1, 'SCT001', '2023-11-15 00:00:00', 'CTN0001', 'SL0123', 'MDH001', 'note', 'root', '2023-11-15 10:54:46', 'root', '2023-11-15 10:54:46', 0);
INSERT INTO `tbl_warehouse_import` VALUES (22, 6, 1, 1, 1, 'KHO 01', 'ROOT', 'IMP202311000021', '2023-11-15 00:00:00', 1, 'SCT001', '2023-11-15 15:07:40', 'CTN0001', 'SL0123', 'MDH001', 'note', 'root', '2023-11-15 11:00:12', 'root', '2023-11-15 11:00:12', 0);
INSERT INTO `tbl_warehouse_import` VALUES (23, 6, 1, 1, 1, 'KHO 01', 'ROOT', 'IMP202311000022', '2023-11-15 00:00:00', 1, 'SCT001', '2023-11-15 00:00:00', 'CTN0001', 'SL0123', 'MDH001', 'note', 'root', '2023-11-16 07:34:53', 'root', '2023-11-16 07:34:53', 0);
INSERT INTO `tbl_warehouse_import` VALUES (24, 6, 1, 1, 1, 'KHO 01', 'ROOT', 'IMP202311000023', '2023-11-15 00:00:00', 1, 'SCT001', '2023-11-15 00:00:00', 'CTN0001', 'SL0123', 'MDH001', 'note', 'root', '2023-11-16 07:36:12', 'root', '2023-11-16 07:36:12', 0);
INSERT INTO `tbl_warehouse_import` VALUES (25, 7, 1, 1, 1, 'KHO 02', 'ROOT', 'IMP202311000024', '2023-11-18 00:00:00', 1, 'SCT001', '2023-11-18 00:00:00', 'CTN0001', 'SL0123', 'MDH001', 'note', 'root', '2023-11-18 10:19:13', 'root', '2023-11-18 10:19:13', 0);
INSERT INTO `tbl_warehouse_import` VALUES (26, 6, 1, 1, 1, 'KHO 01', 'ROOT', 'IMP202311000025', '2023-11-25 00:00:00', 1, 'SCT001', '2023-11-25 00:00:00', 'CTN0001', 'SL0123', 'MDH001', 'note', 'root', '2023-11-25 08:24:02', 'root', '2023-11-25 08:24:11', 0);
INSERT INTO `tbl_warehouse_import` VALUES (27, 6, 1, 1, 1, 'KHO 01', 'ROOT', 'IMP202311000026', '2023-11-09 00:00:00', 0, 'DOC0001', '2023-11-09 00:00:00', 'CTN0001', '0123', '0123', '', 'root', '2023-11-26 10:17:04', 'root', '2023-12-09 13:55:06', 0);
INSERT INTO `tbl_warehouse_import` VALUES (28, 6, 1, 1, 1, 'KHO 01', 'ROOT', 'IMP202312000027', '2023-11-09 00:00:00', 1, 'DOC0001', '2023-11-09 00:00:00', 'CTN0001', '0123', '0123', '', 'root', '2023-12-08 03:38:46', 'root', '2023-12-08 08:19:13', 0);
INSERT INTO `tbl_warehouse_import` VALUES (30, 6, 1, 1, 1, 'KHO 01', 'ROOT', 'IMP202312000028', '2023-12-08 00:00:00', 1, NULL, NULL, NULL, NULL, NULL, NULL, 'root', '2023-12-08 04:47:34', 'root', '2023-12-08 04:47:34', 0);
INSERT INTO `tbl_warehouse_import` VALUES (31, 7, 3, NULL, 1, 'KHO 02', 'Manh Hung 2', 'IMP202312000029', '2023-12-11 00:00:00', 1, 'SCT001', '2023-12-11 00:00:00', NULL, NULL, NULL, 'note 202312111040', 'nhanvien02', '2023-12-11 21:45:38', NULL, NULL, 1);
INSERT INTO `tbl_warehouse_import` VALUES (32, 7, 3, NULL, 1, 'KHO 02', 'Manh Hung 2', 'IMP202312000030', '2023-12-11 00:00:00', 0, NULL, NULL, NULL, NULL, NULL, NULL, 'nhanvien02', '2023-12-11 22:05:22', 'nhanvien02', '2023-12-11 22:35:35', 1);
INSERT INTO `tbl_warehouse_import` VALUES (33, 6, 1, 2, 1, 'KHO 01', 'ROOT', 'IMP202312000031', '2023-12-12 00:00:00', 1, 'SCT002', '2023-12-12 00:00:00', NULL, NULL, NULL, NULL, 'root', '2023-12-12 08:23:08', NULL, NULL, 0);

-- ----------------------------
-- Table structure for tbl_warehouse_import_file_connect
-- ----------------------------
DROP TABLE IF EXISTS `tbl_warehouse_import_file_connect`;
CREATE TABLE `tbl_warehouse_import_file_connect`  (
  `file_id` bigint UNSIGNED NOT NULL,
  `wh_import_id` bigint UNSIGNED NOT NULL,
  PRIMARY KEY (`file_id`, `wh_import_id`) USING BTREE,
  UNIQUE INDEX `UK_d9tdkwgkll9d08h26nni1eqg9`(`file_id` ASC) USING BTREE,
  INDEX `tbl_wh_import_file_connect_file_id`(`file_id` ASC) USING BTREE,
  INDEX `tbl_wh_import_file_connect_wh_import_id`(`wh_import_id` ASC) USING BTREE,
  CONSTRAINT `tbl_wh_import_file_connect_file_id` FOREIGN KEY (`file_id`) REFERENCES `tbl_file_description` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tbl_wh_import_file_connect_wh_import_id` FOREIGN KEY (`wh_import_id`) REFERENCES `tbl_warehouse_import` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_warehouse_import_file_connect
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_warehouse_import_item
-- ----------------------------
DROP TABLE IF EXISTS `tbl_warehouse_import_item`;
CREATE TABLE `tbl_warehouse_import_item`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `parent_id` bigint UNSIGNED NOT NULL,
  `product_id` bigint UNSIGNED NOT NULL,
  `unit_target_id` bigint UNSIGNED NOT NULL,
  `unit_source_id` bigint UNSIGNED NOT NULL,
  `product_detail_id` bigint UNSIGNED NULL DEFAULT NULL,
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `consignment_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `quantity_target` double NOT NULL,
  `quantity_source` double NOT NULL,
  `price` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_tbl_wh_import_item_product_id`(`product_id` ASC) USING BTREE,
  INDEX `FK_tbl_wh_import_item_unit_target_id`(`unit_target_id` ASC) USING BTREE,
  INDEX `FK_tbl_wh_import_item_unit_source_id`(`unit_source_id` ASC) USING BTREE,
  INDEX `FK_tbl_wh_import_item_parent_id`(`parent_id` ASC) USING BTREE,
  INDEX `FK_tbl_wh_import_item_product_detail_id`(`product_detail_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_wh_import_item_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `tbl_warehouse_import` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_wh_import_item_product_detail_id` FOREIGN KEY (`product_detail_id`) REFERENCES `tbl_product_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_wh_import_item_product_id` FOREIGN KEY (`product_id`) REFERENCES `tbl_product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_wh_import_item_unit_source_id` FOREIGN KEY (`unit_source_id`) REFERENCES `tbl_product_unit_connect` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_wh_import_item_unit_target_id` FOREIGN KEY (`unit_target_id`) REFERENCES `tbl_product_unit_connect` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_warehouse_import_item
-- ----------------------------
INSERT INTO `tbl_warehouse_import_item` VALUES (13, 2, 1, 1, 1, 1, 'Hàng hóa 1', '', 10, 10, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (14, 3, 1, 2, 1, 1, 'Hàng hóa 1', '', 2, 0.2, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (15, 4, 1, 5, 1, 1, 'Hàng hóa 1', 'L02', 3, 1.5, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (16, 5, 1, 5, 1, 1, 'Hàng hóa 1', '', 2, 1, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (17, 6, 1, 5, 1, 1, 'Hàng hóa 1', '', 2, 1, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (18, 7, 1, 5, 1, 1, 'Hàng hóa 1', 'L01', 2, 1, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (19, 8, 1, 5, 1, 1, 'Hàng hóa 1', 'L01', 2, 1, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (20, 9, 1, 5, 1, 1, 'Hàng hóa 1', 'L01', 2, 1, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (21, 10, 1, 5, 1, 1, 'Hàng hóa 1', 'L01', 2, 1, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (22, 11, 1, 5, 1, 1, 'Hàng hóa 1', 'L01', 2, 1, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (23, 12, 1, 5, 1, 1, 'Hàng hóa 1', 'L01', 2, 1, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (24, 13, 1, 5, 1, 1, 'Hàng hóa 1', 'L01', 2, 1, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (25, 14, 1, 5, 1, 1, 'Hàng hóa 1', 'L01', 2, 1, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (27, 16, 1, 1, 1, 1, 'Hàng hóa 1', '', 10, 10, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (28, 17, 1, 1, 1, 1, 'Hàng hóa 1', '', 10, 10, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (29, 18, 1, 1, 1, 1, 'Hàng hóa 1', '', 10, 10, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (30, 19, 1, 1, 1, 1, 'Hàng hóa 1', '', 10, 10, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (31, 20, 1, 1, 1, 1, 'Hàng hóa 1', '', 10, 10, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (32, 21, 1, 5, 1, 1, 'Hàng hóa 1', 'L01', 2, 1, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (33, 22, 1, 2, 1, 1, 'Hàng hóa 1', '', 3, 0.3, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (34, 23, 1, 2, 1, 1, 'Hàng hóa 1', 'L02', 3, 0.3, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (35, 24, 1, 2, 1, 1, 'Hàng hóa 1', 'L02', 3, 0.3, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (36, 15, 1, 1, 1, 1, 'Hàng hóa 1', 'L02', 10, 10, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (37, 25, 1, 1, 1, 1, 'Hàng hóa 1', '', 2, 2, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (38, 25, 1, 2, 1, 1, 'Hàng hóa 1', '', 3, 0.3, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (40, 26, 1, 2, 1, 1, 'Hàng hóa 1', 'L03', 3, 0.3, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (45, 30, 1, 5, 1, 1, 'Hàng hóa 1', '', 2, 1, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (46, 28, 1, 1, 1, 1, 'Hàng hóa 1', '', 10, 10, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (47, 27, 1, 1, 1, 1, 'Hàng hóa 1', '', 10, 10, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (48, 31, 1, 2, 1, 1, 'Hàng hóa 1', 'L0001', 10, 1, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (50, 32, 1, 1, 1, 1, 'Hàng hóa 1', '', 2, 2, 223);
INSERT INTO `tbl_warehouse_import_item` VALUES (51, 33, 5, 8, 8, 5, 'Đậu xanh', '', 100, 100, 30);

-- ----------------------------
-- Table structure for tbl_warehouse_import_item_detail
-- ----------------------------
DROP TABLE IF EXISTS `tbl_warehouse_import_item_detail`;
CREATE TABLE `tbl_warehouse_import_item_detail`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `parent_id` bigint UNSIGNED NOT NULL,
  `floor_id` bigint UNSIGNED NOT NULL,
  `floor_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `quantity` double NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_tbl_wh_import_item_detail_parent_id`(`parent_id` ASC) USING BTREE,
  INDEX `FK_tbl_wh_import_item_detail_floor_id`(`floor_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_wh_import_item_detail_floor_id` FOREIGN KEY (`floor_id`) REFERENCES `tbl_warehouse_floor` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_wh_import_item_detail_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `tbl_warehouse_import_item` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 78 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_warehouse_import_item_detail
-- ----------------------------
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (13, 13, 267, 'A-1-0', 10);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (14, 14, 289, 'B-0-0', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (15, 14, 292, 'B-1-1', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (16, 15, 301, 'A-0-0', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (17, 15, 303, 'A-1-0', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (18, 15, 305, 'A-2-0', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (19, 16, 301, 'A-0-0', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (20, 16, 303, 'A-1-0', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (21, 17, 301, 'A-0-0', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (22, 17, 303, 'A-1-0', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (23, 18, 301, 'A-0-0', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (24, 18, 303, 'A-1-0', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (25, 19, 302, 'A-0-1', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (26, 19, 304, 'A-1-1', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (27, 20, 302, 'A-0-1', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (28, 20, 304, 'A-1-1', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (29, 21, 302, 'A-0-1', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (30, 21, 304, 'A-1-1', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (31, 22, 302, 'A-0-1', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (32, 22, 304, 'A-1-1', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (33, 23, 302, 'A-0-1', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (34, 23, 304, 'A-1-1', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (35, 24, 302, 'A-0-1', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (36, 24, 304, 'A-1-1', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (37, 25, 302, 'A-0-1', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (38, 25, 304, 'A-1-1', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (40, 27, 267, 'A-1-0', 10);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (41, 28, 267, 'A-1-0', 10);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (42, 29, 267, 'A-1-0', 10);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (43, 30, 267, 'A-1-0', 10);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (44, 31, 267, 'A-1-0', 10);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (45, 32, 302, 'A-0-1', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (46, 32, 304, 'A-1-1', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (47, 33, 312, 'B-2-1', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (48, 33, 303, 'A-1-0', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (49, 33, 301, 'A-0-0', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (50, 34, 312, 'B-2-1', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (51, 34, 303, 'A-1-0', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (52, 34, 301, 'A-0-0', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (53, 35, 312, 'B-2-1', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (54, 35, 303, 'A-1-0', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (55, 35, 301, 'A-0-0', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (56, 36, 267, 'A-1-0', 10);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (57, 37, 327, 'A-0-0', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (58, 37, 329, 'A-1-0', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (59, 38, 337, 'B-0-0', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (60, 38, 339, 'B-1-0', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (61, 38, 341, 'B-2-0', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (64, 40, 308, 'B-0-1', 2);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (65, 40, 267, 'A-1-0', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (70, 45, 316, 'C-0-1', 2);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (71, 46, 267, 'A-1-0', 10);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (72, 47, 267, 'A-1-0', 10);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (73, 48, 346, 'B-4-1', 10);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (76, 50, 328, 'A-0-1', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (77, 50, 338, 'B-0-1', 1);
INSERT INTO `tbl_warehouse_import_item_detail` VALUES (78, 51, 272, 'A-3-1', 100);

-- ----------------------------
-- Table structure for tbl_warehouse_inventory
-- ----------------------------
DROP TABLE IF EXISTS `tbl_warehouse_inventory`;
CREATE TABLE `tbl_warehouse_inventory`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `warehouse_id` bigint UNSIGNED NOT NULL,
  `wh_import_id` bigint UNSIGNED NULL DEFAULT NULL,
  `wh_export_id` bigint UNSIGNED NULL DEFAULT NULL,
  `wh_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `wh_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `product_type_id` bigint UNSIGNED NOT NULL,
  `status` int NOT NULL,
  `input_date` datetime NOT NULL,
  `note` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `modify_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_tbl_warehouse_inventory_wh_import_id`(`wh_import_id` ASC) USING BTREE,
  INDEX `FK_tbl_warehouse_inventory_wh_export_id`(`wh_export_id` ASC) USING BTREE,
  INDEX `FK_tbl_warehouse_inventory_warehouse_id`(`warehouse_id` ASC) USING BTREE,
  INDEX `FK_tbl_warehouse_inventory_product_type_id`(`product_type_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_warehouse_inventory_product_type_id` FOREIGN KEY (`product_type_id`) REFERENCES `tbl_product_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_warehouse_inventory_warehouse_id` FOREIGN KEY (`warehouse_id`) REFERENCES `tbl_warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_warehouse_inventory_wh_export_id` FOREIGN KEY (`wh_export_id`) REFERENCES `tbl_warehouse_export` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_warehouse_inventory_wh_import_id` FOREIGN KEY (`wh_import_id`) REFERENCES `tbl_warehouse_import` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_warehouse_inventory
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_warehouse_inventory_employee_connect
-- ----------------------------
DROP TABLE IF EXISTS `tbl_warehouse_inventory_employee_connect`;
CREATE TABLE `tbl_warehouse_inventory_employee_connect`  (
  `employee_id` bigint UNSIGNED NOT NULL,
  `wh_inventory_id` bigint UNSIGNED NOT NULL,
  PRIMARY KEY (`employee_id`, `wh_inventory_id`) USING BTREE,
  INDEX `tbl_wh_inventory_employee_connect_employee_id`(`employee_id` ASC) USING BTREE,
  INDEX `tbl_wh_inventory_employee_connect_wh_import_id`(`wh_inventory_id` ASC) USING BTREE,
  CONSTRAINT `tbl_wh_inventory_employee_connect_employee_id` FOREIGN KEY (`employee_id`) REFERENCES `tbl_employee` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tbl_wh_inventory_employee_connect_wh_import_id` FOREIGN KEY (`wh_inventory_id`) REFERENCES `tbl_warehouse_inventory` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_warehouse_inventory_employee_connect
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_warehouse_inventory_file_connect
-- ----------------------------
DROP TABLE IF EXISTS `tbl_warehouse_inventory_file_connect`;
CREATE TABLE `tbl_warehouse_inventory_file_connect`  (
  `file_id` bigint UNSIGNED NOT NULL,
  `wh_inventory_id` bigint UNSIGNED NOT NULL,
  PRIMARY KEY (`file_id`, `wh_inventory_id`) USING BTREE,
  UNIQUE INDEX `UK_8fuonmslt1vk88ggj6r9pe97x`(`file_id` ASC) USING BTREE,
  INDEX `tbl_wh_inventory_file_connect_file_id`(`file_id` ASC) USING BTREE,
  INDEX `tbl_wh_inventory_file_connect_wh_import_id`(`wh_inventory_id` ASC) USING BTREE,
  CONSTRAINT `tbl_wh_inventory_file_connect_file_id` FOREIGN KEY (`file_id`) REFERENCES `tbl_file_description` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tbl_wh_inventory_file_connect_wh_import_id` FOREIGN KEY (`wh_inventory_id`) REFERENCES `tbl_warehouse_inventory` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_warehouse_inventory_file_connect
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_warehouse_inventory_item
-- ----------------------------
DROP TABLE IF EXISTS `tbl_warehouse_inventory_item`;
CREATE TABLE `tbl_warehouse_inventory_item`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `parent_id` bigint UNSIGNED NOT NULL,
  `product_id` bigint UNSIGNED NOT NULL,
  `unit_check_id` bigint UNSIGNED NOT NULL,
  `product_detail_id` bigint UNSIGNED NULL DEFAULT NULL,
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `consignment_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `quantity_real` double NOT NULL,
  `quantity_fake` double NOT NULL,
  `price` double NULL DEFAULT NULL,
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `modify_date` datetime NULL DEFAULT NULL,
  `input_date` datetime NULL DEFAULT NULL,
  `floor_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_tbl_wh_inventory_item_product_id`(`product_id` ASC) USING BTREE,
  INDEX `FK_tbl_wh_inventory_item_unit_check_id`(`unit_check_id` ASC) USING BTREE,
  INDEX `FK_tbl_wh_inventory_item_parent_id`(`parent_id` ASC) USING BTREE,
  INDEX `FK_tbl_wh_inventory_item_product_detail_id`(`product_detail_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_wh_inventory_item_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `tbl_warehouse_inventory` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_wh_inventory_item_product_detail_id` FOREIGN KEY (`product_detail_id`) REFERENCES `tbl_product_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_wh_inventory_item_product_id` FOREIGN KEY (`product_id`) REFERENCES `tbl_product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_wh_inventory_item_unit_check_id` FOREIGN KEY (`unit_check_id`) REFERENCES `tbl_product_unit_connect` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_warehouse_inventory_item
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_warehouse_location
-- ----------------------------
DROP TABLE IF EXISTS `tbl_warehouse_location`;
CREATE TABLE `tbl_warehouse_location`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `modify_date` datetime NULL DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `map_point` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `warehouse_area_id` bigint UNSIGNED NOT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IND_tbl_warehouse_location_deleted`(`deleted` ASC) USING BTREE,
  INDEX `FK_tbl_warehouse_location_tbl_warehouse_area_warehouse_area_id`(`warehouse_area_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_warehouse_location_tbl_warehouse_area_warehouse_area_id` FOREIGN KEY (`warehouse_area_id`) REFERENCES `tbl_warehouse_area` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 201 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_warehouse_location
-- ----------------------------
INSERT INTO `tbl_warehouse_location` VALUES (134, 'admin', '2023-11-07 20:27:25', NULL, '2023-11-07 20:27:25', 'A-1', 'WA002/AR000/LO001', '0.1', 11, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (135, 'admin', '2023-11-07 20:27:25', NULL, '2023-11-07 20:27:25', 'A-2', 'WA002/AR000/LO002', '0.2', 11, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (136, 'admin', '2023-11-07 20:27:25', NULL, '2023-11-07 20:27:25', 'A-3', 'WA002/AR000/LO003', '0.3', 11, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (137, 'admin', '2023-11-07 20:27:25', NULL, '2023-11-07 20:27:25', 'A-4', 'WA002/AR000/LO004', '0.4', 11, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (138, 'admin', '2023-11-07 20:27:25', NULL, '2023-11-07 20:27:25', 'A-5', 'WA002/AR000/LO005', '0.5', 11, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (145, 'admin', '2023-11-07 20:30:44', NULL, '2023-11-07 20:30:44', 'B-0', 'WA002/AR000/LO000', '2.0', 13, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (146, 'admin', '2023-11-07 20:30:44', NULL, '2023-11-07 20:30:44', 'B-1', 'WA002/AR000/LO001', '2.1', 13, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (147, 'admin', '2023-11-07 20:30:44', NULL, '2023-11-07 20:30:44', 'B-2', 'WA002/AR000/LO002', '2.2', 13, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (148, 'admin', '2023-11-07 20:30:44', NULL, '2023-11-07 20:30:44', 'B-3', 'WA002/AR000/LO003', '2.3', 13, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (149, 'admin', '2023-11-07 20:30:44', NULL, '2023-11-07 20:30:44', 'B-4', 'WA002/AR000/LO004', '2.4', 13, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (150, 'admin', '2023-11-07 20:30:44', NULL, '2023-11-07 20:30:44', 'B-5', 'WA002/AR000/LO005', '2.5', 13, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (151, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'A-0', 'WA002/AR000/LO000', '3.0', 11, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (152, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'A-1', 'WA002/AR000/LO001', '3.1', 11, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (153, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'A-2', 'WA002/AR000/LO002', '3.2', 11, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (154, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'B-0', 'WA002/AR000/LO000', '0.0', 13, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (155, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'B-1', 'WA002/AR000/LO001', '5.0', 13, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (156, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'B-2', 'WA002/AR000/LO002', '5.1', 13, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (157, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'B-3', 'WA002/AR000/LO003', '5.2', 13, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (158, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'C-0', 'WA002/AR000/LO000', '7.5', 14, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (159, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'C-1', 'WA002/AR000/LO001', '7.0', 14, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (160, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'C-2', 'WA002/AR000/LO002', '7.1', 14, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (161, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'C-3', 'WA002/AR000/LO003', '7.2', 14, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (162, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'C-4', 'WA002/AR000/LO004', '7.3', 14, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (163, 'admin', '2023-11-07 20:52:23', NULL, '2023-11-07 20:52:23', 'C-5', 'WA002/AR000/LO005', '7.4', 14, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (164, 'root', '2023-11-18 10:13:58', 'nhanvien02', '2023-12-12 00:23:25', 'A-0', 'WA003/AR000/LO000', '0.0', 15, b'1');
INSERT INTO `tbl_warehouse_location` VALUES (165, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'A-1', 'WA003/AR000/LO001', '0.1', 15, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (166, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'A-2', 'WA003/AR000/LO002', '0.2', 15, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (167, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'A-3', 'WA003/AR000/LO003', '0.3', 15, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (168, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'A-4', 'WA003/AR000/LO004', '0.4', 15, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (169, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'B-0', 'WA003/AR001/LO000', '2.0', 16, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (170, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'B-1', 'WA003/AR001/LO001', '2.1', 16, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (171, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'B-2', 'WA003/AR001/LO002', '2.2', 16, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (172, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'B-3', 'WA003/AR001/LO003', '2.3', 16, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (173, 'root', '2023-11-18 10:13:58', 'root', '2023-11-18 10:13:58', 'B-4', 'WA003/AR001/LO004', '2.4', 16, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (192, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'A-7', 'WA004/AR000/LO007', '0.0', 17, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (193, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'A-8', 'WA004/AR000/LO008', '0.1', 17, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (194, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'A-9', 'WA004/AR000/LO009', '0.2', 17, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (195, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'A-10', 'WA004/AR000/LO010', '0.3', 17, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (196, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'A-11', 'WA004/AR000/LO011', '0.4', 17, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (197, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'B-7', 'WA004/AR001/LO007', '3.0', 18, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (198, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'B-8', 'WA004/AR001/LO008', '3.1', 18, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (199, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'B-9', 'WA004/AR001/LO009', '3.2', 18, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (200, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'B-10', 'WA004/AR001/LO010', '3.3', 18, b'0');
INSERT INTO `tbl_warehouse_location` VALUES (201, 'root', '2023-12-03 17:33:17', 'root', '2023-12-03 17:33:17', 'B-11', 'WA004/AR001/LO011', '3.4', 18, b'0');

-- ----------------------------
-- Table structure for tbl_warehouse_product_type_connect
-- ----------------------------
DROP TABLE IF EXISTS `tbl_warehouse_product_type_connect`;
CREATE TABLE `tbl_warehouse_product_type_connect`  (
  `warehouse_id` bigint UNSIGNED NOT NULL,
  `type_id` bigint UNSIGNED NOT NULL,
  PRIMARY KEY (`warehouse_id`, `type_id`) USING BTREE,
  INDEX `FK_tbl_wh_product_type_connect_warehouse_id`(`warehouse_id` ASC) USING BTREE,
  INDEX `FK_tbl_wh_product_type_connect_type_id`(`type_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_wh_product_type_connect_type_id` FOREIGN KEY (`type_id`) REFERENCES `tbl_product_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_wh_product_type_connect_warehouse_id` FOREIGN KEY (`warehouse_id`) REFERENCES `tbl_warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_warehouse_product_type_connect
-- ----------------------------
INSERT INTO `tbl_warehouse_product_type_connect` VALUES (6, 1);
INSERT INTO `tbl_warehouse_product_type_connect` VALUES (6, 2);
INSERT INTO `tbl_warehouse_product_type_connect` VALUES (6, 3);
INSERT INTO `tbl_warehouse_product_type_connect` VALUES (7, 1);
INSERT INTO `tbl_warehouse_product_type_connect` VALUES (7, 2);
INSERT INTO `tbl_warehouse_product_type_connect` VALUES (7, 3);
INSERT INTO `tbl_warehouse_product_type_connect` VALUES (8, 1);
INSERT INTO `tbl_warehouse_product_type_connect` VALUES (8, 2);
INSERT INTO `tbl_warehouse_product_type_connect` VALUES (8, 3);

-- ----------------------------
-- Table structure for tbl_warehouse_structure
-- ----------------------------
DROP TABLE IF EXISTS `tbl_warehouse_structure`;
CREATE TABLE `tbl_warehouse_structure`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `warehouse_id` bigint UNSIGNED NOT NULL,
  `row_num` int NULL DEFAULT NULL,
  `column_num` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_tbl_warehouse_structure_warehouse_id`(`warehouse_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_warehouse_structure_warehouse_id` FOREIGN KEY (`warehouse_id`) REFERENCES `tbl_warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_warehouse_structure
-- ----------------------------
INSERT INTO `tbl_warehouse_structure` VALUES (1, 'root', '2023-09-07 11:59:22', 6, 20, 20);
INSERT INTO `tbl_warehouse_structure` VALUES (2, 'root', '2023-09-07 12:00:00', 7, 20, 20);
INSERT INTO `tbl_warehouse_structure` VALUES (3, 'root', '2023-10-07 12:00:27', 8, 20, 20);
INSERT INTO `tbl_warehouse_structure` VALUES (14, 'nhanvien02', '2023-12-12 00:10:54', 7, 20, 20);
INSERT INTO `tbl_warehouse_structure` VALUES (15, 'nhanvien02', '2023-12-12 00:12:36', 7, 20, 20);
INSERT INTO `tbl_warehouse_structure` VALUES (16, 'nhanvien02', '2023-12-12 00:20:37', 7, 20, 20);
INSERT INTO `tbl_warehouse_structure` VALUES (17, 'nhanvien02', '2023-12-12 00:23:25', 7, 20, 20);

-- ----------------------------
-- Table structure for tbl_warehouse_transfer
-- ----------------------------
DROP TABLE IF EXISTS `tbl_warehouse_transfer`;
CREATE TABLE `tbl_warehouse_transfer`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `wh_import_id` bigint UNSIGNED NOT NULL,
  `wh_export_id` bigint UNSIGNED NOT NULL,
  `employee_id` bigint UNSIGNED NOT NULL,
  `wh_import_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `wh_export_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `employee_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `product_type_id` bigint UNSIGNED NOT NULL,
  `status` int NOT NULL,
  `input_date` datetime NOT NULL,
  `document_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `document_date` datetime NULL DEFAULT NULL,
  `note` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `modify_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_tbl_warehouse_transfer_wh_import_id`(`wh_import_id` ASC) USING BTREE,
  INDEX `FK_tbl_warehouse_transfer_wh_export_id`(`wh_export_id` ASC) USING BTREE,
  INDEX `FK_tbl_warehouse_transfer_employee_id`(`employee_id` ASC) USING BTREE,
  INDEX `FK_tbl_warehouse_transfer_product_type_id`(`product_type_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_warehouse_transfer_employee_id` FOREIGN KEY (`employee_id`) REFERENCES `tbl_employee` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_warehouse_transfer_product_type_id` FOREIGN KEY (`product_type_id`) REFERENCES `tbl_product_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_warehouse_transfer_wh_export_id` FOREIGN KEY (`wh_export_id`) REFERENCES `tbl_warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_warehouse_transfer_wh_import_id` FOREIGN KEY (`wh_import_id`) REFERENCES `tbl_warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_warehouse_transfer
-- ----------------------------
INSERT INTO `tbl_warehouse_transfer` VALUES (2, 6, 6, 1, 'KHO 01', 'KHO 01', 'ROOT', 'TRA202311000002', 1, 1, '2023-11-09 19:01:00', 'DOC0001', '2023-11-09 19:01:00', '', 'root', '2023-11-22 22:04:49', 'root', '2023-11-23 06:25:20');
INSERT INTO `tbl_warehouse_transfer` VALUES (3, 6, 6, 1, 'KHO 01', 'KHO 01', 'ROOT', 'TRA202311000003', 1, 1, '2023-11-09 19:01:00', 'DOC0001', '2023-11-09 19:01:00', '', 'root', '2023-11-23 06:31:32', 'root', '2023-11-23 06:31:32');
INSERT INTO `tbl_warehouse_transfer` VALUES (4, 6, 6, 1, 'KHO 01', 'KHO 01', 'ROOT', 'TRA202311000004', 1, 1, '2023-11-09 19:01:00', 'DOC0001', '2023-11-09 19:01:00', '', 'root', '2023-11-23 06:32:46', 'root', '2023-11-23 06:32:46');
INSERT INTO `tbl_warehouse_transfer` VALUES (5, 6, 6, 1, 'KHO 01', 'KHO 01', 'ROOT', 'TRA202311000005', 1, 1, '2023-11-09 19:01:00', 'DOC0001', '2023-11-09 19:01:00', '', 'root', '2023-11-23 06:51:15', 'root', '2023-11-23 06:51:15');
INSERT INTO `tbl_warehouse_transfer` VALUES (6, 6, 6, 1, 'KHO 01', 'KHO 01', 'ROOT', 'TRA202311000006', 1, 1, '2023-11-09 00:00:00', 'DOC0001', '2023-11-09 00:00:00', '', 'root', '2023-11-23 06:51:37', 'root', '2023-11-23 18:11:10');
INSERT INTO `tbl_warehouse_transfer` VALUES (7, 6, 6, 1, 'KHO 01', 'KHO 01', 'ROOT', 'TRA202311000007', 1, 0, '2023-11-23 00:00:00', 'SCT001', '2023-11-23 00:00:00', 'note', 'root', '2023-11-23 16:31:01', 'root', '2023-11-23 16:31:01');
INSERT INTO `tbl_warehouse_transfer` VALUES (8, 6, 6, 1, 'KHO 01', 'KHO 01', 'ROOT', 'TRA202312000008', 1, 0, '2023-12-08 00:00:00', NULL, NULL, NULL, 'root', '2023-12-08 09:13:19', 'root', '2023-12-08 09:13:19');
INSERT INTO `tbl_warehouse_transfer` VALUES (9, 8, 6, 1, 'WH 04', 'KHO 01', 'ROOT', 'TRA202312000009', 1, 1, '2023-12-12 00:00:00', 'SCT003', '2023-12-12 00:00:00', NULL, 'root', '2023-12-12 08:25:03', NULL, NULL);
INSERT INTO `tbl_warehouse_transfer` VALUES (10, 6, 6, 1, 'KHO 01', 'KHO 01', 'ROOT', 'TRA202312000010', 1, 1, '2023-12-12 00:00:00', 'SCT005', '2023-12-12 00:00:00', NULL, 'root', '2023-12-12 08:28:13', NULL, NULL);

-- ----------------------------
-- Table structure for tbl_warehouse_transfer_file_connect
-- ----------------------------
DROP TABLE IF EXISTS `tbl_warehouse_transfer_file_connect`;
CREATE TABLE `tbl_warehouse_transfer_file_connect`  (
  `file_id` bigint UNSIGNED NOT NULL,
  `wh_transfer_id` bigint UNSIGNED NOT NULL,
  PRIMARY KEY (`file_id`, `wh_transfer_id`) USING BTREE,
  UNIQUE INDEX `UK_2iholm2avpibqtd37x6dud3h1`(`file_id` ASC) USING BTREE,
  INDEX `tbl_wh_transfer_file_connect_file_id`(`file_id` ASC) USING BTREE,
  INDEX `tbl_wh_transfer_file_connect_wh_import_id`(`wh_transfer_id` ASC) USING BTREE,
  CONSTRAINT `FKer9q1wbv8stkmdesfdgg3o9kg` FOREIGN KEY (`wh_transfer_id`) REFERENCES `tbl_warehouse_import` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tbl_wh_transfer_file_connect_file_id` FOREIGN KEY (`file_id`) REFERENCES `tbl_file_description` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tbl_wh_transfer_file_connect_wh_import_id` FOREIGN KEY (`wh_transfer_id`) REFERENCES `tbl_warehouse_transfer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_warehouse_transfer_file_connect
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_warehouse_transfer_item
-- ----------------------------
DROP TABLE IF EXISTS `tbl_warehouse_transfer_item`;
CREATE TABLE `tbl_warehouse_transfer_item`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `parent_id` bigint UNSIGNED NOT NULL,
  `product_id` bigint UNSIGNED NOT NULL,
  `unit_target_id` bigint UNSIGNED NOT NULL,
  `unit_source_id` bigint UNSIGNED NOT NULL,
  `product_detail_id` bigint UNSIGNED NULL DEFAULT NULL,
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `consignment_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `quantity_target` double NOT NULL,
  `price` double NULL DEFAULT NULL,
  `quantity_source` double NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_tbl_wh_transfer_item_product_id`(`product_id` ASC) USING BTREE,
  INDEX `FK_tbl_wh_transfer_item_unit_target_id`(`unit_target_id` ASC) USING BTREE,
  INDEX `FK_tbl_wh_transfer_item_unit_source_id`(`unit_source_id` ASC) USING BTREE,
  INDEX `FK_tbl_wh_transfer_item_parent_id`(`parent_id` ASC) USING BTREE,
  INDEX `FK_tbl_wh_transfer_item_product_detail_id`(`product_detail_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_wh_transfer_item_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `tbl_warehouse_transfer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_wh_transfer_item_product_detail_id` FOREIGN KEY (`product_detail_id`) REFERENCES `tbl_product_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_wh_transfer_item_product_id` FOREIGN KEY (`product_id`) REFERENCES `tbl_product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_wh_transfer_item_unit_source_id` FOREIGN KEY (`unit_source_id`) REFERENCES `tbl_product_unit_connect` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_wh_transfer_item_unit_target_id` FOREIGN KEY (`unit_target_id`) REFERENCES `tbl_product_unit_connect` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_warehouse_transfer_item
-- ----------------------------
INSERT INTO `tbl_warehouse_transfer_item` VALUES (2, 2, 1, 1, 1, 1, 'Hàng hóa 1', '', 1, 223, 1);
INSERT INTO `tbl_warehouse_transfer_item` VALUES (3, 2, 1, 1, 1, 1, 'Hàng hóa 1', '', 1, 223, 1);
INSERT INTO `tbl_warehouse_transfer_item` VALUES (4, 2, 1, 1, 1, 1, 'Hàng hóa 1', '', 1, 223, 1);
INSERT INTO `tbl_warehouse_transfer_item` VALUES (5, 2, 1, 1, 1, 1, 'Hàng hóa 1', '', 1, 223, 1);
INSERT INTO `tbl_warehouse_transfer_item` VALUES (6, 3, 1, 1, 1, 1, 'Hàng hóa 1', '', 1, 223, 1);
INSERT INTO `tbl_warehouse_transfer_item` VALUES (7, 4, 1, 1, 1, 1, 'Hàng hóa 1', '', 1, 223, 1);
INSERT INTO `tbl_warehouse_transfer_item` VALUES (8, 5, 1, 1, 1, 1, 'Hàng hóa 1', '', 1, 223, 1);
INSERT INTO `tbl_warehouse_transfer_item` VALUES (11, 7, 1, 5, 1, 1, 'Hàng hóa 1', 'L01', 1, 223, 0.5);
INSERT INTO `tbl_warehouse_transfer_item` VALUES (14, 6, 1, 1, 1, 1, 'Hàng hóa 1', '', 1, 223, 1);
INSERT INTO `tbl_warehouse_transfer_item` VALUES (15, 8, 1, 2, 1, 1, 'Hàng hóa 1', 'L02', 1, 223, 0.1);
INSERT INTO `tbl_warehouse_transfer_item` VALUES (16, 9, 5, 8, 8, 5, 'Đậu xanh', '', 10, 30, 10);
INSERT INTO `tbl_warehouse_transfer_item` VALUES (17, 10, 5, 8, 8, 5, 'Đậu xanh', '', 10, 30, 10);

-- ----------------------------
-- Table structure for tbl_warehouse_transfer_item_detail
-- ----------------------------
DROP TABLE IF EXISTS `tbl_warehouse_transfer_item_detail`;
CREATE TABLE `tbl_warehouse_transfer_item_detail`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `parent_id` bigint UNSIGNED NOT NULL,
  `floor_id` bigint UNSIGNED NOT NULL,
  `floor_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `quantity` double NOT NULL,
  `input_date` datetime NOT NULL,
  `type` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_tbl_wh_transfer_item_detail_parent_id`(`parent_id` ASC) USING BTREE,
  INDEX `FK_tbl_wh_transfer_item_detail_floor_id`(`floor_id` ASC) USING BTREE,
  CONSTRAINT `FK_tbl_wh_transfer_item_detail_floor_id` FOREIGN KEY (`floor_id`) REFERENCES `tbl_warehouse_floor` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_wh_transfer_item_detail_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `tbl_warehouse_transfer_item` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_warehouse_transfer_item_detail
-- ----------------------------
INSERT INTO `tbl_warehouse_transfer_item_detail` VALUES (1, 2, 267, 'A-1-0', 1, '2023-11-09 19:01:00', 0);
INSERT INTO `tbl_warehouse_transfer_item_detail` VALUES (2, 2, 268, 'A-1-1', 1, '2023-11-09 19:01:00', 1);
INSERT INTO `tbl_warehouse_transfer_item_detail` VALUES (3, 3, 267, 'A-1-0', 1, '2023-11-09 19:01:00', 0);
INSERT INTO `tbl_warehouse_transfer_item_detail` VALUES (4, 3, 268, 'A-1-1', 1, '2023-11-09 19:01:00', 1);
INSERT INTO `tbl_warehouse_transfer_item_detail` VALUES (5, 4, 267, 'A-1-0', 1, '2023-11-09 19:01:00', 0);
INSERT INTO `tbl_warehouse_transfer_item_detail` VALUES (6, 4, 268, 'A-1-1', 1, '2023-11-09 19:01:00', 1);
INSERT INTO `tbl_warehouse_transfer_item_detail` VALUES (7, 5, 267, 'A-1-0', 1, '2023-11-09 19:01:00', 0);
INSERT INTO `tbl_warehouse_transfer_item_detail` VALUES (8, 5, 268, 'A-1-1', 1, '2023-11-09 19:01:00', 1);
INSERT INTO `tbl_warehouse_transfer_item_detail` VALUES (9, 6, 267, 'A-1-0', 1, '2023-11-09 19:01:00', 0);
INSERT INTO `tbl_warehouse_transfer_item_detail` VALUES (10, 6, 268, 'A-1-1', 1, '2023-11-09 19:01:00', 1);
INSERT INTO `tbl_warehouse_transfer_item_detail` VALUES (11, 7, 267, 'A-1-0', 1, '2023-11-09 19:01:00', 0);
INSERT INTO `tbl_warehouse_transfer_item_detail` VALUES (12, 7, 268, 'A-1-1', 1, '2023-11-09 19:01:00', 1);
INSERT INTO `tbl_warehouse_transfer_item_detail` VALUES (13, 8, 267, 'A-1-0', 1, '2023-11-09 19:01:00', 0);
INSERT INTO `tbl_warehouse_transfer_item_detail` VALUES (14, 8, 268, 'A-1-1', 1, '2023-11-09 19:01:00', 1);
INSERT INTO `tbl_warehouse_transfer_item_detail` VALUES (19, 11, 304, 'A-1-1', 1, '2023-11-15 00:00:00', 0);
INSERT INTO `tbl_warehouse_transfer_item_detail` VALUES (20, 11, 316, 'C-0-1', 1, '2023-11-23 00:00:00', 1);
INSERT INTO `tbl_warehouse_transfer_item_detail` VALUES (25, 14, 267, 'A-1-0', 1, '2023-11-09 19:01:00', 0);
INSERT INTO `tbl_warehouse_transfer_item_detail` VALUES (26, 14, 268, 'A-1-1', 1, '2023-11-09 00:00:00', 1);
INSERT INTO `tbl_warehouse_transfer_item_detail` VALUES (27, 15, 301, 'A-0-0', 1, '2023-11-15 00:00:00', 0);
INSERT INTO `tbl_warehouse_transfer_item_detail` VALUES (28, 15, 301, 'A-0-0', 1, '2023-12-08 00:00:00', 1);
INSERT INTO `tbl_warehouse_transfer_item_detail` VALUES (29, 16, 272, 'A-3-1', 10, '2023-12-12 00:00:00', 0);
INSERT INTO `tbl_warehouse_transfer_item_detail` VALUES (30, 16, 402, 'B-11-1', 5, '2023-12-12 00:00:00', 1);
INSERT INTO `tbl_warehouse_transfer_item_detail` VALUES (31, 16, 400, 'B-10-1', 5, '2023-12-12 00:00:00', 1);
INSERT INTO `tbl_warehouse_transfer_item_detail` VALUES (32, 17, 272, 'A-3-1', 10, '2023-12-12 00:00:00', 0);
INSERT INTO `tbl_warehouse_transfer_item_detail` VALUES (33, 17, 297, 'B-4-0', 5, '2023-12-12 00:00:00', 1);
INSERT INTO `tbl_warehouse_transfer_item_detail` VALUES (34, 17, 296, 'B-3-1', 5, '2023-12-12 00:00:00', 1);

SET FOREIGN_KEY_CHECKS = 1;
