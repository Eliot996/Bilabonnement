-- For locations
INSERT INTO Locations(name) VALUES("HQ");
INSERT INTO Locations(name) VALUES("DS Forhandler");

-- for Users TODO: fix roles and passwords
INSERT INTO Users(`name`, `password`, `role`, `locationId`, `salt`) Values ("Sofia", "password", "ADMIN", 1, "013456789");
INSERT INTO Users(`name`, `password`, `role`, `locationId`, `salt`) Values ("Lasse", "password", "Dataregistration", 1, "013456789");
INSERT INTO Users(`name`, `password`, `role`, `locationId`, `salt`) Values ("Arboe", "password", "DamageAndRectification", 1, "013456789");
INSERT INTO Users(`name`, `password`, `role`, `locationId`, `salt`) Values ("Mathias", "password", "BusinessDeveloper", 1, "013456789");

