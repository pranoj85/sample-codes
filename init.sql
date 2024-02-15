CREATE TABLE `food-api-command`.`restaurants` (
  `id` int NOT NULL AUTO_INCREMENT,
  `restaurant_name` varchar(45) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `ratings` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `food-api-command`.`restaurants` AUTO_INCREMENT=1;

CREATE TABLE `food-api-command`.`menus` (
  `menu_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `restaurant_id` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `food-api-command`.`menus` AUTO_INCREMENT=1;