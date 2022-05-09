-- For locations
INSERT INTO Location(name) VALUES("HQ");
INSERT INTO Location(name) VALUES("DS Forhandler");

-- for Users TODO: fix roles and passwords
INSERT INTO User(`name`, `password`, `role`, `locationId`) Values ("Sofia", "password", "ADMIN", 1);
INSERT INTO User(`name`, `password`, `role`, `locationId`) Values ("Lasse", "password", "Dataregistration", 1);
INSERT INTO User(`name`, `password`, `role`, `locationId`) Values ("Arboe", "password", "DamageAndRectification", 1);
INSERT INTO User(`name`, `password`, `role`, `locationId`) Values ("Mathias", "password", "BusinessDeveloper", 1);

