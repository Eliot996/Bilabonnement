package com.na.bilabonnement.services;

import com.na.bilabonnement.models.User;
import com.na.bilabonnement.repositories.DummyUserRepo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


//Made by: Tobias Arboe
class UserServiceTest
{

    @Test
    void checkLogin() {
        //Arrange
        UserService userService = new UserService();
        userService.setRepo(new DummyUserRepo());

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

    /**
    *  @author Mathias(Eliot996)
    */
    @Test
    void createUser(){
        // Arrange
        UserService userService = new UserService();
        userService.setRepo(new DummyUserRepo());

        String password = "password";

        // Act
        User createdUser = userService.createUser("name", password, 1, 9);

        // assert
        // to make sure the password constructor works
        assertNotEquals(createdUser.getPassword(), password);
    }

    /**
     *  @author Mathias(Eliot996)
     */
    @Test
    void GetAllUsers(){
        // Arrange
        UserService userService = new UserService();
        userService.setRepo(new DummyUserRepo());

        int expectedLength = 4;

        // Act
        int actualLength = userService.getAllUsers().size();

        // assert
        assertEquals(expectedLength,actualLength);
    }
}