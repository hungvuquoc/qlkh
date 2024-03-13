SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `tbl_warehouse_inventory`;
CREATE TABLE `tbl_warehouse_inventory`
(
    `id`              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `warehouse_id`    BIGINT UNSIGNED NOT NULL,
    `wh_import_id`    BIGINT UNSIGNED DEFAULT NULL,
    `wh_export_id`    BIGINT UNSIGNED DEFAULT NULL,
    `wh_code`         VARCHAR(30)     NOT NULL,
    `wh_name`         VARCHAR(255)    NOT NULL,
    `code`            VARCHAR(30)     NOT NULL,
    `product_type_id` BIGINT UNSIGNED NOT NULL,
    `status`          INT             NOT NULL,
    `input_date`      DATETIME        NOT NULL,
    `note`            VARCHAR(1000)   DEFAULT NULL,
    `create_by`       VARCHAR(100)    NOT NULL,
    `create_date`     DATETIME        NOT NULL,
    `modify_by`       VARCHAR(100)    DEFAULT NULL,
    `modify_date`     DATETIME        DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_tbl_warehouse_inventory_wh_import_id` (`wh_import_id`),
    KEY `FK_tbl_warehouse_inventory_wh_export_id` (`wh_export_id`),
    KEY `FK_tbl_warehouse_inventory_warehouse_id` (`warehouse_id`),
    KEY `FK_tbl_warehouse_inventory_product_type_id` (`product_type_id`),
    CONSTRAINT `FK_tbl_warehouse_inventory_wh_import_id` FOREIGN KEY (`wh_import_id`) REFERENCES `tbl_warehouse_import` (`id`),
    CONSTRAINT `FK_tbl_warehouse_inventory_wh_export_id` FOREIGN KEY (`wh_export_id`) REFERENCES `tbl_warehouse_export` (`id`),
    CONSTRAINT `FK_tbl_warehouse_inventory_warehouse_id` FOREIGN KEY (`warehouse_id`) REFERENCES `tbl_warehouse` (`id`),
    CONSTRAINT `FK_tbl_warehouse_inventory_product_type_id` FOREIGN KEY (`product_type_id`) REFERENCES `tbl_product_type` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;


DROP TABLE IF EXISTS `tbl_warehouse_inventory_item`;
CREATE TABLE `tbl_warehouse_inventory_item`
(
    `id`                 BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `parent_id`          BIGINT UNSIGNED NOT NULL,
    `product_id`         BIGINT UNSIGNED NOT NULL,
    `unit_check_id`      BIGINT UNSIGNED NOT NULL,
    `product_detail_id`  BIGINT UNSIGNED DEFAULT NULL,
    `floor_id`           BIGINT UNSIGNED DEFAULT NULL,
    `input_date`         DATETIME        NOT NULL,
    `product_name`       VARCHAR(255)    NOT NULL,
    `consignment_number` VARCHAR(100) DEFAULT NULL,
    `quantity_real`      DOUBLE          NOT NULL,
    `quantity_fake`      DOUBLE          NOT NULL,
    `price`              DOUBLE       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_tbl_wh_inventory_item_product_id` (`product_id`),
    KEY `FK_tbl_wh_inventory_item_unit_check_id` (`unit_check_id`),
    KEY `FK_tbl_wh_inventory_item_parent_id` (`parent_id`),
    KEY `FK_tbl_wh_inventory_item_product_detail_id` (`product_detail_id`),
    CONSTRAINT `FK_tbl_wh_inventory_item_product_id` FOREIGN KEY (`product_id`) REFERENCES `tbl_product` (`id`),
    CONSTRAINT `FK_tbl_wh_inventory_item_unit_check_id` FOREIGN KEY (`unit_check_id`) REFERENCES `tbl_product_unit_connect` (`id`),
    CONSTRAINT `FK_tbl_wh_inventory_item_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `tbl_warehouse_inventory` (`id`),
    CONSTRAINT `FK_tbl_wh_inventory_item_product_detail_id` FOREIGN KEY (`product_detail_id`) REFERENCES `tbl_product_detail` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;


DROP TABLE IF EXISTS `tbl_warehouse_inventory_file_connect`;
CREATE TABLE `tbl_warehouse_inventory_file_connect`
(
    `file_id`         BIGINT UNSIGNED NOT NULL,
    `wh_inventory_id` BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (`file_id`, `wh_inventory_id`),
    KEY `tbl_wh_inventory_file_connect_file_id` (`file_id`),
    KEY `tbl_wh_inventory_file_connect_wh_import_id` (`wh_inventory_id`),
    CONSTRAINT `tbl_wh_inventory_file_connect_file_id` FOREIGN KEY (`file_id`) REFERENCES `tbl_file_description` (`id`),
    CONSTRAINT `tbl_wh_inventory_file_connect_wh_import_id` FOREIGN KEY (`wh_inventory_id`) REFERENCES `tbl_warehouse_inventory` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;


DROP TABLE IF EXISTS `tbl_warehouse_inventory_employee_connect`;
CREATE TABLE `tbl_warehouse_inventory_employee_connect`
(
    `employee_id`     BIGINT UNSIGNED NOT NULL,
    `wh_inventory_id` BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (`employee_id`, `wh_inventory_id`),
    KEY `tbl_wh_inventory_employee_connect_employee_id` (`employee_id`),
    KEY `tbl_wh_inventory_employee_connect_wh_import_id` (`wh_inventory_id`),
    CONSTRAINT `tbl_wh_inventory_employee_connect_employee_id` FOREIGN KEY (`employee_id`) REFERENCES `tbl_employee` (`id`),
    CONSTRAINT `tbl_wh_inventory_employee_connect_wh_import_id` FOREIGN KEY (`wh_inventory_id`) REFERENCES `tbl_warehouse_inventory` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;


SET FOREIGN_KEY_CHECKS = 1;