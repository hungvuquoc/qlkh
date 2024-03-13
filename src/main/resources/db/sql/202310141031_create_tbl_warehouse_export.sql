SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `tbl_warehouse_export`;
CREATE TABLE `tbl_warehouse_export`
(
    `id`               BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `warehouse_id`     BIGINT UNSIGNED NOT NULL,
    `employee_id`      BIGINT UNSIGNED NOT NULL,
    `product_type_id`  BIGINT UNSIGNED NOT NULL,
    `type`             INT             DEFAULT NULL,
    `warehouse_name`   VARCHAR(255)  DEFAULT NULL,
    `employee_name`    VARCHAR(255)    NOT NULL,
    `code`             VARCHAR(30)     NOT NULL,
    `input_date`       DATETIME        NOT NULL,
    `status`           INT             NOT NULL,
    `document_number`  VARCHAR(100)  DEFAULT NULL,
    `document_date`    DATETIME      DEFAULT NULL,
    `container_number` VARCHAR(100)  DEFAULT NULL,
    `seal_number`      VARCHAR(100)  DEFAULT NULL,
    `quality`          VARCHAR(100)  DEFAULT NULL,
    `order_number`     VARCHAR(100)  DEFAULT NULL,
    `consumer_name`    VARCHAR(255)  DEFAULT NULL,
    `note`             VARCHAR(1000) DEFAULT NULL,
    `create_by`        VARCHAR(100)    NOT NULL,
    `create_date`      DATETIME        NOT NULL,
    `modify_by`        VARCHAR(100)  DEFAULT NULL,
    `modify_date`      DATETIME      DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_tbl_warehouse_export_warehouse_id` (`warehouse_id`),
    KEY `FK_tbl_warehouse_export_employee_id` (`employee_id`),
    KEY `FK_tbl_warehouse_export_product_type_id` (`product_type_id`),
    CONSTRAINT `FK_tbl_warehouse_export_warehouse_id` FOREIGN KEY (`warehouse_id`) REFERENCES `tbl_warehouse` (`id`),
    CONSTRAINT `FK_tbl_warehouse_export_employee_id` FOREIGN KEY (`employee_id`) REFERENCES `tbl_employee` (`id`),
    CONSTRAINT `FK_tbl_warehouse_export_product_type_id` FOREIGN KEY (`product_type_id`) REFERENCES `tbl_product_type` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;


DROP TABLE IF EXISTS `tbl_warehouse_export_item`;
CREATE TABLE `tbl_warehouse_export_item`
(
    `id`                 BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `parent_id`          BIGINT UNSIGNED NOT NULL,
    `product_id`         BIGINT UNSIGNED NOT NULL,
    `unit_target_id`     BIGINT UNSIGNED NOT NULL,
    `unit_source_id`     BIGINT UNSIGNED NOT NULL,
    `product_detail_id`  BIGINT UNSIGNED DEFAULT NULL,
    `product_name`       VARCHAR(255)    NOT NULL,
    `consignment_number` VARCHAR(100)    DEFAULT NULL,
    `quantity_target`    DOUBLE          NOT NULL,
    `price`              DOUBLE          DEFAULT NULL,
    `quantity_source`    DOUBLE          NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_tbl_wh_export_item_product_id` (`product_id`),
    KEY `FK_tbl_wh_export_item_unit_target_id` (`unit_target_id`),
    KEY `FK_tbl_wh_export_item_unit_source_id` (`unit_source_id`),
    KEY `FK_tbl_wh_export_item_parent_id` (`parent_id`),
    KEY `FK_tbl_wh_export_item_product_detail_id` (`product_detail_id`),
    CONSTRAINT `FK_tbl_wh_export_item_product_id` FOREIGN KEY (`product_id`) REFERENCES `tbl_product` (`id`),
    CONSTRAINT `FK_tbl_wh_export_item_unit_target_id` FOREIGN KEY (`unit_target_id`) REFERENCES `tbl_product_unit_connect` (`id`),
    CONSTRAINT `FK_tbl_wh_export_item_unit_source_id` FOREIGN KEY (`unit_source_id`) REFERENCES `tbl_product_unit_connect` (`id`),
    CONSTRAINT `FK_tbl_wh_export_item_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `tbl_warehouse_export` (`id`),
    CONSTRAINT `FK_tbl_wh_export_item_product_detail_id` FOREIGN KEY (`product_detail_id`) REFERENCES `tbl_product_detail` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;


DROP TABLE IF EXISTS `tbl_warehouse_export_item_detail`;
CREATE TABLE `tbl_warehouse_export_item_detail`
(
    `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `parent_id`  BIGINT UNSIGNED NOT NULL,
    `floor_id`   BIGINT UNSIGNED NOT NULL,
    `floor_name` VARCHAR(255)    NOT NULL,
    `input_date` DATETIME        NOT NULL,
    `quantity`   DOUBLE          NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_tbl_wh_export_item_detail_parent_id` (`parent_id`),
    KEY `FK_tbl_wh_export_item_detail_floor_id` (`floor_id`),
    CONSTRAINT `FK_tbl_wh_export_item_detail_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `tbl_warehouse_export_item` (`id`),
    CONSTRAINT `FK_tbl_wh_export_item_detail_floor_id` FOREIGN KEY (`floor_id`) REFERENCES `tbl_warehouse_floor` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;


DROP TABLE IF EXISTS `tbl_warehouse_export_file_connect`;
CREATE TABLE `tbl_warehouse_export_file_connect`
(
    `file_id`      BIGINT UNSIGNED NOT NULL,
    `wh_export_id` BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (`file_id`, `wh_export_id`),
    KEY `tbl_wh_export_file_connect_file_id` (`file_id`),
    KEY `tbl_wh_export_file_connect_wh_import_id` (`wh_export_id`),
    CONSTRAINT `tbl_wh_export_file_connect_file_id` FOREIGN KEY (`file_id`) REFERENCES `tbl_file_description` (`id`),
    CONSTRAINT `tbl_wh_export_file_connect_wh_import_id` FOREIGN KEY (`wh_export_id`) REFERENCES `tbl_warehouse_export` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;


SET FOREIGN_KEY_CHECKS = 1;