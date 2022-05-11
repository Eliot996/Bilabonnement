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
                                        `password` VARCHAR(255) NOT NULL,
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

