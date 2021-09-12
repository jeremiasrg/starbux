
CREATE TABLE `client` (
  `id` BIGINT(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `drink` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `order_drink` (
  `id` bigint(20) NOT NULL,
  `drink_id` bigint(20) NOT NULL,
  `drink_unit_price` double NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `order_drink_topping` (
  `id` bigint(20) NOT NULL,
  `drink_id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `topping_id` bigint(20) NOT NULL,
  `topping_unit_price` double NOT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  `order_drink_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `sb_order` (
  `id` bigint(20) NOT NULL,
  `date_time` datetime(6) NOT NULL,
  `discount` double NOT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  `address` varchar(300) NOT NULL,
  `client_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `topping` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `client`
  CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT FIRST;

ALTER TABLE `drink`
  CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT FIRST;

ALTER TABLE `sb_order`
  CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT FIRST;

ALTER TABLE `topping`
  CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT FIRST;

ALTER TABLE `user`
  CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT FIRST;

ALTER TABLE `order_drink`
  CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT FIRST;

ALTER TABLE `order_drink_topping`
  CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT FIRST;

