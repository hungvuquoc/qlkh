SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user`
(
    `id`                      bigint unsigned NOT NULL AUTO_INCREMENT,
    `create_by`               varchar(100)    NOT NULL,
    `create_date`             datetime        NOT NULL,
    `modify_by`               varchar(100) DEFAULT NULL,
    `modify_date`             datetime     DEFAULT NULL,
    `account_non_locked`      bit(1)          NOT NULL,
    `active`                  bit(1)          NOT NULL,
    `email`                   varchar(150) DEFAULT NULL,
    `failed_attempt`          tinyint      DEFAULT NULL,
    `lock_time`               datetime     DEFAULT NULL,
    `need_fix_pass`           bit(1)       DEFAULT b'0',
    `password`                varchar(255)    NOT NULL,
    `username`                varchar(100)    NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_tbl_user_username` (`username`),
    UNIQUE KEY `UK_tbl_user_email` (`email`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `tbl_role`;
CREATE TABLE `tbl_role`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT,
    `create_by`   varchar(100)    NOT NULL,
    `create_date` datetime        NOT NULL,
    `modify_by`   varchar(100) DEFAULT NULL,
    `modify_date` datetime     DEFAULT NULL,
    `description` varchar(150) DEFAULT NULL,
    `name`        varchar(150)    NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_tbl_role_name` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `tbl_authority`;
CREATE TABLE `tbl_authority`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT,
    `name`        varchar(150)    NOT NULL,
    `description` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_tbl_authority_name` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `tbl_user_role`;
CREATE TABLE `tbl_user_role`
(
    `user_id` bigint unsigned NOT NULL,
    `role_id` bigint unsigned NOT NULL,
    PRIMARY KEY (user_id, role_id),
    KEY `FK_tbl_user_role_tbl_role_role_id` (`role_id`),
    KEY `FK_tbl_user_role_tbl_user_user_id` (`user_id`),
    CONSTRAINT `FK_tbl_user_role_tbl_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `tbl_role` (`id`),
    CONSTRAINT `FK_tbl_user_role_tbl_user_user_id` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `tbl_user_authority`;
CREATE TABLE `tbl_user_authority`
(
    `user_id` bigint unsigned NOT NULL,
    `authority_id` bigint unsigned NOT NULL,
    PRIMARY KEY (user_id, authority_id),
    KEY `FK_tbl_user_authority_user_id` (`user_id`),
    KEY `FK_tbl_user_authority_authority_id` (`authority_id`),
    CONSTRAINT `FK_tbl_user_authority_authority_id` FOREIGN KEY (`authority_id`) REFERENCES `tbl_authority` (`id`),
    CONSTRAINT `FK_tbl_user_authority_user_id` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `tbl_role_authority`;
CREATE TABLE `tbl_role_authority`
(
    `role_id` bigint unsigned NOT NULL,
    `authority_id` bigint unsigned NOT NULL,
    PRIMARY KEY (role_id, authority_id),
    KEY `FK_tbl_role_authority_user_id` (`role_id`),
    KEY `FK_tbl_role_authority_authority_id` (`authority_id`),
    CONSTRAINT `FK_tbl_role_authority_authority_id` FOREIGN KEY (`authority_id`) REFERENCES `tbl_authority` (`id`),
    CONSTRAINT `FK_tbl_role_authority_role_id` FOREIGN KEY (`role_id`) REFERENCES `tbl_role` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `tbl_verification_codes`;
CREATE TABLE `tbl_verification_codes`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT,
    `create_by`   varchar(100)    NOT NULL,
    `create_date` datetime        NOT NULL,
    `modify_by`   varchar(100) DEFAULT NULL,
    `modify_date` datetime     DEFAULT NULL,
    `code_sms`    varchar(255) DEFAULT NULL,
    `email`       varchar(255) DEFAULT NULL,
    `expired`     datetime     DEFAULT NULL,
    `type`        int          DEFAULT NULL,
    `user_id`     bigint       DEFAULT NULL,
    `username`    varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `tbl_file_description`;
CREATE TABLE `tbl_file_description`
(
    `id`           bigint unsigned NOT NULL AUTO_INCREMENT,
    `create_by`    varchar(100)    NOT NULL,
    `create_date`  datetime        NOT NULL,
    `modify_by`    varchar(100) DEFAULT NULL,
    `modify_date`  datetime     DEFAULT NULL,
    `content_size` bigint       DEFAULT NULL,
    `content_type` varchar(255) DEFAULT NULL,
    `extension`    varchar(255) DEFAULT NULL,
    `file_path`    varchar(255) DEFAULT NULL,
    `name`         varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `tbl_file_old`;
CREATE TABLE `tbl_file_old`
(
    `id`         bigint unsigned NOT NULL AUTO_INCREMENT,
    `created_at` datetime     DEFAULT NULL,
    `path`       varchar(300) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;