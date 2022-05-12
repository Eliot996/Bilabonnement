package com.na.bilabonnement.services;

import com.na.bilabonnement.models.User;
import com.na.bilabonnement.models.UserRole;
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
    void getAllUsers(){
        // Arrange
        UserService userService = new UserService();
        userService.setRepo(new DummyUserRepo());

        int expectedLength = 4;

        // Act
        int actualLength = userService.getAllUsers().size();

        // assert
        assertEquals(expectedLength,actualLength);
    }

    @Test
    void updateUser_username(){
        // Arrange
        UserService userService = new UserService();
        userService.setRepo(new DummyUserRepo());

        String name = "test name";
        String password = "password";
        int roleID = 1;
        int locationID = 0;

        String expected = "changed name";

        User user = userService.createUser(name, password, roleID, locationID);

        // Act
        user = userService.updateUser(user.getId(), expected, roleID, locationID);

        String actual = user.getUsername();

        // assert
        assertEquals(expected,actual);
    }

    @Test
    void updateUser_location(){
        // Arrange
        UserService userService = new UserService();
        userService.setRepo(new DummyUserRepo());

        String name = "test name";
        String password = "password";
        int roleID = 1;
        int locationID = 0;

        int expected = 1;

        User user = userService.createUser(name, password, roleID, locationID);

        // Act
        user = userService.updateUser(user.getId(), password, roleID, expected);

        int actual = user.getLocationId();

        // assert
        assertEquals(expected,actual);
    }

    @Test
    void updateUser_roleID(){
        // Arrange
        UserService userService = new UserService();
        userService.setRepo(new DummyUserRepo());

        String name = "test name";
        String password = "password";
        int roleID = 1;
        int locationID = 0;

        UserRole expected = UserRole.ADMINISTRATOR;

        User user = userService.createUser(name, password, roleID, locationID);

        // Act
        user = userService.updateUser(user.getId(), password, 3, locationID);

        UserRole actual = user.getRole();

        // assert
        assertEquals(expected,actual);
    }

    @Test
    void updateUser_password(){
        // Arrange
        UserService userService = new UserService();
        userService.setRepo(new DummyUserRepo());

        String name = "test name";
        String password = "password";
        int roleID = 1;
        int locationID = 0;

        String newPassword = "new password";

        User user = userService.createUser(name, password, roleID, locationID);

        // Act
        user = userService.updateUser(user.getId(), name, newPassword, roleID, locationID);

        User actual = userService.login(user.getUsername(), newPassword);

        // assert
        assertEquals(user.getId(), actual.getId());
    }


}