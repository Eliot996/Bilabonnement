package com.na.bilabonnement.services;

import com.na.bilabonnement.models.User;
import com.na.bilabonnement.models.UserRole;
import com.na.bilabonnement.repositories.IUserRepository;
import com.na.bilabonnement.repositories.UserRepo;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;

public class UserService {

    private IUserRepository repo = UserRepo.getInstance();
    public void setRepo(IUserRepository repo) {
        this.repo = repo;
    }

    private final String PEPPER_CHARACTERS = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789";
    private final Random random = new Random();

    /**
     *  @author Tobias Arboe
     */
    public boolean checkLogin(String expectedUsername, String expectedPassword){
        boolean loginValidity = false;

        List<User> listOfUsers = repo.getAllEntities();

        for (User user: listOfUsers
             ) {
            if (user.getUsername().equals(expectedUsername) && user.getPassword().equals(expectedPassword) ){
                loginValidity = true;
            }
        }

        return loginValidity;
    }

    /**
    *  @author Mathias(Eliot996)
    */
    public User createUser(String username, String password, int roleID, int locationID) {
        String salt = generateSalt();
        String pepper = generatePepper();

        User newUser = new User(-1, // marked to signify it is temporary id
                username,
                hashPassword(pepper, password, salt),
                salt,
                UserRole.values()[roleID],
                locationID);

        return repo.create(newUser);
    }

    /**
    *  @author Mathias(Eliot996)
    */
    private String generatePepper() {
        return String.valueOf(
                PEPPER_CHARACTERS.charAt(
                        random.nextInt(PEPPER_CHARACTERS.length())));
    }

    /**
    *  @author Mathias(Eliot996)
    */
    private String generateSalt() {
        StringBuilder salt = new StringBuilder();

        for (int i = 0; i < 16; i++) {
            salt.append(Character.toChars(random.nextInt(94) + 32));
        }
        return salt.toString();
    }

    /**
    *  @author Mathias(Eliot996)
    */
    private String hashPassword(String pepper, String password, String salt) {
        MessageDigest digest = null;

        try {
            digest = MessageDigest.getInstance("SHA3-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] encodedHash = digest.digest((pepper + password + salt).getBytes(StandardCharsets.UTF_8));


        return bytesToHex(encodedHash);
    }

    /**
    *  @author Mathias(Eliot996)
    */
    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
