
INSERT INTO `drink` (`active`, `name`, `price`) VALUES
(b'1', 'Black Coffee', 4.0);
INSERT INTO `drink` (`active`, `name`, `price`) VALUES
(b'1', 'Latte', 5.0);
INSERT INTO `drink` (`active`, `name`, `price`) VALUES
(b'1', 'Mocha', 6.0);
INSERT INTO `drink` (`active`, `name`, `price`) VALUES
(b'1', 'Tea', 3.0);


INSERT INTO `topping` (`active`, `name`, `price`) VALUES
(b'1', 'Milk', 2.0);
INSERT INTO `topping` (`active`, `name`, `price`) VALUES
(b'1', 'Hazelnut syrup', 3.0);
INSERT INTO `topping` (`active`, `name`, `price`) VALUES
(b'1', 'Chocolate sauce', 5.0);
INSERT INTO `topping` (`active`, `name`, `price`) VALUES
(b'1', 'Lemon', 2.0);


INSERT INTO `user` (`id`, `email`, `password`, `role`) VALUES
(1, 'admin@admin.com', '$2a$10$0vX71kWJ6KSSzqi04Ie7rOD8.dBrKaLSbSjTrKexJlUJ9MbmqyyU6', 'ROLE_ADMINISTRATOR');