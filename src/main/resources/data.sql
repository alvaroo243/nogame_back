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
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nickname` VARCHAR(50) NOT NULL UNIQUE,
  `email` VARCHAR(150) NOT NULL UNIQUE,
  `password` VARCHAR(150) NOT NULL,
  `created_ts` DATE,
  PRIMARY KEY (`id`, `email`) 
);

-- -----------------------------------------------------
-- Table `type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `type` ;

CREATE  TABLE IF NOT EXISTS `type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) UNIQUE,
  `advantages` TEXT NOT NULL,
  `disadvantages` TEXT NOT NULL,
  PRIMARY KEY (`id`,`name`) 
);

INSERT INTO type (name,advantages,disadvantages)
VALUES ('General','Tiene mucho ataque','No consigue tantos recursos y necesita mas tiempo para investigar');

INSERT INTO type (name,advantages,disadvantages)
VALUES ('Investigador','Investigas mas con menos tiempo','Tienes menor ataque y no consigues tantos recursos');

INSERT INTO type (name,advantages,disadvantages)
VALUES ('Recolector','Reolectas mas con menos tiempo','Tienes menor ataque y necesitas mas tiempo para investigar');

-- -----------------------------------------------------
-- Table `planet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `planet` ;

CREATE  TABLE IF NOT EXISTS `planet` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `player` INT(11),
  `image` VARCHAR(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- Table `player`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `player` ;

CREATE  TABLE IF NOT EXISTS `player` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(50) UNIQUE NULL DEFAULT NULL,
  `userId` INT(11) NOT NULL UNIQUE,
  `userEmail` VARCHAR(150) NOT NULL UNIQUE,
  `level` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `player_type`
	FOREIGN KEY (`type`)
    REFERENCES `type` (`name`),
  CONSTRAINT `player_userId`
	FOREIGN KEY (`userId`)
    REFERENCES `user` (`id`),
  CONSTRAINT `player_userEmail`
	FOREIGN KEY (`userEmail`)
    REFERENCES `user` (`email`)
);

-- -----------------------------------------------------
-- Table `resource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resource`;

CREATE TABLE IF NOT EXISTS `resource` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL UNIQUE,
    PRIMARY KEY (`id`,`name`)
);

INSERT INTO resource (name)
VALUES ('Piedra');
INSERT INTO resource (name)
VALUES ('Cristal');
INSERT INTO resource (name)
VALUES ('Hierro');
INSERT INTO resource (name)
VALUES ('Petroleo');

-- -----------------------------------------------------
-- Table `resource_storage`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resource_storage`;

CREATE TABLE IF NOT EXISTS `resource_storage` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
    `resource` VARCHAR(150) NOT NULL UNIQUE,
    `planet` INT(11),
    `quantity` INT(11),
    PRIMARY KEY (`id`),
    CONSTRAINT `resource_storage_type`
	  FOREIGN KEY (`resource`)
      REFERENCES `resource` (`name`),
	CONSTRAINT `resource_storage_planet`
      FOREIGN KEY (`planet`)
      REFERENCES `planet` (`id`)
);

-- -----------------------------------------------------
-- Table `troops`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `troops`;

CREATE TABLE IF NOT EXISTS `troops` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL UNIQUE,
    `special` BOOLEAN DEFAULT FALSE,
    `value` INT NOT NULL DEFAULT 1,
    `type` VARCHAR(50) UNIQUE,
    PRIMARY KEY (`id`),
    CONSTRAINT `troop_type`
      FOREIGN KEY (`type`)
      REFERENCES `type` (`name`)
);

-- -----------------------------------------------------
-- Table `structure`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `structure`;

CREATE TABLE IF NOT EXISTS `structure` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL UNIQUE,
	`upgrade_time_formula` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id`,`name`)
);

INSERT INTO structure (name,upgrade_time_formula)
VALUES ('Recolector de piedra','50');
INSERT INTO structure (name,upgrade_time_formula)
VALUES ('Recolector de cristal', '50');
INSERT INTO structure (name,upgrade_time_formula)
VALUES ('Recolector de hierro', '50');
INSERT INTO structure (name,upgrade_time_formula)
VALUES ('Recolector de petroleo','50');

-- -----------------------------------------------------
-- Table `structure_formula`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `structure_formula`;

CREATE TABLE IF NOT EXISTS `structure_formula`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`structure` VARCHAR(50) NOT NULL UNIQUE,
	`resource` VARCHAR(150) NOT NULL UNIQUE,
	`upgrade_formula` VARCHAR(50) NOT NULL,
	`production_formula` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `structures`
		FOREIGN KEY(`structure`)
		REFERENCES `structure`(`name`),
	CONSTRAINT `resources`
		FOREIGN KEY(`resource`)
		REFERENCES `resource`(`name`)
);

INSERT INTO structure_formula(structure,resource,upgrade_formula,production_formula)
VALUES('Recolector de piedra','Piedra','asdf','asdf');

INSERT INTO structure_formula(structure,resource,upgrade_formula,production_formula)
VALUES('Recolector de cristal','Cristal','asdf','asdf');

INSERT INTO structure_formula(structure,resource,upgrade_formula,production_formula)
VALUES('Recolector de hierro','Hierro','asdf','asdf');

INSERT INTO structure_formula(structure,resource,upgrade_formula,production_formula)
VALUES('Recolector de petroleo','Petroleo','asdf','asdf');

-- -----------------------------------------------------
-- Table `structure_created`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `structure_created`;

CREATE TABLE IF NOT EXISTS `structure_created`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`structure` VARCHAR(50) NOT NULL UNIQUE,
	`planet` INT(11) NOT NULL,
	`player` VARCHAR(50) NOT NULL,
	`upgrade_ongoing` BOOLEAN DEFAULT 0 NOT NULL,
	`current_level` INT(11) DEFAULT 0,
	PRIMARY KEY (`id`),
	CONSTRAINT `structures_name`
		FOREIGN KEY(`structure`)
		REFERENCES `structure`(`name`)
);

-- -----------------------------------------------------
-- Table `research`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `research`;

CREATE TABLE IF NOT EXISTS `research`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(150) NOT NULL UNIQUE,
	`upgrade_time_formula` VARCHAR(150) NOT NULL,
	PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- Table `research_required`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `research_required`;

CREATE TABLE IF NOT EXISTS `research_required`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`research` INT(11) NOT NULL,
	`level` INT(11) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `research`
		FOREIGN KEY(`research`)
		REFERENCES `research`(`id`),
	CONSTRAINT `research_required`
		FOREIGN KEY(`research`)
		REFERENCES `research`(`id`)
	
);

-- -----------------------------------------------------
-- Table `research_level`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `research_level`;

CREATE TABLE IF NOT EXISTS `research_level`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`player` INT(11) NOT NULL,
	`research` INT(11) NOT NULL,
	`current_level` INT(11) DEFAULT 0,
	`upgrade_ongoing` BOOLEAN DEFAULT 0 NOT NULL,
	`upgrade_end_time` TIME(5),
	PRIMARY KEY(`id`),
	CONSTRAINT `research_level`
		FOREIGN KEY (`research`)
		REFERENCES `research`(`id`),
	CONSTRAINT `player`
		FOREIGN KEY (`player`)
		REFERENCES `player`(`userId`)
);


-- -----------------------------------------------------
-- Table `research_formula`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `research_formula`;

CREATE TABLE IF NOT EXISTS `research_formula`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`research` INT(11) NOT NULL,
	`resource` VARCHAR(150) NOT NULL,
	`upgrade_formula` VARCHAR(50),
	PRIMARY KEY(`id`),
	CONSTRAINT `research_formula`
		FOREIGN KEY (`research`)
		REFERENCES `research`(`id`),
	CONSTRAINT `resource_formula`
		FOREIGN KEY(`resource`)
		REFERENCES `resource`(name)
);

-- -----------------------------------------------------
-- Table `prerequisite_structure`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `prerequisite_structure`;

CREATE TABLE IF NOT EXISTS `prerequisite_structure`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`structure` VARCHAR(150) NOT NULL,
	`level_required` INT(11) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `prerequisite_structures`
		FOREIGN KEY(`structure`)
		REFERENCES `structure`(`name`)
); 


-- -----------------------------------------------------
-- Table `prerequisite_research`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `prerequisite_research`;

CREATE TABLE IF NOT EXISTS `prerequisite_research`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`structure` VARCHAR(150) NOT NULL,
	`level_required` INT(11) NOT NULL,
	`resource` VARCHAR(150) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `prerequisite_research_structures`
		FOREIGN KEY(`structure`)
		REFERENCES `structure`(`name`),
	CONSTRAINT `prerequisite_research_resource`
		FOREIGN KEY(`resource`)
		REFERENCES `resource`(`name`)
); 