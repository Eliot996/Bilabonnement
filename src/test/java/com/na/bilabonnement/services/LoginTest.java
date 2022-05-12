package com.na.bilabonnement.services;

import com.na.bilabonnement.models.User;
import com.na.bilabonnement.repositories.DummyUserRepo;
import com.na.bilabonnement.repositories.UserRepo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest
{

    /**
          *  @author Arboe(H4ppyN4p)
          */
    @Test
    void login() {
        //Arrange

        UserService userService = new UserService();
        DummyUserRepo dummyUserRepo = new DummyUserRepo();

        userService.setRepo(dummyUserRepo);

        String name = "Sofie";
        String password = "kode";
        int roleId = 1;
        int locationId = 1;

        User validUser = userService.createUser(name, password, roleId, locationId);


        final User invalidUser = null;

        String validUname1 = "Sofie";
        String validPass = "kode";

        String invalidUname = "Sofia";
        String invalidPass = "Kode";

        //Act
        User testUser1 = userService.login(validUname1, validPass);
        User testUser2 = userService.login(validUname1, invalidPass);
        User testUser3 = userService.login(invalidUname, validPass);

        //Assert
        assertEquals(testUser1, validUser);
        assertEquals(testUser2, invalidUser);
        assertEquals(testUser3, invalidUser);




    }
}