package com.na.bilabonnement;

import com.na.bilabonnement.models.Car;
import com.na.bilabonnement.models.CarStatus;
import com.na.bilabonnement.services.CarService;
import com.na.bilabonnement.services.UserService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class DataCreator {

    private static Scanner fileScanner = null;

    public static void main(String[] args) {
        makeCars();
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

    private static void makeCars() {
        try {
            fileScanner = new Scanner(new File("sql/cars.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Random r = new Random();
        String[] fuelTypes = {"benzin", "diesel", "hybrid(b/el)", "hybrid(d/el)", "elektrisk"};

        CarService cs = new CarService();

        fileScanner.nextLine();

        String chassisNumber, make, model, color;
        int status, carPrice, scrapPrice, registrationfee, co2Emission, kilometersDriven, id = 100;
        while (fileScanner.hasNextLine()) {
            String[] elements = fileScanner.nextLine().split(",");

            chassisNumber = elements[0];
            make = elements[2];
            model = elements[3];
            color = elements[9];

            status = Integer.parseInt(elements[1]);
            carPrice = Integer.parseInt(elements[4]);
            scrapPrice = Integer.parseInt(elements[5]);
            registrationfee = Integer.parseInt(elements[6]);
            co2Emission = Integer.parseInt(elements[7]);
            kilometersDriven = Integer.parseInt(elements[8]);


            cs.createCar(new Car(id,
                    chassisNumber,
                    CarStatus.values()[status - 1],
                    make,
                    model,
                    "",
                    carPrice,
                    scrapPrice,
                    registrationfee,
                    co2Emission,
                    kilometersDriven,
                    "",
                    color,
                    fuelTypes[r.nextInt(4)],
                    1));

            id++;
        }
    }
}
