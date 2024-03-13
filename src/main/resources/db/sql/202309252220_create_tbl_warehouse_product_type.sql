SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `tbl_warehouse_product_type_connect`;
CREATE TABLE `tbl_warehouse_product_type_connect`
(
    `warehouse_id` BIGINT UNSIGNED NOT NULL,
    `type_id` BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (`warehouse_id`, `type_id`),
    KEY `FK_tbl_wh_product_type_connect_warehouse_id` (`warehouse_id`),
    KEY `FK_tbl_wh_product_type_connect_type_id` (`type_id`),
    CONSTRAINT `FK_tbl_wh_product_type_connect_warehouse_id` FOREIGN KEY (`warehouse_id`) REFERENCES `tbl_warehouse` (`id`),
    CONSTRAINT `FK_tbl_wh_product_type_connect_type_id` FOREIGN KEY (`type_id`) REFERENCES `tbl_product_type` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;