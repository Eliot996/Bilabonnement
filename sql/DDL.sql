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
                                        `carPrice` INT NULL,
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
                                            FOREIGN KEY (`locationId`)
                                                REFERENCES `bilabonnement`.`locations` (`id`)
                                                ON DELETE NO ACTION
                                                ON UPDATE NO ACTION);


CREATE TABLE `bilabonnement`.`rental_agreements` (
                                                     `id` INT NOT NULL AUTO_INCREMENT,
                                                     `carId` INT NOT NULL,
                                                     `startDate` DATE NOT NULL,
                                                     `endDate` DATE NOT NULL,
                                                     `price` INT NULL,
                                                     `contract` MEDIUMBLOB NULL, -- 16MB max
                                                     `filename`VARCHAR(100) NULL,
                                                     `type` VARCHAR(45) NULL,
                                                     PRIMARY KEY (`id`),
                                                     INDEX `carId_RA_FK_idx` (`carId` ASC) VISIBLE,
                                                     CONSTRAINT `carId_RA_FK`
                                                         FOREIGN KEY (`carId`)
                                                             REFERENCES `bilabonnement`.`cars` (`id`)
                                                             ON DELETE NO ACTION
                                                             ON UPDATE NO ACTION);

CREATE TABLE `damage_report` (
                                 `id` int NOT NULL,
                                 `notes` text,
                                 `technicianId` int DEFAULT NULL,
                                 `carId` int DEFAULT NULL,
                                 PRIMARY KEY (`id`),
                                 KEY `technitianId_idx` (`technicianId`),
                                 KEY `carId_idx` (`carId`),
                                 CONSTRAINT `carId` FOREIGN KEY (`carId`) REFERENCES `cars` (`id`),
                                 CONSTRAINT `technicianId` FOREIGN KEY (`technicianId`) REFERENCES `users` (`id`));

CREATE TABLE `bilabonnement`.`damageline` (
                                              `damageReportId` INT NOT NULL,
                                              `lineNumber` INT NOT NULL,
                                              `damageNotes` VARCHAR(255) NULL,
                                              `price` INT NULL,
                                              PRIMARY KEY (`lineNumber`, damageReportId),
                                              INDEX `damageReportId_idx` (`damageReportId` ASC) VISIBLE,
                                              CONSTRAINT `damageReportId`
                                                  FOREIGN KEY (`damageReportId`)
                                                      REFERENCES `bilabonnement`.`damage_report` (`id`)
                                                      ON DELETE NO ACTION
                                                      ON UPDATE NO ACTION);
