package com.na.bilabonnement.models;

import java.io.File;
import java.util.Date;

public class RentalAgreement {

    private int id, carId, price, typeId;
    private Date startDate, endDate;
    private File contract;
    private RentalType type;

    public RentalAgreement(int id, int carId, int price, Date startDate, Date endDate, File contract, RentalType type) {
        this.id = id;
        this.carId = carId;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contract = contract;
        this.type = type;
    }

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public File getContract() {
        return contract;
    }

    public void setContract(File contract) {
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
}
