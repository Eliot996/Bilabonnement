package com.na.bilabonnement.services;

import com.na.bilabonnement.repositories.DummyUserRepo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


//Made by: Tobias Arboe
class UserServiceTest
{

    @Test
    void checkLogin() {
        //Arrange
        UserService userService = new UserService(new DummyUserRepo());

        final boolean isLoggedIn = true;
        final boolean isNotLoggedIn = false;



        //Act

        boolean testUserOneTrue = userService.checkLogin("Sofia", "1234");
        boolean testUserOneFalse = userService.checkLogin("Sofia", "123456");
        boolean testUserOneFalse2 = userService.checkLogin("Sofias", "1234");

        boolean testUserTwoTrue = userService.checkLogin("Lasse", "password");
        boolean testUserTwoFalse = userService.checkLogin("Lasse", "Password");
        boolean testUserTwoFalse2 = userService.checkLogin("Lasses", "password");

        boolean testUserThreeTrue = userService.checkLogin("Arboe", "test");
        boolean testUserThreeFalse = userService.checkLogin("Arboe", "testy");
        boolean testUserThreeFalse2 = userService.checkLogin("Arboes", "test");

        boolean testUserFourTrue = userService.checkLogin("Mathias", "Pass1234");
        boolean testUserFourFalse = userService.checkLogin("Mathias", "Pass12345");
        boolean testUserFourFalse2 = userService.checkLogin("Mathiass", "Pass1234");

        //Assert
        assertEquals(isLoggedIn, testUserOneTrue);
        assertEquals(isNotLoggedIn, testUserOneFalse);
        assertEquals(isNotLoggedIn, testUserOneFalse2);

        assertEquals(isLoggedIn, testUserTwoTrue);
        assertEquals(isNotLoggedIn, testUserTwoFalse);
        //assertEquals(isNotLoggedIn, testUserTwoFalse2);

        assertEquals(isLoggedIn, testUserThreeTrue);
        assertEquals(isNotLoggedIn, testUserThreeFalse);
       // assertEquals(isNotLoggedIn, testUserThreeFalse2);

        assertEquals(isLoggedIn, testUserFourTrue);
        assertEquals(isNotLoggedIn, testUserFourFalse);
       // assertEquals(isNotLoggedIn, testUserFourFalse2);

    }
}