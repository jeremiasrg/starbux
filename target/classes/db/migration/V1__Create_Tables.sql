--
-- create table `drink`
--

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `drink` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `active` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `drink`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `drink`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
COMMIT;

--
-- create table `sb_order`
--

CREATE TABLE `sb_order` (
  `id` bigint(20) NOT NULL,
  `customer_name` varchar(100) NOT NULL,
  `date_time` datetime(6) NOT NULL,
  `discount` double NOT NULL,
  `active` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `sb_order`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `sb_order`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
COMMIT;

--
-- Table structure for table `order_drink`
--

CREATE TABLE `order_drink` (
  `id` bigint(20) NOT NULL,
  `drink_id` bigint(20) NOT NULL,
  `drink_unit_price` double NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `order_drink_topping`
--

CREATE TABLE `order_drink_topping` (
  `id` bigint(20) NOT NULL,
  `drink_id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `topping_id` bigint(20) NOT NULL,
  `topping_unit_price` double NOT NULL,
  `active` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `topping`
--

CREATE TABLE `topping` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `active` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `order_drink`
--
ALTER TABLE `order_drink`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKod6xytjdn5omi6akyj4xdax1e` (`drink_id`),
  ADD KEY `FKf7s8crntxckf6026kci9w4eb6` (`order_id`);

--
-- Indexes for table `order_drink_topping`
--
ALTER TABLE `order_drink_topping`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKte1d9n1inau5119ta1ihiy5tr` (`drink_id`),
  ADD KEY `FKplabdch5owa3np8kq7s9u5fy2` (`topping_id`);

--
-- Indexes for table `topping`
--
ALTER TABLE `topping`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `order_drink`
--
ALTER TABLE `order_drink`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `order_drink_topping`
--
ALTER TABLE `order_drink_topping`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `topping`
--
ALTER TABLE `topping`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `order_drink`
--
ALTER TABLE `order_drink`
  ADD CONSTRAINT `FKf7s8crntxckf6026kci9w4eb6` FOREIGN KEY (`order_id`) REFERENCES `sb_order` (`id`),
  ADD CONSTRAINT `FKod6xytjdn5omi6akyj4xdax1e` FOREIGN KEY (`drink_id`) REFERENCES `drink` (`id`);

--
-- Constraints for table `order_drink_topping`
--
ALTER TABLE `order_drink_topping`
  ADD CONSTRAINT `FKplabdch5owa3np8kq7s9u5fy2` FOREIGN KEY (`topping_id`) REFERENCES `topping` (`id`),
  ADD CONSTRAINT `FKte1d9n1inau5119ta1ihiy5tr` FOREIGN KEY (`drink_id`) REFERENCES `order_drink` (`id`);
COMMIT;