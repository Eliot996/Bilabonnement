package com.na.bilabonnement.services;

import com.na.bilabonnement.models.RentalAgreement;
import com.na.bilabonnement.models.RentalType;
import com.na.bilabonnement.repositories.DummyRentalAgreementRepo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;


public class RentalAgreementTest {

    @Test
    void RentalAgreement_Create() {
        // Arrange
        RentalAgreementService RA_SERVICE = new RentalAgreementService();
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
}
