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

        User userOne = userService.createUser("Sofia", "1234", 0, 1);
        User userTwo = userService.createUser("Lasse", "password", 1, 1);
        User userThree = userService.createUser("Arboe", "test", 2, 1);
        User userFour = userService.createUser("Mathias", "Pass1234", 3, 1);

        //Act
        User testUserOneTrue = userService.login("Sofia", "1234");
        User testUserOneFalse = userService.login("Sofia", "123456");
        User testUserOneFalse2 = userService.login("Sofias", "1234");

        User testUserTwoTrue = userService.login("Lasse", "password");
        User testUserTwoFalse = userService.login("Lasse", "Password");
        User testUserTwoFalse2 = userService.login("Lasses", "password");

        User testUserThreeTrue = userService.login("Arboe", "test");
        User testUserThreeFalse = userService.login("Arboe", "testy");
        User testUserThreeFalse2 = userService.login("Arboes", "test");

        User testUserFourTrue = userService.login("Mathias", "Pass1234");
        User testUserFourFalse = userService.login("Mathias", "Pass12345");
        User testUserFourFalse2 = userService.login("Mathiass", "Pass1234");

        //Assert
        assertEquals(userOne, testUserOneTrue);
        assertNull(testUserOneFalse);
        assertNull(testUserOneFalse2);

        assertEquals(userTwo, testUserTwoTrue);
        assertNull(testUserTwoFalse);
        assertNull(testUserTwoFalse2);

        assertEquals(userThree, testUserThreeTrue);
        assertNull(testUserThreeFalse);
        assertNull(testUserThreeFalse2);

        assertEquals(userFour, testUserFourTrue);
        assertNull(testUserFourFalse);
        assertNull(testUserFourFalse2);

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

        userService.createUser("Sofia", "1234", 0, 1);
        userService.createUser("Lasse", "password", 1, 1);
        userService.createUser("Arboe", "test", 2, 1);
        userService.createUser("Mathias", "Pass1234", 3, 1);

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