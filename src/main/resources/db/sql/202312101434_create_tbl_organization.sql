SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `tbl_organization`;
CREATE TABLE `tbl_organization`
(
    `id`              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `code`            VARCHAR(20)  DEFAULT NULL,
    `name`            VARCHAR(300) DEFAULT NULL,
    `enterprise_code` VARCHAR(20)  DEFAULT NULL,
    `manager_name`    VARCHAR(255) DEFAULT NULL,
    `address`         VARCHAR(300) DEFAULT NULL,
    `phone`           VARCHAR(15)  DEFAULT NULL,
    `email`           VARCHAR(255) DEFAULT NULL,
    `description`     VARCHAR(500) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;