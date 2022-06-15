package com.na.bilabonnement;

import com.na.bilabonnement.services.UserService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataCreator {

    private static Scanner fileScanner = null;

    public static void main(String[] args) {

    }

    private static void makeUsers() {
        // make file scanner
        try {
            fileScanner = new Scanner(new File("sql/user.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        UserService us = new UserService();

        while (fileScanner.hasNextLine()) {
            String username = fileScanner.nextLine();

            us.createUser(username, "kode", 1, 1);
        }
    }
}
