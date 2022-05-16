CREATE SCHEMA `bilabonnement` ;

USE `bilabonnement` ;

CREATE TABLE `bilabonnement`.`Locations` (
                                            `id` INT NOT NULL AUTO_INCREMENT,
                                            `name` VARCHAR(255) NOT NULL,
                                            PRIMARY KEY (`id`),
                                            UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
                                            UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);


CREATE TABLE `bilabonnement`.`Users` (
                                        `id` INT NOT NULL AUTO_INCREMENT,
                                        `name` VARCHAR(255) NOT NULL,
                                        `password` VARCHAR(64) NOT NULL,
                                        `salt` VARCHAR(16) NOT NULL,
                                        `role` VARCHAR(25) NOT NULL,
                                        `locationId` INT NOT NULL,
                                        PRIMARY KEY (`id`),
                                        UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
                                        INDEX `locationId_idx` (`locationId` ASC) VISIBLE,
                                        CONSTRAINT `locationId`
                                            FOREIGN KEY (`locationId`)
                                                REFERENCES `bilabonnement`.`Locations` (`id`)
                                                ON DELETE NO ACTION
                                                ON UPDATE NO ACTION);


CREATE TABLE `bilabonnement`.`cars` (
                                        `id` INT NOT NULL,
                                        `chassisNumber` VARCHAR(17) NULL,
                                        `status` VARCHAR(255) NULL,
                                        `make` VARCHAR(255) NULL,
                                        `model` VARCHAR(255) NULL,
                                        `trimLevel` VARCHAR(2400) NULL,
                                        `scrapPrice` INT NULL,
                                        `registrationFee` INT NULL,
                                        `co2Emission` VARCHAR(255) NULL,
                                        `kilometersDriven` INT NULL,
                                        `damages` VARCHAR(2400) NULL,
                                        `colour` VARCHAR(255) NULL,
                                        `fuelType` VARCHAR(255) NULL,
                                        `locationId` INT NULL,
                                        PRIMARY KEY (`id`),
                                        UNIQUE INDEX `chassisNumber_UNIQUE` (`chassisNumber` ASC) VISIBLE,
                                        CONSTRAINT `locationId_FK`
                                            FOREIGN KEY (`id`)
                                                REFERENCES `bilabonnement`.`locations` (`id`)
                                                ON DELETE NO ACTION
                                                ON UPDATE NO ACTION)
    ENGINE = InnoDB;
