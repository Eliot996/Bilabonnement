package com.na.bilabonnement;

import com.na.bilabonnement.models.*;
import com.na.bilabonnement.services.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class DataCreator {

    private static Scanner fileScanner = null;
    private static final UserService USER_SERVICE = new UserService();
    private static final Random random = new Random();

    public static void main(String[] args) {
        System.out.println("making users");
        makeUsers();
        System.out.println("make users: done");

        System.out.println("making RentalAgreements");
        makeRentalAgreements();
        System.out.println("make RentalAgreements: done");

        System.out.println("making damageReports");
        makeDamageReports();
        System.out.println("make damageReports: done");

        System.out.println("making Damagelines");
        makeDamageLines();
        System.out.println("make damageLines: done");
    }

    private static void makeUsers() {
        try {
            fileScanner = new Scanner(new File("sql/user.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (fileScanner.hasNextLine()) {
            String username = fileScanner.nextLine();

            USER_SERVICE.createUser(username, "kode", random.nextInt(3), 1);
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

    private static void makeDamageReports() {
        CarService cs = new CarService();
        ArrayList<Car> cars = (ArrayList<Car>) cs.getAllCars();

        ArrayList<User> users = (ArrayList<User>) USER_SERVICE.getAllUsers();

        users.removeIf(u -> u.getRole() != UserRole.DAMAGE_AND_RECTIFICATION);

        DamageReportService ds = new DamageReportService();
        for (Car c : cars) {
            if (c.getStatus() == CarStatus.READY_FOR_SALE) {
                ds.createDamageReport(new DamageReport(-1, "",
                        users.get(random.nextInt(users.size() - 1)).getId(), c.getId()));
            }
        }
    }

    private static void makeDamageLines() {
        DamageReportLineService DRLS = new DamageReportLineService();
        ArrayList<DamageReportLine> damageReportLines = new ArrayList<>();
        try {
            fileScanner = new Scanner(new File("sql/damages.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (fileScanner.hasNextLine()) {
            String[] elements = fileScanner.nextLine().split(",");

            damageReportLines.add(
                    new DamageReportLine(-1, -1,
                            elements[0], Integer.parseInt(elements[1]))
            );
        }



        DamageReportService DS = new DamageReportService();
        ArrayList<DamageReport> damageReports = (ArrayList<DamageReport>) DS.getAllDamageReports();

        int count;
        DamageReportLine drl;
        for (DamageReport dr : damageReports) {
            count = random.nextInt(5);

            for (int i = 0; i < count; i++) {
                drl = damageReportLines.get(random.nextInt(damageReportLines.size() - 1));
                DRLS.createDamageReportLine(new DamageReportLine(dr.getId(), i, drl.getDamageNotes(), drl.getPrice()));
            }

        }
     }

     private static void makeRentalAgreements() {
         CarService cs = new CarService();
         ArrayList<Car> cars = (ArrayList<Car>) cs.getAllCars();


         for (Car c : cars) {
             if (c.getStatus() != CarStatus.READY_TO_BE_RENTED) {
                 if (c.getStatus() == CarStatus.BACK_FROM_BEING_RENTED ||c.getStatus() ==  CarStatus.READY_FOR_SALE) {
                     makeOverDueRentalAgreement(c);
                 } else {
                     makeUnderDueRentalAgreement(c);
                 }
             }
         }

     }

    private static void makeOverDueRentalAgreement(Car c) {
        RentalAgreementService ras = RentalAgreementService.getInstance();
        RentalAgreement ra = null;

        if (random.nextDouble() > 0.2) {
            LocalDate endDate = LocalDate.now().minusDays(random.nextInt(360) + 1);
            LocalDate startDate = LocalDate.from(endDate).minusDays(random.nextInt(2160 - 120) + 120);

            ra = new RentalAgreement(-1, c.getId(), random.nextInt(2500) + 2500, startDate, endDate,  RentalType.UNLIMITED);
            ra.setTypeId(1);
        } else {
            LocalDate endDate = LocalDate.now().minusDays(random.nextInt(360) + 1);
            LocalDate startDate = LocalDate.from(endDate).minusDays(150);

            ra = new RentalAgreement(-1, c.getId(), random.nextInt(2500) + 2500, startDate, endDate,  RentalType.LIMITED);
            ra.setTypeId(0);
        }
        ras.create(ra);
    }

    private static void makeUnderDueRentalAgreement(Car c) {
        RentalAgreementService ras = RentalAgreementService.getInstance();
        RentalAgreement ra = null;

        if (random.nextDouble() > 0.2) {
            LocalDate endDate = LocalDate.now().plusDays(random.nextInt(360) + 1);
            LocalDate startDate = LocalDate.from(endDate).minusDays(random.nextInt(2160 - 120) + 120);

            ra = new RentalAgreement(-1, c.getId(), random.nextInt(2500) + 2500, startDate, endDate,  RentalType.UNLIMITED);
            ra.setTypeId(1);
        } else {
            LocalDate endDate = LocalDate.now().plusDays(random.nextInt(360) + 1);
            LocalDate startDate = LocalDate.from(endDate).minusDays(150);

            ra = new RentalAgreement(-1, c.getId(), random.nextInt(2500) + 2500, startDate, endDate,  RentalType.LIMITED);
            ra.setTypeId(0);
        }
        ras.create(ra);
    }
}
