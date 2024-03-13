SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `tbl_employee`;
CREATE TABLE `tbl_employee`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `create_by`   VARCHAR(100)    NOT NULL,
    `create_date` DATETIME        NOT NULL,
    `modify_by`   VARCHAR(100)    DEFAULT NULL,
    `modify_date` DATETIME        DEFAULT NULL,
    `user_id`     BIGINT UNSIGNED DEFAULT NULL,
    `code`        VARCHAR(30)     NOT NULL,
    `name`        VARCHAR(255)    NOT NULL,
    `birthday`    DATE            NOT NULL,
    `gender`      INT         NOT NULL,
    `phone`       VARCHAR(11)     NOT NULL,
    `deleted`     bit(1)          DEFAULT 0,
    PRIMARY KEY (`id`),
    KEY `FK_tbl_employee_user_id` (`user_id`),
    CONSTRAINT `FK_tbl_employee_user_id` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;


SET FOREIGN_KEY_CHECKS = 1;