package com.na.bilabonnement.services;

import com.na.bilabonnement.models.User;
import com.na.bilabonnement.models.UserRole;
import com.na.bilabonnement.repositories.interfaces.IUserRepository;
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

    public User login(String username, String password) {
        // Get the user from the database
        User user = repo.getSingleEntityByUsername(username);

        if (user == null) {
            return null;
        }

        if (checkPassword(user.getPassword(), user.getSalt(), password)) {
            return user;
        } else {
            return null;
        }
    }

    /**
     *  @author Tobias Arboe
     */
    private boolean checkPassword(String userPassword, String userSalt, String passwordToCheck) {
        String hashToCheck;

        for (int i = 0; i < PEPPER_CHARACTERS.length(); i++) {
            hashToCheck = hashPassword(PEPPER_CHARACTERS.substring(i, i+1),
                    passwordToCheck,
                    userSalt);

            if (hashToCheck.equals(userPassword)) {
                return true;
            }
        }
        return false;
    }

    /**
    *  @author Mathias(Eliot996)
    *  handels creation of the password, including the hashing of the password
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
     *  @author Tobias Arboe
     */
    public User getUser(String username){
        return repo.getSingleEntityByUsername(username);
    }

    /**
     *  @author Mathias(Eliot996)
     */
    public User getUser(int id){
        return repo.getSingleEntityById(id);
    }

    /**
     *  @author Mathias(Eliot996)
     */
    public List<User> getAllUsers() {
        return repo.getAllEntities();
    }

    /**
     *  @author Sofia
     */
    public boolean deleteUser(int userId){
        return repo.deleteById(userId);
    }

    /**
     *  @author Mathias(Eliot996)
     *  returns a random char as a string
     */
    private String generatePepper() {
        return String.valueOf(
                PEPPER_CHARACTERS.charAt(
                        random.nextInt(PEPPER_CHARACTERS.length())));
    }

    /**
    *  @author Mathias(Eliot996)
    *  generates a random 16 character string, from 94 possible characters
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
    *  hashes the password, with the salt and pepper added unto it
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
    *  converts the byte[] into a string
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

    /**
     *  @author Mathias(Eliot996)
     *  Updates the user, WITHOUT a password
     */
    public User updateUser(int id, String username, int roleID, int locationID) {
        User user = repo.getSingleEntityById(id);
        user.setUsername(username);
        user.setRole(UserRole.values()[roleID]);
        user.setLocationId(locationID);
        return repo.update(user);
    }

    /**
     *  @author Mathias(Eliot996)
     *  Updates the user, WITH a password
     */
    public User updateUser(int id, String username, String password, int roleID, int locationID) {
        String salt = generateSalt();
        String pepper = generatePepper();

        User user = repo.getSingleEntityById(id);
        user.setUsername(username);
        user.setSalt(salt);
        user.setPassword(hashPassword(pepper, password, salt));
        user.setRole(UserRole.values()[roleID]);
        user.setLocationId(locationID);

        return repo.update(user);
    }
}
