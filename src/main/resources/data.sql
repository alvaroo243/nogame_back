-- -----------------------------------------------------
-- DataBase `nogame_database`
-- -----------------------------------------------------

CREATE USER IF NOT EXISTS 'nogame'@'localhost' IDENTIFIED BY 'grupo2';
GRANT ALL PRIVILEGES ON *.* TO 'nogame'@'localhost' WITH GRANT OPTION;
ALTER USER 'nogame'@'localhost' REQUIRE NONE WITH 
MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0; 

DROP DATABASE IF EXISTS `nogame`;

CREATE DATABASE IF NOT EXISTS `nogame`;

GRANT ALL PRIVILEGES ON `nogame`.* TO 'nogame'@'localhost';

USE `nogame`;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE  TABLE IF NOT EXISTS `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nickname` VARCHAR(50) NOT NULL UNIQUE,
  `email` VARCHAR(150) NOT NULL UNIQUE,
  `password` VARCHAR(150) NOT NULL,
  `first` INT(11) DEFAULT NULL UNIQUE,
  `created_ts` DATE,
  PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- TABLAS NECESARIAS PARA IMPLEMENTAR LA SEGURIDAD
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `roles`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `roles` ;

CREATE  TABLE IF NOT EXISTS `roles` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) 
);

-- -----------------------------------------------------
-- Table `usuarios_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `usuarios_roles` ;

CREATE  TABLE IF NOT EXISTS `usuarios_roles` (
  idUsuario INT(11)  NOT NULL,  
  idRol INT(11)  NOT NULL,
  PRIMARY KEY (`idUsuario`,`idRol`),
  CONSTRAINT `usuarios_roles_fk_usuarios`
    FOREIGN KEY (`idUsuario` )
    REFERENCES `user` (`id` ),
  CONSTRAINT `usuarios_roles_fk_roles`
    FOREIGN KEY (`idRol` )
    REFERENCES `roles` (`id` ) 
);

INSERT INTO `roles` (`id`, `nombre`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- -----------------------------------------------------
-- FIN TABLAS NECESARIAS PARA IMPLEMENTAR LA SEGURIDAD
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `type` ;

CREATE  TABLE IF NOT EXISTS `type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) UNIQUE,
  `advantages` TEXT NOT NULL,
  `disadvantages` TEXT NOT NULL,
  PRIMARY KEY (`id`) 
);

INSERT INTO type (name,advantages,disadvantages)
VALUES ('General','Tiene mucho ataque','No consigue tantos recursos y necesita mas tiempo para investigar');

INSERT INTO type (name,advantages,disadvantages)
VALUES ('Investigador','Investigas mas con menos tiempo','Tienes menor ataque y no consigues tantos recursos');

INSERT INTO type (name,advantages,disadvantages)
VALUES ('Recolector','Reolectas mas con menos tiempo','Tienes menor ataque y necesitas mas tiempo para investigar');

-- -----------------------------------------------------
-- Table `player`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `player` ;

CREATE  TABLE IF NOT EXISTS `player` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `type` INT(11) DEFAULT NULL,
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
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `player` INT(11),
  `image` VARCHAR(250) DEFAULT NULL UNIQUE,
  `first` BOOLEAN DEFAULT FALSE,
  `created_ts` DATE,
  CONSTRAINT `planet_player` 
    FOREIGN KEY (`player`) 
	REFERENCES `player` (`id`),
  PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- Table `resource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resource`;

CREATE TABLE IF NOT EXISTS `resource` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL UNIQUE,
	`image` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
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
-- Table `resource_storage`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resource_storage`;

CREATE TABLE IF NOT EXISTS `resource_storage` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
    `resource` INT(11) NOT NULL,
	`structure` INT(11) NOT NULL,
    `planet` INT(11),
    `quantity` INT(11),
    PRIMARY KEY (`id`),
    CONSTRAINT `resource_storage_type`
	  FOREIGN KEY (`resource`)
      REFERENCES `resource` (`id`),
	CONSTRAINT `resource_storage_structure`
	  FOREIGN KEY (`structure`)
	  REFERENCES `structure` (`id`),
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
    `type` INT(11),
    PRIMARY KEY (`id`),
    CONSTRAINT `troop_type`
      FOREIGN KEY (`type`)
      REFERENCES `type` (`id`)
); 

-- -----------------------------------------------------
-- Table `structure_formula`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `structure_formula`;

CREATE TABLE IF NOT EXISTS `structure_formula`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`structure` VARCHAR(50) NOT NULL,
	`resource` VARCHAR(150) NOT NULL,
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
	`structure` VARCHAR(50) NOT NULL,
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
		REFERENCES `player`(`id`)
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
		REFERENCES `resource`(`name`)
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

ALTER TABLE `user` ADD FOREIGN KEY (`first`) REFERENCES `planet` (`id`);