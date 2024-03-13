SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `tbl_warehouse`;
CREATE TABLE `tbl_warehouse`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `create_by`   VARCHAR(100)    NOT NULL,
    `create_date` DATETIME        NOT NULL,
    `modify_by`   VARCHAR(100)             DEFAULT NULL,
    `modify_date` DATETIME                 DEFAULT NULL,
    `name`        VARCHAR(100)    NOT NULL,
    `code`        VARCHAR(100)    NOT NULL,
    `address`     VARCHAR(300)             DEFAULT NULL,
    `deleted`     BIT(1)         NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`),
    KEY IND_tbl_warehouse_deleted (deleted),
    CONSTRAINT UC_tbl_warehouse_code UNIQUE (code)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

DROP TABLE IF EXISTS `tbl_warehouse_structure`;
CREATE TABLE `tbl_warehouse_structure`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `create_by`   VARCHAR(100)    NOT NULL,
    `create_date` DATETIME        NOT NULL,
    `warehouse_id` BIGINT UNSIGNED NOT NULL,
    `row_num`     INT(5)                   DEFAULT NULL,
    `column_num`  INT(5)                   default null,
    PRIMARY KEY (`id`),
    CONSTRAINT FK_tbl_warehouse_structure_warehouse_id FOREIGN KEY (warehouse_id) REFERENCES tbl_warehouse (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

DROP TABLE IF EXISTS `tbl_warehouse_area`;
CREATE TABLE `tbl_warehouse_area`
(
    `id`           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `create_by`    VARCHAR(100)    NOT NULL,
    `create_date`  DATETIME        NOT NULL,
    `modify_by`    VARCHAR(100)             DEFAULT NULL,
    `modify_date`  DATETIME                 DEFAULT NULL,
    `name`         VARCHAR(100)    NOT NULL,
    `code`         VARCHAR(100)    NOT NULL,
    `warehouse_id` BIGINT UNSIGNED NOT NULL,
    `deleted`      BIT(1)         NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`),
    KEY IND_tbl_warehouse_area_deleted (deleted),
    CONSTRAINT FK_tbl_warehouse_area_tbl_warehouse_warehouse_id FOREIGN KEY (warehouse_id) REFERENCES tbl_warehouse (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;


DROP TABLE IF EXISTS `tbl_warehouse_location`;
CREATE TABLE `tbl_warehouse_location`
(
    `id`                BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `create_by`         VARCHAR(100)    NOT NULL,
    `create_date`       DATETIME        NOT NULL,
    `modify_by`         VARCHAR(100)             DEFAULT NULL,
    `modify_date`       DATETIME                 DEFAULT NULL,
    `name`              VARCHAR(100)    NOT NULL,
    `code`              VARCHAR(100)    NOT NULL,
    `map_point`         VARCHAR(100)    NOT NULL,
    `warehouse_area_id` BIGINT UNSIGNED NOT NULL,
    `deleted`           BIT(1)         NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`),
    KEY IND_tbl_warehouse_location_deleted (deleted),
    CONSTRAINT FK_tbl_warehouse_location_tbl_warehouse_area_warehouse_area_id FOREIGN KEY (warehouse_area_id) REFERENCES tbl_warehouse_area (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;


DROP TABLE IF EXISTS `tbl_warehouse_floor`;
CREATE TABLE `tbl_warehouse_floor`
(
    `id`                    BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `create_by`             VARCHAR(100)    NOT NULL,
    `create_date`           DATETIME        NOT NULL,
    `modify_by`             VARCHAR(100)             DEFAULT NULL,
    `modify_date`           DATETIME                 DEFAULT NULL,
    `name`                  VARCHAR(100)    NOT NULL,
    `code`                  VARCHAR(100)    NOT NULL,
    `warehouse_location_id` BIGINT UNSIGNED NOT NULL,
    `deleted`               BIT(1)         NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`),
    KEY IND_tbl_tbl_warehouse_floor_deleted (deleted),
    CONSTRAINT FK_tbl_warehouse_floor_tbl_wh_location_warehouse_location_id FOREIGN KEY (warehouse_location_id) REFERENCES tbl_warehouse_location (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

SET FOREIGN_KEY_CHECKS = 1;