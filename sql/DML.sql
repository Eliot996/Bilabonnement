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


--for cars
INSERT INTO `bilabonnement`.`cars` (`chassisNumber`, `status`, `make`, `model`, `trimLevel`, `scrapPrice`, `registrationFee`, `co2Emission`, `kilometersDriven`, `damages`, `colour`, `fuelType`, `locationId`) VALUES ('12345678', 'klar til levering', 'citroen', 'c5', 'tip top', '5000', '20000', '126', '97000', 'punkteret', 'gul', 'dielsel', '1');
INSERT INTO `bilabonnement`.`cars` (`chassisNumber`, `status`, `make`, `model`, `trimLevel`, `scrapPrice`, `registrationFee`, `co2Emission`, `kilometersDriven`, `damages`, `colour`, `fuelType`, `locationId`) VALUES ('12345', 'ikke klar', 'toyota', 'aygo', 'lort', '6000', '10002', '117', '20594', 'tip tip', 'grå', 'benzin', '2');
INSERT INTO `bilabonnement`.`cars` (`chassisNumber`, `status`, `make`, `model`, `trimLevel`, `scrapPrice`, `registrationFee`, `co2Emission`, `kilometersDriven`, `damages`, `colour`, `fuelType`, `locationId`) VALUES ('123894', 'fint nok', 'renault', 'clio', 'sport', '4000', '123', '98', '50', 'ok', 'lilla', 'benzin', '2');
INSERT INTO `bilabonnement`.`cars` (`chassisNumber`, `status`, `make`, `model`, `trimLevel`, `scrapPrice`, `registrationFee`, `co2Emission`, `kilometersDriven`, `damages`, `colour`, `fuelType`, `locationId`) VALUES ('12345123123', 'god stand', 'bmw', 'i4', 'brugt', '3500', '1234', '0', '197000', 'ingen', 'sort', 'el', '1');
