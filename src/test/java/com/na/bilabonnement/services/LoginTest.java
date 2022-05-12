package com.na.bilabonnement.services;

import com.na.bilabonnement.models.User;
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

        final User validUser = userService.getSingleUserByUsername("Sofia");
        final User invalidUser = null;

        String validUname1 = "Sofia";
        String validPass = "kode";

        String invalidUname1 = "sofia";
        String invalidPass1 = "Kode";
        String invalidPass2 = "47e848a689bef815d23c2261394d0e0dd6c7e95b7a831d25ba0c3bbe14535fde";


        //Act
        User testUser1 = userService.login(validUname1, validPass);
        User testUser2 = userService.login(invalidUname1, validPass);
        User testUser3 = userService.login(validUname1, invalidPass1);
        User testUser4 = userService.login(validUname1, invalidPass2);
        User testUser5 = userService.login(invalidUname1, invalidPass2);

        //Assert
        assertEquals(testUser1, validUser);

    }
}