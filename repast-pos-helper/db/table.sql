CREATE TABLE `app_debug_log` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`user_id` BIGINT(20) NOT NULL,
	`merchant_id` BIGINT(20) NOT NULL,
	`type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '日志类型',
	`detail` TEXT NULL COMMENT '日志数据明细，json数据，自定义',
	`create_time` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
	PRIMARY KEY (`id`),
	INDEX `merchant_type_user` (`merchant_id`, `type`, `user_id`)
)
COMMENT='app 调试日志'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
