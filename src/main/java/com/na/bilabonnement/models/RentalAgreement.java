package com.na.bilabonnement.models;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class RentalAgreement {
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private int id, carId, price, typeId;
    private LocalDate startDate, endDate;
    private MultipartFile contract;
    private RentalType type;

    private String startDateString, endDateString;

    /**
     *  @author Mathias(Eliot996)
     */
    public RentalAgreement(int id, int carId, int price, LocalDate startDate, LocalDate endDate,  RentalType type) {
        this.id = id;
        this.carId = carId;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
    }

    /**
     *  @author Mathias(Eliot996)
     */
    public RentalAgreement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public MultipartFile getContract() {
        return contract;
    }

    public void setContract(MultipartFile contract) {
        this.contract = contract;
    }

    public RentalType getType() {
        return type;
    }

    public void setType(RentalType type) {
        this.type = type;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getStartDateString() {
        return startDateString;
    }

    public void setStartDateString(String startDateString) {
        this.startDateString = startDateString;
        startDate = LocalDate.parse(startDateString);
    }

    public String getEndDateString() {
        return endDateString;
    }

    public void setEndDateString(String endDateString) {
        this.endDateString = endDateString;
        endDate = LocalDate.parse(endDateString);
    }
}
