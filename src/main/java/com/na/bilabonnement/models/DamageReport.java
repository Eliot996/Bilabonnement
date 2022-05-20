package com.na.bilabonnement.models;
/*
@Author Lasse
*/
public class DamageReport
{
    private int id;
    private String notes;
    private int technicianId;
    private int carId;



    public DamageReport(int id, String notes, int technicianId, int carId)
    {
        this.id=id;
        this.notes=notes;
        this.technicianId=technicianId;
        this.carId=carId;
    }

    public DamageReport(){
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public int getTechnicianId()
    {
        return technicianId;
    }

    public void setTechnicianId(int technicianId)
    {
        this.technicianId = technicianId;
    }

    public int getCarId()
    {
        return carId;
    }

    public void setCarId(int carId)
    {
        this.carId = carId;
    }
}
