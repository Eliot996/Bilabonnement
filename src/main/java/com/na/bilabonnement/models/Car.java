package com.na.bilabonnement.models;

/*
@Author Lasse
*/
public class Car {

    private int id;
    private String chassisNumber;
    private String status;
    private String make;
    private String model;
    private String trimLevel;
    private int carPrice;
    private int scrapPrice;
    private int registrationFee;
    private int co2Emission;
    private int kilometersDriven;
    private String damage;
    private String colour;
    private String fuelType;
    private int locationId;


    public Car(int id, String chassisNumber, String status, String make, String model, String trimLevel, int carPrice, int scrapPrice, int registrationFee, int co2Emission, int kilometersDriven, String damage, String colour, String fuelType, int locationId)
    {
        this.id = id;
        this.chassisNumber = chassisNumber;
        this.status = status;
        this.make = make;
        this.model = model;
        this.trimLevel = trimLevel;
        this.carPrice = carPrice;
        this.scrapPrice = scrapPrice;
        this.registrationFee = registrationFee;
        this.co2Emission = co2Emission;
        this.kilometersDriven = kilometersDriven;
        this.damage = damage;
        this.colour = colour;
        this.fuelType = fuelType;
        this.locationId = locationId;
    }

    public Car(){
    }


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getChassisNumber()
    {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber)
    {
        this.chassisNumber = chassisNumber;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getMake()
    {
        return make;
    }

    public void setMake(String make)
    {
        this.make = make;
    }


    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public String getTrimLevel()
    {
        return trimLevel;
    }

    public void setTrimLevel(String trimLevel)
    {
        this.trimLevel = trimLevel;
    }

    public int getCarPrice()
    {
        return carPrice;
    }

    public void setCarPrice(int carPrice)
    {
        this.carPrice = carPrice;
    }

    public int getScrapPrice()
    {
        return scrapPrice;
    }

    public void setScrapPrice(int scrapPrice)
    {
        this.scrapPrice = scrapPrice;
    }

    public int getRegistrationFee()
    {
        return registrationFee;
    }

    public void setRegistrationFee(int registrationFee)
    {
        this.registrationFee = registrationFee;
    }

    public int getCo2Emission()
    {
        return co2Emission;
    }

    public void setCo2Emission(int co2Emission)
    {
        this.co2Emission = co2Emission;
    }

    public int getKilometersDriven()
    {
        return kilometersDriven;
    }

    public void setKilometersDriven(int kilometersDriven)
    {
        this.kilometersDriven = kilometersDriven;
    }

    public String getDamage()
    {
        return damage;
    }

    public void setDamage(String damage)
    {
        this.damage = damage;
    }

    public String getColour()
    {
        return colour;
    }

    public void setColour(String colour)
    {
        this.colour = colour;
    }

    public String getFuelType()
    {
        return fuelType;
    }

    public void setFuelType(String fuelType)
    {
        this.fuelType = fuelType;
    }

    public int getLocationId()
    {
        return locationId;
    }

    public void setLocationId(int locationId)
    {
        this.locationId = locationId;
    }
}
