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
<<<<<<< Updated upstream
	`id` INT(11) NOT NULL,
    `resource_id` INT(11),
    `planet_id` INT(11),
    `quantity` INT,
    PRIMARY KEY (`id`),
    CONSTRAINT `resource_storage_type`
	  FOREIGN KEY (`resource_id`)
=======
	`id` INT(11) NOT NULL AUTO_INCREMENT,
    `resource` INT(11) NOT NULL,
    `planet` INT(11),
    `quantity` INT(11),
    PRIMARY KEY (`id`),
    CONSTRAINT `resource_storage_type`
	  FOREIGN KEY (`resource`)
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
);
=======
); 

-- -----------------------------------------------------
-- Table `structure`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `structure`;

CREATE TABLE IF NOT EXISTS `structure` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL UNIQUE,
	`upgrade_time_formula` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id`)
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
	`structure` INT(11) NOT NULL,
	`resource` VARCHAR(150) NOT NULL,
	`upgrade_formula` VARCHAR(50) NOT NULL,
	`production_formula` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `structures`
		FOREIGN KEY(`structure`)
		REFERENCES `structure`(`id`),
	CONSTRAINT `resources`
		FOREIGN KEY(`resource`)
		REFERENCES `resource`(`id`)
);

INSERT INTO structure_formula(structure,resource,upgrade_formula,production_formula)
VALUES(1,1,'asdf','asdf');

INSERT INTO structure_formula(structure,resource,upgrade_formula,production_formula)
VALUES(2,2,'asdf','asdf');

INSERT INTO structure_formula(structure,resource,upgrade_formula,production_formula)
VALUES(3,3,'asdf','asdf');

INSERT INTO structure_formula(structure,resource,upgrade_formula,production_formula)
VALUES(4,4,'asdf','asdf');

-- -----------------------------------------------------
-- Table `structure_created`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `structure_created`;

CREATE TABLE IF NOT EXISTS `structure_created`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`structure`INT(11) NOT NULL,
	`planet` INT(11) NOT NULL,
	`player` INT(11) NOT NULL,
	`upgrade_ongoing` BOOLEAN DEFAULT 0 NOT NULL,
	`current_level` INT(11) DEFAULT 0,
	PRIMARY KEY (`id`),
	CONSTRAINT `structures_id`
		FOREIGN KEY(`structure`)
		REFERENCES `structure`(`id`)
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
		REFERENCES `player`(`id`)
);


-- -----------------------------------------------------
-- Table `research_formula`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `research_formula`;

CREATE TABLE IF NOT EXISTS `research_formula`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`research` INT(11) NOT NULL,
	`resource` INT(11) NOT NULL,
	`upgrade_formula` VARCHAR(50),
	PRIMARY KEY(`id`),
	CONSTRAINT `research_formula`
		FOREIGN KEY (`research`)
		REFERENCES `research`(`id`),
	CONSTRAINT `resource_formula`
		FOREIGN KEY(`resource`)
		REFERENCES `resource`(`id`)
);

-- -----------------------------------------------------
-- Table `prerequisite_structure`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `prerequisite_structure`;

CREATE TABLE IF NOT EXISTS `prerequisite_structure`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`structure` INT(11) NOT NULL,
	`level_required` INT(11) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `prerequisite_structures`
		FOREIGN KEY(`structure`)
		REFERENCES `structure`(`id`)
); 


-- -----------------------------------------------------
-- Table `prerequisite_research`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `prerequisite_research`;

CREATE TABLE IF NOT EXISTS `prerequisite_research`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`structure` INT(11) NOT NULL,
	`level_required` INT(11) NOT NULL,
	`resource` INT(11) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `prerequisite_research_structures`
		FOREIGN KEY(`structure`)
		REFERENCES `structure`(`id`),
	CONSTRAINT `prerequisite_research_resource`
		FOREIGN KEY(`resource`)
		REFERENCES `resource`(`id`)
); 

ALTER TABLE `user` ADD FOREIGN KEY (`first`) REFERENCES `planet` (`id`);
>>>>>>> Stashed changes
