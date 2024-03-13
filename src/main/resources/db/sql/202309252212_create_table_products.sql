SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `tbl_product_type`;
CREATE TABLE `tbl_product_type`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `create_by`   VARCHAR(100)    NOT NULL,
    `create_date` DATETIME        NOT NULL,
    `modify_by`   VARCHAR(100) DEFAULT NULL,
    `modify_date` DATETIME     DEFAULT NULL,
    `code`        VARCHAR(30)     NOT NULL,
    `name`        VARCHAR(255)    NOT NULL,
    `deleted`     bit(1)       DEFAULT 0,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UQ_tbl_product_type_code` (`code`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;



DROP TABLE IF EXISTS `tbl_product_group`;
CREATE TABLE `tbl_product_group`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `create_by`   VARCHAR(100)    NOT NULL,
    `create_date` DATETIME        NOT NULL,
    `modify_by`   VARCHAR(100) DEFAULT NULL,
    `modify_date` DATETIME        NOT NULL,
    `code`        VARCHAR(30)     NOT NULL,
    `name`        VARCHAR(255)    NOT NULL,
    `deleted`     bit(1)       DEFAULT 0,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UQ_tbl_product_group` (`code`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;



DROP TABLE IF EXISTS `tbl_product_unit`;
CREATE TABLE `tbl_product_unit`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `create_by`   VARCHAR(100)    NOT NULL,
    `create_date` DATETIME        NOT NULL,
    `modify_by`   VARCHAR(100) DEFAULT NULL,
    `modify_date` DATETIME     DEFAULT NULL,
    `code`        VARCHAR(30)     NOT NULL,
    `name`        VARCHAR(255)    NOT NULL,
    `deleted`     bit(1)       DEFAULT 0,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UQ_tbl_product_unit_code` (`code`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;



DROP TABLE IF EXISTS `tbl_supplier`;
CREATE TABLE `tbl_supplier`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `create_by`   VARCHAR(100)    NOT NULL,
    `create_date` DATETIME        NOT NULL,
    `modify_by`   VARCHAR(100) DEFAULT NULL,
    `modify_date` DATETIME     DEFAULT NULL,
    `code`        VARCHAR(30)     NOT NULL,
    `name`        VARCHAR(255)    NOT NULL,
    `deleted`     bit(1)       DEFAULT 0,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UQ_tbl_supplier_code` (`code`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;



DROP TABLE IF EXISTS `tbl_product`;
CREATE TABLE `tbl_product`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `create_by`   VARCHAR(100)    NOT NULL,
    `create_date` DATETIME        NOT NULL,
    `modify_by`   VARCHAR(100)  DEFAULT NULL,
    `modify_date` DATETIME      DEFAULT NULL,
    `code`        VARCHAR(30)     NOT NULL,
    `name`        VARCHAR(500)    NOT NULL,
    `name_print`  VARCHAR(500)  DEFAULT NULL,
    `note`        VARCHAR(1000) DEFAULT NULL,
    `type_id`     BIGINT UNSIGNED NOT NULL,
    `deleted`     bit(1)        DEFAULT 0,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UQ_tbl_product_code` (`code`) USING BTREE,
    KEY `FK_tbl_product_type_id` (`type_id`),
    CONSTRAINT `FK_tbl_product_type_id` FOREIGN KEY (`type_id`) REFERENCES `tbl_product_type` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;


DROP TABLE IF EXISTS `tbl_product_detail`;
CREATE TABLE `tbl_product_detail`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `create_by`   VARCHAR(100)    NOT NULL,
    `create_date` DATETIME        NOT NULL,
    `modify_by`   VARCHAR(100) DEFAULT NULL,
    `modify_date` DATETIME     DEFAULT NULL,
    `product_id`  BIGINT UNSIGNED NOT NULL,
    `price`       DOUBLE       DEFAULT NULL,
    `quality`     VARCHAR(255) DEFAULT NULL,
    `size`        VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_tbl_product_detail_product_id` (`product_id`),
    CONSTRAINT `FK_tbl_product_detail_product_id` FOREIGN KEY (`product_id`) REFERENCES `tbl_product` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;


DROP TABLE IF EXISTS `tbl_product_group_connect`;
CREATE TABLE `tbl_product_group_connect`
(
    `product_id`  BIGINT UNSIGNED NOT NULL,
    `group_id`    BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (`product_id`, `group_id`),
    KEY `FK_tbl_product_group_connect_product_id` (`product_id`),
    KEY `FK_tbl_product_group_connect_group_id` (`group_id`),
    CONSTRAINT `FK_tbl_product_group_connect_product_id` FOREIGN KEY (`product_id`) REFERENCES `tbl_product` (`id`),
    CONSTRAINT `FK_tbl_product_group_connect_group_id` FOREIGN KEY (`group_id`) REFERENCES `tbl_product_group` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;



DROP TABLE IF EXISTS `tbl_product_unit_connect`;
CREATE TABLE `tbl_product_unit_connect`
(
    `id`            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `create_by`     VARCHAR(100)    NOT NULL,
    `create_date`   DATETIME        NOT NULL,
    `modify_by`     VARCHAR(100) DEFAULT NULL,
    `modify_date`   DATETIME     DEFAULT NULL,
    `product_id`    BIGINT UNSIGNED NOT NULL,
    `unit_id`       BIGINT UNSIGNED NOT NULL,
    `ratio`         DOUBLE       DEFAULT 0.0,
    `is_default`    BIT(1)       DEFAULT 0,
    `is_use_report` BIT(1)       DEFAULT 0,
    `deleted`       BIT(1)       DEFAULT 0,
    PRIMARY KEY (`id`),
    KEY `FK_tbl_product_unit_connect_product_id` (`product_id`),
    KEY `FK_tbl_product_unit_connect_unit_id` (`unit_id`),
    CONSTRAINT `FK_tbl_product_unit_connect_product_id` FOREIGN KEY (`product_id`) REFERENCES `tbl_product` (`id`),
    CONSTRAINT `FK_tbl_product_unit_connect_unit_id` FOREIGN KEY (`unit_id`) REFERENCES `tbl_product_unit` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;



DROP TABLE IF EXISTS `tbl_product_supplier_connect`;
CREATE TABLE `tbl_product_supplier_connect`
(
    `product_id`  BIGINT UNSIGNED NOT NULL,
    `supplier_id` BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (`product_id`, `supplier_id`),
    KEY `FK_tbl_product_supplier_connect_product_id` (`product_id`),
    KEY `FK_tbl_product_supplier_connect_supplier_id` (`supplier_id`),
    CONSTRAINT `FK_tbl_product_supplier_connect_product_id` FOREIGN KEY (`product_id`) REFERENCES `tbl_product` (`id`),
    CONSTRAINT `FK_tbl_product_supplier_connect_supplier_id` FOREIGN KEY (`supplier_id`) REFERENCES `tbl_supplier` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;


DROP TABLE IF EXISTS `tbl_product_file_description_connect`;
CREATE TABLE `tbl_product_file_description_connect`
(
    `product_id`  BIGINT UNSIGNED NOT NULL,
    `file_id`     BIGINT UNSIGNED NOT NULL,
    `order_by`    INT UNSIGNED    NOT NULL,
    PRIMARY KEY (`product_id`, `file_id`),
    KEY `FK_tbl_product_file_description_product_id` (`product_id`),
    KEY `FK_tbl_product_file_description_file_id` (`file_id`),
    CONSTRAINT `FK_tbl_product_file_description_product_id` FOREIGN KEY (`product_id`) REFERENCES `tbl_product` (`id`),
    CONSTRAINT `FK_tbl_product_file_description_file_id` FOREIGN KEY (`file_id`) REFERENCES `tbl_file_description` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;