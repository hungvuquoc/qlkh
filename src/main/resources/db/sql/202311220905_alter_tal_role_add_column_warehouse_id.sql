SET FOREIGN_KEY_CHECKS = 0;

ALTER TABLE `tbl_role`
ADD COLUMN `warehouse_id` BIGINT UNSIGNED DEFAULT NULL;

ALTER TABLE `tbl_role`
ADD CONSTRAINT `FK_tbl_role_warehouse_id` FOREIGN KEY (`warehouse_id`) REFERENCES `tbl_warehouse` (`id`);

SET FOREIGN_KEY_CHECKS = 1;