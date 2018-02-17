CREATE SCHEMA `let_us_see`;

USE SCHEMA `le_us_see`;

CREATE TABLE IF NOT EXISTS `user_details` (
	`id` int(10) NOT NULL AUTO_INCREMENT,
	`name` varchar(250) NOT NULL,
	`email` varchar(250) NOT NULL,
	`company_name` varchar(250) NOT NULL,
	`password` varchar(500) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY `email` (`email`)
);

CREATE TABLE IF NOT  EXISTS `column`(
	`id` int(10) PRIMARY KEY AUTO_INCREMENT,
	`column_name` varchar(250) NOT NULL,
	`data_type`  varchar(250) NOT NULL,
    `column_index` int
);

CREATE TABLE IF NOT EXISTS `table`(
	`id` int(10) PRIMARY KEY AUTO_INCREMENT,
	`table_name` varchar(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS `rule_vale`(
	`id` int(10) PRIMARY KEY AUTO_INCREMENT,
	`table_name` varchar(250) NOT NULL,
	`column_name` varchar(250) NOT NULL,
	`data_type`  varchar(250) NOT NULL,
    `column_index` int NOT NULL,
	`rule_type` varchar(250) NOT NULL,
	`rule_key` varchar(250) NOT NULL,
	`rule_value` varchar(250) NOT NULL
);


