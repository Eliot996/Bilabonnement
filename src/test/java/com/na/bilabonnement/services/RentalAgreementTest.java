package com.na.bilabonnement.services;

import com.na.bilabonnement.models.RentalAgreement;
import com.na.bilabonnement.models.RentalType;
import com.na.bilabonnement.repositories.DummyCarRepo;
import com.na.bilabonnement.repositories.DummyRentalAgreementRepo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;


public class RentalAgreementTest {

    /**
     *  @author Mathias(Eliot996)
     */
    @Test
    void RentalAgreement_Create() {
        // Arrange
        RentalAgreementService RA_SERVICE = RentalAgreementService.getInstance();
        RA_SERVICE.setRepo(new DummyRentalAgreementRepo());

        int id = -1;
        int carId = 1;
        int price = 3500;
        LocalDate startDate = LocalDate.of(2020, 10, 1);
        LocalDate endDate = LocalDate.of(2022, 10, 1);
        RentalType rentalType = RentalType.UNLIMITED;

        RentalAgreement expected = new RentalAgreement(id, carId, price, startDate, endDate, rentalType);

        // Act
        RentalAgreement actual = RA_SERVICE.create(expected);

        // assert
        assertNotEquals(id, actual.getId());
        assertEquals(carId, actual.getCarId());
        assertEquals(startDate, actual.getStartDate());
        assertEquals(endDate, actual.getEndDate());
        assertEquals(price, actual.getPrice());
        assertEquals(rentalType, actual.getType());
    }

    /**
     *  @author Mathias(Eliot996)
     */
    @Test
    void view_all_rentalAgreements_withBind() {
        // Arrange
        CarService CAR_SERVICE = new CarService();
        CAR_SERVICE.setRepo(new DummyCarRepo());

        RentalAgreementService RA_SERVICE = RentalAgreementService.getInstance();
        RA_SERVICE.setRepo(new DummyRentalAgreementRepo());
        RA_SERVICE.setCarService(CAR_SERVICE);

        int id = -1;
        int carId = 1;
        int price = 3500;
        LocalDate startDate = LocalDate.of(2020, 10, 1);
        LocalDate endDate = LocalDate.of(2022, 10, 1);
        RentalType rentalType = RentalType.UNLIMITED;

        RA_SERVICE.create( new RentalAgreement(id, carId, price, startDate, endDate, rentalType) );

        carId = 2;
        RA_SERVICE.create( new RentalAgreement(id, carId, price, startDate, endDate, rentalType) );

        carId = 3;
        RA_SERVICE.create( new RentalAgreement(id, carId, price, startDate, endDate, rentalType) );

        // Act

        List<RentalAgreement> list = RA_SERVICE.getAll();

        // Assert
        assertEquals(list.get(0).getCar().getId(), list.get(0).getCarId());
        assertEquals(list.get(1).getCar().getId(), list.get(1).getCarId());
        assertEquals(list.get(2).getCar().getId(), list.get(2).getCarId());
    }

    @Test
    void update_rentalAgreement_withFile() {
        // Arrange
        CarService CAR_SERVICE = new CarService();
        CAR_SERVICE.setRepo(new DummyCarRepo());

        RentalAgreementService RA_SERVICE = RentalAgreementService.getInstance();
        RA_SERVICE.setRepo(new DummyRentalAgreementRepo());
        RA_SERVICE.setCarService(CAR_SERVICE);

        RA_SERVICE.create( new RentalAgreement(-1, 1, 3500, LocalDate.of(2020, 10, 1),
                LocalDate.of(2022, 10, 1), RentalType.UNLIMITED) );

        MultipartFile file = new MockMultipartFile("file", "filename.txt", "text/plain", "some text".getBytes());

        RentalAgreement ra = RA_SERVICE.get(0);

        ra.setContract(file);

        // Act
        RA_SERVICE.update(ra);

        // Assert
        assertEquals(1, ra.getTypeId());
    }

    @Test
    void update_rentalAgreement_withoutFile() {
        // Arrange
        CarService CAR_SERVICE = new CarService();
        CAR_SERVICE.setRepo(new DummyCarRepo());

        RentalAgreementService RA_SERVICE = RentalAgreementService.getInstance();
        RA_SERVICE.setRepo(new DummyRentalAgreementRepo());
        RA_SERVICE.setCarService(CAR_SERVICE);

        RA_SERVICE.create( new RentalAgreement(-1, 1, 3500, LocalDate.of(2020, 10, 1),
                LocalDate.of(2022, 10, 1), RentalType.UNLIMITED) );

        MultipartFile file = new MockMultipartFile("null", "null", "null", "".getBytes());

        RentalAgreement ra = RA_SERVICE.get(0);

        ra.setContract(file);

        // Act
        RA_SERVICE.update(ra);

        // Assert
        assertEquals(2, ra.getTypeId());
    }
}
