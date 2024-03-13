INSERT INTO `tbl_user`
(`id`, `create_by`, `create_date`, `modify_by`, `modify_date`, `account_non_locked`, `active`, `email`, `need_fix_pass`, `password`, `username`, `failed_attempt`, `lock_time`)
VALUES (1, 'admin', '2022-02-02 00:00:00', 'admin', '2022-02-02 00:00:00', b'1', b'1', 'root@gmail.com', 0, '$2a$10$BWi6mJVbU7JsY60Y3nhpVusoSZB8suV9U/BoNu3XfVPcX0Dxdgeiq', 'root', 0, NULL);


INSERT INTO `tbl_role` (`id`, `create_by`, `create_date`, `modify_by`, `modify_date`, `description`, `name`) VALUES (1, 'admin', '2022-02-02 00:00:00', 'admin', '2022-02-02 00:00:00', 'ROOT', 'ROLE_ROOT');
INSERT INTO `tbl_role` (`id`, `create_by`, `create_date`, `modify_by`, `modify_date`, `description`, `name`) VALUES (2, 'admin', '2022-02-02 00:00:00', 'admin', '2022-02-02 00:00:00', 'ADMIN', 'ROLE_ADMIN');
INSERT INTO `tbl_role` (`id`, `create_by`, `create_date`, `modify_by`, `modify_date`, `description`, `name`) VALUES (3, 'admin', '2022-02-02 00:00:00', 'admin', '2022-02-02 00:00:00', 'USER', 'ROLE_USER');


INSERT INTO `tbl_user_role` (`user_id`, `role_id`) VALUES (1, 1);


INSERT INTO `tbl_authority` (`id`, `name`, `description`) VALUES (1, 'USER_VIEW', null);
INSERT INTO `tbl_authority` (`id`, `name`, `description`) VALUES (2, 'USER_SEARCH', null);
INSERT INTO `tbl_authority` (`id`, `name`, `description`) VALUES (3, 'USER_CREATE', null);
INSERT INTO `tbl_authority` (`id`, `name`, `description`) VALUES (4, 'USER_UPDATE', null);
INSERT INTO `tbl_authority` (`id`, `name`, `description`) VALUES (5, 'USER_DELETE', null);
INSERT INTO `tbl_authority` (`id`, `name`, `description`) VALUES (6, 'USER_UPLOAD_FILE', null);
INSERT INTO `tbl_authority` (`id`, `name`, `description`) VALUES (7, 'USER_DOWNLOAD_FILE', null);
INSERT INTO `tbl_authority` (`id`, `name`, `description`) VALUES (8, 'USER_VIEW_FILE', null);

INSERT INTO `tbl_authority` (`id`, `name`, `description`) VALUES (9, 'ROLE_VIEW', null);
INSERT INTO `tbl_authority` (`id`, `name`, `description`) VALUES (10, 'ROLE_SEARCH', null);
INSERT INTO `tbl_authority` (`id`, `name`, `description`) VALUES (11, 'ROLE_CREATE', null);
INSERT INTO `tbl_authority` (`id`, `name`, `description`) VALUES (12, 'ROLE_UPDATE', null);
INSERT INTO `tbl_authority` (`id`, `name`, `description`) VALUES (13, 'ROLE_DELETE', null);


INSERT INTO  `tbl_role_authority` (`role_id`, `authority_id`) VALUES (1, 1);
INSERT INTO  `tbl_role_authority` (`role_id`, `authority_id`) VALUES (1, 2);
INSERT INTO  `tbl_role_authority` (`role_id`, `authority_id`) VALUES (1, 3);
INSERT INTO  `tbl_role_authority` (`role_id`, `authority_id`) VALUES (1, 4);
INSERT INTO  `tbl_role_authority` (`role_id`, `authority_id`) VALUES (1, 5);
INSERT INTO  `tbl_role_authority` (`role_id`, `authority_id`) VALUES (1, 6);
INSERT INTO  `tbl_role_authority` (`role_id`, `authority_id`) VALUES (1, 7);
INSERT INTO  `tbl_role_authority` (`role_id`, `authority_id`) VALUES (1, 8);
INSERT INTO  `tbl_role_authority` (`role_id`, `authority_id`) VALUES (1, 9);
INSERT INTO  `tbl_role_authority` (`role_id`, `authority_id`) VALUES (1, 10);
INSERT INTO  `tbl_role_authority` (`role_id`, `authority_id`) VALUES (1, 11);
INSERT INTO  `tbl_role_authority` (`role_id`, `authority_id`) VALUES (1, 12);
INSERT INTO  `tbl_role_authority` (`role_id`, `authority_id`) VALUES (1, 13);