SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `tbl_product_floor`;
CREATE TABLE `tbl_product_floor`
(
    `id`                 BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `product_id`         BIGINT UNSIGNED NOT NULL,
    `floor_id`           BIGINT UNSIGNED NOT NULL,
    `unit_target_id`     BIGINT UNSIGNED NOT NULL,
    `unit_source_id`     BIGINT UNSIGNED NOT NULL,
    `product_detail_id`  BIGINT UNSIGNED DEFAULT NULL,
    `consignment_number` VARCHAR(100)    DEFAULT NULL,
    `quantity_target`    DOUBLE          NOT NULL,
    `quantity_source`    DOUBLE          NOT NULL,
    `input_date`         DATETIME        NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_tbl_product_floor_product_id` (`product_id`),
    KEY `FK_tbl_product_floor_unit_target_id` (`unit_target_id`),
    KEY `FK_tbl_product_floor_unit_source_id` (`unit_source_id`),
    KEY `FK_tbl_product_floor_floor_id` (`floor_id`),
    KEY `FK_tbl_product_floor_product_detail_id` (`product_detail_id`),
    CONSTRAINT `FK_tbl_product_floor_product_id` FOREIGN KEY (`product_id`) REFERENCES `tbl_product` (`id`),
    CONSTRAINT `FK_tbl_product_floor_unit_target_id` FOREIGN KEY (`unit_target_id`) REFERENCES `tbl_product_unit_connect` (`id`),
    CONSTRAINT `FK_tbl_product_floor_unit_source_id` FOREIGN KEY (`unit_source_id`) REFERENCES `tbl_product_unit_connect` (`id`),
    CONSTRAINT `FK_tbl_product_floor_floor_id` FOREIGN KEY (`floor_id`) REFERENCES `tbl_warehouse_floor` (`id`),
    CONSTRAINT `FK_tbl_product_floor_product_detail_id` FOREIGN KEY (`product_detail_id`) REFERENCES `tbl_product_detail` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;


SET FOREIGN_KEY_CHECKS = 1;