-- For locations
INSERT INTO Locations(name) VALUES("HQ");
INSERT INTO Locations(name) VALUES("DS Forhandler");

-- for Users
INSERT INTO Users(`name`, `password`, `role`, `locationId`, `salt`)
Values ("Sofia",
        "47e848a689bef815d23c2261394d0e0dd6c7e95b7a831d25ba0c3bbe14535fde",
        "ADMINISTRATOR",
        1,
        'e?A6T=H>{LR!J_6@');
INSERT INTO Users(`name`, `password`, `role`, `locationId`, `salt`)
Values ("Lasse",
        "d946fe694348712d1d3645884c3788d2db48020a061f24cc27240003c49df1c9",
        "DATA_REGISTRATION",
        1,
        'a9j9:BxrDe Siz=G');
INSERT INTO Users(`name`, `password`, `role`, `locationId`, `salt`)
Values ("Arboe",
        "82c2219020526e273a6e907756f6c9baf3dfb7ab4bcc9bb47583f6ae8789eb74",
        "DAMAGE_AND_RECTIFICATION",
        1,
        '%KZph?CX!h(lVS6+');
INSERT INTO Users(`name`, `password`, `role`, `locationId`, `salt`)
Values ("Mathias",
        "789cc7c6c05663635b7c5596ad036a4f0b45a514bc2f9fe4d487f08e217f0566",
        "BUSINESS_DEVELOPER",
        1,
        'v{zF/ $HY{ti|%xP');


-- for cars
INSERT INTO `bilabonnement`.`cars` (`id`, `chassisNumber`, `status`, `make`, `model`, `trimLevel`, `carPrice`, `scrapPrice`, `registrationFee`, `co2Emission`, `kilometersDriven`, `damages`, `colour`, `fuelType`, `locationId`)
    VALUES (1, '12345678', 'READY_FOR_DELIVERY', 'citroen', 'c5', 'tip top', '8000', '5000', '20000', '126', '97000', 'punkteret', 'gul', 'dielsel', '1');
INSERT INTO `bilabonnement`.`cars` (`id`,`chassisNumber`, `status`, `make`, `model`, `trimLevel`, `carPrice`, `scrapPrice`, `registrationFee`, `co2Emission`, `kilometersDriven`, `damages`, `colour`, `fuelType`, `locationId`)
    VALUES (2, '12345', 'READY_TO_BE_RENTED', 'toyota', 'aygo', 'lort', '9000', '6000', '10002', '117', '20594', 'tip tip', 'grå', 'benzin', '2');
INSERT INTO `bilabonnement`.`cars` (`id`,`chassisNumber`, `status`, `make`, `model`, `trimLevel`, `carPrice`, `scrapPrice`, `registrationFee`, `co2Emission`, `kilometersDriven`, `damages`, `colour`, `fuelType`, `locationId`)
    VALUES (3, '123894', 'READY_FOR_SALE', 'renault', 'clio', 'sport', '7000', '4000', '123', '98', '50', 'ok', 'lilla', 'benzin', '2');
INSERT INTO `bilabonnement`.`cars` (`id`,`chassisNumber`, `status`, `make`, `model`, `trimLevel`, `carPrice`, `scrapPrice`, `registrationFee`, `co2Emission`, `kilometersDriven`, `damages`, `colour`, `fuelType`, `locationId`)
    VALUES (4, '12345123123', 'BACK_FROM_BEING_RENTED', 'bmw', 'i4', 'brugt', '5500', '3500', '1234', '0', '197000', 'ingen', 'sort', 'el', '1');
INSERT INTO `bilabonnement`.`cars` (`id`,`chassisNumber`, `status`, `make`, `model`, `trimLevel`, `carPrice`, `scrapPrice`, `registrationFee`, `co2Emission`, `kilometersDriven`, `damages`, `colour`, `fuelType`, `locationId`)
    VALUES (5, 'vsszzzf56343434', 'RENTED', 'bmw', 'x4', 'brugt', '20000', '4000', '1234', '250', '19', 'ingen', 'blå', 'diesel', '1');


-- for rental agreements
INSERT INTO `bilabonnement`.`rental_agreements` (`carId`, `startDate`, `endDate`, `price`, `type`)
    VALUES ('1', '2022-10-02', '2022-12-02', '3500', 'UNLIMITED');
INSERT INTO `bilabonnement`.`rental_agreements` (`carId`, `startDate`, `endDate`, `price`, `type`)
    VALUES ('2', '2022-05-01', '2022-12-01', '4000', 'UNLIMITED');
INSERT INTO `bilabonnement`.`rental_agreements` (`carId`, `startDate`, `endDate`, `price`, `type`)
    VALUES ('3', '2021-01-01', '2022-02-01', '5000', 'UNLIMITED');
INSERT INTO `bilabonnement`.`rental_agreements` (`carId`, `startDate`, `endDate`, `price`, `type`)
    VALUES ('4', '2020-02-01', '2023-01-01', '4000', 'UNLIMITED');

-- for damage reports
INSERT INTO `bilabonnement`.`damage_report` (`id`, `notes`, `technicianId`, `carId`) VALUES ('1', 'skrammer for 100000', '1', '1');
INSERT INTO `bilabonnement`.`damage_report` (`id`, `notes`, `technicianId`, `carId`) VALUES ('2', 'smadret rude', '2', '2');
INSERT INTO `bilabonnement`.`damage_report` (`id`, `notes`, `technicianId`, `carId`) VALUES ('3', 'smadret gearkasse', '3', '3');
INSERT INTO `bilabonnement`.`damage_report` (`id`, `notes`, `technicianId`, `carId`) VALUES ('4', 'smadret bremer', '4', '4');

-- for damage lines
INSERT INTO `bilabonnement`.`damageline` (`lineNumber`, `damageReportId`, `damageNotes`, `price`) VALUES ('1', '1', 'skrammer', '12');
INSERT INTO `bilabonnement`.`damageline` (`lineNumber`, `damageReportId`, `damageNotes`, `price`) VALUES ('1', '2', 'bilsrkammer', '123');
INSERT INTO `bilabonnement`.`damageline` (`lineNumber`, `damageReportId`, `damageNotes`, `price`) VALUES ('1', '3', 'dørskrammer', '1234');
INSERT INTO `bilabonnement`.`damageline` (`lineNumber`, `damageReportId`, `damageNotes`, `price`) VALUES ('1', '4', 'vinduesskrammer', '12345');
