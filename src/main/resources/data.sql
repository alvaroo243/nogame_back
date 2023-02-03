-- -----------------------------------------------------
-- DataBase `nogame_database`
-- -----------------------------------------------------
DROP DATABASE IF EXISTS `nogame_database`;

CREATE DATABASE IF NOT EXISTS `nogame_database`;

USE `nogame_database`;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE  TABLE IF NOT EXISTS `user` (
  `id` INT(11) NOT NULL ,
  `nickname` VARCHAR(50) NOT NULL UNIQUE,
  `email` VARCHAR(150) NOT NULL UNIQUE,
  `password` VARCHAR(50) NOT NULL,
  `created_ts` DATE,
  PRIMARY KEY (`id`) 
);

-- -----------------------------------------------------
-- Table `type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `type` ;

CREATE  TABLE IF NOT EXISTS `type` (
  `id` INT(11) NOT NULL ,
  `name` VARCHAR(50) NOT NULL,
  `advantages` TEXT NOT NULL,
  `disadvantages` TEXT NOT NULL,
  PRIMARY KEY (`id`) 
);

-- -----------------------------------------------------
-- Table `player`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `player` ;

CREATE  TABLE IF NOT EXISTS `player` (
  `id` INT(11) NOT NULL ,
  `type` INT(11) NULL DEFAULT NULL ,
  `user` INT(11) NOT NULL,
  `level` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `player_type`
	FOREIGN KEY (`type`)
    REFERENCES `type` (`id`),
  CONSTRAINT `player_user`
	FOREIGN KEY (`user`)
    REFERENCES `user` (`id`)
);

-- -----------------------------------------------------
-- Table `planet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `planet` ;

CREATE  TABLE IF NOT EXISTS `planet` (
  `id` INT(11) NOT NULL ,
  `name` VARCHAR(50) NOT NULL,
  `player_id` INT(11) DEFAULT 0,
  PRIMARY KEY (`id`),
  CONSTRAINT `planet_player`
	FOREIGN KEY (`player_id`)
    REFERENCES `player` (`id`)
);

-- -----------------------------------------------------
-- Table `resource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resource`;

CREATE TABLE IF NOT EXISTS `resource` (
	`id` INT(11) NOT NULL,
    `name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- Table `resource_storage`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resource_storage`;

CREATE TABLE IF NOT EXISTS `resource_storage` (
	`id` INT(11) NOT NULL,
    `resource_id` INT(11),
    `planet_id` INT(11),
    `quantity` INT,
    PRIMARY KEY (`id`),
    CONSTRAINT `resource_storage_type`
	  FOREIGN KEY (`resource_id`)
      REFERENCES `resource` (`id`),
	CONSTRAINT `resource_storage_planet`
      FOREIGN KEY (`planet_id`)
      REFERENCES `planet` (`id`)
);

-- -----------------------------------------------------
-- Table `troops`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `troops`;

CREATE TABLE IF NOT EXISTS `troops` (
	`id` INT(11) NOT NULL,
    `name` VARCHAR(50) NOT NULL,
    `special` BOOLEAN DEFAULT FALSE,
    `value` INT NOT NULL DEFAULT 1,
    `type` INT(11) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `troop_type`
      FOREIGN KEY (`type`)
      REFERENCES `type` (`id`)
);