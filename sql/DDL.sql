CREATE SCHEMA `bilabonnement` ;

SELECT `bilabonnement` ;

CREATE TABLE `bilabonnement`.`Location` (
                                            `id` INT NOT NULL AUTO_INCREMENT,
                                            `name` VARCHAR(255) NOT NULL,
                                            PRIMARY KEY (`id`),
                                            UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
                                            UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);


CREATE TABLE `bilabonnement`.`User` (
                                        `id` INT NOT NULL AUTO_INCREMENT,
                                        `name` VARCHAR(255) NOT NULL,
                                        `password` VARCHAR(255) NOT NULL,
                                        `role` VARCHAR(45) NOT NULL,
                                        `locationId` INT NOT NULL,
                                        PRIMARY KEY (`id`),
                                        UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
                                        INDEX `locationId_idx` (`locationId` ASC) VISIBLE,
                                        CONSTRAINT `locationId`
                                            FOREIGN KEY (`locationId`)
                                                REFERENCES `bilabonnement`.`Location` (`id`)
                                                ON DELETE NO ACTION
                                                ON UPDATE NO ACTION);

