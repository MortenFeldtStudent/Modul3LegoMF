DROP DATABASE IF EXISTS lego;

CREATE SCHEMA `lego` DEFAULT CHARACTER SET utf8 ;

USE `lego` ;

CREATE TABLE `lego`.`users` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(100) NOT NULL,
  `password` CHAR(64) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  UNIQUE KEY (`email`),
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;

CREATE TABLE `lego`.`orders` (
  `order_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `orderdate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `shipped` DATETIME DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  CONSTRAINT `Link_to_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `lego`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE `lego`.`order_details` (
  `order_id` INT(11) NOT NULL,
  `length` INT(11) NOT NULL,
  `wide` INT(11) NOT NULL,
  `height` INT(11) NOT NULL,
  `door_length` INT(11) NOT NULL,
  `door_height` INT(11) NOT NULL,
  `window_length` INT(11) NOT NULL,
  `window_height` INT(11) NOT NULL,
  CONSTRAINT `Link_to_order_id`
    FOREIGN KEY (`order_id`)
    REFERENCES `lego`.`orders` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;